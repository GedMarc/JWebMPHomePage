package com.jwebmp.examples.demos.homepage.display.injection;

import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;

import jakarta.validation.constraints.NotNull;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;

@SuppressWarnings("Duplicates")
public class InjectionsBasicsScreen
		extends DisplayScreen
{
	public InjectionsBasicsScreen()
	{

	}

	@Override
	public @NotNull BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(Container_Fluid);
		container.add(new InjectionsBasicsPart());
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
