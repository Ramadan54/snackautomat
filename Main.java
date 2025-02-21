
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Payment payment = new Payment();

        System.out.println("Willkommen zu unseren Snackautomaten \n");

        //Snack initialisieren
    Snack[] snacks = {
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

        while (true) {
            System.out.println("\n Wähle ein bitte ein Produkt:");
            for (int i = 0; i < snacks.length; i++) {
                snacks[i].KonsoleAnzeige(i+1);  // Zeigt die Snack-Liste mit Nummern an
            }
            System.out.println("0. Beenden");

            System.out.print("Deine Auswahl: ");
            int auswahl = scanner.nextInt();

            if (auswahl == 0) {
                System.out.println("Danke für deinen Einkauf!");
                break;
            } else if (auswahl >= 1 && auswahl <= snacks.length) {
                Snack gewaehlterSnack = snacks[auswahl - 1];

                if (gewaehlterSnack.getMenge() > 0) {
                    System.out.println("Sie haben " + gewaehlterSnack.getName() + " für " + gewaehlterSnack.getPreis() + " Fr. gewählt.");

                    if (payment.bezahlen(gewaehlterSnack.getPreis())) {
                        gewaehlterSnack.Snackkaufen(); // Menge reduzieren
                        System.out.println("Auf Wiedersehen");
                    } else {
                        System.out.println("Zahlung fehlgeschlagen. Bitte erneut versuchen.");
                    }
                } else {
                    System.out.println("Dieses Produkt ist ausverkauft.");
                }
            } else {
                System.out.println("Ungültige Eingabe. Bitte erneut versuchen.");
            }
        }

        scanner.close();
    }
}