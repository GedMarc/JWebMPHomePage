package com.jwebmp.examples.demos.homepage.display.about.c3p0module;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.H4;
import com.jwebmp.examples.demos.homepage.components.display.DefaultSmartWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.components.display.MetaInfTree;
import com.jwebmp.examples.demos.homepage.display.about.requestscoped.JPAModuleScreen;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class C3P0Screen
		extends DisplayPart<C3P0Screen>
{
	public C3P0Screen()
	{
		BSCardBody all = getDefaultBody();
		Div about = buildAbout();
		Div clazz = buildDBModule();
		Div spi = buildSpi();

		DefaultSmartWizard wizard = new DefaultSmartWizard("c3p0wizard");
		wizard.addStep(new SmartWizardStep(about, "About", "The basics for C3P0"));
		wizard.addStep(new SmartWizardStep(clazz, "Database Module", "Configuring the module"));
		wizard.addStep(new SmartWizardStep(spi, "SPI", "Provide the module"));

		all.add(wizard);
		all.add(buildGoToSource(getClass()));

		add(all);
	}

	private Div buildAbout()
	{
		Div about = new Div<>().addClass(Col_12);
		about.add(new H3<>("C3P0 Module"));
		about.add(new H4<>("JPA Data Source Pooling"));

		about.add("Baeldung give a great overview of this library <a target=\"_blank\" href=\"https://www.baeldung.com/hibernate-c3p0\">here</a>," +
		          "Information about C3P0 itself can be found <a target=\"_blank\" href=\"https://www.mchange.com/projects/c3p0/\">here</a>");

		about.add("The C3P0 Module allows you to utilize Hibernates implementation of C3P0 Data Pooling straight out the box .");
		about.add("There isn't any configuration required by default, and all settings can be manipulated using the C3P0ConnectionPropertiesReader," +
		          "<br/>or directly in your <code>Database Module</code>");

		about.add("This module is highly recommended for pure JPA applications" +
		          "<br/>Default pool settings (added automatically to persistence units if missing) can be set as below");

		addSourceToContainer(C3P0Screen.class, "c3p0config.txt", SourceCodeLanguages.Java, about);
		return about;
	}

	private Div buildDBModule()
	{
		Div clazz = new Div<>().addClass(Col_12);

		clazz.add("C3P0 connections are configured through the <code><i>C3P0ConnectionBaseInfo</i></code> class, ");

		addSourceToContainer(C3P0Screen.class, "c3p0_configure.txt", SourceCodeLanguages.Java, clazz);

		clazz.add(
				"The C3P0 Pooled DataSource enables injected <code>@Inject @Annotation DataSource </code> JPA connections. " +
				"<br/> You can get the datasource directly through <code>GuiceContext.get(Key.of(DataSource.class,Annotation.class));</code>"
		         );
		return clazz;
	}

	private Div buildSpi()
	{
		Div spi = new Div<>().addClass(Col_12);

		spi.add(new H3("Provide the Service"));
		spi.add("As per usual, provide your module to the injection engine with IGuiceModule." +
		        "<br/> The two mechanisms for running systems are listed below");

		spi.add(new H4<>("JRE 8"));
		spi.add(new MetaInfTree("persistence.xml"));
		spi.add(new H4<>("JPMS"));

		addSourceToContainer(JPAModuleScreen.class, "modules.txt", SourceCodeLanguages.Java, spi);
		return spi;

	}
}
