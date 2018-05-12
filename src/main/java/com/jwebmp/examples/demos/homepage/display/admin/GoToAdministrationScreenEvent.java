package com.jwebmp.examples.demos.homepage.display.admin;


import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.plugins.bootstrap4.cards.BSCardEvents;
import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.events.click.ClickAdapter;
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
		//	response.addComponent(GuiceContext.getInstance(AdministrationScreen.class));
	}
}
