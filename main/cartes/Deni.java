/**
 * Classe Deni qui hérite de la classe Carte
 *
 * @see Carte
 */

public class Deni extends Carte {

    /**
     * Nombre de cartes deni dans le jeu
     */
    public static final int NB_CARTES = 3;

    /**
     * Constructeur de la classe Deni
     */
    public Deni() {
        this.couleur = EnumCouleur.bleu;
        this.points = 2;
        this.nom = "Deni";
        this.description = "Défaussez une carte de votre Main. Copiez le pouvoir de cette carte.";
    }

    /**
     * Utilisation du pouvoir de la carte
     */
    @Override
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
        Utils.println("Voici votre main :", "vert");
        Pile.cartesToString(cartesSansDeni, true, true);
        // on demande au joueur quelle carte il veut défausser parmi sa main - la carte deni
        Utils.println("Quelle carte voulez-vous défausser ? (1-" + (cartesSansDeni.getNbCartes()) + ")", "vert");
        // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
        int choixCarte = 0;
        boolean carteValide = false;
        while (!carteValide) {
            try {
                choixCarte = Utils.inputInt("Choix : ", "jaune", true, cartesSansDeni.getNbCartes());
                // on récupère la carte choisie
                Carte carteChoisie = main.getCarte(choixCarte - 1);
                main.defausserCarte(carteChoisie);
                Utils.println("Vous avez défaussé la carte " + carteChoisie.getNom() + " de votre main", "vert");
                // on utilise le pouvoir de la carte choisie
                carteChoisie.utiliserPouvoir();
                carteValide = true;
            } catch (Exception e) {
                Utils.println("Erreur : choix invalide", "rouge");
            }
        }
    }
}
