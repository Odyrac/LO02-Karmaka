package cards;
import game.*;

/**
 * Classe Deni qui implémente le pouvoir de la carte Deni
 * Permet de défausser une carte de sa main et de copier son pouvoir
 * Hérite de la classe game.Carte
 *
 * @see Carte
 */
public class Deni extends Carte {

    /**
     * Nombre de cartes dans le jeu
     */
    public static final int NB_CARTES = 3;

    /**
     * Ìnstancie une carte Deni avec les valeurs par défaut
     */
    public Deni() {
        this.couleur = EnumCouleur.bleu;
        this.points = 2;
        this.nom = "Deni";
        this.description = "Défaussez une carte de votre game.Main. Copiez le pouvoir de cette carte.";
    }

    /**
     * Utilise le pouvoir de la carte
     */
    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main
        Pile main = joueurActuel.getMain();
        // on ajoute la carte deni aux cartes jouées pour pouvoir si la carte est dans la main
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on défausse tout de suite la carte deni pour éviter qu'elle reste si on utilise une autre carte déni
        main.defausserCarte(this);
        // on affiche la main du joueur
        Pile cartesSansDeni = new Pile();
        for (int i = 0; i < main.getNbCartes(); i++) {
            if (main.getCarte(i) != this) {
                cartesSansDeni.ajouterCarte(main.getCarte(i));
            }
        }
        if (cartesSansDeni.getNbCartes() <= 0) {
            Utils.println("Vous n'avez pas de carte à copier", "rouge");
        } else {
            Utils.println("Voici votre main :", "vert");
            Pile.cartesToString(cartesSansDeni, true, true);
            // on demande au joueur quelle carte il veut défausser (on répète la question tant qu'il ne choisit pas une carte valide)
            Utils.println("Quelle carte voulez-vous défausser ? (1-" + cartesSansDeni.getNbCartes() + ")", "vert");
            // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
            int choixCarte = 0;
            boolean carteValide = false;
            while (!carteValide) {
                try {
                    choixCarte = Utils.inputInt("Choix : ", "jaune", true, cartesSansDeni.getNbCartes());
                    // on récupère la carte choisie
                    Carte carteChoisie = cartesSansDeni.getCarte(choixCarte - 1);
                    // on la défausse
                    main.defausserCarte(carteChoisie);
                    // on crée une instance temporaire de la carte choisie pour ne pas rajouter et on utilise son pouvoir
                    Carte carteTemp = null;
                    try {
                        carteTemp = carteChoisie.getClass().getDeclaredConstructor().newInstance();
                        carteTemp.utiliserPouvoir();
                        // on supprime la carte temporaire
                        carteTemp = null;
                    } catch (Exception e) {
                        Utils.println("Erreur : impossible de copier la carte", "rouge");
                    }
                    carteValide = true;
                } catch (Exception e) {
                    Utils.println("Erreur : choix invalide", "rouge");
                }
            }
        }
    }
}
