package com.jwebmp.examples.apps.homepage.services;

import com.google.inject.Singleton;
import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.core.plugins.PluginInformation;
import com.jwebmp.examples.apps.homepage.components.dto.Plugins;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class PluginsService
{
	private static final List<Plugins> plugins = new ArrayList<>();
	
	public String constructMavenDependency(String pluginName)
	{
		Plugins plugins = getPlugin(pluginName);
		
		String baseDep = "<dependency>\n" +
				"    <groupId>" + plugins.getPluginGroupId() + "</groupId>\n" +
				"    <artifactId>" + plugins.getPluginArtifactId() + "</artifactId>\n" +
				"</dependency>";
		
		return baseDep;
	}
	
	public Plugins getPlugin(String pluginName)
	{
		return getAllPlugins().stream()
		                      .parallel()
		                      .filter(a -> a.getPluginUniqueName()
		                                    .equalsIgnoreCase(pluginName))
		                      .findFirst()
		                      .orElse(new Plugins().setPluginName(pluginName)
		                                           .setPluginUniqueName(pluginName));
	}
	
	public List<Plugins> getAllPlugins()
	{
		if (plugins.isEmpty())
		{
			//this takes a toll
			GuiceContext.instance()
			            .getScanResult()
			            .getClassesWithAnnotation(PluginInformation.class.getCanonicalName())
			            .forEach(a -> {
				            PluginInformation pi = a.loadClass()
				                                    .getAnnotation(PluginInformation.class);
				            Plugins p = new Plugins();
				            p.setPluginName(pi.pluginName());
				            p.setPluginDescription(pi.pluginDescription());
				            p.setPluginVersion(pi.pluginVersion());
				            p.setProjectStatus("Ready");
				            p.setPluginCategory(pi.pluginCategories());
				            p.setPluginSourceURL(pi.pluginSourceUrl());
				            p.setPluginGithubUrl(pi.pluginGitUrl());
				            p.setPluginDonateUrl(pi.pluginSourceDonateUrl());
				            p.setPluginUniqueName(pi.pluginUniqueName());
				            p.setPluginWiki(pi.pluginWikiUrl());
				            p.setPluginOriginalSite(pi.pluginOriginalHomepage());
				            p.setPluginLogoUrl(pi.pluginIconUrl());
				            p.setPluginModuleName(pi.pluginModuleName());
				            p.setPluginGroupId(pi.pluginGroupId());
				            p.setPluginArtifactId(pi.pluginArtifactId());
				            plugins.add(p);
			            });
		}
		return plugins;
	}
}
