package com.jwebmp.examples.demos.homepage.display.persistence;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.display.DisplayCard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.display.about.c3p0module.C3P0Screen;
import com.jwebmp.examples.demos.homepage.display.about.ehcache.EHCacheScreen;
import com.jwebmp.examples.demos.homepage.display.about.entityassist.EntityAssistScreen;
import com.jwebmp.examples.demos.homepage.display.about.persistencehandling.GuicedPersistenceScreen;
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
		                                             .setText("Persistence"));
		return crumbs;
	}


	private Div buildPersistence()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3("Core Modular Addons"));

		BSNavTabs tabs = new BSNavTabs().setBordered(true)
		                                .setJustified(true);
		if (!getPage().isMobileOrSmartTablet())
		{
			tabs.setVerticalLeftTabs(true);
		}
		tabs.getTabContents()
		    .addClass(W_100)
		    .addStyle("padding:0px;");

		tabs.addTab("About", buildPersistenceAboutScreen(), true);

		tabs.addTab("Guiced Persistence", new GuicedPersistenceScreen(), false);

		tabs.addTab("JPA Module", new JPAModuleScreen(), false);
		tabs.addTab("JTA Module", new JTAModuleScreen(), false);


		tabs.addTab("Scoping Transactions", new RequestScopedTransactionsScreen(), false);


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

		about.add("These modules are <strong>completely optional</strong>, and provide 100% JPMS modular implementations for using various APIs.</br>" +
		          "These all utilize a programmatic approach allowing overriding for test cases if need be, and are backwards compatible with JRE 8 to assist in porting application to JPMS");

		about.add("<a href=\"#\">Add-Ons</a> provide additional configurations for enables modules, while <a href=\"#\">Modules</a> provide core functionality");

		return about;
	}

}
