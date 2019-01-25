package com.jwebmp.examples.demos.homepage.display.injection;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.DefaultTable;
import com.jwebmp.examples.demos.homepage.components.display.DefaultSmartWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.components.display.PluginModulePart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.FontAwesomeList;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

import static com.jwebmp.core.utilities.StaticStrings.*;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons.*;
import static com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class InjectionsBasicsPart
		extends DisplayPart<InjectionsBasicsPart>
{
	public InjectionsBasicsPart()
	{
		BSCardBody body = getDefaultBody();
		body.add(new PluginModulePart("Guiced Injection"));

		DefaultSmartWizard wizard = new DefaultSmartWizard("guicedinjectionwizard");

		Div about = buildAbout();
		Div startup = buildPicture();
		Div spi = buildSpi();

		wizard.addStep(new SmartWizardStep(about, new SmartWizardStepItem("About", "Injection without all the annotations")));
		wizard.addStep(new SmartWizardStep(spi, new SmartWizardStepItem("SPI", "Provide your service")));
		wizard.addStep(new SmartWizardStep(startup, new SmartWizardStepItem("Startup", "The booting sequence")));
		body.add(wizard);
		body.add(buildGoToSource(getClass()));

		add(body);
	}

	private Div buildAbout()
	{
		Div d = new Div();
		d.add(new H3("Injection <small>(with/out all the injection)</small>"));
		d.add("<code>Guiced Injection</code> is an API that provides E.E. like capabilities directly to JPMS." +
		      "<br/>This module assists and builds up a Guice Injector in Google's <code>guice</code> API using Service Providers" +
		      "<br/>It's singular purpose is to order Startup, Loading, and Post Executions. Nothing else." +
		      "<br/>ClassGraph will be splitting out into its own module in time.");

		addSourceToContainer(InjectionsBasicsPart.class, "guice_context_example.txt", Java, d);

		FontAwesomeList list = new FontAwesomeList();
		list.addItem("", FontAwesome.icon(exclamation_triangle, Light));
		list.addItem("", FontAwesome.icon(wind_warning, Light));
		list.addItem("100% JPMS. Yes, you want to do this.", FontAwesome.icon(badge_check, Light));

		d.add(list);
		d.add("<br/>" + HTML_TAB + "All normal injecting is available through standard mechanisms." +
		      "<br/>" + HTML_TAB + new Link<>("https://github.com/google/guice/wiki/Motivation", "_blank").setTiny(true)
		                                                                                                  .setText("View Guice Wiki")
		                                                                                                  .toString(true));

		FontAwesomeList list2 = new FontAwesomeList();
		list2.addItem(
				"Field Injection - Not for JPMS. Illegal Access Reflection. " +
				"<br/>" + HTML_TAB + "Will still work JRE 8, and with some parameters JPMS" +
				"<br/>" + HTML_TAB + "But you should have been moving away from already"
				,
				FontAwesome.icon(exclamation_triangle, Light));
		list2.addItem("Constructor Injection - Go for it, be a little weary. Constructor pollution happens quickly after around 6 months of dev",
		              FontAwesome.icon(wind_warning, Light));
		list2.addItem("Method Interception - Yep. bindInterceptor() in any IGuiceModule", FontAwesome.icon(badge_check, Light));
		list2.addItem("Method Parameters - Any guice constructed class", FontAwesome.icon(badge_check, Light));
		list2.addItem("In-Method Fetch - You betcha, Use the example above. This is what you want to do.", FontAwesome.icon(badge_check, Light));
		list2.addItem("Multi-Module Binding - Hells yes", FontAwesome.icon(badge_check, Light));
		list2.addItem("Strictly Named JPMS - Would you expect anything else?", FontAwesome.icon(badge_check, Light));
		list2.addItem("Sorted and Named Startup - PreStartup, Module Ordering , and Post Startup", FontAwesome.icon(badge_check, Light));
		list2.addItem("Addons for Persistence, Servlets, Spring, Dagger, JMX,JNDI, Rest, Swagger,Web Sockets etc", FontAwesome.icon(badge_check, Light));

		d.add(list2);

		return d;
	}

	private Div buildPicture()
	{
		Div d = new Div();
		d.add(new H3<>("The Startup Sequence"));

		d.add("The startup sequence happens on the first call to <code>GuiceContext.inject()</code>. This triggers off the below sequence of events." +
		      "<br/>This can be fired off at any time in unit test, integrations, and runtime," +
		      "</br>To restart the entire system simply call <code>GuiceContext.destroy()</code> and <code>GuiceContext.inject()</code>");
		d.add("Configures with <code>IGuiceConfigurators</code>. " +
		      "<br/>" + HTML_TAB + "Determines if a Class Path scan is necessary" +
		      "<br/>Load all <code>IGuicePreStartup</code> in order" +
		      "<br/>Installs all <code>IGuiceModule</code> into the base module in order. These are any Guice Module." +
		      "<br/>Runs all <code>IGuiceDefaultBinders</code> to the base injector - in order" +
		      "<br/>Starts all <code>IGuicePostStartup</code>. Any startup's with the same sort order are loaded asynchronously");

		d.add(new Image<>("images/guiceinjection/StartupOrder.png").addClass("d-block img-fluid"));

		return d;
	}

	private Div buildSpi()
	{
		Div d = new Div();

		d.add(new H3<>("SPI's Available"));

		d.add(new H4("com.jwebmp.guicedinjection.interfaces"));

		d.add("These services allow you to control the boot and injection configuration sequence in its entirety." +
		      "<br/>All Services (extending IDefaultService) are named and sorted accordingly to <code>sortOrder()</code>");

		d.add("The JWebMP Guice Injection Assistant (<a target=\"_blank\" href=\"https://github.com/GedMarc/GuiceInjection\">Guiced Injection<a/>) " +
		      " can be run standalone without JWebMP in any JRE8 or higher environment. " +
		      "<br/>As a completely portable library you can configure your bindings with your current application" +
		      " long before actually porting a site to JWebMP.");

		DefaultTable<?> table = new DefaultTable<>();
		table.addHeader("Service Loader", "Purpose");

		table.addRow("IGuiceConfigurator",
		             "Configures the class path scanner during the booting sequence. This allows full control over what is scanned and not scanned during boot.\" +\n" +
		             "<br/>By default the Class Path Scanner is disabled.");
		table.addRow("IGuicePreStartup",
		             "Executes any required code blocks before the Injector is built. Great for configuration. Do not try to inject during this phase.");
		table.addRow("IGuiceModule",
		             "Service Loader to port your existing Guice Modules into the Global Injection Context");
		table.addRow("IGuiceDefaultBinder",
		             "Sometimes you want to register and bind inside of the parent module. This binder exposes the parent module for custom bindings at the top level.");
		table.addRow("IGuicePostStartup",
		             "Executes the given operations immediately after the injection cycle has completed. " +
		             "<br/>Used mostly to start database connections,configure JCache, etc, before making the site available" +
		             " <br/>Post startup items with the same sort order will be executed asynchronously");
		d.add(table);

		return d;
	}
}
