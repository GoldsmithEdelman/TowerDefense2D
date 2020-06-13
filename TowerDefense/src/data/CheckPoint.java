package data;

public class CheckPoint
{
    private Tile _tile;
    private int _x, _y; //direction/from the enemy's current position

    public CheckPoint(Tile tile, int x, int y)
    {
        this._tile = tile;
        this._x = x;
        this._y = y;
    }

    /**
     * @return the tile
     */
    public Tile getTile()
    {
        return _tile;
    }

    /**
     * @param tile the tile to set
     */
    public void setTile(Tile tile)
    {
        this._tile = tile;
    }

    /**
     * @return the x
     */
    public int getX()
    {
        return _x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x)
    {
        this._x = x;
    }

    /**
     * @return the y
     */
    public int getY()
    {
        return _y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y)
    {
        this._y = y;
    }

}
