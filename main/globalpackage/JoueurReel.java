/**
 * Cette classe représente un joueur réel dans le jeu.
 */
public class JoueurReel extends Joueur  {

    /**
     * Constructeur de la classe JoueurReel.
     * @param pseudo Le pseudo du joueur réel.
     */
    public JoueurReel(String pseudo) {
        // Initialise le joueur en utilisant la méthode de la classe parente
        initialiserJoueur(pseudo);
    }

    public String toString() {
        return "Joueur réel : " + pseudo;
    }
}
