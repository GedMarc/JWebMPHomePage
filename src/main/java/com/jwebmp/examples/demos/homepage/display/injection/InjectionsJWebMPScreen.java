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
public class InjectionsJWebMPScreen
		extends DisplayScreen
{
	public InjectionsJWebMPScreen()
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

		//container.add(wizard);
		container.add(buildJWebMP());

		return container;
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
