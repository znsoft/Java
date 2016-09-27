import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by va.tkalin on 31.08.2016.
 */
public class TwoThreadsOneClass extends JFrame implements ActionListener, Runnable {
    public JLabel label1 = new JLabel();
    public JLabel label2 = new JLabel();
    public JButton myButton1 = new JButton("1");
    public JButton myButton2 = new JButton("2");

    // Конструктор!
    TwoThreadsOneClass()
    {
        // Создать окно с кнопкой и текстовым полем!
        GridLayout gl = new GridLayout(5, 1);
        this.getContentPane().setLayout(gl);
        myButton1.addActionListener(this);
        myButton2.addActionListener(this);
        this.getContentPane().add(myButton1);
        this.getContentPane().add(myButton2);
        this.getContentPane().add(label1);
        this.getContentPane().add(label2);

    }

    public static void main(String[] args)
    {
        TwoThreadsOneClass myWindow = new TwoThreadsOneClass();
        //Убедись,что окно закрывается по нажатию на крестик в углу!
        myWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Установи размеры окна и сделай его видимым!
        myWindow.setBounds(0, 0, 150, 100);
        myWindow.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
       // первый поток из класса
        if (e.getSource() == myButton1) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 3000000; i++)
                    {
                        TwoThreadsOneClass.this.label1.setText("123 " + i);
                    }
                }
            });
            t.start();
        }

        // второй поток из класса
        else if (e.getSource() == myButton2) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    for (int i = 3000000; i > 0; i--)
                    {
                        TwoThreadsOneClass.this.label2.setText("321 " + i);
                    }
                }
            });
            t.start();
        }
    }

        public void run()
        {
            // Заморозить на некоторое время, чтобы показать, что!
            // элементы окна НЕ блокируются!
            for (int i = 0; i < 300000; i++)
            {
                this.setTitle("i=" + i);
            }
        }
    }
