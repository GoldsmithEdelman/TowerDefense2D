package helpers;

import org.lwjgl.Sys;

public class Clock
{
    private static boolean _pause = false;
    public static long _lastFrame;
    public static long _totalTime;
    public static float _d = 0; //temporary holder for delta
    public static float _multiplier = 1; // for the speed

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
        int delta = (int) (currentTime - _lastFrame);
        _lastFrame = getTime();
        if (delta * 0.001f > 0.05f) return 0.05f; //fix for the enemies movement when the window moves
        //         to be deleted
        //         System.out.println(delta * 0.0 1f);
        return delta * 0.001f;
    }

    public static float delta()
    {
        if (_pause)
            return 0;
        else
            return _d * _multiplier;
    }

    public static float totalTime()
    {
        return _totalTime;
    }

    public static float multiplier()
    {
        return _multiplier;
    }

    public static void update()
    {
        _d = getDelta();
        _totalTime += _d;
    }

    public static void changeMultiplier(int change)
    {
        if (_multiplier + change < -1 && _multiplier + change > 7)
        {

        }
        else
        {
            _multiplier += change;
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
