package com.jwebmp.examples.demos.homepage.display.jqueryui;

import com.jwebmp.FileTemplates;
import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.Link;
import com.jwebmp.base.html.Paragraph;
import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.MintonPanel;
import com.jwebmp.examples.demos.homepage.display.OuterLayout;
import com.jwebmp.examples.demos.homepage.display.jqueryui.demos.JQUIDraggableDemoScreen;
import com.jwebmp.examples.demos.homepage.display.menu.ChangeScreenEvent;
import com.jwebmp.examples.demos.homepage.entities.Plugins;
import com.jwebmp.examples.demos.homepage.entities.Plugins_;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.jqmetro.metro.JQMetroTiles;
import com.jwebmp.plugins.jqmetro.metro.enumerations.TileAccentThemes;
import com.jwebmp.plugins.jqmetro.metro.enumerations.TileCount;
import com.jwebmp.plugins.jqmetro.metro.tiles.StaticTile;
import com.jwebmp.plugins.jqmetro.metro.tiles.TileFace;
import com.jwebmp.plugins.jquerylayout.layout.JQLayoutArea;
import com.jwebmp.plugins.jquerylayout.layout.events.JQLayoutSlideCloseLayoutDivFeature;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import za.co.mmagon.entityassist.enumerations.Operand;
import za.co.mmagon.guiceinjection.GuiceContext;

import java.util.Optional;

public class JQueryUIDemoScreen
		extends DisplayScreen
{
	private String pluginName = "jQuery UI";

	public JQueryUIDemoScreen()
	{
		super("JQuery UI");
		OuterLayout outerLayout = GuiceContext.getInstance(OuterLayout.class);
		addFeature(new JQLayoutSlideCloseLayoutDivFeature(outerLayout.getPane(JQLayoutArea.East)));
	}

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(BSContainerOptions.Container_Fluid);
		BSRow descriptionRow = new BSRow();
		Optional<Plugins> oPlugin = new Plugins().builder()
		                                         .where(Plugins_.pluginName, Operand.Equals, pluginName)
		                                         .get();
		if (oPlugin.isPresent())
		{
			descriptionRow.add(new Paragraph<>(oPlugin.get()
			                                          .getPluginDescription()).addClass("col-12")
			                                                                  .addStyle("padding-left:20px;"));
		}
		container.add(descriptionRow);

		BSRow row = new BSRow();


		BSColumn fullColumn = new BSColumn(BSColumnOptions.Col_12, BSColumnOptions.Col_12);
		BSColumn rightColumn = new BSColumn(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12);

		BSColumn leftColumnTop = new BSColumn(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12);
		BSColumn rightColumnTop = new BSColumn(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12);

		DivSimple<?> mavenDisplayDiv = buildDependencyInformation(StringEscapeUtils.escapeHtml4(
				FileTemplates.getFileTemplate(JQueryUIDemoScreen.class, "jquipom.txt", "jquipom.txt")
				             .toString()));
		leftColumnTop.add(new MintonPanel("Dependency Information", "bg-pink", mavenDisplayDiv).addClass("col-12"));

		DivSimple artiInfo = buildArtifactInformation("jQuery UI ");
		rightColumnTop.add(new MintonPanel("Artifact Information", "bg-pink", artiInfo).addClass("col-12"));


		fullColumn.add(sourceCodeContentPanel());

		row.add(fullColumn);
		row.add(rightColumn);

		BSRow topRow = new BSRow();
		topRow.add(leftColumnTop);
		topRow.add(rightColumnTop);

		container.add(topRow);
		container.add(row);

		return container;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Web Frameworks"));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("JQuery UI"));
		return crumbs;
	}

	private DivSimple<?> buildDependencyInformation(String depedencyInfo)
	{
		DivSimple<?> mavenDisplayDiv = new DivSimple<>();
		JQSourceCodePrettify prettify = new JQSourceCodePrettify();
		prettify.setSourceCodeLanguage(SourceCodeLanguages.XML);
		prettify.setShowLineNums(false);
		prettify.setText(depedencyInfo);
		mavenDisplayDiv.add(prettify);
		return mavenDisplayDiv;
	}

	private DivSimple<?> buildArtifactInformation(String pluginName)
	{
		DivSimple<?> mavenDisplayDiv = new DivSimple<>();

		Optional<Plugins> oPlugin = new Plugins().builder()
		                                         .where(Plugins_.pluginName, Operand.Equals, pluginName)
		                                         .get();
		if (oPlugin.isPresent())
		{
			Plugins plugin = oPlugin.get();

			BSRow versionRow = new BSRow();
			versionRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12).setText("Source Widget Version"));
			versionRow.add(
					new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12).add(new Link<>(plugin.getPluginSourceURL(), "_blank").setText(plugin.getPluginVersion())));
			mavenDisplayDiv.add(versionRow);

			BSRow donationRow = new BSRow();
			donationRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12).setText("Donate to the Widget Devs"));
			donationRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12).add(new Link<>(plugin.getPluginDonateUrl(), "_blank").setText("Yes I'll Support!")));
			mavenDisplayDiv.add(donationRow);

			BSRow teamcityRow = new BSRow();
			teamcityRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12).setText("TeamCity CI"));
			teamcityRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12).add(new Link<>(plugin.getPluginTeamCityUrl(), "_blank").setText("Go To TeamCity")));
			mavenDisplayDiv.add(teamcityRow);

			BSRow jiraRow = new BSRow();
			jiraRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12).setText("Jira Project Tracking"));
			jiraRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12).add(new Link<>(plugin.getPluginJiraUrl(), "_blank").setText("Go To Jira")));
			mavenDisplayDiv.add(jiraRow);

			BSRow sonarRow = new BSRow();
			sonarRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12).setText("SonarQube Quality Gate"));
			sonarRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12).add(new Link<>(plugin.getPluginSonarUrl(), "_blank").setText("Go To SonarQube")));
			mavenDisplayDiv.add(sonarRow);

			BSRow artifactoryRow = new BSRow();
			artifactoryRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12).setText("Artifactory Direct Download"));
			artifactoryRow.add(
					new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_12).add(new Link<>(plugin.getPluginArtifactoryUrl(), "_blank").setText("Go To Artifactory")));
			mavenDisplayDiv.add(artifactoryRow);
		}
		else
		{
			add("Plugin name wrong for artifact information");
		}

		return mavenDisplayDiv;
	}

	private SourceCodeContentPanel sourceCodeContentPanel()
	{
		DivSimple<?> homeScreen = new DivSimple<>();
		SourceCodeContentPanel sc = new SourceCodeContentPanel<>("JQuery UI Source Code Panel", homeScreen).setShowHeader(false)
		                                                                                                   .setShowCode(false)
		                                                                                                   .setShowRefresh(true);
		JQMetroTiles tiles = new JQMetroTiles();
		tiles.setTheme(TileAccentThemes.Magenta);
		tiles.setTileCount(TileCount.ten);
		tiles.getOptions()
		     .setInitDelay(0);
		tiles.addStyle("width", "inherit");

		tiles.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Feature")
		                                                       .addParagraph("Draggable<br/><br/>Allow elements to be moved using the mouse"))
		                              .addStyle("cursor", "pointer")
		                              .addEvent(new ChangeScreenEvent(new JQUIDraggableDemoScreen(), "p=" + pluginName).setID(JQUIDraggableDemoScreen.class.getCanonicalName()
		                                                                                                                                                   .replace('.', '_')))
		                              .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));
		tiles.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Feature")
		                                                       .addParagraph("Droppable<br/><br/>Create targets for draggable elements."))
		                              .addStyle("cursor", "pointer")
		                              .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));
		tiles.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Feature")
		                                                       .addParagraph("Resizable<br/><br/>Change the size of an element using the mouse."))
		                              .addStyle("cursor", "pointer")
		                              .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));
		tiles.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Feature")
		                                                       .addParagraph("Selectable<br/><br/>Use the mouse to select elements, individually or in a group."))
		                              .addStyle("cursor", "pointer")
		                              .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));
		tiles.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Feature")
		                                                       .addParagraph("Sortable<br/><br/>Reorder elements in a list or grid using the mouse."))
		                              .addStyle("cursor", "pointer")
		                              .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));

		sc.getContent()
		  .add(tiles);


		JQMetroTiles components = new JQMetroTiles();

		components.setTheme(TileAccentThemes.Emerald);
		components.setTileCount(TileCount.ten);
		components.getOptions()
		          .setInitDelay(0);
		components.addStyle("width", "inherit");

		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph("Accordion<br/><br/>Reorder elements in a list or grid using the mouse."))
		                                   .addStyle("cursor", "pointer")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));

		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph(
				                                                            "Auto Complete<br/><br/>Enables users to quickly find and select from a pre-populated list of values as they type, leveraging searching and filtering."))
		                                   .addStyle("cursor", "pointer")
		                                   .addAttribute("data-bounce", "true")
		                                   .addClass("two-wide")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));

		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph(
				                                                            "Button<br/><br/>Enhances standard form elements like buttons, inputs and anchors to themeable buttons with appropriate hover and active styles."))
		                                   .addStyle("cursor", "pointer")
		                                   .addClass("two-wide")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));


		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph("Control Group<br/><br/>Groups multiple buttons and other widgets into one visual set."))
		                                   .addStyle("cursor", "pointer")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));


		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph(
				                                                            "Checkbox Radio<br/><br/>Enhances standard checkbox and radio input element to themeable buttons with appropriate hover and active styles."))
		                                   .addStyle("cursor", "pointer")
		                                   .addClass("two-wide")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));

		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph("Date Picker<br/><br/>Select a date from a popup or inline calendar"))
		                                   .addStyle("cursor", "pointer")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));


		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph("Dialog<br/><br/>Open content in an interactive overlay."))
		                                   .addStyle("cursor", "pointer")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));


		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph("Menu<br/><br/>Themeable menu with mouse and keyboard interactions for navigation."))
		                                   .addStyle("cursor", "pointer")
		                                   .addClass("two-wide")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));


		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph("Progress Bar<br/><br/>Display status of a determinate or indeterminate process."))
		                                   .addStyle("cursor", "pointer")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));


		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph(
				                                                            "Select Menu<br/><br/>Duplicates and extends the functionality of a native HTML select element to overcome the limitations of the native control."))
		                                   .addStyle("cursor", "pointer")
		                                   .addClass("two-wide")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));


		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph("Slider<br/><br/>Drag a handle to select a numeric value."))
		                                   .addStyle("cursor", "pointer")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));


		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph(
				                                                            "Spinner<br/><br/>Enhance a text input for entering numeric values, with up/down buttons and arrow key handling."))
		                                   .addStyle("cursor", "pointer")
		                                   .addClass("two-wide")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));


		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph(
				                                                            "Tabs<br/><br/>A single content area with multiple panels, each associated with a header in a list."))
		                                   .addStyle("cursor", "pointer")
		                                   .addClass("two-wide")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));

		components.addTile(new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                            .addParagraph("Tooltip<br/><br/>Customizable, themeable tooltips, replacing native tooltips."))
		                                   .addStyle("cursor", "pointer")
		                                   .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));


		sc.getContent()
		  .add(components);

		return sc;
	}
}
