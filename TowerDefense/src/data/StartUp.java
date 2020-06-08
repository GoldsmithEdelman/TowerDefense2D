package data;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class StartUp
{
    public StartUp()
    {
        Display.setTitle("Tower Defense");
        try
        {
            Display.setDisplayMode(new DisplayMode(600, 400));
            Display.create();
        }
        catch (LWJGLException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new StartUp();
    }
}
