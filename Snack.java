import javax.swing.*;

public class Snack {
    String name;
    double preis;
    int menge;


    // Konstruktor für Initialisierung
    public Snack(String name, double preis, int menge) {
        this.name = name;
        this.preis = preis;
        this.menge = menge;

    }

    public void Snackkaufen() {
        if (menge > 0) {
            menge--; //Menge reduziert
            JOptionPane.showMessageDialog(null, name + " gekauft. Verbleibend: " + menge); //Ausgabe im Dialog
        }
    }

    //Vefügbar machen
    public String getName() {
        return name;
    }
    public double getPreis() {
        return preis;
    }

    public int getMenge() {
        return menge;
    }

    public void auffuellen(int menge) {
        this.menge += menge;
        JOptionPane.showMessageDialog(null, name + " aufgefüllt. Neue Menge: " + this.menge); //Ausgabe im Dialog
    }
}
