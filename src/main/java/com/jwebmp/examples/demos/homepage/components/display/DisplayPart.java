package com.jwebmp.examples.demos.homepage.components.display;

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.attributes.LinkAttributes;
import com.jwebmp.core.htmlbuilder.css.colours.ColourNames;
import com.jwebmp.examples.demos.homepage.components.AlertMessage;
import com.jwebmp.plugins.bootstrap4.alerts.BSAlert;
import com.jwebmp.plugins.bootstrap4.cards.BSCard;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.cards.parts.styles.BSCardButtonDarkOutline;
import com.jwebmp.plugins.bootstrap4.options.BSDisplayOptions;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.FontAwesomeList;
import com.jwebmp.plugins.fontawesome5.IFontAwesomeIcon;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;
import com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.options.JSTreeNodeOptions;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;
import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.guicedee.guicedinjection.json.StaticStrings.CHAR_DOT;
import static com.guicedee.guicedinjection.json.StaticStrings.CHAR_UNDERSCORE;
import static com.jwebmp.core.utilities.StaticStrings.*;
import static com.jwebmp.plugins.bootstrap4.alerts.BSAlertOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSBorderOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

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

	protected BSAlert addWhiteAlert(String text)
	{
		return new AlertMessage(text, Alert_Dark).addClass(Bg_Light)
		                                         .setAddDismissButton(false)
		                                         .setID("useCodeIconsAM");

	}

	protected BSAlert addBlueAlert(String text)
	{
		return new AlertMessage(text, Alert_Dark).addClass(Bg_Dark)
		                                         .addClass(Border_Primary)
		                                         .addClass(ColourNames.White.toString())
		                                         .addStyle("color", "white")
		                                         .setAddDismissButton(false)
		                                         .setID("useCodeIconsAM");
	}

	public BSCardBody getDefaultBody()
	{
		BSCardBody<?> all = new BSCardBody<>();
		all.addClass(Row);
		all.addClass(Bg_Dark);
		all.addStyle("display:grid;overflow-y:auto;padding:0px;");
		return all;
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

	protected ComponentHierarchyBase buildGoToSource(Class classToSendTo)
	{
		return buildGoToSource("View this pages source code", classToSendTo);
	}

	protected ComponentHierarchyBase buildGoToSource(String title, Class... classToSendTo)
	{
		List<ComponentHierarchyBase> allButtons = new ArrayList<>();
		for (Class aClass : classToSendTo)
		{
			String addressToUse = aClass.getCanonicalName()
			                            .replaceAll("\\.", "\\/")
			                      + ".java";
			BSCardButtonDarkOutline button = buildSourceButton(aClass, title, addressToUse);
			allButtons.add(button);
		}
		return allButtons.get(0)
		                 .addClass(BSDisplayOptions.Block);
	}

	private BSCardButtonDarkOutline buildSourceButton(Class classFor, String title, String addressToUse)
	{
		BSCardButtonDarkOutline<?> card = new BSCardButtonDarkOutline<>();
		card.addStyle("background-color", "#3d4853")
		    .addStyle("color", "#3bafda");
		card.addClass("d-block");
		card.setText(title + HTML_TAB + FontAwesome.icon(FontAwesomeIcons.external_link_alt, FontAwesomeStyles.Light)
		                                           .toString(0));

		card.addAttribute(LinkAttributes.HRef.toString(), "https://github.com/GedMarc/JWebMPHomePage/blob/master/src/main/java/" + addressToUse);
		card.addAttribute(LinkAttributes.Target.toString(), "_blank");

		return card;
	}

	protected ComponentHierarchyBase buildGoToSource(Class clazz, Class... classToSendTo)
	{
		List<Class> clazzes = new ArrayList<>(Arrays.asList(classToSendTo));
		clazzes.add(0, clazz);
		List<ComponentHierarchyBase> allButtons = new ArrayList<>();
		for (Class aClass : clazzes)
		{
			String addressToUse = aClass.getCanonicalName()
			                            .replaceAll("\\.", "\\/")
			                      + ".java";
			BSCardButtonDarkOutline button = buildSourceButton(aClass, "View Source [" + aClass.getSimpleName() + "]", addressToUse);
			allButtons.add(button);
		}
		DivSimple<?> row = new DivSimple<>().addClass(Row)
		                                    .setID("buttonsRow")
		                                    .addClass(BSDisplayOptions.Flex)
		                                    .addClass("justify-content-around");
		for (ComponentHierarchyBase allButton : allButtons)
		{
			allButton.addClass("flex-fill");
			allButton.addStyle("height", "45px");
			allButton.removeClass("d-block");
			row.add(allButton);
		}
		return row;

	}

	public Div<?, ?, ?, ?, ?> getCodeBlockJava(Class reference, String fileName)
	{
		Div d = new Div<>().addClass(Col_12);
		d.setID("JavaCodeBlock");
		d.addClass("ng-non-bindable");
		addSourceToContainer(reference, fileName, Java, d);
		return d;
	}

	protected JQSourceCodePrettify addSourceToContainer(Class reference, String filename, SourceCodeLanguages language, Div container)
	{
		StringBuilder contents = FileTemplates.getFileTemplate(reference, reference.getName() + filename, filename);
		if(contents != null)
		{
			JQSourceCodePrettify prettify = new JQSourceCodePrettify<>().addStyle("background:#333;")
			                                                            .setSourceCodeLanguage(language)
			                                                            .setText(StringEscapeUtils.escapeHtml4(contents.toString()));
			container.add(prettify);
			return prettify;
		}
		return null;
	}
}
