package helpers;

import org.lwjgl.Sys;

public class Clock
{
    private static boolean _pause = false;
    public static long lastFrame;
    public static long totalTime;
    public static float d = 0; //temporary holder for delta
    public static float multiplier = 1; // for the speed

    public static long getTime()
    {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }

    /**
     * for moving entities, moving towers etc
     * 
     * time between now and last update to the game
     * @return
     */

    public static float getDelta()
    {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta * 0.01f;
    }

    public static float delta()
    {
        if (_pause)
            return 0;
        else
            return d * multiplier;
    }

    public static float totalTime()
    {
        return totalTime;
    }

    public static float multiplier()
    {
        return multiplier;
    }

    public static void update()
    {
        d = getDelta();
        totalTime += d;
    }

    public static void changeMultiplier(int change)
    {
        if (multiplier + change < -1 && multiplier + change > 7)
        {

        }
        else
        {
            multiplier += change;
        }
    }

    public static void pause()
    {
        if (_pause)
            _pause = false;
        else
            _pause = true;
    }

}
