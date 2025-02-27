import javax.swing.*;

public class Payment {
    private SnackAutomatGUI gui; // 🟢 Referenz zur GUI

    public Payment(SnackAutomatGUI gui) { // 🟢 Konstruktor mit GUI-Referenz
        this.gui = gui;
    }

    public boolean bezahlen(double preis) {
        String[] optionen = {"Bar", "Kreditkarte"};
        int zahlungsmethode = JOptionPane.showOptionDialog(null, "Wie möchtest du bezahlen?",
                "Zahlungsmethode", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionen, optionen[0]);

        if (zahlungsmethode == 0) {
            return barzahlung(preis);
        } else if (zahlungsmethode == 1) {
            return kartenzahlung(preis);
        }
        return false;
    }

    private boolean barzahlung(double preis) {
        double eingegebenesGeld = 0;

        while (eingegebenesGeld < preis) {
            String eingabe = JOptionPane.showInputDialog("Preis: " + preis + " Fr.\nGib den Betrag ein:");

            if (eingabe == null) return false; // Falls "Abbrechen" gedrückt wird, Zahlung abbrechen
            try {
                double betrag = Double.parseDouble(eingabe);

                if (betrag > 0) { // Nur positive Beträge akzeptieren
                    eingegebenesGeld += betrag;
                    JOptionPane.showMessageDialog(null, "Bisher eingeworfen: " + String.format("%.2f", eingegebenesGeld) + " Fr.");
                } else {
                    JOptionPane.showMessageDialog(null, "Ungültiger Betrag");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Bitte eine gültige Zahl eingeben!");
            }
        }

        double rueckgeld = eingegebenesGeld - preis;
        JOptionPane.showMessageDialog(null, "Zahlung erfolgreich!\nRückgeld: " + String.format("%.2f", rueckgeld) + " Fr.");

        //Guthaben aktualisieren und anzeigen
        gui.updateGuthaben(-preis);
        return true;
    }

    private boolean kartenzahlung(double preis) {
        String pin = JOptionPane.showInputDialog("Bitte gib deinen 4-stelligen PIN ein:");
        if (pin != null && pin.matches("\\d{4}")) {
            gui.updateGuthaben(-preis); //Auch bei Kartenzahlung Guthaben aktualisieren
            return true;
        }
        JOptionPane.showMessageDialog(null, "Ungültige PIN!");
        return false;
    }
}