package com.jwebmp.examples.demos.homepage.display.forgotpassword;

import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.events.click.ClickAdapter;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.display.TopBar;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.examples.demos.homepage.display.menu.West;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.logger.LogFactory;

import javax.management.InstanceAlreadyExistsException;
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

		Subscribers subscriber = GuiceContext.getInstance(SessionProperties.class)
		                                     .getSubscriber();
		try
		{
			subscriber.changePassword(newSubs);
		}
		catch (InstanceAlreadyExistsException e)
		{
			log.log(Level.SEVERE, "How did this happen? Unknown session subscriber on set password", e);
		}

		response.addComponent(GuiceContext.getInstance(HomePage.class));
		response.addComponent(GuiceContext.getInstance(TopBar.class));
		response.addComponent(GuiceContext.getInstance(West.class));

	}
}
