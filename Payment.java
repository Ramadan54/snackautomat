import javax.swing.*;

public class Payment {

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

            if (eingabe == null) return false; // Falls der Nutzer abbricht
            try {
                eingegebenesGeld += Double.parseDouble(eingabe);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ungültige Eingabe!");
            }
        }

        JOptionPane.showMessageDialog(null, "Zahlung erfolgreich!");
        return true;
    }

    private boolean kartenzahlung(double preis) {
        String pin = JOptionPane.showInputDialog("Bitte gib deinen 4-stelligen PIN ein:");
        return pin != null && pin.matches("\\d{4}");
    }
}