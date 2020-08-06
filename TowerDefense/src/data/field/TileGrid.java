package data.field;

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
    private int _tilesWidth;
    private int _tilesHeight;

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
        this._tilesWidth = newMap[0].length;
        this._tilesHeight = newMap.length;
        _map = new Tile[_tilesWidth][_tilesHeight];
        for (int i = 0; i < _map.length; i++)
        {
            for (int j = 0; j < _map[i].length; j++)
            {
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
                    break;
                case 3:
                    _map[i][j] = new Tile(i * 64, j * 64, 64, 64,
                            TileType.Menu);
                    break;
                
            	case 4:
            		_map[i][j] = new Tile(i * 64, j * 64, 64, 64,
                        TileType.SPAWN);
            		break;
            
            	case 5:
            		_map[i][j] = new Tile(i * 64, j * 64, 64, 64,
            				TileType.RED);
            		break;
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
     * if by the edges returns a null tile
     * 
     * @param x
     * @param y
     * @return
     */

    public Tile getTile(int xTile, int yTile)
    {
        if (xTile < _tilesWidth && yTile < _tilesHeight && xTile > -1
                && yTile > -1)
            return _map[xTile][yTile];
        else
            return new Tile(0, 0, 0, 0, TileType.NULL);
    }

    public TileType getTileType(int xTile, int yTile)
    {
    	System.out.println(xTile);
        if (xTile < _tilesWidth && yTile < _tilesHeight && xTile > -1
                && yTile > -1) {
        	return _map[xTile][yTile].getType();
        	
        }
        else
            return new Tile(0, 0, 0, 0, TileType.NULL).getType();
    }

    public void draw()
    {
        for (int i = 0; i < _map.length; i++)
        {
            for (int j = 0; j < _map[i].length; j++)
            {
                _map[i][j].draw();
            }
        }
    }

    /**
     * @return the _tilesWidth
     */
    public int getTilesWidth()
    {
        return _tilesWidth;
    }

    /**
     * @param tilesWidth the tilesWidth to set
     */
    public void setTilesWidth(int tilesWidth)
    {
        this._tilesWidth = tilesWidth;
    }

    /**
     * @return the _tilesHeight
     */
    public int getTilesHeight()
    {
        return _tilesHeight;
    }

    /**
     * @param tilesHeight the tilesHeight to set
     */
    public void setTilesHeight(int tilesHeight)
    {
        this._tilesHeight = tilesHeight;
    }

}
