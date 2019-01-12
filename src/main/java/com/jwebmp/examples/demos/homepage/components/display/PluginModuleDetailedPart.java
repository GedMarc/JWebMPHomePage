package com.jwebmp.examples.demos.homepage.components.display;

import com.google.common.base.Strings;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.DefaultTable;
import com.jwebmp.examples.demos.homepage.components.plugins.PluginCachingClass;
import com.jwebmp.examples.demos.homepage.entities.Plugins;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.cards.BSCardChildren;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.cards.parts.styles.BSCardButtonDarkOutline;
import com.jwebmp.plugins.bootstrap4.collapse.BSCollapse;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSAlignmentHorizontalOptions;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;
import com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles;

import static com.jwebmp.core.utilities.StaticStrings.*;
import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class PluginModuleDetailedPart
		extends DisplayPart
		implements BSCardChildren
{
	public PluginModuleDetailedPart(String pluginName)
	{
		BSCardBody<?> body = new BSCardBody<>();
		//body.addClass(BSContainerOptions.Row);
		body.addClass(Bg_Dark);
		body.addStyle("display:grid;padding:0px;");

		BSCardButtonDarkOutline<?> button = new BSCardButtonDarkOutline<>();
		button.addStyle("background-color", "#3d4853")
		      .addStyle("color", "#3bafda");
		button.setText("View Plugin and Module Information " + HTML_TAB + FontAwesome.icon(FontAwesomeIcons.caret_circle_down, FontAwesomeStyles.Light)
		                                                                             .toString(0));
		DivSimple<?> content = new DivSimple<>();
		BSCollapse.link(button, content, true);

		BSColumn<?> left = new BSColumn<>().addClass(Col_12, Col_Md_6);
		BSColumn<?> right = new BSColumn<>().addClass(Col_12, Col_Md_6);
		BSRow<?> detailRow = new BSRow<>();
		detailRow.add(left);
		detailRow.add(right);
		content.add(detailRow);

		//The plugin information
		Plugins pluginsOptional = GuiceContext.get(PluginCachingClass.class)
		                                      .getPlugin(pluginName);

		DefaultTable<?> table = new DefaultTable<>()
				                        .addHeader("Name", "Module Name", "Artifact ID", "Group ID", "Version", "Status");

		Plugins plugin = pluginsOptional;
		table.addRow(plugin.getPluginName(), plugin.getPluginModuleName(), plugin.getPluginArtifactId(), plugin.getPluginGroupId(),
		             plugin.getPluginVersion(), plugin.getProjectStatus());

		content.add(table);

		body.add(button);
		body.add(content);

		int colSize = 3;
		if (Strings.isNullOrEmpty(plugin.getPluginOriginalSite()))
		{
			colSize = 4;
		}

		BSRow footerRow = new BSRow<>().resetHorizontalSinks()
		                               .addClass(BSAlignmentHorizontalOptions.Align_Center);
		footerRow.add(new Link<>(plugin.getPluginSonarUrl(), "_blank").setText("View SonarQube")
		                                                              .addClass("col-" + colSize));
		footerRow.add(new Link<>(plugin.getPluginJiraUrl(), "_blank").setText("Log a Bug")
		                                                             .addClass("col-" + colSize));
		footerRow.add(new Link<>(plugin.getPluginGithubUrl(), "_blank").setText("View Plugin Source")
		                                                               .addClass("col-" + colSize));

		if (!Strings.isNullOrEmpty(plugin.getPluginOriginalSite()))
		{
			footerRow.add(new Link<>(plugin.getPluginSourceURL(), "_blank").setText("View Plugin Demo Site")
			                                                               .addClass("col-" + colSize));
		}
		content.add(footerRow);

		add(body);
	}
}
