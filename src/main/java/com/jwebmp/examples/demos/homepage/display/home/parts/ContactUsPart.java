package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.PrettyPrimaryButton;
import com.jwebmp.plugins.bootstrap4.buttons.BSButton;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions;
import com.jwebmp.plugins.bootstrap4.options.BSAlignmentVerticalOptions;
import com.jwebmp.plugins.bootstrap4.options.BSSpacingOptions;
import com.jwebmp.plugins.fontawesome.FontAwesome;
import com.jwebmp.plugins.fontawesome.FontAwesomeIcons;
import com.jwebmp.plugins.fontawesome.FontAwesomeProperties;

import static com.jwebmp.plugins.bootstrap4.options.BSAlignmentHorizontalOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSMarginOptions.*;
import static com.jwebmp.plugins.fontawesome.FontAwesomeIcons.*;

public class ContactUsPart<J extends ContactUsPart<J>>
		extends DivSimple<J>
{
	public ContactUsPart()
	{
		addClass("d-flex flex-row my-flex-container");
		addClass(Align_Between);
		addClass(BSAlignmentVerticalOptions.Align_Middle);
		add(buildIcon(facebook_square, "https://www.facebook.com/JWebMP/", "FaceBook"));
		add(buildIcon(twitter_square, "https://twitter.com/jwebmp", "Twitter"));
		add(buildIcon(youtube_square, "https://www.youtube.com/channel/UCKmp3cltVruaBZtGU5VH_Lg", "YouTube"));
		add(buildIcon(envelope_square, "mailto:support@jwebmp.com", "Direct Email"));
		//add(buildIcon(refresh, "https://jwebmp.com/teamcity/"));
	}

	private Link buildIcon(FontAwesomeIcons icon, String link, String title)
	{
		Link actualLink = new Link(link, "_blank");
		BSButton button = new BSButton<>().setText(FontAwesome.icon(icon, FontAwesomeProperties.$3x)
		                                                      .toString(0)
		                                           + "<br/>" + title)
		                                  .addClass(MarginLeft_1)
		                                  .addClass(MarginTop_1)
		                                  .addClass(BSButtonOptions.Btn_Outline_Light)
		                                  .addClass(Align_Center);
		actualLink.add(button);
		return actualLink;
	}
}
