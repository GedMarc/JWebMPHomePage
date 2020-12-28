package com.jwebmp.plugins.themes.mintontheme;

import com.jwebmp.examples.apps.homepage.DemoBody;
import com.jwebmp.examples.apps.homepage.HomePage;
import org.junit.jupiter.api.Test;

class DemoBodyTest
{
	@Test
	public void testBody()
	{
		DemoBody db = new DemoBody();
		//	System.out.println(db.toString(0));
		
		HomePage hp = new HomePage();
		System.out.println(hp.toString(0));
	}
}