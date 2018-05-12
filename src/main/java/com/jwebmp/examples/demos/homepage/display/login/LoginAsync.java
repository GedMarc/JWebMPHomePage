package com.jwebmp.examples.demos.homepage.display.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.persist.Transactional;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.entities.SubscriberVisitors;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.UserActivity;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.examples.demos.homepage.entities.enumerations.UserActivityGroup;
import za.co.mmagon.guiceinjection.GuiceContext;

public class LoginAsync
		implements Runnable
{
	private Subscribers newSubs;
	private Subscribers sub;
	private SessionProperties props;
	private Visitors visitors;

	public LoginAsync()
	{
	}

	public LoginAsync(Subscribers newSubs, SessionProperties props, Subscribers sub, Visitors visitors)
	{
		this.newSubs = newSubs;
		this.props = props;
		this.sub = sub;
		this.visitors = visitors;
	}

	@Override
	@Transactional
	public void run()
	{
		sub.builder()
		   .getEntityManager()
		   .merge(sub);

		visitors.builder()
		        .getEntityManager()
		        .merge(visitors);

		if (new SubscriberVisitors().builder()
		                            .findBySubscriberAndVisitorID(sub, visitors)
		                            .getAll()
		                            .isEmpty())
		{
			SubscriberVisitors.create(sub, visitors);
		}

		if (newSubs.isRememberMe())
		{
			sub.setRememberMe(true);
		}
		sub.setLogInActive(true);
		sub.update();

		UserActivity ua = new UserActivity();
		ua.setActivity("LoggedIn");
		ua.setActivityGroup(UserActivityGroup.LoginRequest);
		ua.setDescription("The user logged in");

		try
		{
			ua.setJson(GuiceContext.getInstance(ObjectMapper.class)
			                       .writeValueAsString(visitors));
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
		ua.setImageUrl("fa fa-sign-in");
		ua.setTitle("Logged In");
		ua.persist();
	}
}
