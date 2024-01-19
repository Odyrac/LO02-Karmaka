package cards;
import game.*;

/**
 * Classe Roulette qui implémente le pouvoir de la carte Roulette
 * Permet de défausser jusqu'à 2 cartes de sa main et de piocher autant de cartes +1 à la source
 * Hérite de la classe game.Carte
 *
 * @see Carte
 */
public class Roulette extends Carte {

    /**
     * Nombre de cartes dans le jeu
     */
    public static final int NB_CARTES = 3;

    /**
     * Ìnstancie une carte Roulette avec les valeurs par défaut
     */
    public Roulette() {
        this.couleur = EnumCouleur.rouge;
        this.points = 2;
        this.nom = "Roulette";
        this.description = "Défaussez jusqu'a 2 cartes de votre game.Main. Vous pouvez ensuite puiser à la Source autant de carte(s) +1.";
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
        // on demande au joueur combien de cartes il veut défausser
        Utils.println("Combien de cartes voulez-vous défausser ? (0-2)", "vert");
        // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
        int choixNbCarte = 0;
        boolean carteValide = false;
        while (!carteValide) {
            try {
                choixNbCarte = Utils.inputInt("Choix : ", "jaune", true, 2, true);
                carteValide = true;
            } catch (Exception e) {
                Utils.println("Erreur : choix invalide", "rouge");
            }
        }
        // on cree une pile cartessansroulette pour ne pas pouvoir placer la carte roulette sur la vie future
        Pile cartesSansRoulette = new Pile();
        for (int i = 0; i < main.getNbCartes(); i++) {
            if (main.getCarte(i) != this) {
                cartesSansRoulette.ajouterCarte(main.getCarte(i));
            }
        }
        // on défausse les cartes
        for (int i = 0; i < choixNbCarte; i++) {
            if (cartesSansRoulette.getNbCartes() > 0) {
                Pile.cartesToString(cartesSansRoulette, true, true);
                // on demande au joueur quelle carte il veut défausser
                Utils.println("Quelle carte voulez-vous défausser ? (1-" + cartesSansRoulette.getNbCartes() + ")", "vert");
                // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
                int choixCarte = 0;
                carteValide = false;
                while (!carteValide) {
                    try {
                        choixCarte = Utils.inputInt("Choix : ", "jaune", true, main.getNbCartes());
                        // on récupère la carte choisie
                        Carte carteChoisie = main.getCarte(choixCarte - 1);
                        // on la défausse
                        main.defausserCarte(carteChoisie);
                        // on affiche un message
                        Utils.println("Vous avez défaussé la carte " + carteChoisie.getNom(), "vert");
                        carteValide = true;
                    } catch (Exception e) {
                        Utils.println("Erreur : choix invalide", "rouge");
                    }
                }
            } else {
                Utils.println("Vous n'avez pas de carte à défausser", "rouge");
            }
        }
        // on affiche la main du joueur
        Utils.println("Voici votre main :", "vert");
        Pile.cartesToString(main, true, true);
        // on demande au joueur combien de cartes il veut piocher
        Utils.println("Combien de cartes voulez-vous piocher ? (0-" + (choixNbCarte + 1) + ")", "vert");
        // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
        carteValide = false;
        while (!carteValide) {
            try {
                choixNbCarte = Utils.inputInt("Choix : ", "jaune", true, choixNbCarte + 1, true);
                carteValide = true;
            } catch (Exception e) {
                Utils.println("Erreur : choix invalide", "rouge");
            }
        }
        // on pioche les cartes
        for (int i = 0; i < choixNbCarte + 1; i++) {
            try {
                // on récupère la carte piochée
                Carte cartePiochee = Partie.getInstance().getPlateau().getLaSource().piocherCarte();
                // on l'ajoute à la main du joueur
                main.ajouterCarte(cartePiochee);
                // on affiche un message
                Utils.println("Vous avez pioché la carte " + cartePiochee.getNom(), "vert");
            } catch (Exception e) {
                Utils.println("Erreur : la source est vide", "rouge");
            }
        }
        // on affiche la main du joueur
        Utils.println("Voici votre main :", "vert");
        Pile.cartesToString(main, true, true);
    }
}
