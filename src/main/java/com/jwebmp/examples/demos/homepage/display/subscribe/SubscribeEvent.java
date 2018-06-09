package com.jwebmp.examples.demos.homepage.display.subscribe;


import com.google.inject.Key;
import com.google.inject.name.Names;
import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.*;
import com.jwebmp.events.click.ClickAdapter;
import com.jwebmp.examples.demos.homepage.display.TopBar;
import com.jwebmp.examples.demos.homepage.display.menu.West;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.guiceinjection.GuiceContext;
import com.jwebmp.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.utilities.StaticStrings;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InvalidAttributeValueException;
import javax.persistence.NoResultException;
import java.util.Map;
import java.util.Optional;

public class SubscribeEvent
		extends ClickAdapter
{
	public SubscribeEvent()
	{
		this(null);
	}

	public SubscribeEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("SubmitSubscribeEvent");
		if (component != null)
		{
			component.addDto("subscribe", new JavaScriptPart());
		}
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		Map<String, String> localStorage = GuiceContext.getInstance(Key.get(Map.class, Names.named("LocalStorage")));
		if (localStorage.containsKey("jwamsmk"))
		{
			try
			{
				Subscribers newSubs = call.getVariable("subscribe")
				                          .as(Subscribers.class);
				newSubs.isValid();

				if (!newSubs.getPassword()
				            .equals(newSubs.getConfirmPassword()))
				{
					response.addReaction(new AjaxResponseReaction("Invalid Confirm Password", "Please ensure that the original password and confirm password are correct.",
					                                              ReactionType.DialogDisplay, AjaxResponseType.Danger));
					return;
				}

				String guid = localStorage.get(StaticStrings.LOCAL_STORAGE_PARAMETER_KEY);

				Optional<Visitors> returningVisitor = new Visitors().builder()
				                                                    .findByGuid(guid)
				                                                    .get();
				if (!returningVisitor.isPresent())
				{
					throw new InstanceAlreadyExistsException("This already exists");
				}

				Subscribers s = newSubs.create(returningVisitor.get())
				                       .get();

				response.addComponent(GuiceContext.get(TopBar.class));
				response.addComponent(GuiceContext.get(West.class));

			}
			catch (NoResultException nre)
			{
				//NEVER EVER EVER
			}
			catch (InstanceAlreadyExistsException e)
			{
				response.addReaction(
						new AjaxResponseReaction("Whoops", "You've already been registered. We've resent your confirmation email for security reasons.", ReactionType.DialogDisplay,
						                         AjaxResponseType.Danger));
			}
			catch (InvalidAttributeValueException e)
			{
				response.addReaction(new AjaxResponseReaction("Whoops", e.getMessage(), ReactionType.DialogDisplay, AjaxResponseType.Danger));
			}
		}
	}
}
