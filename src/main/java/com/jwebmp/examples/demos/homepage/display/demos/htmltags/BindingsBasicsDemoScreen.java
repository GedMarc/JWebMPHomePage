package com.jwebmp.examples.demos.homepage.display.demos.htmltags;

import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class BindingsBasicsDemoScreen
		extends PluginDemoScreen
{

	public BindingsBasicsDemoScreen()
	{
		super("HtmlTags", "Base", "Form Basics");

		addComponentTile("AngularVariableWatcher", "Notifies the server of a change for a client variable").addStyle("cursor:default");
		addComponentTile("AngularChangeEvent", "Event that fires when a variable is updated").addStyle("cursor:default");

		BSRow binding = BSRow.newInstance();
		BSColumn topColumn;
		BSColumn leftColumn = BSColumn.newInstance();

		getAdditionalsRight().add(topColumn = new BSColumn<>(Col_12));

		topColumn.add("Data Bindings are variable based, and are registered using .bind(). " +
		              "<br/>You can return any number of variables at any time back to the server, and send any number of variables to the client." +
		              "<br/>You can map any input to any variable, whether in a form or not, and displaying is as easy as standard Angular syntax {{variable.name}}")
		         .addClass("ng-non-bindable");
		topColumn.add(getCodeBlockJava(FormBasicsDemoScreen.class, "bindingsbasic.txt"));
		topColumn.add(
				"Any object that can be serialized with the JacksonJSON framework (pretty much everything) can be used as a transfer object. JavaScriptPart takes care of serialization" +
				"and adds some fields like referenceId");
		topColumn.add(getCodeBlockJava(FormBasicsDemoScreen.class, "bindingsbasic_2.txt").setID("coce1"));
		topColumn.add("Client Side Variables/Pre-Populated form fields are sent to the client via addDto() method with the given object");
		topColumn.add(getCodeBlockJava(FormBasicsDemoScreen.class, "bindingsbasic_3.txt").setID("coce2"));
		leftColumn.add("Any event can send back multiple variables when fired. The registered variables are retrievable through the AjaxCall");
		leftColumn.add(getCodeBlockJava(FormBasicsDemoScreen.class, "bindingsbasic_4.txt").setID("coce3"));

		leftColumn.add("Watch Angular Variables by utilizing the AngularFeature found on the Page Object");
		leftColumn.add(getCodeBlockJava(FormBasicsDemoScreen.class, "bindingsbasic_6.txt").setID("coce4"));

		getAdditionals().add(leftColumn);

		//getBottomRows().add(binding);
	}

}
