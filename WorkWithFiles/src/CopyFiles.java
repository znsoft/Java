import javax.swing.*;
import java.awt.*;


public class CopyFiles {

    JButton copyButton = new JButton("Copy");
    JFormattedTextField path_from = new JFormattedTextField();
    JFormattedTextField path_to = new JFormattedTextField();
    JButton browse_from = new JButton("...");
    JButton browse_to = new JButton("...");

    private CopyFiles()
    {
        CopyFilesEngine Engine = new CopyFilesEngine(this);

        JPanel p0 = new JPanel();
        p0.setLayout(new BorderLayout());
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2,3));

        JLabel label1 = new JLabel("Copy from: ");
        p1.add(label1);
        p1.add(path_from);
        p1.add(browse_from);
        JLabel label2 = new JLabel("Copy to: ");
        p1.add(label2);
        p1.add(path_to);
        p1.add(browse_to);

        p0.add("North",p1);
        p0.add("South",copyButton);

        browse_from.addActionListener(Engine);
        browse_to.addActionListener(Engine);
        copyButton.addActionListener(Engine);

        JFrame frame = new JFrame("Copying files");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(p0);
        frame.setPreferredSize(new Dimension(500,120));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main (String[] args)
    {
        new CopyFiles();
    }
}
