package com.jwebmp.examples.demos.homepage.display.forgotpassword;

import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.*;
import com.jwebmp.events.click.ClickAdapter;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import lombok.extern.java.Log;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.logging.Level;

@Log
public class ForgotPasswordEvent
		extends ClickAdapter
{
	public ForgotPasswordEvent()
	{
		this(null);
	}

	public ForgotPasswordEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("ForgotPasswordEvent");
		returnVariable("subscribe");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		Subscribers newSubs = call.getVariable("subscribe")
		                          .as(Subscribers.class);
		Optional<Subscribers> subs = Subscribers.findByEmail(newSubs.getEmailAddress());
		if (subs.isPresent())
		{
			try
			{
				Subscribers subscriber = subs.get();
				subscriber.setConfirmationKey(UUID.randomUUID()
				                                  .toString());
				subscriber.setConfirmed(false);
				subscriber.setRememberMe(false);
				subscriber.setLogInActive(false);
				subscriber.update();

				Executors.defaultThreadFactory()
				         .newThread(new ForgotPasswordAsync(newSubs, subs.get()))
				         .start();

			}
			catch (Exception e)
			{
				log.log(Level.WARNING, "Can't store user activity audit for password reset?", e);
			}
		}

		response.addReaction(new AjaxResponseReaction().setReactionTitle("Forgot Password Requested")
		                                               .setReactionData(
				                                               "We've sent an email with instructions to reset your password. " + "<br/><br/> If you can't find it, try checking your Junk/Spam mail folders.")
		                                               .setResponseType(AjaxResponseType.Success)
		                                               .setReactionType(ReactionType.DialogDisplay));

		response.addDto("subscribe", null);
	}
}
