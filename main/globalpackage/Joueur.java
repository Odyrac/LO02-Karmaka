public class Joueur {
    protected Pile main;
    protected Pile pile;
    protected Pile vieFuture;
    protected Pile oeuvres;
    protected int anneauxKarmiques;
    protected EnumEchelleKarmique positionEchelleKarmique;


    public void initialiserJoueur() {
        anneauxKarmiques = 0;
        positionEchelleKarmique = EnumEchelleKarmique.bousier;
        main = new Pile();
        pile = new Pile();
        vieFuture = new Pile();
        oeuvres = new Pile();


    }

    public Pile getMain() {
        return main;
    }

    public Pile getPile() {
        return pile;
    }
}
