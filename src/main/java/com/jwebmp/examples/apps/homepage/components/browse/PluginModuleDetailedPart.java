package com.jwebmp.examples.apps.homepage.components.browse;

import com.google.common.base.Strings;
import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.apps.homepage.components.DefaultTable;
import com.jwebmp.examples.apps.homepage.components.PrettyCollapsable;
import com.jwebmp.examples.apps.homepage.components.dto.Plugins;
import com.jwebmp.examples.apps.homepage.services.PluginsService;
import com.jwebmp.plugins.bootstrap4.cards.BSCardChildren;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSAlignmentHorizontalOptions;

import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.Bg_Dark;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_12;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_Md_6;

public class PluginModuleDetailedPart
		extends DivSimple<PluginModuleDetailedPart>
		implements BSCardChildren
{
	public PluginModuleDetailedPart(String pluginName)
	{
		BSCardBody<?> body = new BSCardBody<>();
		//body.addClass(BSContainerOptions.Row);
		body.addClass(Bg_Dark);
		body.addStyle("display:grid;padding:0px;");
		
		DivSimple<?> content = new DivSimple<>();
		body.add(new PrettyCollapsable(content, "View Plugin and Module Information"));
		
		
		BSColumn<?> left = new BSColumn<>().addClass(Col_12, Col_Md_6);
		BSColumn<?> right = new BSColumn<>().addClass(Col_12, Col_Md_6);
		BSRow<?> detailRow = new BSRow<>();
		detailRow.add(left);
		detailRow.add(right);
		content.add(detailRow);
		
		//The plugin information
		Plugins pluginsOptional = GuiceContext.get(PluginsService.class)
		                                      .getPlugin(pluginName);
		
		DefaultTable<?> table = new DefaultTable<>()
				.addHeader("Name", "Module Name", "Artifact ID", "Group ID", "Version", "Status");
		
		Plugins plugin = pluginsOptional;
		table.addRow(plugin.getPluginName(), plugin.getPluginModuleName(), plugin.getPluginArtifactId(), plugin.getPluginGroupId(),
		             plugin.getPluginVersion(), plugin.getProjectStatus());
		
		content.add(table);
		
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
