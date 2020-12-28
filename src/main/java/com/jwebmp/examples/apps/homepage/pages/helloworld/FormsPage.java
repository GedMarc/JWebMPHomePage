package com.jwebmp.examples.apps.homepage.pages.helloworld;

import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.apps.homepage.components.SwapScreen;
import com.jwebmp.plugins.themes.mintontheme.pages.PageTitle;
import com.jwebmp.plugins.themes.mintontheme.partials.BreadcrumbItem;
import com.jwebmp.plugins.themes.mintontheme.partials.Breadcrumbs;
import com.jwebmp.examples.apps.homepage.pages.fileupload.AngularFileUploadDemoScreen;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_12;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_Md_6;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.Row;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.Java;

public class FormsPage
		extends SwapScreen<FormsPage>
{
	public FormsPage()
	{
		BSCardBody<?> all = getDefaultBody();
		
		//all.add(new PluginModulePart("JWebMP Core"));
		
		all.add(buildAbout());
		all.add(buildBindings());
		all.add(buildDefaultAngular());
		all.add(buildBlueImpFileUpload());
		add(all);
	}
	
	@Override
	public PageTitle pageTitle()
	{
		return new PageTitle("Basics - Forms", new Breadcrumbs().addCrumb(new BreadcrumbItem("JWebMP"))
		                                                        .addCrumb(new BreadcrumbItem("Basics"))
		                                                        .addCrumb(new BreadcrumbItem("Forms", true))
		);
	}
	
	
	private DivSimple<?> buildAbout()
	{
		DivSimple<?> div = new DivSimple<>();
		div.add("Forms in their most basic form are nothing more than additional tags, with data binding attributes using the client data-binder provider (AngularJS or <i>Vue*</i>)" +
				        "<br/>This allows a very flexible system allowing cross-page, cross field, client and server side validations and more to be incredibly simple.");
		
		div.add("Input raw tag types are identified by <code>Input...Type</code>, and are usually wrapped in a form, although this is completely optional. Tags are bound to data stored on the client side, allowing for mapping and storage across pages, navigations, and indeed any action");
		
		addSourceToContainer(FormsPage.class, "bindingsbasic.txt", Java, div);
		
		return div;
	}
	
	private DivSimple<?> buildBindings()
	{
		DivSimple<?> div = new DivSimple<>();
		
		div.add("Data Bindings are variable based, and are registered using <code>.bind()</code>. " +
				        "<br/>You can return any number of variables at any time back to the server, and send any number of variables to the client. Pre-Populating forms is merely sending the data object to the client" +
				        "<br/>You can map almost anything to any variable, displaying data follows the standard Angular syntax <code>{{variable.name}}</code>." +
				        "<br/>Add the class <code>ng-non-bindable</code> to skip replacements in the text")
		   .addClass("ng-non-bindable");
		div.add(
				"Any object that can be serialized with the JacksonJSON framework (pretty much everything) can be used as a client data object. " +
						"<br/JavaScriptPart takes care of serialization and adds some fields like referenceId");
		
		div.add("Client Side Variables/Pre-Populated form fields are sent to the client via addDto() method on any component, the method simply places the data on the client");
		addSourceToContainer(FormsPage.class, "bindingsbasic_3.txt", Java, div);
		
		div.add("Form validations are simple, And styling is straight forward, without using any additional UI frameworks like Bootstrap or JQuery UI");
		addSourceToContainer(FormsPage.class, "formbasics.txt", Java, div);
		return div;
	}
	
	private DivSimple<?> buildDefaultAngular()
	{
		DivSimple<?> div = new DivSimple<>().addClass(Row);
		DivSimple<?> left = new DivSimple<>().addClass(Col_12, Col_Md_6);
		DivSimple<?> right = new DivSimple<>().addClass(Col_12, Col_Md_6);
		
		div.add(left);
		div.add(right);
		
		right.add(new DivSimple<>("The file gets added to the AjaxCall in the following JSON format"));
		right.add(new DivSimple<>("Use AngularFile(s)Upload for multiple files" +
				                          "<br/>AngularFiles contains a collection of the files"));
		
		DivSimple<?> jsonDiv = new DivSimple<>().addClass(Col_12);
		addSourceToContainer(AngularFileUploadDemoScreen.class, "examplejson.txt", SourceCodeLanguages.JS, jsonDiv);
		right.add(jsonDiv);
		
		DivSimple<?> codeExample = new DivSimple<>().addClass(Col_12);
		codeExample.add("Use <code>AngularFileInput</code> as the Web Component.");
		codeExample.add("Then map the AngularFile as part of your transfer object as below");
		addSourceToContainer(AngularFileUploadDemoScreen.class, "examplecode.txt", SourceCodeLanguages.Java, codeExample);
		left.add(codeExample);
		
		return div;
	}
	
	private DivSimple<?> buildBlueImpFileUpload()
	{
		DivSimple<?> div = new DivSimple<>();
		
		div.add(new Link<>("https://blueimp.github.io/jQuery-File-Upload/angularjs.html", "_blank")
				        .setText("BlueImp File Upload ")
				        .toString(0)
				        + " allows you to control every aspect of file uploading, from icons, progression monitoring to deletions");
		div.add("This library allows you to take full control over that process");
		
		addSourceToContainer(FormsPage.class, "blueimpexample.txt", SourceCodeLanguages.Java, div);
		
		return div;
	}
}
