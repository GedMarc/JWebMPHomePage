package com.jwebmp.examples.demos.homepage.display.subscribe;

import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.events.click.ClickAdapter;
import com.jwebmp.guiceinjection.GuiceContext;

public class GoToSubscribeEvent
		extends ClickAdapter
{
	public GoToSubscribeEvent()
	{
		this(null);
	}

	public GoToSubscribeEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("GoToSubscribeEvent");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		response.addComponent(GuiceContext.getInstance(SubscribeScreen.class));
	}
}
