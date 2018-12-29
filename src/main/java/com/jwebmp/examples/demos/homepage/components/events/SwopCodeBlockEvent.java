package com.jwebmp.examples.demos.homepage.components.events;

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.angular.directives.events.click.ClickEvent;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import org.apache.commons.text.StringEscapeUtils;

import static com.jwebmp.core.utilities.StaticStrings.*;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class SwopCodeBlockEvent
		extends ClickEvent
{
	public SwopCodeBlockEvent()
	{
		super(null);
	}

	public SwopCodeBlockEvent(Class classRef, String filename)
	{
		super(null);
		setID(classRef.getCanonicalName()
		              .replace(CHAR_DOT, CHAR_UNDERSCORE) + "||" + filename.replace(CHAR_DOT, CHAR_UNDERSCORE));
	}

	@Override
	public void preConfigure()
	{
		super.preConfigure();
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		String[] splits = call.getEventId()
		                      .split("\\|\\|");

		String screen = splits[0]
				                .replace(CHAR_UNDERSCORE, CHAR_DOT);
		Div d = new Div<>().addClass(Col_12);
		try
		{
			Class clazz = Class.forName(screen);
			String filename = splits[1].replace(CHAR_UNDERSCORE, CHAR_DOT);
			//Send back the code option

			d.setID("JavaCodeBlock");
			StringBuilder contents = FileTemplates.getFileTemplate(clazz, screen + filename, filename);
			d.add(new JQSourceCodePrettify<>().addStyle("background:#333;")
			                                  .setSourceCodeLanguage(Java)
			                                  .setText(StringEscapeUtils.escapeHtml4(contents.toString())));
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		response.addComponent(d);

	}
}
