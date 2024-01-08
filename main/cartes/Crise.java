/**
 * Classe Crise qui hérite de la classe Carte
 *
 * @see Carte
 */

public class Crise extends Carte {

    /**
     * Nombre de cartes crise dans le jeu
     */
    public static final int NB_CARTES = 3;

    /**
     * Constructeur de la classe Crise
     */
    public Crise() {
        this.couleur = EnumCouleur.rouge;
        this.points = 2;
        this.nom = "Crise";
        this.description = "Le rival de votre choix défausse une de ses Oeuvres.";
    }

    /**
     * Utilisation du pouvoir de la carte
     */
    @Override
    public void utiliserPouvoir() {
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        //on récupère la main du joueur actuel
        Pile main = joueurActuel.getMain();
        Joueur joueurAdverse = Partie.getInstance().getJoueurAdverse(joueurActuel);
        // on ajoute la carte crise aux cartes jouées pour pouvoir
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        Pile oeuvresAdverse = joueurAdverse.getOeuvres();
        if (oeuvresAdverse.getNbCartes() > 0 && oeuvresAdverse != null) {
            //on passe le tour du joueur adverse
            Partie.getInstance().setJoueurActuel(joueurAdverse);
            // on demande au joueur quelle carte il veut défausser parmi ses oeuvres
            Utils.println("Quelle carte voulez-vous défausser ? (1-" + oeuvresAdverse.getNbCartes() + ")", "vert");
            // on affiche les oeuvres du joueur adverse
            Pile.cartesToString(oeuvresAdverse, true, true);
            // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
            int choixCarte = 0;
            boolean carteValide = false;
            while (!carteValide) {
                try {
                    choixCarte = Utils.inputInt("Choix : ", "jaune", true, oeuvresAdverse.getNbCartes());
                    // on récupère la carte choisie
                    Carte carteChoisie = oeuvresAdverse.getCarte(choixCarte - 1);
                    oeuvresAdverse.defausserCarte(carteChoisie);
                    Utils.println("Vous avez défaussé la carte " + carteChoisie.getNom() + " de la main de " + joueurAdverse.getPseudo(), "vert");
                    carteValide = true;
                } catch (Exception e) {
                    Utils.println("Erreur : choix invalide", "rouge");
                }
            }
        } else {
            Utils.println("Le joueur adverse n'a pas d'oeuvre à défausser", "vert");
        }
        Partie.getInstance().setJoueurActuel(joueurActuel);
        // on affiche un message
        Utils.println("C'est à nouveau à votre tour de jouer", "vert");
    }
}
