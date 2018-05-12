package com.jwebmp.examples.demos.homepage.display.contactus;

import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.DivSimple;

public class ContactUsScreen extends DivSimple
{
	public ContactUsScreen()
	{
		setID("content");
		addStyle("overflow-y:auto;overflow-x:hidden;");
		addStyle("overflow-x:hidden !important;");
		addStyle("height:100%;");

		add("Contact Us Here!");
	}
}
