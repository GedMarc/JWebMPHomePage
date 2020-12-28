package com.jwebmp.examples.apps.homepage.pages.helloworld;

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.core.utilities.EscapeChars;
import com.jwebmp.examples.apps.homepage.components.SwapScreen;
import com.jwebmp.plugins.themes.mintontheme.pages.PageTitle;
import com.jwebmp.plugins.themes.mintontheme.partials.BreadcrumbItem;
import com.jwebmp.plugins.themes.mintontheme.partials.Breadcrumbs;
import com.jwebmp.plugins.themes.mintontheme.timeline.TimelineLayout;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_12;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.HTML;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.Java;

public class GeneratingHTMLPage extends SwapScreen<GeneratingHTMLPage>
{
	private final TimelineLayout layout = new TimelineLayout(true);
	
	public GeneratingHTMLPage()
	{
		setRenderSourceSection(true);
		getSourceClasses().add(GeneratingHTMLPage.class);
		try
		{
			BSRow<?> row = BSRow.newInstance();
			BSColumn<?> fullWidth = BSColumn.newInstance(Col_12);
			fullWidth.add(buildConsoleScreen());
			row.add(fullWidth);
			
			row.add(new Link<>("https://github.com/GedMarc/JWebMP-Examples-Undertow-HelloWorld", "_blank")
					        .setText("All these examples can be download at the Github Repository")
					        .addClass(Col_12));
			add(row);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public PageTitle pageTitle()
	{
		return new PageTitle("JWebMP - HTML Generation", new Breadcrumbs().addCrumb(new BreadcrumbItem("JWebMP"))
		                                                                  .addCrumb(new BreadcrumbItem("Components"))
		                                                                  .addCrumb(new BreadcrumbItem("Generating HTML", true))
		);
	}
	
	private DivSimple<?> buildConsoleScreen()
	{
		DivSimple<?> d = new DivSimple<>();
		
		DivSimple<?> sourceExampleDiv = new DivSimple<>();
		
		BSNavTabs<?> sourceTabs = new BSNavTabs<>();
		sourceTabs.addClass(BSNavsOptions.Tabs_Bordered);
		DivSimple<?> javaExample = new DivSimple<>();
		javaExample.add(
				"Standard Output requires no server to render and is used for Unit Tests. These may of course be used to port JWebMP components across to other frameworks as well.");
		javaExample.add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                            .setSourceCodeLanguage(SourceCodeLanguages.Java)
		                                            .setText(EscapeChars.forHTML(
				                                            FileTemplates.getFileTemplate(ComponentsPage.class, "helloworld_console_java", "helloworldjavaconsole.txt", false)
				                                                         .toString()
				                                                         .replace('\t', ' ')
				                                                         .replace("    ", "  "))));
		sourceTabs.addTab("HTML Output", javaExample, true);
		
		javaExample.add("This creates the below output")
		           .add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                            .setSourceCodeLanguage(HTML)
		                                            .setText(EscapeChars.forHTML(
				                                            FileTemplates.getFileTemplate(ComponentsPage.class, "helloworld_console_html.txtt", "helloworldhtmlconsole.txt",
				                                                                          false)
				                                                         .toString()
				                                                         .replace('\t', ' ')
				                                                         .replace("    ", "  "))));
		
		buildScriptsTab(sourceTabs);
		
		buildJsonTab(sourceTabs);
		
		DivSimple<?> cssTab;
		sourceTabs.addTab("Styling", cssTab = buildTab("Basic Styling is incredibly simple, from Beginner to CSS Expert, all your needs should be catered for.", Java,
		                                               FileTemplates.getFileTemplate(ComponentsPage.class, "helloworldcssconsole.txt", "helloworldcssconsole.txt")), false);
		
		cssTab.add("The CSS is produced through the methods and combined into the page")
		      .add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                       .setSourceCodeLanguage(SourceCodeLanguages.HTML)
		                                       .setText(EscapeChars.forHTML(FileTemplates.getFileTemplate(ComponentsPage.class, "helloworldcssconsole_htmloutput.txt",
		                                                                                                  "helloworldcssconsole_htmloutput.txt", false)
		                                                                                 .toString()
		                                                                                 .replace('\t', ' ')
		                                                                                 .replace("    ", "  ")))
		                                       .addStyle("overflow-y:auto")
		                                       .addStyle("max-height:250px;"));
		cssTab.add("Adding the Annotations to a Class generates the CSS as a CSS Class.");
		cssTab.add(
				"Extending a CSSComponent will allow you to create and group features functions events and queries to any grouped class selector that specifies the ID of the component.");
		
		buildClassTab(sourceTabs);
		sourceExampleDiv.add(sourceTabs);
		
		d.add(sourceExampleDiv);
		
		return d;
	}
	
	private void buildScriptsTab(BSNavTabs<?> sourceTabs)
	{
		DivSimple<?> jsTab;
		sourceTabs.addTab("Scripts", jsTab = buildTab(
				"At its very core, managing the dependencies is always quick and easy.<br/>By utilizing these basics, complex widgets and components can be put together.<br/> Theme Jars become simple to implement and web libraries can be adopted very quickly.",
				Java, FileTemplates.getFileTemplate(ComponentsPage.class, "helloworldjavascriptconsole.txt", "helloworldjavascriptconsole.txt")), false);
		
		jsTab.add("The structure is ordered and prioritized accordingly when built. Setting the Priority to Top_Shelf places references in the header. ")
		     .add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                      .setSourceCodeLanguage(HTML)
		                                      .setText(EscapeChars.forHTML(
				                                      FileTemplates.getFileTemplate(ComponentsPage.class, "helloworldjavascriptconsole_output.txtt",
				                                                                    "helloworldjavascriptconsole_output.txt", false)
				                                                   .toString()
				                                                   .replace('\t', ' ')
				                                                   .replace("    ", "  "))));
	}
	
	private void buildJsonTab(BSNavTabs<?> sourceTabs)
	{
		DivSimple<?> jsonSerializeTab;
		sourceTabs.addTab("JSON", jsonSerializeTab = buildTab(
				"By default, all objects toString() serialize into JSON. This allows for persistable screen objects, and are great for persistent session implementations across clusters",
				Java, FileTemplates.getFileTemplate(ComponentsPage.class, "helloworldjsonconsole.txt", "helloworldjsonconsole.txt")), false);
		
		jsonSerializeTab.add("This produces a completely deserializable component object")
		                .add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                                 .setSourceCodeLanguage(SourceCodeLanguages.JS)
		                                                 .setText(EscapeChars.forHTML(
				                                                 FileTemplates.getFileTemplate(ComponentsPage.class, "helloworldjsonconsole_output.txt",
				                                                                               "helloworldjsonconsole_output.txt", false)
				                                                              .toString()
				                                                              .replace('\t', ' ')
				                                                              .replace("    ", "  ")))
		                                                 .addStyle("overflow-y:auto")
		                                                 .addStyle("max-height:250px;"));
	}
	
	private DivSimple<?> buildTab(String description, SourceCodeLanguages language, StringBuilder fileTemplate)
	{
		DivSimple<?> htmlExample = new DivSimple<>();
		htmlExample.add(description);
		htmlExample.add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                            .setSourceCodeLanguage(language)
		                                            .setText(EscapeChars.forHTML(fileTemplate.toString()
		                                                                                     .replace('\t', ' ')
		                                                                                     .replace("    ", "  "))));
		return htmlExample;
	}
	
	private void buildClassTab(BSNavTabs<?> sourceTabs)
	{
		DivSimple<?> cssTab;
		sourceTabs.addTab("Class Allocation", cssTab = buildTab("Class assignment allows you to group features, events, and other items together<br/>" +
				                                                        "Extending a CSSComponent will allow you to create and group features functions events and queries to any grouped class selector that specifies the ID of the component.",
		                                                        Java,
		                                                        FileTemplates.getFileTemplate(ComponentsPage.class, "helloworldcssclassyconsole.txt", "helloworldcssclassyconsole.txt")),
		                  false);
		
		cssTab.add("Utilizing CSS Selectors you can assign any feature, event, or action in groups.")
		      .add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                       .setSourceCodeLanguage(SourceCodeLanguages.HTML)
		                                       .setText(EscapeChars.forHTML(FileTemplates.getFileTemplate(ComponentsPage.class, "helloworldcssclassyconsole_output.txt",
		                                                                                                  "helloworldcssclassyconsole_output.txt", false)
		                                                                                 .toString()
		                                                                                 .replace('\t', ' ')
		                                                                                 .replace("    ", "  ")))
		                                       .addStyle("overflow-y:auto")
		                                       .addStyle("max-height:250px;"));
	}
}
