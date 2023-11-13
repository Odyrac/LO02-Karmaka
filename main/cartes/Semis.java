public class Semis extends Carte{

    public static final int NB_CARTES = 3;

    public Semis() {
        this.couleur = EnumCouleur.vert;
        this.points = 2;
        this.nom = "Semis";
        this.description = "Puisez 2 cartes à la Source, puis placez sur votre Vie Future 2 cartes de votre Main.";
    }
}
