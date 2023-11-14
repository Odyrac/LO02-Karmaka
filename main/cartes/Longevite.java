public class Longevite extends Carte{

public static final int NB_CARTES = 3;

    public Longevite() {
        this.couleur = EnumCouleur.vert;
        this.points = 2;
        this.nom = "Longévité";
        this.description = "Placez 2 cartes puiées à la Source sur la Pile d'un joueur.";
    }
}
