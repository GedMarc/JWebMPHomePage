package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.buttons.BSButton;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.IFontAwesomeIcon;
import com.jwebmp.plugins.fontawesome5.options.FontAwesomeSizes;

import static com.jwebmp.plugins.bootstrap4.options.BSAlignmentHorizontalOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSMarginOptions.*;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeBrandIcons.*;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons.*;

public class ContactUsPart<J extends ContactUsPart<J>>
		extends DisplayPart<J>
{
	public ContactUsPart()
	{
		addClass("d-flex flex-row my-flex-container");
		BSCardBody<?> body = addCardBody();
		body.addClass(Align_Between);
		body.addClass("row");
		body.add(buildIcon(facebook_square, "https://www.facebook.com/JWebMP/", "FaceBook"));
		body.add(buildIcon(twitter_square, "https://twitter.com/jwebmp", "Twitter"));
		body.add(buildIcon(youtube_square, "https://www.youtube.com/channel/UCKmp3cltVruaBZtGU5VH_Lg", "YouTube"));
		body.add(buildIcon(envelope_square, "mailto:support@jwebmp.com", "Email"));

		addStyle("margin-bottom:1rem;");
	}

	private Link buildIcon(IFontAwesomeIcon icon, String link, String title)
	{
		Link actualLink = new Link(link, "_blank");
		BSButton button = new BSButton<>().setText(FontAwesome.icon(icon, FontAwesomeSizes.$2x)
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
