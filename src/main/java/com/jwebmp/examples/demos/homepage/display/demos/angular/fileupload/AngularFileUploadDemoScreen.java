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

		getAdditionalsRight().add(new Div("The file gets added to the AjaxCall in the following JSON format"));
		getAdditionalsRight().add(new Div("Use AngularFile(s)Upload for multiple files" +
		                                  "<br/>AngularFiles contains a collection of the files"));

		Div jsonDiv = new Div<>().addClass(Col_12);
		addSourceToContainer(AngularFileUploadDemoScreen.class, "examplejson.txt", SourceCodeLanguages.JS, jsonDiv);
		getAdditionalsRight().add(jsonDiv);

		Div codeExample = new Div<>().addClass(Col_12);
		codeExample.add("Use AngularFileInput instead of InputFileType,");
		codeExample.add("Simply map the AngularFile as part of your transfer object as below");
		addSourceToContainer(AngularFileUploadDemoScreen.class, "examplecode.txt", SourceCodeLanguages.Java, codeExample);
		getAdditionals().add(codeExample);
	}
}
