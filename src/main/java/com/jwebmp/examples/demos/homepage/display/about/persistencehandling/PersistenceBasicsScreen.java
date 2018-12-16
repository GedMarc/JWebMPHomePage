package com.jwebmp.examples.demos.homepage.display.about.persistencehandling;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.DefaultTable;
import com.jwebmp.examples.demos.homepage.components.display.DefaultDisplayWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.display.MetaInfTree;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSTableOptions;
import com.jwebmp.plugins.bootstrap4.tables.BSTable;
import com.jwebmp.plugins.bootstrap4.tables.BSTableRow;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.FontAwesomeList;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

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
		Div aboutContent = buildAbout();
		Div annotateContainer = buildAnnotate();
		Div databaseMOdule = buildDatabaseModule();
		Div providesModule = buildProvides();
		Div encapsulateContent = buildEncapsulation();
		Div servicesContent = buildServices();
		Div enterpriseContent = buildEnterprise();
		Div addonsContent = buildAddons();


		Div pageContentRow = new BSRow();


		DefaultSlimScroll scroll = new DefaultSlimScroll(pageContentRow);
		scroll.getOptions()
		      .setHeight("500px");

		DefaultDisplayWizard wizard = new DefaultDisplayWizard("eventWizard");

		wizard.addStep(new SmartWizardStep(aboutContent, new SmartWizardStepItem("About", new SmallText("Persistence Basics"))));
		wizard.addStep(new SmartWizardStep(annotateContainer, new SmartWizardStepItem("Annotate", new SmallText("Create your annotation"))));
		wizard.addStep(new SmartWizardStep(databaseMOdule, new SmartWizardStepItem("Database Module", new SmallText("Setup the Module"))));
		wizard.addStep(new SmartWizardStep(providesModule, new SmartWizardStepItem("Provide", new SmallText("Supply Module with SPI"))));
		wizard.addStep(new SmartWizardStep(encapsulateContent, new SmartWizardStepItem("Encapsulate", new SmallText("Encapsulate writes accordingly"))));
		wizard.addStep(new SmartWizardStep(servicesContent, new SmartWizardStepItem("Services", new SmallText("Provided services"))));
		wizard.addStep(new SmartWizardStep(enterpriseContent, new SmartWizardStepItem("Enterprise", new SmallText("Instantly Dual-Run"))));
		//wizard.addStep(new SmartWizardStep(addonsContent, new SmartWizardStepItem("Addons Content", new SmallText("Utility Modules"))));

		return wizard;
	}

	private Div buildAbout()
	{
		Div about = new Div();
		about.add(new H3("Persistence Management"));

		about.add("Persistence can be provided either through <a target=\"_blank\" href=\"https://github.com/google/guice/wiki/GuicePersist\">Guice Persist</a> in the default manner, Registering the IGuiceModule," +
		          "<br/>or this library, <a target=\"_blank\" href=\"https://github.com/GedMarc/GuicedPersistence\">Guiced Persistence library<a/> which allows automatic testing and complete support." +
		          "<br/>As an entirely separate module, it can run separately in any environment, and allows quick porting database management tasks across.");

		about.add("This module is independent of JWebMP and can run on its own in any application." +
		          "<br/> Add-on Modules are used to read persistence files and configure connections" +
		          "<br/>At least one addon module is required.");

		about.add(new DefaultTable<>().addHeader("Add-on", "Description", "Artifact ID")
		                              .addRow("<a target=\"_blank\" href=\"https://github.com/GedMarc/GuicedPersistence-HibernatePropertiesReader\">" +
		                                      "Hibernate Properties Reader</a>"
				                              , "Configures the data source from hibernate properties", "guiced-persistence-hibernateproperties-reader")
/*

		                              .addRow("<a target=\"_blank\" href=\"https://github.com/GedMarc/GuicedPersistence-EclipseLinkProperties\">EclipseLink Properties Reader</a>",
		                                      "Configures the data source from eclipse link properties", "guiced-persistence-eclipselink-reader")
*/
		                              .addRow("<a target=\"_blank\" href=\"https://github.com/GedMarc/GuicedPersistence-SystemPropertiesReader\">" +
		                                      "System Properties Reader</a>",
		                                      "Overwrites the persistence properties with the given system environment value <code>${system.property}</code>", "guiced-persistence-systemproperties-reader")

		                              .addRow("<a target=\"_blank\" href=\"https://github.com/GedMarc/GuicedPersistence-JPA\">" +
		                                      "JPA Properties Reader</a>",
		                                      "Reads properties specific to JPA, and configures a JPA Transaction Handler for all JPA Data Sources", "guiced-persistence-jpa")

		                              .addRow("<a target=\"_blank\" href=\"https://github.com/GedMarc/GuicedPersistence-EhCache\">" +
		                                      "EH Cache Properties Reader</a>",
		                                      "Configures Eh Cache for the persistence units, and includes all necessary persistence unit updates", "guiced-persistence-ehcache")

		                              .addRow("<a target=\"_blank\" href=\"https://github.com/GedMarc/GuicedPersistence-C3P0\">" +
		                                      "C3P0 Properties Reader</a>",
		                                      "Configures C3P0 Data Source Pooling for JPA Modules", "guiced-persistence-c3p0")

		                              .addRow("<a target=\"_blank\" href=\"https://github.com/GedMarc/GuicedPersistence-BTM\">" +
		                                      "BTM Properties Reader</a>",
		                                      "Bitronix BTM Handler for XA and Non-XA Connections and Pooling. Using Strictly Named Module", "guiced-persistence-btm")

		                              .addRow("<a target=\"_blank\" href=\"https://github.com/GedMarc/GuicedPersistence-Wildfly\">" +
		                                      "Wildfly Properties Reader</a>",
		                                      "Reads the <code>getStandaloneName()</code> Standalone Filename and configures the data sources accordingly", "guiced-persistence-wildfly")
		         );

		about.add("Guiced Persistence allows you to bind multiple persistence units into Guice Modules utilizing Annotations." +
		          "<br/>It is a programmatic approach to database configuration and does not require any external files to setup.");

		about.add("This module reads persistence units, provides base classes and service injection points for the associated modules." +
		          "<br/> The module should be automatically imported, but if not - ");

		addSourceToContainer(PersistenceBasicsScreen.class, "mavenconfig.txt", SourceCodeLanguages.XML, about);

		return about;
	}

	private Div buildAddons()
	{
		Div addons = new Div();

		FontAwesomeList list = new FontAwesomeList(true);

		list.addItem("<a target=\"_blank\" href=\"https://github.com/GedMarc/GuicedPersistence-Wildfly\">" +
		             "Wildfly Properties Reader</a>&nbsp; <small><i></i></small>",
		             FontAwesome.icon(FontAwesomeIcons.check));

		addons.add(list);
		return addons;
	}

	private Div buildProvides()
	{
		Div provides = new Div();

		provides.add(new H3<>("Provide the Database Module"));
		provides.add(new H4("JPMS"));
		provides.add("Simply provide the module to the injection platform");
		addSourceToContainer(PersistenceBasicsScreen.class, "providesexample.txt", SourceCodeLanguages.JS, provides);

		provides.add(new H3<>("JRE 8"));
		provides.add("In JRE 8, you need to create the services file in your META-INF/services folder. " +
		             "These files contain a reference to the services.");

		provides.add(new MetaInfTree("com.jwebmp.guicedinjection.interfaces.IGuiceModule"));

		provides.add(new H3<>("Auto Start Data Source"));
		provides.add(
				"<br/>You can also create a service binding from the same module to IGuicePostStartup to instantiate the connection on boot instead of first use (Injecting DataSource)" +
				"<br/>Or simply set <code>setAutoStart(true);</code> to create an asynchronous post startup thread without needing a module specification");

		//provides.add(new MetaInfTree("com.jwebmp.guicedinjection.interfaces.IGuicePostStartup"));

		return provides;
	}

	private Div buildAnnotate()
	{
		Div annotate = new Div();

		annotate.add(new H3("Marking your connection"));
		annotate.add("First thing you need is an annotation to mark your Entity Manager and related bindings." +
		             "<br/>This annotation will be used in a lot of places identifying the correct connections to use.");
		addSourceToContainer(PersistenceBasicsScreen.class, "annotation.txt", SourceCodeLanguages.Java, annotate);

		annotate.add("This class is required for Guiced Persistence.");

		return annotate;
	}

	private Div buildDatabaseModule()
	{
		Div dbModule = new Div();

		dbModule.add("Create your database module. This will directly link, and automatically bind everything you need. " +
		             "<br/>By default the actual binding is a Private Module that is installed into the Abstract Module<br/>");
		dbModule.add("<a target=\"_blank\" href=\"https://github.com/bitronix/btm/wiki/JDBC-pools-configuration\">JTA Connection Pool Properties</a>");


		addSourceToContainer(PersistenceBasicsScreen.class, "abstractmodule.txt", SourceCodeLanguages.Java, dbModule);


		return dbModule;
	}

	private Div buildServices()
	{
		Div services = new Div();

		services.add("Services are located in com.jwebmp.guicedpersistence.services");

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
		services.add(settingUpTable);

		services.add(new MetaInfTree("com.jwebmp.guicedpersistence.services.ITransactionHandler",
		                             "com.jwebmp.guicedpersistence.services.IPropertiesConnectionInfoReader",
		                             "com.jwebmp.guicedpersistence.services.IPropertiesEntityManagerReader"));


/*
		services.add("The GuiceConfigurator is configured as below : ");

		addSourceToContainer(PersistenceBasicsScreen.class, "config.txt", SourceCodeLanguages.Java, services);
*/

		return services;
	}

	private Div buildEncapsulation()
	{
		Div encapsulation = new Div();
		encapsulation.add("To utilize encapsulation and lock down database usage to a module (As per EE), Create a Private Module and install the Database Module into it.");
		encapsulation.add("You can override the sort order and provide the same value to enable asynchronous loading." +
		                  "<br/>Register your Private Module through <code>provides</code> or JRE 8 SPI service file instead of your module");
		addSourceToContainer(PersistenceBasicsScreen.class, "privatemodule.txt", SourceCodeLanguages.Java, encapsulation);

		encapsulation.add("You can use your Private Module to define your interface bindings, use <code>expose(Clazz.class)</code> to expose bindings internal to your module");

		return encapsulation;
	}

	private Div buildEnterprise()
	{
		Div enterprise = new Div();
		enterprise.add("To mark a persistence unit to be excluded from any scans or processing - add the property to persistence.xml ");

		addSourceToContainer(PersistenceBasicsScreen.class, "guicedignore.txt", SourceCodeLanguages.XML, enterprise);

		enterprise.add(
				"If you are in migration phase and are switching from an EE environment to JPMS, " +
				"<br/>you can safely run the two side-by-side, or can switch off EE by adding in the relevant exclusion property." +
				"<br/>e.g. for JBoss or Wildfly " +
				"<br/>");

		addSourceToContainer(PersistenceBasicsScreen.class, "jbossignore.txt", SourceCodeLanguages.XML, enterprise);

		return enterprise;
	}


	@Override
	public @NotNull BSContainer<?> getContentContainer()
	{
		BSContainer container = new BSContainer(Container_Fluid);

		container.add(buildSmartWizard());

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
