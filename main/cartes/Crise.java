public class Crise extends Carte{

    public static final int NB_CARTES = 3;

    public Crise() {
        this.couleur = EnumCouleur.rouge;
        this.points = 2;
        this.nom = "Crise";
        this.description = "Le rival de votre choix défausse une de ses Oeuvres.";
    }
}
