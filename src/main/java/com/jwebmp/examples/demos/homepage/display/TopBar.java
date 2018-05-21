package com.jwebmp.examples.demos.homepage.display;


import com.jwebmp.Feature;
import com.jwebmp.base.html.*;
import com.jwebmp.base.html.attributes.LinkAttributes;
import com.jwebmp.base.html.inputs.InputTextType;
import com.jwebmp.base.html.interfaces.children.ListChildren;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.display.login.LogoutEvent;
import com.jwebmp.examples.demos.homepage.display.privacy.GoToPrivacyScreenEvent;
import com.jwebmp.examples.demos.homepage.display.termsandconditions.GoToTermsAndConditionsEvent;
import com.jwebmp.examples.demos.homepage.entities.HomePageNotifications;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions;
import com.jwebmp.plugins.bootstrap4.options.BSTypographyOptions;
import com.jwebmp.plugins.jquerylayout.layout.enumerations.JQLayoutArea;
import com.jwebmp.plugins.moment.Moment;
import za.co.mmagon.guiceinjection.GuiceContext;

import java.util.Date;

/**
 * @author Marc Magon
 * @since 30 Jul 2017
 */
public class TopBar
		extends DivSimple<TopBar>
{

	private static final long serialVersionUID = 1L;

	/*
	 * Constructs a new TopBar
	 */
	public TopBar()
	{
		setID("topbar");
		addFeature(new Feature("FullScreenFeature")
		{
			@Override
			protected void assignFunctionsToComponent()
			{
				addQuery("$('#btn-fullscreen').on('click', function () {\n" + "            toggleFullScreen();\n" + "        });");
			}

		});
	}

	@Override
	public void preConfigure()
	{
		if (!isConfigured())
		{
			addClass("topbar");
			//Logo
			Div logoLeft = new DivSimple();
			logoLeft.addClass("topbar-left");
			Link buttonLink = new Link<>("/", "", "<i class=\"mdi mdi-radar\"></i> <span>JWebMP<i><small>&nbsp;beta</small></i></span></a>").addClass("logo");
			logoLeft.add(new DivSimple<>().addClass(BSTypographyOptions.Text_Center)
			                              .add(buttonLink));

			add(logoLeft);

			add(getNav());

		}
		super.preConfigure();
	}

	private Div getNav()
	{
		DivSimple<?> nav = new DivSimple<>();
		nav.addClass("navbar-custom");
		nav.setTag("nav");

		//Right RIght Side
		List<ListChildren, ?, ?, ?> easyButtonList = new List<>(false);
		easyButtonList.addClass("list-inline float-right mb-0");

		easyButtonList.add(buildTopMenuItem(true, "btn-fullscreen", "mdi mdi-crop-free noti-icon", "45px"));

		ListItem<?> rightSideOpener = buildTopMenuItem(false, "action_screen_opener", "mdi mdi-dots-horizontal noti-icon", "45px").addClass("right-bar-toggle");
		easyButtonList.add(rightSideOpener);
		rightSideOpener.addFeature(GuiceContext.getInstance(OuterLayout.class)
		                                       .createToggleButton(rightSideOpener, JQLayoutArea.East));


		Long highlightedNotificationCount = new HomePageNotifications().builder()
		                                                               .getCount();


		ListItem<?> notiDropDown = new ListItem<>().addClass("list-inline-item dropdown notification-list");
		Link notDropDownLink = new Link<>("#").addClass("nav-link dropdown-toggle arrow-none waves-light waves-effect")
		                                      .addAttribute(LinkAttributes.Data_Toggle, "dropdown")
		                                      .addAttribute("role", "button")
		                                      .addAttribute("aria-haspopup", "false")
		                                      .addAttribute("aria-expanded", "false")
		                                      .add(new Italic<>().addClass("mdi mdi-bell noti-icon"))
		                                      .add(new Span(highlightedNotificationCount + "").addClass("badge badge-pink noti-icon-badge"));


		DivSimple<?> notificationsDropDown = new DivSimple<>();
		notificationsDropDown.addClass("dropdown-menu dropdown-menu-right dropdown-arrow dropdown-menu-lg");


		notificationsDropDown.add(new DivSimple<>().addClass("dropdown-item noti-title")
		                                           .add(new H5<>().addClass("font-16")
		                                                          .add(new Span<>().setText(highlightedNotificationCount + "")
		                                                                           .addClass("badge badge-danger float-right"))
		                                                          .setRenderTextBeforeChildren(false)
		                                                          .setText("Notifications")));

		java.util.List<HomePageNotifications> homePageNotificationsList = new HomePageNotifications().findAll();
		homePageNotificationsList.forEach(a ->
		                                  {
			                                  notificationsDropDown.add(buildDropDownNotificationItem(a.getClazz(), a.getIcon(), a.getTitle(), a.getDate()));
		                                  });


		Link<?> allLink = new Link<>().addClass("dropdown-item notify-item notify-all")
		                              .setText("View All");
		notificationsDropDown.add(allLink);


		notiDropDown.add(notDropDownLink);
		notiDropDown.add(notificationsDropDown);
		notiDropDown.addStyle("width", "45px");
		easyButtonList.add(notiDropDown);
		nav.add(easyButtonList);


		//Middle Right Side (Search and Stuff)
		List<ListChildren, ?, ?, ?> searchList = new List<>().addClass("list-inline menu-left mb-0");


		ListItem leftMenuItem = new ListItem<>().addClass("float-left")
		                                        .add(new Button<>().addClass("button-menu-mobile open-left waves-light waves-effect")
		                                                           .add(new Italic<>().addClass("mdi mdi-menu")));
		leftMenuItem.addFeature(GuiceContext.getInstance(OuterLayout.class)
		                                    .createToggleButton(leftMenuItem, JQLayoutArea.West));

		searchList.add(leftMenuItem);


		Form<?> searchForm = new Form<>().addAttribute("role", "search");
		InputTextType<?> searchInput = new InputTextType<>().setPlaceholder("Search...")
		                                                    .addClass("form-control");

		searchList.add(new ListItem<>().addClass("hide-phone app-search")
		                               .add(searchForm.add(searchInput)
		                                              .add(new Link("#").add(new Italic<>().addClass("fa fa-search")))));

		nav.add(searchList);
		easyButtonList.add(buildProfileDropDown());

		return nav;
	}

	private ListItem<?> buildTopMenuItem(boolean hidePhone, String id, String icon, String width)
	{
		ListItem<?> item = new ListItem<>();
		item.addClass("list-inline-item notification-list");
		if (hidePhone)
		{
			item.addClass("hide-phone");
		}

		Link<?> link = new Link<>("#");
		link.addClass("nav-link waves-light waves-effect text-center");
		link.setID(id);
		if (icon != null)
		{
			Italic<?> iconHtml = new Italic<>();
			iconHtml.addClass(icon);
			link.add(iconHtml);
		}
		link.addStyle("width", width);
		item.add(link);
		return item;
	}

	private Link<?> buildDropDownNotificationItem(String backgroundClass, String icon, String title, Date when)
	{
		Link<?> link = new Link("#");
		link.addClass("dropdown-item notify-item");

		DivSimple<?> iconDiv = new DivSimple<>().addClass("notify-icon")
		                                        .addClass(backgroundClass)
		                                        .add(new Italic<>().addClass(icon));
		link.add(iconDiv);

		Paragraph details = new Paragraph<>().addClass("notify-details");
		details.setText(title);
		details.setRenderTextBeforeChildren(true);
		if (when != null)
		{
			Moment<?> moment = new Moment<>(when);
			moment.addClass(BSTypographyOptions.Text_Muted);
			moment.setTag("small");
			details.add(moment);
		}
		link.add(details);

		return link;
	}

	private ListItem buildProfileDropDown()
	{
		ListItem<?> profileDropDown = new ListItem<>().addClass("list-inline-item dropdown notification-list");
		Link notDropDownLink = new Link<>("#").addClass("nav-link dropdown-toggle arrow-none waves-light waves-effect")
		                                      .addAttribute(LinkAttributes.Data_Toggle, "dropdown")
		                                      .addAttribute("role", "button")
		                                      .addAttribute("aria-haspopup", "false")
		                                      .addAttribute("aria-expanded", "false")
		                                      .add(new Italic<>().addClass("fa fa-user noti-icon"));

		SessionProperties sessionProperties = GuiceContext.getInstance(SessionProperties.class);

		DivSimple<?> profileDropDownContent = new DivSimple<>();
		profileDropDownContent.addClass("dropdown-menu dropdown-menu-right dropdown-arrow dropdown-menu-lg");

		H5 headerText = new H5<>().addClass("font-16")
		                          .setRenderTextBeforeChildren(false);

		profileDropDownContent.add(new DivSimple<>().addClass("dropdown-item noti-title")
		                                            .add(headerText));


		if (sessionProperties.isLoggedIn())
		{
			Subscribers subscriber = sessionProperties.getSubscriber();
			headerText.setText(subscriber.getFirstName() != null ? subscriber.getFirstName() + " " + subscriber.getLastName() : "My Profile");

			profileDropDownContent.add(buildDropDownNotificationItem(BSBackgroundOptions.Bg_Success.toString(), "mdi mdi-comment-account", "Account Details", (Date) null));
			profileDropDownContent.add(buildDropDownNotificationItem(BSBackgroundOptions.Bg_Success.toString(), "mdi mdi-comment-account", "My Plugin Requests", (Date) null));
			profileDropDownContent.add(buildDropDownNotificationItem(BSBackgroundOptions.Bg_Success.toString(), "mdi mdi-comment-account", "Forums", (Date) null));

			Link<?> allLink = new Link<>().addClass("dropdown-item notify-item notify-all")
			                              .setText("Logout");
			allLink.addEvent(new LogoutEvent(allLink));
			profileDropDownContent.add(allLink);
		}
		else
		{
			headerText.setText("Guest");
			profileDropDownContent.add(buildDropDownNotificationItem(BSBackgroundOptions.Bg_Success.toString(), "mdi mdi-comment-account", "Login", (Date) null));
			profileDropDownContent.add(buildDropDownNotificationItem(BSBackgroundOptions.Bg_Success.toString(), "mdi mdi-comment-account", "T & C's", (Date) null).addEvent(
					new GoToTermsAndConditionsEvent(null)));
			profileDropDownContent.add(
					buildDropDownNotificationItem(BSBackgroundOptions.Bg_Success.toString(), "mdi mdi-comment-account", "Privacy Statement", (Date) null).addEvent(
							new GoToPrivacyScreenEvent(null)));
			profileDropDownContent.add(buildDropDownNotificationItem(BSBackgroundOptions.Bg_Success.toString(), "mdi mdi-comment-account", "Chat Policy", (Date) null));
		}

		profileDropDown.add(notDropDownLink);
		profileDropDown.add(profileDropDownContent);
		profileDropDown.addStyle("width", "45px");
		return profileDropDown;
	}

	private Link<?> buildDropDownNotificationItem(String backgroundClass, String icon, String title, String description, Date when)
	{
		Link<?> link = new Link("#");
		link.addClass("dropdown-item notify-item");

		DivSimple<?> iconDiv = new DivSimple<>().addClass("notify-icon")
		                                        .addClass(backgroundClass)
		                                        .add(new Italic<>().addClass(icon));
		link.add(iconDiv);

		Paragraph details = new Paragraph<>().addClass("notify-details");
		details.setText(title);
		details.setRenderTextBeforeChildren(true);

		Paragraph descriptions = new Paragraph<>();
		descriptions.setText(description);
		descriptions.setRenderTextBeforeChildren(true);
		details.add(descriptions);

		if (when != null)
		{
			Moment<?> moment = new Moment<>(when);
			moment.addClass(BSTypographyOptions.Text_Muted);
			moment.setTag("small");
			details.add(moment);
		}
		link.add(details);

		return link;
	}

	private Link<?> buildDropDownNotificationItem(String backgroundClass, String icon, String detailsParagraph, String text)
	{
		Link<?> link = new Link("#");
		link.addClass("dropdown-item notify-item");

		DivSimple<?> iconDiv = new DivSimple<>().addClass("notify-icon")
		                                        .addClass(backgroundClass)
		                                        .add(new Italic<>().addClass(icon));
		link.add(iconDiv);

		Paragraph details = new Paragraph<>().addClass("notify-details");
		details.setText(detailsParagraph);
		details.setRenderTextBeforeChildren(true);
		if (text != null)
		{
			Span sp = new Span();
			sp.setTag("small");
			sp.addClass(BSTypographyOptions.Text_Muted);
			details.add(sp);
		}
		link.add(details);


		return link;
	}
}
