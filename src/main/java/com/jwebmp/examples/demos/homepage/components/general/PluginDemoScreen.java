package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.core.base.html.Paragraph;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.entityassist.enumerations.Operand;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.display.ComingSoon;
import com.jwebmp.examples.demos.homepage.display.OuterLayout;
import com.jwebmp.examples.demos.homepage.display.demos.jqui.demos.JQUIDraggableDemoScreen;
import com.jwebmp.examples.demos.homepage.display.menu.ChangeScreenEvent;
import com.jwebmp.examples.demos.homepage.entities.Plugins;
import com.jwebmp.examples.demos.homepage.entities.Plugins_;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.jqlayout.enumerations.JQLayoutArea;
import com.jwebmp.plugins.jqlayout.events.JQLayoutSlideCloseLayoutDivFeature;
import com.jwebmp.plugins.metrojs.JQMetroTiles;
import com.jwebmp.plugins.metrojs.enumerations.TileAccentThemes;
import com.jwebmp.plugins.metrojs.enumerations.TileCount;
import com.jwebmp.plugins.metrojs.tiles.StaticTile;
import com.jwebmp.plugins.metrojs.tiles.TileFace;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class PluginDemoScreen
		extends DisplayScreen
{

	private final List<Div<?, ?, ?, ?, ?>> additionals = new ArrayList<>();
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

	public PluginDemoScreen(String pluginName, String... breadCrumbs)
	{
		OuterLayout outerLayout = GuiceContext.getInstance(OuterLayout.class);
		addFeature(new JQLayoutSlideCloseLayoutDivFeature(outerLayout.getPane(JQLayoutArea.East)));

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

	public void addComponentTile(String name, String description, DisplayScreen<?> demoDisplayScreen)
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
	}

	public void addFeatureTile(String name, String description, DisplayScreen<?> demoDisplayScreen)
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
		tileAdded = true;
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
		leftColumnTop.add(mavenDisplayDiv).addClass("col-12");

		artiInfo = buildArtifactInformation();
		rightColumnTop.add(artiInfo).addClass("col-12");

		optionBrowsers.forEach(a ->rightColumnTop.add(a));

		//fullColumn.add(sourceCodeContentPanel());

		row.add(fullColumn);
		row.add(rightColumn);

		BSRow topRow = new BSRow();
		topRow.add(leftColumnTop);
		topRow.add(rightColumnTop);

		container.add(topRow);
		container.add(row);

		if (!tileAdded && additionals.isEmpty())
		{
			container.add(new ComingSoon().setRenderBreadcrumb(false));
		}

		BSRow<?> additionalRow = BSRow.newInstance();
		for (Div<?, ?, ?, ?, ?> additional : additionals)
		{
			additionalRow.add(additional);
		}
		if (!additionalRow.getChildren()
		                  .isEmpty())
		{
			container.add(additionalRow);
		}

		return container;
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

	private DivSimple<?> buildArtifactInformation()
	{
		DivSimple<?> mavenDisplayDiv = new DivSimple<>();

		Plugins plugin = getPlugin(pluginName);
		if (plugin != null)
		{
			BSRow versionRow = new BSRow();
			versionRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("Source Widget Version"));
			versionRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(new Link<>(plugin.getPluginSourceURL(), "_blank").setText(plugin.getPluginVersion())));
			mavenDisplayDiv.add(versionRow);

			BSRow donationRow = new BSRow();
			donationRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("Donate to the Widget Devs"));
			donationRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).add(new Link<>(plugin.getPluginDonateUrl(), "_blank").setText("Yes I'll Support!")));
			mavenDisplayDiv.add(donationRow);

			BSRow teamcityRow = new BSRow();
			teamcityRow.add(new BSColumn<>(BSColumnOptions.Col_Md_6, Col_12).setText("TeamCity CI"));
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

	@CacheResult
	public Plugins getPlugin(@CacheKey String name)
	{
		Optional<Plugins> oPlugin = new Plugins().builder()
		                                         .where(Plugins_.pluginName, Operand.Equals, pluginName)
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

		OptionsBrowser ob;
		optionBrowsers.add(ob = new OptionsBrowser(forObject));
		return ob;

	}
}
