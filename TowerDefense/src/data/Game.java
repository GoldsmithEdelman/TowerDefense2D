package data;

import static helpers.Artist.quickLoadPngTexture;

public class Game
{
    private TileGrid _grid;
    private Player _player;
    private WaveManager _waveManager;
    public static final int TILE_SIZE = 64;

    // to be deleted
    //temporary for testing
    //    TowerCannon tower;

    public Game(int[][] map)
    {
        _grid = new TileGrid(map);
        _waveManager = new WaveManager(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(0, 1), _grid, 64, 64, 70,25), 2, 2);
        _player = new Player(_grid, _waveManager);
        //        to be deleted
        //        tower = new TowerCannon(quickLoadPngTexture("cannonbase"),
        //                _grid.getTile(8, 7), 20);

    }

    public void update()
    {
        // order is important; grid first then towers on top
        _grid.draw();
        _waveManager.update();
        _player.update();
        
        //          to be deleted
        //        tower.update();
    }
}
