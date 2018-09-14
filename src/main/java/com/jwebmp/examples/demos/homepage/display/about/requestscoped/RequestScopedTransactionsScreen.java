package com.jwebmp.examples.demos.homepage.display.about.requestscoped;

import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;

import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class RequestScopedTransactionsScreen
		extends DisplayPart<RequestScopedTransactionsScreen>
{
	public RequestScopedTransactionsScreen()
	{
		BSCardBody all = new BSCardBody();

		all.addClass("row bg-dark");
		all.addStyle("display:grid;overflow-y:auto;");

		all.add("Scoping Transactions has been made incredibly simple, Typically there are 3 standard configurations, but implementing your own through the ITransactionHandler is pretty straight forward");
		all.add("Persist Services are started up automatically from the Guice Persist library.");

		all.add("Session-Per-Transaction Strategy where an Entity Manager exists for each started Unit Transaction or Unit of Work is implemented by default for backend operations whether or not it is annotated. " +
		        "<br/>These are marked with @com.jwebmp.guicedpersistence.db.annotations.Transactional and designate the specific Entity Manager to use. " +
		        "<br/>The default @Transactional are supported, or if the custom @Transactional is not specified, it will utilize which ever persistence module was loaded inside your JPMS module. " +
		        "<br/>Doing it this way is a little less multi-jpa friendly (strictly one persistence unit per JPMS module) but may be precisely what you are looking for, CQRC by default.");

		addSourceToContainer(RequestScopedTransactionsScreen.class, "sessionscope.txt", Java, all);

		all.add("Request scoped transactions and Units of Work greatly increase performance when used with Shared Transaction Connections and huge vo1umes of concurrent users");
		all.add("In web environments session-per-transaction is atypical, and generally a session-per-http-request is preferred (sometimes also called open-session-in-view)." +
		        "<br/>To enable this strategy, simply include the module GuicedServletsRequestScoping with your application and the filters will be automatically registered.");
		all.add("For Standalone,Backend, Web Service, or &quot;EJB&quot; modules utilizing DAO encapsulation, Session Per Transaction is Preferred. For Web, RESTful, or &quot;WAR&quot; like modules, use Open in View.");

		all.add("You can also map the same persistence unit to both forms of transaction management in separate modules, ensuring backend and frontend operate accordingly through the JPMS layering. ");
		all.add("This site is configured this way, All event logging, item storing and with Beans Validation 2.0 - Database actions occur asynchronously. All request scoped in a open-view strategy, and all async processes on a session-per-transaction strategy utilizing the same persistence connection.");

		all.add("You can selectively disable open in view when using the module as demonstrated below");

		addSourceToContainer(RequestScopedTransactionsScreen.class, "requestscoped.txt", Java, all);

		all.add("<a target=\"_blank\" href=\"https://github.com/google/guice/wiki/JPA\">About Guice-Persist and Strategies</a>");

		add(all);
	}
}
