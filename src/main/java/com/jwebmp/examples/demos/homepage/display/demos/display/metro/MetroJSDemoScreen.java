package com.jwebmp.examples.demos.homepage.display.demos.display.metro;

import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.plugins.metrojs.JQMetroOptions;

public class MetroJSDemoScreen
		extends PluginDemoScreen
{
	public MetroJSDemoScreen()
	{
		super("JQ Metro", "Display", "Metro Tiles");

		addComponentTile("FlipTile", "flip two tile faces in a vertical or horizontal direction", null);
		addComponentTile("FlipListTile", "trigger a list of tiles to flip between tile faces with custom delays", null);
		addComponentTile("CarouselTile", "slide any number of tile faces infinitely or slide to a specific tile face", null);
		addComponentTile("SlideTile", "slide two tile faces at an arbitrary number of stopping points", null);
		addComponentTile("StaticTile", "A tile with no animations, like these", null);

		addFeatureTile("JQMetroTiles", "Tile Container to add tiles to", null);
		addFeatureTile("TileFace", "A content div for a tile", null);
		addFeatureTile("TileTitle", "A caption for a given tile if required", null);

		addOptionsBrowser(new JQMetroOptions());

	}
}
