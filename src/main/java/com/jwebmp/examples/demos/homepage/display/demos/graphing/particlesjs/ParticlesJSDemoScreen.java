package com.jwebmp.examples.demos.homepage.display.demos.graphing.particlesjs;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.particlejs.ParticleJSPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class ParticlesJSDemoScreen
		extends DemoScreen
{
	public ParticlesJSDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.particlejs", ParticleJSPageConfigurator.class,
		                          "Particles", true, true));
		add(buildGoToSource(ParticleJSPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
