package com.jwebmp.examples.demos.homepage.display.events;

import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;

public class EventScreen
		extends PluginDemoScreen
{
	public EventScreen()
	{
		super("HtmlTags", "JWebMP", "Events");

		add("Any event can send back multiple variables when fired. The registered variables are retrievable through the AjaxCall");
		//	add(getCodeBlockJava(FormBasicsDemoScreen.class, "bindingsbasic_4.txt").setID("coce3"));

		add("Watch Angular Variables by utilizing the AngularFeature found on the Page Object");
		//	add(getCodeBlockJava(FormBasicsDemoScreen.class, "bindingsbasic_6.txt").setID("coce4"));

	}
}
