package data.startup;

/**
 * Pending POA for cleaning up, finishing basic features, writing more tests and expanding
 * 
 * Maze algorithm
 * 
 * AI for enemy waves
 * AI for towers 
 * AI waves vs towers 
 * 
 */

import data.menu.MainMenu;

public class StartUp
{
    @SuppressWarnings("unused")
    private MainMenu _menu;

    public StartUp()
    {
        _menu = new MainMenu();
    }

    public static void main(String[] args)
    {
        new StartUp();
    }
}
