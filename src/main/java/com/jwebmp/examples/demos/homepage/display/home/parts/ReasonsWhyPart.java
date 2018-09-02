package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.components.general.MintonCheckBox;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.FontAwesomeList;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;

public class ReasonsWhyPart
		extends DisplayPart

{
	public ReasonsWhyPart()
	{
		FontAwesomeList list = new FontAwesomeList();
		list.addItem("Pure Java Development", new FontAwesome<>().setIcon(FontAwesomeIcons.check));
		list.addItem("Non-Invasive<br/>EJB Servlets JSP JSF continue as normal", new FontAwesome<>().setIcon(FontAwesomeIcons.check));
		list.addItem("Standalone / Container / EE / MicroProfile", new FontAwesome<>().setIcon(FontAwesomeIcons.check));

		addCardBody().add(list);
	}

	private Div buildCheckbox(String label, boolean checked, String clazz)
	{
		return (Div) new MintonCheckBox(checked, label, clazz).addClass("col-12");
	}
}
