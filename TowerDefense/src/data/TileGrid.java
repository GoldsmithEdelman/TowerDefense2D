package data;

import static helpers.Artist.*;

/**
 * 
 * base for a rudimentary tile editor
 * 
 * @author 
 *
 */

public class TileGrid
{
    public Tile[][] _map;

    /**
     * Default constructor for a grass-grid
     * 
     */

    public TileGrid()
    {
        _map = new Tile[20][15];
        for (int i = 0; i < _map.length; i++)
        {
            for (int j = 0; j < _map[i].length; j++)
            {
                _map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Grass);
            }
        }
    }

    /**
     * allows adding multiple tile types via switch
     * @param newMap
     */

    public TileGrid(int[][] newMap)
    {
        _map = new Tile[20][15];
        for (int i = 0; i < _map.length; i++)
        {
            for (int j = 0; j < _map[i].length; j++)
            {
                //to be deleted                
                //                if (newMap[j][i] == 0)
                //                {
                //                    _map[i][j] = new Tile(i * 64, j * 64, 64, 64,
                //                            TileType.Grass);
                //                }
                //                else
                //                {
                //                    _map[i][j] = new Tile(i * 64, j * 64, 64, 64,
                //                            TileType.Water);
                //                }
                switch (newMap[j][i])
                {
                case 0:
                    _map[i][j] = new Tile(i * 64, j * 64, 64, 64,
                            TileType.Grass);
                    break;
                case 1:
                    _map[i][j] = new Tile(i * 64, j * 64, 64, 64,
                            TileType.Water);
                    break;
                case 2:
                    _map[i][j] = new Tile(i * 64, j * 64, 64, 64,
                            TileType.Dirt);
                }
            }
        }
    }

    /**
     * 
     * Setter for a specific tile. Allows to dynamically change the map
     * 
     * @param xCoord
     * @param yCoord
     * @param type
     */

    public void setTile(int x, int y, TileType type)
    {
        _map[x][y] = new Tile(x * 64, y * 64, 64, 64, type);
    }

    /**
     * 
     * Returns a type of a specific tile. Allows to dynamically access the map
     * 
     * @param x
     * @param y
     * @return
     */

    public Tile getTile(int xTile, int yTile)
    {
        return _map[xTile][yTile];
    }

    public void draw()
    {
        for (int i = 0; i < _map.length; i++)
        {
            for (int j = 0; j < _map[i].length; j++)
            {
                // to be deleted
                //                Tile dummyTile = _map[i][j];
                //                drawRectangleTexture(dummyTile.getTexture(), dummyTile.getX(),
                //                        dummyTile.getY(), dummyTile.getWidth(),
                //                        dummyTile.getHeight());
                _map[i][j].draw();
            }
        }
    }
}
