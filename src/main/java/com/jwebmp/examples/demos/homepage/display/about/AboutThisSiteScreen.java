package com.jwebmp.examples.demos.homepage.display.about;

import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScrollFeature;
import com.jwebmp.examples.demos.homepage.components.display.CircleDisplayWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.general.OptionsBrowser;
import com.jwebmp.examples.demos.homepage.components.general.SPILoadedServicesBrowser;
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
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.*;

public class AboutThisSiteScreen
		extends DisplayScreen<AboutThisSiteScreen>
{
	public AboutThisSiteScreen()
	{
		super("Site Development");
	}

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer container = new BSContainer(BSContainerOptions.Container_Fluid);

		DivSimple<?> divApi = new DivSimple<>();

		CircleDisplayWizard wizard = new CircleDisplayWizard("siteoperations");
		wizard.addStep(new SmartWizardStep(buildRuntimeScreen(),new SmartWizardStepItem("RUNTIME")));
		wizard.addStep(new SmartWizardStep(divApi,new SmartWizardStepItem("API")));

		BSRow runtimeRow = new BSRow();


		BSColumn column1 = new BSColumn(BSColumnOptions.Col_Md_6);
		BSColumn column2 = new BSColumn(BSColumnOptions.Col_Md_6);

		column2.add(buildOptionsBrowserAbout());

		runtimeRow.add(column1);
		runtimeRow.add(column2);

		divApi.add(runtimeRow);
		container.add(wizard);

		Div bottomRow = new Div();
		container.add(bottomRow);

		return container;
	}

	private Div buildRuntimeScreen()
	{
		DivSimple div = new DivSimple();
		div.add(new H3<>("This site is using the following frameworks and modules to run"));
		div.add("The services loaded show in order of loading, All the service providers that have been loaded so far.");


		BSRow runtimeRow = new BSRow();



		BSColumn column1 = new BSColumn(BSColumnOptions.Col_Md_6);
		BSColumn column2 = new BSColumn(BSColumnOptions.Col_Md_6);

//		column1.add(new H1<>("All Optional Plugins"));
		column1.add(buildSiteSpecs());

		column2.add(buildSPITree());

		runtimeRow.add(column1);
		runtimeRow.add(column2);

		div.add(runtimeRow);
		return div;
	}

	private Div buildSPITree()
	{
		DivSimple<?> divSimple = new DivSimple<>();
	//	divSimple.add(new H3<>("Services Loaded"));
		//divSimple.add("<code>GuiceContext.getAllLoadedServices()</code>");
		divSimple.add(new SPILoadedServicesBrowser());
		DefaultSlimScrollFeature scroll = new DefaultSlimScrollFeature(divSimple);
		scroll.getOptions()
		      .setHeight("450px");

		return divSimple;
	}

	private Div buildSiteSpecs()
	{
		Div div = new DivSimple();
		//div.add(new H3("Site Specs"));
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
