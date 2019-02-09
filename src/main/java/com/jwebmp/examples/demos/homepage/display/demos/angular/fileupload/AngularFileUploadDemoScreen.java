package com.jwebmp.examples.demos.homepage.display.demos.angular.fileupload;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.examples.demos.homepage.components.general.OptionsBrowser;
import com.jwebmp.plugins.angularfileupload.angular.AngularFileUploadPageConfigurator;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;

public class AngularFileUploadDemoScreen
		extends DemoScreen
{
	public AngularFileUploadDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angularfileupload.angular", AngularFileUploadPageConfigurator.class,
		                          "Angular File Upload", true, true));
		add(buildGoToSource(AngularFileUploadPageConfigurator.class, DefaultPackageAPI.class, OptionsBrowser.class));

		Div sourceDiv = new Div<>().addClass(Col_12, Row);

		sourceDiv.add(new Div("The file gets added to the AjaxCall in the following JSON format"));
		sourceDiv.add(new Div("Use AngularFile(s)Upload for multiple files" +
		                      "<br/>AngularFiles contains a collection of the files"));

		Div jsonDiv = new Div<>().addClass(Col_12);
		addSourceToContainer(AngularFileUploadDemoScreen.class, "examplejson.txt", SourceCodeLanguages.JS, jsonDiv);
		sourceDiv.add(jsonDiv);

		Div codeExample = new Div<>().addClass(Col_12);
		codeExample.add("Use AngularFileInput instead of InputFileType,");
		codeExample.add("Simply map the AngularFile as part of your transfer object as below");
		addSourceToContainer(AngularFileUploadDemoScreen.class, "examplecode.txt", SourceCodeLanguages.Java, codeExample);
		sourceDiv.add(codeExample);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		add(more);
	}

}
