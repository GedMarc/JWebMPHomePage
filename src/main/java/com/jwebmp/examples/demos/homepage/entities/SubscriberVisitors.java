/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwebmp.examples.demos.homepage.entities;


import com.jwebmp.entityassist.CoreEntity;
import com.jwebmp.examples.demos.homepage.entities.builders.SubscriberVisitorsBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Marc Magon
 * @since 30 Jul 2017
 */
@Entity(name = "SubscriberVisitors")
@Table(name = "SubscriberVisitors")
public class SubscriberVisitors
		extends CoreEntity<SubscriberVisitors, SubscriberVisitorsBuilder, Long>
		implements Serializable
{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "SubscriberVisitorsID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subscriberVisitorID;

	@JoinColumn(name = "SubscriberID",
			referencedColumnName = "SubscriberID")
	@ManyToOne(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private Subscribers subscriberID;

	@ManyToOne(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JoinColumn(name = "VisitorID",
			referencedColumnName = "VisitorID")
	private Visitors visitorID;

	/*
	 * Constructs a new Subscribers
	 */
	public SubscriberVisitors()
	{
		//Nothing needed
	}

	public static void create(Subscribers subs, Visitors visitor)
	{
		SubscriberVisitors sv = new SubscriberVisitors();
		sv.setSubscriberID(subs);
		sv.setVisitorID(visitor);
		sv.builder()
		  .setRunDetached(true)
		  .persist(sv);
	}

	@Override
	public Long getId()
	{
		return getSubscriberVisitorID();
	}

	@Override
	public SubscriberVisitors setId(Long id)
	{
		setSubscriberVisitorID(id);
		return this;
	}

	public Long getSubscriberVisitorID()
	{
		return subscriberVisitorID;
	}

	public void setSubscriberVisitorID(Long subscriberVisitorID)
	{
		this.subscriberVisitorID = subscriberVisitorID;
	}

	public Subscribers getSubscriberID()
	{
		return subscriberID;
	}

	public void setSubscriberID(Subscribers subscriberID)
	{
		this.subscriberID = subscriberID;
	}

	public Visitors getVisitorID()
	{
		return visitorID;
	}

	public void setVisitorID(Visitors visitorID)
	{
		this.visitorID = visitorID;
	}
}
