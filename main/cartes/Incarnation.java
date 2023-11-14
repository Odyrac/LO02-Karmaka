public class Incarnation extends Carte{

    public static final int NB_CARTES = 5;

    public Incarnation() {
        this.couleur = EnumCouleur.mosaique;
        this.points = 1;
        this.nom = "Incarnation";
        this.description = "Choisissez une de vos Oeuvres. Copiez son pouvoir.";
    }
}
