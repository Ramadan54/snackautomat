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

        //Vending Machine Bild skalieren**
        JPanel vendingPanel = new JPanel();
        vendingPanel.setLayout(new BoxLayout(vendingPanel, BoxLayout.Y_AXIS));

        ImageIcon vendingIcon = new ImageIcon("Vending Machine.png");
        Image scaledVending = vendingIcon.getImage().getScaledInstance(-1, 400, Image.SCALE_SMOOTH);
        JLabel vendingLabel = new JLabel(new ImageIcon(scaledVending));
        vendingPanel.add(vendingLabel);

        //Panel für den Ziffernblock
        JPanel keypadPanel = new JPanel(new BorderLayout());

        //Display über die Tasten setzen
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.CENTER);
        display.setPreferredSize(new Dimension(180, 50));
        JPanel displayWrapper = new JPanel();
        displayWrapper.add(display);

        //Ziffernblock enger setzen
        JPanel panel = new JPanel(new GridLayout(4, 3, 0, 0));

        for (int i = 0; i < 10; i++) {
            final int number = i;
            ImageIcon icon = new ImageIcon(number + ".png");
            Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JButton button = new JButton(scaledIcon);
            button.setPreferredSize(new Dimension(50, 50));

            //Unsichtbare Hitboxen entfernen
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setBorder(BorderFactory.createEmptyBorder());

            button.setFocusPainted(false);
            button.setContentAreaFilled(false);

            button.addActionListener(e -> display.setText(display.getText() + number));
            panel.add(button);
        }

        //OK-Button
        ImageIcon okIcon = new ImageIcon("Häckchen.png");
        Image scaledOk = okIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton btnOk = new JButton(new ImageIcon(scaledOk));
        btnOk.setPreferredSize(new Dimension(50, 50));
        btnOk.setMargin(new Insets(0, 0, 0, 0));
        btnOk.setBorder(BorderFactory.createEmptyBorder());
        btnOk.setFocusPainted(false);
        btnOk.setContentAreaFilled(false);
        btnOk.addActionListener(e -> handleInput());
        panel.add(btnOk);

        //Admin-Button
        ImageIcon adminIcon = new ImageIcon("Staff.png");
        Image scaledAdmin = adminIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton btnAdmin = new JButton(new ImageIcon(scaledAdmin));
        btnAdmin.setPreferredSize(new Dimension(50, 50));
        btnAdmin.setMargin(new Insets(0, 0, 0, 0));
        btnAdmin.setBorder(BorderFactory.createEmptyBorder());
        btnAdmin.setFocusPainted(false);
        btnAdmin.setContentAreaFilled(false);
        btnAdmin.addActionListener(e -> handleAdminLogin());
        panel.add(btnAdmin);

        //Komponenten anordnen
        keypadPanel.add(displayWrapper, BorderLayout.NORTH);
        keypadPanel.add(panel, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(vendingPanel, BorderLayout.WEST);
        mainPanel.add(keypadPanel, BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 450);
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
                JOptionPane.showMessageDialog(frame, "Ungültige Eingabe");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Bitte eine Zahl eingeben");
        }
    }

    //Admin-Login Passwortabfrage
    private void handleAdminLogin() {
        String password = JOptionPane.showInputDialog(frame, "Passwort eingeben:");

        //Prüfen ob Passwort eingegeben wurde
        if (password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Keine Eingabe erkannt");
            return;
        }

        //Admin-Zugang prüfen
        if ("1234".equals(password.trim())) {
            JOptionPane.showMessageDialog(frame, "Admin-Modus aktiviert");
            handleRestock();
        } else {
            JOptionPane.showMessageDialog(frame, "Falsches Passwort");
        }
    }

    private void handleSnackSelection(int index) {
        Snack gewaehlterSnack = snacks[index];

        if (gewaehlterSnack.getMenge() > 0) {
            if (payment.bezahlen(gewaehlterSnack.getPreis())) {
                gewaehlterSnack.Snackkaufen();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Dieses Produkt ist ausverkauft");
        }
    }

    private void handleRestock() {
        try {
            int snackNummer = Integer.parseInt(JOptionPane.showInputDialog(frame, "Nummer des Snacks zum Auffüllen:"));
            int menge = Integer.parseInt(JOptionPane.showInputDialog(frame, "Menge zum Auffüllen:"));

            if (snackNummer >= 1 && snackNummer <= snacks.length && menge > 0) {
                snacks[snackNummer - 1].auffuellen(menge);
                JOptionPane.showMessageDialog(frame, "Snack erfolgreich aufgefüllt");
            } else {
                JOptionPane.showMessageDialog(frame, "Ungültige Eingabe");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Bitte eine gültige Zahl eingeben");
        }
    }
}