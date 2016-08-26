import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;


public class Dialog {
    JButton orderButton = new JButton("Create order");
    JLabel orderInfo = new JLabel("Order information:");

    private String[] models = {"",
                       "Tesla",
                       "Bolt",
                       "Lightning"};

    JComboBox modelList = new JComboBox(models);

    JFormattedTextField quantity;
    private Dialog()
    {

        quantity = new JFormattedTextField();
        DialogModule Engine = new DialogModule(this);

        quantity.setDocument(new PlainDocument()
        {
            String chars = "0123456789.,";
            @Override
            public  void insertString(int offs, String str, AttributeSet a) throws BadLocationException
            {
                if (chars.indexOf(str) != -1)
                {
                    if (offs < 10 )
                    {
                        if ((str.equals(".") || str.equals(",")) && (quantity.getText().indexOf('.') != -1 || quantity.getText().indexOf(',') != -1))
                        {
                        }
                    else
                        {
                            super.insertString(offs, str, a);
                        }
                    }
                }
            }
        });

        JPanel p0 = new JPanel();
        p0.setLayout(new BorderLayout());
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(3,2));

        JLabel modelName = new JLabel("Model:");
        p1.add(modelName);
        p1.add(modelList);
        JLabel quantityLabel = new JLabel("Quantity:");
        p1.add(quantityLabel);
        p1.add(quantity);
        p1.add(orderButton);

        p0.add("North",p1);
        p0.add("South", orderInfo);

        JFrame frame = new JFrame("Order:");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(p0);
        frame.setPreferredSize(new Dimension(500,150));
        frame.setResizable(false);
        modelList.addItemListener(Engine);
        quantity.addActionListener(Engine);
        orderButton.addActionListener(Engine);


        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        new Dialog();
    }
}
