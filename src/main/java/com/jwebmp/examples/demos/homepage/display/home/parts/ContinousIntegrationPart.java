package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.plugins.bootstrap4.buttons.BSButton;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonLightOutline;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonPrimaryOutline;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonSecondaryOutline;
import com.jwebmp.plugins.bootstrap4.options.BSAlignmentHorizontalOptions;
import com.jwebmp.plugins.fontawesome.FontAwesome;
import com.jwebmp.plugins.fontawesome.FontAwesomeIcons;

import static com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSAlignmentHorizontalOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSMarginOptions.*;
import static com.jwebmp.plugins.fontawesome.FontAwesomeProperties.*;

public class ContinousIntegrationPart
		extends DivSimple<ContinousIntegrationPart>
{
	public ContinousIntegrationPart()
	{
		addClass("d-flex flex-row my-flex-container");
		addClass(Align_Between);

		add(buildLink(new BSButtonLightOutline<>().setText(
				FontAwesome.icon(FontAwesomeIcons.github, $2x).toString(0) + "<br/>Github")
		                                            .addClass(MarginLeft_1)
		                                            .addClass(MarginTop_1)
		                                            .addClass(Align_Center), "https://github.com/GedMarc?tab=repositories"));

		add(buildLink(new BSButtonLightOutline<>().setText(
				FontAwesome.icon(FontAwesomeIcons.train, $2x).toString(0) + "<br/>Jira")
		                                                         .addClass(MarginLeft_1)
		                                                         .addClass(MarginTop_1)
		                                                         .addClass(Align_Center), "https://jwebmp.com/jira/"));
		add(buildLink(new BSButtonLightOutline<>().setText(
				FontAwesome.icon(FontAwesomeIcons.cog, $2x).toString(0) + "<br/>TeamCity")
		                                            .addClass(MarginLeft_1)
		                                            .addClass(MarginTop_1)
		                                            .addClass(Align_Center), "https://jwebmp.com/teamcity/"));
		add(buildLink(new BSButtonLightOutline<>().setText(
				FontAwesome.icon(FontAwesomeIcons.check_square, $2x).toString(0) + "<br/>Code Quality")
		                                          .addClass(MarginLeft_1)
		                                          .addClass(MarginTop_1)
		                                          .addClass(Align_Center), "https://jwebmp.com/sonar/"));
	}

	private Link buildLink(BSButton button,String url)
	{
		Link link = new Link<>(url,"_blank").add(button);
		return link;
	}
}
