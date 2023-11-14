public class Deni extends Carte {

    public static final int NB_CARTES = 3;

    public Deni() {
        this.couleur = EnumCouleur.bleu;
        this.points = 2;
        this.nom = "Deni";
        this.description = "Défaussez une carte de votre Main. Copiez le pouvoir de cette carte.";
    }
}
