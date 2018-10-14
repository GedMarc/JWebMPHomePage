package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.buttons.BSButton;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonLightOutline;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeBrandIcons;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;

import static com.jwebmp.plugins.bootstrap4.options.BSAlignmentHorizontalOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSMarginOptions.*;
import static com.jwebmp.plugins.fontawesome5.options.FontAwesomeSizes.*;

public class ContinousIntegrationPart
		extends DisplayPart<ContinousIntegrationPart>
{
	public ContinousIntegrationPart()
	{
		addClass("d-flex flex-row my-flex-container");
		BSCardBody<?> body = addCardBody();
		body.addClass("row");
		body.addClass(Align_Between);

		body.add(buildLink(new BSButtonLightOutline<>().setText(
				FontAwesome.icon(FontAwesomeBrandIcons.github, $2x)
				           .toString(0) + "<br/>Github")
		                                               .addClass(MarginLeft_1)
		                                               .addClass(MarginTop_1)
		                                               .addClass(Align_Center), "https://github.com/GedMarc?tab=repositories"));

		body.add(buildLink(new BSButtonLightOutline<>().setText(
				FontAwesome.icon(FontAwesomeIcons.train, $2x)
				           .toString(0) + "<br/>Jira")
		                                               .addClass(MarginLeft_1)
		                                               .addClass(MarginTop_1)
		                                               .addClass(Align_Center), "https://jwebmp.com/jira/"));
		body.add(buildLink(new BSButtonLightOutline<>().setText(
				FontAwesome.icon(FontAwesomeIcons.cog, $2x)
				           .toString(0) + "<br/>TeamCity")
		                                               .addClass(MarginLeft_1)
		                                               .addClass(MarginTop_1)
		                                               .addClass(Align_Center), "https://jwebmp.com/teamcity/"));
		body.add(buildLink(new BSButtonLightOutline<>().setText(
				FontAwesome.icon(FontAwesomeIcons.check_square, $2x)
				           .toString(0) + "<br/>SonarQube")
		                                               .addClass(MarginLeft_1)
		                                               .addClass(MarginTop_1)
		                                               .addClass(Align_Center), "https://jwebmp.com/sonar/"));
		addStyle("margin-bottom", "1rem");

		Link starLink = new Link("https://github.com/GedMarc/JWebMP/stargazers", "_blank");
		starLink.setText("Remember to Star This Project!");
		starLink.addStyle("color:white;")
		        .addStyle("position:absolute;")
		        .addStyle("top:0px;")
		        .addStyle("right:0px;");

		add(starLink.setTiny(true)
		            .toString(true));
	}

	private Link buildLink(BSButton button, String url)
	{
		Link link = new Link<>(url, "_blank").add(button);
		return link;
	}
}
