package com.jwebmp.examples.demos.homepage.display.about;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.display.DisplayCard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
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

import java.util.ArrayList;
import java.util.List;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.*;

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
		                                     .removeSpacingTop();
		tabs.addTab("About", buildDefaultScreen(), true);
		tabs.addTab("Injection", buildInjection(), false);
		tabs.addTab("JWebMP", buildJWebMP(), false);
		tabs.addTab("JPA JTA", buildPersistence(), false);
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
		column1.add(buildPowerfulMinimalist());

		column1.add(buildComponentRender());

		//column2.add(buildSiteControl());
		column2.add(buildPluginsPanel());

		row.add(column1);
		row.add(column2);

		body.add(row);
		return body;
	}

	private BSCardBody buildJWebMP()
	{
		BSCardBody body = new BSCardBody().removePadding();
		return body;
	}

	private BSCardBody buildInjection()
	{
		BSCardBody body = new BSCardBody().removePadding();
		return body;
	}

	private BSCardBody buildPersistence()
	{
		BSCardBody body = new BSCardBody().removePadding();
		return body;
	}

	private BSCardBody buildJCache()
	{
		BSCardBody body = new BSCardBody().removePadding();
		return body;
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

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Fast Classpath Scanner"))
		                                       .add(new TableCell<>("2.21"))
		                                       .add(new TableCell<>("<a href=\"https://github.com/lukehutch/fast-classpath-scanner\" target=\"_blank\">Link</a>"))
		                                       //  .add(new TableCell<>("fastclasspath.version"))
		                                       .add(new TableCell<>("Scanner")));

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

		return card;
	}

	private Div buildPowerfulMinimalist()
	{
		DisplayCard card = new DisplayCard();
		Div d = card.addCardBody();

		d.add(new H3<>("Going Postal"));
		d.add("Go straight into mobile and next generation web development utilizing Cordiva and PhoneGap. The Cordovify Plugin enables instant integration with Cordova allowing a complete suite of hybrid device functions for IOS, Android, Browser, Windows Mobile and Windows Universal applications");

		return card;
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

	private Div buildPluginsPanel()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3<>("Modular Plugins"));
		div.add("Everything is Modular. Simply including the library configures and activates as necessary.");
		//div.add("Including the Plugin JAR or depedency will add everything necessary to the build, and the Page objects for full reference. <br/>Selective Page References can be enabled.");
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

}
