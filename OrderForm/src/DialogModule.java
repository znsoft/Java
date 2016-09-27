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

    public void actionPerformed(ActionEvent e)
    {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton == parent.orderButton)
        {
            try
            {
                checkForm(parent.modelList.getSelectedItem().toString(), parent.quantity.getText());
            }
            catch (TooManyItemsException message)
            {
                parent.orderInfo.setText("Order information: " + message.getMessage());
                return;
            }
        }

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

    private void checkForm(String model, String quantity) throws TooManyItemsException
    {
        if (model.equals("") || quantity.equals("000.00"))
        {
            throw new TooManyItemsException(model,0);
        }
    }

    public void itemStateChanged(ItemEvent e)
    {
        if (e.getStateChange() == ItemEvent.SELECTED)
        {
            model = parent.modelList.getSelectedItem().toString();
        }
    }

    private class TooManyItemsException extends Exception
    {
        TooManyItemsException(String model, double quantity)
        {
            super("Невозможно выполнить заказ! Модель: "+model+" в количестве: "+quantity);
        }
    }
}
