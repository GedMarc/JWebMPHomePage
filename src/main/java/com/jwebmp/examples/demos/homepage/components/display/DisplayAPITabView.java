package com.jwebmp.examples.demos.homepage.components.display;

import com.jwebmp.core.Event;
import com.jwebmp.core.base.ComponentFeatureBase;
import com.jwebmp.core.base.ComponentHTMLBase;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.core.utilities.EscapeChars;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScrollFeature;
import com.jwebmp.examples.demos.homepage.components.general.ObjectBrowser;
import com.jwebmp.examples.demos.homepage.components.general.OptionsBrowser;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.logger.LogFactory;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.toastr.ToastrFeature;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class DisplayAPITabView
		extends DivSimple<DisplayAPITabView>
{
	private static final long serialVersionUID = 1L;
	private static final OptionsBrowser defaultBrowser = new OptionsBrowser(new JavaScriptPart());
	private static final Logger log = LogFactory.getLog("DisplayASPITabView");

	private final Class defaultObjectClass;

	public DisplayAPITabView(Class defaultObjectClass)
	{
		this.defaultObjectClass = defaultObjectClass;
		setID("displayapitabview");
	}

	@Override
	public void init()
	{
		if (!isInitialized())
		{
			add(buildTabsView(defaultObjectClass));
		}
		super.init();
	}

	private BSNavTabs buildTabsView(Class defaultObjectClass)
	{
		BSNavTabs<?> tabs = new BSNavTabs<>();
		DivSimple<?> clazzContainer = new DivSimple<>();
		DivSimple<?> clazzApi = new DivSimple<>();
		DivSimple<?> htmlOutput = new DivSimple<>();
		DivSimple<?> jsOutput = new DivSimple<>();
		clazzContainer.add(clazzApi);

		ObjectBrowser browser = new ObjectBrowser(defaultObjectClass, "pluginObjectBrowser").addStyle("height:100%");
		clazzApi.add(browser);

		DivSimple<?> optionsContainer = new DivSimple<>();
		DivSimple<?> optionsApi = new DivSimple<>();
		try
		{

			if (Event.class.isAssignableFrom(defaultObjectClass))
			{
				optionsApi.add("Events usually don't have any specific options");
				htmlOutput.add("Events add attributes to html components, and the binder takes over from there.");
				jsOutput.add("These are server side driven events. Features denote a client side only action.");
			}
			else if (ComponentFeatureBase.class.isAssignableFrom(defaultObjectClass))
			{
				try
				{
					ComponentFeatureBase comp = (ComponentFeatureBase) defaultObjectClass.getDeclaredConstructor()
					                                                                     .newInstance();
					if (comp.getOptions() == null || comp.getOptions()
					                                     .getClass()
					                                     .equals(JavaScriptPart.class))
					{
						optionsApi.add("This component does not have any explicit JavaScript/JSON options.");
					}
					if (comp.getOptions() != null)
					{
						optionsApi.add(new OptionsBrowser(comp.getOptions()));
					}
					else
					{
						optionsApi.add(defaultBrowser);
					}

					if (ComponentHTMLBase.class.isAssignableFrom(defaultObjectClass))
					{
						ComponentHTMLBase htmlBase = (ComponentHTMLBase) comp;
						htmlOutput.add("The below is the output of <code>toString(0)</code>");
						htmlOutput.add(new JQSourceCodePrettify<>().setSourceCodeLanguage(HTML)
						                                           .setText(EscapeChars.forHTML(htmlBase.toString(0))));
						htmlOutput.add("I'm working on getting the full options api working for generation.");

						String js = htmlBase.renderJavascript()
						                    .toString();
						if (js.trim()
						      .isEmpty())
						{
							jsOutput.add("No JavaScript is rendered for this component. Looks like it can take features tho!");
						}
						else
						{
							jsOutput.add("This components renders the following JavaScript");
							jsOutput.add(new JQSourceCodePrettify<>().setSourceCodeLanguage(JS)
							                                         .setText(EscapeChars.forHTML(htmlBase.renderJavascript()
							                                                                              .toString())));
						}
					}
				}
				catch (NoSuchMethodException | InvocationTargetException me)
				{
					AjaxResponse ar = GuiceContext.get(AjaxResponse.class);
					ar.addFeature(new ToastrFeature<>()
							              .setTitle("Missing Default Constructor")
							              .setMessage("Class [" + defaultObjectClass +
							                          "] is missing a default constructor... ;/"));
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, "Type not handled [" + defaultObjectClass + "]", e);
				}
			}
			else if (IPageConfigurator.class.isAssignableFrom(defaultObjectClass))
			{

			}
			else
			{
				LogFactory.getLog(getClass())
				          .warning("Not catered for [" + defaultObjectClass + "]");
			}
		}
		catch (Exception e)
		{
			LogFactory.getLog(getClass())
			          .log(Level.SEVERE, "Unable to create component?", e);
		}

		optionsContainer.add(optionsApi);

		tabs.addTab("Class API", clazzContainer, true);
		tabs.addTab("Options API", optionsContainer, false);
		tabs.addTab("HTML", htmlOutput, false);
		tabs.addTab("JavaScript", jsOutput, false);

		DefaultSlimScrollFeature scroll2 = new DefaultSlimScrollFeature(clazzApi);
		scroll2.getOptions()
		       .setHeight("400px");

		tabs.getTabContents()
		    .addStyle("padding", "0px");
		addStyle("background-color", "#333 !important");

		return tabs;
	}
}
