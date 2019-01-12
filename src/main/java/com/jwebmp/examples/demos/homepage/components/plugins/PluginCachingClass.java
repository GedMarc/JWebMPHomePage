package com.jwebmp.examples.demos.homepage.components.plugins;

import com.google.inject.Singleton;
import com.jwebmp.examples.demos.homepage.entities.Plugins;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;

@Singleton
public class PluginCachingClass
{
	@CacheResult
	public Plugins getPlugin(@CacheKey String name)
	{
		return new Plugins().builder()
		                    .findByName(name)
		                    .get()
		                    .orElse(null);
	}
}
