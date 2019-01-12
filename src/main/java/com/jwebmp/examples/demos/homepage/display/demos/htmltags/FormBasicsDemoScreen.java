package com.jwebmp.examples.demos.homepage.display.demos.htmltags;

import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;

public class FormBasicsDemoScreen
		extends PluginDemoScreen
{

	public FormBasicsDemoScreen()
	{
		super("HtmlTags", "Base", "Form Basics");


		addComponentTile("Form", "The default HTML form tag").addStyle("cursor:default");
		addComponentTile("AngularForm", "A form collection to assign success and failure classes to an input").addStyle("cursor:default");
		addComponentTile("FieldSet", "Groups form fields together").addStyle("cursor:default");

		getAdditionalsRight().add(getCodeBlockJava(FormBasicsDemoScreen.class, "formbasics.txt"));
	}

}
