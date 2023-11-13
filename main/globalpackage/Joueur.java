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
    }
}
