/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwebmp.examples.demos.homepage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jwebmp.entityassist.CoreEntity;
import com.jwebmp.examples.demos.homepage.entities.builders.VisitorsBuilder;
import org.apache.commons.lang3.NotImplementedException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author Marc Magon
 * @since 30 Jul 2017
 */
@Entity(name = "Visitors")
@Table(name = "Visitors",
		uniqueConstraints = @UniqueConstraint(columnNames = {"LocalStorageKey"}))
public class Visitors
		extends CoreEntity<Visitors, VisitorsBuilder, Long>
		implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "VisitorID",
			columnDefinition = "bigint")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long visitorID;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1)
	@Column(nullable = false,
			name = "LocalStorageKey")
	@JsonIgnore
	private String localStorageKey;
	@JsonIgnore
	@OneToMany(mappedBy = "linkedVisitorID",
			fetch = FetchType.LAZY)
	private List<Visitors> visitorsList;
	@JoinColumn(name = "LinkedVisitorID",
			referencedColumnName = "VisitorID")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Visitors linkedVisitorID;
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy = "visitorID",
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Subscribers> subscribersList;
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy = "visitorID",
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Visits> visitsID;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy = "visitorID",
			fetch = FetchType.LAZY)
	private List<SubscriberVisitors> subscriberVisitorsList;

	/*
	 * Constructs a new Visitors
	 */
	public Visitors()
	{
		//Nothing needed
	}

	public static Visitors createNew(String localStorageKey)
	{
		Visitors v = new Visitors();
		v.setLocalStorageKey(localStorageKey);
		v.builder()
		 .setRunDetached(true)
		 .persist(v);
		return v;
	}

	public static Visitors linkToVisitorID(String guid)
	{
		throw new NotImplementedException("Visitors have not yet been linked");
	}

	@Override
	public Long getId()
	{
		return getVisitorID();
	}

	@Override
	public Visitors setId(Long id)
	{
		setVisitorID(id);
		return this;
	}

	public boolean isAdmin()
	{
		if (getSubscribersList() == null || getSubscribersList().isEmpty())
		{
			return false;
		}

		for (Subscribers subs : getSubscribersList())
		{
			if (subs.isAdministrator())
			{
				return true;
			}
		}
		return false;
	}

	public boolean isGuest()
	{
		return getSubscribersList() == null || getSubscribersList().isEmpty();

	}

	public Long getVisitorID()
	{
		return visitorID;
	}

	public void setVisitorID(Long visitorID)
	{
		this.visitorID = visitorID;
	}

	public String getLocalStorageKey()
	{
		return localStorageKey;
	}

	public void setLocalStorageKey(String localStorageKey)
	{
		this.localStorageKey = localStorageKey;
	}

	public List<Visitors> getVisitorsList()
	{
		return visitorsList;
	}

	public void setVisitorsList(List<Visitors> visitorsList)
	{
		this.visitorsList = visitorsList;
	}

	public Visitors getLinkedVisitorID()
	{
		return linkedVisitorID;
	}

	public void setLinkedVisitorID(Visitors linkedVisitorID)
	{
		this.linkedVisitorID = linkedVisitorID;
	}

	public List<Subscribers> getSubscribersList()
	{
		return subscribersList;
	}

	public void setSubscribersList(List<Subscribers> subscribersList)
	{
		this.subscribersList = subscribersList;
	}

	public List<Visits> getVisitsID()
	{
		return visitsID;
	}

	public void setVisitsID(List<Visits> visitsID)
	{
		this.visitsID = visitsID;
	}

	public List<SubscriberVisitors> getSubscriberVisitorsList()
	{
		return subscriberVisitorsList;
	}

	public void setSubscriberVisitorsList(List<SubscriberVisitors> subscriberVisitorsList)
	{
		this.subscriberVisitorsList = subscriberVisitorsList;
	}

	@Override
	public String toString()
	{
		return "Visitor ID =" + visitorID + " ]";
	}
}
