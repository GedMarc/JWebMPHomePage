package com.jwebmp.examples.demos.homepage.components.plugins;

import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.core.base.html.SmallText;
import com.entityassist.enumerations.Operand;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.entities.Plugins;
import com.jwebmp.examples.demos.homepage.entities.Plugins_;
import com.jwebmp.plugins.bootstrap4.cards.layout.BSCardBox;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

import java.util.Optional;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

@SuppressWarnings("Duplicates")
public class PluginInfoPart
		extends DisplayPart<PluginInfoPart>
{
	private final String pluginName;

	public PluginInfoPart(String pluginName)
	{
		this.pluginName = pluginName;
	}

	private DivSimple<?> buildDependencyInformation(String depedencyInfo)
	{
		BSCardBox<?> mavenDisplayDiv = new BSCardBox<>();
		JQSourceCodePrettify prettify = new JQSourceCodePrettify();
		prettify.addStyle("background-color:#36404a;");
		prettify.setSourceCodeLanguage(SourceCodeLanguages.XML);
		prettify.setShowLineNums(false);
		prettify.setText(depedencyInfo);
		mavenDisplayDiv.add(prettify);
		prettify.addStyle("padding-bottom:0px !important;");
		mavenDisplayDiv.add(new SmallText("Simply switch the group to com.jwebmp.jre10 or jre11 to move between releases :)"));
		return mavenDisplayDiv;
	}

	private DivSimple<?> buildArtifactInformation()
	{
		BSCardBox<?> mavenDisplayDiv = new BSCardBox<>();

		Optional<Plugins> plugin = new Plugins().builder()
		                                        .where(Plugins_.pluginName, Operand.Equals, pluginName)
		                                        .get();


		if (plugin.isPresent())
		{
			BSRow versionRow = new BSRow();
			versionRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("Source Widget"));
			versionRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(
					new Link<>(plugin.get()
					                 .getPluginSourceURL(), "_blank").setText("View Demo <br/> Version " + plugin.get()
					                                                                                             .getPluginVersion())));
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
}
