package com.jwebmp.examples.demos.homepage.display.about;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.display.DisplayCard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.display.MetaInfTree;
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

import static com.jwebmp.plugins.bootstrap4.options.BSMarginOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.*;

public class SPIScreen
		extends DisplayScreen<SPIScreen>
{
	public SPIScreen()
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
		tabs.addTab("Injection", buildInjection(), false);
		tabs.addTab("*<i>Classpath</i>", buildClassPath(), false);
		tabs.addTab("JWebMP", buildJWebMP(), false);

		//tabs.addTab("Persistence", buildPersistence(), false);
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

		//		column1.add(buildDependencies());
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

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuicePreStartup"))
		                                       .add(new TableCell<>(
				                                       "Executes any required code blocks before the Injector is built. Great for configuration. Do not try to call the GuiceContext in these classes")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuiceModule"))
		                                       .add(new TableCell<>("Service Loader to port your existing Guice Modules into the Global Injection Context")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuiceDefaultBinder"))
		                                       .add(new TableCell<>(
				                                       "Registers a Guice Injection module with the default binders. This is for backend binding with no requirements on servlets, and provides nearly the same encapsulation via JPMS as an EJB would to an WAR")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuicePostStartup"))
		                                       .add(new TableCell<>(
				                                       "Executes the given operations immediately after the injection cycle has completed. Used mostly to start database connections before making the site available")));
		div.add(table);

		div.add(new Image("images/guiceinjection/StartupOrder.png"));


		return card;
	}

	private Div buildClassPath()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3("Classpath Scanning with <a href=\"https://github.com/classgraph/classgraph\" target=\"_blank\">ClassGraph</a>"));
		div.add("Class Path scanning is completely optional, and allows you to manage and scan as necessary utilizing ClassGraph. " +
		        "<br/>These Services allow you to perform basic operations to filter and clean any items from the scan yielding a more than 50% performance increase." +
		        "<br/>You can also set and modify the GuiceContext scan result as necessary.");


		BSTable<?> table = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                  .addClass(Table_Hover);
		table.setSmall(true);
		table.setBordered(true);
		table.setStriped(true);

		table.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Service Loader"))
		                                                       .add(new TableHeaderCell<>("Purpose"))
		                                      ));
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuiceConfigurator"))
		                                       .add(new TableCell<>(
				                                       "Configures the scanner for boot operations. The scanner is optional and doesn't execute by default. The classpathScan property can be set to enable this functionality.")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IFileContentsScanner"))
		                                       .add(new TableCell<>(
				                                       "Registers a filename to be collected, such as persistence.xml or hazelcast-client.xml. Ensure that the path is located in a PathContentsScanner")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IPackageContentsScanner"))
		                                       .add(new TableCell<>(
				                                       "Registers the given package to be included during the optional classpath scan. Only these packages will be included in retrieved results")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IPathContentsScanner"))
		                                       .add(new TableCell<>("Registers the given path (No Class Files) to search for IFileContentsScanners.<br/> Usually META-INF")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IPathContentsBlacklistScanner"))
		                                       .add(new TableCell<>("Registers paths to be excluded from all scans")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuiceScanJarExclusions"))
		                                       .add(new TableCell<>("Registers JAR files to be excluded from all scans")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuiceScanModuleExclusions"))
		                                       .add(new TableCell<>("Registers Modules (JPMS) to be excluded from all scans")));

		div.add(table);

		div.add(new Image("images/guiceinjection/ClasspathScanningConfiguration.png"));

		return card;
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

		div.add(new MetaInfTree("com.jwebmp.core.services.IPage", "com.jwebmp.core.services.IErrorPage"));

		return card;
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
