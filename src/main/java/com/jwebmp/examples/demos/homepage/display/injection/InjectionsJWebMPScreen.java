package com.jwebmp.examples.demos.homepage.display.injection;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.DefaultTable;
import com.jwebmp.examples.demos.homepage.components.display.DefaultSmartWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.display.PluginModulePart;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

import jakarta.validation.constraints.NotNull;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

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

		container.add(new PluginModulePart("JWebMP Core"));

		Div aboutContent = buildJWebMP();
		Div coreSpi = buildCoreSPI();
		Div interceptSpi = buildIntercepterSPI();
		Div eventSpi = buildEventSPI();
		Div renderingSpi = buildRenderingSpi();
		Div componentRender = buildComponentRendering();

		DefaultSmartWizard wizard = new DefaultSmartWizard("injectionsJWebMP");

		wizard.addStep(new SmartWizardStep(aboutContent, new SmartWizardStepItem("About", new SmallText("Servicing from JWebMP"))));
		wizard.addStep(new SmartWizardStep(coreSpi, new SmartWizardStepItem("Core SPI", new SmallText("Core services for applications"))));
		wizard.addStep(new SmartWizardStep(interceptSpi, new SmartWizardStepItem("Interceptor SPI", new SmallText("Services for intercepting calls"))));
		wizard.addStep(new SmartWizardStep(eventSpi, new SmartWizardStepItem("Events SPI", new SmallText("Event services for binding and such"))));
		wizard.addStep(new SmartWizardStep(renderingSpi, new SmartWizardStepItem("Rendering SPI", new SmallText("Manage page rendering"))));
		wizard.addStep(new SmartWizardStep(componentRender, new SmartWizardStepItem("Component Render", new SmallText("Manipulating a components render"))));


		container.add(wizard);

		container.add(buildGoToSource(getClass()));

		return container;
	}

	private Div buildJWebMP()
	{
		Div div = new Div();
		div.add(new H3("JWebMP Service Loaders"));
		div.add("The JWebMP Core provides the bases of a lot of functionality for the system. " +
		        "<br/>These services allow you to manage and modify any portion of the rendering for the specific controllers");

		div.add("These services allow you to control the very heart of JWebMP" +
		        "<br/>For the most part, the only thing you will ever need to worry about, is <code>IPage</code>, and on the odd time, <code>IErrorPage</code>.");

		DefaultTable<?> table = new DefaultTable<>();
		table.addHeader("Service Loader", "Purpose ");

		table.addRow("IPage", "Designates a page that must be rendered. The class must extend Page.<br/> Annotate with @PageConfiguration to configure URL's");
		table.addRow("IErrorPage", "Allows to set a custom page to render errors on.");

		div.add(table);

		div.add("At any point, you can modify <code>GuiceContext.getAllLoadedServices()</code> to update and remove services as required, or configured dynamically.");

		return div;
	}

	private Div buildCoreSPI()
	{
		Div div = new Div();

		div.add(new H4("com.jwebmp.core.services"));

		div.add("JWebMP Services are directly related to the display and representation of a site or site URL." +
		        "<br/> IPage has a default implementation <code>extends Page</code> that should be used for all pages.");
		DefaultTable<?> table = new DefaultTable<>();

		table.addRow("IPageConfigurator", "Configures and modifies the page before rendering as required by the given module");
		table.addRow("IRegularExpressions", "Supplies a list of default regular expressions to the client browser on connect for quick static access");

		div.add(table);

		return div;
	}

	private Div buildIntercepterSPI()
	{
		Div div = new Div();

		div.add(new H4("com.jwebmp.interception.services"));

		div.add("Interception Services are for investigating calls and ensuring their validity in terms of the application." +
		        "<br/>The following services are made available for intercepting calls.");

		DefaultTable<?> table = new DefaultTable<>();
		table.addHeader("Service Loader", "Purpose ");

		table.addRow("AjaxCallIntercepter", "Intercepts Ajax calls from the client resulting in an AjaxCall and AjaxResponse");
		table.addRow("DataCallIntercepter", "Intercepts Data calls from the client made directly to servlets");
		table.addRow("SiteCallIntercepter", "Intercepts All Calls");

		div.add(table);

		return div;
	}

	private Div buildEventSPI()
	{
		Div div = new Div();

		div.add(new H4("com.jwebmp.events"));

		div.add("Event Intercepters are used for configuring event components with required values for the library." +
		        "<br/>These exist to be able to switch between Angular JS and Vue with ease and no impact on current applications." +
		        "<br/>The names are pretty self explanatory. All services implement <code>DefaultService</code> and are named and sortable." +
		        "<br/>You should nearly never have to use any of these");

		DefaultSlimScroll scroll = new DefaultSlimScroll();
		DefaultTable<?> table = new DefaultTable<>();
		table.addHeader("Service Loader");

		table.addRow("IOnActivateService");
		table.addRow("IOnBeforeActivateService");
		table.addRow("IOnBeforeCloseService");
		table.addRow("IOnBeforeLoadService");
		table.addRow("IOnBlurService");
		table.addRow("IOnBeforeStopService");
		table.addRow("IOnButtonClickService");
		table.addRow("IOnCancelService");
		table.addRow("IOnChangeService");
		table.addRow("IOnCheckedService");
		table.addRow("IOnCloseService");
		table.addRow("IOnClickService");
		table.addRow("IOnCompleteService");
		table.addRow("IOnCreateService");
		table.addRow("IOnDeActivateService");
		table.addRow("IOnDragService");
		table.addRow("IOnDragStartService");
		table.addRow("IOnDragStopService");
		table.addRow("IOnDropService");
		table.addRow("IOnDropOverService");
		table.addRow("IOnFocusService");
		table.addRow("IOnKeyDownService");
		table.addRow("IOnKeyPressedService");
		table.addRow("IOnKeyUpService");
		table.addRow("IOnLoadService");
		table.addRow("IOnMouseEnterService");
		table.addRow("IOnMouseMoveService");
		table.addRow("IOnMouseOutService");
		table.addRow("IOnMouseOverService");
		table.addRow("IOnMouseUpService");
		table.addRow("IOnOpenService");
		table.addRow("IOnReceiveService");
		table.addRow("IOnRemoveService");
		table.addRow("IOnResizeService");
		table.addRow("IOnResizeStopService");
		table.addRow("IOnResponseService");
		table.addRow("IOnRightClickService");
		table.addRow("IOnSelectService");
		table.addRow("IOnSearchService");
		table.addRow("IOnSelectedService");
		table.addRow("IOnSelectedService");
		table.addRow("IOnSelectingService");
		table.addRow("IOnSlideService");
		table.addRow("IOnSortService");
		table.addRow("IOnSpinService");
		table.addRow("IOnStartService");
		table.addRow("IOnStopService");
		table.addRow("IOnSubmitService");
		table.addRow("IOnUnSelectedService");
		table.addRow("IOnUpdateService");
		table.addRow("IOnDataBind");
		table.addRow("IOnDataBindCloak");

		scroll.add(table);

		div.add(scroll);

		return div;
	}

	private Div buildRenderingSpi()
	{
		Div div = new Div();
		div.add(new H3("Rendering Services"));
		div.add("The following services allow custom management of the page rendering itself," +
		        "<br/>These can render anything in the header, and scripts body footer.");

		DefaultTable<?> table = new DefaultTable<>();
		table.addHeader("Service Loader", "Purpose ");

		table.addRow("IDynamicRenderingServlet", "Servlets that can be rendered in-page, or dynamically as its own servlet can be serviced with these.");
		table.addRow("RenderBeforeLinks", "Renders a StringBuilder before the CSS Links in the page");
		table.addRow("RenderAfterLinks", "Renders a StringBuilder after the CSS Links in the page");
		table.addRow("RenderBeforeDynamicScripts", "Renders a StringBuilder before the IDynamicScriptService's are placed in the page");
		table.addRow("RenderAfterDynamicScripts", "Renders a StringBuilder after the IDynamicScriptService's are placed in the page");
		table.addRow("RenderBeforeScripts", "Renders a StringBuilder before the Script Links in the page");
		table.addRow("RenderAfterScripts", "Renders a StringBuilder after the Script Links in the page");
		div.add(table);
		return div;
	}

	private Div buildComponentRendering()
	{
		Div div = new Div();
		div.add(new H3("Component Render"));
		div.add("Every component comes with a set of methods that can be used/overridden to return absolutely anything at any point in the rendering process." +
		        "<br/> These allow you to completely manage every part of the rendering process at every level");

		DefaultTable<?> table = new DefaultTable<>();
		table.addHeader("Method", "Purpose");

		table.addRow("<code>toString()</code>", "Renders the component in JSON format");
		table.addRow("<code>toString(tabCount:int)</code>", "Renders the HTML of the component with the given number of tabs");
		table.addRow("<code>toString(true)</code>", "Renders the HTML of the component with 0 tab count");
		table.addRow("<code>renderHTML()</code>", "Method that calls the rendering methods below. Override to control entire rendering mechanism");
		table.addRow("<code>renderBeforeTag()</code>", "Anything to render before the tag");
		table.addRow("<code>renderTextBeforeChildren()</code>", "If <code>isTextRenderedBeforeChildren()</code>");
		table.addRow("<code>renderBeforeChildren()</code>", "Returns StringBuilder for anything to be added custom before children rendering");
		table.addRow("<code>renderChildren()</code>", "Returns StringBuilder of children renderHTML()");
		table.addRow("<code>renderTextAfterChildren()</code>", "If <code>!isTextRenderedBeforeChildren()</code>");
		table.addRow("<code>renderAfterChildren()</code>", "Returns StringBuilder of anything custom to add after children have rendered");
		table.addRow("<code>renderAfterTag()</code>",
		             "Returns StringBuilder of anything custom to add after the tag. use <code>getTabCount()</code> to align properly in development mode.");

		div.add(table);

		Div scroll = new Div();
		addSourceToContainer(InjectionsJWebMPScreen.class, "comment_class.txt", Java, scroll);
		DefaultReadMore more = new DefaultReadMore(scroll, "Comment Component Example");
		div.add(more);

		return div;
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
