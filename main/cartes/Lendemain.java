public class Lendemain extends Carte{

    public static final int NB_CARTES = 3;

    public Lendemain() {
        this.couleur = EnumCouleur.vert;
        this.points = 1;
        this.nom = "Lendemain";
        this.description = "Puisez une carte à la Source. Vous pouvez ensuite jouer une autre carte.";
    }
}
