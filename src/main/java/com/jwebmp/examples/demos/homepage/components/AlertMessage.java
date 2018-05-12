package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.plugins.bootstrap4.alerts.BSAlert;
import com.jwebmp.plugins.bootstrap4.alerts.BSAlertOptions;
import com.jwebmp.plugins.fontawesome.FontAwesome;
import lombok.Data;
import com.jwebmp.plugins.bootstrap4.alerts.BSAlert;
import com.jwebmp.plugins.bootstrap4.alerts.BSAlertOptions;
import com.jwebmp.plugins.fontawesome.FontAwesome;

@Data
public class AlertMessage
		extends BSAlert<AlertMessage>
{
	private FontAwesome icon;

	public AlertMessage(String text, BSAlertOptions style)
	{
		this(text);
		addClass(style);
	}

	public AlertMessage(String text)
	{
		setID("alert-display");
		setText(text);
		addClass("fade-in");
		setSuccess(true);

		if (text == null)
		{
			addStyle("display:none;");
		}
	}
}
