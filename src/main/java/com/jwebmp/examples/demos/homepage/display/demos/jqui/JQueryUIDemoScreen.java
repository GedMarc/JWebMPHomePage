package com.jwebmp.examples.demos.homepage.display.demos.jqui;

import com.jwebmp.examples.demos.homepage.components.events.SwopCodeBlockEvent;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.plugins.jqueryui.accordion.options.JQUIAccordionOptions;
import com.jwebmp.plugins.jqueryui.autocomplete.options.JQUIAutoCompleteOptions;
import com.jwebmp.plugins.jqueryui.button.options.JQUIButtonOptions;
import com.jwebmp.plugins.jqueryui.checkboxradio.options.JQUICheckBoxRadioOptions;
import com.jwebmp.plugins.jqueryui.controlgroup.options.JQUIControlGroupOptions;
import com.jwebmp.plugins.jqueryui.datepicker.options.JQUIDatePickerOptions;
import com.jwebmp.plugins.jqueryui.dialog.options.JQUIDialogOptions;
import com.jwebmp.plugins.jqueryui.draggable.options.JQUIDraggableOptions;
import com.jwebmp.plugins.jqueryui.droppable.options.JQUIDroppableOptions;
import com.jwebmp.plugins.jqueryui.menu.options.JQUIMenuOptions;
import com.jwebmp.plugins.jqueryui.progressbar.options.JQUIProgressBarOptions;
import com.jwebmp.plugins.jqueryui.resizable.options.JQUIResizableOptions;
import com.jwebmp.plugins.jqueryui.selectable.options.JQUISelectableOptions;
import com.jwebmp.plugins.jqueryui.selectmenu.options.JQUISelectMenuOptions;
import com.jwebmp.plugins.jqueryui.slider.options.JQUISliderOptions;
import com.jwebmp.plugins.jqueryui.sortable.options.JQUISortableOptions;
import com.jwebmp.plugins.jqueryui.spinner.options.JQUISpinnerOptions;
import com.jwebmp.plugins.jqueryui.tabs.options.JQUITabOptions;
import com.jwebmp.plugins.jqueryui.tooltips.options.JQUITooltipOptions;

public class JQueryUIDemoScreen
		extends PluginDemoScreen
{
	public JQueryUIDemoScreen()
	{
		super("JQuery UI", "Web Frameworks", "jQuery UI");

		addFeatureTile("Draggable", "Allow elements to be moved using the mouse", JQUIDraggableOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "draggable.txt"));
		addFeatureTile("Droppable", "Enable any DOM element to be droppable, a target for draggable elements.", JQUIDroppableOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "droppable.txt"));
		addFeatureTile("Resizable", "Enable any DOM element to be resizable.",
		               JQUIResizableOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "resizable.txt"));
		addFeatureTile("Selectable", "Enable a DOM element (or group of elements) to be selectable",
		               JQUISelectableOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "selectable.txt"));
		addFeatureTile("Sortable",
		               "Enable a group of DOM elements to be sortable.",
		               JQUISortableOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "sortable.txt"));

		addComponentTile("Accordion", "Displays collapsible content panels for presenting information in a limited amount of space.", JQUIAccordionOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "accordion.txt"));
		addComponentTile("Auto Complete", "Enables users to quickly find and select from a pre-populated list of values as they type, leveraging searching and filtering.",
		                 JQUIAutoCompleteOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "autocomplete.txt"));
		addComponentTile("Button", "Enhances standard form elements like buttons, inputs and anchors to themeable buttons with appropriate hover and active styles",
		                 JQUIButtonOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "button.txt"));

		addComponentTile("CheckboxRadio", "Enhances standard checkbox and radio input element to themeable buttons with appropriate hover and active styles.",
		                 JQUICheckBoxRadioOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "checkboxradio.txt"));

		addComponentTile("Control Group", "A controlgroup featuring various form controls.",
		                 JQUIControlGroupOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "controlgroup.txt"));

		addComponentTile("Date Picker", "Select a date from a popup or inline calendar.",
		                 JQUIDatePickerOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "datepicker.txt"));

		addComponentTile("Dialog", "Open content in an interactive overlay.",
		                 JQUIDialogOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "dialog.txt"));

		addComponentTile("Menu", "Themeable menu with mouse and keyboard interactions for navigation.",
		                 JQUIMenuOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "menu.txt"));

		addComponentTile("Progress Bar", "Display status of a determinate or indeterminate process.",
		                 JQUIProgressBarOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "progressbar.txt"));

		addComponentTile("Select Menu", "Duplicates and extends the functionality of a native HTML select element to overcome the limitations of the native control.",
		                 JQUISelectMenuOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "selectmenu.txt"));

		addComponentTile("Slider", "Drag a handle to select a numeric value.",
		                 JQUISliderOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "slider.txt"));

		addComponentTile("Spinner", "Enhance a text input for entering numeric values, with up/down buttons and arrow key handling",
		                 JQUISpinnerOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "spinner.txt"));

		addComponentTile("Tabs", "A single content area with multiple panels, each associated with a header in a list.",
		                 JQUITabOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "tabs.txt"));

		addComponentTile("Tooltips", "Customizable, themeable tooltips, replacing native tooltips.",
		                 JQUITooltipOptions.class)
				.addEvent(new SwopCodeBlockEvent(JQueryUIDemoScreen.class, "tooltips.txt"));

		addOptionsBrowser(new JQUIAccordionOptions());

		getAdditionalsRight().add(getCodeBlockJava(JQueryUIDemoScreen.class, "accordion.txt"));

	}
}
