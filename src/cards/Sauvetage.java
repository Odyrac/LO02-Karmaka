package cards;
import game.*;

/**
 * Classe Sauvetage qui implémente le pouvoir de la carte Sauvetage
 * Permet de récupérer une des 3 dernières cartes de la fosse
 * Hérite de la classe game.Carte
 *
 * @see Carte
 */
public class Sauvetage extends Carte {

    /**
     * Nombre de cartes dans le jeu
     */
    public static final int NB_CARTES = 3;

    /**
     * Ìnstancie une carte Sauvetage avec les valeurs par défaut
     */
    public Sauvetage() {
        this.couleur = EnumCouleur.vert;
        this.points = 2;
        this.nom = "Sauvetage";
        this.description = "Ajoutez à votre game.Main une des 3 dernières cartes de la Fosse.";
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
        // on récupère la fosse
        Pile fosse = Partie.getInstance().getPlateau().getLaFosse();
        // on crée une pile temporaire pour les 3 dernières cartes de la fosse
        Pile cartesFosse = new Pile();
        for (int i = 0; i < 3; i++) {
            try {
                // on récupère la carte
                Carte carte = fosse.getCarte(fosse.getNbCartes() - 1 - i);
                // on l'ajoute à la pile
                cartesFosse.ajouterCarte(carte);
            } catch (Exception e) {
                Utils.println("Erreur : la fosse est vide", "rouge");
            }
        }
        if (cartesFosse.getNbCartes() == 0) {
            Utils.println("La fosse est vide", "rouge");
        } else {
            // on affiche les 3 dernières cartes de la fosse
            Utils.println("Voici les 3 dernières cartes de la fosse :", "vert");
            Pile.cartesToString(cartesFosse, true, true);
            // on demande au joueur d'en choisir une dans le range des cartes de la fosse
            Utils.println("Quelle carte voulez-vous ajouter à votre main ? (1-" + cartesFosse.getNbCartes() + ")", "vert");
            // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
            int choixCarte = 0;
            boolean carteValide = false;
            while (!carteValide) {
                try {
                    choixCarte = Utils.inputInt("Choix : ", "jaune", true, cartesFosse.getNbCartes());
                    // on récupère la carte choisie
                    Carte carteChoisie = cartesFosse.getCarte(choixCarte - 1);
                    // on l'ajoute à la main
                    main.ajouterCarte(carteChoisie);
                    // on la retire de la fosse
                    fosse.supprimerCarte(fosse.getCarteIndex(carteChoisie));
                    // on affiche un message
                    Utils.println("Vous avez ajouté la carte " + carteChoisie.getNom() + " à votre main", "vert");
                    carteValide = true;
                } catch (Exception e) {
                    Utils.println("Erreur : choix invalide", "rouge");
                }
            }
        }
    }
}
