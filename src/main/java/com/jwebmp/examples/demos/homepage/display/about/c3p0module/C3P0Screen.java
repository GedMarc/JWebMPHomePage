package com.jwebmp.examples.demos.homepage.display.about.c3p0module;

import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

public class C3P0Screen
		extends DisplayPart<C3P0Screen>
{
	public C3P0Screen()
	{
		BSCardBody all = new BSCardBody();
		all.addClass("row bg-dark");
		all.addStyle("display:grid;overflow-y:auto;");
		all.add("The C3P0 Module allows you to utilize Hibernates implementation of C3P0 Data Pooling out the box.");
		all.add("There isn't any configuration required by default, and all settings can be manipulated using the C3P0ConnectionPropertiesReader");
		all.add("This module is highly recommended for pure JPA applications");

		addSourceToContainer(C3P0Screen.class, "c3p0config.txt", SourceCodeLanguages.Java, all);

		add(all);
	}
}
