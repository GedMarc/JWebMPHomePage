/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwebmp.examples.demos.homepage.components;

import com.google.inject.Singleton;
import com.jwebmp.base.html.Image;
import com.jwebmp.plugins.bootstrap4.navbar.parts.BSNavBarBrandImage;
import com.jwebmp.base.html.Image;
import com.jwebmp.examples.demos.homepage.display.home.events.GoHomeEvent;
import com.jwebmp.plugins.bootstrap4.navbar.parts.BSNavBarBrandImage;

/**
 * @author Marc Magon
 * @since 07 Aug 2017
 */
@Singleton
public class BrandImage
		extends BSNavBarBrandImage
{

	private static final long serialVersionUID = 1L;

	/*
	 * Constructs a new BrandImage
	 */
	public BrandImage()
	{
		super(new Image<>(
				"https://scontent-jnb1-1.xx.fbcdn.net/v/t1.0-1/15965425_403281776682490_3698780637189972200_n" + "" + "" + "" + "" + "" + ".png?oh=ef5dd4eeac5431320c4b375a5bb92ed5&oe=5ACD10DB").addStyle(
				"height:67px;width:67px;"));
		setID("BrandImage");
		addStyle("cursor:pointer;");
		addEvent(new GoHomeEvent(this));
	}

}
