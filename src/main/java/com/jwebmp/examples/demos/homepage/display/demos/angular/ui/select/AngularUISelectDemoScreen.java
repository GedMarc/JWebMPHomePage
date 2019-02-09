package com.jwebmp.examples.demos.homepage.display.demos.angular.ui.select;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.angularuiselect.AngularUISelectPageConfigurator;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class AngularUISelectDemoScreen
		extends DemoScreen
{
	public AngularUISelectDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angularfileupload.angular", AngularUISelectPageConfigurator.class,
		                          "Angular UI Select", true, true));
		add(buildGoToSource(AngularUISelectPageConfigurator.class, AngularUISelectDemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);


		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
