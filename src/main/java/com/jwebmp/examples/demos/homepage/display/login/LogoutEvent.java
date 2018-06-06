package com.jwebmp.examples.demos.homepage.display.login;

import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.*;
import com.jwebmp.events.click.ClickAdapter;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.display.TopBar;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.examples.demos.homepage.display.menu.West;
import com.jwebmp.guiceinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.cards.BSCardEvents;

import java.util.concurrent.Executors;

public class LogoutEvent
		extends ClickAdapter
		implements BSCardEvents
{
	public LogoutEvent()
	{
	}

	public LogoutEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("LogoutEvent");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		SessionProperties sp = GuiceContext.getInstance(SessionProperties.class);

		LogoutAsync la = new LogoutAsync(sp.getSubscriber(), sp, sp.getVisitor());
		Executors.defaultThreadFactory()
		         .newThread(la)
		         .start();

		sp.setLoggedIn(false);
		sp.setSubscriber(null);
		sp.setVisitor(null);

		response.addComponent(GuiceContext.getInstance(TopBar.class));
		response.addComponent(GuiceContext.getInstance(West.class));
		response.addComponent(GuiceContext.getInstance(HomePage.class));

		response.addReaction(new AjaxResponseReaction().setReactionTitle("Logged Out")
		                                               .setReactionData("You have been successfully logged out.")
		                                               .setResponseType(AjaxResponseType.Success)
		                                               .setReactionType(ReactionType.DialogDisplay));
	}
}
