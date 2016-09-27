import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


class CopyFilesEngine implements ActionListener {

    private CopyFiles parent;

    CopyFilesEngine(CopyFiles parent)
    {
        this.parent = parent;
    }

    private void copy() throws IOException {
        Path path_from = Paths.get(parent.path_from.getText());
        Path path_to = Paths.get(parent.path_to.getText());
        Files.copy(path_from, path_to, StandardCopyOption.REPLACE_EXISTING);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked_button = (JButton) e.getSource();

        if (clicked_button == parent.browse_from) {
            JFileChooser file_open = new JFileChooser();
            int ret = file_open.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                parent.path_from.setText(file_open.getSelectedFile().getAbsolutePath());
            }
        }

        if (clicked_button == parent.browse_to) {
            JFileChooser file_open = new JFileChooser();
            int ret = file_open.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                parent.path_to.setText(file_open.getSelectedFile().getAbsolutePath());
            }
        }

        if (clicked_button == parent.copyButton)
        {
            try
            {
                copy();
                JOptionPane.showMessageDialog(null, "Success!");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
