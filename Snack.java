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
    public void KonsoleAnzeige(int index){
        System.out.println(index + ". " + name + " - " + preis + " CHF (" + menge + " Stück verfügbar)");
    }

    public boolean Snackkaufen() {
        if (menge > 0) {
            menge--; //Menge reduziert
            return true;

        }else {
            System.out.println("Dieses Produkt ist ausverkauft");
            return false;
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
}
