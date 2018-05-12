package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.base.html.Div;
import com.jwebmp.base.html.DivSimple;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.MintonCheckBox;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;

import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.Bg_Secondary;

public class ReasonsWhyPart
		extends DivSimple

{
	public ReasonsWhyPart()
	{
		DivSimple<?> sourceExampleDiv = new DivSimple();
		SourceCodeContentPanel<?> portlet = new SourceCodeContentPanel<>("The Difference Is In The Code", DisplayCodeParts.ReasonsWhy, sourceExampleDiv);
		portlet.setBgColor(Bg_Secondary);
		portlet.setShowCode(true);
		portlet.setShowHeader(true);

		sourceExampleDiv.add(buildCheckbox("100% Pure JDK 8", true, "checkbox-primary"));
		sourceExampleDiv.add(buildCheckbox("Non-Invasive. <br/>Servlets, JSPs,JSF continue as normal", true, "checkbox-success"));
		sourceExampleDiv.add(buildCheckbox("EE7^, MicroProfile, Standalone. <br/>Migrate at your own Pace.", true, "checkbox-info"));

		sourceExampleDiv.add(buildCheckbox("Focused on Technology.<br/>Maintainability, and Scalability.", true, "checkbox-purple"));
		sourceExampleDiv.add(buildCheckbox("Speed Up Deliveries and Development<br/>The Code Always Comes First", true, "checkbox-pink"));
		sourceExampleDiv.add(buildCheckbox("Browser, Android, IOS, Windows Desktop and Universal Applications" + "<br/> <i>Cordova</i> Supported!", true, "checkbox-pink"));

		sourceExampleDiv.add(buildCheckbox("Testable. End To End. Out The Box.<br/> 100% UT and IT is stock standard", true, "checkbox-success"));
		sourceExampleDiv.add(buildCheckbox("Unbelievably Fast.<br/>Type Safe, Domain Driven", true, "checkbox-info"));

		sourceExampleDiv.add(buildCheckbox("Domain Event System <br/>Domain Event Stores", true, "checkbox-purple"));
		sourceExampleDiv.add(buildCheckbox("Open Unit Test </br>Open Quality Gate Infrastructure", true, "checkbox-pink"));
		sourceExampleDiv.add(buildCheckbox("Community Driven, Community Supported", true, "checkbox-inverse"));
		sourceExampleDiv.add(buildCheckbox("Open Project Management<br/>Contribute to the Architecture", true, "checkbox-dark"));
		add(portlet);
	}

	private Div buildCheckbox(String label, boolean checked, String clazz)
	{
		return (Div) new MintonCheckBox(checked, label, clazz).addClass("col-12");
	}
}
