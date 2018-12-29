package com.jwebmp.examples.demos.homepage.display.about.requestscoped;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.H4;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.display.DefaultSmartWizard;
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
		BSCardBody all = getDefaultBody();

		DefaultSmartWizard wizard = new DefaultSmartWizard("jpamodulewizard");

		Div about = buildAbout();
		Div clazz = buildDBModule();
		Div spi = buildSpi();


		wizard.addStep(new SmartWizardStep(about, new SmartWizardStepItem("Persistence.xml", "Getting Started with DBs")));
		wizard.addStep(new SmartWizardStep(clazz, new SmartWizardStepItem("DB Class", "Your mapping class")));
		wizard.addStep(new SmartWizardStep(spi, new SmartWizardStepItem("SPI", "Provide your service")));

		all.add(wizard);
		all.add(buildGoToSource(getClass()));
		add(all);
	}

	private Div buildAbout()
	{
		Div about = new Div<>().addClass(Col_12);

		about.add("JPA is the most basic, and probably the most used (in SE) form of persistence unit." +
		          "<br/> It forms the foundation of generally all database connectivity from Java, and is specified with a file named persistence.xml located in the META-INF folder");

		about.add("JPA (as in not JTA) is identified by using RESOURCE_LOCAL as the persistence unit type, or by leaving this field completely empty");

		about.add(new MetaInfTree("persistence.xml"));

		about.add(new Link<>("https://www.thoughts-on-java.org/jpa-persistence-xml/", "_blank")
				          .setText("You can read more on these here"));

		addSourceToContainer(JPAModuleScreen.class, "jpa_persistence_example.txt", SourceCodeLanguages.XML, about);
		about.add("Keep in mind, for JPA, when you have two or more persistence units in an application, " +
		          "<br/>or a persistence unit and a message queue, You should use JTA");


		about.add("Guiced Persistence allows you to map these by simply inheriting the <code><i>DatabaseModule</i></code> class.");

		return about;
	}

	private Div buildDBModule()
	{
		Div clazz = new Div<>().addClass(Col_12);
		clazz.add("JPA is configured through the <code><i>JPAConnectionBaseInfo</i></code> class.");

		addSourceToContainer(JPAModuleScreen.class, "jpaconfigure.txt", SourceCodeLanguages.Java, clazz);

		clazz.add("The JPA Connection Base Info does not provide a data source, as the connections can come from anywhere, and differently for each provider." +
		          "<br/> Data Connection Pooling is definitely recommended and can be used with <code>C3P0 Data Connection Pooling</code> module add-on");

		return clazz;
	}

	private Div buildSpi()
	{
		Div spi = new Div<>().addClass(Col_12);


		spi.add(new H3("Provide the Service"));
		spi.add("As per usual, provide your module to the injection engine with IGuiceModule." +
		        "<br/> The two mechanisms for running systems are listed below");

		spi.add(new H4<>("JRE 8"));
		spi.add(new MetaInfServicesTree("com.jwebmp.guicedinjection.interfaces.IGuiceModule"));
		spi.add(new H4<>("JPMS"));

		addSourceToContainer(JPAModuleScreen.class, "modules.txt", SourceCodeLanguages.Java, spi);

		return spi;

	}

}
