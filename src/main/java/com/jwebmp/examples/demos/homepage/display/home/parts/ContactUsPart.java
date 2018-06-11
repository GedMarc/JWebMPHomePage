package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.base.html.DivSimple;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions;
import com.jwebmp.plugins.bootstrap4.options.BSAlignmentVerticalOptions;
import com.jwebmp.plugins.bootstrap4.options.BSSpacingOptions;
import com.jwebmp.plugins.fontawesome.FontAwesome;
import com.jwebmp.plugins.fontawesome.FontAwesomeIcons;
import com.jwebmp.plugins.fontawesome.FontAwesomeProperties;

import static com.jwebmp.plugins.bootstrap4.options.BSAlignmentHorizontalOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;
import static com.jwebmp.plugins.fontawesome.FontAwesomeIcons.*;

public class ContactUsPart<J extends ContactUsPart<J>>
		extends DivSimple<J>
{
	public ContactUsPart()
	{
		addClass(Row);
		addClass(Align_Center);
		addClass(BSAlignmentVerticalOptions.Align_Middle);
		add(buildIcon(facebook_square, "https://www.facebook.com/JWebMP/"));
		add(buildIcon(twitter_square, "https://twitter.com/jwebmp"));
		add(buildIcon(github_square, "https://github.com/GedMarc/JWebMP"));
		add(buildIcon(youtube_square, "https://www.youtube.com/channel/UCKmp3cltVruaBZtGU5VH_Lg"));
		add(buildIcon(envelope_square, "mailto:support@jwebmp.com"));
		//add(buildIcon(refresh, "https://jwebmp.com/teamcity/"));
	}

	private FontAwesome buildIcon(FontAwesomeIcons icon, String link)
	{
		FontAwesome fa = FontAwesome.icon(icon, FontAwesomeProperties.$5x)
		                            .addClass(BSSpacingOptions.Margin_Right_2)
		                            .addClass(BSSpacingOptions.Margin_Bottom_2)
		                            .addClass("btn")
		                            .addClass(BSButtonOptions.Btn_Outline_Primary);

		fa.setTag("a");
		fa.addAttribute("href", link);
		if (!link.contains("mailto:"))
		{
			fa.addAttribute("target", "_blank");
		}
		return fa;
	}
}
