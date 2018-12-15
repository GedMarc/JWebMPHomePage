package com.jwebmp.examples.demos.homepage.display.about.requestscoped;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.components.display.MetaInfTree;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class JPAModuleScreen
		extends DisplayPart<JPAModuleScreen>
{
	public JPAModuleScreen()
	{
		BSCardBody all = new BSCardBody();

		all.addClass("row bg-dark");
		all.addStyle("display:grid;overflow-y:auto;padding:0px;");

		BSNavTabs tabs = new BSNavTabs();
		tabs.getTabContents()
		    .addClass(W_100)
		    .addStyle("display", "table")
		    .addStyle("padding:0px;");

		Div persistenceTab = new Div<>().addClass(Col_12);
		Div classTab = new Div<>().addClass(Col_12);

		tabs.addTab("Persistence.xml", persistenceTab, true);
		tabs.addTab("DBClass", classTab, false);

		all.add(tabs);

		persistenceTab.add("JPA is the most basic, and probably the most used (in SE) form of persistence unit.");
		persistenceTab.add("It is identified by using RESOURCE_LOCAL as the persistence unit type, or by leaving the field completely empty.");

		persistenceTab.add(
				//new Link<>("https://spin.atomicobject.com/2012/08/21/guice-aop-and-jpa/", "_blank")
				new Link<>("https://www.thoughts-on-java.org/jpa-persistence-xml/", "_blank")
						.setText("You can read more on these here"));

		addSourceToContainer(JPAModuleScreen.class, "jpa_persistence_example.txt", SourceCodeLanguages.XML, persistenceTab);
		persistenceTab.add("Keep in mind, for JPA, when you have two or more persistence units in an application, " +
		                   "<br/>or a persistence unit and a message queue, You should use JTA");

		persistenceTab.add("Guiced Persistence allows you to map these by simply inheriting the <strong><i>DatabaseModule</i></strong>");

		classTab.add("JPA is configured through the <strong><i>JPAConnectionBaseInfo</i></strong> class.");

		addSourceToContainer(JPAModuleScreen.class, "jpaconfigure.txt", SourceCodeLanguages.Java, classTab);

		classTab.add(new H3("Provide the Service"));

		classTab.add("JRE 8");
		classTab.add(new MetaInfTree("com.jwebmp.guicedinjection.interfaces.IGuiceModule"));
		classTab.add("JPMS");
		addSourceToContainer(JPAModuleScreen.class, "modules.txt", SourceCodeLanguages.Java, classTab);

		classTab.add("If you are using JPA, think about using connection pools - such as C3P0. <br/>");

		add(all);
	}
}
