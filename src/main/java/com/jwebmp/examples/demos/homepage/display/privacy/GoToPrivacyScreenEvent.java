package com.jwebmp.examples.demos.homepage.display.privacy;

import com.google.inject.Singleton;
import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.events.click.ClickAdapter;
import com.jwebmp.guiceinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.cards.BSCardEvents;


@Singleton
public class GoToPrivacyScreenEvent
		extends ClickAdapter
		implements BSCardEvents
{
	public GoToPrivacyScreenEvent()
	{
		this(null);
	}

	public GoToPrivacyScreenEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("GoToPrivacyScreenEvent");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		response.addComponent(GuiceContext.getInstance(PrivacyScreen.class));
	}
}
