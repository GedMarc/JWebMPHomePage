package com.jwebmp.examples.demos.homepage.display.demos.tables.xeditable;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.xeditable.XEditablePageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class XEditablesDemoScreen
		extends DemoScreen
{
	public XEditablesDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.xeditable", XEditablePageConfigurator.class,
		                          "XEditable", true, true));
		add(buildGoToSource(XEditablesDemoScreen.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		add(more);
	}
}
