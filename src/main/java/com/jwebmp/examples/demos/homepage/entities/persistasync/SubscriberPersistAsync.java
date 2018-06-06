package com.jwebmp.examples.demos.homepage.entities.persistasync;


import com.jwebmp.FileTemplates;
import com.jwebmp.SessionHelper;
import com.jwebmp.examples.demos.homepage.MailService;
import com.jwebmp.examples.demos.homepage.display.confirmemail.ConfirmEmailAddressScreen;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.guiceinjection.GuiceContext;

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

	@Override
	public void run()
	{
		StringBuilder confirmEmailTemplate = FileTemplates.getFileTemplate(ConfirmEmailAddressScreen.class, "confirmemailtemplate", "PleaseConfirmEmail.html");

		String linkUrl = SessionHelper.getServerPath();
		linkUrl += "?p=ConfirmEmailAddressScreen&key=" + subscribers.getConfirmationKey();
		String emailTemplate = confirmEmailTemplate.toString()
		                                           .replaceAll("%%LINKADDRESS%%", linkUrl);
		GuiceContext.getInstance(MailService.class)
		            .sendEmail("no-reply@jwebmp.com", "Confirmation Email", emailTemplate, subscribers.getEmailAddress());
	}
}

