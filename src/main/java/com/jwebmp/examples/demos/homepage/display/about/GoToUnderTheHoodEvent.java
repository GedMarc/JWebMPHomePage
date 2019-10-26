package com.jwebmp.examples.demos.homepage.display.about;

import com.google.inject.Singleton;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.cards.BSCardEvents;

@Singleton
public class GoToUnderTheHoodEvent
		extends ClickAdapter
		implements BSCardEvents
{
	public GoToUnderTheHoodEvent()
	{
		this(null);
	}

	public GoToUnderTheHoodEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("GoToAbout");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		response.addComponent(GuiceContext.get(SPIScreen.class));
	}
}
