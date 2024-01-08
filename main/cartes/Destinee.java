/**
 * Classe Destinee qui hérite de la classe Carte
 *
 * @see Carte
 */

public class Destinee extends Carte {

    /**
     * Nombre de cartes destinée dans le jeu
     */
    public static final int NB_CARTES = 2;

    /**
     * Constructeur de la classe Destinee
     */
    public Destinee() {
        this.couleur = EnumCouleur.bleu;
        this.points = 2;
        this.nom = "Destinée";
        this.description = "Regardez les 3 premières cartes de la Source ; ajoutez-en jusqu’à 2 à votre Vie Future. Replacez le reste dans l'ordre souhaité.";
    }

    /**
     * Utilisation du pouvoir de la carte
     */
    @Override
    public void utiliserPouvoir() {
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        //on récupère sa main
        Pile main = joueurActuel.getMain();
        Pile source = Partie.getInstance().getPlateau().getLaSource();
        // on ajoute la carte destinée aux cartes jouées pour pouvoir
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on affiche les 3 premières cartes de la source
        Utils.println("Voici les 3 premières cartes de la source :", "vert");
        Pile cartesSource = new Pile();
        for (int i = 0; i < 3; i++) {
            cartesSource.ajouterCarte(source.getCarte(i));
        }
        // on demande au joueur quelles cartes il veut ajouter à sa vie future (0 pour sortir)
        Utils.println("sélectionnez les cartes que vous voulez ajouter à votre vie future (0 pour sortir) :", "vert");
        // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
        int choixCarte = 0;
        boolean carteValide = false;
        for (int i = 0; i < 2; i++) {
            // on affiche les cartes de la source
            Pile.cartesToString(cartesSource, true, true);
            while (!carteValide) {
                try {
                    choixCarte = Utils.inputInt("Choix : ", "jaune", true, cartesSource.getNbCartes());
                    if (choixCarte != 0) {
                        // on récupère la carte choisie
                        Carte carteChoisie = cartesSource.getCarte(choixCarte - 1);
                        // on l'ajoute à la vie future du joueur
                        joueurActuel.getVieFuture().ajouterCarte(carteChoisie);
                        // on la retire de la source et de la pile cartesSource
                        source.supprimerCarte(source.getCarteIndex(carteChoisie));
                        cartesSource.supprimerCarte(cartesSource.getCarteIndex(carteChoisie));
                        // on affiche un message
                        Utils.println("Vous avez ajouté la carte " + carteChoisie.getNom() + " à votre vie future", "vert");
                    }
                    carteValide = true;
                } catch (Exception e) {
                    Utils.println("Erreur : choix invalide", "rouge");
                }
            }

        }
    }


}