/**
 * Classe Transmigration qui hérite de la classe Carte
 *
 * @see Carte
 */

public class Transmigration extends Carte {

    /**
     * Nombre de cartes transmigration dans le jeu
     */
    public static final int NB_CARTES = 3;

    /**
     * Constructeur de la classe Transmigration
     */
    public Transmigration() {
        this.couleur = EnumCouleur.bleu;
        this.points = 1;
        this.nom = "Transmigration";
        this.description = "Placez dans votre Main n’importe quelle carte de votre Vie Future.";
    }

    /**
     * Utilisation du pouvoir de la carte
     */
    @Override
    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main pour ajouter la carte transmigration aux cartes jouees pour pouvoir
        Pile main = joueurActuel.getMain();
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on affiche la vie future du joueur
        Utils.println("Voici votre vie future :", "vert");
        Pile.cartesToString(joueurActuel.getVieFuture(), true, true);
        // on demande au joueur quelle carte il veut placer dans sa main (on répète la question tant qu'il ne choisit pas une carte valide)
        Utils.println("Quelle carte voulez-vous placer dans votre main ? (1-" + joueurActuel.getVieFuture().getNbCartes() + ")", "vert");
        // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
        int choixCarte = 0;
        boolean carteValide = false;
        while (!carteValide) {
            try {
                choixCarte = Utils.inputInt("Choix : ", "jaune", true, joueurActuel.getVieFuture().getNbCartes());
                // on récupère la carte choisie
                Carte carteChoisie = joueurActuel.getVieFuture().getCarte(choixCarte - 1);
                // on l'ajoute à la main
                main.ajouterCarte(carteChoisie);
                // on la retire de la vie future
                int index = joueurActuel.getVieFuture().getCarteIndex(carteChoisie);
                joueurActuel.getVieFuture().supprimerCarte(index);
                // on affiche un message
                Utils.println("Vous avez placé la carte " + carteChoisie.getNom() + " dans votre main", "vert");
                carteValide = true;
            } catch (Exception e) {
                Utils.println("Erreur : choix invalide", "rouge");
            }
        }
    }
}
