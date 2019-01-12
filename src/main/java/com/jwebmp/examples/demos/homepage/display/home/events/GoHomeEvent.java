package com.jwebmp.examples.demos.homepage.display.home.events;

import com.jwebmp.core.Component;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.guicedinjection.GuiceContext;

public class GoHomeEvent
		extends ClickAdapter
{
	public GoHomeEvent()
	{
		super(null);
	}

	public GoHomeEvent(Component comp)
	{
		super(comp);
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		response.addComponent(GuiceContext.getInstance(HomePage.class));
	}
}
