package com.jwebmp.examples.demos.homepage.display.login;

import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.events.click.ClickAdapter;
import com.jwebmp.guicedinjection.GuiceContext;

public class GoToLoginEvent
		extends ClickAdapter
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
