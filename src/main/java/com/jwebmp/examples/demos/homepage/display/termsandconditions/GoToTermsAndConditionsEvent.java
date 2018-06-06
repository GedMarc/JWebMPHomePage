package com.jwebmp.examples.demos.homepage.display.termsandconditions;

import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.events.click.ClickAdapter;
import com.jwebmp.guiceinjection.GuiceContext;
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
		response.addComponent(GuiceContext.getInstance(TermsAndConditionsScreen.class));
	}
}
