package com.jwebmp.examples.demos.homepage.entities;

import com.jwebmp.entityassist.CoreEntity;
import com.jwebmp.examples.demos.homepage.entities.builders.PluginsBuilder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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
}
