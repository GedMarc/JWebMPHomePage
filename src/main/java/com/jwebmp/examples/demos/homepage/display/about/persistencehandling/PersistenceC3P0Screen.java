package com.jwebmp.examples.demos.homepage.display.about.persistencehandling;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.display.about.c3p0module.C3P0Screen;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;

import javax.validation.constraints.NotNull;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;

public class PersistenceC3P0Screen
		extends DisplayScreen
{
	public PersistenceC3P0Screen()
	{

	}

	@Override
	public @NotNull BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(Container_Fluid);
		Div pageContentRow = new BSRow();

		DefaultSlimScroll scroll = new DefaultSlimScroll(pageContentRow);
		scroll.getOptions()
		      .setHeight("500px");
		container.add(buildc3p0());
		return container;
	}

	private Div buildc3p0()
	{
		Div persistence = new Div();
		persistence.add(GuiceContext.get(C3P0Screen.class));
		return persistence;
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
