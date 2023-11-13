public class Jubile extends Carte{

    public static final int NB_CARTES = 2;

    public Jubile() {
        this.couleur = EnumCouleur.vert;
        this.points = 3;
        this.nom = "Jubilé";
        this.description = "Placez jusqu'à 2 cartes de votre Main sur vos Oeuvres.";
    }
}
