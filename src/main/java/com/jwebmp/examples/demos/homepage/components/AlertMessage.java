package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.plugins.bootstrap4.alerts.BSAlert;
import com.jwebmp.plugins.bootstrap4.alerts.BSAlertOptions;
import com.jwebmp.plugins.fontawesome.FontAwesome;

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
		setDark(true);
		add(createDismissButton());

		if (text == null)
		{
			addStyle("display:none;");
		}
	}

	public FontAwesome getIcon()
	{
		return icon;
	}

	public void setIcon(FontAwesome icon)
	{
		this.icon = icon;
	}
}
