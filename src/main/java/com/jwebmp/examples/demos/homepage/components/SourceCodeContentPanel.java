package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.FileTemplates;
import com.jwebmp.base.ComponentBase;
import com.jwebmp.base.html.Div;
import com.jwebmp.base.html.DivSimple;
import com.jwebmp.examples.demos.homepage.components.general.MintonPanel;
import com.jwebmp.examples.demos.homepage.display.SourceCodeDetailsContainer;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import org.apache.commons.lang3.StringEscapeUtils;
import za.co.mmagon.guiceinjection.Pair;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;

public class SourceCodeContentPanel<J extends SourceCodeContentPanel<J>>
		extends MintonPanel<J>
{
	private final DivSimple<?> container = new DivSimple<>();
	boolean first = false;
	private BSNavTabs<?> sourceTabs = new BSNavTabs<>();
	private SourceCodeDetailsContainer<?> details;
	private DivSimple<?> javaCodeDiv;
	private DivSimple<?> htmlCodeDiv;
	private DivSimple<?> cssCodeDiv;
	private DivSimple<?> jsCodeDiv;
	private DivSimple<?> testCodeDiv;
	private Map<String, DivSimple<?>> customCodeDiv;

	public SourceCodeContentPanel(String title, @Nullable Div componentHierarchyBase)
	{
		this(title, null, componentHierarchyBase);
	}

	public SourceCodeContentPanel(String title, DisplayCodeParts details, @Nullable Div componentHierarchyBase)
	{
		this(title, details, componentHierarchyBase, "bg-primary");
	}

	public SourceCodeContentPanel(String title, DisplayCodeParts details, @Nullable Div componentHierarchyBase, String bgColour)
	{
		super(title, componentHierarchyBase, bgColour);
		setShowCode(true);
		setShowHeader(false);
		setCodeParts(details);

		if (componentHierarchyBase == null)
		{
			setContent(container);
		}
		else
		{
			setContent(componentHierarchyBase);
		}
		if (details != null)
		{
			this.details = details.getSourceCodeDetailsContainer();
		}
		else
		{
			this.details = new SourceCodeDetailsContainer();
		}

		javaCodeDiv = new DivSimple<>();
		htmlCodeDiv = new DivSimple<>();
		cssCodeDiv = new DivSimple<>();
		jsCodeDiv = new DivSimple<>();
		testCodeDiv = new DivSimple<>();
		customCodeDiv = new LinkedHashMap<>();
	}

	@Override
	public void preConfigure()
	{
		if (!isConfigured())
		{

			sourceTabs.addClass(BSNavsOptions.Tabs_Bordered);
			if (details.getJavaSnippetLocations()
			           .getKeyOptional()
			           .isPresent())
			{
				buildJavaSourceDiv();
			}

			if (details.getHtmlSnippetLocations()
			           .getKeyOptional()
			           .isPresent())
			{
				buildHtmlSourceDiv();
			}

			if (details.getJavaScriptSnippetLocations()
			           .getKeyOptional()
			           .isPresent())
			{
				buildJavascriptSourceDiv();
			}

			if (details.getCssSnippetLocations()
			           .getKeyOptional()
			           .isPresent())
			{
				buildCssSourceDiv();
			}

			if (details.getTestsScriptSnippetLocations()
			           .getKeyOptional()
			           .isPresent())
			{
				buildTestsSourceDiv();
			}

			details.getCustomLocations()
			       .forEach((String key, Pair<Class, String> value) ->
			                {
				                Class reference = value.getKey();
				                String filename = value.getValue();
				                buildCustomSourceDiv(key, reference, filename, new Div());
			                });


			if (!sourceTabs.getTabs()
			               .isEmpty() && !isCodeButtonPanel())
			{
				getContent().add(sourceTabs);
			}
		}
		super.preConfigure();
	}

	private Div buildJavaSourceDiv()
	{
		javaCodeDiv.add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                            .setSourceCodeLanguage(SourceCodeLanguages.Java)
		                                            .setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate((Class) getDetails().getJavaSnippetLocations()
		                                                                                                                                     .getKey(),
		                                                                                                                 (String) getDetails().getJavaSnippetLocations()
		                                                                                                                                      .getValue(),
		                                                                                                                 (String) getDetails().getJavaSnippetLocations()
		                                                                                                                                      .getValue(), true)
		                                                                                                .toString())));
		sourceTabs.addTab("Java", javaCodeDiv, !first);
		first = true;
		return javaCodeDiv;
	}

	private Div buildHtmlSourceDiv()
	{
		htmlCodeDiv.add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                            .setSourceCodeLanguage(SourceCodeLanguages.Java)
		                                            .setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate((Class) getDetails().getHtmlSnippetLocations()
		                                                                                                                                     .getKey(),
		                                                                                                                 (String) getDetails().getHtmlSnippetLocations()
		                                                                                                                                      .getValue(),
		                                                                                                                 (String) getDetails().getHtmlSnippetLocations()
		                                                                                                                                      .getValue(), true)
		                                                                                                .toString())));
		sourceTabs.addTab("Java", htmlCodeDiv, !first);
		first = true;
		return javaCodeDiv;
	}

	private Div buildJavascriptSourceDiv()
	{
		try
		{


			jsCodeDiv.add(new JQSourceCodePrettify<>().addStyle("background:black;")
			                                          .setSourceCodeLanguage(SourceCodeLanguages.Java)
			                                          .setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate((Class) getDetails().getJavaScriptSnippetLocations()
			                                                                                                                                   .getKey(),
			                                                                                                               (String) getDetails().getJavaScriptSnippetLocations()
			                                                                                                                                    .getValue(),
			                                                                                                               (String) getDetails().getJavaScriptSnippetLocations()
			                                                                                                                                    .getValue(), true)
			                                                                                              .toString())));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		sourceTabs.addTab("Javascript", jsCodeDiv, !first);
		first = true;
		return jsCodeDiv;
	}

	private Div buildCssSourceDiv()
	{
		cssCodeDiv.add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                           .setSourceCodeLanguage(SourceCodeLanguages.Java)
		                                           .setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate((Class) getDetails().getCssSnippetLocations()
		                                                                                                                                    .getKey(),
		                                                                                                                (String) getDetails().getCssSnippetLocations()
		                                                                                                                                     .getValue(),
		                                                                                                                (String) getDetails().getCssSnippetLocations()
		                                                                                                                                     .getValue(), true)
		                                                                                               .toString())));
		sourceTabs.addTab("CSS", cssCodeDiv, !first);
		first = true;
		return javaCodeDiv;
	}

	private Div buildTestsSourceDiv()
	{
		testCodeDiv.add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                            .setSourceCodeLanguage(SourceCodeLanguages.Java)
		                                            .setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate((Class) getDetails().getTestsScriptSnippetLocations()
		                                                                                                                                     .getKey(),
		                                                                                                                 (String) getDetails().getTestsScriptSnippetLocations()
		                                                                                                                                      .getValue(),
		                                                                                                                 (String) getDetails().getTestsScriptSnippetLocations()
		                                                                                                                                      .getValue(), true)
		                                                                                                .toString())));
		sourceTabs.addTab("Tests", testCodeDiv, !first);
		first = true;
		return javaCodeDiv;
	}

	private Div buildCustomSourceDiv(String text, Class reference, String fileName, Div content)
	{
		if (content == null)
		{
			content = new Div();
		}
		try
		{
			content.add(new JQSourceCodePrettify<>().addStyle("background:black;")
			                                        .setSourceCodeLanguage(SourceCodeLanguages.Java)
			                                        .setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate(reference, fileName, fileName, true)
			                                                                                            .toString())));
			sourceTabs.addTab(text, content, !first);
			first = true;
		}
		catch (Exception e)
		{
			ComponentBase.log.log(Level.FINER, "Couldn't find file to render", e);
			content.add("Couldn't find filename : " + fileName);
		}

		return content;
	}

	public SourceCodeDetailsContainer getDetails()
	{
		return details;
	}

	public void setDetails(SourceCodeDetailsContainer details)
	{
		this.details = details;
	}

	public BSNavTabs<?> getSourceTabs()
	{
		return sourceTabs;
	}

	public void setSourceTabs(BSNavTabs<?> sourceTabs)
	{
		this.sourceTabs = sourceTabs;
	}

	public DivSimple<?> getJavaCodeDiv()
	{
		return javaCodeDiv;
	}

	public void setJavaCodeDiv(DivSimple<?> javaCodeDiv)
	{
		this.javaCodeDiv = javaCodeDiv;
	}

	public DivSimple<?> getHtmlCodeDiv()
	{
		return htmlCodeDiv;
	}

	public void setHtmlCodeDiv(DivSimple<?> htmlCodeDiv)
	{
		this.htmlCodeDiv = htmlCodeDiv;
	}

	public DivSimple<?> getCssCodeDiv()
	{
		return cssCodeDiv;
	}

	public void setCssCodeDiv(DivSimple<?> cssCodeDiv)
	{
		this.cssCodeDiv = cssCodeDiv;
	}

	public DivSimple<?> getJsCodeDiv()
	{
		return jsCodeDiv;
	}

	public void setJsCodeDiv(DivSimple<?> jsCodeDiv)
	{
		this.jsCodeDiv = jsCodeDiv;
	}

	public DivSimple<?> getTestCodeDiv()
	{
		return testCodeDiv;
	}

	public void setTestCodeDiv(DivSimple<?> testCodeDiv)
	{
		this.testCodeDiv = testCodeDiv;
	}

	public Map<String, DivSimple<?>> getCustomCodeDiv()
	{
		return customCodeDiv;
	}

	public void setCustomCodeDiv(Map<String, DivSimple<?>> customCodeDiv)
	{
		this.customCodeDiv = customCodeDiv;
	}
}

