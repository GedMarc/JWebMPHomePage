package com.jwebmp.examples.apps.homepage.components.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Accessors(chain = true)
@Getter
@Setter
public class Plugins
{
	private String pluginUniqueName;
	private String pluginName;
	private String pluginVersion;
	private String pluginDescription;
	private String pluginSourceURL;
	private String pluginGithubUrl;
	private String pluginJiraUrl;
	private String pluginTeamCityUrl;
	private String pluginArtifactoryUrl;
	private String pluginLogoUrl;
	private String pluginSnapshotUrl;
	private String pluginDonateUrl;
	private int pluginComponentCount;
	private int pluginFeatureCount;
	private String pluginSonarUrl;
	private String projectStatus;
	private String pluginArtifactId;
	private String pluginGroupId;
	private String pluginModuleName;
	private String pluginCategory;
	private String pluginSubCategory;
	private String pluginOriginalSite;
	private LocalDate pluginLastUpdatedDate;
	private String pluginAuthor;
	private String pluginWiki;
}
