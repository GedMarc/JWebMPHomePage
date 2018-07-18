package com.jwebmp.examples.demos.homepage;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailService
		implements AutoCloseable
{
	private static final Logger log = Logger.getLogger("MailService");

	private Session session;
	private Properties properties;

	private Store mailStore;

	private List<String> toAddresses;
	private String fromAddress;
	private List<String> ccAddresses;
	private List<String> bccAddresses;

	private String subject;
	private String body;
	private Map<String, byte[]> attachments;

	@NotNull
	private String user = "test";
	@NotNull
	private String credentials = "test";

	public MailService()
	{
		this(null);
	}

	/**
	 * Constructs with the given mail session properties
	 *
	 * @param mailSessionProperties
	 */
	public MailService(Properties mailSessionProperties)
	{
		properties = mailSessionProperties;
		if (properties == null)
		{
			properties = new Properties();
			try
			{
				properties.load(HomePageStartup.class.getResourceAsStream("mailserver.properties"));
				user = properties.getProperty("mail.smtp.user");
				credentials = properties.getProperty("password");
			}
			catch (IOException e)
			{
				properties.setProperty("mail.smtp.host", "localhost");
			}
			properties.put("mail.transport.protocol", "smtp");
			properties.put("mail.smtp.port", "25");
			properties.put("mail.smtp.auth", "true");
		}
		Authenticator auth = new SMTPAuthenticator();
		session = Session.getInstance(properties, auth);
		session.setDebug(true);
		try
		{
			mailStore = session.getStore("smtp");
		}
		catch (NoSuchProviderException e)
		{
			log.log(Level.SEVERE, "Unable to build the mail bean", e);
			mailStore = null;
		}
	}

	public MailService sendEmail(String fromAddresses, String subject, String body, String... toAddresses)
	{
		setFromAddress(fromAddresses);
		setSubject(subject);
		setBody(body);
		getBccAddresses().addAll(Arrays.asList(toAddresses));
		setSubject(subject);
		setBody(body);
		transport();
		return this;
	}

	@NotNull
	private List<String> getBccAddresses()
	{
		if (bccAddresses == null)
		{
			bccAddresses = new ArrayList<>();
		}
		return bccAddresses;
	}

	/**
	 * Transports the message across the border
	 *
	 * @return
	 */
	public MailService transport()
	{
		try
		{
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(getFromAddress()));

			InternetAddress[] to = new InternetAddress[getToAddresses().size()];
			List<String> addresses = getToAddresses();
			for (int i = 0; i < addresses.size(); i++)
			{
				String a = addresses.get(i);
				to[i] = InternetAddress.parse(a)[0];
			}

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, to);
			addresses = getBccAddresses();
			InternetAddress[] bcc = new InternetAddress[addresses.size()];
			for (int i = 0; i < addresses.size(); i++)
			{
				String a = addresses.get(i);
				bcc[i] = InternetAddress.parse(a)[0];
			}
			message.setRecipients(Message.RecipientType.BCC, bcc);
			addresses = getCcAddresses();
			InternetAddress[] cc = new InternetAddress[addresses.size()];
			for (int i = 0; i < addresses.size(); i++)
			{
				String a = addresses.get(i);
				cc[i] = InternetAddress.parse(a)[0];
			}
			message.setRecipients(Message.RecipientType.CC, cc);
			// Set Subject: header field
			message.setSubject(getSubject());
			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
			// Now set the actual message
			messageBodyPart.setContent(getBody(), "text/html");
			// Create a multipar message
			Multipart multipart = new MimeMultipart();
			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			getAttachments().forEach((key, value) ->
			                         {
				                         try
				                         {
					                         multipart.addBodyPart(addAttachment(key, value));
				                         }
				                         catch (MessagingException e)
				                         {
					                         log.log(Level.SEVERE, "Unable to add attachment to mail mime body part", e);
				                         }
			                         });
			// Send the complete message parts
			message.setContent(multipart, "text/html");
			// Send message
			Transport.send(message);
			log.fine("Sent email [" + getSubject() + "] to [" + getToAddresses() + "] successfully.");
		}
		catch (MessagingException e)
		{
			log.log(Level.SEVERE, "Error constructing Mime Message", e);
		}
		return this;
	}

	private String getFromAddress()
	{
		return fromAddress;
	}

	@NotNull
	private List<String> getToAddresses()
	{
		if (toAddresses == null)
		{
			toAddresses = new ArrayList<>();
		}
		return toAddresses;
	}

	@NotNull
	private List<String> getCcAddresses()
	{
		if (ccAddresses == null)
		{
			ccAddresses = new ArrayList<>();
		}
		return ccAddresses;
	}

	public String getSubject()
	{
		return subject;
	}

	public MailService setSubject(String subject)
	{
		this.subject = subject;
		return this;
	}

	@NotNull
	public String getBody()
	{
		if (body == null)
		{
			body = "";
		}
		return body;
	}

	public MailService setBody(String body)
	{
		this.body = body;
		return this;
	}

	@NotNull
	private Map<String, byte[]> getAttachments()
	{
		if (attachments == null)
		{
			attachments = new LinkedHashMap<>();
		}
		return attachments;
	}

	/**
	 * Adds the attachment to the outgoing mail
	 *
	 * @param filename
	 * @param content
	 *
	 * @return
	 *
	 * @throws MessagingException
	 */
	private MimeBodyPart addAttachment(String filename, byte[] content) throws MessagingException
	{
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		DataSource source = new ByteArrayDataSource(content, MimetypesFileTypeMap.getDefaultFileTypeMap()
		                                                                         .getContentType(filename));
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		return messageBodyPart;
	}

	@NotNull
	public MailService setFromAddress(String fromAddress)
	{
		this.fromAddress = fromAddress;
		return this;
	}

	public MailService addToAddress(String toAddress)
	{
		getToAddresses().add(toAddress);
		return this;
	}

	public MailService addBCCAddress(String toAddress)
	{
		getBccAddresses().add(toAddress);
		return this;
	}

	public MailService addCCAddress(String toAddress)
	{
		getCcAddresses().add(toAddress);
		return this;
	}

	public MailService attachFile(String filename, byte[] contents)
	{
		getAttachments().put(filename, contents);
		return this;
	}

	protected Store getMailStore()
	{
		return mailStore;
	}

	/**
	 * Returns the mail session
	 *
	 * @return
	 */
	@NotNull
	public Session getSession()
	{
		return session;
	}

	/**
	 * Gets the properties for the mail session
	 *
	 * @return
	 */
	@NotNull
	public Properties getProperties()
	{
		return properties;
	}

	@Override
	public void close()
	{
		try
		{
			session.getStore()
			       .close();
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}

	private class SMTPAuthenticator
			extends javax.mail.Authenticator
	{
		@Override
		public PasswordAuthentication getPasswordAuthentication()
		{
			return new PasswordAuthentication(user, credentials);
		}
	}
}
