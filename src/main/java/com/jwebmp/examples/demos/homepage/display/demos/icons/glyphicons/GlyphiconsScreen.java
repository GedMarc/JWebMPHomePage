package com.jwebmp.examples.demos.homepage.display.demos.icons.glyphicons;

import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.Italic;
import com.jwebmp.base.html.Link;
import com.jwebmp.base.html.Paragraph;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.glyphicons.Glyphicons;

public class GlyphiconsScreen
		extends PluginDemoScreen
{

	public GlyphiconsScreen()
	{
		super("Glyphicons", "Icon Sets", "Glyphicons", "Bootstrap 3 Set");
	}

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(BSContainerOptions.Container);
		BSRow row = new BSRow();
		SourceCodeContentPanel panel = new SourceCodeContentPanel("Glyphicons Example", DisplayCodeParts.Glyphicons, null);
		panel.getContent()
		     .add(row);
		panel.setShowHeader(true);

		container.add(panel);

		for (Glyphicons glyphicon : Glyphicons.values())
		{
			DivSimple d = new DivSimple();
			d.addClass("col-3 text-center");
			d.add(new Italic<>().addClass(glyphicon.toString() + " fa-2x"));
			d.add(new Paragraph<>(glyphicon.toString()));
			row.add(d);
		}
		return container;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Icon Sets"));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Glyphicons"));
		return crumbs;
	}


}
