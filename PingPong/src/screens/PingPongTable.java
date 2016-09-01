package screens;

import engine.PingPongEngine;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Color;

public class PingPongTable extends JPanel implements GameConstants
{
    private JLabel label;
    private int computerRacket_Y = COMPUTER_RACKET_Y_START;
    private int kidRacket_Y = KID_RACKET_Y_START;
    private int ballX;
    private int ballY;
    Dimension preferredSize = new Dimension(TABLE_WIDTH,TABLE_HEIGHT);

    // Этот метод устанавливает размер
    // Вызывается виртуальной Java машиной
    // когда юзаем Frame.pack()
    public Dimension getPreferredSize()
    {
        return preferredSize;
    }

    //Конструктор. Создает обработчик событий мыши.
    PingPongTable()
    {
        PingPongEngine gameEngine =new PingPongEngine(this);
        // Обрабатывает движения мыши для передвижения ракеток
        addMouseMotionListener(gameEngine);
        // Обрабатываем!события клавиатуры
        addKeyListener(gameEngine);
        // Создать экземпляр окна
        JFrame frame = new JFrame("Ping Pong Green Table");
        // Убедиться, что окно может быть закрыто по нажатию на
        //крестик в углу
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(this);
        label = new JLabel("Press N for a new game, S to serve or Q to quit");
        container.add(label);
        frame.pack();
        frame.setBounds(0, 0, TABLE_WIDTH + 15, TABLE_HEIGHT + 50);
        frame.setVisible(true);
    }

    // Перерисовать окно. Этот метод вызывается виртуальной
    // машиной, когда нужно обновить экран или
    // вызывается метод repaint() из PingPointGameEngine
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);  // ЗАЧЕМ?
        g.setColor(Color.GREEN);
        // Нарисовать стол
        g.fillRect(X0, Y0, TABLE_WIDTH, TABLE_HEIGHT);
        g.setColor(Color.YELLOW);
        // Нарисовать правую ракетку
        g.fillRect(KID_RACKET_X, kidRacket_Y, RACKET_WIDTH, RACKET_LENGTH);
        g.setColor(Color.BLUE);
        // Нарисовать левую ракетку
        g.fillRect(COMPUTER_RACKET_X, computerRacket_Y, RACKET_WIDTH, RACKET_LENGTH);
        g.setColor(Color.WHITE);
        g.drawRect(FIELD_X0, FIELD_Y0, FIELD_WIDTH, FIELD_HEIGHT);
        g.drawLine(TABLE_WIDTH/2, FIELD_X0, TABLE_WIDTH/2, FIELD_BOTTOM);
        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE); //Нарисовать мяч
        // Отобразить точку как маленький квадрат 2x2 пикселей
        requestFocus();
    }

    // Установить текущее положение ракетки ребенка
    public void setKidRacket_Y(int yCoordinate)
    {
        this.kidRacket_Y = yCoordinate;
        repaint();
    }

    // Вернуть текущее положение ракетки ребенка
    public int getKidRacket_Y()
    {
        return kidRacket_Y;
    }

    // Установить текущее положение ракетки компьютера!
    public void setComputerRacket_Y(int yCoordinate)
    {
        this.computerRacket_Y = yCoordinate;
        repaint();
    }

    // Установить игровое сообщение
    public void setMessageText(String text)
    {
        label.setText(text);
        repaint();
    }

    // Установить позицию мяча!
    public void setBallPosition(int xPos, int yPos)
    {
        ballX=xPos;
        ballY=yPos;
        repaint();
    }

    public static void main (String[] args)
    {
        PingPongTable table = new PingPongTable();
    }
}
