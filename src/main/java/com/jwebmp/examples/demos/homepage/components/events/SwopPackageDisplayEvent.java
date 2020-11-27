package com.jwebmp.examples.demos.homepage.components.events;

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.events.click.ClickAdapter;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import org.apache.commons.text.StringEscapeUtils;

import static com.guicedee.guicedinjection.json.StaticStrings.CHAR_DOT;
import static com.guicedee.guicedinjection.json.StaticStrings.CHAR_UNDERSCORE;
import static com.jwebmp.core.utilities.StaticStrings.*;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_12;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.Java;

public class SwopPackageDisplayEvent
		extends ClickAdapter
{
	public SwopPackageDisplayEvent()
	{
		super(null);
	}

	public SwopPackageDisplayEvent(Class classRef, String packageName, String pluginName)
	{
		super(null);
		setID(classRef.getCanonicalName()
		              .replace(CHAR_DOT, CHAR_UNDERSCORE) +
		      "||" + pluginName.replace(CHAR_DOT, CHAR_UNDERSCORE) +
		      "||" + packageName.replace(CHAR_DOT, CHAR_UNDERSCORE));
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		String[] splits = call.getEventId()
		                      .split("\\|\\|");
		String screen = splits[0]
				                .replace(CHAR_UNDERSCORE, CHAR_DOT);
		Class classRef = GuiceContext.instance()
		                             .getScanResult()
		                             .loadClass(screen, false);
		String pluginName = splits[1].replace(CHAR_UNDERSCORE, CHAR_DOT);
		String packageName = splits[2].replace(CHAR_UNDERSCORE, CHAR_DOT);

		DefaultPackageAPI newPackageAPI = new DefaultPackageAPI(packageName, classRef, pluginName, true, true);
		response.addComponent(newPackageAPI);
		Div d = new Div<>().addClass(Col_12);
		d.setID("JavaCodeBlock");
		try
		{
			Class clazz = Class.forName(screen);
			//Send back the code option
			StringBuilder contents = FileTemplates.getFileTemplate(clazz, screen + pluginName, pluginName);
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

	@Override
	public void preConfigure()
	{
		super.preConfigure();
	}
}
