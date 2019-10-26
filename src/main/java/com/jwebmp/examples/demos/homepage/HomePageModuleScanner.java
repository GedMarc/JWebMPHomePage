package com.jwebmp.examples.demos.homepage;

import com.guicedee.guicedinjection.interfaces.IGuiceScanJarInclusions;
import com.guicedee.guicedinjection.interfaces.IGuiceScanModuleInclusions;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class HomePageModuleScanner
		implements IGuiceScanModuleInclusions<HomePageModuleScanner>, IGuiceScanJarInclusions<HomePageModuleScanner>
{
	@Override
	public @NotNull Set<String> includeJars()
	{
		Set<String> strings = new HashSet<>();
		strings.add("ServiceCreate.jar");
		return strings;
	}

	@Override
	public @NotNull Set<String> includeModules()
	{
		Set<String> strings = new HashSet<>();
		strings.add("com.jwebmp.examples.demos.homepage");
		return strings;
	}
}
