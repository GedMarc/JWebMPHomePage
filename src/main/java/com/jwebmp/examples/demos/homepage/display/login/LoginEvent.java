package com.jwebmp.examples.demos.homepage.display.login;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.exceptions.MissingComponentException;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.components.AlertMessage;
import com.jwebmp.examples.demos.homepage.components.events.DefaultClick;
import com.jwebmp.examples.demos.homepage.display.TopBar;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.examples.demos.homepage.display.menu.West;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.logger.LogFactory;
import com.jwebmp.plugins.bootstrap4.alerts.BSAlertOptions;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginEvent
		extends DefaultClick
{
	private static final Logger log = LogFactory.getLog("LoginEvent");

	public LoginEvent()
	{
		this(null);
	}

	public LoginEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("LoginEvent");
		returnVariable("subscribe");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		if (call.getVariable("subscribe") == null)
		{
			throw new RuntimeException("Didn't enter anything ;) This what an error dialog looks like from the server...");
		}
		Subscribers newSubs = call.getVariable("subscribe")
		                          .as(Subscribers.class);
		boolean subs = false;
		try
		{
			Optional<Subscribers> sub = Subscribers.findByLoginAndPassword(newSubs.getEmailAddress(), newSubs.getPassword());
			if (sub.isPresent())
			{
				Subscribers subscriber = sub.get();
				if (!subscriber.isConfirmed())
				{
					throw new UnsupportedOperationException("Looks like you're still waiting for a confirmation email.<br/>Please check your Spam or Junk folder");
				}

				SessionProperties properties = GuiceContext.getInstance(SessionProperties.class);
				properties.setSubscriber(sub.get());
				properties.setLoggedIn(true);

				subscriber.setLogInActive(true);
				subscriber.setRememberMe(newSubs.isRememberMe());

				subscriber.update();
				subscriber.builder()
				          .getEntityManager()
				          .flush();

				Visitors v = properties.getVisitor();

				//Do all the DB funky stuff in the backend
				LoginAsync la = new LoginAsync(newSubs, GuiceContext.getInstance(SessionProperties.class), sub.get(), v);
				Executors.defaultThreadFactory()
				         .newThread(la)
				         .start();

				response.addReaction(new AjaxResponseReaction().setReactionTitle("Logged In")
				                                               .setReactionData("You were successfully logged in")
				                                               .setResponseType(AjaxResponseType.Success)
				                                               .setReactionType(ReactionType.DialogDisplay));

				response.addComponent(GuiceContext.getInstance(HomePage.class));
				response.addComponent(GuiceContext.getInstance(TopBar.class));
				response.addComponent(GuiceContext.getInstance(West.class));
			}
		}
		catch (UnsupportedOperationException e)
		{
			response.addReaction(new AjaxResponseReaction().setReactionTitle("Awaiting your action")
			                                               .setReactionData(e.getMessage())
			                                               .setResponseType(AjaxResponseType.Warning)
			                                               .setReactionType(ReactionType.DialogDisplay));
		}
		catch (MissingComponentException e)
		{
			response.addComponent(new AlertMessage("Not quite right, try again...", BSAlertOptions.Alert_Dark));
		}
		catch (Exception e)
		{
			response.addComponent(new AlertMessage("That's a little odd, looks like me this time. It's all been logged.", BSAlertOptions.Alert_Dark));
			log.log(Level.SEVERE, "Error", e);
		}
		//Clear the login fields
		response.addDto("subscribe", null);
	}
}
