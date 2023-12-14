import java.io.Serializable;

/**
 * Cette classe représente un joueur virtuel dans le jeu.
 */
public class JoueurVirtuel extends Joueur implements Serializable {

    /**
     * Constructeur de la classe JoueurVirtuel.
     * Initialise le joueur virtuel avec un pseudo par défaut.
     */
    public JoueurVirtuel() {
        // Initialise le joueur en utilisant la méthode de la classe parente avec un pseudo null
        initialiserJoueur(null);
    }
}
