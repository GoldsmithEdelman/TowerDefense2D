package data.player;

import static helpers.Artist.*;

import java.awt.Color;
import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

public class Menu
{
    Texture _texture;
    int _width = WIDTH;
    int _x, _y;
    int _pointer = 0;
    int _money;
    int _kosten;
    int _thirdkosten;
    TrueTypeFont _font;

    public Menu()
    {
        _texture = quickLoadPngTexture("labyr");
        _x = WIDTH - 64;
        _pointer = 1;
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        _font = new TrueTypeFont(awtFont, false);
        _money = 1000;
        _kosten = 10;
        _thirdkosten= 100;
    }

    public void setPointer(int i)
    {
        _pointer = i;
        switch (i)
        {
        case 1:
            _texture = quickLoadPngTexture("labyr");
            _kosten = 10;
            break;
        case 2:
            _texture = quickLoadPngTexture("Tower2");
            _kosten = 100;
            break;
        case 3:
            _texture = quickLoadPngTexture("Turm3");
            _kosten = _thirdkosten;
            System.out.println(_thirdkosten);
            break;
        case 10:
            _texture = quickLoadPngTexture("Loeschen");
            _kosten = 0;
            break;
        }

    }

    public int getPointer()
    {
        return _pointer;
    }

    public void update()
    {
        draw();
        drawstring(_kosten);
    }

    public void draw()
    {
        drawRectangleTexture(_texture, _x, _y, 64, 64);
    }

    public void test()
    {

    }

    public void drawstring(int kosten)
    {

        String s = "Kostet: " + kosten;
        _font.drawString(1000, 0, s);

    }

    public int getmoney()
    {
        return _money;
    }

    public void addmoney(int money)
    {
        _money = _money + money;
    }

    public void submoney(int money)
    {
        _money = _money - money;
    }
    
    public void updateThirdMoney(int thirdkosten) {
    	_thirdkosten = thirdkosten;
    	_kosten = thirdkosten;
    }
}
