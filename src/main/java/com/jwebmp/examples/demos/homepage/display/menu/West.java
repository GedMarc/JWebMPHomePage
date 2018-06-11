package com.jwebmp.examples.demos.homepage.display.menu;

import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.html.*;
import com.jwebmp.base.html.interfaces.GlobalChildren;
import com.jwebmp.base.html.interfaces.events.GlobalEvents;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayScreens;
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
		SlimScrollFeature scrollFeature = new DefaultSlimScroll(this);

		sidebarInner = new Div<>();
		sidebarInner.addClass("sidebar-inner slimscrollleft");

		sidebarMenu = new DivSimple<>();
		sidebarMenu.setID("sidebar-menu");

		globalList = new List<>();

		ListItem homeItem;
		globalList.add(homeItem = buildMenuItem("ti-home", "Home"));
		homeItem.addEvent(new ChangeScreenEvent(homeItem, "p=HomePageScreen").setID(DisplayScreens.HomePageScreen.toString()));

		buildMenuSection("Main", false, homeItem, buildAboutJWebMPDropDown(), buildGettingStarted(), buildTestingFramework());
		buildMenuSection("Quick Starts", false, homeItem, buildMyFirstSites());
		//buildMenuSection("UI Kits", false, homeItem, buildMyFirstSites(), buildUIKits(), buildInstantSites());

		buildMenuSection("Plugin Library", true, homeItem, buildCorePlugins(), buildAngularTools());

		buildMenuSection("Bootstrap", true, buildBootstrap4(), buildBootstrap3());
		buildMenuSection("JQuery UI", true, buildJQueryUI());
		buildMenuSection("Components", true, buildDisplayComponents(), buildForms(), buildGraphing(), buildMapping(), buildTablesTrees());
		buildMenuSection("Icon Sets", true, buildIconSets());


		sidebarInner.add(sidebarMenu);

		sidebarMenu.add(globalList);
		sidebarMenu.add(new DivSimple<>().addClass("clearfix"));
		sidebarInner.add(new DivSimple<>().addClass("clearfix"));

		add(sidebarInner);
	}

	private ListItem<?> buildBootstrap4()
	{
		List uiKit = new List<>();

		uiKit.add(new ListItem<>().add(buildListItem("#bs41", Bootstrap4).setText("Bootstrap 4")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs42", Bootstrap4DateTimePicker).setText("DateTime Picker")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs43", Bootstrap4Dialog).setText("Dialog")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs44", Bootstrap4NyaSelect).setText("Nya Select")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs45", Bootstrap4QuickForms).setText("Quick Forms")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs46", Bootstrap4Switch).setText("Switch")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs37", Bootstrap3TagsInput).setText("Tags Input")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs48", Bootstrap4Swatch).setText("BootSwatch Themes")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Bootstrap 4", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildBootstrap3()
	{
		List uiKit = new List<>();

		uiKit.add(new ListItem<>().add(buildListItem("#bs31", Bootstrap3).setText("Bootstrap 3")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs32", Bootstrap3DateTimePicker).setText("DateTime Picker")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs33", Bootstrap3Dialog).setText("Dialog")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs33", Bootstrap3Sb2AdminTheme).setText("Sb2 Admin")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs34", Bootstrap3NyaSelect).setText("Nya Select")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs35", Bootstrap3QuickForms).setText("Quick Forms")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs36", Bootstrap3Switch).setText("Switch")));
		uiKit.add(new ListItem<>().add(buildListItem("#bs37", Bootstrap3TagsInput).setText("Tags Input")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Bootstrap 3", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildDisplayComponents()
	{
		List uiKit = new List<>();

		uiKit.add(new ListItem<>().add(buildListItem("#dsa11", EasingEffects).setText("Easing Animations")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa12", FastClick).setText("Fast Click")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa13", FullCalendar).setText("Full Calendar")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa14", Globalize).setText("Globalize")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa15", MetroJS).setText("Metro Tiles")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa16", Modernizr).setText("Modernizr")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa17", Moment).setText("Moment Dates")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa18", Pace).setText("Pace Loader")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa21", PlusAsTab).setText("Plus as Tab")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa22", RadialSliderGem).setText("Radial Carousel")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa23", SoftHistoryChange).setText("History Controls")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa24", Toastr).setText("Toastr Notifications")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa25", VerticalTimelineGem).setText("Vertical Timeline Gem")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa26", WebLogAppender).setText("Web Logger")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa26", SourceCodePrettifier).setText("Source Prettify")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa26", DisplayDynamicSourceCodeViewer).setText("Dynamic Source Switcher")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Display Tools", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildJQueryUI()
	{
		List uiKit = new List<>();

		uiKit.add(new ListItem<>().add(buildListItem("#ui41", JQueryUI).setText("JQueryUI")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui42", JQueryUIDateTimePicker).setText("DateTime Picker")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui43", JQueryUILayout).setText("Border Layout")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui44", SpectrumColorPicker).setText("Spectrum Colour Picker")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui45", JQueryUIThemes).setText("ThemeRoller Themes")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui46", JQueryUIThemesNestable).setText("Nested Theme Roller")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui37", JQueryUIVerticalTimeline).setText("Vertical Timeline")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "JQuery UI", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildAngularTools()
	{
		List uiKit = new List<>();

		uiKit.add(new ListItem<>().add(buildListItem("#ui411", AngularAnimate).setText("Animate")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui421", AngularAnimatedChange).setText("Animated Change")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui431", AngularAutoExpand).setText("Auto Expand")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui441", AngularIonSlider).setText("Ion Slider")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui451", AngularNgSlimScroll).setText("NG Slim Scroll")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui461", AngularSanitize).setText("Sanitize")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui371", AngularScrollPosition).setText("Scroll Position")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui371", AngularTouch).setText("Touch")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui371", AngularTrackWidth).setText("Track Width")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui371", AngularZoomIn).setText("Zoom In")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Angular Tools", uiKit);
		return dropDown1;
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
		uiKit.add(new ListItem<>().add(buildListItem("#j").setText("Output Control")));
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
		uiKit.add(new ListItem<>().add(buildListItem("#n", HelloWorldScreen).setText("Hello World")));
		uiKit.add(new ListItem<>().add(buildListItem("#o", JQueryUILayout).setText("Layouts")));
		uiKit.add(new ListItem<>().add(buildListItem("#p").setText("Basic Examples")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Getting Started", uiKit);
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

	private Link<?> buildListItem(String uniqueHashBangId, DisplayScreens screen)
	{
		Link link = new Link(uniqueHashBangId);
		changeScreenAdapter(screen, link);
		return link;
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

		menuItemList.addAttribute("data-parent", "#west");

		return item;
	}

	private ChangeScreenEvent changeScreenAdapter(DisplayScreens screenReference, ComponentHierarchyBase<?, ?, ?, GlobalEvents, ?> comp)
	{
		ChangeScreenEvent adapter = new ChangeScreenEvent(comp, "p=" + screenReference.toString());
		adapter.setID(screenReference.toString());
		comp.addEvent(adapter);
		return adapter;
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

	private ListItem<?> buildForms()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#b9").setText("Angular Forms")));
		uiKit.add(new ListItem<>().add(buildListItem("#e9", AngularFileUpload).setText("BlueImp File Upload")));
		uiKit.add(new ListItem<>().add(buildListItem("#e8", AngularFileUpload).setText("NG File Upload")));
		uiKit.add(new ListItem<>().add(buildListItem("#f1", IonRangeSlider).setText("Ion Range Slider")));
		uiKit.add(new ListItem<>().add(buildListItem("#f2", PrettyCheckboxes).setText("Pretty Checkboxes")));
		uiKit.add(new ListItem<>().add(buildListItem("#f3", ProgressButtons).setText("Angular Progress Buttons")));
		uiKit.add(new ListItem<>().add(buildListItem("#g1", SmartWizard).setText("Smart Wizard")));
		uiKit.add(new ListItem<>().add(buildListItem("#g2", TextInputEffects).setText("Text Input Effects")));
		uiKit.add(new ListItem<>().add(buildListItem("#e7", QuickForms).setText("Quick Forms")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Form Plugins", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildGraphing()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#g5", D3).setText("D3 Graphing")));
		uiKit.add(new ListItem<>().add(buildListItem("#g6", C3).setText("C3 Graphing")));
		uiKit.add(new ListItem<>().add(buildListItem("#g7", NVD3).setText("NVD3 Graphing")));
		uiKit.add(new ListItem<>().add(buildListItem("#ga4", JQPlot).setText("JQPlot")));
		uiKit.add(new ListItem<>().add(buildListItem("#gb4", EasyPieChart).setText("Easy Pie Chart")));
		uiKit.add(new ListItem<>().add(buildListItem("#gc4", GradientsLinear).setText("Linear Gradients")));
		uiKit.add(new ListItem<>().add(buildListItem("#gd4", ImageHeatMap).setText("Image Heat Map")));
		uiKit.add(new ListItem<>().add(buildListItem("#ge4", ImageHeatMap).setText("Particles Display")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Graphing", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildMapping()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#g8", LeafletJS).setText("LeafletJS")));
		uiKit.add(new ListItem<>().add(buildListItem("#g9", GoogleMaps).setText("Google Maps")));

		ListItem dropDown1 = buildSubList("ti-paint-bucket", "Mapping Plugins", uiKit);
		return dropDown1;
	}


}
