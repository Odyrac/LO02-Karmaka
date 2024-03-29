package cards;
import game.*;

/**
 * Classe Longevite qui implémente le pouvoir de la carte Longevite
 * Permet de placer 2 cartes de la source sur la pile d'un joueur
 * Hérite de la classe game.Carte
 *
 * @see Carte
 */
public class Longevite extends Carte {

    /**
     * Nombre de cartes dans le jeu
     */
    public static final int NB_CARTES = 3;

    /**
     * Ìnstancie une carte Longevite avec les valeurs par défaut
     */
    public Longevite() {
        this.couleur = EnumCouleur.vert;
        this.points = 2;
        this.nom = "Longévité";
        this.description = "Placez 2 cartes puisées à la Source sur la game.Pile d'un joueur.";
    }

    /**
     * Utilise le pouvoir de la carte
     */
    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main pour ajouter la carte longevite aux cartes jouees pour pouvoir
        Pile main = joueurActuel.getMain();
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on récupère le joueur cible (on a toujours 2 joueurs donc c'est forcément l'autre)
        Joueur joueurAdverse = Partie.getInstance().getJoueurAdverse(joueurActuel);
        // on récupère sa pile
        Pile pile = joueurAdverse.getPile();
        // on pioche 2 cartes à la source
        Pile source = Partie.getInstance().getPlateau().getLaSource();
        for (int i = 0; i < 2; i++) {
            try {
                // on récupère la carte
                Carte carte = source.piocherCarte();
                // on l'ajoute à la pile
                pile.ajouterCarte(carte);
            } catch (Exception e) {
                Utils.println("Erreur : la source est vide", "rouge");
            }
        }
        // on affiche un message
        Utils.println("Vous avez placé 2 cartes de la source sur la pile de " + joueurAdverse.getPseudo(), "vert");
    }
}
