package com.jwebmp.examples.demos.homepage;

import com.jwebmp.guicedinjection.interfaces.IPackageContentsScanner;

import java.util.HashSet;
import java.util.Set;

public class IPackageScanner
		implements IPackageContentsScanner
{
	@Override
	public Set<String> searchFor()
	{
		Set<String> strings = new HashSet<>();
		strings.add("com.jwebmp.examples.demos.homepage");
		return strings;
	}
}
