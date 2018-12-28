package com.jwebmp.examples.demos.homepage.components.display;

import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.options.JSTreeNodeOptions;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;

import java.util.ArrayList;
import java.util.List;

public class MetaInfServicesTree
		extends JSTree<MetaInfServicesTree>
{
	public MetaInfServicesTree(String... metaInfs)
	{
		setTheme(new JSTreeDefaultDarkTheme());

		JSTreeListItem<?> rootItem = new JSTreeListItem<>("src")
				                             .setOptions(new JSTreeNodeOptions<>().setDisabled(false)
				                                                                  .setIcon("far fa-caret-circle-down")
				                                                                  .setOpened(true));

		JSTreeListItem<?> folder1 = new JSTreeListItem<>("META-INF", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
		                                                                                      .setOpened(true));

		JSTreeListItem<?> folder2 = new JSTreeListItem<>("services", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
		                                                                                      .setOpened(true));


		List<JSTreeListItem<?>> files = new ArrayList<>();
		for (String metaInf : metaInfs)
		{
			files.add(new JSTreeListItem<>(metaInf, new JSTreeNodeOptions<>().setIcon("far fa-file")));
		}


		JSTreeListItem<?> folderResources = new JSTreeListItem<>("resources", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
		                                                                                               .setOpened(true));

		JSTreeListItem<?> file2 = new JSTreeListItem<>("favicon.ico", new JSTreeNodeOptions<>().setIcon("far fa-file-alt"));

		for (JSTreeListItem<?> file : files)
		{
			folder2.add(file);
		}
		folder1.add(folder2);

		//folderResources.add(file2);

		//folder1.add(folderResources);
		rootItem.add(folder1);

		addRoot(rootItem);
		//setID("directory-structure-example");
	}
}
