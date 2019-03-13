package com.jwebmp.examples.demos.homepage.display.admin;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import com.jwebmp.plugins.bootstrap4.cards.BSCardEvents;

public class GoToAdministrationScreenEvent
		extends ClickAdapter
		implements BSCardEvents
{
	public GoToAdministrationScreenEvent()
	{
		this(null);
	}

	public GoToAdministrationScreenEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("GoToAdministrationScreenEvent");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		//	response.addComponent(GuiceContext.get(AdministrationScreen.class));
	}
}
