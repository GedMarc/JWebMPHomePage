package com.jwebmp.examples.demos.homepage.display.menu;

import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.html.*;
import com.jwebmp.base.html.interfaces.GlobalChildren;
import com.jwebmp.base.html.interfaces.events.GlobalEvents;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayScreens;
import com.jwebmp.generics.LeftOrRight;
import com.jwebmp.htmlbuilder.css.colours.ColourCSSImpl;
import com.jwebmp.plugins.angularslimscroll.SlimScrollFeature;
import com.jwebmp.plugins.bootstrap4.accordion.BSAccordion;
import com.jwebmp.plugins.bootstrap4.collapse.BSCollapse;

import static com.jwebmp.examples.demos.homepage.enumerations.DisplayScreens.*;

/**
 * @author Marc Magon
 * @since 31 Jul 2017
 */
public class West
		extends Div
{

	private static final long serialVersionUID = 1L;

	private Div<GlobalChildren, ?, ?, ?, ?> sidebarInner;
	private DivSimple<?> sidebarMenu;
	private List globalList;

	private BSAccordion<?> accordion = new BSAccordion<>();

	/*
	 * Constructs a new West
	 */
	public West()
	{
		setID("west");
		addClass("left side-menu");
		SlimScrollFeature scrollFeature = new SlimScrollFeature(this);
		scrollFeature.getOptions()
		             .setHeight("100%")
		             .setAlwaysVisible(true)
		             .setPosition(LeftOrRight.Right)
		             .setSize(5)
		             .setColor(new ColourCSSImpl("#98a6ad"))
		             .setWheelStep(5);

		sidebarInner = new Div<>();
		sidebarInner.addClass("sidebar-inner slimscrollleft");

		sidebarMenu = new DivSimple<>();
		sidebarMenu.setID("sidebar-menu");

		globalList = new List<>();

		ListItem homeItem;
		globalList.add(homeItem = buildMenuItem("ti-home", "Home"));
		homeItem.addEvent(new ChangeScreenEvent(homeItem, "p=HomePageScreen").setID(DisplayScreens.HomePageScreen.toString()));

		buildMenuSection("Main", false, homeItem, buildAboutJWebMPDropDown(), buildGettingStarted(), buildTestingFramework());
		buildMenuSection("Quick Starts", false, homeItem, buildMyFirstSites(), buildUIKits(), buildInstantSites());
		buildMenuSection("UI Kits", false, homeItem, buildMyFirstSites(), buildUIKits(), buildInstantSites());

		buildMenuSection("Plugin Library", true, homeItem, buildCorePlugins(), buildUtilityPlugins(), buildIconSets(), buildBrowsers(), buildTablesTrees(), buildForms(),
		                 buildGraphing(), buildMapping());


		sidebarInner.add(sidebarMenu);

		sidebarMenu.add(globalList);
		sidebarMenu.add(new DivSimple<>().addClass("clearfix"));
		sidebarInner.add(new DivSimple<>().addClass("clearfix"));

		add(sidebarInner);
	}


	private void buildMenuSection(String title, boolean hideOnStart, ComponentHierarchyBase... content)
	{
		ListItem li = new ListItem<>().addClass("menu-title")
		                              .setText(title);
		globalList.add(li);
		for (ComponentHierarchyBase componentHierarchyBase : content)
		{
			globalList.add(componentHierarchyBase);
		}
	}

	private ListItem<?> buildMenuItem(String icon, String title)
	{
		ListItem item = new ListItem();
		Link<?> link = new Link();
		link.addClass("waves-effect waves-primary");
		if (icon != null)
		{
			Italic<?> italic = new Italic<>();
			italic.addClass(icon);
			link.add(italic);
		}
		Span text = new Span(title);
		link.add(text);

		item.add(link);
		return item;
	}

	private ListItem<?> buildAboutJWebMPDropDown()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#a", AboutJWebMP).setText("About JWebMP")));
		uiKit.add(new ListItem<>().add(buildListItem("#a", UnderTheHood).setText("Under The Hood")));
		uiKit.add(new ListItem<>().add(buildListItem("#asite", AboutThisSIte).setText("About This Site")));
	/*	uiKit.add(new ListItem<>().add(buildListItem("#b").setText("ReadMe F A Q")));
		uiKit.add(new ListItem<>().add(buildListItem("#f").setText("Events & Event Stores")));
		uiKit.add(new ListItem<>().add(buildListItem("#c").setText("Security")));
		uiKit.add(new ListItem<>().add(buildListItem("#d").setText("Migrating Applications")));

		uiKit.add(new ListItem<>().add(buildListItem("#a1").setText("CSS Integration")));
*/
		ListItem dropDown1 = buildSubList("ti-paint-bucket", "JWebMP", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildGettingStarted()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#i").setText("MicroProfile")));
		uiKit.add(new ListItem<>().add(buildListItem("#h").setText("Enterprise Edition")));
		uiKit.add(new ListItem<>().add(buildListItem("#j").setText("Embedding Containers")));
		uiKit.add(new ListItem<>().add(buildListItem("#j").setText("Ouput Control")));
		/**/

		//uiKit.add(new ListItem<>().add(buildListItem("#m").setText("Unit Testing")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Environments", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildTestingFramework()
	{
		List uiKit = new List<>();

		uiKit.add(new ListItem<>().add(buildListItem("#k").setText("Integration Testing")));
		uiKit.add(new ListItem<>().add(buildListItem("#l").setText("Unit Testing")));
		uiKit.add(new ListItem<>().add(buildListItem("#l").setText("Open Support")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Testing", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildMyFirstSites()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#n").setText("Hello World")));
		uiKit.add(new ListItem<>().add(buildListItem("#o").setText("Layouts")));
		uiKit.add(new ListItem<>().add(buildListItem("#p").setText("Basic Examples")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "First Examples", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildUIKits()
	{
		List uiKit = new List<>();


		uiKit.add(new ListItem<>().add(buildListItem("#t", DisplayScreens.JQueryUI).setText("JQuery UI")));
		uiKit.add(new ListItem<>().add(buildListItem("#u", DisplayScreens.Bootstrap4).setText("Bootstrap")));
		uiKit.add(new ListItem<>().add(buildListItem("#v").setText("JQX Widgets")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "UI Bundles", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildInstantSites()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#w").setText("SB2 Admin <small>(BS3 core)</small>")));
		uiKit.add(new ListItem<>().add(buildListItem("#x").setText("Blur Admin <small>(BS3 core)</small>")));
		uiKit.add(new ListItem<>().add(buildListItem("#y").setText("Minton <small>(BS4 core)</small>")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Instant Sites", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildCorePlugins()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#a2").setText("HTML Tag Collection")));
		uiKit.add(new ListItem<>().add(buildListItem("#a3").setText("Angular Integration")));
		uiKit.add(new ListItem<>().add(buildListItem("#a4").setText("AJAX Implementation")));
		uiKit.add(new ListItem<>().add(buildListItem("#a5").setText("Atmosphere Push Web Sockets")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Core Plugins", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildUtilityPlugins()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#c2").setText("Video Conferencing <small>(6bit)</small>")));
		uiKit.add(new ListItem<>().add(buildListItem("#c3").setText("Angular Utilities")));
		uiKit.add(new ListItem<>().add(buildListItem("#c4").setText("Bootstrap Dialog <small>(3&4)</small>")));
		uiKit.add(new ListItem<>().add(buildListItem("#e2").setText("Easing Animations")));
		uiKit.add(new ListItem<>().add(buildListItem("#e3").setText("Toastr Notifications")));
		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Utility Plugins", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildIconSets()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#c1", DisplayScreens.FontAwesome).setText("Font Awesome")));
		uiKit.add(new ListItem<>().add(buildListItem("#e4", DisplayScreens.Glyphicons).setText("Glyphicons")));
		uiKit.add(new ListItem<>().add(buildListItem("#e5").setText("Ion Icons")));
		uiKit.add(new ListItem<>().add(buildListItem("#e5").setText("Skycons")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Icon Sets", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildBrowsers()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#b5").setText("Fast Click")));
		uiKit.add(new ListItem<>().add(buildListItem("#b6").setText("Globalize")));
		uiKit.add(new ListItem<>().add(buildListItem("#b7").setText("Modernizr")));
		uiKit.add(new ListItem<>().add(buildListItem("#b8").setText("Pace Loader")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Browser Addons", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildTablesTrees()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#b5").setText("Data Tables")));
		uiKit.add(new ListItem<>().add(buildListItem("#b6", DisplayScreens.JSTree).setText("JS Tree")));
		uiKit.add(new ListItem<>().add(buildListItem("#b7").setText("X Editable")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Tables / Trees", uiKit);
		return dropDown1;
	}

	private Link<?> buildListItem(String uniqueHashBangId)
	{
		return buildListItem(uniqueHashBangId, DisplayScreens.ComingSoon);
	}

	private ListItem<?> buildSubList(String icon, String title, List menuItemList)
	{
		ListItem<?> item = new ListItem<>();
		item.addClass("has_sub");
		Link<?> dropdownLink = new Link<>("javascript:void(0);");
		dropdownLink.addClass("waves-effect waves-primary");

		Italic iconItalic = new Italic();
		iconItalic.addClass(icon);
		Span span = new Span(title);

		dropdownLink.add(iconItalic);
		dropdownLink.add(span);
		dropdownLink.add(new Span<>().addClass("menu-arrow pull-right"));

		item.add(dropdownLink);

		menuItemList.addClass("list-unstyled");
		item.add(menuItemList);

		BSCollapse.link(dropdownLink, menuItemList, true);

		return item;
	}

	private Link<?> buildListItem(String uniqueHashBangId, DisplayScreens screen)
	{
		Link link = new Link(uniqueHashBangId);
		changeScreenAdapter(screen, link);
		return link;
	}

	private ChangeScreenEvent changeScreenAdapter(DisplayScreens screenReference, ComponentHierarchyBase<?, ?, ?, GlobalEvents, ?> comp)
	{
		ChangeScreenEvent adapter = new ChangeScreenEvent(comp, "p=" + screenReference.toString());
		adapter.setID(screenReference.toString());
		comp.addEvent(adapter);
		return adapter;
	}

	private ListItem<?> buildForms()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#b9").setText("Angular Forms")));
		uiKit.add(new ListItem<>().add(buildListItem("#e6").setText("BS Forms <small>(3&4)</small>")));
		uiKit.add(new ListItem<>().add(buildListItem("#e7").setText("Quick Forms")));
		uiKit.add(new ListItem<>().add(buildListItem("#e8").setText("NG File Upload")));
		uiKit.add(new ListItem<>().add(buildListItem("#e9").setText("BlueImp File Upload")));
		uiKit.add(new ListItem<>().add(buildListItem("#f1").setText("Ion Slider")));
		uiKit.add(new ListItem<>().add(buildListItem("#f2").setText("Pretty Checkboxes")));
		uiKit.add(new ListItem<>().add(buildListItem("#f3").setText("Angular Progress Buttons")));
		uiKit.add(new ListItem<>().add(buildListItem("#f4").setText("Angular UI Select")));
		uiKit.add(new ListItem<>().add(buildListItem("#f5").setText("Bootstrap Switch")));
		uiKit.add(new ListItem<>().add(buildListItem("#f6").setText("Bootstrap Select")));
		uiKit.add(new ListItem<>().add(buildListItem("#f7").setText("Bootstrap Tags Input")));
		uiKit.add(new ListItem<>().add(buildListItem("#f8").setText("Bootstrap Date Time Picker")));
		uiKit.add(new ListItem<>().add(buildListItem("#f9").setText("Bootstrap Nya Select")));
		uiKit.add(new ListItem<>().add(buildListItem("#g1").setText("Smart Wizard")));
		uiKit.add(new ListItem<>().add(buildListItem("#g2").setText("Text Input Effects")));

		uiKit.add(new ListItem<>().add(buildListItem("#g3").setText("Quick Forms")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Form Plugins", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildGraphing()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#g4", DisplayScreens.JQPlot).setText("JQPlot")));
		uiKit.add(new ListItem<>().add(buildListItem("#g5").setText("D3 Graphing")));
		uiKit.add(new ListItem<>().add(buildListItem("#g6").setText("C3 Graphing")));
		uiKit.add(new ListItem<>().add(buildListItem("#g7").setText("NVD3 Graphing")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Graph Plugins", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildMapping()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#g8").setText("LeafletJS")));
		uiKit.add(new ListItem<>().add(buildListItem("#g9").setText("Google Maps")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Mapping Plugins", uiKit);
		return dropDown1;
	}


}
