package com.jwebmp.plugins.themes.mintontheme.leftsidebar;

import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserOptionsMenuTest
{
    @Test
    public void testOptionsMenu()
    {
        UserOptionsMenu menu = new UserOptionsMenu();

        menu.addItem(FontAwesome.icon(FontAwesomeIcons.bell_on),"Test Menu Item",null);
        System.out.println(menu.toString(0));
    }
}