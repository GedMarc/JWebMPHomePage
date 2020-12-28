package com.jwebmp.examples.apps.homepage.pages.welcome;

import com.jwebmp.plugins.themes.mintontheme.faq.FAQCardItemLayout;
import com.jwebmp.examples.apps.homepage.pages.viewers.FileViewer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.XML;
import static java.nio.charset.StandardCharsets.UTF_8;

public class WelcomePageMavenSection extends FAQCardItemLayout
{
	public WelcomePageMavenSection()
	{
		try
		{
			addItem("B", "BOM", new FileViewer(IOUtils.toString(WelcomePageMavenSection.class.getResourceAsStream("jwebmp_bom_maven.xml"), UTF_8), XML));
			addItem("J", "Javax Namespace", new FileViewer(IOUtils.toString(WelcomePageMavenSection.class.getResourceAsStream("jwebmp_javax_maven.xml"), UTF_8), XML));
			
			addItem("11", "JDK 11", new FileViewer(IOUtils.toString(WelcomePageMavenSection.class.getResourceAsStream("jre11_maven.xml"), UTF_8), XML));
			addItem("14", "JDK 14", new FileViewer(IOUtils.toString(WelcomePageMavenSection.class.getResourceAsStream("jre14_maven.xml"), UTF_8), XML));
			addItem("15", "JDK 15", new FileViewer(IOUtils.toString(WelcomePageMavenSection.class.getResourceAsStream("jre15_maven.xml"), UTF_8), XML));
			
			addItem("8", "JDK 8", new FileViewer(IOUtils.toString(WelcomePageMavenSection.class.getResourceAsStream("jre8_maven.xml"), UTF_8), XML));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
