package com.jwebmp.examples.demos.homepage.display.about.persistencehandling;

import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.components.display.MetaInfTree;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

public class SettingUpScreen
		extends DisplayPart<SettingUpScreen>
{
	public SettingUpScreen()
	{
		BSCardBody all = new BSCardBody();
		all.addClass("row bg-dark");
		all.addStyle("display:grid;overflow-y:auto;");

		all.add("First thing you need is an annotation to mark your Entity Manager and related bindings.");

		addSourceToContainer(SettingUpScreen.class, "annotation.txt", SourceCodeLanguages.Java, all);
		all.add("Create your database module. This will directly link, and automatically bind everything you need. " +
		        "<br/>By default the actual binding is a Private Module that is installed into the Abstract Module<br/>");
		addSourceToContainer(SettingUpScreen.class, "abstractmodule.txt", SourceCodeLanguages.Java, all);

		all.add("Finally, &quot;Provide&quot; your module to GuiceContext via the IGuiceModule service.");

		all.add(new MetaInfTree("com.jwebmp.guicedinjection.interfaces.IGuiceModule"));

		addSourceToContainer(SettingUpScreen.class, "providesexample.txt", SourceCodeLanguages.JS, all);

		all.add("<a target=\"_blank\" href=\"https://github.com/bitronix/btm/wiki/JDBC-pools-configuration\">JTA Connection Pool Properties</a>");

		all.add("To utilize encapsulation and lock down database usage to a module (As per EE), Create a Private Module and install the Database Module into it.");

		all.add("You can override the sort order and provide the same value to enable asynchronous loading." +
		        "<br/>Remove the registration for your Database Module from SPI IGuiceModule (Keep IGuicePostStartup)");

		addSourceToContainer(SettingUpScreen.class, "privatemodule.txt", SourceCodeLanguages.Java, all);

		all.add(new MetaInfTree("com.jwebmp.guicedinjection.interfaces.IGuicePostStartup"));

		add(all);
	}
}
