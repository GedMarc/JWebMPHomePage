package com.jwebmp.examples.demos.homepage.display.privacy;

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.core.base.html.Paragraph;
import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;

public class PrivacyScreen
		extends DisplayScreen<PrivacyScreen>
{

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer block = new BSContainer<>().addClass("col-md-12")
		                                       .addClass("ml-3")
		                                       .addClass("mr-3");
		Paragraph p = new Paragraph();
		p.setTextOnly(true);
		StringBuilder sb = FileTemplates.getFileTemplate(PrivacyScreen.class, "privacy", "PrivacyPolicy.html");
		p.setText(sb.toString());
		block.add(p);
		return block;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Privacy"));
		return crumbs;
	}
}
