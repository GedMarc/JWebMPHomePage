package com.jwebmp.plugins.themes.mintontheme;

import com.jwebmp.examples.apps.homepage.HomePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HomePageTest {

    @Test
    public void testPageRender()
    {
        System.out.println(new HomePage().toString(0));
    }


    @Test
    public void testBodyRender()
    {
        System.out.println(new HomePage().getBody().toString(0));
    }
}