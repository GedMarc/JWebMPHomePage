package com.jwebmp.examples.demos.homepage.display.menu;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.html.*;
import com.jwebmp.core.base.html.interfaces.events.GlobalEvents;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScrollFeature;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayScreens;
import com.jwebmp.plugins.bootstrap4.accordion.BSAccordion;
import com.jwebmp.plugins.bootstrap4.collapse.BSCollapse;

import static com.jwebmp.core.utilities.StaticStrings.*;
import static com.jwebmp.examples.demos.homepage.enumerations.DisplayScreens.*;

/**
 * @author GedMarc
 * @since 31 Jul 2017
 */
public class West
		extends Div
{
	private Div<IComponentHierarchyBase, ?, ?, ?, ?> sidebarInner;
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

		new DefaultSlimScrollFeature(this);

		sidebarInner = new Div<>();
		sidebarInner.addClass("sidebar-inner");

		sidebarMenu = new DivSimple<>();
		sidebarMenu.setID("sidebar-menu");

		globalList = new List<>();

		ListItem homeItem;
		ListItem quickStartItem;
		ListItem pluginsListing;

		globalList.add(homeItem = buildMenuItem("fal fa-hotel fa-2x fa-fw", HTML_TAB + "Home"));

		globalList.add(quickStartItem = buildMenuItem("fal fa-rabbit-fast fa-3x", HTML_TAB + "Quick Start"));


		globalList.add(buildBasicsMenu());
		buildAddonsMenu();

		globalList.add(pluginsListing = buildMenuItem("fal fa-steering-wheel fa-2x", HTML_TAB + "Full Plugins List"));


		homeItem.addEvent(new ChangeScreenEvent(homeItem, "p=HomePageScreen").setID(DisplayScreens.HomePageScreen.toString()));
		quickStartItem.addEvent(new ChangeScreenEvent(quickStartItem, "p=QuickStart").setID(DisplayScreens.QuickStartScreen.toString()));

		pluginsListing.addEvent(new ChangeScreenEvent(pluginsListing, "p=PluginsListScreen").setID(DisplayScreens.PluginsListScreen.toString()));

		buildMenuSection("MIX N MATCH", true, homeItem, buildCorePlugins(), buildAngularTools(), buildBootstrap4(),
		                 buildJQueryUI(), buildJQXWidgets());

		buildMenuSection("COMPONENTS", true, buildDisplayComponents(), buildForms(), buildGraphing(), buildMapping(), buildTablesTrees());
		buildMenuSection("ICONS", true, buildIconSets());


		globalList.add(buildMoreMenu());


		sidebarInner.add(sidebarMenu);

		sidebarMenu.add(globalList);
		sidebarMenu.add(new DivSimple<>().addClass("clearfix"));
		sidebarInner.add(new DivSimple<>().addClass("clearfix"));

		add(sidebarInner);
	}


	private ListItem<?> buildMoreMenu()
	{
		List uiKit = new List<>();

		uiKit.add(new ListItem<>().add(buildListItem("#a2", TCs).setText(HTML_TAB + "T&amp;C's"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", Privacy).setText(HTML_TAB + "Privacy"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", ChatPolicy).setText(HTML_TAB + "Chat Policy"))
		                          .setRenderTextBeforeChildren(false));

		ListItem dropDown1 = buildSubList("fal fa-question", "More", uiKit);
		return dropDown1;
	}

	private void buildAddonsMenu()
	{
		buildMenuSection("UTILITY MODULES", true
				, buildAddonsInjection()
				, buildAddonsPersistence()
				, buildAddonsServlets()
				, buildAddonsCaching());
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

	private ListItem buildAddonsInjection()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#a2", InjectionBasicsScreen).setText("Basics"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", InjectionClassGraphScreen).setText("ClassGraph"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", InjectionJWebMPScreen).setText("JWebMP"))
		                          .setRenderTextBeforeChildren(false));
		ListItem dropDown1 = buildSubList("fal fa-question", "Injection", uiKit);
		return dropDown1;
	}

	private ListItem buildAddonsPersistence()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#a2", PersistenceBasicsScreen).setText(HTML_TAB + "Basics"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", PersistenceJpaScreen).setText(HTML_TAB + "JPA"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", PersistenceJtaScreen).setText(HTML_TAB + "JTA"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", PersistenceC3P0Screen).setText(HTML_TAB + "C3P0"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", PersistenceEntityAssistScreen).setText(HTML_TAB + "Entity Assist"))
		                          .setRenderTextBeforeChildren(false));

		ListItem dropDown1 = buildSubList("fal fa-question", "Persistence", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildBasicsMenu()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#a2", AboutJWebMP).setText(HTML_TAB + "About"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", EventsScreen).setText(HTML_TAB + "Events"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", FormsScreen).setText(HTML_TAB + "Forms"))
		                          .setRenderTextBeforeChildren(false));

		ListItem dropDown1 = buildSubList("fal fa-abacus", "Basics", uiKit);
		return dropDown1;
	}

	private ListItem buildAddonsServlets()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#a2", ServletsBasicsScreen).setText(HTML_TAB + "Basics"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", ServletsWebSocketsScreen).setText(HTML_TAB + "Web Sockets"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", ServletsJSFScreen).setText(HTML_TAB + "JSF"))
		                          .setRenderTextBeforeChildren(false));
		/*uiKit.add(new ListItem<>().add(buildListItem("#a2", PersistenceJpaScreen).setText(HTML_TAB + "Request Scoping JTA"))
		                          .setRenderTextBeforeChildren(false));*/
/*		uiKit.add(new ListItem<>().add(buildListItem("#a2", ServletsUADetectScreen).setText(HTML_TAB + "UA Detector"))
		                          .setRenderTextBeforeChildren(false));*/
		uiKit.add(new ListItem<>().add(buildListItem("#a2", ServletsRestScreen).setText(HTML_TAB + "Rest"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", ServletsWebServicesScreen).setText(HTML_TAB + "Web Services"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", ServletsSwaggerScreen).setText(HTML_TAB + "Swagger"))
		                          .setRenderTextBeforeChildren(false));
		/*uiKit.add(new ListItem<>().add(buildListItem("#a2", PersistenceEntityAssistScreen).setText(HTML_TAB + "Metrics API"))
		                          .setRenderTextBeforeChildren(false));*/
		ListItem dropDown1 = buildSubList("fal fa-question", "Servlets", uiKit);
		return dropDown1;
	}

	private ListItem buildAddonsCaching()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#a2", CachingHazelcastScreen).setText(HTML_TAB + "HazelCast"))
		                          .setRenderTextBeforeChildren(false));
		uiKit.add(new ListItem<>().add(buildListItem("#a2", CachingEhCacheScreen).setText(HTML_TAB + "EhCache"))
		                          .setRenderTextBeforeChildren(false));

		ListItem dropDown1 = buildSubList("fal fa-question", "Caching", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildMenuItem(String icon, String title)
	{
		ListItem item = new ListItem();
		item.addStyle("margin", "0px");
		Link<?> link = new Link();
		link.addStyle("margin", "0px");
		link.addStyle("line-height", "1");
		link.addClass("waves-effect waves-light waves-primary");
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

	private ListItem<?> buildCorePlugins()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#a2", HtmlTagsDemoScreen).setText("HTML Tags")));
		uiKit.add(new ListItem<>().add(buildListItem("#a3", BasicInputDemoScreen).setText("HTML Inputs")));
		ListItem dropDown1 = buildSubList("fal fa-plug", "Core", uiKit);
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

	private Link<?> buildListItem(String uniqueHashBangId, DisplayScreens screen)
	{
		Link link = new Link(uniqueHashBangId);
		changeScreenAdapter(screen, link);
		return link;
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
		dropdownLink.add(new Italic<>().addClass("fas fa-angle-double-down menu-arrow pull-right")
		                               .addStyle("margin-right:0px;"));

		//dropdownLink.addFeature(new MenuIconSwapOnClick(dropdownLink, FontAwesomeIcons.angle_double_right, FontAwesomeIcons.angle_double_down));

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
		//uiKit.add(new ListItem<>().add(buildListItem("#dsa26", WebLogAppender).setText("Web Logger")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa26", SourceCodePrettifier).setText("Source Prettify")));
		uiKit.add(new ListItem<>().add(buildListItem("#dsa26", DisplayDynamicSourceCodeViewer).setText("Dynamic Source Switcher")));

		ListItem dropDown1 = buildSubList("fal fa-wrench", "Display", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildAngularTools()
	{
		List uiKit = new List<>();

		uiKit.add(new ListItem<>().add(buildListItem("#ui411", AngularAnimate).setText("Animate")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui421", AngularAnimatedChange).setText("Animated Change")));
		//uiKit.add(new ListItem<>().add(buildListItem("#ui431", AngularAutoExpand).setText("Auto Expand")));
		uiKit.add(new ListItem<>().add(buildListItem("#e8", AngularFileUpload).setText("Angular File Upload")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui441", AngularIonSlider).setText("Angular Ion Slider")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui451", AngularNgSlimScroll).setText("Angular Slim Scroll")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui461", AngularSanitize).setText("Angular Sanitize")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui371", AngularScrollPosition).setText("Scroll Position")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui371", AngularTouch).setText("Touch")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui371", AngularTrackWidth).setText("Track Width")));
		uiKit.add(new ListItem<>().add(buildListItem("#ui371", AngularZoomIn).setText("Zoom In")));
		ListItem dropDown1 = buildSubList("fal fa-toolbox", "Angular Tools", uiKit);

		return dropDown1;
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

		ListItem dropDown1 = buildSubList("fal fa-feather-alt", "Bootstrap 4", uiKit);
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

		ListItem dropDown1 = buildSubList("fal fa-vector-square", "JQuery UI", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildJQXWidgets()
	{
		List uiKit = new List<>();

		uiKit.add(new ListItem<>().add(buildListItem("#ui41", JQXWidgets).setText("JQXWidgets")));
		ListItem dropDown1 = buildSubList("fal fa-vector-square", "<i>JQXWidgets*</i>", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildForms()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#b9").setText("Angular Forms")));
		uiKit.add(new ListItem<>().add(buildListItem("#e9", AngularFileUpload).setText("BlueImp File Upload")));
		uiKit.add(new ListItem<>().add(buildListItem("#f1", IonRangeSlider).setText("Ion Range Slider")));
		uiKit.add(new ListItem<>().add(buildListItem("#f2", PrettyCheckboxes).setText("Pretty Checkboxes")));
		uiKit.add(new ListItem<>().add(buildListItem("#f3", ProgressButtons).setText("Angular Progress Buttons")));
		uiKit.add(new ListItem<>().add(buildListItem("#g1", SmartWizard).setText("Smart Wizard")));
		uiKit.add(new ListItem<>().add(buildListItem("#g2", TextInputEffects).setText("Text Input Effects")));
		uiKit.add(new ListItem<>().add(buildListItem("#e7", QuickForms).setText("Quick Forms")));

		ListItem dropDown1 = buildSubList("fal fa-compass", "Forms", uiKit);
		return dropDown1;
	}

	private Link<?> buildListItem(String uniqueHashBangId)
	{
		return buildListItem(uniqueHashBangId, DisplayScreens.ComingSoon);
	}

	private ListItem<?> buildMapping()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#g8", LeafletJS).setText("LeafletJS")));
		uiKit.add(new ListItem<>().add(buildListItem("#g9", GoogleMaps).setText("Google Maps")));

		ListItem dropDown1 = buildSubList("fal fa-map-marker-plus", "Mapping", uiKit);
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
		//uiKit.add(new ListItem<>().add(buildListItem("#gd4", ImageHeatMap).setText("Image Heat Map")));
	//	uiKit.add(new ListItem<>().add(buildListItem("#ge4", ImageHeatMap).setText("Particles Display")));

		ListItem dropDown1 = buildSubList("fal fa-chart-line", "Graphing", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildIconSets()
	{
		List uiKit = new List<>();
		//uiKit.add(new ListItem<>().add(buildListItem("#c1", DisplayScreens.FontAwesome).setText("Font Awesome")));
		uiKit.add(new ListItem<>().add(buildListItem("#c153", DisplayScreens.FontAwesome5).setText("Font Awesome 5")));
		uiKit.add(new ListItem<>().add(buildListItem("#e4", DisplayScreens.Glyphicons).setText("Glyphicons")));
		uiKit.add(new ListItem<>().add(buildListItem("#e5", DisplayScreens.IonIcons).setText("Ion Icons")));
		uiKit.add(new ListItem<>().add(buildListItem("#e52", DisplayScreens.MDI3).setText("Material Design Icons")));
		uiKit.add(new ListItem<>().add(buildListItem("#e53", DisplayScreens.MDI2).setText("MDI 2.485")));
		uiKit.add(new ListItem<>().add(buildListItem("#e5").setText("Skycons")));
		uiKit.add(new ListItem<>().add(buildListItem("#e5").setText("Weather Icons")));
		uiKit.add(new ListItem<>().add(buildListItem("#e5").setText("Themify Icons")));

		ListItem dropDown1 = buildSubList("fal fa-low-vision", "Icons", uiKit);
		return dropDown1;
	}

	private ListItem<?> buildTablesTrees()
	{
		List uiKit = new List<>();
		uiKit.add(new ListItem<>().add(buildListItem("#b5").setText("Data Tables")));
		uiKit.add(new ListItem<>().add(buildListItem("#b6", DisplayScreens.JSTree).setText("JS Tree")));
		uiKit.add(new ListItem<>().add(buildListItem("#b7").setText("X Editable")));

		ListItem dropDown1 = buildSubList("fal fa-table ", "Tables / Trees", uiKit);
		return dropDown1;
	}

}
