package com.jwebmp.examples.demos.homepage.display.termsandconditions;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.cards.BSCardEvents;

public class GoToTermsAndConditionsEvent
		extends ClickAdapter
		implements BSCardEvents
{
	public GoToTermsAndConditionsEvent()
	{
		this(null);
	}

	public GoToTermsAndConditionsEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("GoToTermsAndConditionsEvent");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		response.addComponent(GuiceContext.get(TermsAndConditionsScreen.class));
	}
}
