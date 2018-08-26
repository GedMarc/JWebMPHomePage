package com.jwebmp.examples.demos.homepage.components.display;

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.AlertMessage;
import com.jwebmp.plugins.bootstrap4.alerts.BSAlert;
import com.jwebmp.plugins.bootstrap4.cards.BSCard;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.FontAwesomeList;
import com.jwebmp.plugins.fontawesome5.IFontAwesomeIcon;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.options.JSTreeNodeOptions;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;
import org.apache.commons.text.StringEscapeUtils;

import static com.jwebmp.core.utilities.StaticStrings.*;
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
		StringBuilder contents = FileTemplates.getFileTemplate(reference, reference.getName() + filename, filename);
		container.add(new JQSourceCodePrettify<>().addStyle("background:#333;")
		                                          .setSourceCodeLanguage(language)
		                                          .setText(StringEscapeUtils.escapeHtml4(contents.toString())));
	}

	protected BSAlert addWhiteAlert(String text)
	{
		return new AlertMessage(text, Alert_Dark).addClass(Bg_Light)
		                                         .setAddDismissButton(false)
		                                         .setID("useCodeIconsAM");

	}

	protected Div addServiceTree(String serviceName)
	{
		Div d = new Div();
		JSTree<?> directoryStructureExample = new JSTree<>();
		directoryStructureExample.setTheme(new JSTreeDefaultDarkTheme());

		JSTreeListItem<?> rootItem = new JSTreeListItem<>("src")
				                             .setOptions(new JSTreeNodeOptions<>().setDisabled(false)
				                                                                  .setIcon("fal fa-caret-circle-down")
				                                                                  .setOpened(true));

		JSTreeListItem<?> folder1 = new JSTreeListItem<>("META-INF", new JSTreeNodeOptions<>()
				                                                             .setIcon("fal fa-folder-open")
				                                                             .setOpened(true));

		JSTreeListItem<?> folder2 = new JSTreeListItem<>("services", new JSTreeNodeOptions<>()
				                                                             .setIcon("fal fa-folder-open")
				                                                             .setOpened(true));
		JSTreeListItem<?> file1 = new JSTreeListItem<>(serviceName, new JSTreeNodeOptions<>()
				                                                            .setIcon("fal fa-file")
		);
		folder2.add(file1);
		folder1.add(folder2);

		rootItem.add(folder1);

		directoryStructureExample.addRoot(rootItem);
		directoryStructureExample.setID("directory-structure-example_" + serviceName.replace(CHAR_DOT, CHAR_UNDERSCORE));

		d.add(directoryStructureExample);
		return d;

	}
}
