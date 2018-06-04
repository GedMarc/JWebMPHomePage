package com.jwebmp.examples.demos.homepage.display.about;

import com.jwebmp.base.html.*;
import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.MintonPanel;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.bootstrap4.options.BSTableOptions;
import com.jwebmp.plugins.bootstrap4.tables.BSTable;
import com.jwebmp.plugins.bootstrap4.tables.BSTableRow;

import static com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts.ComponentUtilityMethods;
import static com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts.InjectionControlMethods;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_12;
import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.Table_Hover;

public class UnderTheHoodScreen
		extends DisplayScreen<UnderTheHoodScreen>
{
	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer container = new BSContainer(BSContainerOptions.Container_Fluid);

		BSRow row = new BSRow();

		BSColumn column1 = new BSColumn(BSColumnOptions.Col_Md_8);
		BSColumn column2 = new BSColumn(BSColumnOptions.Col_Md_4);


		column1.add(new MintonPanel<>("Whats running this thing", buildUnderTheHood(), "bg-primary").setShowHeader(false));
		column1.add(new MintonPanel<>("Whats running this thing", buildPowerfulMinimalist(), "bg-primary").setShowHeader(false));

		column1.add(new MintonPanel<>("Component Common Methods", buildComponentRender(), "bg-primary").setShowHeader(false));

		column2.add(new MintonPanel<>("Injection Assists", buildSiteControl(), "bg-primary").setShowHeader(false));
		column2.add(new MintonPanel<>("Plugins?", buildPluginsPanel(), "bg-primary").setShowHeader(false));

		row.add(column1);
		row.add(column2);

		container.add(row);
		return container;
	}

	private Div buildUnderTheHood()
	{
		Div div = new DivSimple();
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

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Fast Classpath Scanner"))
		                                       .add(new TableCell<>("2.21"))
		                                       .add(new TableCell<>("<a href=\"https://github.com/lukehutch/fast-classpath-scanner\" target=\"_blank\">Link</a>"))
		                                       //  .add(new TableCell<>("fastclasspath.version"))
		                                       .add(new TableCell<>("Scanner")));
/*
		table.add(new BSTableRow<>(BSTableOptions.Table_Hover).add(new TableCell<>("Google Guava"))
		                                                      .add(new TableCell<>("24.1.1-jre"))
		                                                      .add(new TableCell<>("<a href=\"https://github.com/google/guava\" target=\"_blank\">Link</a>"))
		                                                      .add(new TableCell<>("guava.version"))
		                                                      .add(new TableCell<>("Utility")));
*/
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Google Guice"))
		                                       .add(new TableCell<>("4.2.0"))
		                                       .add(new TableCell<>("<a href=\"https://github.com/google/guice\" target=\"_blank\">Link</a>"))
		                                       //  .add(new TableCell<>("guice.version"))
		                                       .add(new TableCell<>("DI Provider")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Guice Injection"))
		                                       .add(new TableCell<>(""))
		                                       .add(new TableCell<>("<a href=\"https://github.com/GedMarc/GuiceInjection\" target=\"_blank\">Link</a>"))
		                                       // .add(new TableCell<>("jwebmp.version"))
		                                       .add(new TableCell<>("Domain Context")));
/*
		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Servlet 3"))
		                                       .add(new TableCell<>("3.1.0"))
		                                       .add(new TableCell<>("3.0 can be used"))
		                                       .add(new TableCell<>("servlet.api.version"))
		                                       .add(new TableCell<>("Web API")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("JSR311"))
		                                       .add(new TableCell<>("1.1.1"))
		                                       .add(new TableCell<>("javax.ws.rs"))
		                                       .add(new TableCell<>("jsr311.version"))
		                                       .add(new TableCell<>("Rest API")));*/

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("JacksonJSON"))
		                                       .add(new TableCell<>("2.9.5"))
		                                       .add(new TableCell<>("<a href=\"https://github.com/FasterXML/jackson\" target=\"_blank\">Link</a>"))
		                                       //   .add(new TableCell<>("jackson.version"))
		                                       .add(new TableCell<>("JSON API")));

		div.add(table);


		div.add(new H3("Web Libraries"));

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

		webTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("UA Detector"))
		                                          .add(new TableCell<>("0.9.22"))
		                                          //  .add(new TableCell<>("Accessed in Page.class"))
		                                          .add(new TableCell<>("maven"))
		                                          .add(new TableCell<>("Device Info Provider")));


		webTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("JQuery"))
		                                          .add(new TableCell<>("3.2.1"))
		                                          //  .add(new TableCell<>("JQueryPageConfigurator.class"))
		                                          .add(new TableCell<>("bower"))
		                                          .add(new TableCell<>("JavaScript API")));


		webTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Angular"))
		                                          .add(new TableCell<>("1.4.6"))
		                                          //   .add(new TableCell<>("AngularPageConfigurator.class"))
		                                          .add(new TableCell<>("bower"))
		                                          .add(new TableCell<>("Data Binder")));

		div.add(webTable);

		return div;
	}

	private Div buildPowerfulMinimalist()
	{
		Div d = new Div();

		d.add(new H3<>("Going Postal"));
		d.add("Go straight into mobile and next generation web development utilizing Cordiva and PhoneGap. The Cordovify Plugin enables instant integration with Cordova allowing a complete suite of hybrid device functions for IOS, Android, Browser, Windows Mobile and Windows Universal applications");

		return d;
	}

	private Div buildComponentRender()
	{
		DivSimple<?> div = new DivSimple<>();
		div.addClass("row");

		SourceCodeContentPanel<?> sourcePanel = new SourceCodeContentPanel("System Control", ComponentUtilityMethods, null);
		sourcePanel.setCodeButtonPanel(false);

		div.add(sourcePanel);
		return div;
	}

	private Div buildSiteControl()
	{
		DivSimple<?> div = new DivSimple<>();
		div.addClass("row");

		div.add(new H5<>("Change anything with interfaces").addClass(Col_12));
		div.add(new H5<>("Service Locators are used to configure classpath scanning").addClass(Col_12));

		SourceCodeContentPanel<?> sourcePanel = new SourceCodeContentPanel("Dynamic Call Injections", InjectionControlMethods, null);
		sourcePanel.setCodeButtonPanel(false);
		div.add(sourcePanel);

		return div;
	}

	private Div buildPluginsPanel()
	{
		Div div = new DivSimple();
		div.add(new H3<>("Modular Plugins"));
		div.add("Plugins are designed in a modular fashion, and are built on an inclusive basis");
		div.add("Including the Plugin JAR or depedency will add everything necessary to the build, and the Page objects for full reference. <br/>Selective Page References can be enabled.");
		return div;
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

}
