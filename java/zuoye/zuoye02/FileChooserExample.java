import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileChooserExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("File Chooser Example");

        JButton button = new JButton("Choose File");
        button.setBounds(100, 100, 200, 30);

        JDialog dialog = new JDialog();
        dialog.setModal(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(dialog);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    System.out.println("Selected File: " + filePath);
                }

                dialog.dispose(); // Close the JDialog
            }
        });

        dialog.add(button);
        dialog.setSize(400, 200);
        dialog.setLayout(null);
        dialog.setVisible(true);

        frame.setSize(400, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}