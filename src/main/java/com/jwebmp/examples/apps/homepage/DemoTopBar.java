package com.jwebmp.examples.apps.homepage;

import com.jwebmp.core.base.html.H6;
import com.jwebmp.core.base.html.Image;
import com.jwebmp.plugins.themes.mintontheme.appsearch.AppSearchResultsHeader;
import com.jwebmp.plugins.themes.mintontheme.topbar.DefaultMenuDropDown;
import com.jwebmp.plugins.themes.mintontheme.topbar.TopBar;
import com.jwebmp.plugins.themes.mintontheme.topbar.TopBarSettingsTrigger;
import com.jwebmp.plugins.themes.mintontheme.topbar.dropbox.DropBoxes;
import com.jwebmp.plugins.themes.mintontheme.topbar.megamenu.MegaMenu;
import com.jwebmp.plugins.themes.mintontheme.topbar.megamenu.MegaMenuAdvert;
import com.jwebmp.plugins.themes.mintontheme.topbar.megamenu.MegaMenuSection;
import com.jwebmp.plugins.themes.mintontheme.topbar.notifications.NotificationFooter;
import com.jwebmp.plugins.themes.mintontheme.topbar.notifications.NotificationItem;
import com.jwebmp.plugins.themes.mintontheme.topbar.notifications.NotificationsDropDown;
import com.jwebmp.plugins.themes.mintontheme.topbar.profile.ProfileDropdown;
import com.jwebmp.plugins.bootstrap4.badge.styles.BSBadgeDanger;
import com.jwebmp.plugins.bootstrap4.badge.styles.BSBadgeSuccess;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonPrimary;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;

import static com.jwebmp.examples.apps.homepage.HomePage.randomUserAvatar;
import static com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions.Btn_Rounded;
import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSColoursOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSDefaultOptions.Float_Right;
import static com.jwebmp.plugins.bootstrap4.options.BSDefaultOptions.Text_Overflow;
import static com.jwebmp.plugins.bootstrap4.options.BSMarginOptions.MarginBottom_2;
import static com.jwebmp.plugins.bootstrap4.options.BSTypographyOptions.Text_Uppercase;
import static com.jwebmp.plugins.fontawesome5.FontAwesome.icon;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons.*;
import static com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles.Duotone;
import static com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles.Light;

public class DemoTopBar extends TopBar
{
	public DemoTopBar()
	{
		searchForm();
		dropBoxes();
		notifications();
		profile();
		
		setSettingsTrigger(new TopBarSettingsTrigger(icon(cogs, Duotone)));
		addMenuButton(icon(bars));
		addNewDropDown(new DefaultMenuDropDown("Create New")
				               .addItem(icon(briefcase), "New Plugin Request", null)
				               .addItem(icon(acorn), "New Example Request", null)
				               .addItem(icon(lightbulb), "New Support Request", null)
				               .addDivider()
				               .addItem(icon(headphones), "Help & Support", null)
		);
		megaMenu();
	}
	
	private void searchForm()
	{
		getSearchForm()
				.getDropDown()
				.addHeading(new AppSearchResultsHeader().setResults(9))
				
				.addStaticItem(icon(FontAwesomeIcons.home), "Analytics Report")
				.addStaticItem(icon(FontAwesomeIcons.question_circle), "How can I help you?")
				.addStaticItem(icon(FontAwesomeIcons.user_cog), "User Profile Settings")
				.addHeading(new H6<>("Users").addClass(Text_Overflow)
				                             .addClass(Text_Uppercase)
				                             .addClass(MarginBottom_2))
				.getNotifications()
				.addNotification(randomUserAvatar(), "Erwin E. Brown", "UI Designer")
				.addNotification(randomUserAvatar(), "Jacob Deo", "Developer")
		;
	}
	
	private void dropBoxes()
	{
		setDropBoxes(new DropBoxes(icon(FontAwesomeIcons.game_board_alt)));
		getDropBoxes()
				.getDropBoxContainer()
				.addButton("https://www.guicedee.com", "images/guicedee.png", "Guiced EE")
				.addButton("https://github.com/GedMarc/JWebMP", "images/github.png", "Github")
				.addButton("https://www.facebook.com/JWebMP", "logo.png", "Facebook")
				.addButton("https://gitter.im/JWebMP/Lobby", "images/gitterim.ico", "Gitter IM");
	}
	
	private void notifications()
	{
		setNotificationsDropDown(new NotificationsDropDown("Notifications",
		                                                   icon(FontAwesomeIcons.bell_on),
		                                                   new BSBadgeDanger<>().setText("5"))
				                         .addNotification(new NotificationItem(icon(comment, Duotone), Bg_Success, Text_White,
				                                                               "Doug Dukes commented on Admin Dashboard", "1 min ago")
						                                          .setShaded(true))
				                         .addNotification(new NotificationItem(new Image<>(randomUserAvatar()),
				                                                               "Marc Magon", "Checkout Guiced EE! Portable all the way!")
						                                          .setAsUserMessage(true))
				                         .addNotification(new NotificationItem(new Image<>(randomUserAvatar()),
				                                                               "User", "Wow! This is so quick!")
						                                          .setAsUserMessage(true))
				                         .addNotification(new NotificationItem(icon(user_plus, Duotone), Bg_Secondary, Text_Dark,
				                                                               "A new user registered", "5 hours ago"))
				                         .addNotification(new NotificationItem(icon(user_friends, Duotone), Bg_Info, Text_Light,
				                                                               "Angelique commented on Admin", "6 days ago"))
				                         .addNotification(new NotificationItem(icon(user_visor, Duotone), Bg_Warning, Text_Light,
				                                                               "Marc Magon liked GuicedEE", "a while ago"))
				                         .addFooter(new NotificationFooter(icon(arrow_right, Duotone), "View all"))
		);
	}
	
	private void profile()
	{
		setProfileDropdown(new ProfileDropdown(new Image<>().bind("user.userImageURL"), "{{user.fullName}}")
				                   .setChevronIcon(icon(chevron_down, Light))
				                   .addHeaderItem("Welcome!")
				                   .addItem(icon(FontAwesomeIcons.user_cog, Duotone), "My Account")
				                   .addItem(icon(FontAwesomeIcons.user_hard_hat, Duotone), "Settings")
				                   .addItem(icon(users_crown, Duotone), "My Wallet",
				                            new BSBadgeSuccess<>(false).setText("3")
				                                                       .addClass(Float_Right)
				                   )
				                   .addItem(icon(user_lock, Duotone), "Settings")
				                   .addDivider()
				                   .addItem(icon(sign_out, Duotone), "Logout")
		)
		;
	}
	
	private void megaMenu()
	{
		
		addMegaMenu(new MegaMenu("Mega Menu", icon(chevron_down, Light))
				            .addSection(new MegaMenuSection("JQuery UI Components")
						                        .addItem("Core", null)
						                        .addItem("Date Time Picker", null)
						                        .addItem("Border Layout", null)
						                        .addItem("Colour Picker", null)
						                        .addItem("Theme Roller", null)
						                        .addItem("Quick Forms", null)
				            )
				            .addSection(new MegaMenuSection("Bootstrap Components")
						                        .addItem("Core", null)
						                        .addItem("Date Time Picker", null)
						                        .addItem("Dialog", null)
						                        .addItem("NyaSelect", null)
						                        .addItem("Tags", null)
						                        .addItem("Bootswatch", null)
						                        .addItem("Quick Forms", null)
				            )
				            .addSection(new MegaMenuSection("UI Kit Components")
						                        .addItem("Coming soon...", null)
				            )
				            .setAdvert(new MegaMenuAdvert("JWebMP", "All you will ever need!",
				                                          new BSButtonPrimary<>().setText("Get Running Now")
				                                                                 .addClass(Btn_Rounded)))
		);
	}
}
