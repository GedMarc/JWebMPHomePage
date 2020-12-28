package com.jwebmp.examples.apps.homepage.pages.helloworld;

import com.jwebmp.core.Component;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.H4;
import com.jwebmp.core.base.interfaces.ICssStructure;
import com.jwebmp.examples.apps.homepage.components.browse.ObjectBrowser;
import com.jwebmp.examples.apps.homepage.components.SwapScreen;
import com.jwebmp.plugins.themes.mintontheme.pages.PageTitle;
import com.jwebmp.plugins.themes.mintontheme.partials.BreadcrumbItem;
import com.jwebmp.plugins.themes.mintontheme.partials.Breadcrumbs;
import com.jwebmp.plugins.bootstrap4.alerts.styles.BSAlertInfo;
import com.jwebmp.plugins.bootstrap4.alerts.styles.BSAlertSuccess;
import com.jwebmp.plugins.bootstrap4.alerts.styles.BSAlertWarning;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.fontawesome5.FontAwesomeList;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_12;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_Md_6;
import static com.jwebmp.plugins.bootstrap4.options.BSMarginOptions.MarginTop_1;
import static com.jwebmp.plugins.bootstrap4.options.BSMarginOptions.MarginTop_Md_3;
import static com.jwebmp.plugins.bootstrap4.options.BSTypographyOptions.Text_Center;
import static com.jwebmp.plugins.fontawesome5.FontAwesome.icon;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons.long_arrow_alt_down;
import static com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles.Duotone;

public class ComponentsPage
		extends SwapScreen<ComponentsPage>
{
	public ComponentsPage()
	{
		setRenderSourceSection(true);
		getSourceClasses().add(ComponentsPage.class);
		
		BSColumn<?> left = new BSColumn<>(Col_Md_6);
		BSColumn<?> right = new BSColumn<>(Col_Md_6);
		
		left.add(new H3<>("JWebMP Component Concept"));
		left.add("Components are at the heart of the entire rendering engine and allow you to group features, sets, scripts or other browser sets together.");
		left.add("Components are broken up into 6 distinct compartments, and each forms the abstraction hierarchy for control, and maintenance.");
		left.add(new BSAlertInfo<>().addClass(Text_Center)
		                            .setText("It is better to use a Java Modular System JDK to produce JLink images that support optimized containerized deploys for e.g. Docker and/or Kubernetes"));
		
		left.add(new H4<>("In hierarchical descending order these are "));
		FontAwesomeList<?, ?, ?> fl = new FontAwesomeList<>();
		fl.addItem("Base - <strong>ID Handling, Tiny Render Management</strong>", icon(long_arrow_alt_down, Duotone));
		fl.addItem("Dependency Base - <strong>JavaScript and CSS References</strong>", icon(long_arrow_alt_down, Duotone));
		fl.addItem("Feature Base - <strong>Executable Javascript Queries</strong>", icon(long_arrow_alt_down, Duotone));
		fl.addItem("Event Base - <strong>Client-Side Executions</strong>", icon(long_arrow_alt_down, Duotone));
		fl.addItem("HTML Base - <strong>Tag Rendering Management</strong>", icon(long_arrow_alt_down, Duotone));
		fl.addItem("HTML Attribute Base - <strong>Attributes Rendering Handling</strong>", icon(long_arrow_alt_down, Duotone));
		fl.addItem("Data Binding Base - <strong>Handles client-side variable binding</strong>", icon(long_arrow_alt_down, Duotone));
		fl.addItem("Theme Base - <strong>Attaches CSS management themes, and handles CSS class rendering</strong>", icon(long_arrow_alt_down, Duotone));
		fl.addItem("Hierarchy Base - <strong>Children Hierarchy Management</strong>", icon(long_arrow_alt_down, Duotone));
		fl.addItem("Style Base - <strong>In-Object CSS Annotations Handler</strong>", icon(long_arrow_alt_down, Duotone));
		fl.addItem("Component - <strong>A Strictly-Typed Component</strong>", icon(long_arrow_alt_down, Duotone));
		left.add(fl);
		left.add(new BSAlertSuccess<>().addClass(MarginTop_1)
		                               .addClass(MarginTop_Md_3)
		                               .setText("<strong>Hint</strong> Using JDK 14 and higher produces optimized class files for this form of development " +
				                                        "as a class structure. Go ahead on jump on the new release cycle with no concerns!"));
		
		add(left);
		
		right.add(new H3<>("Objects API"));
		right.add("A component when referencing itself can contain a large number of methods relating to the hierarchy");
		right.add("This makes development of any component super quick, but for new users may make auto-complete navigation a bit tedious");
		right.add(new BSAlertSuccess<>().setText("<strong>Hint </strong> Use methods that start with <code>as()</code> to filter out available methods"));
		right.add("The base methods of a 'complete' component are below. " +
				          "Different components may only extend a specific item of the hierarchy as well, such as a pure dependency or JQuery feature...");
		right.add("A default component comes with at least 60 default methods as highlighted below");
		right.add(new ObjectBrowser(Component.class));
		
		right.add("The interfaces are grouped into only their relevant methods, and allow quick and simple navigation through the entire API");
		
		right.add(new ObjectBrowser(ICssStructure.class, "objBrowser"));
		
		add(right);
		add(new BSAlertWarning<>().addClass(Text_Center)
		                          .addClass(Col_12)
		                          .addClass(MarginTop_1)
		                          .addClass(MarginTop_Md_3)
		                          .setText("<strong>JDK 8</strong> Backwards compatibility can only go so far, " +
				                                   "While it is supported, " +
				                                   "it is mainly to assist in porting from existing systems to the Java Modular System"));
	}
	
	@Override
	public PageTitle pageTitle()
	{
		return new PageTitle("JWebMP - Components - Basics", new Breadcrumbs().addCrumb(new BreadcrumbItem("JWebMP"))
		                                                                      .addCrumb(new BreadcrumbItem("Components"))
		                                                                      .addCrumb(new BreadcrumbItem("Basics", true))
		);
	}
}
