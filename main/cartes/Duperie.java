public class Duperie extends Carte{

    public static final int NB_CARTES = 3;

    public Duperie() {
        this.couleur = EnumCouleur.bleu;
        this.points = 2;
        this.nom = "Duperie";
        this.description = "Regardez 3 cartes de la Main d'un rival; ajoutez-en une à votre Main.";
    }
}
