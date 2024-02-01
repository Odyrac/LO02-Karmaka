package cards;
import game.*;

/**
 * Classe Duperie qui implémente le pouvoir de la carte Duperie
 * Permet de regarder 3 cartes de la main d'un joueur et d'en ajouter une à sa main
 * Hérite de la classe game.Carte
 *
 * @see Carte
 */
public class Duperie extends Carte {

    /**
     * Nombre de cartes dans le jeu
     */
    public static final int NB_CARTES = 3;

    /**
     * Ìnstancie une carte Duperie avec les valeurs par défaut
     */
    public Duperie() {
        this.couleur = EnumCouleur.bleu;
        this.points = 2;
        this.nom = "Duperie";
        this.description = "Regardez 3 cartes de la game.Main d'un rival; ajoutez-en une à votre game.Main.";
    }

    /**
     * Utilise le pouvoir de la carte
     */
    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère le joueur cible (on a toujours 2 joueurs donc c'est forcément l'autre)
        Joueur joueurAdverse = Partie.getInstance().getJoueurAdverse(joueurActuel);
        // on récupère la main du joueur adverse
        Pile mainAdverse = joueurAdverse.getMain();
        // on récupère la main du joueur actuel
        Pile main = joueurActuel.getMain();
        // on ajoute la carte duperie aux cartes jouées pour pouvoir
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on affiche les 3 premières cartes de la main adverse
        Utils.println("Voici les 3 premières cartes de la main de " + joueurAdverse.getPseudo() + " :", "vert");
        Pile cartesMainAdverse = new Pile();
        for (int i = 0; i < 3; i++) {
            try {
                cartesMainAdverse.ajouterCarte(mainAdverse.getCarte(i));
            } catch (Exception e) {
                Utils.println("Erreur : la main de " + joueurAdverse.getPseudo() + " est vide", "rouge");
            }
        }
        if (cartesMainAdverse.getNbCartes() == 0) {
            Utils.println("La main de " + joueurAdverse.getPseudo() + " est vide", "rouge");
        } else {
            Pile.cartesToString(cartesMainAdverse, true, true);
            // on demande au joueur quelle carte il veut ajouter à sa main
            Utils.println("sélectionnez la carte que vous voulez ajouter à votre main :", "vert");
            // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
            int choixCarte = 0;
            boolean carteValide = false;
            while (!carteValide) {
                try {
                    choixCarte = Utils.inputInt("Choix : ", "jaune", true, cartesMainAdverse.getNbCartes());
                    // on récupère la carte choisie
                    Carte carteChoisie = cartesMainAdverse.getCarte(choixCarte - 1);
                    // on l'ajoute à la main du joueur actuel
                    main.ajouterCarte(carteChoisie);
                    // on la retire de la main du joueur adverse
                    mainAdverse.supprimerCarte(mainAdverse.getCarteIndex(carteChoisie));
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
