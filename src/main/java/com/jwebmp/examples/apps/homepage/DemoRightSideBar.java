package com.jwebmp.examples.apps.homepage;

import com.jwebmp.core.base.html.Image;
import com.jwebmp.plugins.themes.mintontheme.chat.*;
import com.jwebmp.plugins.themes.mintontheme.rightsidebar.RightSideBar;
import com.jwebmp.plugins.themes.mintontheme.settings.RightSideSettingsWindow;
import com.jwebmp.plugins.themes.mintontheme.settings.SettingsItem;
import com.jwebmp.plugins.themes.mintontheme.settings.SettingsSummary;
import com.jwebmp.plugins.themes.mintontheme.tasks.RightSideTaskWindow;
import com.jwebmp.plugins.themes.mintontheme.tasks.TaskSummaryItem;
import com.jwebmp.plugins.themes.mintontheme.tasks.TasksSummary;
import com.jwebmp.plugins.bootstrap4.listgroup.tabs.BSTabContainer;
import com.jwebmp.plugins.fontawesome5.options.FontAwesomeSizes;

import static com.jwebmp.examples.apps.homepage.HomePage.randomUserAvatar;
import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSColoursOptions.*;
import static com.jwebmp.plugins.fontawesome5.FontAwesome.icon;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons.*;

public class DemoRightSideBar extends RightSideBar
{
	public DemoRightSideBar()
	{
		BSTabContainer<?> chatTab = addChatTab();
		
		RightSideChatWindow cw = (RightSideChatWindow) chatTab.getTabPane();
		cw.setChatGroupsSummary(new ChatGroupsSummary("Group Chats")
				                        .addItem(new ChatGroupSummaryItem("General Chat", Text_Success))
				                        .addItem(new ChatGroupSummaryItem("Quick Support", Text_Warning))
				                        .addItem(new ChatGroupSummaryItem("Suggestions Box", Text_Danger))
				                        .addItem(new ChatGroupSummaryItem("Freelance", Text_Primary))
		);
		cw.setChatFavouritesSummary(new ChatsSummary("Favourites")
				                            .setHeaderIcon(icon(stars, FontAwesomeSizes.$2x).addClass(Text_Danger))
				                            .addItem(new ChatSummaryItem("Andrew Mackie",
				                                                         "It will seem like simplified English.",
				                                                         new Image<>(randomUserAvatar()),
				                                                         Bg_Warning)
				                            )
				                            .addItem(new ChatSummaryItem("Rory Dalyell",
				                                                         "Reuse everything.",
				                                                         new Image<>(randomUserAvatar()),
				                                                         Bg_Primary)
				                            )
				                            .addItem(new ChatSummaryItem("Jaxon Dunhill",
				                                                         "Achieve the fastest development.",
				                                                         new Image<>(randomUserAvatar()),
				                                                         Bg_Danger)
				                            )
		
		);
		cw.setChatsSummary(new ChatsSummary("Other Chats")
				                   .setHeaderIcon(icon(question_circle, FontAwesomeSizes.$2x).addClass(Text_Info))
				                   .addItem(new ChatSummaryItem("Jackson Therry",
				                                                "Single Language Development",
				                                                new Image<>(randomUserAvatar()),
				                                                Bg_Warning)
				                   )
				                   .addItem(new ChatSummaryItem("Charles Deakin",
				                                                "No need for heavy containers.",
				                                                new Image<>(randomUserAvatar()),
				                                                Bg_Primary)
				                   )
				                   .addItem(new ChatSummaryItem("Ryan Salting",
				                                                "Request Scoped by Default.",
				                                                new Image<>(randomUserAvatar()),
				                                                Bg_Danger)
				                   )
				                   .addItem(new ChatSummaryItem("Sean Howse",
				                                                "Crazy Performance!.",
				                                                new Image<>(randomUserAvatar()),
				                                                Bg_Info)
				                   )
				                   .addItem(new ChatSummaryItem("Dean Cowarde",
				                                                "Unit Test Everything!.",
				                                                new Image<>(randomUserAvatar()),
				                                                Bg_Secondary)
				                   )
				                   .addItem(new ChatSummaryItem("Ryan Edwards",
				                                                "It's already in Jakarta!.",
				                                                new Image<>(randomUserAvatar()),
				                                                Bg_Warning)
				                   )
				                   .addItem(new ChatSummaryItem("Built for Maintenance",
				                                                "Quickest updating and maintenance around",
				                                                new Image<>(randomUserAvatar()),
				                                                Bg_Success)
				                   )
				                   .setLoadMoreButtonText("Load More")
				                   .setLoadMoreIcon(icon(circle_notch).addClass("fa-pulse"))
		);
		
		BSTabContainer<?> tasksTab = addTasksTab();
		
		RightSideTaskWindow taskPane = (RightSideTaskWindow) tasksTab.getTabPane();
		taskPane.addTaskGroup(new TasksSummary("Component Status")
				                      .addItem(new TaskSummaryItem("Framework Development", Bg_Success, 99))
				                      .addItem(new TaskSummaryItem("JWebMP Core", Bg_Success, 100))
				                      .addItem(new TaskSummaryItem("JWebMP CSS Render", Bg_Success, 100))
				                      .addItem(new TaskSummaryItem("JWebMP JS Render", Bg_Success, 100))
				                      .addItem(new TaskSummaryItem("JWebMP Cordova Render", Bg_Danger, 15)))
		        .addTaskGroup(new TasksSummary("JQuery UI Status")
				                      .addItem(new TaskSummaryItem("Core", Bg_Success, 100))
				                      .addItem(new TaskSummaryItem("DataType Tools", Bg_Success, 100))
				                      .addItem(new TaskSummaryItem("Border Layout", Bg_Success, 100))
				                      .addItem(new TaskSummaryItem("Colour Picker", Bg_Success, 100))
				                      .addItem(new TaskSummaryItem("Default Theme Roller", Bg_Success, 100))
				                      .addItem(new TaskSummaryItem("Quick Forms", Bg_Success, 100))
		        );
		
		BSTabContainer<?> settingsTab = addSettingsTab();
		RightSideSettingsWindow sw = (RightSideSettingsWindow) settingsTab.getTabPane();
		
		SettingsItem userViewCheck = new SettingsItem("Sidebar User Info", "leftsidebar-user",
		                                              "sidebaruser-check", "Enable", "true")
				.setChecked(true);
		userViewCheck.getRadioInput()
		             .addAttribute("type", "checkbox");
		
		sw.addSettingsGroup(new SettingsSummary("Theme Settings")
				                    .addItem(new SettingsItem("Colour Scheme", "color-scheme-mode", "light-mode-check", "Light Mode", "Light").setChecked(true))
				                    .addItem(new SettingsItem(null, "color-scheme-mode", "dark-mode-check", "Dark Mode", "dark"))
				                    .addItem(new SettingsItem("Topbar", "topbar-color", "darktopbar-check", "Dark", "dark"))
				                    .addItem(new SettingsItem(null, "topbar-color", "lighttopbar-check", "Light", "light"))
				                    .addItem(new SettingsItem("Left Sidebar Color", "leftsidebar-color", "light-check", "Light", "Light").setChecked(true))
				                    .addItem(new SettingsItem(null, "leftsidebar-color", "dark-check", "Dark", "dark"))
				                    .addItem(new SettingsItem(null, "leftsidebar-color", "brand-check", "Brand", "brand"))
				                    .addItem(new SettingsItem(null, "leftsidebar-color", "gradient-check", "Gradient", "gradient"))
				                    .addItem(new SettingsItem("Left Sidebar Size", "leftsidebar-size", "default-size-check", "Default", "default"))
				                    .addItem(new SettingsItem(null, "leftsidebar-size", "condensed-check", "Condensed <small>(Extra Small size)</small>", "condensed"))
				                    .addItem(new SettingsItem(null, "leftsidebar-size", "compact-check", "Compact <small>(Small size)</small>", "compact"))
				                    .addItem(userViewCheck)
		);
	}
}
