package com.jwebmp.examples.demos.homepage.display;

import com.jwebmp.base.html.Body;
import com.jwebmp.base.html.Div;
import com.jwebmp.base.html.Script;
import com.jwebmp.base.html.interfaces.GlobalChildren;
import com.jwebmp.base.references.CSSReference;
import com.jwebmp.base.references.JavascriptReference;
import com.jwebmp.base.servlets.enumarations.RequirementsPriority;
import com.jwebmp.components.pace.PaceLoaderPageConfigurator;
import com.jwebmp.components.pace.PaceThemeColour;
import com.jwebmp.components.pace.preloadedthemes.PaceTheme;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import za.co.mmagon.guiceinjection.GuiceContext;

/**
 * @author Marc Magon
 * @since 30 Jul 2017
 */
public class DisplayBody
		extends Body
{

	private static final long serialVersionUID = 1L;

	private Div<GlobalChildren, ?, ?, ?, ?> wrapper;
	private OuterLayout outerLayout;

	/*
	 * Constructs a new Display Body
	 */
	public DisplayBody()
	{
		//addFeature(new Layout(this));

		PaceLoaderPageConfigurator.setPaceTheme(PaceTheme.CornerIndicator.setThemeColour(PaceThemeColour.Blue));

		addClass("fixed-left");

		add(new Script<>().setJavascript("var resizefunc = [];"));

		addCssReference(new CSSReference("2", 1.0, "css/icons.css"));
		addCssReference(new CSSReference("1", 1.0, "css/style.css"));
		addCssReference(new CSSReference("4", 1.0, "plugins/switchery/switchery.min.css"));
		addCssReference(new CSSReference("3", 1.0, "plugins/jquery-circliful/css/jquery.circliful.css"));
		addCssReference(new CSSReference("a", 1.0, "themeoverrides.css"));


		addJavaScriptReference(new JavascriptReference("1", 1.0, "js/detect.js", 160));
		addJavaScriptReference(new JavascriptReference("1a", 1.0, "js/fastclick.js", 160));
		//addJavaScriptReference(new JavascriptReference("2", 1.0, "js/jquery.slimscroll.js", 161));
		addJavaScriptReference(new JavascriptReference("2a", 1.0, "js/jquery.blockUI.js", 161));
		addJavaScriptReference(new JavascriptReference("3", 1.0, "js/waves.js", 162));
		addJavaScriptReference(new JavascriptReference("4", 1.0, "js/wow.min.js", 163));
		addJavaScriptReference(new JavascriptReference("5", 1.0, "js/jquery.nicescroll.js", 164));
		addJavaScriptReference(new JavascriptReference("6", 1.0, "js/jquery.scrollTo.min.js", 165));
		addJavaScriptReference(new JavascriptReference("7", 1.0, "plugins/switchery/switchery.min.js", 166));

		addJavaScriptReference(new JavascriptReference("8", 1.0, "plugins/waypoints/lib/jquery.waypoints.min.js", 167));
		addJavaScriptReference(new JavascriptReference("9", 1.0, "plugins/counterup/jquery.counterup.min.js", 168));
		addJavaScriptReference(new JavascriptReference("a", 1.0, "plugins/jquery-circliful/js/jquery.circliful.min.js", 169));
		addJavaScriptReference(new JavascriptReference("b", 1.0, "plugins/jquery-sparkline/jquery.sparkline.min.js", 170));
		addJavaScriptReference(new JavascriptReference("c", 1.0, "plugins/countdown/dest/jquery.countdown.min.js", 171));
		addJavaScriptReference(new JavascriptReference("d", 1.0, "plugins/simple-text-rotator/jquery.simple-text-rotator.min.js", 172));


		addJavaScriptReference(new JavascriptReference("y", 1.0, "js/jquery.core.js", 171));
		addJavaScriptReference(new JavascriptReference("z", 1.0, "js/jquery.app.js", 172));
		//wrapper = new Div<>().setID("wrapper");

		addClass(BSColumnOptions.H_100);
		addClass(BSColumnOptions.W_100);

		outerLayout = GuiceContext.getInstance(OuterLayout.class);
		add(outerLayout);

		addJavaScriptReference(new JavascriptReference("e", 1.0, "js/modernizr.min.js").setPriority(RequirementsPriority.Top_Shelf));
	}
}
