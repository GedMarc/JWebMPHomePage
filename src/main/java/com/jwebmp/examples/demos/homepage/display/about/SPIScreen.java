package com.jwebmp.examples.demos.homepage.display.about;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.display.DisplayCard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.display.MetaInfServicesTree;
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

import static com.jwebmp.plugins.bootstrap4.options.BSMarginOptions.*;

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
		//tabs.addTab("Injection", buildInjection(), false);
		//	tabs.addTab("*<i>Classpath</i>", buildClassPath(), false);
		//	tabs.addTab("JWebMP", buildJWebMP(), false);

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

		div.add(new MetaInfServicesTree("com.jwebmp.core.services.IPage", "com.jwebmp.core.services.IErrorPage"));

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
