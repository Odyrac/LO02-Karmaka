package cards;
import game.*;

/**
 * Classe Bassesse qui implémente le pouvoir de la carte Bassesse
 * Permet de défausser 2 cartes au hasard de la main d'un joueur
 * Hérite de la classe game.Carte
 *
 * @see Carte
 */
public class Bassesse extends Carte {

    /**
     * Nombre de cartes dans le jeu
     */
    public static final int NB_CARTES = 2;

    /**
     * Ìnstancie une carte Bassesse avec les valeurs par défaut
     */
    public Bassesse() {
        this.couleur = EnumCouleur.rouge;
        this.points = 3;
        this.nom = "Bassesse";
        this.description = "Défaussez au hasard 2 cartes de la game.Main d'un rival.";
    }

    /**
     * Utilise le pouvoir de la carte
     */
    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main
        Pile main = joueurActuel.getMain();
        // on récupère le joueur cible (on a toujours 2 joueurs donc c'est forcément l'autre)
        Joueur joueurAdverse = Partie.getInstance().getJoueurAdverse(joueurActuel);
        // on récupère la main du joueur adverse
        Pile mainAdverse = joueurAdverse.getMain();
        // on ajoute la carte bassesse aux cartes jouées pour pouvoir
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on défausse 2 cartes au hasard
        for (int i = 0; i < 2; i++) {
            try {
                // on récupère une carte au hasard
                Carte carte = mainAdverse.getCarteAleatoire();
                // on la défausse
                mainAdverse.defausserCarte(carte);
            } catch (Exception e) {
                Utils.println("Erreur : la main de " + joueurAdverse.getPseudo() + " est vide", "rouge");
            }
        }
        // on affiche un message
        Utils.println("Vous avez défaussé 2 cartes au hasard de la main de " + joueurAdverse.getPseudo(), "vert");
    }
}
