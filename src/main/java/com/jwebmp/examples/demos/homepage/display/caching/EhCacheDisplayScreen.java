package com.jwebmp.examples.demos.homepage.display.caching;

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

public class EhCacheDisplayScreen
		extends DisplayScreen<EhCacheDisplayScreen>
{

	@Override
	public @NotNull BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(Container_Fluid);
		container.add(new PluginModulePart("Guiced Persistence - EhCache"));
		container.add(buildScreen());
		container.add(buildGoToSource(getClass()));
		return container;
	}

	private Div buildScreen()
	{
		BSColumn persistence = new BSColumn(Col_12);
		persistence.add(new EhCacheDisplayPart());
		return persistence;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("EH Cache"));
		return crumbs;
	}
}
