package com.jwebmp.examples.demos.homepage.display.about;

import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.html.*;
import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.general.MintonPanel;
import com.jwebmp.examples.demos.homepage.components.general.OptionsBrowser;
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
import com.jwebmp.plugins.fontawesome.FontAwesome;
import com.jwebmp.plugins.fontawesome.FontAwesomeIcons;
import com.jwebmp.plugins.fontawesome.FontAwesomeProperties;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.*;
import static com.jwebmp.utilities.StaticStrings.*;

public class AboutThisSiteScreen
		extends DisplayScreen<AboutThisSiteScreen>
{
	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer container = new BSContainer(BSContainerOptions.Container_Fluid);

		BSRow row = new BSRow();

		BSColumn column1 = new BSColumn(BSColumnOptions.Col_Md_6);
		BSColumn column2 = new BSColumn(BSColumnOptions.Col_Md_6);

		column1.add(new H1<>(HTML_TAB + "What You Looking At"));

		column2.add(new H1<>(HTML_TAB + "Do.. Whatever really"));
		column2.add(new MintonPanel<>("Why are all the pages built differently?", buildWhatThisIsAllAbout(), "bg-primary").setShowHeader(false));

		column1.add(new MintonPanel<>("Site Specs", buildSiteSpecs(), "bg-primary").setShowHeader(false));

		column2.add(new MintonPanel<>("Options Browser", buildOptionsBrowserAbout(), "bg-primary").setShowHeader(false));

		row.add(column1);
		row.add(column2);

		container.add(row);
		return container;
	}

	private Div buildWhatThisIsAllAbout()
	{
		DivSimple<?> div = new DivSimple<>();
		div.add(new H3<>("Non-Restrictive Development"));
		div.add("Code icons <strong>" + FontAwesome.icon(FontAwesomeIcons.code, FontAwesomeProperties.$2x)
		                                           .setTiny(true)
		                                           .toString(
				                                           true) + "</strong> display meaningful snippets of the widget that you are viewing to quickly grasp how to include it in your own sites.");

		div.add("This site uses many different methods of doing the same thing across many different pages.I think a core part should not be restricting development to any particular pattern.");

		div.add("There are tons of examples of the capabilities of the framework and how <i>any</i> pattern of your choice may be used in your web and mobile development, whether you are templating and importing HTMLs with specified variables or going native Java only, you're free to develop to your own preference.");
		//div.add("All the source is ofcourse available on GitHub, with the developer a simple support ticket,issue log, or stack exchange query away - You never have to worry about getting stuck");
		//div.add("I chose a straight forward WAR structure in a Domain Driven Architecture Pattern, running on Undertow, and using Azure with Hibernate 5.2 for the persistence strategy. Bitronix BTM for JTA to ensure Test and Production are identical." + "<br/>The Query Builder/Entity Management API is another stand-alone project and is a powerful - if unknown - Criteria Builder Manager for DDD with all the bells and whistles.");
		//div.add("I could have gone the EE EJB JCache DAO Named Queries route as well, but doesn't that seem like a touch overkill for a site with no background running tasks or JMS requirements? :)");

		return div;
	}

	private Div buildSiteSpecs()
	{
		Div div = new DivSimple();
		div.add(new H3("Site Specs"));


		BSTable<?> compatibilityTable = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                               .addClass(Table_Hover);
		compatibilityTable.setSmall(true);
		compatibilityTable.setBordered(true);
		compatibilityTable.setStriped(true);

		compatibilityTable.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Name"))
		                                                                    .add(new TableHeaderCell<>("Notes"))
		                                                                    .add(new TableHeaderCell<>("Info"))));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Guiced Persistence"))
		                                                    .add(new TableCell<>("Entity Manager/JTA Handler"))
		                                                    .add(new TableCell<>(
				                                                    "Multi-JPA (2.1^) Guice Injection Handler with built-in JTA for Test Cases and Production Environments. Uses Bitronix as the provider and AOP as the pattern.")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Entity Assist"))
		                                                    .add(new TableCell<>("Domain Query Handler"))
		                                                    .add(new TableCell<>("Criteria API 1.7 with CRP Builder. Tightly Couples Builders Utilizing Types.")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Guiced Hazelcast"))
		                                                    .add(new TableCell<>("JCache Provider"))
		                                                    .add(new TableCell<>("Provides Hazelcast Caching with the JCache API and Guice Injection")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Undertow"))
		                                                    .add(new TableCell<>("Embedded Container"))
		                                                    .add(new TableCell<>("Web-Services, Rest and Http Container. Running behind an NGINX reverse proxy.")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("JUnit 5.2.0"))
		                                                    .add(new TableCell<>("Testing Framework"))
		                                                    .add(new TableCell<>("Non-Restrictive, Can also use TestNG.")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Selenium 4.1.0"))
		                                                    .add(new TableCell<>("Integration Testing"))
		                                                    .add(new TableCell<>("You can use PhantomJS, but as usual a GUI is required.")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("JWebMP Plugins"))
		                                                    .add(new TableCell<>("All of them"))
		                                                    .add(new TableCell<>(
				                                                    "No better way to demonstrate complete interoperability than putting everything into a Single-Page styled site? Take a look at the raw source your browser received...")));

		div.add(compatibilityTable);
		return div;
	}

	private Div buildOptionsBrowserAbout()
	{
		Div d = new Div<>().addClass("row");

		d.add(new H3<>("The Object Browser").addClass(Col_12));

		BSColumn<?> leftColumn = new BSColumn<>(Col_Md_12);
		//BSColumn<?> rightColumn = new BSColumn<>(Col_Md_6);

		OptionsBrowser op = new OptionsBrowser(new AjaxCall<>()).setID("opAboutTree");
		leftColumn.add(op);
		leftColumn.add("Double click, or select the expand icon for more details");

		leftColumn.add("The options browser is a simple reflection of any object and can be found on all the demo pages");
		leftColumn.add("These classes are referenced from the getOptions() method available and produce the entire API of the referenced web library");
		leftColumn.add("The toString() for JavascriptPart returns a JSON representation of that object");

		d.add(leftColumn);
		//d.add(rightColumn);

		return d;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("This Site"));
		return crumbs;
	}
}
