package com.jwebmp.examples.demos.homepage.display.forgotpassword;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.events.click.ClickAdapter;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.guicedee.guicedinjection.GuiceContext;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

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

				ForgotPasswordAsync async = GuiceContext.get(ForgotPasswordAsync.class);
				async.setNewSubs(newSubs);
				async.setSubs(subs.get());
				async.run();
			}
			catch (Exception e)
			{
				Logger.getLogger("ForgotPassword")
				      .log(Level.WARNING, "Can't store user activity audit for password reset?", e);
			}
		}

		response.addReaction(new AjaxResponseReaction().setReactionTitle("Forgot Password Requested")
		                                               .setReactionData(
				                                               "We've sent an email with instructions to reset your password. " +
				                                               "<br/><br/> If you can't find it, try checking your Junk/Spam mail folders.")
		                                               .setResponseType(AjaxResponseType.Success)
		                                               .setReactionType(ReactionType.DialogDisplay));

		response.addDto("subscribe", null);
	}
}
