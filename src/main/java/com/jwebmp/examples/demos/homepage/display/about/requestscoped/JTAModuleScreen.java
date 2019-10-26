package com.jwebmp.examples.demos.homepage.display.about.requestscoped;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.examples.demos.homepage.components.display.*;
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
		BSCardBody all = getDefaultBody();

		all.add(new PluginModulePart("Guiced Persistence BTM/JTA"));

		DefaultSmartWizard wizard = new DefaultSmartWizard("jtamodulewizard");

		Div about = buildAbout();
		Div clazz = buildDBModule();
		Div spi = buildSpi();
		Div impl = buildImpl();

		wizard.addStep(new SmartWizardStep(about, new SmartWizardStepItem("Persistence", "persistence.xml configuration")));
		wizard.addStep(new SmartWizardStep(clazz, new SmartWizardStepItem("Module Class", "Configuring your unit")));
		wizard.addStep(new SmartWizardStep(spi, new SmartWizardStepItem("Services", "Notify with SPI")));
		wizard.addStep(new SmartWizardStep(impl, new SmartWizardStepItem("Implementation", "How it works")));

		all.add(wizard);

		all.add(buildGoToSource(getClass()));

		add(all);
	}


	private Div buildAbout()
	{
		Div about = new Div<>().addClass(Col_12);

		about.add(new H3("Persistence Implementation"));
		about.add("JTA is picked up either through a resource type of JTA, or by using the jta datasource property.");

		addSourceToContainer(JTAModuleScreen.class, "jta_persistence_example.txt", SourceCodeLanguages.XML, about);

		about.add("<br/>This file is placed into your sources directory...");
		about.add(new MetaInfTree("persistence.xml"));

		return about;
	}

	private Div buildDBModule()
	{
		Div clazz = new Div<>().addClass(Col_12);


		clazz.add(new H3("Class Structure"));

		clazz.add("The class <code>BTMConnectionBaseInfo</code> is used for JTA. " +
		          "<br/>The boolean constructor specifies XA or Non XA.");
		clazz.add("Below you can also see some default optimizations for JDBC 4 drivers.");

		addSourceToContainer(JTAModuleScreen.class, "jtaconfigure.txt", SourceCodeLanguages.Java, clazz);

		return clazz;
	}

	private Div buildSpi()
	{
		Div spi = new Div<>().addClass(Col_12);

		spi.add(new H3("Provide the Service"));
		spi.add("As per usual, provide your module to the injection engine with IGuiceModule." +
		        "<br/> The two mechanisms for running systems are listed below");

		spi.add("JRE 8");
		spi.add(new MetaInfServicesTree("com.guicedee.guicedinjection.interfaces.IGuiceModule"));
		spi.add("JPMS");
		spi.add("In your <code>module-info.java</code> file, add in your provisions clause");

		addSourceToContainer(JPAModuleScreen.class, "modules.txt", SourceCodeLanguages.Java, spi);

		spi.add("There is also a <code>setAutoStart(true);</code> method that will auto start the persistence unit.");

		return spi;

	}

	private Div buildImpl()
	{
		Div impl = new Div<>().addClass(Col_12);

		impl.add(new H3("Background Implementation"));

		impl.add("JTA is included with the usage of Bitronix BTM. " +
		         "<br/>This was chosen for its connection pooling, " +
		         " <strong><u>identical</u></strong> usage and transaction management " +
		         "between production and unit tests.");

		impl.add("Transactions are managed through the <code>BTMAutomatedTransactionHandler</code> class");

		impl.add(
				"The Guiced Persistence implementation of BTM allows you to map both XA and Non-XA resources, " +
				"<br/>and will assign the LRC XA Handler for Non-XA persistence units." +
				"<br/>This allows you to control and manage UserTransactions, Units of Work, and Transactional Rollbacks accordingly.");

		impl.add("Transactions can use <code>@Transactional</code> annotation for marking and updating. " +
		         "<br/><code>com.google.inject.persist</code> and <code>javax.transaction</code> @Transactional annotations are supported.");

		impl.add("To specify a separate marked annotation to use for transactions, other than what was specified in the binding module, " +
		         "<br/>you can specify <code>@com.guicedee.guicedpersistence.db.annotations.Transactional</code> to mark as below.");
		addSourceToContainer(JPAModuleScreen.class, "sessionscope.txt", SourceCodeLanguages.Java, impl);
		return impl;

	}
}
