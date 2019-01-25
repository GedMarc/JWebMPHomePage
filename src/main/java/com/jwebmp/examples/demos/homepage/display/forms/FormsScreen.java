package com.jwebmp.examples.demos.homepage.display.forms;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.core.base.html.SmallText;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.display.DefaultSmartWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.display.PluginModulePart;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
import javax.validation.constraints.NotNull;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class FormsScreen
		extends DisplayScreen<FormsScreen>
{
	public FormsScreen()
	{
		//Default constructor
	}

	@Override
	public @NotNull BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(Container_Fluid);
		container.add(new PluginModulePart("JWebMP Core"));
		container.add(new FormsPart());
		container.add(buildGoToSource(getClass()));
		return container;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));

		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Forms"));
		return crumbs;
	}

	@Override
	@CacheResult
	public @NotNull String toString(@CacheKey Integer integer)
	{
		return super.toString(integer);
	}

	class FormsPart
			extends DisplayPart<FormsPart>
	{
		public FormsPart()
		{
			BSCardBody all = getDefaultBody();

			DefaultSmartWizard wizard = new DefaultSmartWizard("formsWizard");

			wizard.addStep(new SmartWizardStep(buildAbout(), new SmartWizardStepItem("About", new SmallText("The Important Things"))));
			wizard.addStep(new SmartWizardStep(buildBindings(), new SmartWizardStepItem("Bindings", new SmallText("Mapping to client side variables"))));

			all.add(wizard);
			add(all);
		}

		private Div buildAbout()
		{
			Div div = new Div();
			div.add("Forms in their most basic form are nothing more than additional tags, with binding data attributes using the client provider (AngularJS or <i>Vue</i>)" +
			        "<br/>This allows a very flexible system allowing cross-page, cross field, validations and more to be incredibly simple.");

			div.add("You can view below a basic form with a single input, and custom messages");

			addSourceToContainer(FormsScreen.class, "bindingsbasic.txt", Java, div);

			return div;
		}

		private Div buildBindings()
		{
			Div div = new Div();

			div.add("Data Bindings are variable based, and are registered using <code>.bind()</code>. " +
			        "<br/>You can return any number of variables at any time back to the server, and send any number of variables to the client." +
			        "<br/>You can map any input to any variable, whether in a form or not, and displaying is as easy as standard Angular syntax {{variable.name}}")
			   .addClass("ng-non-bindable");
			div.add(
					"Any object that can be serialized with the JacksonJSON framework (pretty much everything) can be used as a transfer object. " +
					"<br/JavaScriptPart takes care of serialization and adds some fields like referenceId");
			div.add(getCodeBlockJava(FormsPart.class, "bindingsbasic_2.txt").setID("coce1"));
			div.add("Client Side Variables/Pre-Populated form fields are sent to the client via addDto() method with the given object");
			div.add(getCodeBlockJava(FormsPart.class, "bindingsbasic_3.txt").setID("coce2"));

			div.add("Angular forms allow some good controls...");
			Div more = getCodeBlockJava(FormsPart.class, "formbasics.txt");
			div.add(new DefaultReadMore(more, "Angular Form Basics"));

			return div;
		}

	}
}
