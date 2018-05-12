package com.jwebmp.examples.demos.homepage.display.forgotpassword;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.*;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.examples.demos.homepage.entities.SubscriberVisitors;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.UserActivity;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.examples.demos.homepage.entities.enumerations.UserActivityGroup;
import com.jwebmp.plugins.bootstrap4.cards.BSCardEvents;
import lombok.extern.java.Log;
import za.co.mmagon.entityassist.enumerations.ActiveFlag;
import za.co.mmagon.guiceinjection.GuiceContext;
import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.*;
import com.jwebmp.events.click.ClickAdapter;
import com.jwebmp.examples.demos.homepage.Passwords;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.display.TopBar;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.examples.demos.homepage.display.menu.West;
import com.jwebmp.examples.demos.homepage.entities.SubscriberVisitors;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.UserActivity;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.examples.demos.homepage.entities.enumerations.UserActivityGroup;
import com.jwebmp.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.plugins.bootstrap4.cards.BSCardEvents;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class ResetPasswordEvent
		extends ClickAdapter
		implements BSCardEvents
{
	public ResetPasswordEvent()
	{
		this(null);
	}

	public ResetPasswordEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("ResetPasswordEvent");
		returnVariable("subscribe");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		Subscribers newSubs = (Subscribers) call.getVariable("subscribe")
		                                        .as(Subscribers.class);

		if (!(newSubs.getPassword() == null || newSubs.getPassword()
		                                              .equals(newSubs.getConfirmPassword()) || newSubs.getPassword()
		                                                                                              .isEmpty()))
		{
			response.addReaction(new AjaxResponseReaction().setReactionTitle("Password not valid")
			                                               .setReactionData("Please make sure you enter the same for password and confirm password")
			                                               .setResponseType(AjaxResponseType.Success)
			                                               .setReactionType(ReactionType.DialogDisplay));
		}
		else
		{


			Optional<Subscribers> subs = Optional.ofNullable(GuiceContext.getInstance(SessionProperties.class)
			                                                             .getSubscriber());
			try
			{
				if (subs.isPresent())
				{
					UserActivity ua = new UserActivity();
					ua.setActivity("ResetPassword");
					ua.setActivityGroup(UserActivityGroup.LoginRequest);
					ua.setDescription("A password reset was performed for [" + newSubs.getEmailAddress() + "]");
					Visitors v = GuiceContext.getInstance(Visitors.class);
					try
					{
						ua.setJson(GuiceContext.getInstance(ObjectMapper.class)
						                       .writeValueAsString(v));
					}
					catch (JsonProcessingException e)
					{
						e.printStackTrace();
					}
					ua.setImageUrl("fa fa-sign-in");
					ua.setTitle("Password Reset Complete");
					ua.persist();

					Subscribers s = subs.get();
					String saltString = s.getRandomSalt();
					byte[] salt = saltString.getBytes();
					byte[] saltDecrypted = Passwords.integerDecrypt(saltString);
					char[] pass = newSubs.getPassword()
					                     .toCharArray();
					byte[] passHashed = Passwords.hash(pass, saltDecrypted);
					String saltEncrypted = Passwords.integerEncrypt(salt);
					String passEncrypted = Passwords.integerEncrypt(passHashed);

					subs.get()
					    .setPassword(passEncrypted);
					subs.get()
					    .setConfirmationKey(UUID.randomUUID()
					                            .toString());
					subs.get()
					    .setConfirmed(false);
					subs.get()
					    .setActiveFlag(ActiveFlag.Active);
					subs.get()
					    .setLogInActive(false);
					subs.get()
					    .setRememberMe(false);
					subs.get()
					    .update();

					if (new SubscriberVisitors().builder()
					                            .findBySubscriberAndVisitorID(subs.get(), v)
					                            .getAll()
					                            .isEmpty())
					{
						SubscriberVisitors.create(subs.get(), v);
					}
					response.addComponent(GuiceContext.getInstance(HomePage.class));

					response.addReaction(new AjaxResponseReaction().setReactionTitle("Password Reset Completed")
					                                               .setReactionData("Your password has successfully been reset")
					                                               .setResponseType(AjaxResponseType.Success)
					                                               .setReactionType(ReactionType.DialogDisplay));
				}
				else
				{
					response.addReaction(new AjaxResponseReaction().setReactionTitle("Password Reset Couldnt be completed")
					                                               .setReactionData("Unfortunately your password could not be reset.")
					                                               .setResponseType(AjaxResponseType.Success)
					                                               .setReactionType(ReactionType.DialogDisplay));
				}
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, "Can't store user activity audit for password reset?", e);
				response.addReaction(new AjaxResponseReaction().setReactionTitle("Password Reset Couldnt be completed")
				                                               .setReactionData("Unfortunately your password could not be reset.")
				                                               .setResponseType(AjaxResponseType.Success)
				                                               .setReactionType(ReactionType.DialogDisplay));
			}

			//	response.addComponent(GuiceContext.getInstance(ForgotPasswordScreen.class));

			response.addComponent(GuiceContext.getInstance(TopBar.class));
			response.addComponent(GuiceContext.getInstance(West.class));
		}

		response.addDto("subscribe", new JavaScriptPart());
	}
}
