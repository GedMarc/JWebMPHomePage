package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.components.general.MintonCheckBox;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.FontAwesomeList;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;

import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.*;

public class ReasonsWhyPart
		extends DisplayPart

{
	public ReasonsWhyPart()
	{
		FontAwesomeList list = new FontAwesomeList();
		list.addItem("Pure Java Development", new FontAwesome<>().setIcon(FontAwesomeIcons.check));
		list.addItem("Non-Invasive<br/>Servlets JSP JSF continue as normal", new FontAwesome<>().setIcon(FontAwesomeIcons.check));
		list.addItem("Standalone Embedded Container EE7 EE8 MicroProfile", new FontAwesome<>().setIcon(FontAwesomeIcons.check));

		addCardBody().add(list);
	}

	private Div buildCheckbox(String label, boolean checked, String clazz)
	{
		return (Div) new MintonCheckBox(checked, label, clazz).addClass("col-12");
	}
}
