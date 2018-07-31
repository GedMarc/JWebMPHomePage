package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.plugins.bootstrap4.alerts.BSAlert;
import com.jwebmp.plugins.bootstrap4.alerts.BSAlertOptions;
import com.jwebmp.plugins.fontawesome.FontAwesome;

public class AlertMessage
		extends BSAlert<AlertMessage>
{
	private FontAwesome icon;
	private boolean addDismissButton = true;

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

		if (text == null)
		{
			addStyle("display:none;");
		}
	}

	@Override
	public void preConfigure()
	{
		if (!isConfigured())
		{
			if (isAddDismissButton())
			{
				add(createDismissButton());
			}
		}
		super.preConfigure();
	}

	public FontAwesome getIcon()
	{
		return icon;
	}

	public void setIcon(FontAwesome icon)
	{
		this.icon = icon;
	}

	public boolean isAddDismissButton()
	{
		return addDismissButton;
	}

	public AlertMessage setAddDismissButton(boolean addDismissButton)
	{
		this.addDismissButton = addDismissButton;
		return this;
	}
}
