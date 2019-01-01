package com.jwebmp.examples.demos.homepage.display.servlets;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;

import javax.validation.constraints.NotNull;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;

public class ServletsBasicMappedScreen
		extends DisplayScreen
{
	public ServletsBasicMappedScreen()
	{

	}

	@Override
	public @NotNull BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(Container_Fluid);
		DefaultSlimScroll scroll = new DefaultSlimScroll();
		scroll.add(buildScreen());
		container.add(scroll);
		return container;
	}

	private Div buildScreen()
	{
		Div persistence = new Div();
		persistence.add(GuiceContext.get(ServletsBasicScreen.class));
		return persistence;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Servlets"));
		return crumbs;
	}

}
