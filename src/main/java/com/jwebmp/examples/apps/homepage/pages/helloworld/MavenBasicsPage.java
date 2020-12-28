package com.jwebmp.examples.apps.homepage.pages.helloworld;

import com.jwebmp.examples.apps.homepage.components.SwapScreen;
import com.jwebmp.plugins.themes.mintontheme.pages.PageTitle;
import com.jwebmp.plugins.themes.mintontheme.partials.BreadcrumbItem;
import com.jwebmp.plugins.themes.mintontheme.partials.Breadcrumbs;
import com.jwebmp.plugins.themes.mintontheme.timeline.TimelineItem;
import com.jwebmp.examples.apps.homepage.pages.viewers.FileViewer;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.options.JSTreeNodeOptions;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_Md_6;
import static com.jwebmp.plugins.bootstrap4.options.BSTypographyOptions.Text_Left;
import static java.nio.charset.StandardCharsets.UTF_8;

public class MavenBasicsPage extends SwapScreen<MavenBasicsPage>
{
	public MavenBasicsPage()
	{
		setRenderSourceSection(true);
		getSourceClasses().add(MavenBasicsPage.class);
		
		BSRow<?> row = new BSRow<>();
		BSColumn<?> left = new BSColumn<>(Col_Md_6).addClass(Text_Left);
		BSColumn<?> right = new BSColumn<>(Col_Md_6);
		
		left.add(new TimelineItem(false)
				         //   .addClass(Col_12)
				         .addHeading("Project Setup")
				         .addSubHeading("This is a default directory structure for a maven project, with a pom.xml file that defines that dependencies and everything else. " +
						                        "You can also use gradle or ant")
				         .addContent(buildStructureTree()));
		row.add(left);
		try
		{
			right.add(new TimelineItem(true)
					          //        .addClass(Col_12)
					          .addHeading("Configure Build")
					          .addSubHeading("Maven has been around forever, so you need to set it up for the newer things, you can swap out the release tag for your JDK version of course")
					          .addContent(new FileViewer(IOUtils.toString(getClass().getResourceAsStream("pom.txt"), UTF_8), SourceCodeLanguages.XML)
					          ));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		row.add(right);
		add(row);
	}
	
	@Override
	public PageTitle pageTitle()
	{
		return new PageTitle("Project Setup - JDK 15", new Breadcrumbs().addCrumb(new BreadcrumbItem("JWebMP"))
		                                                                .addCrumb(new BreadcrumbItem("Hello World"))
		                                                                .addCrumb(new BreadcrumbItem("Maven Basics", true))
		);
	}
	
	private JSTree<?> buildStructureTree()
	{
		JSTree<?> directoryStructureExample = new JSTree<>().addClass(Text_Left);
		JSTreeListItem<?> rootItem = new JSTreeListItem<>("project")
				.setOptions(new JSTreeNodeOptions<>().setDisabled(false)
				                                     .setIcon("far fa-caret-circle-down")
				                                     .setOpened(true)
				)
				.add(new JSTreeListItem<>("pom.xml", new JSTreeNodeOptions<>().setIcon("far fa-file-alt")));
		
		JSTreeListItem<?> srcItem = new JSTreeListItem<>("src")
				.setOptions(new JSTreeNodeOptions<>().setDisabled(false)
				                                     .setIcon("far fa-caret-circle-down")
				                                     .setOpened(true)
				)
				.add(new JSTreeListItem<>("main", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
				                                                           .setOpened(true)
				     ).add(new JSTreeListItem<>("java", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
				                                                                 .setOpened(true)
				           ).add(new JSTreeListItem<>("module-info.java", new JSTreeNodeOptions<>().setIcon("far fa-file-alt")))
				     )
				      .add(new JSTreeListItem<>("resources", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
				                                                                      .setOpened(true)
				           ).add(new JSTreeListItem<>("META-INF", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
				                                                                           .setOpened(true)
				                 ).add(new JSTreeListItem<>("resources", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
				                                                                                  .setOpened(true)
				                       )
				                 )
				                  .add(new JSTreeListItem<>("<small>services <i>(*jre8 only)</i></small>",
				                                            new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
				                                                                     .setOpened(true)
				                       )
				                  )
				           )
				      )
				)
				.add(new JSTreeListItem<>("test", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
				                                                           .setOpened(true)
				     ).add(new JSTreeListItem<>("java", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
				                                                                 .setOpened(true)
				           )
				     )
				      .add(new JSTreeListItem<>("resources", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
				                                                                      .setOpened(true)
				           ).add(new JSTreeListItem<>("META-INF", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
				                                                                           .setOpened(true)
				                 ).add(new JSTreeListItem<>("resources", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
				                                                                                  .setOpened(true)
				                       )
				                 )
				                  .add(new JSTreeListItem<>("services", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
				                                                                                 .setOpened(true)
				                       )
				                  )
				           )
				      )
				);
		
		rootItem.add(srcItem);
		
		directoryStructureExample.addRoot(rootItem);
		directoryStructureExample.setID("directory-structure-example");
		return directoryStructureExample;
	}
}
