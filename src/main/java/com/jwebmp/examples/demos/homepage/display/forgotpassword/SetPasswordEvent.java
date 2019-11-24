package com.jwebmp.examples.demos.homepage.display.forgotpassword;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import com.entityassist.EntityAssistException;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.display.TopBar;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.examples.demos.homepage.display.menu.West;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.guicedee.guicedinjection.GuiceContext;
import com.guicedee.logger.LogFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SetPasswordEvent
		extends ClickAdapter
{
	private static final Logger log = LogFactory.getLog("SetPasswordEvent");

	public SetPasswordEvent()
	{
		this(null);
	}

	public SetPasswordEvent(ComponentHierarchyBase component)
	{
		super(component);
		returnVariable("subscriber");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		Subscribers newSubs = call.getVariable("subscriber")
		                          .as(Subscribers.class);

		Subscribers subscriber = GuiceContext.get(SessionProperties.class)
		                                     .getSubscriber();
		try
		{
			subscriber.changePassword(newSubs);
		}
		catch (EntityAssistException e)
		{
			log.log(Level.SEVERE, "How did this happen? Unknown session subscriber on set password", e);
		}

		response.addComponent(GuiceContext.get(HomePage.class));
		response.addComponent(GuiceContext.get(TopBar.class));
		response.addComponent(GuiceContext.get(West.class));

	}
}
