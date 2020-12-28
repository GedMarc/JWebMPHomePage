package com.jwebmp.examples.apps.homepage.pages.fileupload;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.examples.apps.homepage.components.browse.DefaultPackageAPI;
import com.jwebmp.examples.apps.homepage.components.SwapScreen;
import com.jwebmp.plugins.themes.mintontheme.pages.PageTitle;
import com.jwebmp.plugins.themes.mintontheme.partials.BreadcrumbItem;
import com.jwebmp.plugins.themes.mintontheme.partials.Breadcrumbs;
import com.jwebmp.plugins.angularfileupload.angular.AngularFileUploadPageConfigurator;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_12;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.Row;

public class AngularFileUploadDemoScreen
		extends SwapScreen<AngularFileUploadDemoScreen>
{
	public AngularFileUploadDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angularfileupload.angular", AngularFileUploadPageConfigurator.class,
		                          "Angular File Upload", true, true));
		DivSimple<?> sourceDiv = new DivSimple<>().addClass(Col_12, Row);
		
		sourceDiv.add(new Div("The file gets added to the AjaxCall in the following JSON format"));
		sourceDiv.add(new Div("Use AngularFile(s)Upload for multiple files" +
				                      "<br/>AngularFiles contains a collection of the files"));
		
		DivSimple<?> jsonDiv = new DivSimple<>().addClass(Col_12);
		addSourceToContainer(AngularFileUploadDemoScreen.class, "examplejson.txt", SourceCodeLanguages.JS, jsonDiv);
		sourceDiv.add(jsonDiv);
		
		DivSimple<?> codeExample = new DivSimple<>().addClass(Col_12);
		codeExample.add("Use AngularFileInput instead of InputFileType,");
		codeExample.add("Simply map the AngularFile as part of your transfer object as below");
		addSourceToContainer(AngularFileUploadDemoScreen.class, "examplecode.txt", SourceCodeLanguages.Java, codeExample);
		sourceDiv.add(codeExample);
	}
	
	@Override
	public PageTitle pageTitle()
	{
		return new PageTitle("Angular File Upload", new Breadcrumbs().addCrumb(new BreadcrumbItem("JWebMP"))
		                                                             .addCrumb(new BreadcrumbItem("Plugins"))
		                                                             .addCrumb(new BreadcrumbItem("Angular"))
		                                                             .addCrumb(new BreadcrumbItem("File Upload", true))
		);
	}
	
}
