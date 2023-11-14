public class Sauvetage extends Carte{

    public static final int NB_CARTES = 3;

    public Sauvetage() {
        this.couleur = EnumCouleur.vert;
        this.points = 2;
        this.nom = "Sauvetage";
        this.description = "Ajoutez à votre Main une des 3 dernières cartes de la Fosse.";
    }
}
