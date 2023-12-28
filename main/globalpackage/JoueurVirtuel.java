import java.util.Random;

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
        // Initialise le joueur en utilisant la méthode de la classe parente avec un
        // pseudo null
        initialiserJoueur(null);
        setStrategie(strategie);
    }

    public int jouer(int minVal, int nombreChoix) {
        Utils.println(minVal + " " + nombreChoix, "orange");
        if (getStrategie().equals("aleatoire")) {
            return jouerAleatoire(minVal, nombreChoix);
        } else if (getStrategie().equals("defensif")) {
            return jouerDefensif(minVal, nombreChoix);
        } else if (getStrategie().equals("agressif")) {
            return jouerAgressif(minVal, nombreChoix);
        } else {
            return jouerAleatoire(minVal, nombreChoix);
        }
    }

    /**
     * Méthode qui permet au joueur virtuel de jouer de manière aléatoire.
     * 
     * @param minVal      La valeur minimale possible.
     * @param nombreChoix Le nombre de choix disponible pour le joueur.
     * @return Le choix du joueur.
     */
    public int jouerAleatoire(int minVal, int nombreChoix) {
        if (minVal == nombreChoix) {
            Utils.println("[BOT] Choix du JoueurVirtuel : " + minVal + "/" + nombreChoix, "rose");
            return minVal;
        }
        int choix = minVal + (int) (Math.random() * (nombreChoix - minVal + 1));
        Utils.println("[BOT] Choix du JoueurVirtuel : " + choix + "/" + nombreChoix, "rose");
        return choix;
    }

    /**
     * Méthode qui permet au joueur virtuel de jouer de manière défensive.
     * 
     * @param minVal      La valeur minimale possible.
     * @param nombreChoix Le nombre de choix disponible pour le joueur.
     * @return Le choix du joueur.
     */
    public int jouerDefensif(int minVal, int nombreChoix) {
        if (minVal == nombreChoix) {
            Utils.println("[BOT] Choix du JoueurVirtuel : " + minVal + "/" + nombreChoix, "rose");
            return minVal;
        }

        int choix;
        if (nombreChoix == 5) {

            // Si le nombre de choix est 5 (menu de jeu), alors il ne joue pas le choix 3
            // (pouvoir)
            Random random = new Random();
            int[] valeursPossibles = { 1, 2, 4, 5 };
            int indexAleatoire = random.nextInt(valeursPossibles.length);
            return valeursPossibles[indexAleatoire];
            
        } else {
            choix = minVal + (int) (Math.random() * (nombreChoix - minVal + 1));
        }

        Utils.println("[BOT] Choix du JoueurVirtuel : " + choix, "rose");
        return choix;
    }

    /**
     * Méthode qui permet au joueur virtuel de jouer de manière agressive.
     * 
     * @param minVal      La valeur minimale possible.
     * @param nombreChoix Le nombre de choix disponible pour le joueur.
     * @return Le choix du joueur.
     */
    public int jouerAgressif(int minVal, int nombreChoix) {
        if (minVal == nombreChoix) {
            Utils.println("[BOT] Choix du JoueurVirtuel : " + minVal + "/" + nombreChoix, "rose");
            return minVal;
        }

        int choix;

        // Si le nombre de choix est 5 (menu de jeu), alors il y a une chance sur 2
        // qu'il
        // joue le choix 4 (pouvoir) si il a des cartes dans sa main
        if (nombreChoix == 5 && (int) (Math.random() * 2) == 0
                && Partie.getInstance().getJoueurActuel().getMain().getNbCartes() > 0) {
            choix = 4;
        } else {
            choix = minVal + (int) (Math.random() * (nombreChoix - minVal + 1));
        }

        Utils.println("[BOT] Choix du JoueurVirtuel : " + choix, "rose");
        return choix;
    }

    public String getStrategie() {
        return strategie;
    }

    public void setStrategie(String strategie) {
        this.strategie = strategie;
    }
}
