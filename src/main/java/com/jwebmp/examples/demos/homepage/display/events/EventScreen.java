package com.jwebmp.examples.demos.homepage.display.events;

import com.google.inject.Singleton;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.html.*;
import com.jwebmp.core.events.activate.ActivateAdapter;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.display.DefaultDisplayWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.general.ObjectBrowser;
import com.jwebmp.examples.demos.homepage.components.general.OptionsBrowser;
import com.jwebmp.examples.demos.homepage.components.general.PackagesBrowser;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.FontAwesomeList;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

import javax.validation.constraints.NotNull;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class EventScreen
		extends DisplayScreen
{
	public EventScreen()
	{

	}

	@Override
	public @NotNull BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(Container_Fluid);
		//container.addClass("row");

		container.add(new H3<>("Event Management<br/>"));

		Div aboutContent = new Div();


		aboutContent.add("Events are driven through AngularJS and are added to components through the <code>addEvent()</code> method.<br/><br/>");
		addSourceToContainer(EventScreen.class, "event_example.txt", Java, aboutContent);
		aboutContent.add(
				"Event Classes are used on the server to perform the relevant function. <br/>" +
				"<br/>Event Classes can/should be stored in their own files to be used in a Domain Events and Domain Event Store manner, " +
				" This will allow events to be portable.<br/>" +
				"<code>AjaxCall</code> and <code>AjaxResponse</code> are request scoped objects that manage all WebSocket and Ajax calls. Calls are incoming, Responses are outgoing<br/>" +
				"This means that you can use the same event classes for web socket server side push, and ajax client driven events.");

		Div whatAvailableContainer = new Div();
		Div rowContainer = new BSRow();
		whatAvailableContainer.add(new H3<>("The Call and Response objects"));
		whatAvailableContainer.add("Every call has default data associated with it, for the Call (Client to Server), and Response (Server to Client). " +
		                           "<br/>For WebSockets, Only the AjaxResponse needs to be used.");
		whatAvailableContainer.add(rowContainer);

		rowContainer.add(new OptionsBrowser(new AjaxCall<>()).addClass("col-md-6 col-12"));
		rowContainer.add(new OptionsBrowser(new AjaxResponse<>()).addClass("col-md-6 col-12"));

		whatAvailableContainer.add("<br/>Events themselves are components, and can be persisted in JSON format using the <code>toString()</code> method.<br/>" +
		                           "A component can contain any number of events to be fired at any point.");

		whatAvailableContainer.add(
				"The default events available are located in <code>com.jwebmp.core.events</code> and are generically typed to limit events to certain objects,<br/>" +
				"Plugins may also specify their own events in the same manner");


		Div pageContent = new Div();
		Div pageContentRow = new BSRow();
		DefaultSlimScroll scroll = new DefaultSlimScroll(pageContentRow);
		scroll.getOptions()
		      .setHeight("500px");

		pageContent.add(new H3<>("Below you can find all the events in the core of JWebMP."));
		pageContent.add("Events are restricted through type safety in each of the components." +
		                //   "<br/>Each tag denotes the following available for that component/event" +
		                "");

		FontAwesomeList cafej = new FontAwesomeList();
		cafej.addItem("<C> - Children", FontAwesome.icon(pencil_alt));
		cafej.addItem("<A> - Attributes", FontAwesome.icon(pencil_alt));
		cafej.addItem("<F> - Features", FontAwesome.icon(pencil_alt));
		cafej.addItem("<E> - Events", FontAwesome.icon(pencil_alt));
		cafej.addItem("<J> - Java Type (CRP)", FontAwesome.icon(pencil_alt));
		//pageContent.add(cafej);
		pageContent.add("Navigate through the objects in the packages below, to view the object to the right.");
		pageContent.add(pageContentRow);

		pageContentRow.add(new PackagesBrowser("com.jwebmp.core.events").addClass("col-12 col-md-6"));
		pageContentRow.add(new ObjectBrowser(ActivateAdapter.class).addClass("col-12 col-md-6"));


		Div dataContent = new Div();
		dataContent.add(new H3<>("Moving Data between Client and Server"));
		dataContent.add("Data is stored in angular variables on the client, and are registered in any component." +
		                "<br/>Client Variables are named in dot syntax <code>variable.name</code> where each variable or sub object is a JSON object.");
		dataContent.add("The main classes for data transport are <code>JavaScriptPart</code> for <code>getOptions()</code>, and <code>AngularDataVariables</code> for returning variables.");

		dataContent.add("You register a variable for creation (although they do get auto-created) with the <code>addDto(\"variable.name\", object)</code>. " +
		                "This will initialize/override this variable with the <a href=\"https://www.baeldung.com/jackson\" target=\"_blank\">Jackson JSON</a> representation of this class.");
		addSourceToContainer(EventScreen.class, "registerevent.txt", Java, dataContent);
		dataContent.add(
				"You return variable data in by registering the variable in events that occur. " +
				"<br/>You can return as many variables as you want, and you can register as many events to any component as you want." +
				"<br/>You can split data between as many pages and variables as you like, or populate single variables from multiple sources.");
		addSourceToContainer(EventScreen.class, "returneventdata.txt", Java, dataContent);

		dataContent.add("When the event is fired and the appropriate class is called, A list of all variables in the call are presented to you. " +
		                "This may include variables that have been automatically added through custom modules. LocalStorage Authentication an example of this.");

		//dataContent.add(new Image("images/ajaxcallresponsewithparameters.png"));

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
