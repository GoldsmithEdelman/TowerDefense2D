package data.menu;

import static helpers.Artist.beginSession;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
    private JButton _level1button;
    private JButton _level2button;
    private JButton _level3button;
    private JPanel _hauptpanel;
    private JPanel _Level1;
    private JPanel _Level2;
    private JPanel _Level3;

    public MainMenu()
    {
        _mainMenu = this;

        createButtons();
        createPanel();
        createLayout();
        createListeners();
        setWindow();
        readData();
        this.setVisible(true);

    }
    private void createPanel() {
    	_hauptpanel = new JPanel();
    	_Level1 = new JPanel();
    	_Level2 = new JPanel();
    	_Level3 = new JPanel();
    	_Level1.setBackground(Color.RED);
    	_Level2.setBackground(Color.RED);
    	_Level3.setBackground(Color.RED);
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
        _content.setLayout(new GridLayout(3, 1));
        _content.add(_play);
        _content.add(_level1button);
        _content.add(_Level1);
        _content.add(_editor);
        _content.add(_level2button);
        _content.add(_Level2);
        _content.add(_quit);
        _content.add(_level3button);
        _content.add(_Level3);
    }

    private void createButtons()
    {
        _play = new JButton("Play");
        _editor = new JButton("Editor");
        _quit = new JButton("Quit");
        _level1button = new JButton("Level1");
        _level2button = new JButton("Level2");
        _level3button = new JButton("Level3");
    }

    private void createListeners()
    {
        _play.addActionListener(new MainMenuActionListener("play", this));
        _editor.addActionListener(new MainMenuActionListener("editor", this));
        _quit.addActionListener(new MainMenuActionListener("quit", this));
        _level1button.addActionListener(new MainMenuActionListener("level1", this));
        _level2button.addActionListener(new MainMenuActionListener("level2", this));
        _level3button.addActionListener(new MainMenuActionListener("level3", this));

        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                _mainMenu.doCommand("quit");
            }
            @Override
            public void windowGainedFocus(WindowEvent e) {
            	readData();
            	super.windowGainedFocus(e);
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
        	this.setVisible(false);
            this.dispose();
            beginSession();
            StateManager.setState(GameState.EDITOR);
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
        else if (command.equals("level1"))
        {
            this.setVisible(false);
            this.dispose();
            beginSession();
            StateManager.setState(GameState.LEVEL1);
            StateManager.run();
            while (!Display.isCloseRequested())
            {

                Clock.update(); //always update the clock before drawing enemies
                StateManager.update();
                if(StateManager.getrun() == false) {
                	readData();
                	this.setVisible(true);
                	break;
                }
                Display.update();
                Display.sync(60);
            }
            Display.destroy();
        }
        else if (command.equals("level2"))
        {
            this.setVisible(false);
            this.dispose();
            beginSession();
            StateManager.setState(GameState.LEVEL2);
            StateManager.run();
            while (!Display.isCloseRequested())
            {

                Clock.update(); //always update the clock before drawing enemies
                StateManager.update();
                if(StateManager.getrun() == false) {
                	readData();
                	this.setVisible(true);
                	break;
                }
                Display.update();
                Display.sync(60);
            }
            Display.destroy();
        }
        else if (command.equals("level3"))
        {
            this.setVisible(false);
            this.dispose();
            beginSession();
            StateManager.setState(GameState.LEVEL3);
            StateManager.run();
            while (!Display.isCloseRequested())
            {

                Clock.update(); //always update the clock before drawing enemies
                StateManager.update();
                if(StateManager.getrun() == false) {
                	readData();
                	this.setVisible(true);
                	break;
                }
                Display.update();
                Display.sync(60);
            }
            Display.destroy();
        }
        else if (command.equals("quit"))
        {
            this.dispose();
            System.exit(0);
        }
    }
    
    private void readData() {
    	ArrayList<String> list = new ArrayList<String>();
    	File file = new File("data");
    	if(!file.exists()) {
        	try {
    			file.createNewFile();
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    	}
		try {
			FileReader fr = new FileReader(file.getAbsolutePath());
			BufferedReader br = new BufferedReader(fr);
			
			String str;
			try {
				
				while ((str=br.readLine())!=null) {
					list.add(str);
				}
				colorPanel(list);
			} catch (IOException e) {
				System.out.println("Err114");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Err113");
			e.printStackTrace();
		}
    }
    
    private void colorPanel(ArrayList<String> list) {
    	for (int i = 0; i < list.size(); i++) {
			if(list.get(i).equals("LEVEL1")) {
				_Level1.setBackground(Color.green);
			}
			if(list.get(i).equals("LEVEL2")) {
				_Level2.setBackground(Color.green);
			}
			if(list.get(i).equals("LEVEL3")) {
				_Level3.setBackground(Color.green);
			}
		}
    }
}
