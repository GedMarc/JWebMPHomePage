package com.jwebmp.examples.demos.homepage.display.about.c3p0module;

import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.H4;
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

		all.add(new H3<>("C3P0 Data Source Pooling"));
		all.add(new H4<>("JPA e.g. <code>Non-JTA</code>"));

		all.add("Baeldung give a great overview of this library <a target=\"_blank\" href=\"https://www.baeldung.com/hibernate-c3p0\">here</a>," +
		        "Information about C3P0 itself can be found <a target=\"_blank\" href=\"https://www.mchange.com/projects/c3p0/\">here</a>");

		all.add("The C3P0 Module allows you to utilize Hibernates implementation of C3P0 Data Pooling straight out the box .");
		all.add("There isn't any configuration required by default, and all settings can be manipulated using the C3P0ConnectionPropertiesReader," +
		        "<br/>or directly in your <code>Database Module</code>");

		all.add("This module is highly recommended for pure JPA applications");

		addSourceToContainer(C3P0Screen.class, "c3p0config.txt", SourceCodeLanguages.Java, all);

		add(all);
	}
}
