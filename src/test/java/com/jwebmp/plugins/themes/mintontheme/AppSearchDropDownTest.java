package com.jwebmp.plugins.themes.mintontheme;

import com.jwebmp.plugins.themes.mintontheme.appsearch.AppSearchDropDown;
import org.junit.jupiter.api.Test;

class AppSearchDropDownTest
{
	
	@Test
	public void testSearchDropdown()
	{
		AppSearchDropDown dd = new AppSearchDropDown();
		
		System.out.println(dd.toString(0));
		
	}
}