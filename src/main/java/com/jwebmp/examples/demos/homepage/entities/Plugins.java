package com.jwebmp.examples.demos.homepage.entities;

import com.jwebmp.entityassist.CoreEntity;
import com.jwebmp.examples.demos.homepage.entities.builders.PluginsBuilder;

import javax.cache.annotation.CacheDefaults;
import javax.persistence.*;

@Entity
@Cacheable(true)
@CacheDefaults(cacheName = "PluginsCache")
public class Plugins
		extends CoreEntity<Plugins, PluginsBuilder, Long>
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pluginsId;
	@Column
	private String pluginName;
	@Column
	private String pluginVersion;
	@Column
	private String pluginDescription;
	@Column
	private String pluginSourceURL;
	@Column
	private String pluginGithubUrl;
	@Column
	private String pluginJiraUrl;
	@Column
	private String pluginTeamCityUrl;
	@Column
	private String pluginArtifactoryUrl;
	@Column
	private String pluginLogoUrl;
	@Column
	private String pluginSnapshotUrl;
	@Column
	private String pluginDonateUrl;
	@Column
	private int pluginComponentCount;
	@Column
	private int pluginFeatureCount;
	@Column
	private String pluginSonarUrl;
	@Column
	private String projectStatus;
	@Column
	private String pluginArtifactId;
	@Column
	private String pluginGroupId;
	@Column
	private String pluginModuleName;
	@Column
	private String pluginCategory;
	@Column
	private String pluginSubCategory;

	@Override
	public Long getId()
	{
		return pluginsId;
	}

	@Override
	public Plugins setId(Long id)
	{
		pluginsId = id;
		return this;
	}

	public Long getPluginsId()
	{
		return pluginsId;
	}

	public void setPluginsId(Long pluginsId)
	{
		this.pluginsId = pluginsId;
	}

	public String getPluginName()
	{
		return pluginName;
	}

	public void setPluginName(String pluginName)
	{
		this.pluginName = pluginName;
	}

	public String getPluginVersion()
	{
		return pluginVersion;
	}

	public void setPluginVersion(String pluginVersion)
	{
		this.pluginVersion = pluginVersion;
	}

	public String getPluginDescription()
	{
		return pluginDescription;
	}

	public void setPluginDescription(String pluginDescription)
	{
		this.pluginDescription = pluginDescription;
	}

	public String getPluginSourceURL()
	{
		return pluginSourceURL;
	}

	public void setPluginSourceURL(String pluginSourceURL)
	{
		this.pluginSourceURL = pluginSourceURL;
	}

	public String getPluginGithubUrl()
	{
		return pluginGithubUrl;
	}

	public void setPluginGithubUrl(String pluginGithubUrl)
	{
		this.pluginGithubUrl = pluginGithubUrl;
	}

	public String getPluginJiraUrl()
	{
		return pluginJiraUrl;
	}

	public void setPluginJiraUrl(String pluginJiraUrl)
	{
		this.pluginJiraUrl = pluginJiraUrl;
	}

	public String getPluginTeamCityUrl()
	{
		return pluginTeamCityUrl;
	}

	public void setPluginTeamCityUrl(String pluginTeamCityUrl)
	{
		this.pluginTeamCityUrl = pluginTeamCityUrl;
	}

	public String getPluginArtifactoryUrl()
	{
		return pluginArtifactoryUrl;
	}

	public void setPluginArtifactoryUrl(String pluginArtifactoryUrl)
	{
		this.pluginArtifactoryUrl = pluginArtifactoryUrl;
	}

	public String getPluginLogoUrl()
	{
		return pluginLogoUrl;
	}

	public void setPluginLogoUrl(String pluginLogoUrl)
	{
		this.pluginLogoUrl = pluginLogoUrl;
	}

	public String getPluginSnapshotUrl()
	{
		return pluginSnapshotUrl;
	}

	public void setPluginSnapshotUrl(String pluginSnapshotUrl)
	{
		this.pluginSnapshotUrl = pluginSnapshotUrl;
	}

	public String getPluginDonateUrl()
	{
		return pluginDonateUrl;
	}

	public void setPluginDonateUrl(String pluginDonateUrl)
	{
		this.pluginDonateUrl = pluginDonateUrl;
	}

	public int getPluginComponentCount()
	{
		return pluginComponentCount;
	}

	public void setPluginComponentCount(int pluginComponentCount)
	{
		this.pluginComponentCount = pluginComponentCount;
	}

	public int getPluginFeatureCount()
	{
		return pluginFeatureCount;
	}

	public void setPluginFeatureCount(int pluginFeatureCount)
	{
		this.pluginFeatureCount = pluginFeatureCount;
	}

	public String getPluginSonarUrl()
	{
		return pluginSonarUrl;
	}

	public void setPluginSonarUrl(String pluginSonarUrl)
	{
		this.pluginSonarUrl = pluginSonarUrl;
	}

	public String getProjectStatus()
	{
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus)
	{
		this.projectStatus = projectStatus;
	}

	/**
	 * Getter for property 'pluginArtifactId'.
	 *
	 * @return Value for property 'pluginArtifactId'.
	 */
	public String getPluginArtifactId()
	{
		return pluginArtifactId;
	}

	/**
	 * Setter for property 'pluginArtifactId'.
	 *
	 * @param pluginArtifactId
	 * 		Value to set for property 'pluginArtifactId'.
	 */
	public void setPluginArtifactId(String pluginArtifactId)
	{
		this.pluginArtifactId = pluginArtifactId;
	}

	/**
	 * Getter for property 'pluginGroupId'.
	 *
	 * @return Value for property 'pluginGroupId'.
	 */
	public String getPluginGroupId()
	{
		return pluginGroupId;
	}

	/**
	 * Setter for property 'pluginGroupId'.
	 *
	 * @param pluginGroupId
	 * 		Value to set for property 'pluginGroupId'.
	 */
	public void setPluginGroupId(String pluginGroupId)
	{
		this.pluginGroupId = pluginGroupId;
	}

	/**
	 * Getter for property 'pluginModuleName'.
	 *
	 * @return Value for property 'pluginModuleName'.
	 */
	public String getPluginModuleName()
	{
		return pluginModuleName;
	}

	/**
	 * Setter for property 'pluginModuleName'.
	 *
	 * @param pluginModuleName
	 * 		Value to set for property 'pluginModuleName'.
	 */
	public void setPluginModuleName(String pluginModuleName)
	{
		this.pluginModuleName = pluginModuleName;
	}

	/**
	 * Getter for property 'pluginCategory'.
	 *
	 * @return Value for property 'pluginCategory'.
	 */
	public String getPluginCategory()
	{
		return pluginCategory;
	}

	/**
	 * Setter for property 'pluginCategory'.
	 *
	 * @param pluginCategory
	 * 		Value to set for property 'pluginCategory'.
	 */
	public void setPluginCategory(String pluginCategory)
	{
		this.pluginCategory = pluginCategory;
	}

	/**
	 * Getter for property 'pluginSubCategory'.
	 *
	 * @return Value for property 'pluginSubCategory'.
	 */
	public String getPluginSubCategory()
	{
		return pluginSubCategory;
	}

	/**
	 * Setter for property 'pluginSubCategory'.
	 *
	 * @param pluginSubCategory
	 * 		Value to set for property 'pluginSubCategory'.
	 */
	public void setPluginSubCategory(String pluginSubCategory)
	{
		this.pluginSubCategory = pluginSubCategory;
	}
}
