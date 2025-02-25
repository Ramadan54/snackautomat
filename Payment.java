import java.util.Scanner;

public class Payment {
    private Scanner scanner;

    public Payment() {
        this.scanner = new Scanner(System.in);
    }

    public boolean bezahlen(double preis) {
        System.out.println("Wie möchtest du bezahlen?");
        System.out.println("1. Bar");
        System.out.println("2. Kreditkarte");
        System.out.print("Deine Auswahl: ");

        if (!scanner.hasNextInt()) {    //Prüft ob eingabe eine Zahl ist
            System.out.println("Fehler: Bitte geben sie eine Zahl ein!");
            scanner.next(); //ungültige eingabe überspringen
            return false;
        }

        int zahlungsmethode = scanner.nextInt();

        if (zahlungsmethode == 1) {
            return barzahlung(preis);
        } else if (zahlungsmethode == 2) {
            return kartenzahlung(preis);
        } else {
            System.out.println("Ungültige Auswahl. Versuche es erneut.");
            return false;
        }
    }

    //Barzahlung
    private boolean barzahlung(double preis) {
        double eingegebenesGeld = 0; //Startgeld
        System.out.println("Der Preis beträgt: " + preis + " Fr.");

        while (eingegebenesGeld < preis) {
            System.out.print("Gib den Betrag ein: ");

            if (scanner.hasNextDouble()) {
                double betrag = scanner.nextDouble();
                if (betrag > 0) {  // Nur positive Zahlen akzeptieren
                    eingegebenesGeld += betrag;
                    System.out.println("Bisher eingeworfen: " + eingegebenesGeld + " Fr.");
                } else {
                    System.out.println("Ungültiger Betrag! Bitte nochmals versuchen.");
                }
            } else {
                System.out.println("Fehler: Bitte eine gültige Zahl eingeben!");
                scanner.next();  // Ungültige Eingabe überspringen
            }
        }

        double rueckgeld = eingegebenesGeld - preis;
        System.out.println("Erfolgreich bezahlt.");
        if (rueckgeld > 0) {
            System.out.println("Dein Rückgeld: " + rueckgeld + " Fr.");
        }
        return true;
    }

    private boolean kartenzahlung(double preis) {
        System.out.println("Zahlung mit Kreditkarte ausgewählt: ");
        System.out.println("Bitte geben deinen 4 stelligen PIN ein: ");

        int pin = scanner.nextInt(); //simoliert eine PIN eingabe
        if (pin >= 1000 && pin <= 9999) { //Pin überprüfung ob es 4 stellig ist
            System.out.println("Zahlung von " + preis + " Fr. erfolgreich");
            return true;
        }else{
            System.out.println("Falscher PIN. Zahlung fehlgeschlagen.");
            return false;
        }
    }


}
