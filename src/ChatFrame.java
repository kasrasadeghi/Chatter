import javax.swing.*;

/**
 * Created by Sarah M on 2/25/2016.
 */
public class ChatFrame extends JFrame{
    private JPanel rootPanel;
    private JTextField addressField;
    private JTextField portField;
    private JButton connectButton;
    private JCheckBox hostingCheckBox;
    private JTextField nameField;

    public ChatFrame() {
        super("Chatter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane(rootPanel);
        connectButton.addActionListener((ae) -> {
            String address = addressField.getText();
            String port = portField.getText();
            String name = nameField.getText();
            if (hostingCheckBox.isSelected())
                Main.setupServer(port, name);
            else Main.setupClient(address, port, name);
        });

        pack();
        setVisible(true);
    }
}
