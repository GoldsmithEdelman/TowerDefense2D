package data;

import static helpers.Artist.*;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import helpers.Clock;

public class StartUp
{
    public StartUp()
    {
        beginSession();

        //temporary array
        int[][] map = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // map is 20x15
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        TileGrid grid = new TileGrid(map);
        grid.setTile(3, 4, TileType.Dirt); //test change Grass to Dirt
        grid.setTile(3, 5, grid.getTile(3, 4)
            .getType()); //test setter with getter, change one below Grass to Dirt
        Enemy enemyTest = new Enemy(quickLoadPngTexture("enemy"),
                grid.getTile(2, 2), grid, 64, 64, 6);
        Wave waveTest = new Wave(20, enemyTest);
        Player playerTest = new Player(grid);

        // to be deleted        
        //        Tile tile = new Tile(0, 0, 64, 64, TileType.Grass); // makes an actual tile
        //        Tile tile2 = new Tile(0, 64, 64, 64, TileType.Water);

        //to be deleted
        //        Texture textureWaterTest = loadTexture("resources/water.png", "PNG"); //test whether textures load
        //        Texture textureGrassTest = loadTexture("resources/grass.png", "PNG"); //test whether textures load
        //        Texture quickLoadWaterTest = quickLoadPngTexture("water"); // test quick load
        //        Texture quickLoadGrassTest = quickLoadPngTexture("grass"); // test quick load

        //moved to Artist
        //        Display.setTitle("Tower Defense");
        //        try
        //        {
        //            Display.setDisplayMode(new DisplayMode(600, 400));
        //            Display.create();
        //        }
        //        catch (LWJGLException e)
        //        {
        //            e.printStackTrace();
        //        }
        //
        //        glMatrixMode(GL_PROJECTION);
        //        glLoadIdentity();
        //        glOrtho(0, 600, 400, 0, 1, -1);
        //        glMatrixMode(GL_MODELVIEW);

        // to be deleted        
        //        float width = 50;
        //        float height = 50;
        //        float x = 100;
        //        float y = 100;

        while (!Display.isCloseRequested())
        {
            // to be deleted            
            //            glBegin(GL_LINES);
            //            glVertex2f(10, 10);
            //            glVertex2f(100, 100);
            //            glEnd();

            //moved to Atist drawRectangle
            //            glBegin(GL_QUADS);
            //            glVertex2f(x, y); // top left
            //            glVertex2f(x + width, y); // top right
            //            glVertex2f(x + width, y + height); // bottom right
            //            glVertex2f(x, y + height); // bottom left
            //            glEnd();

            // to be deleted            
            //            drawRectangle(50, 50, 100, 100);
            //            drawRectangle(150, 150, 100, 100);

            // to be deleted            
            //            drawRectangleTexture(textureWaterTest, 0, 0, 64, 64);
            //            drawRectangleTexture(textureGrassTest, 64, 0, 64, 64);
            //            drawRectangleTexture(quickLoadWaterTest, 64, 64, 64, 64);
            //            drawRectangleTexture(quickLoadGrassTest, 0, 64, 64, 64);

            // to be deleted -> replaced by the Tile.draw() method            
            //            drawRectangleTexture(tile.getTexture(), tile.getX(), tile.getY(),
            //                    tile.getWidth(), tile.getHeight());
            //            drawRectangleTexture(tile2.getTexture(), tile2.getX(), tile2.getY(),
            //                    tile2.getWidth(), tile2.getHeight());

            // to be deleted
            //            tile.draw();
            //            tile2.draw();

            Clock.update(); //always update the clock before drawing enemies
            // to be deleted
            //            enemyTest.update();

            grid.draw();
            waveTest.update();
            playerTest.update();
            // to be deleted
            //            enemyTest.draw();

            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }

    public static void main(String[] args)
    {
        new StartUp();
    }
}
