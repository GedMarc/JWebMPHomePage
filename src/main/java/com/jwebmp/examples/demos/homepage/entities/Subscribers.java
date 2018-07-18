/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwebmp.examples.demos.homepage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jwebmp.entityassist.CoreEntity;
import com.jwebmp.entityassist.converters.LocalDateTimestampAttributeConverter;
import com.jwebmp.entityassist.enumerations.ActiveFlag;
import com.jwebmp.entityassist.enumerations.Operand;
import com.jwebmp.examples.demos.homepage.Passwords;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.entities.builders.SubscribersBuilder;
import com.jwebmp.examples.demos.homepage.entities.persistasync.SubscriberPersistAsync;
import com.jwebmp.exceptions.MissingComponentException;
import com.jwebmp.guicedinjection.GuiceContext;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheResult;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InvalidAttributeValueException;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Marc Magon
 * @since 30 Jul 2017
 */
@Entity(name = "Subscribers")
@Table(name = "Subscribers")
public class Subscribers
		extends CoreEntity<Subscribers, SubscribersBuilder, Long>
		implements Serializable
{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "SubscriberID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subscriberID;
	@Basic()
	@Size(min = 1,
			max = 100)
	@Column(length = 100,
			name = "FirstName")
	private String firstName;
	@Size(max = 100)
	@Column(length = 100,
			name = "LastName")
	private String lastName;
	@Column(name = "BirthDate",
			columnDefinition = "datetime")
	@Convert(converter = LocalDateTimestampAttributeConverter.class)
	private LocalDate birthDate;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1,
			max = 180)
	@Column(nullable = false,
			length = 180,
			name = "EmailAddress")
	private String emailAddress;
	@Basic(optional = false)
	@NotNull
	@Column(nullable = false,
			name = "Unsubscribed")
	private boolean unsubscribed;
	@Basic(optional = false)
	@NotNull
	@Column(nullable = false,
			name = "Administrator")
	private boolean administrator;
	@JsonIgnore
	@JoinColumn(name = "VisitorID",
			referencedColumnName = "VisitorID",
			nullable = false)
	@ManyToOne(optional = false,
			fetch = FetchType.LAZY)
	private Visitors visitorID;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy = "subscriberID",
			fetch = FetchType.LAZY)
	private List<SubscriberVisitors> subscriberVisitorsList;
	@Column(name = "Password")
	private String password;
	@Column(name = "RandomSalt")
	private String randomSalt;
	@Transient
	private String confirmPassword;
	@Column(name = "Confirmed")
	private boolean confirmed;
	@Column(name = "ConfirmationKey")
	private String confirmationKey;
	@Column(name = "ContactDetails")
	private String contactDetails;
	@Column(name = "RememberMe")
	private boolean rememberMe;
	@Column(name = "LogInActive")
	private boolean logInActive;

	/*
	 * Constructs a new Subscribers
	 */
	public Subscribers()
	{
		//Nothing needed
	}

	public Subscribers(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public Subscribers(String emailAddress, String password)
	{
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public static Optional<Subscribers> findByLoginAndPassword(String login, String password) throws MissingComponentException
	{
		Optional<Subscribers> subs = findByEmail(login);
		if (subs.isPresent())
		{
			Subscribers s = subs.get();

			if (!ActiveFlag.getVisibleRangeAndUp()
			               .contains(s.getActiveFlag()))
			{
				throw new MissingComponentException("Invalid Login Details");
			}
			if (s.getEffectiveToDate()
			     .isBefore(LocalDateTime.now()))
			{
				throw new MissingComponentException("Invalid Login Details");
			}

			String saltString = s.getRandomSalt();
			byte[] salt = saltString.getBytes();
			byte[] saltDecrypted = Passwords.integerDecrypt(saltString);
			char[] pass = password.toCharArray();
			byte[] passHashed = Passwords.hash(pass, saltDecrypted);
			String saltEncrypted = Passwords.integerEncrypt(salt);
			String passEncrypted = Passwords.integerEncrypt(passHashed);

			if (!s.getPassword()
			      .equals(passEncrypted))
			{
				throw new MissingComponentException("Invalid Login Details");
			}

			GuiceContext.getInstance(SessionProperties.class)
			            .setLoggedIn(true);
			GuiceContext.getInstance(SessionProperties.class)
			            .setSubscriber(s);
			GuiceContext.getInstance(SessionProperties.class)
			            .setVisitor(s.getVisitorID());
			return subs;
		}
		else
		{
			throw new MissingComponentException("Invalid Login Details");
		}
	}

	public static Optional<Subscribers> findByEmail(String emailAddress)
	{
		Subscribers s = null;

		long count = new Subscribers().builder()
		                              .where(Subscribers_.emailAddress, Operand.Equals, emailAddress)
		                              .inActiveRange()
		                              .getCount();
		if (count == 0)
		{
			return Optional.empty();
		}
		return new Subscribers().builder()
		                        .findByEmail(emailAddress)
		                        .get();
	}

	@CacheResult
	public static Optional<Subscribers> findByIDNumber(@CacheKey String idNumber)
	{
		Optional option = Optional.empty();
		EntityManager em = GuiceContext.getInstance(EntityManager.class);
		List<String> salts = em.createNativeQuery("select distinct RandomSalt from Subscribers")
		                       .getResultList();
		for (String saltString : salts)
		{
			Subscribers s = null;
			if (saltString == null)
			{
				continue;
			}

			byte[] salt = saltString.getBytes();
			byte[] saltDecrypted = Passwords.integerDecrypt(saltString);
			char[] pass = idNumber.toCharArray();
			byte[] passHashed = Passwords.hash(pass, saltDecrypted);

			String saltEncrypted = Passwords.integerEncrypt(salt);
			String passEncrypted = Passwords.integerEncrypt(passHashed);

			try
			{
				s = new Subscribers();
				return s.builder()
				        .findByEmail(passEncrypted)
				        .get();
			}
			catch (NoResultException nre)
			{

			}
		}

		return option;
	}

	@Override
	public Long getId()
	{
		return getSubscriberID();
	}

	@Override
	public Subscribers setId(Long id)
	{
		setSubscriberID(id);
		return this;
	}

	@CacheRemove()
	public Optional<Subscribers> create(Visitors visitor) throws InstanceAlreadyExistsException
	{
		if (findByEmail(getEmailAddress()).isPresent())
		{
			throw new InstanceAlreadyExistsException("This user has already been registered");
		}

		setAdministrator(false);
		setSubscriberID(0L);
		setVisitorID(visitor);
		setUnsubscribed(false);
		byte[] salt = System.getProperty("systemSalt") != null ? System.getProperty("systemSalt")
		                                                               .getBytes() : Passwords.getNextSalt();
		char[] pass = getPassword().toCharArray();
		byte[] passHashed = Passwords.hash(pass, salt);

		String saltEncrypted = Passwords.integerEncrypt(salt);
		String passEncrypted = Passwords.integerEncrypt(passHashed);
		setPassword(passEncrypted);
		setRandomSalt(saltEncrypted);

		setConfirmationKey(UUID.randomUUID()
		                       .toString());
		setConfirmed(false);
		builder().setRunDetached(true)
		         .persist(this);

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(new SubscriberPersistAsync(this));
		executorService.shutdown();

		Optional optional = Optional.ofNullable(getSubscriberID().equals(0L) ? null : this);

		return optional;
	}

	@CacheRemove()
	public Optional<Subscribers> changePassword(Subscribers subscriber) throws InstanceAlreadyExistsException
	{
		if (!findByEmail(getEmailAddress()).isPresent())
		{
			throw new InstanceAlreadyExistsException("No Such Email");
		}

		byte[] salt = System.getProperty("systemSalt") != null ? System.getProperty("systemSalt")
		                                                               .getBytes() : Passwords.getNextSalt();
		char[] pass = subscriber.getPassword()
		                        .toCharArray();
		byte[] passHashed = Passwords.hash(pass, salt);

		String saltEncrypted = Passwords.integerEncrypt(salt);
		String passEncrypted = Passwords.integerEncrypt(passHashed);
		setPassword(passEncrypted);
		setRandomSalt(saltEncrypted);

		setConfirmationKey(UUID.randomUUID()
		                       .toString());
		setConfirmed(false);
		update();
		Optional optional = Optional.ofNullable(getSubscriberID().equals(0L) ? null : this);

		return optional;
	}

	@CacheRemove()
	public Optional<Subscribers> updateRequireConfirmation(Visitors visitor)
	{
		setAdministrator(false);
		setVisitorID(visitor);
		setUnsubscribed(false);
		byte[] salt = System.getProperty("systemSalt") != null ? System.getProperty("systemSalt")
		                                                               .getBytes() : Passwords.getNextSalt();
		char[] pass = getPassword().toCharArray();
		byte[] passHashed = Passwords.hash(pass, salt);

		String saltEncrypted = Passwords.integerEncrypt(salt);
		String passEncrypted = Passwords.integerEncrypt(passHashed);
		setPassword(passEncrypted);
		setRandomSalt(saltEncrypted);

		setConfirmationKey(UUID.randomUUID()
		                       .toString());

		setConfirmed(false);
		builder().setRunDetached(true)
		         .persist(this);

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(new SubscriberPersistAsync(this));
		executorService.shutdown();

		Optional optional = Optional.ofNullable(getSubscriberID().equals(0L) ? null : this);

		return optional;
	}

	public boolean isValid() throws InvalidAttributeValueException
	{
		if (getPassword() == null)
		{
			throw new InvalidAttributeValueException("Password entry seems to have expired.");
		}
		if (getConfirmPassword() == null)
		{
			throw new InvalidAttributeValueException("Confirm password entry was empty. We allow this to show how multiple forms can be bound to the same DTO.");
		}
		if (!getPassword().equals(getConfirmPassword()))
		{
			throw new InvalidAttributeValueException("Passwords do not match");
		}
		if (getEmailAddress() == null)
		{
			throw new InvalidAttributeValueException("Email Address is required");
		}
		return true;
	}

	public Long getSubscriberID()
	{
		return subscriberID;
	}

	public void setSubscriberID(Long subscriberID)
	{
		this.subscriberID = subscriberID;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public LocalDate getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public boolean isUnsubscribed()
	{
		return unsubscribed;
	}

	public void setUnsubscribed(boolean unsubscribed)
	{
		this.unsubscribed = unsubscribed;
	}

	public boolean isAdministrator()
	{
		return administrator;
	}

	public void setAdministrator(boolean administrator)
	{
		this.administrator = administrator;
	}

	public Visitors getVisitorID()
	{
		return visitorID;
	}

	public void setVisitorID(Visitors visitorID)
	{
		this.visitorID = visitorID;
	}

	public List<SubscriberVisitors> getSubscriberVisitorsList()
	{
		return subscriberVisitorsList;
	}

	public void setSubscriberVisitorsList(List<SubscriberVisitors> subscriberVisitorsList)
	{
		this.subscriberVisitorsList = subscriberVisitorsList;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getRandomSalt()
	{
		return randomSalt;
	}

	public void setRandomSalt(String randomSalt)
	{
		this.randomSalt = randomSalt;
	}

	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}

	public boolean isConfirmed()
	{
		return confirmed;
	}

	public void setConfirmed(boolean confirmed)
	{
		this.confirmed = confirmed;
	}

	public String getConfirmationKey()
	{
		return confirmationKey;
	}

	public void setConfirmationKey(String confirmationKey)
	{
		this.confirmationKey = confirmationKey;
	}

	public String getContactDetails()
	{
		return contactDetails;
	}

	public void setContactDetails(String contactDetails)
	{
		this.contactDetails = contactDetails;
	}

	public boolean isRememberMe()
	{
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe)
	{
		this.rememberMe = rememberMe;
	}

	public boolean isLogInActive()
	{
		return logInActive;
	}

	public void setLogInActive(boolean logInActive)
	{
		this.logInActive = logInActive;
	}

	@Override
	public String toString()
	{
		return "Subscriber [" + subscriberID + " ] - " + getFirstName() + " " + getLastName();
	}
}
