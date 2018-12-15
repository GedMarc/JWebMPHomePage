package com.jwebmp.examples.demos.homepage.display.injection;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.core.base.html.SmallText;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.display.DefaultDisplayWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

import javax.validation.constraints.NotNull;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;

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

		container.add(new H3<>("Injection Control<br/>"));

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

		container.add(wizard);

		return container;
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
