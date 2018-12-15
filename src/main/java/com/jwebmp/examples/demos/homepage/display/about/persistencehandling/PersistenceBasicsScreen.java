package com.jwebmp.examples.demos.homepage.display.about.persistencehandling;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.display.MetaInfTree;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.options.BSTableOptions;
import com.jwebmp.plugins.bootstrap4.tables.BSTable;
import com.jwebmp.plugins.bootstrap4.tables.BSTableRow;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

import javax.validation.constraints.NotNull;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.*;

public class PersistenceBasicsScreen
		extends DisplayScreen<PersistenceBasicsScreen>
{
	public PersistenceBasicsScreen()
	{

	}

	private Div buildSmartWizard()
	{



		return new Div();
	}

	@Override
	public @NotNull BSContainer<?> getContentContainer()
	{
		BSContainer container = new BSContainer(Container_Fluid);

		BSCardBody all = new BSCardBody();

		BSNavTabs tabs = new BSNavTabs();

		Div about = new Div();
		Div spi = new Div();
		Div setup = new Div();
		Div more = new Div();

		tabs.addTab("About", about, true);

		tabs.addTab("Setting Up", setup, false);
		tabs.addTab("Encapsulation", more, false);
		tabs.addTab("SPI", spi, false);

		all.add(tabs);

		//all.addClass(Bg_Dark);
		//all.addClass(Border_Secondary);
		all.addStyle("display:grid;overflow-y:auto;padding:0px;");

		about.add(new H3("Persistence Management"));

		about.add("Guiced Persistence allows you to bind multiple persistence units into Guice Modules utilizing Annotations." +
		          "<br/>It is a programmatic approach to database configuration and does not require any external files to setup.");

		about.add("This module reads persistence units, provides base classes and service injection points for the assisting modules.");

		addSourceToContainer(PersistenceBasicsScreen.class, "mavenconfig.txt", SourceCodeLanguages.XML, about);

		about.add("The GuiceConfigurator is configured as below : ");

		addSourceToContainer(PersistenceBasicsScreen.class, "config.txt", SourceCodeLanguages.Java, about);

		about.add("To mark a persistence unit to be excluded from any scans or processing - add the property to persistence.xml ");

		addSourceToContainer(PersistenceBasicsScreen.class, "guicedignore.txt", SourceCodeLanguages.XML, about);

		about.add(
				"If you are in migration phase and are switching from an EE environment to JPMS, " +
				"<br/>you can safely run the two side-by-side, or can switch off EE by adding in the relevant exclusion property." +
				"<br/>e.g. for JBoss or Wildfly " +
				"<br/>");

		addSourceToContainer(PersistenceBasicsScreen.class, "jbossignore.txt", SourceCodeLanguages.XML, about);

		spi.add("Services are located in com.jwebmp.guicedpersistence.services");

		BSTable<?> settingUpTable = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                           .addClass(Table_Hover)
		                                           .fitInContainerBreakWord();
		settingUpTable.setSmall(true);
		settingUpTable.setBordered(true);
		settingUpTable.setStriped(true);

		settingUpTable.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Service Loader"))
		                                                                .add(new TableHeaderCell<>("Purpose"))
		                                               ));
		settingUpTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IPropertiesConnectionInfoReader"))
		                                                .add(new TableCell<>(
				                                                "Populates the ConnectionBaseInfo object with properties from the persistence unit.")));

		settingUpTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IPropertiesEntityManagerReader"))
		                                                .add(new TableCell<>(
				                                                "Utility Service that creates or modifies the properties HashMap for a Persistence Context before being converted to a ConnectionBaseInfo object.")));

		settingUpTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("ITransactionHandler"))
		                                                .add(new TableCell<>("Service that is used to manage transactions, whether for JTA or JPA.")));
		spi.add(settingUpTable);

		spi.add(new MetaInfTree("com.jwebmp.guicedpersistence.services.ITransactionHandler",
		                        "com.jwebmp.guicedpersistence.services.IPropertiesConnectionInfoReader",
		                        "com.jwebmp.guicedpersistence.services.IPropertiesEntityManagerReader"));


		setup.add(new H3("Setting up a connection"));

		setup.add("First thing you need is an annotation to mark your Entity Manager and related bindings.");

/*
		all.add("<br/>This library uses guice-persist as a base, and requires that a Guice Module be registered. The most convenient way is to register a IGuiceModule with a class that extends DatabaseModule." +
		        "<br/>Start your connection for back-ends using either IGuicePostStartup registered to the DatabaseModule or for Web a Request Scoped Filter");
*/

		addSourceToContainer(PersistenceBasicsScreen.class, "annotation.txt", SourceCodeLanguages.Java, setup);
		setup.add("Create your database module. This will directly link, and automatically bind everything you need. " +
		          "<br/>By default the actual binding is a Private Module that is installed into the Abstract Module<br/>");
		setup.add("<a target=\"_blank\" href=\"https://github.com/bitronix/btm/wiki/JDBC-pools-configuration\">JTA Connection Pool Properties</a>");


		addSourceToContainer(PersistenceBasicsScreen.class, "abstractmodule.txt", SourceCodeLanguages.Java, setup);

		setup.add("Finally, &quot;Provide&quot; your module to GuiceContext via the IGuiceModule service." +
		          "<br/> You can also create a service binding from the same module to IGuicePostStartup to instantiate the connection on boot.");

		setup.add("JRE 8");
		setup.add(new MetaInfTree("com.jwebmp.guicedinjection.interfaces.IGuiceModule", "com.jwebmp.guicedinjection.interfaces.IGuicePostStartup"));
		setup.add("JPMS");
		addSourceToContainer(PersistenceBasicsScreen.class, "providesexample.txt", SourceCodeLanguages.JS, setup);

		more.add("To utilize encapsulation and lock down database usage to a module (As per EE), Create a Private Module and install the Database Module into it.");

		more.add("You can override the sort order and provide the same value to enable asynchronous loading." +

		         "<br/>Change the registration for your Database Module to your Private Module, Keep the Abstract Database Module in IGuicePostStartup");

		addSourceToContainer(PersistenceBasicsScreen.class, "privatemodule.txt", SourceCodeLanguages.Java, more);

		more.add(new MetaInfTree("com.jwebmp.guicedinjection.interfaces.IGuiceModule", "com.jwebmp.guicedinjection.interfaces.IGuicePostStartup"));

		container.add(all);

		return container;
	}

	@Override
	public @NotNull BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Persistence"));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Basics"));
		return crumbs;
	}
}
