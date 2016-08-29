package screens;

import engine.PingPongEngine;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Color;

public class PingPongTable extends JPanel implements GameConstants
{
    JLabel label;
    public Point point = new Point(0,0);
    public int ComputerRacket_X = 15;
    private int kidRacket_Y = KID_RACKET_Y_START;
    Dimension preferredSize= new Dimension(TABLE_WIDTH, TABLE_HEIGHT);

    // Этот метод устанавливает размер
    // Вызывается виртуальной Java машиной
    public Dimension getPreferredSize()
    {
        return preferredSize;
    }

    //Конструктор. Создает обработчик событий мыши.
    PingPongTable()
    {
        PingPongEngine gameEngine =new PingPongEngine(this);
        // Обрабатывает клики мыши для отображения ее координат
        addMouseListener(gameEngine);
        // Обрабатывает движения мыши для передвижения ракеток
        addMouseMotionListener(gameEngine);
    }

    // Добавить панель с JLabel в окно
    void addPaneltoFrame(Container container)
    {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(this);
        label = new JLabel("Click to see coordinates");
        container.add(label);
    }

    // Перерисовать окно. Этот метод вызывается виртуальной
    // машиной, когда нужно обновить экран или
    // вызывается метод repaint() из PingPointGameEngine
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        // Нарисовать стол
        g.fillRect(0,0,TABLE_WIDTH,TABLE_HEIGHT);
        g.setColor(Color.yellow);
        // Нарисовать правую ракетку
        g.fillRect(KID_RACKET_X_START, kidRacket_Y,5,30);
        g.setColor(Color.blue);
        // Нарисовать левую ракетку
        g.fillRect(ComputerRacket_X,100,5,30);
        g.setColor(Color.red);
        g.fillOval(25,110,10,10); //Нарисовать мяч
        g.setColor(Color.white);
        g.drawRect(10,10,300,200);
        g.drawLine(160,10,160,210);
        // Отобразить точку как маленький квадрат 2x2 пикселей
        if (point != null) {
            label.setText("Coordinates (x,y): " + point.x +
                    ", " + point.y);
            g.fillOval(point.x, point.y, 2, 2);
        }
    }

    // Установить текущее положение ракетки ребенка
    public void setKidRacket_Y(int xCoordinate)
    {
        this.kidRacket_Y = xCoordinate;
    }

    // Вернуть текущее положение ракетки ребенка
    public int getKidRacket_Y(int xCoordinate)
    {
        return kidRacket_Y;
    }

    public static void main (String[] args)
    {
        // Создать экземпляр окна
        JFrame f = new JFrame("Ping Pong Green Table");
        // Убедиться, что окно может быть закрыто по нажатию на
        //крестик в углу
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PingPongTable table = new PingPongTable();
        table.addPaneltoFrame(f.getContentPane());
        // Установить размер окна и сделать его видимым
        f.pack();
        f.setVisible(true);
    }
}
