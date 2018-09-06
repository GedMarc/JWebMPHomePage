package com.jwebmp.examples.demos.homepage.display.demos.angular.fileupload;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class AngularFileUploadDemoScreen
		extends PluginDemoScreen
{
	public AngularFileUploadDemoScreen()
	{
		super("Angular File Upload", "Angular", "Utilities", "File Upload");

		addComponentTile("Angular File Upload",
		                 "A file upload component that allows the usage of ng-bind for file uploads.");

		getAdditionalsRight().add(new Div("These components are great to bind file uploads directly to client side variables" +
		                                  "This directive is great automatically included when added to the classpath, or a dependency of the library is added."));

		getAdditionalsRight().add(new Div("The file gets added to the AjaxCall in the following JSON format"));

		Div jsonDiv = new Div<>().addClass(Col_12);
		addSourceToContainer(AngularFileUploadDemoScreen.class, "examplejson.txt", SourceCodeLanguages.JS, jsonDiv);
		getAdditionalsRight().add(jsonDiv);

		Div codeExample = new Div<>().addClass(Col_12);
		codeExample.add("Use AngularFileInput instead of InputFileType,");
		codeExample.add("Map the AngularFile as part of your Dto/Entity as below");
		addSourceToContainer(AngularFileUploadDemoScreen.class, "examplecode.txt", SourceCodeLanguages.Java, codeExample);
		getAdditionals().add(codeExample);
	}
}
