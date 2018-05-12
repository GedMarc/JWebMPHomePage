package com.jwebmp.examples.demos.homepage;

import com.google.inject.servlet.SessionScoped;
import com.jwebmp.base.html.DivSimple;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.htmlbuilder.javascript.JavaScriptPart;
import lombok.Data;

@Data
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
}
