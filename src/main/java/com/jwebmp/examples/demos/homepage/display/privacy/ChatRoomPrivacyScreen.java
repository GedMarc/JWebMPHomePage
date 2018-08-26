package com.jwebmp.examples.demos.homepage.display.privacy;

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.core.base.html.Paragraph;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;

public class ChatRoomPrivacyScreen
		extends DisplayScreen<ChatRoomPrivacyScreen>
{

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer block = new BSContainer<>().addClass("col-md-12");
		block.removeClass("container-fluid");
		Paragraph p = new Paragraph();
		p.setTextOnly(true);
		StringBuilder sb = FileTemplates.getFileTemplate(ChatRoomPrivacyScreen.class, "chatroomprivacy", "chatroomagreement.html");
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
		                                             .setText("Chat Room Privacy"));
		return crumbs;
	}
}
