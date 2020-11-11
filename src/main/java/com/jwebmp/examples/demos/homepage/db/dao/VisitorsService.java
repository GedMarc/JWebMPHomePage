package com.jwebmp.examples.demos.homepage.db.dao;

import com.google.inject.Singleton;
import com.jwebmp.examples.demos.homepage.entities.Visitors;

import jakarta.cache.annotation.CacheKey;
import jakarta.cache.annotation.CacheResult;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class VisitorsService
{
	@CacheResult
	public Optional<Visitors> findByUUID(@CacheKey UUID uuid)
	{
		return new Visitors().builder()
		                     .findByGuid(uuid.toString())
		                     .inVisibleRange()
		                     .get();
	}

	public long countVisitors()
	{
		return new Visitors().builder()
		                     .inVisibleRange()
		                     .getCount();
	}
}
