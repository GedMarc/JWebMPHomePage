package com.jwebmp.examples.apps.homepage.pages.helloworld;

import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.events.activate.ActivateAdapter;
import com.jwebmp.examples.apps.homepage.components.DefaultTable;
import com.jwebmp.examples.apps.homepage.components.browse.DefaultPackageAPI;
import com.jwebmp.examples.apps.homepage.components.browse.OptionsBrowser;
import com.jwebmp.examples.apps.homepage.components.SwapScreen;
import com.jwebmp.plugins.themes.mintontheme.pages.PageTitle;
import com.jwebmp.plugins.themes.mintontheme.partials.BreadcrumbItem;
import com.jwebmp.plugins.themes.mintontheme.partials.Breadcrumbs;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_12;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_Md_6;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.Java;

public class EventsPage extends SwapScreen<EventsPage>
{
	public EventsPage()
	{
		BSCardBody<?> all = getDefaultBody();
		DivSimple<?> aboutContent = new DivSimple<>();
		all.add(aboutContent);
		
		aboutContent.add("Events are added to components through the <code>addEvent()</code> method." +
				                 "<br/>");
		
		addSourceToContainer(EventsPage.class, "event_example.txt", Java, aboutContent);
		aboutContent.add(
				"Event Classes are used on the server to perform the relevant function. <br/>" +
						"<br/>Event Classes can/should be stored in their own classes (although this is not enforced), " +
						" This will allow events to be portable and makes development incredibly easy<br/>" +
						"<code>AjaxCall</code> and <code>AjaxResponse</code> are request scoped objects that manage all WebSocket and Ajax calls. Calls are incoming, Responses are outgoing<br/>" +
						"This means that you use the same event classes for web socket server side push, and ajax client driven events");
		DivSimple<?> whatAvailableContainer = new DivSimple<>();
		all.add(whatAvailableContainer);
		
		BSRow<?> rowContainer = new BSRow<>();
		whatAvailableContainer.add(new H3<>("The Call and Response objects"));
		whatAvailableContainer.add("Every call has default data associated with it, for the Call (Client to Server), and Response (Server to Client). " +
				                           "<br/>For WebSockets, Only the AjaxResponse needs to be used.");
		whatAvailableContainer.add(rowContainer);
		
		rowContainer.add(new OptionsBrowser(new AjaxCall<>(), "ajaxCallBrowser").addClass(Col_Md_6, Col_12));
		rowContainer.add(new OptionsBrowser(new AjaxResponse<>(), "ajaxResponseBrowser").addClass(Col_Md_6, Col_12));
		
		whatAvailableContainer.add("<br/>Events themselves are components, and can be persisted in JSON format using the <code>toString()</code> method.<br/>" +
				                           "A component can contain any number of events to be fired at any point.");
		
		whatAvailableContainer.add(
				"The default events available are located in <code>com.jwebmp.core.events</code> and are generically typed to limit events to certain objects,<br/>" +
						"Plugins may also specify their own events in the same manner");
		
		DivSimple<?> pageContent = new DivSimple<>();
		all.add(pageContent);
		pageContent.add(new H3<>("Below you can find the default events in the core of JWebMP."));
		
		pageContent.add("Navigate through the objects in the packages to the left below, select the object to view the capabilities in the right." +
				                "<br/>This tree is using a JSTreeOnSelectEvent and rendering the ObjectBrowser for the id of the selected node.");
		
		pageContent.add(new DefaultPackageAPI("com.jwebmp.core.events", ActivateAdapter.class, null, false, true));
		
		DivSimple<?> dataContent = new DivSimple<>();
		all.add(dataContent);
		dataContent.add(new H3<>("Moving Data between Client and Server"));
		dataContent.add("Data is stored in variables on the client, and are registered in any component. This keeps the sessions light, fast, and compact. " +
				                "<br/>Client Variables are named in dot syntax <code>variable.name</code> where each variable or sub object is a JSON object.");
		dataContent.add(
				"The main classes for data transport are <code>JavaScriptPart</code> for <code>addDto(name,object)</code>, and <code>JsonVariable</code> for <code>AjaxResponse.getDataVariables()</code>.");
		
		dataContent.add("You register a variable for creation (although they do get auto-created) with the <code>addDto(\"variable.name\", object)</code>. " +
				                "This will initialize/override this variable with the <a href=\"https://www.baeldung.com/jackson\" target=\"_blank\">Jackson JSON</a> representation of this class.");
		addSourceToContainer(EventsPage.class, "registerevent.txt", Java, dataContent);
		
		dataContent.add(
				"You return variable data in by registering the variable in events that occur. " +
						"<br/>You can return as many variables as you want, and you can register as many events to any component as you want." +
						"<br/>You can split data between as many pages and variables as you like, or populate single variables from multiple sources.");
		
		addSourceToContainer(EventsPage.class, "returneventdata.txt", Java, dataContent);
		
		dataContent.add("When the event is fired and the appropriate class is called, A list of all variables in the call are presented to you. " +
				                "This may include variables that have been automatically added through custom modules. LocalStorage Authentication an example of this.");
		
		
		add(all);
		
		add(buildSPI());
	}
	
	@Override
	public PageTitle pageTitle()
	{
		return new PageTitle("Basics - Events ", new Breadcrumbs().addCrumb(new BreadcrumbItem("JWebMP"))
		                                                          .addCrumb(new BreadcrumbItem("Basics"))
		                                                          .addCrumb(new BreadcrumbItem("Events", true))
		);
	}
	
	private DivSimple<?> buildSPI()
	{
		DivSimple<?> div = new DivSimple<>().addClass(Col_12);
		div.add(new H3<>("Registering New Events"));
		div.add("Service Providers are used to register, control and provide the page scoped information to the given items. The below providers support the creation of events.");
		DefaultTable<?> table = new DefaultTable<>();
		table.addHeader("Service Loader", "Purpose");
		table.addRow("IEventConfigurator", "Modifies events during serialization, and allows automated control from components to client events");
		table.addRow("IAngularDirective", "Adds the given Angular Directive to the page rendered to the client");
		table.addRow("IAngularControllerScopeStatement", "Adds script text inside the root angular controller with direct access to the scope");
		table.addRow("IAngularModule", "Adds the given angular module to the initialization of angular");
		table.addRow("IAngularConfigurationScopeStatement", "Adds the given scoped statement to the angular configuration script");
		table.addRow("IAngularConfiguration", "Registers a new angular configuration object to be rendered.");
		table.addRow("IAngularController", "Registers the new controller to the Angular script");
		table.addRow("IAngularFactory", "Adds the given factory to the script");
		div.add(table);
		return div;
	}
	
}
