package com.jwebmp.examples.demos.homepage.display.servlets;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.display.PluginModulePart;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;

import jakarta.validation.constraints.NotNull;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;

public class WebSocketDisplayScreen
		extends DisplayScreen<WebSocketDisplayScreen>
{

	@Override
	public @NotNull BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(Container_Fluid);

		container.add(new PluginModulePart("JWebMP WebSockets"));
		container.add(buildScreen());
		container.add(buildGoToSource(WebSocketDisplayPart.class));
		return container;
	}

	private Div buildScreen()
	{
		BSColumn persistence = new BSColumn(Col_12);
		persistence.add(new WebSocketDisplayPart());
		return persistence;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Web Sockets"));
		return crumbs;
	}
}
