package com.jwebmp.examples.demos.homepage.db.dao;

import com.google.inject.Singleton;
import com.jwebmp.examples.demos.homepage.entities.Plugins;
import com.jwebmp.examples.demos.homepage.entities.Plugins_;

import jakarta.cache.annotation.CacheKey;
import jakarta.cache.annotation.CacheResult;
import java.util.List;

@Singleton
public class PluginsService
{

	@CacheResult
	public String constructMavenDependency(@CacheKey String pluginName)
	{
		Plugins plugins = getPlugin(pluginName);
		String baseDep = "<dependency>\n" +
		                 "    <groupId>" + plugins.getPluginGroupId() + "</groupId>\n" +
		                 "    <artifactId>" + plugins.getPluginArtifactId() + "</artifactId>\n" +
		                 "    <version>${jwebmp.version}</version>\n" +
		                 "    <type>jar</type>\n" +
		                 "</dependency>";
		return baseDep;
	}

	@CacheResult
	public Plugins getPlugin(@CacheKey String pluginName)
	{
		return new Plugins().builder()
		                    .findByName(pluginName)
		                    .get()
		                    .get();
	}

	@CacheResult
	public List<Plugins> getAllPlugins()
	{
		return new Plugins().builder()
		                    .inActiveRange()
		                    .orderBy(Plugins_.pluginName)
		                    .getAll();
	}
}
