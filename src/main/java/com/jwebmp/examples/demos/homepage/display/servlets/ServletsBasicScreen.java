package com.jwebmp.examples.demos.homepage.display.servlets;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.display.DefaultSmartWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;

public class ServletsBasicScreen
		extends DisplayPart<ServletsBasicScreen>
{
	public ServletsBasicScreen()
	{
		BSCardBody all = getDefaultBody();
		Div about = buildAbout();
		Div builder = buildJSF();
		Div clazz = buildDBModule();

		DefaultSmartWizard wizard = new DefaultSmartWizard("entityassistwizard");

		wizard.addStep(new SmartWizardStep(about, "About", "All your persistence needs"));
		wizard.addStep(new SmartWizardStep(clazz, "JSF Implementation", "Quickly and instantly run JSF through Guiced Injection"));
		wizard.addStep(new SmartWizardStep(builder, "Injections", "List of Available Keys"));

		all.add(wizard);
		all.add(buildGoToSource(getClass()));

		add(all);
	}

	private Div buildAbout()
	{
		Div all = new Div();
		all.add("Guiced Servlets allows for integration into the Servlet API using <code>Guice Servlets</code> and the <code>Guiced Injection</code> framework." +
		        "<br/>This framework integrates into any Guice enabled server (that's pretty much everything) and exposes a Global ServletModule SPI to bind into the global space.");

		all.add("This framework installs a <code>GuicedFilter</code> to watch all URL's <code>/*</code>. It is registered through a ServletContextListener and web-fragment.xml for WAR files." +
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

		//	DefaultSlimScroll scrolly = new DefaultSlimScroll();
		//	all.add(scrolly);
		//	addSourceToContainer(ServletsBasicScreen.class, "entityassist_3.txt", SourceCodeLanguages.Java, scrolly);
		return all;
	}

	private Div buildDBModule()
	{
		Div all = new Div();

		all.add("At its very base, in order to use this library, entities should inherit the <code>BaseEntity</code> class, Through @MappedSuperclass or otherwise.");
		all.add("This class exposes a method <code>builder()</code> in your entity that is the entry point to your ");

		//	DefaultSlimScroll scroll = new DefaultSlimScroll();
		//	addSourceToContainer(ServletsBasicScreen.class, "entityassist_2.txt", SourceCodeLanguages.Java, scroll);

		//	all.add(scroll);

		return all;
	}


}
