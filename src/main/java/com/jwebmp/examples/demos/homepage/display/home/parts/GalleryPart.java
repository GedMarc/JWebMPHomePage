package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.plugins.blueimp.gallery.BlueImpCarousel;
import com.jwebmp.plugins.blueimp.gallery.BlueImpGallery;
import com.jwebmp.plugins.bootstrap4.cards.layout.BSCardBox;

public class GalleryPart
		extends BSCardBox
{
	public GalleryPart()
	{
		add(buildCarousel());
		add(buildGallery());
	}

	private BlueImpGallery buildCarousel()
	{
		BlueImpCarousel gallery = new BlueImpCarousel();
		gallery.setControls(true);
		gallery.getOptions()
		       .setStartSlideshow(true);
		gallery.addScreen("images/screens/1.png", null, null, "Alt");
		gallery.addScreen("images/screens/2.png", null, null, "Alt");
		gallery.addScreen("images/screens/2015/12.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/3.png", null, null, "Alt");
		gallery.addScreen("images/screens/4.png", null, null, "Alt");
		gallery.addScreen("images/screens/5.png", null, null, "Alt");
		gallery.addScreen("images/screens/6.png", null, null, "Alt");
		gallery.addScreen("images/screens/7.png", null, null, "Alt");
		//gallery.addScreen("images/screens/2012.JPG", null, null, "Alt");
		//gallery.addScreen("images/screens/2012_2.png", null, null, "Alt");
		//gallery.addScreen("images/screens/2013.png", null, null, "Alt");
		//gallery.addScreen("images/screens/2013_2.png", null, null, "Alt");
		//gallery.addScreen("images/screens/2013_3.png", null, null, "Alt");
		gallery.addScreen("images/screens/2014_1.png", null, null, "Alt");
		gallery.addScreen("images/screens/2015/2.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/3.PNG", null, null, "Alt");
		//gallery.addScreen("images/screens/2015/4.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/5.PNG", null, null, "Alt");
		//gallery.addScreen("images/screens/2015/6.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/7.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/8.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/9.PNG", null, null, "Alt");
		//gallery.addScreen("images/screens/2015/10.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/11.PNG", null, null, "Alt");

		gallery.addScreen("images/screens/2015/13.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/14.PNG", null, null, "Alt");
		return gallery;
	}

	private BlueImpGallery buildGallery()
	{
		BlueImpGallery gallery = new BlueImpGallery();
		gallery.setControls(true);
		gallery.addScreen("images/screens/1.png", null, null, "Alt");
		gallery.addScreen("images/screens/2.png", null, null, "Alt");
		gallery.addScreen("images/screens/2015/12.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/3.png", null, null, "Alt");
		gallery.addScreen("images/screens/4.png", null, null, "Alt");
		gallery.addScreen("images/screens/5.png", null, null, "Alt");
		gallery.addScreen("images/screens/6.png", null, null, "Alt");
		gallery.addScreen("images/screens/7.png", null, null, "Alt");
		//gallery.addScreen("images/screens/2012.JPG", null, null, "Alt");
		//gallery.addScreen("images/screens/2012_2.png", null, null, "Alt");
		//gallery.addScreen("images/screens/2013.png", null, null, "Alt");
		//gallery.addScreen("images/screens/2013_2.png", null, null, "Alt");
		//gallery.addScreen("images/screens/2013_3.png", null, null, "Alt");
		gallery.addScreen("images/screens/2014_1.png", null, null, "Alt");
		gallery.addScreen("images/screens/2015/2.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/3.PNG", null, null, "Alt");
		//gallery.addScreen("images/screens/2015/4.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/5.PNG", null, null, "Alt");
		//gallery.addScreen("images/screens/2015/6.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/7.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/8.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/9.PNG", null, null, "Alt");
		//gallery.addScreen("images/screens/2015/10.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/11.PNG", null, null, "Alt");

		gallery.addScreen("images/screens/2015/13.PNG", null, null, "Alt");
		gallery.addScreen("images/screens/2015/14.PNG", null, null, "Alt");
		return gallery;
	}
}
