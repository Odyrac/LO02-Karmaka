/**
 * Classe Vengeance qui hérite de la classe Carte
 *
 * @see Carte
 */

public class Vengeance extends Carte {

    /**
     * Nombre de cartes vengeance dans le jeu
     */
    public static final int NB_CARTES = 2;

    /**
     * Constructeur de la classe Vengeance
     */
    public Vengeance() {
        this.couleur = EnumCouleur.rouge;
        this.points = 3;
        this.nom = "Vengeance";
        this.description = "Défaussez l'Oeuvre Exposée d'un rival.";
    }

    /**
     * Utilisation du pouvoir de la carte
     */
    @Override
    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main pour ajouter la carte vengeance aux cartes jouees pour pouvoir
        Pile main = joueurActuel.getMain();
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on récupère le joueur cible (on a toujours 2 joueurs donc c'est forcément l'autre)
        Joueur joueurAdverse = Partie.getInstance().getJoueurAdverse(joueurActuel);
        // on récupère ses œuvres
        Pile oeuvres = joueurAdverse.getOeuvres();
        // on affiche les œuvres du joueur adverse
        Utils.println("Voici les oeuvres de " + joueurAdverse.getPseudo() + " :", "vert");
        Pile.cartesToString(oeuvres, true, true);
        // on defausse la première carte de la pile d'oeuvres du joueur adverse
        try {
            // on récupère la carte
            Carte carte = oeuvres.getCarte(0);
            // on la défausse
            oeuvres.defausserCarte(carte);
            Utils.println("Vous avez défaussé la première carte de la pile d'oeuvres de " + joueurAdverse.getPseudo(), "vert");
        } catch (Exception e) {
            Utils.println("Erreur : la pile d'oeuvres de " + joueurAdverse.getPseudo() + " est vide", "rouge");
        }
    }
}
