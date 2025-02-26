import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        new SnackAutomatGUI();

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

        String weiterkaufen;

        do {
            System.out.println("\nWähle bitte ein Produkt:");
            for (int i = 0; i < snacks.length; i++) {
                snacks[i].KonsoleAnzeige(i + 1);  // Zeigt die Snack-Liste mit Nummern an
            }
            System.out.println("10. Personal: Snacks auffüllen");
            System.out.println("0. Beenden");

            System.out.print("Deine Auswahl: ");
            int auswahl = scanner.nextInt();

            if (auswahl == 0) {
                break;
            }
            //Snacks auffüllen
            else if (auswahl == 10) {
                System.out.println("Passwort eingeben: ");
                scanner.nextLine();
                String passwort = scanner.nextLine();

                if (passwort.equals("1234")) {
                    System.out.println("Korrektes Passwort. Welche Snacks willst du füllen?:");

                    for (int i = 0; i < snacks.length; i++) {
                        snacks[i].KonsoleAnzeige(i + 1);
                    }

                    System.out.println("Nummer des Snacks: ");
                    int snackNummer = scanner.nextInt();

                    if (snackNummer >= 1 && snackNummer <= snacks.length) {
                        System.out.println("Wie viel soll gefüllt werden?: ");
                        int menge = scanner.nextInt();

                        if (menge > 0) {
                            snacks[snackNummer - 1].auffuellen(menge);
                            System.out.println("Der Snack wurde aufgefüllt");
                        } else {
                            System.out.println("Ungültige Menge!");
                        }
                    } else {
                        System.out.println("Ungültige Snack Nummer");
                    }
                } else {
                    System.out.println("Falsches Passwort");
                }
            }
            //Snacks bezahlen
            else if (auswahl >= 1 && auswahl <= snacks.length) {
                Snack gewaehlterSnack = snacks[auswahl - 1];

                if (gewaehlterSnack.getMenge() > 0) {
                    System.out.println("Du hast " + gewaehlterSnack.getName() + " für " + gewaehlterSnack.getPreis() + " Fr. gewählt.");

                    if (payment.bezahlen(gewaehlterSnack.getPreis())) {
                        gewaehlterSnack.Snackkaufen(); // Menge reduzieren
                    } else {
                        System.out.println("Zahlung fehlgeschlagen. Bitte erneut versuchen.");
                    }
                } else {
                    System.out.println("Dieses Produkt ist ausverkauft.");
                }
            } else {
                System.out.println("Ungültige Eingabe. Bitte erneut versuchen.");
            }

            System.out.println("\nSonst noch etwas? (ja/nein)");
            scanner.nextLine();
            weiterkaufen = scanner.nextLine().toLowerCase();

        } while (weiterkaufen.equals("ja"));

        System.out.println("Danke für deinen Einkauf");
        scanner.close();
    }
}