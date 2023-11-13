public class Recyclage extends Carte{

    public static final int NB_CARTES = 3;

    public Recyclage() {
        this.couleur = EnumCouleur.vert;
        this.points = 1;
        this.nom = "Recyclage";
        this.description = "Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse.";
    }
}
