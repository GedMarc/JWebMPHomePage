package com.jwebmp.examples.demos.homepage.display.demos.icons.fontawesome5;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.fontawesome5.config.FontAwesome5PageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class FontAwesome5DemoScreen
		extends DemoScreen
{

	public FontAwesome5DemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.fontawesome5", FontAwesome5PageConfigurator.class,
		                          "Font Awesome 5", true, true));
		add(buildGoToSource(FontAwesome5PageConfigurator.class, FontAwesome5DemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);


		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		add(more);

		Div details = new Div<>().addClass(Col_12);
		details.add(
				"Icons may display as missing - this highlights the differences between Free and Pro.<br/> To disable showing the icon entirely use the FontAwesome5PageConfigurator to configure accordingly.");
		details.add("Adding your Pro license is as simple as FontAwesome5PageConfigurator.setRootReferenceDir(\"bower_components/font...\");");
		more.add(details);
	}
}
