package com.jwebmp.examples.demos.homepage.display.about.c3p0module;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.H4;
import com.jwebmp.examples.demos.homepage.components.display.DefaultDisplayWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class C3P0Screen
		extends DisplayPart<C3P0Screen>
{
	public C3P0Screen()
	{
		BSCardBody all = new BSCardBody();

		all.addClass("row bg-dark");
		all.addStyle("display:grid;overflow-y:auto;padding:0px;");


		Div about = new Div<>().addClass(Col_12);
		Div clazz = new Div<>().addClass(Col_12);
		Div spi = new Div<>().addClass(Col_12);

		DefaultDisplayWizard wizard = new DefaultDisplayWizard("c3p0wizard");
		wizard.addStep(new SmartWizardStep(about, "About", "The basics for C3P0"));
		wizard.addStep(new SmartWizardStep(clazz, "Database Module", "Configuring the module"));
		wizard.addStep(new SmartWizardStep(clazz, "SPI", "Provide the module"));

		about.add(new H3<>("C3P0 Module"));
		about.add(new H4<>("JPA Data Source Pooling"));

		about.add("Baeldung give a great overview of this library <a target=\"_blank\" href=\"https://www.baeldung.com/hibernate-c3p0\">here</a>," +
		          "Information about C3P0 itself can be found <a target=\"_blank\" href=\"https://www.mchange.com/projects/c3p0/\">here</a>");

		about.add("The C3P0 Module allows you to utilize Hibernates implementation of C3P0 Data Pooling straight out the box .");
		about.add("There isn't any configuration required by default, and all settings can be manipulated using the C3P0ConnectionPropertiesReader," +
		          "<br/>or directly in your <code>Database Module</code>");

		about.add("This module is highly recommended for pure JPA applications");

		addSourceToContainer(C3P0Screen.class, "c3p0config.txt", SourceCodeLanguages.Java, about);

		all.add(wizard);
		add(all);
	}
}
