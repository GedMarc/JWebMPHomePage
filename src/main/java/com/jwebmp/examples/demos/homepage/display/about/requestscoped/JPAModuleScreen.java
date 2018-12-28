package com.jwebmp.examples.demos.homepage.display.about.requestscoped;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.H4;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.display.DefaultDisplayWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.components.display.MetaInfServicesTree;
import com.jwebmp.examples.demos.homepage.components.display.MetaInfTree;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class JPAModuleScreen
		extends DisplayPart<JPAModuleScreen>
{
	public JPAModuleScreen()
	{
		BSCardBody all = new BSCardBody();

		all.addClass("row bg-dark");
		all.addStyle("display:grid;overflow-y:auto;padding:0px;");


		DefaultDisplayWizard wizard = new DefaultDisplayWizard("jpamodulewizard");

		Div persistenceTab = new Div<>().addClass(Col_12);
		Div classTab = new Div<>().addClass(Col_12);
		Div servicesTab = new Div<>().addClass(Col_12);

		wizard.addStep(new SmartWizardStep(persistenceTab, new SmartWizardStepItem("Persistence.xml", "Getting Started with DBs")));
		wizard.addStep(new SmartWizardStep(classTab, new SmartWizardStepItem("DB Class", "Your mapping class")));
		wizard.addStep(new SmartWizardStep(servicesTab, new SmartWizardStepItem("SPI", "Provide your service")));


		all.add(wizard);

		persistenceTab.add("JPA is the most basic, and probably the most used (in SE) form of persistence unit." +
		                   "<br/> It forms the foundation of generally all database connectivity from Java, and is specified with a file named persistence.xml located in the META-INF folder");

		persistenceTab.add("JPA (as in not JTA) is identified by using RESOURCE_LOCAL as the persistence unit type, or by leaving this field completely empty");

		persistenceTab.add(new MetaInfTree("persistence.xml"));

		persistenceTab.add(
				//new Link<>("https://spin.atomicobject.com/2012/08/21/guice-aop-and-jpa/", "_blank")
				new Link<>("https://www.thoughts-on-java.org/jpa-persistence-xml/", "_blank")
						.setText("You can read more on these here"));

		addSourceToContainer(JPAModuleScreen.class, "jpa_persistence_example.txt", SourceCodeLanguages.XML, persistenceTab);
		persistenceTab.add("Keep in mind, for JPA, when you have two or more persistence units in an application, " +
		                   "<br/>or a persistence unit and a message queue, You should use JTA");


		persistenceTab.add("Guiced Persistence allows you to map these by simply inheriting the <code><i>DatabaseModule</i></code> class.");

		classTab.add("JPA is configured through the <code><i>JPAConnectionBaseInfo</i></code> class.");

		addSourceToContainer(JPAModuleScreen.class, "jpaconfigure.txt", SourceCodeLanguages.Java, classTab);

		classTab.add("The JPA Connection Base Info does not provide a data source, as the connections can come from anywhere, and differently for each provider." +
		             "<br/> Data Connection Pooling is definitely recommended and can be used with <code>C3P0 Data Connection Pooling</code> module add-on");

		servicesTab.add(new H3("Provide the Service"));
		servicesTab.add("As per usual, provide your module to the injection engine with IGuiceModule." +
		                "<br/> The two mechanisms for running systems are listed below");

		servicesTab.add(new H4<>("JRE 8"));
		servicesTab.add(new MetaInfServicesTree("com.jwebmp.guicedinjection.interfaces.IGuiceModule"));
		servicesTab.add(new H4<>("JPMS"));

		addSourceToContainer(JPAModuleScreen.class, "modules.txt", SourceCodeLanguages.Java, servicesTab);

		add(all);
	}
}
