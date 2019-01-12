package com.jwebmp.examples.demos.homepage.display.privacy;

import com.google.inject.Singleton;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.cards.BSCardEvents;

@Singleton
public class GoToChatRoomScreenEvent
		extends ClickAdapter
		implements BSCardEvents
{
	public GoToChatRoomScreenEvent()
	{
		this(null);
	}

	public GoToChatRoomScreenEvent(ComponentHierarchyBase component)
	{
		super(component);
		setID("GoToPrivacyScreenEvent");
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		response.addComponent(GuiceContext.getInstance(ChatRoomPrivacyScreen.class));
	}
}
