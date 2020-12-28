package com.jwebmp.examples.apps.homepage.pages.viewers;

import com.google.common.base.Strings;
import com.jwebmp.core.utilities.EscapeChars;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodePrettifyThemes;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import static com.jwebmp.plugins.bootstrap4.options.BSTypographyOptions.Text_Left;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.Java;
import static java.nio.charset.StandardCharsets.UTF_8;

public class SourceCodeViewer extends JQSourceCodePrettify<SourceCodeViewer>
{
	private static final Map<Class<?>, String> sourceCodes = new ConcurrentHashMap<>();
	private final Class<?> clazz;
	
	public SourceCodeViewer(Class<?> clazz)
	{
		this.clazz = clazz;
		addStyle("max-height", "400px");
		addStyle("overflow-y", "auto");
		addStyle("background-color", "#111");
		addClass(Text_Left);
	}
	
	String remove3ConsecutiveDuplicates(String str)
	{
		Vector<Character> v = new Vector<>();
		for (int i = 0; i < str.length(); ++i)
		{
			v.add(str.charAt(i));
			if (v.size() > 2 && str.charAt(i) == ' ')
			{
				int sz = v.size();
				// removing three consecutive duplicates
				if (v.get(sz - 1) == v.get(sz - 2) &&
						v.get(sz - 2) == v.get(sz - 3))
				{
					v.setSize(sz - 3); // Removing three characters
					v.add(str.charAt(i));
				}
			}
		}
		
		// printing the string final string
		StringBuilder out = new StringBuilder();
		for (Character character : v)
		{
			out.append(character);
		}
		return out.toString();
	}
	
	@Override
	public void init()
	{
		if (!isInitialized())
		{
			String sourceCode = "";
			if (sourceCodes.containsKey(clazz))
			{
				sourceCode = sourceCodes.get(clazz);
			}
			else
			{
				try
				{
					String javaFile = IOUtils.toString(clazz.getResourceAsStream(clazz.getSimpleName() + ".java"), UTF_8);
					String line = "";
					StringBuilder output = new StringBuilder();
					try (StringReader sr = new StringReader(javaFile);
					     LineNumberReader lines = new LineNumberReader(sr))
					{
						while ((line = lines.readLine()) != null)
						{
							if (line.startsWith("import ") || Strings.isNullOrEmpty(line))
							{
								continue;
							}
							output.append(remove3ConsecutiveDuplicates(line))
							      .append("\n");
						}
					}
					sourceCode = EscapeChars.forHTML(output.toString()
					                                       .replace("\t", "  "));
					sourceCodes.put(clazz, sourceCode);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			setText(sourceCode);
			setSourceCodeLanguage(Java);
			setTheme(SourceCodePrettifyThemes.Sons_Of_Obsidian_Fixed_BG);
			setShowLineNums(true);
		}
		super.init();
	}
}
