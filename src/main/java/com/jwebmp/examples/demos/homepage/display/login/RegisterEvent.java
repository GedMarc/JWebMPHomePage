package com.jwebmp.examples.demos.homepage.display.login;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.*;
import com.entityassist.EntityAssistException;
import com.jwebmp.examples.demos.homepage.components.events.DefaultClick;
import com.jwebmp.examples.demos.homepage.display.TopBar;
import com.jwebmp.examples.demos.homepage.display.menu.West;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.modal.BSModal;

import java.util.Map;
import java.util.Optional;

import static com.jwebmp.core.utilities.StaticStrings.*;

public class RegisterEvent
		extends DefaultClick
{
	@Inject
	@Named("LocalStorage")
	Map<String, String> localStorage;

	public RegisterEvent()
	{
		this(null);
	}

	public RegisterEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("SubmitSubscribeEvent");
		returnVariable("subscribe");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{

		if (localStorage.containsKey(LOCAL_STORAGE_PARAMETER_KEY))
		{
			try
			{
				Subscribers newSubs = GuiceContext.get(Subscribers.class);
				call.getVariable("subscribe")
				    .update(newSubs);

				newSubs.isValid();

				String guid = localStorage.get(LOCAL_STORAGE_PARAMETER_KEY);
				Optional<Visitors> returningVisitor = new Visitors().builder()
				                                                    .findByGuid(guid)
				                                                    .inActiveRange()
				                                                    .get();

				Optional<Subscribers> subscriberExists = new Subscribers().builder()
				                                                          .findByEmail(newSubs.getEmailAddress())
				                                                          .inActiveRange()
				                                                          .get();
				if (subscriberExists.isPresent())
				{
					//new LogoutEvent().onClick(call, response);
					throw new EntityAssistException("Email Address already registered");
				}
				Optional<Subscribers> subscriber = newSubs.create(returningVisitor.get());
				if (subscriber.isPresent())
				{

					response.addReaction(new AjaxResponseReaction("Please confirm your email address",
					                                              "We've sent through a confirmation email, You'll have partial access until you verify. The link expires in 2 hours.",
					                                              ReactionType.DialogDisplay, AjaxResponseType.Success));
					response.addComponent(GuiceContext.get(TopBar.class));
					response.addComponent(GuiceContext.get(West.class));
				}
				else
				{
					response.addReaction(new AjaxResponseReaction("There was an error creating your user",
					                                              "There was an error creating  your user, please try again or contact support on support@jwebswing.com",
					                                              ReactionType.DialogDisplay, AjaxResponseType.Success));
				}
				BSModal<?> modal = new BSModal<>();
				modal.setID("confirmPasswordModal");
				response.getFeatures()
				        .add(modal.createHideFeature());
			}
			catch (EntityAssistException e)
			{
				response.addReaction(new AjaxResponseReaction("Whoops", "You've already been registered. You can use the Forgot Password option to reset your password.",
				                                              ReactionType.DialogDisplay, AjaxResponseType.Danger));
			}
			catch (Exception e)
			{
				response.addReaction(new AjaxResponseReaction("Whoops", e.getMessage(), ReactionType.DialogDisplay, AjaxResponseType.Danger));
			}
			finally
			{
				response.addDto("subscribe", null);
			}
		}
	}
}
