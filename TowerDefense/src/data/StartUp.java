package data;

import static helpers.Artist.*;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import helpers.Clock;
import helpers.StateManager;

public class StartUp
{
    public StartUp()
    {
        MainMenu menu = new MainMenu();
    }

    public static void main(String[] args)
    {
        new StartUp();
    }
}
