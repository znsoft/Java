package engine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import screens.*;

public class PingPongEngine implements MouseListener, MouseMotionListener, GameConstants
{
    PingPongTable table;
    public int kidRacket_Y = KID_RACKET_Y_START;
    // Конструктор. Содержит ссылку на объект стола!
    public PingPongEngine(PingPongTable greenTable)
    {
        table = greenTable;
    }

    public void mousePressed(MouseEvent e) {
        // Взять X и Y координаты указателя мыши
        // и установить их "белой точке" на столе
        table.point.x = e.getX();
        table.point.y = e.getY();
        // Внутри вызывает метод paintComponent()и обновляет окно
        table.repaint();
    }

    public void mouseMoved(MouseEvent e) {
        int mouse_Y = e.getY();
        boolean flag = true;
        // Если мышь находится выше ракетки ребенка
        // и не выходит за пределы стола –
        // передвинуть ее вверх, в противном случае – опустить вниз
        if (mouse_Y < kidRacket_Y && kidRacket_Y > TABLE_TOP)
        {
            do
            {
                kidRacket_Y -= RACKET_INCREMENT;
                table.setKidRacket_Y(kidRacket_Y);
                table.repaint();
                if (mouse_Y == kidRacket_Y || kidRacket_Y == TABLE_TOP)
                {
                    flag = false;
                }
            } while (flag == true);
        }
        else if (mouse_Y > kidRacket_Y && kidRacket_Y < TABLE_BOTTOM)
        {
            do {
                kidRacket_Y += RACKET_INCREMENT;
                table.setKidRacket_Y(kidRacket_Y);
                table.repaint();
                if (mouse_Y == kidRacket_Y || kidRacket_Y == TABLE_BOTTOM)
                {
                    flag = false;
                }
            } while (flag == true);
        }
    }

    public void mouseReleased(MouseEvent e) {};
    public void mouseEntered(MouseEvent e) {};
    public void mouseExited(MouseEvent e) {};
    public void mouseClicked(MouseEvent e) {};
    public void mouseDragged(MouseEvent e) {}
}
