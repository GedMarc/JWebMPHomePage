package com.jwebmp.examples.apps.homepage;

import com.jwebmp.examples.apps.homepage.components.SwapContainer;
import com.jwebmp.plugins.themes.mintontheme.leftsidebar.LeftSideBarMenu;
import com.jwebmp.plugins.themes.mintontheme.leftsidebar.sidebar.SideBarDropDownItem;
import com.jwebmp.plugins.themes.mintontheme.leftsidebar.sidebar.SideBarHeaderItem;
import com.jwebmp.examples.apps.homepage.events.SwapScreenEvent;
import com.jwebmp.examples.apps.homepage.pages.helloworld.*;
import com.jwebmp.examples.apps.homepage.pages.welcome.AboutJWebMPPage;
import com.jwebmp.plugins.bootstrap4.badge.BSBadge;

import static com.jwebmp.plugins.bootstrap4.badge.BSBadgeOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSDefaultOptions.Float_Right;
import static com.jwebmp.plugins.fontawesome5.FontAwesome.icon;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeBrandIcons.*;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons.*;
import static com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles.Duotone;

public class DemoMenu extends LeftSideBarMenu
{
	public DemoMenu()
	{
		//can render before initialization, allowing lazy loading...
	}
	
	@Override
	public void init()
	{
		if (!isInitialized())
		{
			getMenuList()
					.add(new SideBarHeaderItem("Basics"))
					.add(new SideBarDropDownItem("Hello World", icon(th, Duotone),
					                             new BSBadge<>("5", true, Badge_Success).addClass(Float_Right))
							     .addItem("Maven Structure", new SwapScreenEvent(new SwapContainer(new MavenBasicsPage()), null))
							     .addItem("Get Started", new SwapScreenEvent(new SwapContainer(new GetStartedPage()), null))
							     .addItem("Components", new SwapScreenEvent(new SwapContainer(new ComponentsPage()), null))
							     .addItem("HTML", new SwapScreenEvent(new SwapContainer(new GeneratingHTMLPage()), null))
							     .addItem("Events", new SwapScreenEvent(new SwapContainer(new EventsPage()), null))
							     .addItem("Forms", new SwapScreenEvent(new SwapContainer(new FormsPage()), null))
							     .addItem("About", new SwapScreenEvent(new SwapContainer(new AboutJWebMPPage()), null))
					)
					/*.add(new SideBarDropDownItem("My First App", icon(browser, Duotone),
					                             new BSBadge<>("6", true, Badge_Success).addClass(Float_Right))
							     .addItem("Guiced EE", null)
							     .addItem("DB Connections", null)
							     .addItem("Servlet Mappings", null)
							     .addItem("Push Events", null)
							     .addItem("Rest Services", null)
							     .addItem("JCache Impl", null)
					)*/
					.add(new SideBarDropDownItem("Core Components",
					                             icon(atom_alt, Duotone),
					                             new BSBadge<>("3", true, Badge_Info)
							                             .addClass(Float_Right)
					     ).addItem("All Core Tags", null)
					      .addItem("HTML Inputs", null)
					      .addItem("Data Binding", null)
					)
					.add(new SideBarHeaderItem("Frameworks"))
					.add(new SideBarDropDownItem("JQuery UI",
					                             icon(layer_plus, Duotone),
					                             new BSBadge<>("7", true, Badge_Dark)
							                             .addClass(Float_Right)
					     ).addItem("Core Widgets", null)
					      .addItem("DateTime Picker", null)
					      .addItem("Border Layout", null)
					      .addItem("Colour Picker", null)
					      .addItem("Theme Roller", null)
					      .addItem("Vertical Timeline", null)
					      .addItem("Quick Forms", null)
					)
					.add(new SideBarDropDownItem("Bootstrap",
					                             icon(bootstrap, Duotone),
					                             new BSBadge<>("7", true, Badge_Dark)
							                             .addClass(Float_Right)
					     ).addItem("BS Core", null)
					      .addItem("DateTime Picker", null)
					      .addItem("Dialog", null)
					      .addItem("NyaSelect", null)
					      .addItem("Tags", null)
					      .addItem("Bootswatch", null)
					      .addItem("Quick Forms", null)
					
					)
					.add(new SideBarHeaderItem("Plugins"))
					.add(new SideBarDropDownItem("Display",
					                             icon(ruler_combined, Duotone),
					                             new BSBadge<>("13", true, Badge_Warning)
							                             .addClass(Float_Right)
					     ).addItem("Easing Animations", null)
					      .addItem("Pace Loader", null)
					      .addItem("Toastr", null)
					      .addItem("Fast Click", null)
					      .addItem("Full Calendar", null)
					      .addItem("Globalize", null)
					      .addItem("Metro Tiles", null)
					      .addItem("Modernizr", null)
					      .addItem("Moment JS", null)
					      .addItem("Plus As Tab", null)
					      .addItem("Radial Carousel", null)
					      .addItem("Soft History", null)
					      .addItem("Source Code Prettify", null)
					)
					.add(new SideBarDropDownItem("Angular Tools",
					                             icon(angular, Duotone),
					                             new BSBadge<>("9", true, Badge_Warning)
							                             .addClass(Float_Right)
					     ).addItem("Animate", null)
					      .addItem("File Upload", null)
					      .addItem("Ion Slider", null)
					      .addItem("Slim Scroll", null)
					      .addItem("Sanitize", null)
					      .addItem("Scroll Position", null)
					      .addItem("Touch", null)
					      .addItem("Track Width", null)
					      .addItem("Zoom In", null)
					)
					.add(new SideBarDropDownItem("Form Addons",
					                             icon(keyboard, Duotone),
					                             new BSBadge<>("8", true, Badge_Warning)
							                             .addClass(Float_Right)
					     ).addItem("Angular Messages", null)
					      .addItem("BlueImp File upload", null)
					      .addItem("Ion Range Slider", null)
					      .addItem("Pretty Checkboxes", null)
					      .addItem("Progress Buttons", null)
					      .addItem("Smart Wizard", null)
					      .addItem("Text Input Effects", null)
					      .addItem("Quick Forms", null)
					)
					.add(new SideBarDropDownItem("Maps",
					                             icon(map, Duotone),
					                             new BSBadge<>("2", true, Badge_Danger)
							                             .addClass(Float_Right)
					     ).addItem("Leaflet JS", null)
					      .addItem("Google Maps", null)
					)
					.add(new SideBarDropDownItem("Graphing",
					                             icon(chart_pie_alt, Duotone),
					                             new BSBadge<>("5", true, Badge_Success)
							                             .addClass(Float_Right)
					     ).addItem("C3", null)
					      .addItem("D3", null)
					      .addItem("NVD3", null)
					      .addItem("JQ Plot", null)
					      .addItem("Easy Pie Chart", null)
					)
					.add(new SideBarDropDownItem("Icon Sets",
					                             icon(fort_awesome, Duotone),
					                             new BSBadge<>("8", true, Badge_Success)
							                             .addClass(Float_Right)
					     ).addItem("Font Awesome", null)
					      .addItem("Glyphicons", null)
					      .addItem("Ion Icons", null)
					      .addItem("Material Design Icons", null)
					      .addItem("MDI", null)
					      .addItem("Skycons", null)
					      .addItem("Weather Icons", null)
					      .addItem("Themify Icons", null)
					)
					.add(new SideBarDropDownItem("Tables / Trees",
					                             icon(table, Duotone),
					                             new BSBadge<>("3", true, Badge_Success)
							                             .addClass(Float_Right)
					     ).addItem("Data Tables", null)
					      .addItem("JS Tree", null)
					      .addItem("X Editable", null)
					)
					.add(new SideBarHeaderItem("Examples"))
					.add(new SideBarDropDownItem("Dashboards",
					                             icon(table, Duotone),
					                             new BSBadge<>("3", true, Badge_Success)
							                             .addClass(Float_Right)
					     ).addItem("Sales", null)
					      .addItem("CRM", null)
					      .addItem("Analytics", null)
					)
					.add(new SideBarDropDownItem("Screens",
					                             icon(table, Duotone),
					                             new BSBadge<>("8", true, Badge_Success)
							                             .addClass(Float_Right)
					     ).addItem("Chat", null)
					      .addItem("Calendar", null)
					      .addItem("File Manager", null)
					      .addItem("Tickets", null)
					      .addItem("Inbox", null)
					      .addItem("Feed", null)
					      .addItem("Kanban", null)
					      .addItem("Tasks List", null)
					)
					.add(new SideBarDropDownItem("Ecommerce",
					                             icon(table, Duotone),
					                             new BSBadge<>("8", true, Badge_Success)
							                             .addClass(Float_Right)
					     ).addItem("Products List", null)
					      .addItem("Products Grid", null)
					      .addItem("Product Detail", null)
					      .addItem("Create Product", null)
					      .addItem("Customers", null)
					      .addItem("Orders", null)
					      .addItem("Order Detail", null)
					      .addItem("Sellers", null)
					      .addItem("Shopping Cart", null)
					      .addItem("Checkout", null)
					)
			;
		}
		super.init();
	}
}
