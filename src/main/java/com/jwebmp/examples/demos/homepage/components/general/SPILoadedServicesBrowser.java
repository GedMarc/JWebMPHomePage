package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.base.ComponentBase;
import com.jwebmp.core.base.ComponentDependancyBase;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;
import io.github.classgraph.*;

import jakarta.validation.constraints.NotNull;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class SPILoadedServicesBrowser
		extends JSTree<SPILoadedServicesBrowser>
{


	public SPILoadedServicesBrowser()
	{

		setID("spiServices_");
		setTheme(new JSTreeDefaultDarkTheme());
		constructTree();
	}

	private void constructTree()
	{
		JSTreeListItem<?> rootItem = new JSTreeListItem<>("Services Explorer " + ":  <small><i>GuiceContext.getAllLoadedServices()</i></small>");
		rootItem.getOptions()
		        .setDisabled(false)
		        .setIcon("fal fa-project-diagram")
		        .setOpened(true);

		GuiceContext.getAllLoadedServices().forEach((clazz,services)->{
			JSTreeListItem<?> treeFolder = new JSTreeListItem<>().setText(clazz.getSimpleName());
			treeFolder.getOptions()
			          .setIcon("fal fa-hand-holding-magic");
			rootItem.add(treeFolder);

			services.forEach(a->{
				Class serviceClass = a.getClass();
				JSTreeListItem<?> treeFolderSub = new JSTreeListItem<>().setText(serviceClass.getSimpleName());
				treeFolderSub.getOptions()
				          .setIcon("fal fa-hand-holding-magic");
				treeFolder.add(treeFolderSub);
			});
		});

		addRoot(rootItem);
	}
}
