package helpers;

import data.editor.Editor;
import data.menu.MainMenu;
import data.startup.Game;

public class StateManager
{
    public static enum GameState
    {
        MAINMENU, GAME, EDITOR, LEVEL1, LEVEL2, LEVEL3
    }

    public static GameState _gameState = GameState.MAINMENU;
    public static MainMenu _mainMenu;
    public static Game _game;
    public static Editor _editor;
    private static boolean run = true;

    static int[][] map = {
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, // map is 20x15
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0}};

    static int[][] map1 = {
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, // map is 20x15
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 5, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    static int[][] map2 = {
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, // map is 20x15
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 5, 5, 5, 2, 0, 2, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0}};

    static int[][] map3 = {
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, // map is 20x15
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0},
            {2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 5, 2, 2, 2, 2, 2, 5, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 5, 0, 2, 0, 0, 0},
            {0, 5, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 5, 5, 5, 2, 0, 0, 0},
            {0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0}};

    static int[][] editormap = {
            {0, 1, 2, 3, 0, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5}, // map is 20x15
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0}};

    // Matrices for enemy waves 
    static int[][] Level1 = {{1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2},
            {1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 1, 2},
            {1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
            {2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1},
            {2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3},
            {2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
            {4, 1, 1, 1, 1, 1, 3, 3, 3, 3, 2, 2, 2, 2},};
    static int[][] Level2 = {{1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2},
            {1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 1, 2},
            {1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
            {2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1},
            {2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3},
            {2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
            {4, 1, 1, 1, 1, 1, 3, 3, 3, 3, 2, 2, 2, 2},};

    static int[][] Level22 = {{1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2},
            {1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 1, 2},
            {1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
            {2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1},
            {2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3},
            {2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
            {4, 1, 1, 1, 1, 1, 3, 3, 3, 3, 2, 2, 2, 2},};

    static int[][] Level3 = {{1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2},
            {1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 1, 2},
            {1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
            {2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1},
            {2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3},
            {2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
            {4, 1, 1, 1, 1, 1, 3, 3, 3, 3, 2, 2, 2, 2},            
            {2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3},
            {2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
            {4, 1, 1, 1, 1, 1, 3, 3, 3, 3, 2, 2, 2, 2},
            {2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3},
            {2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3},
            {4, 3, 3, 3, 4, 3, 3, 3, 3, 3, 4, 4, 3, 3},};

    static int[][] customEnemyMap = {{1, 3}, {1, 2, 1, 1}, {3}};

    static int[][] customEnemyMap2 = {{1}, {1, 2, 1, 1}, {1, 2, 2}};

    public static void update()
    {
        switch (_gameState)
        {
        case MAINMENU:
            if (_mainMenu == null)
            {
                _mainMenu = new MainMenu();
            }
            break;
        case GAME:
            if (_game == null)
            {
                _game = new Game(map);
            }
            _game.update();
            if (!_game.getrun())
            {
                run = _game.getrun();
                _game = null;
            }
            break;
        case EDITOR:
            if (_editor == null)
            {
                _editor = new Editor(editormap);
            }
            _editor.update();
            if (!_editor.getrun())
            {
                run = _editor.getrun();
                _editor = null;
            }
            break;

        case LEVEL1:
            gamestart(map1, 19, 4, Level1, "LEVEL1", 60);
            break;

        case LEVEL2:
            gamestart(map2, 0, 2, Level2, 0, 5, Level22, "LEVEL2", 30);
            break;

        case LEVEL3:
            gamestart(map3, 0, 2, Level3, "LEVEL3", 15);
            break;
        }
    }

    private static void gamestart(int[][] map_, int x, int y, int[][] wavemap,
            String s, int playerhealt)
    {
        if (_game == null)
        {
            _game = new Game(map_, wavemap, x, y, s, playerhealt);
        }
        _game.update();
        if (!_game.getrun())
        {
            run = _game.getrun();
            _game = null;
        }
    }

    private static void gamestart(int[][] map_, int x, int y, int[][] wavemap,
            int x2, int y2, int[][] wavemap2, String s, int playerhealt)
    {
        if (_game == null)
        {
            _game = new Game(map_, wavemap, x, y, wavemap2, x2, y2, s,
                    playerhealt);
        }
        _game.update();
        if (!_game.getrun())
        {
            run = _game.getrun();
            _game = null;
        }
    }

    // could be usefull later on
    public static void setState(GameState state)
    {
        _gameState = state;
    }

    public static boolean getrun()
    {
        return run;
    }

    public static void run()
    {
        run = true;
    }
    
    public Editor getEditor() {
    	return _editor;
    }

}
