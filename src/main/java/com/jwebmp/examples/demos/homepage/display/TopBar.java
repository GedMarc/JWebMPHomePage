package com.jwebmp.examples.demos.homepage.display;

import com.jwebmp.core.Feature;
import com.jwebmp.core.Page;
import com.jwebmp.core.base.html.*;
import com.jwebmp.core.base.html.attributes.LinkAttributes;
import com.jwebmp.core.base.html.interfaces.children.ListChildren;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.components.general.events.MenuIconSwapOnClick;
import com.jwebmp.examples.demos.homepage.display.login.LogoutEvent;
import com.jwebmp.examples.demos.homepage.display.menu.ChangeScreenEvent;
import com.jwebmp.examples.demos.homepage.display.privacy.GoToChatRoomScreenEvent;
import com.jwebmp.examples.demos.homepage.display.privacy.GoToPrivacyScreenEvent;
import com.jwebmp.examples.demos.homepage.display.termsandconditions.GoToTermsAndConditionsEvent;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayScreens;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.options.BSTypographyOptions;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;
import com.jwebmp.plugins.jqlayout.enumerations.JQLayoutArea;
import com.jwebmp.plugins.moment.Moment;

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
				addQuery("$('#btn-fullscreen1').on('click', function () {toggleFullScreen();});");
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
			Link buttonLink = new Link<>("/", "", "<span style=\"padding-left: 15px;\">JWebMP<i><small>&nbsp;rc1</small></i></span></a>").addClass("logo");
			logoLeft.add(new DivSimple<>().add(buttonLink));

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

		//Right Right Side
		List<ListChildren, ?, ?, ?> easyButtonList = new List<>(false);
		easyButtonList.addClass("list-inline float-right mb-0");

		ListItem expandButton = buildTopMenuItem(true, "btn-fullscreen1", "fal fa-expand-arrows-alt fa-2x noti-icon", "45px").addClass("strong");
		expandButton.addFeature(new MenuIconSwapOnClick(expandButton, FontAwesomeIcons.expand_arrows_alt, FontAwesomeIcons.compress_alt));
		easyButtonList.add(expandButton);

		Page<?> page = GuiceContext.getInstance(Page.class);

		ListItem donateButton = buildTopMenuItem(false, "donateButton", "fal fa-hand-holding-usd fa-2x fa-fw noti-icon", "45px", "https://paypal.me/MarcMagon", "_blank").addClass(
				"strong");
		easyButtonList.add(donateButton);

		nav.add(easyButtonList);

		Link donateLink = (Link) donateButton.getChildren()
		                                     .iterator()
		                                     .next();
		donateLink.getChildren()
		          .clear();
		donateLink.setDirectToAddress("#");

		donateLink.add(new Paragraph<>("<form target=\"_blank\" action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\" style=\"display:inline-flex;\">\n" +
		                               //"<div id='icon'><i class='far fa-eye-slash'></i></div>\n" +
		                               "<input type=\"hidden\" name=\"cmd\" value=\"_s-xclick\">\n" +
		                               "<input type=\"hidden\" name=\"hosted_button_id\" value=\"NTQB7LV8YTGF8\">\n" +
		                               "<button type=\"submit\" class=\"btn bg-transparent\" " +
		                               " style=\"padding-left:0px;padding-right:0px;\"" +
		                               " alt=\"PayPal - The safer, easier way to pay online!\">\n" +
		                               "    <i class='fal fa-hand-holding-usd fa-2x fa-fw noti-icon'></i>\n" +
		                               "</button>\n" +
		                               "<img alt=\"\" border=\"0\" src=\"https://www.paypalobjects.com/en_US/i/scr/pixel.gif\" width=\"1\" height=\"1\">\n" +
		                               "</form>\n").setTextOnly(true));

		//Middle Right Side (Search and Stuff)
		List<ListChildren, ?, ?, ?> searchList = new List<>().addClass("list-inline menu-left mb-0")
		                                                     .addStyle("height:71px;");

		ListItem leftMenuItem = new ListItem<>().addClass("float-left")
		                                        .setID("openWestButton")
		                                        .add(new Button<>().addClass("button-menu-mobile open-left waves-light waves-effect")
		                                                           .add(new Italic<>().addClass("far fa-bars")));

		if (page.isMobileOrSmartTablet())
		{
			leftMenuItem.addFeature(GuiceContext.getInstance(OuterLayout.class)
			                                    .createAddSlideToggleButton(JQLayoutArea.West, leftMenuItem));
		}
		else
		{
			leftMenuItem.addFeature(GuiceContext.getInstance(OuterLayout.class)
			                                    .createToggleButton(leftMenuItem, JQLayoutArea.West));
		}

		searchList.add(leftMenuItem);

		/*Form<?> searchForm = new Form<>().addAttribute("role", "search");
		InputTextType<?> searchInput = new InputTextType<>().setPlaceholder("Search...")
		                                                    .addClass("form-control");

		searchList.add(new ListItem<>().addClass("hide-phone app-search")
		                               .add(searchForm.add(searchInput)
		                                              .add(new Link("#").add(new Italic<>().addClass("fa fa-search")))));*/

		nav.add(searchList);
		easyButtonList.add(buildProfileDropDown());

		return nav;
	}

	private ListItem<?> buildTopMenuItem(boolean hidePhone, String id, String icon, String width)
	{
		return (buildTopMenuItem(hidePhone, id, icon, width, null, null));
	}

	private ListItem<?> buildTopMenuItem(boolean hidePhone, String id, String icon, String width, String url, String target)
	{
		ListItem<?> item = new ListItem<>();
		item.addClass("list-inline-item notification-list");
		if (hidePhone)
		{
			item.addClass("hide-phone");
		}

		Link<?> link = new Link<>(url == null ? "#" : url);
		if (target != null)
		{
			link.addAttribute(LinkAttributes.Target, target);
		}
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

	private ListItem buildProfileDropDown()
	{
		ListItem<?> profileDropDown = new ListItem<>().addClass("list-inline-item dropdown notification-list");
		Link notDropDownLink = new Link<>("#").addClass("nav-link dropdown-toggle arrow-none waves-light waves-effect")
		                                      .addAttribute(LinkAttributes.Data_Toggle, "dropdown")
		                                      .addAttribute("role", "button")
		                                      .addAttribute("aria-haspopup", "false")
		                                      .addAttribute("aria-expanded", "false")
		                                      .addStyle("width", "45px;")
		                                      .add(new Italic<>().addClass("fal fa-user noti-icon")
		                                                         .addStyle("width:45px;"));

		SessionProperties sessionProperties = GuiceContext.getInstance(SessionProperties.class);

		DivSimple<?> profileDropDownContent = new DivSimple<>();
		profileDropDownContent.addClass("dropdown-menu dropdown-menu-right dropdown-arrow dropdown-menu-lg");
		profileDropDownContent.addStyle("width:200px;");

		H5 headerText = new H5<>().addClass("font-16")
		                          .setRenderTextBeforeChildren(false);

		profileDropDownContent.add(new DivSimple<>().addClass("dropdown-item noti-title")
		                                            .add(headerText));

		if (sessionProperties.isLoggedIn())
		{
			Subscribers subscriber = sessionProperties.getSubscriber();
			headerText.setText(subscriber.getFirstName() != null ? subscriber.getFirstName() + " " + subscriber.getLastName() : "GENERAL");

			profileDropDownContent.add(buildDropDownNotificationItem("", "fal fa-id-card-alt fa-2x fa-fw", "Profile", (Date) null));
			profileDropDownContent.add(buildDropDownNotificationItem("", "fal fa-map-marker-question fa-2x fa-fw", "My Requests", (Date) null));
			profileDropDownContent.add(buildDropDownNotificationItem("", "fal fa-pen-nib fa-2x fa-fw", "Forums", (Date) null));

			Link<?> allLink = new Link<>().addClass("dropdown-item notify-item notify-all")
			                              .setText("LOGOUT");
			allLink.addEvent(new LogoutEvent(allLink));
			profileDropDownContent.add(allLink);
		}
		else
		{
			headerText.setText("GUEST");

			profileDropDownContent.add(
					buildDropDownNotificationItem("", "fal fa-id-card fa-2x",
					                              "Login", (Date) null)
							.addEvent(new ChangeScreenEvent(null, "p=HomePageScreen").setID(DisplayScreens.HomePageScreen.toString())));

			profileDropDownContent.add(buildDropDownNotificationItem("", "fal fa-file-signature fa-2x", "T & C's", (Date) null).addEvent(
					new GoToTermsAndConditionsEvent(null)));
			profileDropDownContent.add(
					buildDropDownNotificationItem("", "fal fa-user-secret fa-2x", "Privacy Statement", (Date) null).addEvent(
							new GoToPrivacyScreenEvent(null)));
			profileDropDownContent.add(buildDropDownNotificationItem("", "fal fa-microphone-alt fa-2x", "Chat Policy", (Date) null).addEvent(
					new GoToChatRoomScreenEvent(null)));
		}

		profileDropDown.add(notDropDownLink);
		profileDropDown.add(profileDropDownContent);
		profileDropDown.addStyle("width", "45px");
		return profileDropDown;
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

}
