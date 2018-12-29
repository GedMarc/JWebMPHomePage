package com.jwebmp.examples.demos.homepage.display.login;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.angular.directives.events.click.ClickEvent;
import com.jwebmp.guicedinjection.GuiceContext;

public class GoToLoginEvent
		extends ClickEvent
{
	public GoToLoginEvent()
	{
		this(null);
	}

	public GoToLoginEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("GoToLoginEvent");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		response.addComponent(GuiceContext.getInstance(LoginPart.class));
	}
}
