package com.jwebmp.examples.demos.homepage.display.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.UserActivity;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.examples.demos.homepage.entities.enumerations.UserActivityGroup;
import com.jwebmp.guiceinjection.GuiceContext;

import java.util.concurrent.Callable;

public class LogoutAsync
		implements Callable, Runnable
{
	private Subscribers sub;
	private SessionProperties props;
	private Visitors visitors;

	public LogoutAsync()
	{
	}

	public LogoutAsync(Subscribers sub, SessionProperties props, Visitors visitors)
	{
		this.sub = sub;
		this.props = props;
		this.visitors = visitors;
	}

	@Override
	public void run()
	{
		try
		{
			call();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Object call()
	{
		UserActivity ua = new UserActivity();
		ua.setActivity("LoggedOut");
		ua.setActivityGroup(UserActivityGroup.LoginRequest);
		ua.setDescription("The user logged out");
		ua.setTitle("Logged Out");

		visitors = new Visitors().builder()
		                         .getEntityManager()
		                         .merge(visitors);

		try
		{
			ua.setJson(GuiceContext.getInstance(ObjectMapper.class)
			                       .writeValueAsString(visitors));
		}
		catch (JsonProcessingException e)
		{

		}
		ua.setImageUrl("fa fa-sign-out");
		ua.setReadMoreUrl("Not Applicable");
		ua.persist();

		Subscribers s = new Subscribers().builder()
		                                 .getEntityManager()
		                                 .merge(sub);
		s.setLogInActive(false);
		s.setRememberMe(false);
		s.update();
		return s;
	}
}
