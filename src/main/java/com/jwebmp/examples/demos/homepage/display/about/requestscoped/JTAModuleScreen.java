package com.jwebmp.examples.demos.homepage.display.about.requestscoped;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.examples.demos.homepage.components.display.DefaultDisplayWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.components.display.MetaInfServicesTree;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class JTAModuleScreen
		extends DisplayPart<JTAModuleScreen>
{
	public JTAModuleScreen()
	{
		BSCardBody all = new BSCardBody();
		all.addClass("row bg-dark");
		all.addStyle("display:grid;overflow-y:auto;padding:0px;");


		DefaultDisplayWizard wizard = new DefaultDisplayWizard("jtamodulewizard");


		Div persistenceTab = new Div<>().addClass(Col_12);
		Div classTab = new Div<>().addClass(Col_12);
		Div servicesTab = new Div<>().addClass(Col_12);
		Div implementationTab = new Div<>().addClass(Col_12);


		wizard.addStep(new SmartWizardStep(persistenceTab, new SmartWizardStepItem("Persistence", "persistence.xml configuration")));
		wizard.addStep(new SmartWizardStep(classTab, new SmartWizardStepItem("Module Class", "Configuring your unit")));
		wizard.addStep(new SmartWizardStep(servicesTab, new SmartWizardStepItem("Services", "Notify with SPI")));
		wizard.addStep(new SmartWizardStep(implementationTab, new SmartWizardStepItem("Implementation", "How it works")));

		all.add(wizard);

		//addSourceToContainer(JTAModuleScreen.class, "jta_auto.txt", SourceCodeLanguages.Java, all);
		persistenceTab.add(new H3("Persistence Implementation"));
		persistenceTab.add("JTA is picked up either through a resource type of JTA, or by using the jta datasource property.");

		addSourceToContainer(JTAModuleScreen.class, "jta_persistence_example.txt", SourceCodeLanguages.XML, persistenceTab);

		classTab.add(new H3("Class Structure"));

		classTab.add("The class <strong><i>BTMConnectionBaseInfo</i></strong> is used for JTA. " +
		             "<br/>The boolean constructor specifies XA or Non XA.");
		classTab.add("Below you can also see some default optimizations for JDBC 4 drivers.");

		addSourceToContainer(JTAModuleScreen.class, "jtaconfigure.txt", SourceCodeLanguages.Java, classTab);


		servicesTab.add("There is also a <code>setAutoStart(true);</code> method that will auto start the persistence unit.");
		servicesTab.add(new H3("Provide the Service"));
		servicesTab.add("As per usual, provide your module to the injection engine with IGuiceModule." +
		                "<br/> The two mechanisms for running systems are listed below");

		servicesTab.add("JRE 8");
		servicesTab.add(new MetaInfServicesTree("com.jwebmp.guicedinjection.interfaces.IGuiceModule"));
		servicesTab.add("JPMS");
		addSourceToContainer(JPAModuleScreen.class, "modules.txt", SourceCodeLanguages.Java, servicesTab);


		implementationTab.add(new H3("Background Implementation"));

		implementationTab.add("JTA is included with the usage of Bitronix BTM. " +
		                      "<br/>This was chosen for its connection pooling, " +
		                      " <strong><u>identical</u></strong> usage and transaction management " +
		                      "between production and unit tests.");

		implementationTab.add("Transactions are managed through the <code>BTMAutomatedTransactionHandler</code> class");

		implementationTab.add(
				"The Guiced Persistence implementation of BTM allows you to map both XA and Non-XA resources, and will assign the LRC XA Handler for Non-XA persistence units." +
				" This allows you to control and manage UserTransactions, Units of Work, and Transactional Rollbacks accordingly.");

		implementationTab.add("Transactions can use <code>@Transactional</code> annotation for marking and updating. " +
		                      "<br/><code>com.google.inject.persist</code> and <code>javax.transaction</code> @Transactional annotations are supported.");

		implementationTab.add("<br/>To specify a separate marked annotation to use for transactions, other than what was specified in the binding module, " +
		                      "<br/>you can specify <code>@com.jwebmp.guicedpersistence.db.annotations.Transactional</code> to mark as below.");
		addSourceToContainer(JPAModuleScreen.class, "sessionscope.txt", SourceCodeLanguages.Java, implementationTab);
/*
		all.add("Implementing Session-Per-Transaction strategy is enabled by default, to enable lower level per DB call, you can use the automated handler for hassle free development. " +
		        "But don't rely on rolling back as each call is committed after persist/merge/delete.");
*/

		add(all);
	}
}
