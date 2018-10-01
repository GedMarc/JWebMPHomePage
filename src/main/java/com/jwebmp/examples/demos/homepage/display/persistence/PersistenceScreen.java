package com.jwebmp.examples.demos.homepage.display.persistence;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.display.DisplayCard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.display.MetaInfTree;
import com.jwebmp.examples.demos.homepage.display.about.c3p0module.C3P0Screen;
import com.jwebmp.examples.demos.homepage.display.about.ehcache.EHCacheScreen;
import com.jwebmp.examples.demos.homepage.display.about.entityassist.EntityAssistScreen;
import com.jwebmp.examples.demos.homepage.display.about.persistencehandling.SettingUpScreen;
import com.jwebmp.examples.demos.homepage.display.about.requestscoped.JPAModuleScreen;
import com.jwebmp.examples.demos.homepage.display.about.requestscoped.JTAModuleScreen;
import com.jwebmp.examples.demos.homepage.display.about.requestscoped.RequestScopedTransactionsScreen;
import com.jwebmp.examples.demos.homepage.display.about.wildfly.WildflyScreen;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.options.BSTableOptions;
import com.jwebmp.plugins.bootstrap4.tables.BSTable;
import com.jwebmp.plugins.bootstrap4.tables.BSTableRow;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.*;

public class PersistenceScreen
		extends DisplayScreen<PersistenceScreen>
{

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer block = new BSContainer<>().addClass("col-md-12");
		block.add(buildPersistence());

		return block;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Privacy"));
		return crumbs;
	}


	private Div buildPersistence()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3("Persistence Handling"));

		div.add("These modules are <strong>completely optional</strong>, and provide a JPMS implementation for using JPA/JTA.</br>" +
		        "They utilize a 100% programmatic approach to connection and database management allowing overriding for test cases if need be." +
		        "<br/>This library uses guice-persist as a base, and requires that a Guice Module be registered. The most convenient way is to register a IGuiceModule with a class that extends AbstractDatabaseProviderModule." +
		        "<br/>Start your connection for back-ends using either IGuicePostStartup registered to the AbstractDatabaseProviderModule or for Web a Request Scoped Filter" +
		        "<br/>These are backwards compatible with JDK 8");

		div.add("<a href=\"#\">Add-Ons</a> provide additional configurations for enables modules, while <a href=\"#\">Modules</a> provide core functionality");

		BSNavTabs tabs = new BSNavTabs().setBordered(true)
		                                .setJustified(true);
		if (!getPage().isMobileOrSmartTablet())
		{
			tabs.setVerticalLeftTabs(true);
		}
		tabs.getTabContents()
		    .addClass(W_100);

		tabs.addTab("About", buildPersistenceAboutScreen(), true);

		tabs.addTab("Setting Up", new SettingUpScreen(), false);
		tabs.addTab("Scoping Transactions", new RequestScopedTransactionsScreen(), false);

		tabs.addTab("JPA Module", new JPAModuleScreen(), false);
		tabs.addTab("JTA Module", new JTAModuleScreen(), false);

		tabs.addTab("Entity Assist Module", new EntityAssistScreen(), false);
		tabs.addTab("C3P0 Addon", new C3P0Screen(), false);
		tabs.addTab("Wildfly Addon", new WildflyScreen(), false);
		tabs.addTab("Glassfish Addon", new DivSimple<>().add("2"), false);
		tabs.addTab("EhCache Addon", new EHCacheScreen(), false);
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
		                                                .add(new TableCell<>("Service that is used to manage transactions, whether for JTA or JPA.")));
		about.add(settingUpTable);

		about.add(new MetaInfTree("com.jwebmp.guicedpersistence.services.ITransactionHandler",
		                          "com.jwebmp.guicedpersistence.services.PropertiesConnectionInfoReader",
		                          "com.jwebmp.guicedpersistence.services.PropertiesEntityManagerReader"));

		return about;
	}

}
