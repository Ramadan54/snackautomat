import javax.swing.*;
import java.awt.*;

public class SnackAutomatGUI {
    private JFrame frame;
    private JTextField display;
    private Snack[] snacks;
    private Payment payment;

    public SnackAutomatGUI() {
        snacks = new Snack[]{
                new Snack("Chips", 1.50, 8),
                new Snack("Cola", 2.00, 6),
                new Snack("Snicker", 1.20, 9),
                new Snack("Red Bull", 2.50, 7),
                new Snack("Kaugummi", 1.15, 11),
                new Snack("Oreo", 3.20, 4),
                new Snack("Twix", 1.50, 6),
                new Snack("Capri-Sonne", 1.20, 8),
                new Snack("Fanta", 2.00, 7)
        };
        payment = new Payment();

        frame = new JFrame("Snackautomat");
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 3));

        // Buttons für 0-9
        for (int i = 0; i < 10; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(e -> display.setText(display.getText() + button.getText()));
            panel.add(button);
        }

        // OK-Button für Bestätigung
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(e -> handleInput());
        panel.add(btnOk);

        // Admin-Button für Snack-Auffüllung
        JButton btnAdmin = new JButton("Admin");
        btnAdmin.addActionListener(e -> handleAdminLogin());
        panel.add(btnAdmin);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setVisible(true);
    }

    private void handleInput() {
        try {
            int auswahl = Integer.parseInt(display.getText());
            display.setText("");

            if (auswahl == 0) {
                JOptionPane.showMessageDialog(frame, "Danke für deinen Einkauf!");
                System.exit(0);
            } else if (auswahl >= 1 && auswahl <= snacks.length) {
                handleSnackSelection(auswahl - 1);
            } else {
                JOptionPane.showMessageDialog(frame, "Ungültige Eingabe!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Bitte eine Zahl eingeben!");
        }
    }

    private void handleAdminLogin() {
        String passwort = JOptionPane.showInputDialog(frame, "Passwort eingeben:");
        if ("1234".equals(passwort)) {
            handleRestock();
        } else {
            JOptionPane.showMessageDialog(frame, "Falsches Passwort!");
        }
    }

    private void handleSnackSelection(int index) {
        Snack gewaehlterSnack = snacks[index];

        if (gewaehlterSnack.getMenge() > 0) {
            if (payment.bezahlen(gewaehlterSnack.getPreis())) {
                gewaehlterSnack.Snackkaufen();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Dieses Produkt ist ausverkauft!");
        }
    }

    private void handleRestock() {
        try {
            int snackNummer = Integer.parseInt(JOptionPane.showInputDialog(frame, "Nummer des Snacks zum Auffüllen:"));
            int menge = Integer.parseInt(JOptionPane.showInputDialog(frame, "Menge zum Auffüllen:"));

            if (snackNummer >= 1 && snackNummer <= snacks.length && menge > 0) {
                snacks[snackNummer - 1].auffuellen(menge);
            } else {
                JOptionPane.showMessageDialog(frame, "Ungültige Eingabe!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Bitte eine gültige Zahl eingeben!");
        }
    }
}