package com.jwebmp.examples.apps.homepage.pages.helloworld;

import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.apps.homepage.components.SwapScreen;
import com.jwebmp.plugins.themes.mintontheme.pages.PageTitle;
import com.jwebmp.plugins.themes.mintontheme.partials.BreadcrumbItem;
import com.jwebmp.plugins.themes.mintontheme.partials.Breadcrumbs;
import com.jwebmp.plugins.themes.mintontheme.timeline.TimelineItem;
import com.jwebmp.plugins.themes.mintontheme.timeline.TimelineLayout;
import com.jwebmp.examples.apps.homepage.pages.viewers.FileViewer;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.options.JSTreeNodeOptions;
import org.apache.commons.io.IOUtils;

import static com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.Col_Md_6;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.Java;
import static java.nio.charset.StandardCharsets.UTF_8;

public class GetStartedPage extends SwapScreen<GetStartedPage>
{
	private final TimelineLayout layout = new TimelineLayout(true);
	
	public GetStartedPage()
	{
		setRenderSourceSection(true);
		getSourceClasses().add(GetStartedPage.class);
		try
		{
			BSRow<?> row = new BSRow<>();
			BSColumn<?> left = new BSColumn<>().addClass(Col_Md_6);
			BSColumn<?> right = new BSColumn<>().addClass(Col_Md_6);
			
			left.add(new TimelineItem(false)
					         .addHeading("You will need a Page")
					         .addSubHeading("Pages are classes that provide a pretty full suite for html tags. You can of course override the entire render by implementing IPage.")
					         .addContent(new FileViewer(IOUtils.toString(getClass().getResourceAsStream("page_helloworld.txt"), UTF_8), Java)));
			
			left.add(new TimelineItem(false)
					         .addHeading("Register with the Framework")
					         .addSubHeading("Create a <code>module-info.java</code> file in the root source directory of your project. ")
					         .addContent(new FileViewer(IOUtils.toString(getClass().getResourceAsStream("module_info.txt"), UTF_8), SourceCodeLanguages.JS)));
			
			left.add(new TimelineItem(false)
					         .addHeading("Create Your PSVM")
					         .addSubHeading("Start it up like any other application, no configuration required")
					         .addContent(new FileViewer(IOUtils.toString(getClass().getResourceAsStream("get_started_boot.txt"), UTF_8), Java))
					         .addSubHeading("JWebMP is already in the Jakarta namespace. To use the Javax namespace, place a classifier on the references in your pom file for javax"));
			
			right.add(new TimelineItem(true)
					          .addHeading("Project Structure")
					          .addSubHeading("JWebMP allows you to keep the most basic of structures in extremely complex applications, never requiring (but allows) html, or jsf bindings," +
							                         "Make your web and mobile development actually feel like Java again!")
					          .addContent(buildStructureTree())
					          .addHeading("Example Project")
					          .addSubHeading("Clone or Fork the example project to get started right away!")
					          .addContent(new BSRow<>().add(new BSColumn<>(Col_Md_6).add(new Link<>("https://github.com/GedMarc/JWebMP-Examples-Undertow-HelloWorld/tree/master")
							                                                                     .setTargetFrameName("_blank")
							                                                                     .addClass(Btn, Btn_Info, Btn_Block)
							                                                                     .setText("Hello World"))
					                      )
					                                   .add(new BSColumn<>(Col_Md_6).add(new Link<>("https://github.com/GedMarc/JWebMP-Examples-Parent/tree/master/Undertow")
							                                                                     .setTargetFrameName("_blank")
							                                                                     .addClass(Btn, Btn_Info, Btn_Block)
							                                                                     .setText("JWebMP Examples"))
					                                   )
					          )
			);
			row.add(left);
			row.add(right);
			
			add(row);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public PageTitle pageTitle()
	{
		return new PageTitle("Hello World - JDK 15", new Breadcrumbs().addCrumb(new BreadcrumbItem("JWebMP"))
		                                                              .addCrumb(new BreadcrumbItem("Hello World"))
		                                                              .addCrumb(new BreadcrumbItem("Get Started", true))
		);
	}
	
	private JSTree<?> buildStructureTree()
	{
		String fileIcon = "far fa-file-alt";
		String folderOpen = "far fa-folder-open";
		
		JSTree<?> directoryStructureExample = new JSTree<>();
		JSTreeListItem<?> rootItem = new JSTreeListItem<>("src")
				.setOptions(new JSTreeNodeOptions<>().setDisabled(false)
				                                     .setIcon("far fa-caret-circle-down")
				                                     .setOpened(true))
				.add(new JSTreeListItem<>("main", new JSTreeNodeOptions<>().setIcon(folderOpen)
				                                                           .setOpened(true))
						     .add(new JSTreeListItem<>("java", new JSTreeNodeOptions<>().setIcon(folderOpen)
						                                                                .setOpened(true))
								          .add(new JSTreeListItem<>("module-info.java", new JSTreeNodeOptions<>().setIcon(fileIcon)))
								          .add(new JSTreeListItem<>("package", new JSTreeNodeOptions<>().setIcon(folderOpen)
								                                                                        .setOpened(true))
										               .add(new JSTreeListItem<>("HelloWorld.java", new JSTreeNodeOptions<>().setIcon(fileIcon)))
								          )
						
						     )
				);
		directoryStructureExample.addRoot(rootItem);
		directoryStructureExample.setID("directory-structure-example");
		return directoryStructureExample;
	}
	
}
