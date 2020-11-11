package com.jwebmp.examples.demos.homepage.display.about.persistencehandling;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScrollFeature;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.display.about.requestscoped.JPAModuleScreen;
import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;

import jakarta.validation.constraints.NotNull;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;

public class PersistenceJPAScreen extends DisplayScreen
{
	public PersistenceJPAScreen()
	{

	}

	@Override
	public @NotNull BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(Container_Fluid);
		Div pageContentRow = new BSRow();

		DefaultSlimScrollFeature scroll = new DefaultSlimScrollFeature(pageContentRow);
		scroll.getOptions()
		      .setHeight("500px");
		container.add(buildJpaPersistence());
		return container;
	}

	private Div buildJpaPersistence()
	{
		Div persistence = new Div();
		persistence.add(GuiceContext.get(JPAModuleScreen.class));
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
