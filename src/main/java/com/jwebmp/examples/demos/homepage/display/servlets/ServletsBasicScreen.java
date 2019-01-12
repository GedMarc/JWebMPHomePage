package com.jwebmp.examples.demos.homepage.display.servlets;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultTable;
import com.jwebmp.examples.demos.homepage.components.display.DefaultSmartWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.components.display.PluginModulePart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;

import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class ServletsBasicScreen
		extends DisplayPart<ServletsBasicScreen>
{
	public ServletsBasicScreen()
	{
		BSCardBody all = getDefaultBody();

		Div about = buildAbout();
		Div jsf = buildJSF();
		Div clazz = buildTest();
		Div spi = buildSpi();

		DefaultSmartWizard wizard = new DefaultSmartWizard("servletsbasictree");

		wizard.addStep(new SmartWizardStep(about, "About", "Enabling servlets in guice"));
		wizard.addStep(new SmartWizardStep(clazz, "Testing", "Get those tests up and running"));
		wizard.addStep(new SmartWizardStep(spi, "SPI", "Services offered"));

		all.add(new PluginModulePart("Guiced Servlets"));
		all.add(wizard);
		all.add(buildGoToSource(getClass()));

		add(all);
	}

	private Div buildAbout()
	{
		Div all = new Div();
		all.add("This API is separate to JWebMP and is used by it. You can use this API anywhere that guice servlets can run." +
		        "<br/>This API is 100% JPMS and Modular");

		all.add("Guiced Servlets allows for integration into the Servlet API using <code>guice-servlets</code> and the <code>Guiced Injection</code> framework." +
		        "<br/>This framework integrates into any Guice enabled server (that's pretty much everything) and exposes a Global ServletModule SPI to bind with into the global space from external modules.");

		all.add("This framework installs a <code>GuicedFilter</code> to watch all URL's <code>/*</code>. " +
		        "<br/>It is registered through a ServletContextListener and web-fragment.xml for WAR file containers - for easy porting, and ServletExtensions for undertow." +
		        "<br/>All Servlet Mapped Keys are registered in the exposed <code>GuicedServletKeys</code> class and can be overridden for tests using a <code>IGuicePreStartup</code> SPI.");

		all.add("All sessions are locatable through the <code>GuicedServletSessionManager</code>.");

		all.add("The SPI implementation exposed is the <code>IGuiceSiteBinder</code> with the <code>GiteSiteInjectorModule</code> used as a base.");
		return all;
	}

	private Div buildJSF()
	{
		Div all = new Div();

		all.add("The builder class can also be used to dynamic query generation to group commonly used expressions into an easy-to-read format." +
		        "<br/> Queries can be written from the calling class, or shortened inside the builder class itself for easy to read, DRY, code.");
		return all;
	}

	private Div buildTest()
	{
		Div all = new Div();
		all.add("Unit Testing servlets is made extremely simple by being able to change the Key that is accessed in order to get an object," +
		        "<br/> the below example shows how to set test a basic servlet.");
		addSourceToContainer(ServletsBasicScreen.class, "servlet_test_execution.txt", Java, all);

		all.add("You can include the test-jar of this module to get instant access to mocks and fakes, bindings etc, or simply view the test source to see how the tests were set up.");
		return all;
	}

	private Div buildSpi()
	{
		Div all = new Div();
		all.add("Guiced Servlets offers a single SPI, to bind into the global ServletModule from external modules.");

		DefaultTable<?> table = new DefaultTable();
		table.addRow("IGuiceSiteBinder");

		all.add(table);
		all.add("This interface must be linked with a <code>GuiceSiteInjectorModule</code> for full effect. " +
		        "<br/>This is done so you can override any method in the Injector Module and apply any custom logic.");
		addSourceToContainer(ServletsBasicScreen.class, "servlets_binding.txt", Java, all);

		return all;
	}


}
