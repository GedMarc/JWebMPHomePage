package com.jwebmp.examples.demos.homepage.display.injection;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.display.DefaultDisplayWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayCard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSTableOptions;
import com.jwebmp.plugins.bootstrap4.tables.BSTable;
import com.jwebmp.plugins.bootstrap4.tables.BSTableRow;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

import javax.validation.constraints.NotNull;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.*;

@SuppressWarnings("Duplicates")
public class InjectionsBasicsScreen
		extends DisplayScreen
{
	public InjectionsBasicsScreen()
	{

	}

	@Override
	public @NotNull BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(Container_Fluid);
		//container.addClass("row");

		//container.add(new H3<>("Injection Control<br/>"));

		Div aboutContent = new Div();
		Div whatAvailableContainer = new Div();
		Div pageContent = new Div();
		Div dataContent = new Div();
		Div pageContentRow = new BSRow();


		DefaultSlimScroll scroll = new DefaultSlimScroll(pageContentRow);
		scroll.getOptions()
		      .setHeight("500px");

		DefaultDisplayWizard wizard = new DefaultDisplayWizard("eventWizard");

		wizard.addStep(new SmartWizardStep(aboutContent, new SmartWizardStepItem("About", new SmallText("The Important Things"))));
		wizard.addStep(new SmartWizardStep(whatAvailableContainer, new SmartWizardStepItem("What's Available", new SmallText("Overview of the objects"))));
		wizard.addStep(new SmartWizardStep(pageContent, new SmartWizardStepItem("Defaults", new SmallText("Standard Events Available"))));
		wizard.addStep(new SmartWizardStep(dataContent, new SmartWizardStepItem("Data", new SmallText("Return data from client"))));


		container.add(buildInjection());


		//container.add(wizard);

		return container;
	}


	private Div buildInjection()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3("Injection Configurations"));

		div.add(new H4("com.jwebmp.guicedinjection.interfaces"));
		div.add("These services allow to control the boot and injection configuration sequence in its entirety." +
		        "<br/>All Services (extending IDefaultService) are named and sorted accordingly to <code>sortOrder()</code>");
		div.add("The Guice Injection assistant (<a target=\"_blank\" href=\"https://github.com/GedMarc/GuiceInjection\">Guiced Injection<a/>) " +
		        " can be run standalone without JWebMP in any JRE8 environment. As a completely portable library you can configure your bindings with your current application" +
		        " long before porting a site.");

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
				                                       "Configures the class path scanner during the booting sequence. This allows full control over what is scanned and not scanned during boot." +
				                                       "<br/>By default the Class Path Scanner is disabled.")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuicePreStartup"))
		                                       .add(new TableCell<>(
				                                       "Executes any required code blocks before the Injector is built. Great for configuration. Do not try to inject during this phase.")));


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


	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));

		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Events"));
		return crumbs;
	}

}
