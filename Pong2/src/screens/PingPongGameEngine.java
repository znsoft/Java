package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PingPongGameEngine implements Runnable, MouseMotionListener,
        ActionListener, KeyListener, GameConstants {

    private PingPongGreenTable table; // ññûëêà íà ñòîë
    Thread worker;
    FileIO file;
    BufferedReader br;
    Object monitor;
    int kidRacket_Y = KID_RACKET_Y_START;
    int computerRacket_Y = COMPUTER_RACKET_Y_START;
    int kidScore;
    int computerScore;
    int slp = SLEEP_TIME;
    int ballX; // êîîðäèíàòà X ìÿ÷à
    int ballY; // êîîðäèíàòà Y ìÿ÷à
    private int level = 1; // ôëàã ñëîæíîñòè(èçíà÷àëüíî - èçè)
    private boolean movingLeft = true;
    private boolean ballServed = false;
    public boolean recMode = false;
    public boolean REC = false;
    private int verticalSlide; // Çíà÷åíèå âåðòèêàëüíîãî ïåðåäâèæåíèÿ ìÿ÷à â
    // ïèêñåëÿõ

    // Êîíñòðóêòîð. Ñîäåðæèò ññûëêó íà îáúåêò ñòîëà
    public PingPongGameEngine(PingPongGreenTable greenTable) {

        table = greenTable;
        monitor = new Object();
        worker = new Thread(this);
        worker.start();
        file = new FileIO(this);
        startNewGame();
    }

    // Îáÿçàòåëüíûå ìåòîäû èç èíòåðôåéñà MouseMotionListener
    // (íåêîòîðûå èç íèõ ïóñòûå,íî äîëæíû áûòü âêëþ÷åíû âñå ðàâíî)
    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        if (!recMode && !(level==4)){
            int mouse_Y = e.getY();
            if (mouse_Y > FIELD_TOP && (mouse_Y + RACKET_LENGTH) < FIELD_BOTTOM) {
                kidRacket_Y = mouse_Y;
            } else
                return;
            table.setKidRacket_Y(kidRacket_Y);
        }
    }

    // Îáÿçàòåëüíûå ìåòîäû èç èíòåðôåéñà KeyListener
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if ('n' == key || 'N' == key || 'ò' == key || 'Ò' == key) {
            startNewGame();
        } else if ('q' == key || 'Q' == key || 'é' == key || 'É' == key) {
            endGame();
        } else if ('s' == key || 'S' == key || 'û' == key || 'Û' == key) {
            kidServe();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    // Îáðàáîò÷èê ñîáûòèé âûáîðà ìåíþ ActionListener
    public void actionPerformed(ActionEvent e) {
        String Comstr = e.getActionCommand();
        if (Comstr.equals("Íîâàÿ èãðà  N"))
            startNewGame();
        if (Comstr.equals("Ïîäà÷à        S"))
            kidServe();
        if (Comstr.equals("Èçè"))
            level = 1;
        if (Comstr.equals("Íîðì"))
            level = 2;
        if (Comstr.equals("Õàðä"))
            level = 3;
        if (Comstr.equals("Âûõîä    Q"))
            endGame();
        if (Comstr.equals("Âîñïðîèçâåñòè èãðó")) {
            REC = false;
            recMode = true;
            computerScore = 0;
            kidScore = 0;
            try {
                br = new BufferedReader(new FileReader("D:\\write\\notes.txt"));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        if (Comstr.equals("BOTmode"))
            if (!(level == 4))
                level = 4;
            else level =1;
    }

    // Íà÷àòü íîâóþ èãðó
    public void startNewGame() {
        recMode = false;
        ballServed = false;
        computerScore = 0;
        kidScore = 0;
        ballX = BALL_START_X;
        ballY = BALL_START_Y;
        table.setBallPosition(ballX, ballY);

        if (level == 4){
            table.setMessageText("Computer: " + computerScore + " Kid: " + kidScore);
//			kidServe();
        }
        REC = true;
    }

    // Çàâåðøèòü èãðó
    public void endGame() {
        System.exit(0);
    }

    // Îáÿçàòåëüíûé ìåòîä run() èç èíòåðôåéñà Runnable
    @Override
    public void run() {

        boolean canBounce = false;
        while (true) {
            synchronized (monitor) {

                if (recMode) {
                    table.setBallPosition(ballX, ballY);
                    table.setKidRacket_Y(kidRacket_Y);
                    table.setComputerRacket_Y(computerRacket_Y);
                    table.setMessageText("Computer: " + computerScore
                            + " Kid: " + kidScore + " recMode - ON");
                } else {

                    if (level == 4) {
                        if (ballY + BALL_SIZE <= (kidRacket_Y + RACKET_LENGTH / 7)
//						if (ballY + BALL_SIZE <= (kidRacket_Y + RACKET_LENGTH)
                                && kidRacket_Y > FIELD_TOP) {
                            kidRacket_Y -= RACKET_INCREMENT;
                        } else if ((kidRacket_Y + RACKET_LENGTH) < FIELD_BOTTOM) {
                            kidRacket_Y += RACKET_INCREMENT;
                        }
                    }

                    table.setKidRacket_Y(kidRacket_Y);
                    if (ballServed) { // åñëè ìÿ÷ äâèæåòñÿ

                        // Øàã 1. Ìÿ÷ äâèæåòñÿ âëåâî?
                        if (movingLeft && ballX > BALL_MIN_X) {

                            canBounce = (ballY + BALL_SIZE >= computerRacket_Y
                                    && ballY < (computerRacket_Y + RACKET_LENGTH) ? true
                                    : false);
                            ballX -= BALL_INCREMENT;
                            ballY += verticalSlide;

                            // Äîáàâèòü ñìåùåíèå ââåðõ èëè âíèç ê ëþáûì
                            // äâèæåíèÿì ìÿ÷à âëåâî èëè âïðàâî

                            table.setBallPosition(ballX, ballY);
                            // Ìîæåò îòñêî÷èòü?
                            if (ballX <= COMPUTER_RACKET_X + BALL_SIZE / 2
                                    && canBounce) {
                                movingLeft = false;
                                if (ballY + BALL_SIZE >= computerRacket_Y
                                        && ballY + BALL_SIZE / 2 < (computerRacket_Y + RACKET_LENGTH / 7))
                                    verticalSlide = -6;
                                if (ballY + BALL_SIZE / 2 >= (computerRacket_Y + RACKET_LENGTH / 7)
                                        && ballY + BALL_SIZE / 2 < (computerRacket_Y + 2 * (RACKET_LENGTH / 7)))
                                    verticalSlide = -4;
                                if (ballY + BALL_SIZE / 2 >= (computerRacket_Y + 2 * (RACKET_LENGTH / 7))
                                        && ballY + BALL_SIZE / 2 <= (computerRacket_Y + 3 * (RACKET_LENGTH / 7)))
                                    verticalSlide = -2;
                                if (ballY + BALL_SIZE / 2 >= (computerRacket_Y + 4 * (RACKET_LENGTH / 7))
                                        && ballY + BALL_SIZE / 2 < (computerRacket_Y + 5 * (RACKET_LENGTH / 7)))
                                    verticalSlide = 2;
                                if (ballY + BALL_SIZE / 2 >= (computerRacket_Y + 5 * (RACKET_LENGTH / 7))
                                        && ballY + BALL_SIZE / 2 < (computerRacket_Y + 6 * (RACKET_LENGTH / 7)))
                                    verticalSlide = 4;
                                if (ballY + BALL_SIZE / 2 >= (computerRacket_Y + 6 * (RACKET_LENGTH / 7))
                                        && ballY <= (computerRacket_Y + 7 * (RACKET_LENGTH / 7)))
                                    verticalSlide = 6;
                            }
                        }

                        // Øàã 2. Ìÿ÷ äâèæåòñÿ âïðàâî?
                        if (!movingLeft && ballX < BALL_MAX_X) {
                            canBounce = (ballY + BALL_SIZE >= kidRacket_Y
                                    && ballY <= (kidRacket_Y + RACKET_LENGTH) ? true
                                    : false);

                            ballX += BALL_INCREMENT;
                            ballY += verticalSlide;
                            table.setBallPosition(ballX, ballY);

                            // Ìîæåò îòñêî÷èòü? çíà÷åíèå îòñêîêà è ñêîðîñòè â
                            // çàâèñèìîñòè îò óðîâíÿ ñëîæíîñòè
                            if (ballX > KID_RACKET_X - BALL_SIZE && canBounce) {
                                movingLeft = true;
                                if (level == 1 || level == 4) {
                                    if (ballY + BALL_SIZE >= kidRacket_Y
                                            && ballY + BALL_SIZE / 2 < (kidRacket_Y + RACKET_LENGTH / 7))
                                        verticalSlide = -6;
                                    if (ballY + BALL_SIZE / 2 >= (kidRacket_Y + RACKET_LENGTH / 7)
                                            && ballY + BALL_SIZE / 2 < (kidRacket_Y + 2 * (RACKET_LENGTH / 7)))
                                        verticalSlide = -4;
                                    if (ballY + BALL_SIZE / 2 >= (kidRacket_Y + 2 * (RACKET_LENGTH / 7))
                                            && ballY + BALL_SIZE / 2 <= (kidRacket_Y + 3 * (RACKET_LENGTH / 7)))
                                        verticalSlide = -2;
                                    if (ballY + BALL_SIZE / 2 >= (kidRacket_Y + 4 * (RACKET_LENGTH / 7))
                                            && ballY + BALL_SIZE / 2 < (kidRacket_Y + 5 * (RACKET_LENGTH / 7)))
                                        verticalSlide = 2;
                                    if (ballY + BALL_SIZE / 2 >= (kidRacket_Y + 5 * (RACKET_LENGTH / 7))
                                            && ballY + BALL_SIZE / 2 < (kidRacket_Y + 6 * (RACKET_LENGTH / 7)))
                                        verticalSlide = 4;
                                    if (ballY + BALL_SIZE / 2 >= (kidRacket_Y + 6 * (RACKET_LENGTH / 7))
                                            && ballY <= (kidRacket_Y + 7 * (RACKET_LENGTH / 7)))
                                        verticalSlide = 6;
                                } else {
//									if (ballY + BALL_SIZE / 2 <= kidRacket_Y
//											+ RACKET_LENGTH / 2)
//										verticalSlide--;
//									else
//										verticalSlide++;

                                }
                            }
                        }

                        // Øàã 3. Ïåðåìåùàòü ðàêåòêó êîìïüþòåðà ââåðõ èëè âíèç,
                        // ÷òîáû áëîêèðîâàòü ìÿ÷

                        if (ballY + BALL_SIZE <= (computerRacket_Y + RACKET_LENGTH / 7)
                                && computerRacket_Y > FIELD_TOP) {
                            computerRacket_Y -= RACKET_INCREMENT;
                        } else if ((computerRacket_Y + RACKET_LENGTH) < FIELD_BOTTOM) {
                            computerRacket_Y += RACKET_INCREMENT;
                        }
                        table.setComputerRacket_Y(computerRacket_Y);
                        // îòñêîê îò ñòåí
                        if (!isBallOnTheTable()) {
                            verticalSlide = verticalSlide * -1;
                        }
                    } // êîíåö if ball served
                } // êîíåö else

                monitor.notify();
            } // êîíåö synchronized
            // Øàã 4. Ïðèòîðìîçèòü
            try {
                if (level == 2)
                    if (slp > 6)
                        slp = slp - 1;
                if (level == 3)
                    if (slp > 4)
                        slp = slp - 1;
                if (level == 4)
                    slp = 5;
                if (recMode == true) slp = 5;
                Thread.sleep(slp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Øàã 5. Îáíîâèòü ñ÷åò, åñëè ìÿ÷ â çåëåíîé îáëàñòè, íî íå
            // äâèæåòñÿ
            if (isBallOnTheTable() && ballServed) {

                if (ballX >= BALL_MAX_X) {
                    computerScore++;
                    displayScore();
                } else if (ballX <= BALL_MIN_X) {
                    kidScore++;
                    displayScore();
                }

            }

        } // Êîíåö while

    }// Êîíåö run()

    // Ïîäàòü ñ òåêóùåé ïîçèöèè ðàêåòêè ðåáåíêà

    private void kidServe() {

        slp = SLEEP_TIME;
        ballX = KID_RACKET_X - BALL_SIZE;
        ballY = kidRacket_Y + RACKET_LENGTH / 2;
        if (ballY > TABLE_HEIGHT / 2) {
            verticalSlide = -1;
        } else {
            verticalSlide = 1;
        }
        table.setMessageText("Computer: " + computerScore + " Kid: " + kidScore);
        table.setBallPosition(ballX, ballY);
        table.setKidRacket_Y(kidRacket_Y);
        ballServed = true;
    }

    private void displayScore() {

        if (computerScore == WINNING_SCORE) {

            table.setMessageText("Computer won! " + computerScore + ":"
                    + kidScore);
            ballServed = false;
            startNewGame();

        } else if (kidScore == WINNING_SCORE) {
            table.setMessageText("You won! " + kidScore + ":" + computerScore);
            ballServed = false;
            startNewGame();
        } else {

            table.setMessageText("Computer: " + computerScore + " Kid: "
                    + kidScore);
            ballServed = false;
        }
        if (level == 4) kidServe();

    }

    // Ïðîâåðèòü, íå ïåðåñåê ëè ìÿ÷ âåðõíþþ èëè íèæíþþ ãðàíèöó ñòîëà
    private boolean isBallOnTheTable() {

        if (ballY >= BALL_MIN_Y && ballY <= BALL_MAX_Y) {
            return true;
        } else {
            return false;
        }
    }
}