package com.jwebmp.examples.demos.homepage.display.demos.jqui;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.examples.demos.homepage.components.general.PackageComponentsBrowser;

import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.jqueryui.JQUIPageConfigurator;
import com.jwebmp.plugins.jqueryui.accordion.JQUIAccordion;
import com.jwebmp.plugins.jqueryui.autocomplete.JQUIAutoComplete;
import com.jwebmp.plugins.jqueryui.button.JQUIButton;
import com.jwebmp.plugins.jqueryui.checkboxradio.JQUICheckBox;
import com.jwebmp.plugins.jqueryui.checkboxradio.JQUIRadioButton;
import com.jwebmp.plugins.jqueryui.controlgroup.JQUIControlGroup;
import com.jwebmp.plugins.jqueryui.datepicker.JQUIDatePicker;
import com.jwebmp.plugins.jqueryui.dialog.JQUIDialog;
import com.jwebmp.plugins.jqueryui.draggable.JQUIDraggable;
import com.jwebmp.plugins.jqueryui.droppable.JQUIDroppable;
import com.jwebmp.plugins.jqueryui.menu.JQUIMenu;
import com.jwebmp.plugins.jqueryui.progressbar.JQUIProgressBar;
import com.jwebmp.plugins.jqueryui.resizable.JQUIResizable;
import com.jwebmp.plugins.jqueryui.selectable.JQUISelectable;
import com.jwebmp.plugins.jqueryui.selectmenu.JQUISelectMenu;
import com.jwebmp.plugins.jqueryui.slider.JQUISlider;
import com.jwebmp.plugins.jqueryui.sortable.JQUISortable;
import com.jwebmp.plugins.jqueryui.spinner.JQUISpinner;
import com.jwebmp.plugins.jqueryui.tabs.JQUITabs;
import com.jwebmp.plugins.jqueryui.tooltips.JQUIToolTipFeature;
import org.apache.commons.lang3.tuple.Pair;

import static com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class JQueryUIDemoScreen
		extends DemoScreen
{
	public JQueryUIDemoScreen()
	{
		Div componentsDiv = new Div<>().addClass(Col_12);
		componentsDiv.addClass(Justify_Content_Center);
		PackageComponentsBrowser browser =
				new PackageComponentsBrowser("JQuery UI",
				                             Pair.of("All", JQUIPageConfigurator.class),
				                             Pair.of("Draggable", JQUIDraggable.class),
				                             Pair.of("Droppable", JQUIDroppable.class),
				                             Pair.of("Resizable", JQUIResizable.class),
				                             Pair.of("Selectable", JQUISelectable.class),
				                             Pair.of("Sortable", JQUISortable.class),
				                             Pair.of("Accordion", JQUIAccordion.class),
				                             Pair.of("Auto Complete", JQUIAutoComplete.class),
				                             Pair.of("Button", JQUIButton.class),
				                             Pair.of("Checkbox", JQUICheckBox.class),
				                             Pair.of("Radio", JQUIRadioButton.class),
				                             Pair.of("Control Group", JQUIControlGroup.class),
				                             Pair.of("Date Picker", JQUIDatePicker.class),
				                             Pair.of("Dialog", JQUIDialog.class),
				                             Pair.of("Menu", JQUIMenu.class),
				                             Pair.of("Progress Bar", JQUIProgressBar.class),
				                             Pair.of("Select Menu", JQUISelectMenu.class),
				                             Pair.of("Slider", JQUISlider.class),
				                             Pair.of("Spinner", JQUISpinner.class),
				                             Pair.of("Tabs", JQUITabs.class),
				                             Pair.of("Tooltips", JQUIToolTipFeature.class)
				);
		//componentsDiv.add(browser);
		add(browser);

		add(new DefaultPackageAPI("com.jwebmp.plugins.jqueryui", JQUIPageConfigurator.class,
		                          "JQuery UI", true, true));
		add(buildGoToSource(JQUIPageConfigurator.class, DefaultPackageAPI.class, DefaultReadMore.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		add(more);
		more.add(getCodeBlockJava(getClass(), "accordion.txt"));
	}
}
