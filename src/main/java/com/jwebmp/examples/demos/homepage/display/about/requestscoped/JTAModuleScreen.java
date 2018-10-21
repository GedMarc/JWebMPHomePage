package com.jwebmp.examples.demos.homepage.display.about.requestscoped;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class JTAModuleScreen
		extends DisplayPart<JTAModuleScreen>
{
	public JTAModuleScreen()
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
		Div implementationTab = new Div<>().addClass(Col_12);

		tabs.addTab("Persistence.xml", persistenceTab, true);
		tabs.addTab("DBClass", classTab, false);
		tabs.addTab("Implementation", implementationTab, false);

		all.add(tabs);

		//addSourceToContainer(JTAModuleScreen.class, "jta_auto.txt", SourceCodeLanguages.Java, all);
		persistenceTab.add(new H3("Persistence Implementation"));
		persistenceTab.add("JTA is picked up either through a resource type of JTA, or by using the jta datasource property.");

		addSourceToContainer(JTAModuleScreen.class, "jta_persistence_example.txt", SourceCodeLanguages.XML, persistenceTab);

		classTab.add(new H3("Class Structure"));

		classTab.add("The class <strong><i>BTMConnectionBaseInfo</i></strong> is used for JTA. " +
		             "<br/>The boolean constructor specifies XA or Non XA.");
		classTab.add("Below you can also see some default optimizations for JDBC 4 drivers.");

		addSourceToContainer(JTAModuleScreen.class, "jtaconfigure.txt", SourceCodeLanguages.Java, classTab);

		implementationTab.add(new H3("Background Implementation"));

		implementationTab.add("JTA is included with the usage of Bitronix BTM. " +
		                      "<br/>This was chosen for its connection pooling, " +
		                      " <strong><u>identical</u></strong> usage and transaction management " +
		                      "between production and unit tests.");

		implementationTab.add("Transactions are managed through the <strong><i>BTMAutomatedTransactionHandler</i></strong> class");

		implementationTab.add(
				"The Guiced Persistence implementation of BTM allows you to map both XA and Non-XA resources, and will assign the LRC XA Handler for Non-XA persistence units." +
				" This allows you to control and manage UserTransactions, Units of Work, and Transactional Rollbacks accordingly.");

/*
		all.add("Implementing Session-Per-Transaction strategy is enabled by default, to enable lower level per DB call, you can use the automated handler for hassle free development. " +
		        "But don't rely on rolling back as each call is committed after persist/merge/delete.");
*/

		add(all);
	}
}
