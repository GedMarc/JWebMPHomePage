package com.jwebmp.examples.demos.homepage.display.demos.jqui.demos;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H6;
import com.jwebmp.core.base.html.List;
import com.jwebmp.core.base.html.ListItem;
import com.jwebmp.core.htmlbuilder.css.displays.Cursors;
import com.jwebmp.core.htmlbuilder.css.displays.Positions;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.plugins.bootstrap4.badge.styles.BSBadgeSecondary;
import com.jwebmp.plugins.jqueryui.draggable.JQUIDraggableFeature;
import com.jwebmp.plugins.jqueryui.draggable.enumerations.Axis;
import com.jwebmp.plugins.jqueryui.draggable.enumerations.JQUIDraggablesHelper;
import com.jwebmp.plugins.jqueryui.draggable.options.JQUIDraggableOptions;
import com.jwebmp.plugins.jqueryui.sortable.JQUISortableFeature;

import static com.jwebmp.plugins.jqueryui.draggable.enumerations.JQUIDraggablesRevertTypes.*;
import static com.jwebmp.plugins.jqueryui.themes.JQUIThemeBlocks.*;

public class JQUIDraggableDemoScreen
		extends DemoScreen
{
	public JQUIDraggableDemoScreen()
	{
		setDemoName("Draggable");
		setComponentName("JQuery UI");

		getQuicknotes().add(new H6("This feature will make any item draggable"));
		getQuicknotes().add(new H6("Attaches to almost everything"));

		getClassesOfInterest().add(new BSBadgeSecondary<>().setText("JQUIDraggableFeature")
		                                                   .addStyle("margin-right:5px;"));
		getClassesOfInterest().add(new BSBadgeSecondary<>().setText("JQUIDraggableOptions")
		                                                   .addStyle("margin-right:5px;"));

		getEventsList().add(new BSBadgeSecondary<>().setText("<h5>Drag</h5>")
		                                            .addStyle("margin-right:5px;"));
		getEventsList().add(new BSBadgeSecondary<>().setText("<h5>DragStart</h5>")
		                                            .addStyle("margin-right:5px;"));
		getEventsList().add(new BSBadgeSecondary<>().setText("<h5>DragStop</h5>")
		                                            .addStyle("margin-right:5px;"));

		setOptionsObject(new JQUIDraggableOptions<>());

		getDrawingPane().add(dragMeAround());
		getDrawingPane().add(autoScroll());
		getDrawingPane().add(verticalOnly());
		getDrawingPane().add(horizontalOnly());
		getDrawingPane().add(constrained());
		getDrawingPane().add(uncontrained());

		getDrawingPane().add(cursorCenter());
		getDrawingPane().add(crosshair());
		getDrawingPane().add(cursorBottom());

		getDrawingPane().add(handleSimple());
		getDrawingPane().add(handleConstraint());

		getDrawingPane().add(withSortable());

		getDrawingPane().add(revertOriginal());
		getDrawingPane().add(revertHelper());
	}

	public WorkDiv dragMeAround()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");
		wd.add("Drag me around");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		JQUIDraggableFeature feature = new JQUIDraggableFeature(wd, null);
		feature.getOptions()
		       .setContainment(getDrawingPane());
		wd.addFeature(feature);

		setOptionsObject(feature.getOptions());

		return wd;
	}

	public WorkDiv autoScroll()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");
		wd.add("Scrollable Sensitivy and Speed");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		JQUIDraggableFeature feature;
		wd.addFeature(feature = new JQUIDraggableFeature(wd, null));
		feature.getOptions()
		       .setScroll(true)
		       .setScrollSensitivity(100)
		       .setScrollSpeed(100);
		feature.getOptions()
		       .setContainment(getDrawingPane());
		setOptionsObject(feature.getOptions());

		return wd;
	}

	public WorkDiv verticalOnly()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");
		wd.add("Verticle Only");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		JQUIDraggableFeature feature;
		wd.addFeature(feature = new JQUIDraggableFeature(wd, null));
		feature.getOptions()
		       .setAxis(Axis.Y);
		feature.getOptions()
		       .setContainment(getDrawingPane());
		setOptionsObject(feature.getOptions());

		return wd;
	}

	public WorkDiv horizontalOnly()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");
		wd.add("Horizontal Only");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		JQUIDraggableFeature feature;
		wd.addFeature(feature = new JQUIDraggableFeature(wd, null));
		feature.getOptions()
		       .setAxis(Axis.X);
		feature.getOptions()
		       .setContainment(getDrawingPane());
		setOptionsObject(feature.getOptions());

		return wd;
	}

	public WorkDiv constrained()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");
		wd.add("Constrained");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		JQUIDraggableFeature feature;
		wd.addFeature(feature = new JQUIDraggableFeature(wd, null));
		feature.getOptions()
		       .setContainment(getDrawingPane());
		setOptionsObject(feature.getOptions());

		return wd;
	}

	public WorkDiv uncontrained()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");
		wd.add("Not Constrained");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		JQUIDraggableFeature feature;
		wd.addFeature(feature = new JQUIDraggableFeature(wd, null));
		setOptionsObject(feature.getOptions());

		return wd;
	}

	public WorkDiv cursorCenter()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");
		wd.add("Mouse will always stick to the center");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		JQUIDraggableFeature feature;
		wd.addFeature(feature = new JQUIDraggableFeature(wd, null));
		feature.getOptions()
		       .setCursorAt(new JQUIDraggableOptions.CursorAt(56, 56))
		       .setCursor(Cursors.Move);

		setOptionsObject(feature.getOptions());

		return wd;
	}

	public WorkDiv crosshair()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");
		wd.add("Cursor is crosshair left -5 and top -5");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		JQUIDraggableFeature feature;
		wd.addFeature(feature = new JQUIDraggableFeature(wd, null));
		feature.getOptions()
		       .setCursorAt(new JQUIDraggableOptions.CursorAt(-5, -5))
		       .setCursor(Cursors.Crosshair);

		setOptionsObject(feature.getOptions());

		return wd;
	}

	public WorkDiv cursorBottom()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");
		wd.add("Cursor is bottom 0");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		JQUIDraggableFeature feature;
		wd.addFeature(feature = new JQUIDraggableFeature(wd, null));
		feature.getOptions()
		       .setCursorAt(new JQUIDraggableOptions.CursorAt().setBottom(0))
		       .setCursor(Cursors.Crosshair);

		setOptionsObject(feature.getOptions());

		return wd;
	}

	public WorkDiv handleSimple()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		Div handle = new Div<>().addClass(UI_State_Default)
		                        .setText("I can be dragged only by this handle");
		wd.add(handle);

		JQUIDraggableFeature feature;
		wd.addFeature(feature = new JQUIDraggableFeature(wd, null));
		feature.getOptions()
		       .setHandle(handle)
		       .setCursor(Cursors.Crosshair);

		setOptionsObject(feature.getOptions());

		return wd;
	}

	public WorkDiv handleConstraint()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");
		wd.add("Handle Constraints");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		Div handle = new Div<>().addClass(UI_State_Default)
		                        .setText("I can be dragged by this handle");
		Div cancel = new Div<>().addClass(UI_State_Highlight)
		                        .setText("But not this one cancelled");
		wd.add(handle);
		wd.add(cancel);

		JQUIDraggableFeature feature;
		wd.addFeature(feature = new JQUIDraggableFeature(wd, null));
		feature.getOptions()
		       .setCancel(cancel.getID(true));

		setOptionsObject(feature.getOptions());

		return wd;
	}

	public WorkDiv withSortable()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		List draggable = new List<>(false).addStyle("list-style-type: none; margin: 0; padding: 0; margin-bottom: 10px;");
		ListItem dragMe = (ListItem) draggable.addItem("Drag me down")
		                                      .addClass(UI_State_Highlight);

		List sortable = new List<>(false).addStyle("list-style-type: none; margin: 0; padding: 0; margin-bottom: 10px;");
		sortable.addItem("Item 1")
		        .addClass(UI_State_Default);
		sortable.addItem("Item 2")
		        .addClass(UI_State_Default);
		sortable.addItem("Item 3")
		        .addClass(UI_State_Default);

		wd.add(draggable);
		wd.add(sortable);

		JQUIDraggableFeature feature;
		dragMe.addFeature(feature = new JQUIDraggableFeature(dragMe, null));
		feature.getOptions()
		       .setConnectToSortableType(sortable)
		       .setHelper(JQUIDraggablesHelper.Clone)
		       .setRevert(Invalid);

		JQUISortableFeature sorting;
		sorting = new JQUISortableFeature(sortable);
		sortable.addFeature(sorting);

		setOptionsObject(feature.getOptions());

		return wd;
	}

	public WorkDiv revertOriginal()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");
		wd.add("Revert Original");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		JQUIDraggableFeature feature;
		wd.addFeature(feature = new JQUIDraggableFeature(wd, null));
		feature.getOptions()
		       .setRevert(Invalid);

		setOptionsObject(feature.getOptions());

		return wd;
	}

	public WorkDiv revertHelper()
	{
		WorkDiv wd = super.newWorkDiv();
		wd.addStyle("width:150px;");
		wd.addStyle("height:150px;");
		wd.addStyle("padding:0.5em;");

		wd.addClass("ui-widget-content");
		wd.addClass("ui-draggable-handle");
		wd.add("Revert Helper");

		wd.getCss()
		  .getDisplay()
		  .setPosition(Positions.Relative);

		JQUIDraggableFeature feature;
		wd.addFeature(feature = new JQUIDraggableFeature(wd, null));
		feature.getOptions()
		       .setRevert(Invalid)
		       .setHelper(JQUIDraggablesHelper.Clone);

		setOptionsObject(feature.getOptions());

		return wd;
	}
}
