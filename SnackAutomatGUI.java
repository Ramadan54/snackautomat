import javax.swing.*;
import java.awt.*;

public class SnackAutomatGUI {
    private JFrame frame;
    private JPanel panel;
    private JTextField display;
    private JButton[] numberButtons;

    public SnackAutomatGUI() {
        // Hauptfenster
        frame = new JFrame("Snackautomat");
        frame.setLayout(new BorderLayout());

        // Display für Benutzereingaben
        display = new JTextField();
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        // Panel für die Buttons
        panel = new JPanel(new GridLayout(4, 3));

        // Buttons erstellen
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(e -> {
                display.setText(display.getText() + ((JButton) e.getSource()).getText());
            });
            panel.add(numberButtons[i]);
        }

        // OK-Button zum Bestätigen
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(e -> handleInput());
        panel.add(btnOk);

        // Panel zum Hauptfenster hinzufügen
        frame.add(panel, BorderLayout.CENTER);

        // Fenster-Einstellungen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setVisible(true);
    }

    //Verarbeitung der Eingabe
    private void handleInput() {
        String eingabe = display.getText();
        JOptionPane.showMessageDialog(frame, "Du hast gewählt: " + eingabe);
        display.setText("");
    }

    // Um GUI zu starten im Main
    public static void main(String[] args) {
        new SnackAutomatGUI();
    }
}