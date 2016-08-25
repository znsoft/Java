/*
 * Игра крестики-нолики на доске 3x3
 */
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Objects;

public class TicTacToe extends Applet implements ActionListener{

    private Button squares[][];
    private Button newGameButton;
    private Label score;
    private Label won;
    private Label lost;
    private int emptySquaresLeft=9;
    private int won_n = 0;
    private int lost_n = 0;

    /**
     * Метод init – это конструктор апплета
     */
    public void init()
    {
        //Устанавливаем менеджер расположения апплета, шрифт и цвет
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        // Изменяем шрифт апплета так, чтобы он был жирным
        // и имел размер 20
        Font appletFont=new Font("Verdana",Font.BOLD, 20);
        this.setFont(appletFont);
        // Создаем кнопку “New Game” и регистрируем в ней
        // слушатель действия
        newGameButton=new Button("New Game");
        newGameButton.addActionListener(this);
        Panel topPanel=new Panel();
        topPanel.setLayout(new GridLayout());
        won = new Label("Won: 0");
        lost = new Label("Lost: 0");
        topPanel.add(won);
        topPanel.add(newGameButton);
        topPanel.add(lost);
        this.add(topPanel,"North");
        Panel centerPanel=new Panel();
        centerPanel.setLayout(new GridLayout(3,3));
        this.add(centerPanel,"Center");
        score=new Label("Your turn!");
        this.add(score,"South");
        // создаем массив, чтобы хранить ссылки на 9 кнопок
        squares=new Button[3][3];
        // Создаем кнопки, сохраняем ссылки на них в массиве
        // регистрируем в них слушатель, красим их
        // в оранжевый цвет и добавляем на панель
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                squares[i][j] = new Button();
                squares[i][j].addActionListener(this);
                squares[i][j].setBackground(Color.ORANGE);
                centerPanel.add(squares[i][j]);
            }
        }
    }

    /*
     * Этот метод будет обрабатывать все события
     * @param ActionEvent объект
     */
    public void actionPerformed(ActionEvent e) {
        Button theButton = (Button) e.getSource();
        // Это кнопка New Game ?
        if (theButton == newGameButton){
            for(int i=0;i<3;i++)
            {
                for (int j=0;j<3;j++)
                {
                    squares[i][j].setEnabled(true);
                    squares[i][j].setLabel("");
                    squares[i][j].setBackground(Color.ORANGE);
                }
            }
            emptySquaresLeft=9;
            score.setText("Your turn!");
            newGameButton.setEnabled(false);
            return; // выходим из метода
        }
        String winner = "";
        // Это одна из клеток?
        for ( int i=0; i<3; i++ )
        {
            for (int j=0;j<3;j++)
            {
                if (theButton == squares[i][j]) {
                    // Проверка на то, что уже кнопка нажата
                    if (!squares[i][j].getLabel().equals("")) {
                        return;
                    }
                    squares[i][j].setLabel("X");
                    winner = lookForWinner();
                    if (!"".equals(winner)) {
                        endTheGame();
                    } else {
                        computerMove();
                        winner = lookForWinner();
                        if (!"".equals(winner)) {
                            endTheGame();
                        }
                    }
                    break;
                }
            }
        } // конец цикла for
        switch (winner)
        {
            case "X":
                score.setText("You won!");
                won_n ++;
                won.setText("Won: "+Integer.toString(won_n));
                break;
            case "O":
                score.setText("You lost!");
                lost_n ++;
                lost.setText("Lost: "+Integer.toString(lost_n));
                break;
            case "T":
                score.setText("It's a tie!");
                break;
        }
    } // конец метода actionPerformed

    /**
     * Этот метод вызывается после каждого хода, чтобы узнать,
     * есть ли победитель. Он проверяет каждый ряд, колонку
     * и диагональ, чтобы найти три клетки с одинаковыми надписями
     * (не пустыми)
     * @return "X", "O", "T" – ничья, "" - еще нет победителя
     */
    private String lookForWinner()
    {
        String theWinner = "";
        emptySquaresLeft--;
        if (emptySquaresLeft==0)
        {
            return "T"; // это ничья. T от английского слова tie
        }

        for (int i=0;i<3;i++)
        {
            if (!Objects.equals(squares[i][0].getLabel(), "") && Objects.equals(squares[i][0].getLabel(), squares[i][1].getLabel()) && Objects.equals(squares[i][0].getLabel(), squares[i][2].getLabel()))
            {
                theWinner = squares[i][0].getLabel();
                highlightWinner(squares[i][0],squares[i][1],squares[i][2]);
            }
            else if (!Objects.equals(squares[0][i].getLabel(), "") && Objects.equals(squares[i][0].getLabel(), squares[1][i].getLabel()) && Objects.equals(squares[0][i].getLabel(), squares[2][i].getLabel()))
            {
                theWinner = squares[0][i].getLabel();
                highlightWinner(squares[0][i],squares[1][i],squares[2][i]);
            }
            else if (!Objects.equals(squares[0][0].getLabel(), "") && Objects.equals(squares[0][0].getLabel(), squares[1][1].getLabel()) && Objects.equals(squares[0][0].getLabel(), squares[2][2].getLabel()))
            {
                theWinner = squares[0][0].getLabel();
                highlightWinner(squares[0][0],squares[1][1],squares[2][2]);
            }
            else if (!Objects.equals(squares[2][0].getLabel(), "") && Objects.equals(squares[2][0].getLabel(), squares[1][1].getLabel()) && Objects.equals(squares[2][0].getLabel(), squares[0][2].getLabel()))
            {
                theWinner = squares[0][0].getLabel();
                highlightWinner(squares[2][0],squares[1][1],squares[0][2]);
            }
        }
        return theWinner;
    }

    /**
     * Этот метод применяет набор правил, чтобы найти
     * лучший компьютерный ход. Если хороший ход
     * не найден, выбирается случайная клетка.
     */
    private void computerMove()
    {
        int selectedSquare[];
        // Сначала компьютер пытается найти пустую клетку
        // рядом с двумя клетками с ноликами, чтобы выиграть
        selectedSquare = findEmptySquare("O");
        // Если он не может найти два нолика, то хотя бы
        // попытается не дать оппоненту сделать ряд из 3-х
        // крестиков,поместив нолик рядом с двумя крестиками
        if ( selectedSquare[0] == -1 && selectedSquare[1] == -1)
        {
            selectedSquare = findEmptySquare("X");
        }
        // если selectedSquare все еще равен -1, то
        // попытается занять центральную клетку
        if ( (selectedSquare[0] == -1) && (selectedSquare[1]== -1) && (squares[1][1].getLabel().equals("")) )
        {
            selectedSquare[0]=1;
            selectedSquare[1]=1;
        }
        // не повезло с центральной клеткой...
        // просто занимаем случайную клетку
        if ( selectedSquare[0] == -1 && selectedSquare[1] == -1)
        {
            selectedSquare = getRandomSquare();
        }
        squares[selectedSquare[0]][selectedSquare[1]].setLabel("O");
    }

    /*
     * Этот метод проверяет каждый ряд, колонку и диагональ
     * чтобы узнать, есть ли в ней две клетки
     * с одинаковыми надписями и пустой клеткой.
     * @param передается X – для пользователя и O – для компа
     * @return количество свободных клеток,
     * или -1, если не найдено две клетки
     * с одинаковыми надписями
     */
    private int[] findEmptySquare(String player)
    {
        int weight[][] = new int[3][3];

        int XY[] = new int[2];
        XY[0] = -1;
        XY[1] = -1;
        for ( int i = 0; i < 3; i++ )
        {
            for ( int j = 0; j < 3; j++ )
            {
                if (squares[i][j].getLabel().equals("O"))
                    weight[i][j] = -1;
                else if (squares[i][j].getLabel().equals("X"))
                    weight[i][j] = 1;
                else
                    weight[i][j] = 0;
            }
        }

        int twoWeights = player.equals("O") ? -2 : 2;

        for ( int i = 0; i < 3; i++ )
        {
            if (weight[0][i] + weight[1][i] + weight[2][i] == twoWeights)
            {
               if (weight[0][i] == 0)
               {
                   XY[0] = 0;
                   XY[1] = i;
               }
                else if (weight[1][i] == 0)
                {
                    XY[0] = 1;
                    XY[1] = i;
                }
               else if (weight[2][i] == 0)
               {
                   XY[0] = 2;
                   XY[1] = i;
               }
            }
            if (weight[i][0] + weight[i][1] + weight[i][2] == twoWeights)
            {
                if (weight[i][0] == 0)
                {
                    XY[0] = i;
                    XY[1] = 0;
                }
                else if (weight[i][1] == 0)
                {
                    XY[0] = i;
                    XY[1] = 1;
                }
                else if (weight[i][2] == 0)
                {
                    XY[0] = i;
                    XY[1] = 2;
                }
            }
            if (weight[0][0] + weight[1][1] + weight[2][2] == twoWeights)
            {
                if (weight[0][0] == 0)
                {
                    XY[0] = 0;
                    XY[1] = 0;
                }
                else if (weight[1][1] == 0)
                {
                    XY[0] = 1;
                    XY[1] = 1;
                }
                else if (weight[2][2] == 0)
                {
                    XY[0] = 2;
                    XY[1] = 2;
                }
            }
            if (weight[2][0] + weight[1][1] + weight[0][2] == twoWeights)
            {
                if (weight[2][0] == 0)
                {
                    XY[0] = 2;
                    XY[1] = 0;
                }
                else if (weight[1][1] == 0)
                {
                    XY[0] = 1;
                    XY[1] = 1;
                }
                else if (weight[0][2] == 0)
                {
                    XY[0] = 0;
                    XY[1] = 2;
                }
            }
        }
        return XY;
    } // конец метода findEmptySquare()


    /**
     * Этот метод выбирает любую пустую клетку
     * @return случайно выбранный номер клетки
     */
    private int[] getRandomSquare()
    {
        boolean gotEmptySquare = false;
        int[] selectedSquare = new int[2];
        selectedSquare[0] = -1;
        selectedSquare[1] = -1;
        do
        {
            selectedSquare[0] = (int) (Math.random() * 3 );
            selectedSquare[1] = (int) (Math.random() * 3 );
            if (squares[selectedSquare[0]][selectedSquare[1]].getLabel().equals(""))
            {
                gotEmptySquare = true; // чтобы закончить цикл
            }
        } while (!gotEmptySquare );
        return selectedSquare;
    } // конец метода getRandomSquare()


    /*
     * Этот метод выделяет выигравшую линию.
     * @param первая, вторая и третья клетки для выделения
     */
    private void highlightWinner(Button button1,Button button2,Button button3)
    {
        button1.setBackground(Color.CYAN);
        button2.setBackground(Color.CYAN);
        button3.setBackground(Color.CYAN);
    }
    // Делаем недоступными клетки и доступной кнопку ”New Game”
    private void endTheGame()
    {
        newGameButton.setEnabled(true);
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                squares[i][j].setEnabled(false);
            }
        }

    }
} // конец класса