package com.jwebmp.examples.demos.homepage.display.helloworld;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestParameters;
import com.jwebmp.FileTemplates;
import com.jwebmp.base.html.Div;
import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.Link;
import com.jwebmp.base.html.interfaces.GlobalChildren;
import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.general.MintonPanel;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.annotation.Nullable;
import java.util.Map;

import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class HelloWorldScreen
		extends DisplayScreen
{
	//Another way of getting a hold of the method parameters
	@Inject
	@RequestParameters
	Map<String, String[]> params;

	private BSContainer block = new BSContainer();

	public HelloWorldScreen()
	{
		super("Hello World");

	}

	@Override
	public BSContainer<?> getContentContainer()
	{
		perform();
		return block;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Hello World"));
		return crumbs;
	}

	public void perform()
	{
		BSRow<?> row = BSRow.newInstance();
		BSColumn<?> fullWidth = BSColumn.newInstance(Col_12);

		BSNavTabs<?> tabs = new BSNavTabs<>();

		tabs.asMe()
		    .addTab("Console Output", buildConsoleScreen(), true);

		fullWidth.add(tabs);
		row.add(fullWidth);
		block.add(row);

	}

	private Div<?, ?, ?, ?, ?> buildConsoleScreen()
	{
		Div d = new Div();

		d.add(new Link<>("https://github.com/GedMarc/JWebMP-Examples-Undertow-HelloWorld", "_blank").setText("All these demos can be download at the Github Repository")
		                                                                                            .addClass(Col_12));

		DivSimple<?> sourceExampleDiv = new DivSimple();
		MintonPanel<?> portlet = new MintonPanel<>("Source Examples", Bg_Secondary.toString(), sourceExampleDiv);

		BSNavTabs<?> sourceTabs = new BSNavTabs<>();
		sourceTabs.addClass(BSNavsOptions.Tabs_Bordered);
		DivSimple<?> javaExample = new DivSimple<>();
		javaExample.add(
				"Standard Output requires no server to render and is used for Unit Tests. These may of course be used to port JWebMP components across to other frameworks as well.");
		javaExample.add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                            .setSourceCodeLanguage(SourceCodeLanguages.Java)
		                                            .setText(StringEscapeUtils.escapeHtml4(
				                                            FileTemplates.getFileTemplate(HelloWorldScreen.class, "helloworld_console_java", "helloworldjavaconsole.txt", false)
				                                                         .toString()
				                                                         .replace('\t', ' ')
				                                                         .replace("    ", "  "))));
		sourceTabs.addTab("Java", javaExample, true);

		javaExample.add("This creates the below output")
		           .add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                            .setSourceCodeLanguage(HTML)
		                                            .setText(StringEscapeUtils.escapeHtml4(
				                                            FileTemplates.getFileTemplate(HelloWorldScreen.class, "helloworld_console_html.txtt", "helloworldhtmlconsole.txt",
				                                                                          false)
				                                                         .toString()
				                                                         .replace('\t', ' ')
				                                                         .replace("    ", "  "))));

		buildScriptsTab(sourceTabs);


		buildJsonTab(sourceTabs);


		Div cssTab;
		sourceTabs.addTab("Styling", cssTab = buildTab("Basic Styling is incredibly simple, from Beginner to CSS Expert, all your needs should be catered for.", Java,
		                                               FileTemplates.getFileTemplate(HelloWorldScreen.class, "helloworldcssconsole.txt", "helloworldcssconsole.txt")), false);


		cssTab.add("The CSS is produced through the methods and combined into the page")
		      .add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                       .setSourceCodeLanguage(SourceCodeLanguages.HTML)
		                                       .setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate(HelloWorldScreen.class, "helloworldcssconsole_htmloutput.txt",
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

		portlet.setShowHeader(false);
		d.add(portlet);

		return d;
	}

	private void buildScriptsTab(BSNavTabs sourceTabs)
	{
		Div jsTab;
		sourceTabs.addTab("Scripts", jsTab = buildTab(
				"At its very core, managing the dependencies is always quick and easy.<br/>By utilizing these basics, complex widgets and components can be put together.<br/> Theme Jars become simple to implement and web libraries can be adopted very quickly.",
				Java, FileTemplates.getFileTemplate(HelloWorldScreen.class, "helloworldjavascriptconsole.txt", "helloworldjavascriptconsole.txt")), false);

		jsTab.add("The structure is ordered and prioritized accordingly when built. Setting the Priority to Top_Shelf places references in the header. ")
		     .add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                      .setSourceCodeLanguage(HTML)
		                                      .setText(StringEscapeUtils.escapeHtml4(
				                                      FileTemplates.getFileTemplate(HelloWorldScreen.class, "helloworldjavascriptconsole_output.txtt",
				                                                                    "helloworldjavascriptconsole_output.txt", false)
				                                                   .toString()
				                                                   .replace('\t', ' ')
				                                                   .replace("    ", "  "))));
	}

	private void buildJsonTab(BSNavTabs sourceTabs)
	{
		Div jsonSerializeTab;
		sourceTabs.addTab("JSON", jsonSerializeTab = buildTab(
				"By default, all objects toString() serialize into JSON. This allows for persistable screen objects, and are great for persistent session implementations across clusters",
				Java, FileTemplates.getFileTemplate(HelloWorldScreen.class, "helloworldjsonconsole.txt", "helloworldjsonconsole.txt")), false);


		jsonSerializeTab.add("This produces a completely deserializable component object")
		                .add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                                 .setSourceCodeLanguage(SourceCodeLanguages.JS)
		                                                 .setText(StringEscapeUtils.escapeHtml4(
				                                                 FileTemplates.getFileTemplate(HelloWorldScreen.class, "helloworldjsonconsole_output.txt",
				                                                                               "helloworldjsonconsole_output.txt", false)
				                                                              .toString()
				                                                              .replace('\t', ' ')
				                                                              .replace("    ", "  ")))
		                                                 .addStyle("overflow-y:auto")
		                                                 .addStyle("max-height:250px;"));
	}

	private Div<GlobalChildren, ?, ?, ?, ?> buildTab(@Nullable String description, SourceCodeLanguages language, StringBuilder fileTemplate)
	{
		DivSimple<?> htmlExample = new DivSimple<>();
		htmlExample.add(description);
		htmlExample.add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                            .setSourceCodeLanguage(language)
		                                            .setText(StringEscapeUtils.escapeHtml4(fileTemplate.toString()
		                                                                                               .replace('\t', ' ')
		                                                                                               .replace("    ", "  "))));
		return htmlExample;
	}

	private void buildClassTab(BSNavTabs sourceTabs)
	{
		Div cssTab;
		sourceTabs.addTab("Classy", cssTab = buildTab(
				"Class assignment allows you to group features, events, and other items together<br/>" + "Extending a CSSComponent will allow you to create and group features functions events and queries to any grouped class selector that specifies the ID of the component.",
				Java, FileTemplates.getFileTemplate(HelloWorldScreen.class, "helloworldcssclassyconsole.txt", "helloworldcssclassyconsole.txt")), false);


		cssTab.add("Utilizing CSS Selectors you can assign any feature, event, or action in groups.")
		      .add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                       .setSourceCodeLanguage(SourceCodeLanguages.HTML)
		                                       .setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate(HelloWorldScreen.class, "helloworldcssclassyconsole_output.txt",
		                                                                                                            "helloworldcssclassyconsole_output.txt", false)
		                                                                                           .toString()
		                                                                                           .replace('\t', ' ')
		                                                                                           .replace("    ", "  ")))
		                                       .addStyle("overflow-y:auto")
		                                       .addStyle("max-height:250px;"));
	}
}
