package com.jwebmp.examples.demos.homepage.display.about;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.display.DisplayCard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.db.HomePageDBStartup;
import com.jwebmp.examples.demos.homepage.display.about.persistencehandling.SettingUpScreen;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.cards.BSCard;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.bootstrap4.options.BSTableOptions;
import com.jwebmp.plugins.bootstrap4.tables.BSTable;
import com.jwebmp.plugins.bootstrap4.tables.BSTableRow;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSMarginOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class UnderTheHoodScreen
		extends DisplayScreen<UnderTheHoodScreen>
{
	public UnderTheHoodScreen()
	{
		super("JWebMP Servicing");
	}

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer container = new BSContainer(BSContainerOptions.Container_Fluid);

		BSCard<?> card = new BSCard();
		card.addStyle("background-color", "#333");

		BSNavTabs<?> tabs = new BSNavTabs<>().setBordered(true)
		                                     .setJustified(true)
		                                     .removeSpacingTop()
		                                     .addClass(MarginRight_1);
		tabs.addTab("About", buildDefaultScreen(), true);
		tabs.addTab("JWebMP", buildJWebMP(), false);
		tabs.addTab("Injection", buildInjection(), false);
		tabs.addTab("Persistence", buildPersistence(), false);
		card.addCardBody()
		    .removePadding()
		    .add(tabs);


	/*	String[] tabHeaders = new String[]{"About","Injection","JWebMP","JPA JTA","JCache"};
		List<BSCardBody> tabContents = new ArrayList<>();

		tabContents.add(buildDefaultScreen());
		tabContents.add(buildInjection());
		tabContents.add(buildPersistence());
		tabContents.add(buildJCache());
*/

		//	card.addTabs(tabHeaders, tabContents);

		container.add(card);
		return container;
	}

	private BSCardBody buildDefaultScreen()
	{
		BSCardBody body = new BSCardBody().removePadding();
		BSRow row = new BSRow();

		BSColumn column1 = new BSColumn(BSColumnOptions.Col_Md_8);
		BSColumn column2 = new BSColumn(BSColumnOptions.Col_Md_4);

		column1.add(buildUnderTheHood());
		//	column1.add(buildPowerfulMinimalist());

		//	column1.add(buildComponentRender());

		//column2.add(buildSiteControl());
		column2.add(buildPluginsPanel());
		column2.add(buildServicingPanel());

		row.add(column1);
		row.add(column2);

		body.add(row);
		return body;
	}

	private Div buildJWebMP()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3("JWebMP Service Loaders"));
		div.add(new H4("com.jwebmp.core.services"));
		BSTable<?> table = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                  .addClass(Table_Hover);
		table.setSmall(true);
		table.setBordered(true);
		table.setStriped(true);

		table.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Service Loader"))
		                                                       .add(new TableHeaderCell<>("Purpose"))
		                                      ));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IPage"))
		                                       .add(new TableCell<>(
				                                       "Designates a page that must be rendered. The class must extend Page.<br/> Annotate with @PageConfiguration to configure URL's")));
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IErrorPage"))
		                                       .add(new TableCell<>("Allows to set a custom page to render errors on.")));
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IPageConfigurator"))
		                                       .add(new TableCell<>("Configures and modifies the page before rendering as required by the given module")));
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IRegularExpressions"))
		                                       .add(new TableCell<>("Supplies a list of default regular expressions to the client browser on connect for quick static access")));

		div.add(table);

		div.add(new H3("Angular Configuration Loaders"));
		div.add(new H4("com.jwebmp.core.base.angular.services"));
		BSTable<?> tableAngular = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                         .addClass(Table_Hover);
		tableAngular.setSmall(true);
		tableAngular.setBordered(true);
		tableAngular.setStriped(true);

		tableAngular.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Service Loader"))
		                                                              .add(new TableHeaderCell<>("Purpose"))
		                                             ));

		tableAngular.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IAngularDirective"))
		                                              .add(new TableCell<>("Adds the given Angular Directive to the page rendered to the client")));
		tableAngular.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IAngularControllerScopeStatement"))
		                                              .add(new TableCell<>("Adds script text inside the root angular controller with direct access to the scope")));
		tableAngular.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IAngularModule"))
		                                              .add(new TableCell<>("Adds the given angular module to the initialization of angular")));
		tableAngular.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IAngularConfigurationScopeStatement"))
		                                              .add(new TableCell<>("Adds the given scoped statement to the angular configuration script")));
		tableAngular.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IAngularConfiguration"))
		                                              .add(new TableCell<>("Registers a new angular configuration object to be rendered.")));
		tableAngular.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IAngularController"))
		                                              .add(new TableCell<>("Registers the new controller to the Angular script")));
		tableAngular.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IAngularFactory"))
		                                              .add(new TableCell<>("Adds the given factory to the script")));

		div.add(tableAngular);
		return card;
	}

	private Div buildInjection()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3("Injection Configurations"));
		div.add(new H4("com.jwebmp.guicedinjection.interfaces"));
		div.add("These services allow you to configure the entire configuration and boot process.");

		BSTable<?> table = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                  .addClass(Table_Hover);
		table.setSmall(true);
		table.setBordered(true);
		table.setStriped(true);

		table.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Service Loader"))
		                                                       .add(new TableHeaderCell<>("Purpose"))
		                                      ));
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IFileContentsScanner"))
		                                       .add(new TableCell<>(
				                                       "Registers a filename to be collected, such as persistence.xml or hazelcast-client.xml. Ensure that the path is located in a PathContentsScanner")));
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuiceConfigurator"))
		                                       .add(new TableCell<>(
				                                       "Configures the scanner for boot operations. The scanner is optional and doesn't execute by default. The classpathScan property can be set to enable this functionality.")));
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuiceDefaultBinder"))
		                                       .add(new TableCell<>(
				                                       "Registers a Guice Injection module with the default binders. This is for backend binding with no requirements on servlets, and provides nearly the same encapsulation via JPMS as an EJB would to an WAR")));
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuiceModule"))
		                                       .add(new TableCell<>("Service Loader to port your existing Guice Modules into the Global Injection Context")));
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuicePostStartup"))
		                                       .add(new TableCell<>(
				                                       "Executes the given operations immediately after the injection cycle has completed. Used mostly to start database connections before making the site available")));
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuicePreStartup"))
		                                       .add(new TableCell<>(
				                                       "Executes any required code blocks before the Injector is built. Great for configuration. Do not try to call the GuiceContext in these classes")));
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IPackageContentsScanner"))
		                                       .add(new TableCell<>(
				                                       "Registers the given package to be included during the optional classpath scan. Only these packages will be included in retrieved results")));
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IPathContentsScanner"))
		                                       .add(new TableCell<>("Registers the given path (No Class Files) to search for IFileContentsScanners.<br/> Usually META-INF")));
		div.add(table);

		return card;
	}

	private Div buildPersistence()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3("Persistence Handling"));
		div.add("These modules are completely optional, and provide a JPMS/JDK10 implementation for using JPA/JTA, as well as a 100% programmatic approach to connection and database management." +
		        "<br/>This library uses guice-persist as a base, and requires a registration to IGuiceModule  with a class that extends AbstractDatabaseProviderModule. " +
		        "<br/>These are backwards compatible with JDK 8");

		div.add("Add-Ons provide additional configurations for enables modules, while modules provide core functionality for their usage");

		BSNavTabs tabs = new BSNavTabs().setBordered(true)
		                                .setJustified(true);
		if (!getPage().isMobileOrSmartTablet())
		{
			tabs.setVerticalLeftTabs(true);
		}
		tabs.getTabContents()
		    .addClass(W_100);

		tabs.addTab("About", buildPersistenceAboutScreen(), true);

		tabs.addTab("Setting Up", buildPersistenceSettingUpScreen(), false);
		tabs.addTab("Request Scoped Transactions", new DivSimple<>().add("1"), false);

		tabs.addTab("JPA Module", new DivSimple<>().add("2"), false);
		tabs.addTab("BTM Module", new DivSimple<>().add("2"), false);
		tabs.addTab("Entity Assist Module", new DivSimple<>().add("2"), false);
		tabs.addTab("C3P0 Addon", new DivSimple<>().add("2"), false);
		tabs.addTab("EhCache Addon", new DivSimple<>().add("2"), false);
		tabs.addTab("Wildfly Addon", new DivSimple<>().add("2"), false);
		tabs.addTab("Glassfish Addon", new DivSimple<>().add("2"), false);
		tabs.addTab("HazelCast Addon", new DivSimple<>().add("2"), false);

		div.add(tabs);

		BSTable<?> table = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                  .addClass(Table_Hover);
		table.setSmall(true);
		table.setBordered(true);
		table.setStriped(true);

		table.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Service Loader"))
		                                                       .add(new TableHeaderCell<>("Purpose"))
		                                      ));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("com.jwebmp.core.services.IPage"))
		                                       .add(new TableCell<>(
				                                       "Designates a page that must be rendered. The class must extend Page.<br/> Annotate with @PageConfiguration to configure URL's")));

		return card;
	}

	private Div buildUnderTheHood()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3("Core Libraries"));
		BSTable<?> table = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                  .addClass(Table_Hover);
		table.setSmall(true);
		table.setBordered(true);
		table.setStriped(true);

		table.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Name"))
		                                                       .add(new TableHeaderCell<>("Version"))
		                                                       .add(new TableHeaderCell<>("Info"))
		                                                       // .add(new TableHeaderCell<>("Maven Property"))
		                                                       .add(new TableHeaderCell<>("Purpose"))));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("ClassGraph"))
		                                       .add(new TableCell<>("4.1.1"))
		                                       .add(new TableCell<>("<a href=\"https://github.com/lukehutch/fast-classpath-scanner\" target=\"_blank\">Link</a>"))
		                                       //  .add(new TableCell<>("fastclasspath.version"))
		                                       .add(new TableCell<>("Scanner")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Google Guice"))
		                                       .add(new TableCell<>("4.2.0"))
		                                       .add(new TableCell<>("<a href=\"https://github.com/google/guice\" target=\"_blank\">Link</a>"))
		                                       //  .add(new TableCell<>("guice.version"))
		                                       .add(new TableCell<>("DI Provider")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Jackson JSON"))
		                                       .add(new TableCell<>("2.9.5"))
		                                       .add(new TableCell<>("<a href=\"https://github.com/FasterXML/jackson\" target=\"_blank\">Link</a>"))
		                                       //   .add(new TableCell<>("jackson.version"))
		                                       .add(new TableCell<>("JSON API")));

		div.add(table);

		div.add(new H3("Core Web Libraries"));

		BSTable<?> webTable = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                     .addClass(Table_Hover);
		webTable.setSmall(true);
		webTable.setBordered(true);
		webTable.setStriped(true);

		webTable.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Name"))
		                                                          .add(new TableHeaderCell<>("Version"))
		                                                          //    .add(new TableHeaderCell<>("Info"))
		                                                          .add(new TableHeaderCell<>("Source"))
		                                                          .add(new TableHeaderCell<>("Purpose"))));

		webTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("JQuery"))
		                                          .add(new TableCell<>("3.3.1"))
		                                          //  .add(new TableCell<>("JQueryPageConfigurator.class"))
		                                          .add(new TableCell<>("bower"))
		                                          .add(new TableCell<>("JavaScript API")));

		webTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Angular"))
		                                          .add(new TableCell<>("1.7.1"))
		                                          //   .add(new TableCell<>("AngularPageConfigurator.class"))
		                                          .add(new TableCell<>("bower"))
		                                          .add(new TableCell<>("Data Binder")));

		div.add(webTable);

		div.add(new H3("Servlet Libraries"));

		BSTable<?> servletInfoTable = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                             .addClass(Table_Hover);
		servletInfoTable.setSmall(true);
		servletInfoTable.setBordered(true);
		servletInfoTable.setStriped(true);

		servletInfoTable.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Name"))
		                                                                  .add(new TableHeaderCell<>("Version"))
		                                                                  //    .add(new TableHeaderCell<>("Info"))
		                                                                  .add(new TableHeaderCell<>("Source"))
		                                                                  .add(new TableHeaderCell<>("Purpose"))));

		servletInfoTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("ua-detector"))
		                                                  .add(new TableCell<>("0.9.22"))
		                                                  //  .add(new TableCell<>("Accessed in Page.class"))
		                                                  .add(new TableCell<>("maven"))
		                                                  .add(new TableCell<>("Device Info Provider")));

/*		servletInfoTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("commons-lang3"))
		                                                  .add(new TableCell<>("3.5"))
		                                                  //  .add(new TableCell<>("Accessed in Page.class"))
		                                                  .add(new TableCell<>("maven"))
		                                                  .add(new TableCell<>("Assists with String manipulation")));*/

		servletInfoTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("commons-text"))
		                                                  .add(new TableCell<>("3.5"))
		                                                  //  .add(new TableCell<>("Accessed in Page.class"))
		                                                  .add(new TableCell<>("maven"))
		                                                  .add(new TableCell<>("Assists with String manipulation")));

		servletInfoTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("commons-io"))
		                                                  .add(new TableCell<>("2.5"))
		                                                  //  .add(new TableCell<>("Accessed in Page.class"))
		                                                  .add(new TableCell<>("maven"))
		                                                  .add(new TableCell<>("Assist with IO usage")));

		servletInfoTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("quality-check"))
		                                                  .add(new TableCell<>("1.3"))
		                                                  //  .add(new TableCell<>("Accessed in Page.class"))
		                                                  .add(new TableCell<>("maven"))
		                                                  .add(new TableCell<>("Dependent from ua-detector")));

		div.add(servletInfoTable);

		//div.add(new H4<>("Total Standalone Size : 3.8MB"));

		return card;
	}

	private Div buildPluginsPanel()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3<>("Modular Plugins"));
		div.add("Everything is Modular. Simply including the library configures and activates as necessary.");
		//div.add("Including the Plugin JAR or depedency will add everything necessary to the build, and the Page objects for full reference. <br/>Selective Page References can be enabled.");
		return card;
	}

	private Div buildServicingPanel()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3<>("Servicing Your Needs"));
		div.add("This frameworks utilizes the Service Loader mechanism in order to link modules together. ");
		div.add("This requires you to configure your module accordingly.");
		div.add("The above tabs identify the available services for your application.");
		div.add("Utilize META-INF/services and the provides for module-info.java");
		return card;
	}

	private Div buildPersistenceAboutScreen()
	{

		Div about = new Div();
		about.add("Services are located in com.jwebmp.guicedpersistence.services");

		BSTable<?> settingUpTable = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                           .addClass(Table_Hover)
		                                           .fitInContainerBreakWord();
		settingUpTable.setSmall(true);
		settingUpTable.setBordered(true);
		settingUpTable.setStriped(true);

		settingUpTable.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Service Loader"))
		                                                                .add(new TableHeaderCell<>("Purpose"))
		                                               ));
		settingUpTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("PropertiesConnectionInfoReader"))
		                                                .add(new TableCell<>(
				                                                "Populates the ConnectionBaseInfo object with properties from the persistence unit.")));
		settingUpTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("PropertiesEntityManagerReader"))
		                                                .add(new TableCell<>(
				                                                "Utility Service that creates or modifies the properties HashMap for a Persistence Context before being converted to a ConnectionBaseInfo object.")));
		settingUpTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("ITransactionHandler"))
		                                                .add(new TableCell<>(
				                                                "Service that is used to automatically wrap database update calls (persist() merge()) etc through the EntityAssist module. It provides automated transaction management at a db call level." +
				                                                "<br/>These are enabled through the connection handler, such as <i>BTMAutomatedTransactionHandler.setActive()</i> for JTA or <i>JPAAutomatedTransactionHandler.setActive()</i> for JPA. " +
				                                                "<br/>Implementing your own is a piece of cake.")));
		Div sourceDis = new Div();
		addSourceToContainer(HomePageDBStartup.class, "startupexample.txt", Java, sourceDis);

		settingUpTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IAsyncStartup"))
		                                                .add(new TableCell<>(
				                                                "Allows you to start a persistence service outside of a request thread. The EntityManagerFactory becomes bound to the executing thread, and any joint threads there-of. If not using request scoped transactions, or module encapsulation, this would be to start the persistence layer from your &quot;EJB&quot;, or equivalent JPMS module. Asynchronously loaded via an ExecutorService<br/><br/>")
				                                                     .add(sourceDis)
				                                                     .add("In JRE 8 you may need to inject a DataSource to start the connection engine. This is due to how the layers are called between java versions and is outside of our control.")
		                                                    ));
		about.add(settingUpTable);

		return about;
	}

	private Div buildPersistenceSettingUpScreen()
	{
		SettingUpScreen settingUp = new SettingUpScreen();
		return settingUp;

	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Under The Hood"));
		return crumbs;
	}

	private Div buildJCache()
	{
		BSCardBody body = new BSCardBody().removePadding();
		return body;
	}

	private Div buildComponentRender()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();

/*		SourceCodeContentPanel<?> sourcePanel = new SourceCodeContentPanel("System Control", ComponentUtilityMethods, null);
		sourcePanel.setCodeButtonPanel(false);

		div.add(sourcePanel);*/
		return card;
	}

	private Div buildPowerfulMinimalist()
	{
		DisplayCard card = new DisplayCard();
		Div d = card.addCardBody();

		d.add(new H3<>("Going Postal"));
		d.add("Go straight into mobile and next generation web development utilizing Cordiva and PhoneGap. " +
		      "<br/>The Cordovify Plugin enables instant integration with Cordova allowing a complete suite of hybrid device functions for IOS, Android, Browser, Windows Mobile and Windows Universal applications");

		return card;
	}

}
