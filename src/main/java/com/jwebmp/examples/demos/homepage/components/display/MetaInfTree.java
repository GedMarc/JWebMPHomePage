package com.jwebmp.examples.demos.homepage.components.display;

import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.options.JSTreeNodeOptions;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;

import java.util.ArrayList;
import java.util.List;

public class MetaInfTree
		extends JSTree<MetaInfTree>
{
	public MetaInfTree(String... metaInfs)
	{
		setTheme(new JSTreeDefaultDarkTheme());
		JSTreeListItem<?> rootItem = new JSTreeListItem<>("src")
				                             .setOptions(new JSTreeNodeOptions<>().setDisabled(false)
				                                                                  .setIcon("far fa-caret-circle-down")
				                                                                  .setOpened(true));
		JSTreeListItem<?> folder1 = new JSTreeListItem<>("META-INF", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
		                                                                                      .setOpened(true));
		List<JSTreeListItem<?>> files = new ArrayList<>();
		for (String metaInf : metaInfs)
		{
			files.add(new JSTreeListItem<>(metaInf, new JSTreeNodeOptions<>().setIcon("far fa-file")));
		}
		for (JSTreeListItem<?> file : files)
		{
			folder1.add(file);
		}
		rootItem.add(folder1);

		addRoot(rootItem);
	}
}
