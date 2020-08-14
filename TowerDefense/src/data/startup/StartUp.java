package data.startup;

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
