/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwebmp.examples.demos.homepage.components;

import com.google.inject.Singleton;
import com.jwebmp.core.base.html.Span;

/**
 * @author GedMarc
 * @since 07 Aug 2017
 */
@Singleton
public class MenuButton
		extends Span
{


	/*
	 * Constructs a new BrandImage
	 */
	public MenuButton()
	{
		setText("<i class=\"fa fa-align-justify\"></i><span class=\"hidden-sm-down\">&nbsp;Menu</span>");
		addClass("hvr-radial-out btn");
		addStyle("cursor:pointer;");
	}
}
