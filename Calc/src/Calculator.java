import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class Calculator {
    JFormattedTextField displayField = new JFormattedTextField();
    JButton buttonEqual=new JButton("=");
    JButton buttonPlus=new JButton("+");
    JButton buttonMinus=new JButton("-");
    JButton buttonDivide=new JButton("/");
    JButton buttonMultiply=new JButton("*");

    // Конструктор
    Calculator() {
        CalculatorEngine calcEngine = new CalculatorEngine(this);
        // Объявляем и инициализируем компоненты окна
        JButton[] numButtons = new JButton[10];
        JButton buttonPoint = new JButton(".");
        JPanel windowContent = new JPanel();
        // Установить менеджер расположения для панели
        BorderLayout bl = new BorderLayout();
        windowContent.setLayout(bl);
        // Добавляем дисплей в верхней части окна
        windowContent.add("North", displayField);
        displayField.setHorizontalAlignment(JFormattedTextField.RIGHT);
        // Создаем панель с менеджером расположения GridLayout
        // в которой будет 12 кнопок - 10 цифр, и
        // кнопки “точка” и “равно”
        JPanel p1 = new JPanel();
        GridLayout gl = new GridLayout(4, 3);
        p1.setLayout(gl);

        for(int i=0; i<10; i++)
        {
            numButtons[i]=new JButton(Integer.toString(i));
            numButtons[i].addActionListener(calcEngine);
            if (i != 0) {
                p1.add(numButtons[i]);
            }
        }
        p1.add(numButtons[0]);
        p1.add(buttonPoint);
        p1.add(buttonEqual);
        // Добавляем панель p1 в центр окна
        windowContent.add("Center", p1);
        // Создаем панель с менеджером расположения GridLayout
        // на которой будет 4 кнопки -
        // Плюс, Минус, Разделить и Умножить
        JPanel p2 = new JPanel();
        GridLayout gl2 = new GridLayout(4, 1);
        p2.setLayout(gl2);
        p2.add(buttonPlus);
        p2.add(buttonMinus);
        p2.add(buttonMultiply);
        p2.add(buttonDivide);
        // Добавляем панель p2 в правую часть окна
        windowContent.add("East", p2);
        // Создаем frame и добавляем в него содержимое JFrame
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(windowContent);
        // Устанавливаем размер окна, так чтобы уместились
        // все компоненты
        frame.pack();
        // Показываем окно
        frame.setVisible(true);
        // Создаем экземпляр слушателя событий (это кстати перенёс вверх) и
        // регистрируем его в каждой кнопке
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        buttonPoint.addActionListener(calcEngine);
        buttonPlus.addActionListener(calcEngine);
        buttonMinus.addActionListener(calcEngine);
        buttonDivide.addActionListener(calcEngine);
        buttonMultiply.addActionListener(calcEngine);
        buttonEqual.addActionListener(calcEngine);
        frame.addWindowListener(calcEngine);

    }
        public static void main(String[] args) {
            // Создаем экземпляр класса “Калькулятор”
            Calculator calc = new Calculator();
        }
}


