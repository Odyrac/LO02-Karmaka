public class Mimetisme extends Carte{

    public static final int NB_CARTES = 2;

    public Mimetisme() {
        this.couleur = EnumCouleur.mosaique;
        this.points = 1;
        this.nom = "Mimétisme";
        this.description = "Choisissez un Rival. Copiez le pouvoir de son Oeuvre Exposée.";
    }
}
