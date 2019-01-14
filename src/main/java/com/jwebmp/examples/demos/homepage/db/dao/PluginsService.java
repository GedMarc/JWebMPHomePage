package com.jwebmp.examples.demos.homepage.db.dao;

import com.google.inject.Singleton;
import com.jwebmp.examples.demos.homepage.entities.Plugins;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheResult;

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


	@SuppressWarnings("unused")
	@CacheRemoveAll
	public void wipeCaches()
	{
		//Dummy to wipe all the caches
	}
}
