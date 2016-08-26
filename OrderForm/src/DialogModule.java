import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DialogModule implements ActionListener, ItemListener {
    private Dialog parent;
    private String model;

    DialogModule(Dialog parent)
    {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        double quantity = Double.parseDouble(parent.quantity.getText());
        if (clickedButton == parent.orderButton)
        {
            try
            {
                checkOrder(model, quantity);
                parent.orderInfo.setText("Order information: " + "Заказ успешно зарегистрирован");
            }
            catch (TooManyItemsException message)
            {
                parent.orderInfo.setText("Order information: " + message.getMessage());
            }
        }
    }

    private void checkOrder(String model, double quantity) throws TooManyItemsException
    {
        if (Objects.equals(model, "Bolt") && quantity > 3)
        {
            throw new TooManyItemsException(model,quantity);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED)
        {
            model = parent.modelList.getSelectedItem().toString();
        }
    }



    private class TooManyItemsException extends Exception
    {
        TooManyItemsException(String model, double quantity)
        {
            super("Невозможно выполнить заказ: "+model+" в количестве: "+quantity);
        }
    }

}
