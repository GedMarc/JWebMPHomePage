package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.SmallText;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.plugins.bootstrap4.options.BSColoursOptions;
import org.apache.commons.lang3.StringEscapeUtils;
import com.jwebmp.FileTemplates;
import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.SmallText;
import com.jwebmp.examples.demos.homepage.components.PrettyInverseButton;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.google.sourceprettify.SourceCodePrettifyThemes;

import static com.jwebmp.plugins.bootstrap4.options.BSColoursOptions.Text_White;

public class MavenPomPart<J extends MavenPomPart<J>>
		extends DivSimple<J>
{
	public MavenPomPart()
	{
		add("<strong>0.35.0.1-SNAPSHOT is the latest build</strong>").addClass(BSColoursOptions.Text_White);

		JQSourceCodePrettify<?> prettifyPom = new JQSourceCodePrettify<>();
		prettifyPom.setSourceCodeLanguage(SourceCodeLanguages.XML);
		prettifyPom.setTheme(SourceCodePrettifyThemes.Sons_Of_Obsidian_Fixed_BG);
		prettifyPom.setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate(HomePage.class, "PomDependency", "pomdependency.txt", true)
		                                                               .toString()));
		add(prettifyPom);
		prettifyPom.addStyle("background:black;");


		add("<strong>Snapshots are available at the open repository</strong>");

		JQSourceCodePrettify<?> prettifyPomRepository = new JQSourceCodePrettify<>();
		prettifyPomRepository.setSourceCodeLanguage(SourceCodeLanguages.XML);
		prettifyPomRepository.setTheme(SourceCodePrettifyThemes.Sons_Of_Obsidian_Fixed_BG);
		prettifyPomRepository.setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate(HomePage.class, "PomRepository", "pomrepository.txt", true)
		                                                                         .toString()));
		add(prettifyPomRepository);
		prettifyPomRepository.addStyle("background:black;");

		add(new PrettyInverseButton<>("https://www.jwebmp.com/artifactory").setText("Visit Artifactory Repository"));
		add(new PrettyInverseButton<>("https://www.jwebswing.com/artifactory").setText("Fork Standalone HelloWorld"));

		add(new SmallText<>("<br/>&nbsp;*0.0.0_0 is the nightly build"));
	}

}
