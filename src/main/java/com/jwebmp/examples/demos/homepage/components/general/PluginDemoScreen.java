package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.html.*;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.entityassist.enumerations.Operand;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.events.SwopObjectBrowserEvent;
import com.jwebmp.examples.demos.homepage.display.demos.jqui.demos.JQUIDraggableDemoScreen;
import com.jwebmp.examples.demos.homepage.display.menu.ChangeScreenEvent;
import com.jwebmp.examples.demos.homepage.entities.Plugins;
import com.jwebmp.examples.demos.homepage.entities.Plugins_;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.cards.layout.BSCardBox;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.metrojs.JQMetroTiles;
import com.jwebmp.plugins.metrojs.enumerations.TileAccentThemes;
import com.jwebmp.plugins.metrojs.enumerations.TileCount;
import com.jwebmp.plugins.metrojs.tiles.StaticTile;
import com.jwebmp.plugins.metrojs.tiles.TileFace;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class PluginDemoScreen
		extends DisplayScreen
{

	private final List<Div<?, ?, ?, ?, ?>> additionals = new ArrayList<>();
	private final List<Div<?, ?, ?, ?, ?>> additionalsRight = new ArrayList<>();

	boolean tileAdded = false;
	private BSColumn fullColumn = new BSColumn(Col_12, Col_12);
	private BSColumn rightColumn = new BSColumn(BSColumnOptions.Col_Md_6, Col_12);
	private BSColumn leftColumnTop = new BSColumn(BSColumnOptions.Col_Md_6, Col_12);
	private BSColumn rightColumnTop = new BSColumn(BSColumnOptions.Col_Md_6, Col_12);
	private String[] breadCrumbs;
	private DivSimple<?> mavenDisplayDiv;
	private DivSimple artiInfo;
	private String pluginName;
	private JQMetroTiles featureTiles = new JQMetroTiles();
	private JQMetroTiles componentTiles = new JQMetroTiles();

	private List<OptionsBrowser> optionBrowsers = new ArrayList<>();
	private List<BSRow> bottomRows = new ArrayList<>();

	public PluginDemoScreen(String pluginName, String... breadCrumbs)
	{
		this.pluginName = pluginName;
		this.breadCrumbs = breadCrumbs;

		featureTiles.setTheme(TileAccentThemes.Mauve);
		featureTiles.setTileCount(TileCount.ten);
		featureTiles.getOptions()
		            .setInitDelay(0);
		featureTiles.addStyle("width", "inherit");

		componentTiles.setTheme(TileAccentThemes.Blue);
		componentTiles.setTileCount(TileCount.ten);
		componentTiles.getOptions()
		              .setInitDelay(0);
		componentTiles.addStyle("width", "inherit");
	}

	public void addDiv(Div div)
	{
		additionals.add(div);
	}

	public StaticTile addComponentTile(String name, String description, Class<? extends JavaScriptPart> part)
	{
		StaticTile tile;
		componentTiles.add(tile = new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                                   .addParagraph(name + "<br/><br/>" + description))
		                                          .addStyle("cursor", "pointer")
		                                          .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));
		if (part != null)
		{
			tile.addEvent(new SwopObjectBrowserEvent(tile).setID(part.getCanonicalName()
			                                                         .replace('.', '_')));
		}
		if (description.length() > 50)
		{
			tile.addClass("two-wide");
		}
		tileAdded = true;
		return tile;
	}

	public StaticTile addComponentTile(String name, String description)
	{
		return addComponentTile(name, description, (DisplayScreen) null);
	}

	public StaticTile addComponentTile(String name, String description, DisplayScreen<?> demoDisplayScreen)
	{
		StaticTile tile;
		componentTiles.add(tile = new StaticTile().addFace(new TileFace<>().addCaption("Component")
		                                                                   .addParagraph(name + "<br/><br/>" + description))
		                                          .addStyle("cursor", "pointer")
		                                          .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));
		if (demoDisplayScreen != null)
		{
			tile.addEvent(new ChangeScreenEvent(demoDisplayScreen, "p=" + pluginName).setID(JQUIDraggableDemoScreen.class.getCanonicalName()
			                                                                                                             .replace('.', '_')));
		}
		if (description.length() > 50)
		{
			tile.addClass("two-wide");
		}
		tileAdded = true;
		return tile;
	}

	public StaticTile addFeatureTile(String name, String description, Class<? extends JavaScriptPart> optionsBrowser)
	{
		StaticTile tile;
		featureTiles.add(tile = new StaticTile().addFace(new TileFace<>().addCaption("Feature")
		                                                                 .addParagraph(name + "<br/><br/>" + description))
		                                        .addStyle("cursor", "pointer")
		                                        .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));
		if (optionsBrowser != null)
		{
			tile.addEvent(new SwopObjectBrowserEvent(tile).setID(optionsBrowser.getCanonicalName()
			                                                                   .replace('.', '_')));
		}
		if (description.length() > 50)
		{
			tile.addClass("two-wide");
		}
		tileAdded = true;
		return tile;
	}

	public StaticTile addFeatureTile(String name, String description)
	{
		return addFeatureTile(name, description, (DisplayScreen) null);
	}

	public StaticTile addFeatureTile(String name, String description, DisplayScreen<?> demoDisplayScreen)
	{
		StaticTile tile;
		featureTiles.add(tile = new StaticTile().addFace(new TileFace<>().addCaption("Feature")
		                                                                 .addParagraph(name + "<br/><br/>" + description))
		                                        .addStyle("cursor", "pointer")
		                                        .addAttribute("data-delay", RandomUtils.nextInt(3500, 8000) + ""));
		if (demoDisplayScreen != null)
		{
			tile.addEvent(new ChangeScreenEvent(demoDisplayScreen, "p=" + pluginName).setID(JQUIDraggableDemoScreen.class.getCanonicalName()
			                                                                                                             .replace('.', '_')));
		}
		if (description.length() > 50)
		{
			tile.addClass("two-wide");
		}
		tileAdded = true;
		return tile;
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
			                                          .getPluginDescription()).addClass(Col_12)
			                                                                  .addStyle("padding-left:20px;"));
		}
		container.add(descriptionRow);

		BSRow row = new BSRow();

		mavenDisplayDiv = buildDependencyInformation(StringEscapeUtils.escapeHtml4(
				FileTemplates.getFileTemplate(getClass(), getClassCanonicalName() + "mavenpom.txt", "mavenpom.txt")
				             .toString()));
		leftColumnTop.add(mavenDisplayDiv)
		             .addClass("col-12");

		artiInfo = buildArtifactInformation();
		rightColumnTop.add(artiInfo)
		              .addClass("col-12");

		optionBrowsers.forEach(a -> rightColumnTop.add(a));

		leftColumnTop.add(componentTiles);
		leftColumnTop.add(featureTiles);

		//fullColumn.add(sourceCodeContentPanel());

		row.add(fullColumn);
		row.add(rightColumn);

		BSRow topRow = new BSRow();
		topRow.add(leftColumnTop);
		topRow.add(rightColumnTop);

		container.add(topRow);
		container.add(row);

		BSRow<?> additionalRow = BSRow.newInstance();
		BSRow<?> additionalRowRight = BSRow.newInstance();

		for (Div<?, ?, ?, ?, ?> additional : additionals)
		{
			additionalRow.add(additional);
		}
		if (!additionalRow.getChildren()
		                  .isEmpty())
		{
			leftColumnTop.add(additionalRow);
		}

		for (Div<?, ?, ?, ?, ?> additional : additionalsRight)
		{
			additionalRowRight.add(additional);
		}
		if (!additionalRowRight.getChildren()
		                       .isEmpty())
		{
			rightColumnTop.add(additionalRowRight);
		}

		for (BSRow bottomRow : bottomRows)
		{
			container.add(bottomRow);
		}

		return container;
	}

	private DivSimple<?> buildDependencyInformation(String depedencyInfo)
	{
		BSCardBox<?> mavenDisplayDiv = new BSCardBox<>();
		JQSourceCodePrettify prettify = new JQSourceCodePrettify();
		prettify.addStyle("background-color:#36404a;");
		prettify.setSourceCodeLanguage(SourceCodeLanguages.XML);
		prettify.setShowLineNums(false);
		prettify.setText(depedencyInfo);
		mavenDisplayDiv.add(prettify);
		prettify.addStyle("padding-bottom:0px !important;");
		mavenDisplayDiv.add(new SmallText("Simply switch the group to com.jwebmp.jre10 or jre11 to move between releases :)"));
		return mavenDisplayDiv;
	}

	private DivSimple<?> buildArtifactInformation()
	{
		BSCardBox<?> mavenDisplayDiv = new BSCardBox<>();

		Plugins plugin = getPlugin(pluginName);
		if (plugin != null)
		{
			BSRow versionRow = new BSRow();
			versionRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("Source Widget"));
			versionRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(
					new Link<>(plugin.getPluginSourceURL(), "_blank").setText("View Demo <br/> Version " + plugin.getPluginVersion())));
			mavenDisplayDiv.add(versionRow);

			BSRow donationRow = new BSRow();
			donationRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("Donate to the Widget Devs"));
			donationRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(new Link<>(plugin.getPluginDonateUrl(), "_blank").setText("Yes I'll Support!")));
			mavenDisplayDiv.add(donationRow);

			BSRow teamcityRow = new BSRow();
			teamcityRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("TeamCity"));
			teamcityRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(new Link<>(plugin.getPluginTeamCityUrl(), "_blank").setText("Go To TeamCity")));
			mavenDisplayDiv.add(teamcityRow);

			BSRow jiraRow = new BSRow();
			jiraRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("Jira Project Tracking"));
			jiraRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(new Link<>(plugin.getPluginJiraUrl(), "_blank").setText("Go To Jira")));
			mavenDisplayDiv.add(jiraRow);

			BSRow sonarRow = new BSRow();
			sonarRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("SonarQube Quality Gate"));
			sonarRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(new Link<>(plugin.getPluginSonarUrl(), "_blank").setText("Go To SonarQube")));
			mavenDisplayDiv.add(sonarRow);

			BSRow artifactoryRow = new BSRow();
			artifactoryRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("Artifactory Direct Download"));
			artifactoryRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(new Link<>(plugin.getPluginArtifactoryUrl(), "_blank").setText("Go To Artifactory")));
			mavenDisplayDiv.add(artifactoryRow);
		}
		else
		{
			add("Plugin name wrong for artifact information");
		}

		return mavenDisplayDiv;
	}

	public Plugins getPlugin(String name)
	{
		Optional<Plugins> oPlugin = new Plugins().builder()
		                                         .where(Plugins_.pluginName, Operand.Equals, name)
		                                         .get();
		return oPlugin.get();
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();

		boolean first = true;
		for (String breadCrumb : breadCrumbs)
		{
			if (first)
			{
				BSBreadCrumbItem homeItem = new BSBreadCrumbItem().setActive(true)
				                                                  .setCrumbLink(new Link<>("#").setText("JWebMP"));
				crumbs.addBreadCrumb(homeItem);
				first = false;
			}
			crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
			                                             .setText(breadCrumb));
		}
		return crumbs;
	}

	public OptionsBrowser addOptionsBrowser(JavaScriptPart forObject)
	{
		OptionsBrowser ob = new OptionsBrowser(forObject);
		ob.setID("pluginObjectBrowser");
		optionBrowsers.add(ob);
		return ob;
	}

	public Div<?, ?, ?, ?, ?> getCodeBlockJava(Class reference, String fileName)
	{
		Div d = new Div<>().addClass(Col_12);
		d.setID("JavaCodeBlock");
		d.addClass("ng-non-bindable");
		addSourceToContainer(reference, fileName, Java, d);
		return d;
	}

	public List<Div<?, ?, ?, ?, ?>> getAdditionals()
	{
		return additionals;
	}

	public List<Div<?, ?, ?, ?, ?>> getAdditionalsRight()
	{
		return additionalsRight;
	}

	public List<BSRow> getBottomRows()
	{
		return bottomRows;
	}

	public void setBottomRows(List<BSRow> bottomRows)
	{
		this.bottomRows = bottomRows;
	}
}
