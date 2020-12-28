package com.jwebmp.examples.apps.homepage.pages.welcome;

import com.jwebmp.examples.apps.homepage.*;
import com.jwebmp.examples.apps.homepage.components.SwapScreen;
import com.jwebmp.plugins.themes.mintontheme.faq.FAQCardLayout;
import com.jwebmp.plugins.themes.mintontheme.faq.FAQPageLayout;
import com.jwebmp.plugins.themes.mintontheme.pages.PageTitle;
import com.jwebmp.plugins.themes.mintontheme.partials.BreadcrumbItem;
import com.jwebmp.plugins.themes.mintontheme.partials.Breadcrumbs;
import com.jwebmp.examples.apps.homepage.pages.viewers.SourceCodeHolder;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonPrimary;

import static com.jwebmp.plugins.fontawesome5.FontAwesome.icon;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeBrandIcons.github;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons.*;
import static com.jwebmp.plugins.fontawesome5.options.FontAwesomeSizes.$3x;
import static com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles.Duotone;

public class WelcomePage extends SwapScreen<WelcomePage>
{
	public WelcomePage()
	{
		add(new FAQPageLayout().addHeaderIcon(icon(toolbox, Duotone).addClass($3x))
		                       .addHeading("JWebMP Framework")
		                       .addHeadingWriteUp("JWebMP is a Web UI Framework designed for ease of use, re-usability on a scale never seen before, " +
				                                          " and is simplistic as possible in design and use")
		                       .addHeadingWriteUp(" Design your applications the same way you would design a Swing application")
		                       .addDirectionButton(icon(github), new BSButtonPrimary<>().setText("Contribute"), null)
		                       .addDirectionButton(icon(paint_brush_alt, Duotone), new BSButtonPrimary<>().setText("Pre-Built Layouts"), null)
		);
		add(new FAQCardLayout()
				    .addSection(icon(user_secret, Duotone), "About", new WelcomePageAboutSection(), true)
				    .addSection(icon(tools, Duotone), "Maven", new WelcomePageMavenSection(), false)
				    .addSection(icon(plug, Duotone), "All Plugins", new PluginsList(), false)
				    .addSection(icon(network_wired, Duotone), "History", new DemoComingSoon(), false)
		);
		add(new FAQPageLayout().addHeaderIcon(icon(code_merge, $3x, Duotone))
		                       .addHeading("Unrestricted Development")
		                       .addHeadingWriteUp("JWebMP allows the creation of programmatic components that can be " +
				                                          "<br/> reused, rendered, altered and managed in any way")
		);


		add(new FAQCardLayout()
				    .addSection(icon(code, Duotone), "Page", new SourceCodeHolder(HomePage.class), true)
				
				    .addSection(icon(scroll, Duotone), "Body", new SourceCodeHolder(DemoBody.class), false)
				    .addSection(icon(align_left, Duotone), "Menu", new SourceCodeHolder(DemoMenu.class), false)
				    .addSection(icon(align_left, Duotone), "User Box", new SourceCodeHolder(DemoUserBox.class), false)
				    .addSection(icon(arrow_to_top, Duotone), "Top Bar", new SourceCodeHolder(DemoTopBar.class), false)
				    .addSection(icon(align_right, Duotone), "Right Bar", new SourceCodeHolder(DemoRightSideBar.class), false)
				    .addSection(icon(home_heart, Duotone), "Welcome Page", new SourceCodeHolder(WelcomePage.class), false)
				    .addSection(icon(receipt, Duotone), "All Plugins", new SourceCodeHolder(PluginsList.class), false)
		);
	}
	
	@Override
	public PageTitle pageTitle()
	{
		return new PageTitle("Welcome to JWebMP", new Breadcrumbs().addCrumb(new BreadcrumbItem("JWebMP"))
		                                                           .addCrumb(new BreadcrumbItem("Home", true)));
	}
}
