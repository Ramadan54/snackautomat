
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

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
            System.out.println("\nWählen Sie bitte ein Produkt:");
            for (int i = 0; i < snacks.length; i++) {
                snacks[i].KonsoleAnzeige(i+1);  // Zeigt die Snack-Liste mit Nummern an
            }
            System.out.println("0. Beenden");

            System.out.print("Ihre Auswahl: ");
            int auswahl = scanner.nextInt();

            if (auswahl == 0) {
                System.out.println("Danke für Ihren Einkauf!");
                break;
            } else if (auswahl >= 1 && auswahl <= snacks.length) {
                if (snacks[auswahl - 1].Snackkaufen()) {
                    System.out.println("Sie haben " + snacks[auswahl - 1].name + " gekauft.");
                }
            } else {
                System.out.println("Ungültige Eingabe. Bitte erneut versuchen.");
            }
        }

        scanner.close();
    }
}