package com.jwebmp.examples.demos.homepage.display.about.persistencehandling;

import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
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
		all.add("First thing you need is an annotation to mark your Entity Manager and related bindings.<br/>" +
		        "This annotation is marked with a @BindingAnnotation.");

		addSourceToContainer(SettingUpScreen.class, "annotation.txt", SourceCodeLanguages.Java, all);
		all.add("Create your database module. This will directly link, and automatically bind everything you need. <br/>By default the actual binding is a Private Module<br/>");
		addSourceToContainer(SettingUpScreen.class, "abstractmodule.txt", SourceCodeLanguages.Java, all);

		all.add("Finally, &quot;Provide&quot; your module to GuiceContext via the IGuiceModule service.");
		all.add(addServiceTree("com.jwebmp.guicedinjection.interfaces.IGuiceModule"));
		addSourceToContainer(SettingUpScreen.class, "providesexample.txt", SourceCodeLanguages.JS, all);
		all.add("<a target=\"_blank\" href=\"https://github.com/bitronix/btm/wiki/JDBC-pools-configuration\">JTA Connection Pool Properties</a>");

		add(all);
	}
}
