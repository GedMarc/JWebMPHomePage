package com.jwebmp.examples.demos.homepage.components.display;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScrollFeature;
import com.jwebmp.examples.demos.homepage.components.WebComponentsService;
import com.jwebmp.examples.demos.homepage.components.general.PackagesBrowser;
import com.jwebmp.examples.demos.homepage.components.general.PrettyCollapsable;
import com.jwebmp.examples.demos.homepage.db.dao.PluginsService;
import com.jwebmp.examples.demos.homepage.entities.Plugins;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.cards.layout.BSCardBox;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Optional;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class DefaultPackageAPI
		extends DivSimple<DefaultPackageAPI>
{
	private Plugins plugins;

	public DefaultPackageAPI(String packageName, Class defaultObjectClass, String pluginName)
	{
		this(packageName, defaultObjectClass, pluginName, true, true);
	}

	public DefaultPackageAPI(String packageName, Class defaultObjectClass, String pluginName, boolean hidePluginInfoByDefault, boolean whitelistSubPackages)
	{
		if (pluginName != null)
		{
			this.plugins = GuiceContext.get(PluginsService.class)
			                           .getPlugin(pluginName);
			add(new PluginModulePart(pluginName, hidePluginInfoByDefault));

			BSRow pluginDetails = new BSRow();

			add(new PrettyCollapsable(pluginDetails, "View Plugin Details"));

			BSColumn left = new BSColumn(Col_12, Col_Md_6);
			BSColumn right = new BSColumn(Col_12, Col_Md_6);

			left.add(buildDependencyInformation());
			right.add(buildArtifactInformation());
			pluginDetails.add(left);
			pluginDetails.add(right);
		}

		add(buildAPITrees(packageName, defaultObjectClass, whitelistSubPackages));
	}

	private DivSimple<?> buildDependencyInformation()
	{
		BSCardBox<?> mavenDisplayDiv = new BSCardBox<>();
		JQSourceCodePrettify prettify = new JQSourceCodePrettify();
		prettify.addStyle("background-color:#36404a;");
		prettify.setSourceCodeLanguage(XML);
		prettify.setShowLineNums(false);
		String baseDep = GuiceContext.get(PluginsService.class)
		                             .constructMavenDependency(plugins.getPluginName());
		prettify.setText(StringEscapeUtils.escapeHtml4(baseDep));
		mavenDisplayDiv.add(prettify);
		prettify.addStyle("padding-bottom:0px !important;");
		return mavenDisplayDiv;
	}

	private DivSimple<?> buildArtifactInformation()
	{
		BSCardBox<?> mavenDisplayDiv = new BSCardBox<>();
		Optional<Plugins> plugin = Optional.ofNullable(plugins);
		if (plugin.isPresent())
		{
			BSRow versionRow = new BSRow();
			versionRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("Source Widget"));
			versionRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(
					new Link<>(plugin.get()
					                 .getPluginSourceURL(), "_blank").setText("View Demo - <small>Version " + plugin.get()
					                                                                                                .getPluginVersion() + "</small>")));
			mavenDisplayDiv.add(versionRow);

			BSRow donationRow = new BSRow();
			donationRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("Donate to the Widget Devs"));
			donationRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(new Link<>(plugin.get()
			                                                                                      .getPluginDonateUrl(), "_blank").setText("Yes I'll Support!")));
			mavenDisplayDiv.add(donationRow);

			BSRow teamcityRow = new BSRow();
			teamcityRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("TeamCity"));
			teamcityRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(new Link<>(plugin.get()
			                                                                                      .getPluginTeamCityUrl(), "_blank").setText("Go To TeamCity")));
			mavenDisplayDiv.add(teamcityRow);

			BSRow jiraRow = new BSRow();
			jiraRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("Jira Project Tracking"));
			jiraRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(new Link<>(plugin.get()
			                                                                                  .getPluginJiraUrl(), "_blank").setText("Go To Jira")));
			mavenDisplayDiv.add(jiraRow);

			BSRow sonarRow = new BSRow();
			sonarRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("SonarQube Quality Gate"));
			sonarRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(new Link<>(plugin.get()
			                                                                                   .getPluginSonarUrl(), "_blank").setText("Go To SonarQube")));
			mavenDisplayDiv.add(sonarRow);

			BSRow artifactoryRow = new BSRow();
			artifactoryRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("Artifactory Direct Download"));
			artifactoryRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(new Link<>(plugin.get()
			                                                                                         .getPluginArtifactoryUrl(), "_blank").setText("Go To Artifactory")));
			mavenDisplayDiv.add(artifactoryRow);
		}
		else
		{
			//add("Plugin name wrong for artifact information");
		}

		return mavenDisplayDiv;
	}

	private Div buildAPITrees(String packageName, Class defaultObjectClass, boolean whitelistSubPackages)
	{

		BSRow pageContentRow = new BSRow();

		Div left = new Div<>().addClass("col-12 col-md-6")
		                      .addStyle("padding-left:0px;padding-right:0px;");
		Div right = new Div<>().addClass("col-12 col-md-6")
		                       .addStyle("padding-left:0px;padding-right:0px;");

		PackagesBrowser packes = GuiceContext.get(WebComponentsService.class)
		                                     .getPackagesBrowser(packageName, whitelistSubPackages);
		packes.setID("pacakageBrowser");

		DefaultSlimScrollFeature scroll = new DefaultSlimScrollFeature(packes);
		scroll.getOptions()
		      .setHeight("440px");

		left.add(packes);

		DisplayAPITabView tabs = GuiceContext.get(WebComponentsService.class)
		                                     .getTabViewDisplay(defaultObjectClass);
		right.add(tabs);

		pageContentRow.add(left);
		pageContentRow.add(right);
		pageContentRow.addStyle("background-color", "#333 !important");
		return pageContentRow;
	}


}
