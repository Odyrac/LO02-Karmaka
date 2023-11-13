public class Vol extends Carte {

    public static final int NB_CARTES = 2;

    public Vol() {
        this.couleur = EnumCouleur.bleu;
        this.points = 3;
        this.nom = "Vol";
        this.description = "Placez dans votre Main l'Oeuvre Exposée d'un rival.";
    }
}
