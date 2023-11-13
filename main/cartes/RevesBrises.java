public class RevesBrises extends Carte {

    public static final int NB_CARTES = 3;

    public RevesBrises() {
        this.couleur = EnumCouleur.bleu;
        this.points = 1;
        this.nom = "Rêves Brisés";
        this.description = "Placez la première carte de la Vie Future d'un rival sur la vôtre.";
    }
}
