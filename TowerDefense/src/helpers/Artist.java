package helpers;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Artist
{
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 960;

    public static void beginSession()
    {
        Display.setTitle("Tower Defense");
        try
        {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
        }
        catch (LWJGLException e)
        {
            e.printStackTrace();
        }
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D); //enables dropping textures to the screen
        glEnable(GL_BLEND); //enables blending on the alpha channel (transparency)
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //setting for the alpha channel 
    }

    public static void drawRectangleTexture(Texture texture, float x, float y,
            float width, float height)
    {
        texture.bind(); // binding texture to openGl. commands that draw to screen will be modifying that texture, until new texture is binded.
        glTranslatef(x, y, 0); // z := 0 -> cause the game is 2D; uses local coordinates instead of global (0,0 in global is top left of the screen)
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0); // top left corner of a texture (local coordinates)
        glVertex2f(0, 0);
        glTexCoord2f(1, 0); // top right corner of the texture (local coordinates)
        glVertex2f(width, 0);
        glTexCoord2f(1, 1); // bottom right corner of the texture (local coordinates)
        glVertex2f(width, height);
        glTexCoord2f(0, 1); // bottom left corner of the texture (local coordinates)
        glVertex2f(0, height);
        glEnd();
        glLoadIdentity(); // replaces the current matrix with the identity matrix. prevents screen popping and other strange screen behaviors. insert before glEnd() to check why it is important 
    }

    /**
     * 
     * Loads textures
     * 
     * @param path
     * @param filetype
     * @return
     * 
     */

    public static Texture loadTexture(String path, String fileType)
    {
        Texture texture = null;
        InputStream input = ResourceLoader.getResourceAsStream(path);
        try
        {
            texture = TextureLoader.getTexture(fileType, input);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return texture;
    }

    /**
     * 
     * Less typing by loading textures 
     * 
     * @param name
     * @return
     */

    public static Texture quickLoadPngTexture(String name)
    {
        Texture texture = null;
        texture = loadTexture("resources/" + name + ".png", "PNG");
        return texture;
    }

    public static void drawRectangle(float x, float y, float width,
            float height)
    {
        glBegin(GL_QUADS);
        glVertex2f(x, y); // top left
        glVertex2f(x + width, y); // top right
        glVertex2f(x + width, y + height); // bottom right
        glVertex2f(x, y + height); // bottom left
        glEnd();
    }
}
