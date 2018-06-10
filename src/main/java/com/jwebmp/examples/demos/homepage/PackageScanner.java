package com.jwebmp.examples.demos.homepage;

import com.jwebmp.guiceinjection.scanners.PackageContentsScanner;

import java.util.HashSet;
import java.util.Set;

public class PackageScanner
		implements PackageContentsScanner
{
	@Override
	public Set<String> searchFor()
	{
		Set<String> strings = new HashSet<>();
		strings.add("META-INF");
		strings.add("blablabla");
		strings.add("com.jwebmp");
		return strings;
	}
}
