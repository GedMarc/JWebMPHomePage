package com.jwebmp.plugins.themes.mintontheme.topbar.profile;

import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;
import org.junit.jupiter.api.Test;

import static com.jwebmp.plugins.fontawesome5.FontAwesome.icon;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons.chevron_down;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons.user_circle;
import static com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles.Duotone;

class ProfileDropdownTest
{
	@Test
	void testProfileDropDown()
	{
		ProfileDropdown pd = new ProfileDropdown(icon(user_circle, Duotone), "{{user.fullName}}");
		pd.setChevronIcon(icon(chevron_down, Duotone));
		
		pd.addHeaderItem("Welcome!");
		pd.addItem(FontAwesome.icon(FontAwesomeIcons.user_cog, Duotone), "My Account");
		pd.addItem(FontAwesome.icon(FontAwesomeIcons.user_hard_hat, Duotone), "Settings");
		System.out.println(pd.toString(0));
	}
	
}