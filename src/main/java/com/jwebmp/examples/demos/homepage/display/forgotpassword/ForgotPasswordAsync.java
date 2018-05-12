package com.jwebmp.examples.demos.homepage.display.forgotpassword;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwebmp.FileTemplates;
import com.jwebmp.SessionHelper;
import com.jwebmp.examples.demos.homepage.MailService;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.UserActivity;
import com.jwebmp.examples.demos.homepage.entities.enumerations.UserActivityGroup;
import lombok.extern.java.Log;
import za.co.mmagon.guiceinjection.GuiceContext;

@Log
public class ForgotPasswordAsync
		implements Runnable
{
	private Subscribers newSubs;
	private Subscribers subs;

	public ForgotPasswordAsync()
	{
	}

	public ForgotPasswordAsync(Subscribers newSubs, Subscribers sub)
	{
		this.newSubs = newSubs;
		subs = sub;
	}

	@Override
	public void run()
	{
		UserActivity ua = new UserActivity();
		ua.setActivity("ForgotPassword");
		ua.setActivityGroup(UserActivityGroup.ForgotPassword);
		ua.setDescription("Forgot Password was requested for [" + newSubs.getEmailAddress() + "]");
		try
		{
			ua.setJson(GuiceContext.getInstance(ObjectMapper.class)
			                       .writeValueAsString(subs.getVisitorID()));
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
		ua.setImageUrl("fa fa-sign-in");
		ua.setTitle("Forgot Password");
		ua.persist();

		StringBuilder forgotpasswordtemplate = FileTemplates.getFileTemplate(ForgotPasswordEvent.class, "forgotpasswordtemplate", "ForgotPasswordEmail.html");

		String linkUrl = SessionHelper.getServerPath();
		linkUrl += "?p=ForgotPassword&key=" + subs.getConfirmationKey();

		String emailTemplate = forgotpasswordtemplate.toString()
		                                             .replaceAll("%%LINKADDRESS%%", linkUrl);
		GuiceContext.getInstance(MailService.class)
		            .sendEmail("noreply@jwebmp.com", "Forgot Password Email", emailTemplate, subs.getEmailAddress());
	}
}
