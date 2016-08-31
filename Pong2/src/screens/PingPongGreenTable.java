package screens;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Ýòîò êëàññ ðèñóåò çåëåíûé ñòîë äëÿ ïèíã-ïîíãà, øàð, ðàêåòêè, îòîáðàæàåò ñ÷åò
 */

public class PingPongGreenTable extends JPanel implements GameConstants {

    private static final long serialVersionUID = 1L;
    private JLabel label;
    PingPongGameEngine gameEngine;
    private int computerRacket_Y = COMPUTER_RACKET_Y_START;
    private int kidRacket_Y = KID_RACKET_Y_START;
    private int ballX = BALL_START_X;
    private int ballY = BALL_START_Y;

    Dimension preferredSize = new Dimension(TABLE_WIDTH, TABLE_HEIGHT); // Óñòàíàâëèâàåì
    // ðàçìåðû
    // îêíà.Âûçûâàåòñÿ
    // âèðòóàëüíîé
    // ìàøèíîé

    public Dimension getPreferredSize() {
        return preferredSize;
    }

    // Êîíñòðóêòîð. Ñîçäàåò îáðàáîò÷èê ñîáûòèé ìûøè.
    PingPongGreenTable() {
        gameEngine = new PingPongGameEngine(this);
        addMouseMotionListener(gameEngine); // Îáðàáàòûâàåì äâèæåíèÿ ìûøè äëÿ ïåðåäâèæåíèÿ ðàêåòîê
        addKeyListener(gameEngine); // Îáðàáàòûâàåì ñîáûòèÿ êëàâèàòóðû
    }

    // Äîáàâèì â îêíî ïàíåëü ñ JLabel
    void addLabeltoFrame(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(this);
        label = new JLabel("Press N for a new game, S to serve or Q to quit");
        label.setBounds(0, TABLE_HEIGHT, TABLE_WIDTH, 20);
        container.add(label);
    }
    //Ñòðîêà ìåíþ, ïóíêòû ìåíþ
    void addMenuBartoFrame (Container container){

        JMenuBar menu = new JMenuBar();
        container.add(menu);
        menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));

        JMenu menuGame = new JMenu ("Èãðà");
        menuGame.setAlignmentX(LEFT_ALIGNMENT);
        menu.add(menuGame);
        JMenuItem item1 = new JMenuItem("Íîâàÿ èãðà  N");
        item1.addActionListener(gameEngine);
        menuGame.add(item1);
        JMenuItem item2 = new JMenuItem("Ïîäà÷à        S");
        item2.addActionListener(gameEngine);
        menuGame.add(item2);

        JMenu menuLevel = new JMenu ("Ñëîæíîñòü");
        menuGame.add(menuLevel);
        JMenuItem item3 = new JMenuItem("Èçè");
        item3.addActionListener(gameEngine);
        menuLevel.add(item3);
        JMenuItem item4 = new JMenuItem("Íîðì");
        item4.addActionListener(gameEngine);
        menuLevel.add(item4);
        JMenuItem item5 = new JMenuItem("Õàðä");
        item5.addActionListener(gameEngine);
        menuLevel.add(item5);
        menuGame. addSeparator();

        JMenuItem item8 = new JMenuItem("BOTmode");
        item8.addActionListener(gameEngine);
        menuGame.add(item8);

        JMenuItem item7 = new JMenuItem("Âîñïðîèçâåñòè èãðó");
        item7.addActionListener(gameEngine);
        menuGame.add(item7);

        JMenuItem item6 = new JMenuItem("Âûõîä    Q");
        item6.addActionListener(gameEngine);
        menuGame.add(item6);
    }

    // Ïåðåðèñîâàòü îêíî. Ýòîò ìåòîä âûçûâàåòñÿ âèðòóàëüíîé
    // ìàøèíîé, êîãäà íóæíî îáíîâèòü ýêðàí èëè
    // âûçûâàåòñÿ ìåòîä repaint() èç PingPoingGameEngine
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Íàðèñîâàòü çåëåíûé ñòîë
        g.setColor(Color.green);
        g.fillRect(0, 0, TABLE_WIDTH, TABLE_HEIGHT);
        // Íàðèñîâàòü áåëûå ëèíèè

        g.setColor(Color.white);
        g.drawRect(FIELD_LEFT, FIELD_TOP, FIELD_WIGTH, FIELD_HEIGTH);
        g.drawLine(TABLE_WIDTH / 2, FIELD_TOP, TABLE_WIDTH / 2, FIELD_BOTTOM);

        // Íàðèñîâàòü ïðàâóþ ðàêåòêó
        g.setColor(Color.blue);
        g.fillRect(KID_RACKET_X, kidRacket_Y, RACKET_WIDTH, RACKET_LENGTH);

        // Íàðèñîâàòü ëåâóþ ðàêåòêó
        g.setColor(Color.red);
        g.fillRect(COMPUTER_RACKET_X, computerRacket_Y, RACKET_WIDTH, RACKET_LENGTH);

        // Íàðèñîâàòü ìÿ÷
        g.setColor(Color.orange);
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

        // Óñòàíîâèòü ôîêóñ íà ñòîë, ÷òîáû
        // îáðàáîò÷èê êëàâèàòóðû ìîã ïîñûëàòü êîìàíäû ñòîëó
        requestFocus();
    }

    // Óñòàíîâèòü òåêóùåå ïîëîæåíèå ðàêåòêè ðåáåíêà
    public void setKidRacket_Y(int yCoordinate) {
        this.kidRacket_Y = yCoordinate;
        repaint();
    }

    // Óñòàíîâèòü òåêóùåå ïîëîæåíèå ðàêåòêè êîìïüþòåðà
    public void setComputerRacket_Y(int yCoordinate) {
        this.computerRacket_Y = yCoordinate;
        repaint();
    }

    // Óñòàíîâèòü èãðîâîå ñîîáùåíèå
    public void setMessageText(String text) {
        label.setText(text);
        repaint();
    }

    // Óñòàíîâèòü ïîçèöèþ ìÿ÷à
    public void setBallPosition(int xPos, int yPos) {
        ballX = xPos;
        ballY = yPos;
        repaint();
    }

    public static void main(String[] args) {

        // Ñîçäàòü ýêçåìïëÿð îêíà
        JFrame f = new JFrame("Ping Pong Green Table");

        // Óáåäèòüñÿ, ÷òî îêíî ìîæåò áûòü çàêðûòî ïî íàæàòèþ íà
        // êðåñòèê â óãëó

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PingPongGreenTable table = new PingPongGreenTable();
        table.addMenuBartoFrame(f.getContentPane());
        table.addLabeltoFrame(f.getContentPane());
        // Óñòàíîâèòü ðàçìåð îêíà è ñäåëàòü åãî âèäèìûì
        f.setBounds(0, 0, TABLE_WIDTH + 15, TABLE_HEIGHT + 80);
        f.setVisible(true);

    }

}