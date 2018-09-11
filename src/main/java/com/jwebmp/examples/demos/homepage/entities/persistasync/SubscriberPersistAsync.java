package com.jwebmp.examples.demos.homepage.entities.persistasync;

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.SessionHelper;
import com.jwebmp.examples.demos.homepage.MailService;
import com.jwebmp.examples.demos.homepage.db.HomePageDB;
import com.jwebmp.examples.demos.homepage.display.confirmemail.ConfirmEmailAddressScreen;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.guicedpersistence.db.annotations.Transactional;

public class SubscriberPersistAsync
		implements Runnable
{
	private Subscribers subscribers;

	public SubscriberPersistAsync()
	{
	}

	public SubscriberPersistAsync(Subscribers subscribers)
	{
		this.subscribers = subscribers;
	}

	public Subscribers getSubscribers()
	{
		return subscribers;
	}

	public SubscriberPersistAsync setSubscribers(Subscribers subscribers)
	{
		this.subscribers = subscribers;
		return this;
	}

	@Override
	public void run()
	{
		doIt();
		StringBuilder confirmEmailTemplate = FileTemplates.getFileTemplate(ConfirmEmailAddressScreen.class, "confirmemailtemplate", "PleaseConfirmEmail.html");

		String linkUrl = SessionHelper.getServerPath();
		linkUrl += "?p=ConfirmEmailAddressScreen&key=" + subscribers.getConfirmationKey();
		String emailTemplate = confirmEmailTemplate.toString()
		                                           .replaceAll("%%LINKADDRESS%%", linkUrl);
		GuiceContext.getInstance(MailService.class)
		            .sendEmail("no-reply@jwebmp.com", "JWebMP Subscription Confirmation Email", emailTemplate, subscribers.getEmailAddress());
	}

	@Transactional(entityManagerAnnotation = HomePageDB.class)
	public void doIt()
	{
		subscribers.builder()
		           .setRunDetached(true)
		           .persist(subscribers);
	}

}

