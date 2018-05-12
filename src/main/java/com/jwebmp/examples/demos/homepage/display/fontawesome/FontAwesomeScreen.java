package com.jwebmp.examples.demos.homepage.display.fontawesome;

import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.Link;
import com.jwebmp.base.html.Paragraph;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.plugins.fontawesome.FontAwesome;
import com.jwebmp.plugins.fontawesome.FontAwesomeIcons;
import com.jwebmp.plugins.fontawesome.FontAwesomeProperties;
import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.Link;
import com.jwebmp.base.html.Paragraph;
import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.fontawesome.FontAwesome;
import com.jwebmp.plugins.fontawesome.FontAwesomeIcons;
import com.jwebmp.plugins.fontawesome.FontAwesomeProperties;

public class FontAwesomeScreen
		extends DisplayScreen
{

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(BSContainerOptions.Container);
		BSRow row = new BSRow();
		SourceCodeContentPanel panel = new SourceCodeContentPanel("Font Awesome 4.7 Icons", DisplayCodeParts.FontAwesome, null);
		panel.getContent()
		     .add(row);
		panel.setShowHeader(true);

		container.add(panel);

		for (FontAwesomeIcons fontAwesomeIcon : FontAwesomeIcons.values())
		{
			DivSimple d = new DivSimple();
			d.addClass("col-3 text-center");
			d.add(new FontAwesome(fontAwesomeIcon, FontAwesomeProperties.$2x));
			d.add(new Paragraph<>(fontAwesomeIcon.toString()));
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
		                                             .setText("Font Awesome 4"));
		return crumbs;
	}
}
