package com.jwebmp.examples.demos.homepage.display.subscribe;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import com.jwebmp.guicedinjection.GuiceContext;

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
		response.addComponent(GuiceContext.get(SubscribeScreen.class));
	}
}
