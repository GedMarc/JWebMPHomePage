package com.jwebmp.examples.apps.homepage;

import com.jwebmp.plugins.themes.mintontheme.leftsidebar.LeftMenuUserBox;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;

import static com.jwebmp.plugins.fontawesome5.FontAwesome.icon;
import static com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles.Duotone;

public class DemoUserBox extends LeftMenuUserBox
{
	public DemoUserBox()
	{
		setProfileImageSourceBinding("user.userImageURL")
				.setFullNameBinding("user.fullName")
				.setRoleNameBinding("user.roleName");
		
		getUserMenu().addItem(icon(FontAwesomeIcons.user, Duotone), "My Account");
		getUserMenu().addItem(icon(FontAwesomeIcons.user_cog, Duotone), "Settings");
		getUserMenu().addItem(icon(FontAwesomeIcons.user_lock, Duotone), "Lock Screen");
		getUserMenu().addItem(icon(FontAwesomeIcons.sign_out, Duotone), "Logout");
	}
}
