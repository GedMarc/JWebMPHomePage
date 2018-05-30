package com.jwebmp.examples.demos.homepage.display.about;

import com.google.inject.Singleton;
import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.events.click.ClickAdapter;
import com.jwebmp.plugins.bootstrap4.cards.BSCardEvents;
import za.co.mmagon.guiceinjection.GuiceContext;


@Singleton
public class GoToAboutScreenEvent
		extends ClickAdapter
		implements BSCardEvents
{
	public GoToAboutScreenEvent()
	{
		this(null);
	}

	public GoToAboutScreenEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("GoToAbout");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		response.addComponent(GuiceContext.getInstance(AboutJWebMPScreen.class));
	}
}
