package com.jwebmp.examples.demos.homepage.display.contactus;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.angular.directives.events.click.ClickEvent;
import com.jwebmp.plugins.bootstrap4.cards.BSCardEvents;

public class GoToContactUsScreenEvent
		extends ClickEvent
		implements BSCardEvents
{
	public GoToContactUsScreenEvent()
	{
		this(null);
	}

	public GoToContactUsScreenEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("GoToContactUsScreenEvent");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		//response.addComponent(GuiceContext.getInstance(ContactUsScreen.class));
	}
}
