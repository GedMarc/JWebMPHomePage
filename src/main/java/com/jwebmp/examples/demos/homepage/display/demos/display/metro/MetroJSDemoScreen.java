package com.jwebmp.examples.demos.homepage.display.demos.display.metro;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.plugins.metrojs.JQMetroOptions;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class MetroJSDemoScreen
		extends PluginDemoScreen
{
	public MetroJSDemoScreen()
	{
		super("JQ Metro", "Display", "Metro Tiles");

		addComponentTile("FlipTile", "flip two tile faces in a vertical or horizontal direction");
		addComponentTile("FlipListTile", "trigger a list of tiles to flip between tile faces with custom delays");
		addComponentTile("CarouselTile", "slide any number of tile faces infinitely or slide to a specific tile face");
		addComponentTile("SlideTile", "slide two tile faces at an arbitrary number of stopping points");
		addComponentTile("StaticTile", "A tile with no animations, like these");

		addFeatureTile("JQMetroTiles", "Tile Container to add tiles to");
		addFeatureTile("TileFace", "A content div for a tile");
		addFeatureTile("TileTitle", "A caption for a given tile if required");

		Div d = new Div<>().addClass(Col_12);
		addSourceToContainer(MetroJSDemoScreen.class, "codesnippet.txt", Java, d);
		getAdditionals().add(d);

		addOptionsBrowser(new JQMetroOptions());

	}
}
