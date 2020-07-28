package data.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuActionListener2 implements ActionListener
{
    private MainMenu _mainMenu;
    private String _command;

    public MainMenuActionListener2(String command, MainMenu menu)
    {
        this._command = command;
        this._mainMenu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        _mainMenu.doCommand(_command);
    }

}
