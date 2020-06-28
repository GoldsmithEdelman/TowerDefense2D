package data;

import static helpers.Artist.beginSession;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lwjgl.opengl.Display;

import helpers.*;
import helpers.StateManager.GameState;

public class MainMenu extends JFrame
{

    private Container _content;
    private MainMenu _mainMenu;
    private JButton _play;
    private JButton _editor;
    private JButton _quit;

    public MainMenu()
    {
        _mainMenu = this;

        createButtons();
        createLayout();
        createListeners();
        setWindow();
        this.setVisible(true);

    }

    private void setWindow()
    {
        this.setTitle("TowerDefence2D: Main Menu");
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.WHITE);
        this.setMinimumSize(new Dimension(400, 200));
    }

    private void createLayout()
    {
        _content = this.getContentPane();
        _content.setLayout(new GridLayout(3, 2));
        _content.add(_play);
        _content.add(_editor);
        _content.add(_quit);
    }

    private void createButtons()
    {
        _play = new JButton("Play");
        _editor = new JButton("Editor");
        _quit = new JButton("Quit");
    }

    private void createListeners()
    {
        _play.addActionListener(new MainMenuActionListener("play", this));
        _editor.addActionListener(new MainMenuActionListener("edito", this));
        _quit.addActionListener(new MainMenuActionListener("quit", this));

        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                _mainMenu.doCommand("quit");
            }
        });

    }

    public void doCommand(String command)
    {
        if (command.equals("play"))
        {
            this.setVisible(false);
            this.dispose();
            beginSession();
            StateManager.setState(GameState.GAME);
            StateManager.run();
            while (!Display.isCloseRequested())
            {

                Clock.update(); //always update the clock before drawing enemies
                StateManager.update();
                if(StateManager.getrun() == false) {
                	this.setVisible(true);
                	break;
                }
                Display.update();
                Display.sync(60);
            }
            Display.destroy();
        }
        else if (command.equals("editor"))
        {
        }
        else if (command.equals("quit"))
        {
            this.dispose();
            System.exit(0);
        }
    }
}
