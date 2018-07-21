package com.jwebmp.examples.demos.homepage;

import com.google.inject.servlet.SessionScoped;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.Visitors;

@SessionScoped
public class SessionProperties
		extends JavaScriptPart<SessionProperties>
{
	private final DivSimple<?> workspace = new DivSimple<>();
	/**
	 * The logged in visitor
	 */
	private Visitors visitor;
	/**
	 * If the user is logged in
	 */
	private boolean loggedIn;
	/**
	 * The set subscriber
	 */
	private Subscribers subscriber;
	/**
	 * GUID
	 */
	private String guid;

	public DivSimple<?> getWorkspace()
	{
		return workspace;
	}

	public Visitors getVisitor()
	{
		return visitor;
	}

	public void setVisitor(Visitors visitor)
	{
		this.visitor = visitor;
	}

	public boolean isLoggedIn()
	{
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn)
	{
		this.loggedIn = loggedIn;
	}

	public Subscribers getSubscriber()
	{
		return subscriber;
	}

	public void setSubscriber(Subscribers subscriber)
	{
		this.subscriber = subscriber;
	}

	public String getGuid()
	{
		return guid;
	}

	public void setGuid(String guid)
	{
		this.guid = guid;
	}
}
