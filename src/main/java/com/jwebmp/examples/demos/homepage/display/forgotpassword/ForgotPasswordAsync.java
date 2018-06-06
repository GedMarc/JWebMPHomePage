package com.jwebmp.examples.demos.homepage.display.forgotpassword;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.persist.Transactional;
import com.jwebmp.FileTemplates;
import com.jwebmp.SessionHelper;
import com.jwebmp.examples.demos.homepage.MailService;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.UserActivity;
import com.jwebmp.examples.demos.homepage.entities.enumerations.UserActivityGroup;
import com.jwebmp.guiceinjection.GuiceContext;
import lombok.extern.java.Log;
import org.apache.commons.lang3.exception.ExceptionUtils;

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
	@Transactional
	public void run()
	{
		UserActivity ua = new UserActivity();
		ua.setActivity("ForgotPassword");
		ua.setActivityGroup(UserActivityGroup.ForgotPassword);
		ua.setDescription("Forgot Password was requested for [" + newSubs.getEmailAddress() + "]");
		try
		{
			ObjectMapper om = GuiceContext.getInstance(ObjectMapper.class);
			om.getSerializationConfig()
			  .without(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			ua.setJson(om.writeValueAsString(subs.getVisitorID()));
		}
		catch (JsonProcessingException e)
		{
			ua.setJson(ExceptionUtils.getStackTrace(e));
		}
		ua.setImageUrl("fa fa-sign-in");
		ua.setTitle("Forgot Password");
		ua.setReadMoreUrl("#");
		ua.persist();

		StringBuilder forgotpasswordtemplate = FileTemplates.getFileTemplate(ForgotPasswordEvent.class, "forgotpasswordtemplate", "ForgotPasswordEmail.html");

		String linkUrl = SessionHelper.getServerPath();
		linkUrl += "?p=ForgotPassword&key=" + subs.getConfirmationKey();

		String emailTemplate = forgotpasswordtemplate.toString()
		                                             .replaceAll("%%LINKADDRESS%%", linkUrl);
		GuiceContext.getInstance(MailService.class)
		            .sendEmail("no-reply@jwebmp.com", "Forgot Password Email", emailTemplate, subs.getEmailAddress());
	}
}
