package com.jwebmp.examples.demos.homepage.display.servlets;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.DefaultTable;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;

import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class WebSocketDisplayPart
		extends DisplayPart<WebSocketDisplayPart>
{
	public WebSocketDisplayPart()
	{
		this(false);
	}

	public WebSocketDisplayPart(boolean scrolly)
	{
		BSCardBody<?> body = getDefaultBody();
		Div all = new Div();
		DefaultSlimScroll scroll = null;
		if (scrolly)
		{
			scroll = new DefaultSlimScroll();
		}
		Div dualExecution = new Div();

		dualExecution.add(new H3<>("Easiest Web Sockets... Ever"));
		dualExecution.add("Web Sockets in JWebMP are Multi-Module controlled for simplicity in implementation, and of course JPMS SPI." +
		                  "<br/>This also allows the security layer to overlap the web socket mechanism for Data and Site calls.");

		dualExecution.add("Web Sockets are implemented using IWebSocketService SPI. The following services are made available");

		DefaultTable table = new DefaultTable();
		table.addHeader("Service Loader", "Purpose");
		table.addRow("IWebSocketAuthDataProvider", "Provides authentication mechanism specific for Web Sockets");
		table.addRow("IWebSocketPreConfiguration", "Provides configuration for the Web Sockets");
		table.addRow("IWebSocketSessionProvider", "Provides a link for Sessions and Web Sockets to identify and ensure security");
		table.addRow("IWebSocketService", "The class to implement to enable your web socket");

		dualExecution.add(table);

		dualExecution.add("Web Sockets messages should be split by broadcast groups. " +
		                  "<br/>It is best to have a single Web Socket per application, and divide messages up by String based naming. You should? annotate your adapter with @Singleton." +
		                  "<br/> This is using <code>Broadcast Groups</code> instead of <code>Channels</code>." +
		                  "<br/>It is up to you of course.");

		dualExecution.add("A default broadcast would look like <code>JWebMPSocket.broadcastMessage(\"Broadcast Group\",new AjaxResponse());</code>");

		addSourceToContainer(WebSocketDisplayPart.class, "websockets_adapter.txt", Java, dualExecution);

		dualExecution.add("The following auth providers are made available in the web socket library. No libraries are enabled by default." +
		                  "<br/> To use a provider, enable it in the config <code>WebSocketsConfiguration.setLocalStorageEnabled(true);</code> in an IGuicePreStartup");

		DefaultTable authTable = new DefaultTable();
		authTable.addHeader("Service Loader", "Purpose");
		authTable.addRow("LocalStorageKeyWSAuth", "Provides the local storage key for the device in web socket messages for verification");
		authTable.addRow("GeoBytesDataWSAuth", "Provides the geo information for the connection using http://gd.geobytes.com/GetCityDetails.");

		dualExecution.add(authTable);

		if (scrolly)
		{
			scroll.add(dualExecution);
			all.add(scroll);
		}
		else
		{
			all.add(dualExecution);
		}
		body.add(all);
		add(body);
	}
}
