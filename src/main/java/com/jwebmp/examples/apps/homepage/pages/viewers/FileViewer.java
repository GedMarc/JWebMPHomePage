package com.jwebmp.examples.apps.homepage.pages.viewers;

import com.jwebmp.core.utilities.EscapeChars;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.google.sourceprettify.SourceCodePrettifyThemes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.jwebmp.plugins.bootstrap4.options.BSTypographyOptions.Text_Left;

public class FileViewer extends JQSourceCodePrettify<FileViewer>
{
	private static final Map<String, String> sourceCodes = new ConcurrentHashMap<>();
	private final String textContents;
	private final SourceCodeLanguages language;
	
	public FileViewer(String textContents, SourceCodeLanguages language)
	{
		this.textContents = EscapeChars.forHTML(textContents);
		this.language = language;
		addStyle("max-height", "400px");
		addStyle("overflow-y", "auto");
		addStyle("background-color", "#111");
		addClass(Text_Left);
	}
	
	@Override
	public void init()
	{
		if (!isInitialized())
		{
			String sourceCode = "";
			if (sourceCodes.containsKey(textContents))
			{
				sourceCode = sourceCodes.get(textContents);
			}
			else
			{
				sourceCode = textContents;
				sourceCodes.put(textContents, sourceCode);
			}
			setText(sourceCode);
			setSourceCodeLanguage(language);
			setTheme(SourceCodePrettifyThemes.Sons_Of_Obsidian_Fixed_BG);
			setShowLineNums(true);
		}
		super.init();
	}
}
