package com.jwebmp.examples.demos.homepage.components.plugins;

import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.examples.demos.homepage.components.DefaultTable;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.entities.Plugins;
import com.jwebmp.plugins.bootstrap4.cards.BSCardChildren;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.cards.parts.styles.BSCardButtonDarkOutline;
import com.jwebmp.plugins.bootstrap4.collapse.BSCollapse;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;
import com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
import java.util.Optional;

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
		body.addClass(BSContainerOptions.Row);
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
		Optional<Plugins> pluginsOptional = getPlugin(pluginName);

		DefaultTable<?> table = new DefaultTable<>()
				                        .addHeader("Name", "Module Name", "Artifact ID", "Group ID", "Version", "Status");

		if (pluginsOptional.isPresent())
		{
			Plugins plugin = pluginsOptional.get();
			table.addRow(plugin.getPluginName(), plugin.getPluginModuleName(), plugin.getPluginArtifactId(), plugin.getPluginGroupId(),
			             plugin.getPluginVersion(), plugin.getProjectStatus());
		}
		content.add(table);

		//

		body.add(button);
		body.add(content);
		add(body);
	}

	@CacheResult
	public Optional<Plugins> getPlugin(@CacheKey String name)
	{
		return new Plugins().builder()
		                    .findByName(name)
		                    .get();
	}
}
