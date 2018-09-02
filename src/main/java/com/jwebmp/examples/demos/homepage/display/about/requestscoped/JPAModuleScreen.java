package com.jwebmp.examples.demos.homepage.display.about.requestscoped;

import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

public class JPAModuleScreen
		extends DisplayPart<JPAModuleScreen>
{
	public JPAModuleScreen()
	{
		BSCardBody all = new BSCardBody();
		all.addClass("row bg-dark");
		all.addStyle("display:grid;overflow-y:auto;");
		all.add("JPA is the most basic, and probably the most used (in SE) form of persistence unit.");
		all.add("It is identified by using RESOURCE_LOCAL as the persistence unit type, or by leaving the field completely empty.");
		all.add("The general rule around JPA, is when you have two or more persistence units in an application, or a persistence unit and a message queue, USE JTA");
		all.add("Guiced Persistence allows you to map these by simply including the AbstractDatabaseModule, and can grant you automated transaction handling" +
		        "<a target=\"_blank\" src=\"https://spin.atomicobject.com/2012/08/21/guice-aop-and-jpa/\"> As shown here</a>");
		all.add("These are implemented for your convenience but are very quick to code.");

		addSourceToContainer(JPAModuleScreen.class, "jpa_auto.txt", SourceCodeLanguages.Java, all);
		addSourceToContainer(JPAModuleScreen.class, "jpa_persistence_example.txt", SourceCodeLanguages.XML, all);

		all.add("The connection information is provided the exact same way as for JTA.");
		all.add("It is recommended to use a DataSource pool, such as C3P0, or similar with JPA Connections. These can be easily included by referencing the addon module.");

		addSourceToContainer(JPAModuleScreen.class, "jpaconfigure.txt", SourceCodeLanguages.Java, all);

		add(all);
	}
}
