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

        int zahlungsmethode = scanner.nextInt();

        if (zahlungsmethode == 1) {
            return barzahlung(preis);
        } else {
            System.out.println("Versuche es erneut.");
            return false;
        }
    }

    private boolean barzahlung(double preis) {
        double eingegebenesGeld = 0;
        System.out.println("Der Preis beträgt: "+ preis +" Fr.");
        while (eingegebenesGeld < preis) {
            System.out.println("Gib den Betrag den du eingibst: ");
            double betrag = scanner.nextDouble();
            eingegebenesGeld += betrag;
            System.out.println("Bisher eingeworfen: " + eingegebenesGeld + " Fr.");
        }
        
        double rueckgeld = eingegebenesGeld - preis;
        System.out.println("Erfolgreich bezahlt");
        if (rueckgeld > 0) {
            System.out.println("Dein Rückgeld: " + rueckgeld + " Fr.");
        }
        return true;
    }

}
