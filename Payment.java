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
        
    }
}
