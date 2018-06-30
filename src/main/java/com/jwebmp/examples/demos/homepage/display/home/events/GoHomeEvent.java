package com.jwebmp.examples.demos.homepage.display.home.events;

import com.jwebmp.Component;
import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.events.click.ClickAdapter;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.guicedinjection.GuiceContext;

public class GoHomeEvent
		extends ClickAdapter
{
	public GoHomeEvent()
	{
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
