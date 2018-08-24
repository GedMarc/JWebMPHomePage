package com.jwebmp.examples.demos.homepage.components.display;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.AlertMessage;
import com.jwebmp.plugins.bootstrap4.alerts.BSAlert;
import com.jwebmp.plugins.bootstrap4.cards.BSCard;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.FontAwesomeList;
import com.jwebmp.plugins.fontawesome5.IFontAwesomeIcon;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import io.undertow.util.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.jwebmp.plugins.bootstrap4.alerts.BSAlertOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.*;

public class DisplayPart<J extends DisplayPart<J>>
		extends BSCard<J>
{
	public DisplayPart()
	{
	}

	public DisplayPart(boolean inverse)
	{
		super(inverse);
	}

	protected FontAwesomeList addIconList(FontAwesomeList list, String text, IFontAwesomeIcon icon, String color)
	{
		list.addItem(text, new FontAwesome<>().setIcon(icon));
		return list;
	}

	protected void addSourceToContainer(Class reference, String filename, SourceCodeLanguages language, Div container)
	{
		try (InputStream stream = reference.getResourceAsStream(filename))
		{
			String file = FileUtils.readFile(stream);
			file = file.replace('\t', ' ')
			           .replace("    ", "  ");
			container.add(new JQSourceCodePrettify<>().addStyle("background:#333;")
			                                          .setSourceCodeLanguage(language)
			                                          .setText(StringEscapeUtils.escapeHtml4(file)));
		}
		catch (Exception e)
		{
			Logger.getLogger("DisplayScreen")
			      .log(Level.SEVERE, "Add source code error", e);
		}
	}

	protected BSAlert addWhiteAlert(String text)
	{
		return new AlertMessage(text, Alert_Dark).addClass(Bg_Light)
		                                         .setAddDismissButton(false)
		                                         .setID("useCodeIconsAM");

	}
}
