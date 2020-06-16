package data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import static helpers.Artist.*;

import java.util.ArrayList;

public class Player
{
    private TileGrid _grid;
    private TileType[] _types;
    private int _index;
    private WaveManager _waveManager;
    private ArrayList<TowerCannon> _towerList;

    public Player(TileGrid grid, WaveManager waveManager)
    {
        this._grid = grid;
        this._types = new TileType[3];
        this._types[0] = TileType.Dirt;
        this._types[1] = TileType.Grass;
        this._types[2] = TileType.Water;
        this._index = 0;

        this._waveManager = waveManager;
        this._towerList = new ArrayList<TowerCannon>();

    }

    /**
     * input from the mouse
     */

    public void setTile()
    {
        _grid.setTile((int) Math.floor(Mouse.getX() / 64),
                (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64),
                _types[_index]);
    }

    /**
     * updates on click or press 
     */

    public void update()
    {
        for (TowerCannon tower : _towerList)
        {
            tower.update();
        }

        // mouse input
        if (Mouse.isButtonDown(0))
        {
            setTile();
        }
        // keyboard input
        while (Keyboard.next())
        {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT
                    && Keyboard.getEventKeyState()) //Keyboard.getEventKeyState() gives one per press
            {
                moveIndex();
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_T
                    && Keyboard.getEventKeyState())
            {
                _towerList.add(new TowerCannon(
                        quickLoadPngTexture("cannonbase"), _grid.getTile(8, 7),
                        10, _waveManager.getCurrentWave()
                            .getEnemies()));
            }
        }
    }

    private void moveIndex()
    {
        _index++;
        if (_index > _types.length - 1)
        {
            _index = 0;
        }
    }

}
