/**
 * Cette classe représente un joueur virtuel dans le jeu.
 */
public class JoueurVirtuel extends Joueur implements Strategie {

    private String strategie;

    /**
     * Constructeur de la classe JoueurVirtuel.
     * Initialise le joueur virtuel avec un pseudo par défaut.
     */
    public JoueurVirtuel(String strategie) {
        // Initialise le joueur en utilisant la méthode de la classe parente avec un pseudo null
        initialiserJoueur(null);
        setStrategie(strategie);
    }

    public int jouer(int nombreChoix) {
        if (getStrategie().equals("aleatoire")) {
            return jouerAleatoire(nombreChoix);
        } else if (getStrategie().equals("defensif")) {
            return jouerDefensif(nombreChoix);
        } else if (getStrategie().equals("agressif")) {
            return jouerAgressif(nombreChoix);
        } else {
            return jouerAleatoire(nombreChoix);
        }
    }

    /**
     * Méthode qui permet au joueur virtuel de jouer de manière aléatoire.
     * @param nombreChoix Le nombre de choix disponible pour le joueur.
     * @return Le choix du joueur.
     */

    public int jouerAleatoire(int nombreChoix) {
        return (int) (Math.random() * nombreChoix);
    }

    /**
     * Méthode qui permet au joueur virtuel de jouer de manière défensive.
     * @param nombreChoix Le nombre de choix disponible pour le joueur.
     * @return Le choix du joueur.
     */

    public int jouerDefensif(int nombreChoix) {
        if (nombreChoix == 5) {
            // Si le nombre de choix est 5 (menu de jeu), alors il ne joue pas le choix 4 (pouvoir)
            return (int) (Math.random() * 4) + 1;
        } else {
            return (int) (Math.random() * nombreChoix);
        }
    }

    /**
     * Méthode qui permet au joueur virtuel de jouer de manière agressive.
     * @param nombreChoix Le nombre de choix disponible pour le joueur.
     * @return Le choix du joueur.
     */

    public int jouerAgressif(int nombreChoix) {
        if (nombreChoix == 5) {
            // Si le nombre de choix est 5 (menu de jeu), alors il y a 2 chance sur 3 qu'il joue le choix 4 (pouvoir) sinon il joue un autre choix
            if ((int) (Math.random() * 3) == 0) {
                return 4;
            } else {
                return (int) (Math.random() * 4) + 1;
            }
        } else {
            return (int) (Math.random() * nombreChoix);
        }
    }


    public String getStrategie() {
        return strategie;
    }

    public void setStrategie(String strategie) {
        this.strategie = strategie;
    }
}
