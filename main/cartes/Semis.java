public class Semis extends Carte{

    public static final int NB_CARTES = 3;

    public Semis() {
        this.couleur = EnumCouleur.vert;
        this.points = 2;
        this.nom = "Semis";
        this.description = "Puisez 2 cartes à la Source, puis placez sur votre Vie Future 2 cartes de votre Main.";
    }

    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main pour ajouter la carte longevite aux cartes jouees pour pouvoir
        Pile main = joueurActuel.getMain();
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on pioche 2 cartes à la source
        Pile source = Partie.getInstance().getPlateau().getLaSource();
        for (int i = 0; i < 2; i++) {
            // on récupère la carte
            Carte carte = source.piocherCarte();
            // on l'ajoute à la main
            main.ajouterCarte(carte);
        }
        // on affiche un message
        Utils.println("Vous avez pioché 2 cartes à la source", "vert");

        // placer 2 cartes de la main sur la vie future
        for(int i = 0; i < 2; i++) {
            // on affiche la main du joueur
            Utils.println("Voici votre main :", "vert");
            Pile.cartesToString(main, true, true);
            if (main.getNbCartes() > 0) {
                // on demande au joueur quelle carte il veut placer sur sa vie future
                Utils.println("Quelle carte voulez-vous placer sur votre vie future ? (1-" + main.getNbCartes() + ")", "vert");
                // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
                int choixCarte = 0;
                boolean carteValide = false;
                while (!carteValide) {
                    try {
                        choixCarte = Utils.inputInt("Choix : ", "jaune", true, main.getNbCartes());
                        // on récupère la carte choisie
                        Carte carteChoisie = main.getCarte(choixCarte - 1);
                        // on l'ajoute à la vie future
                        joueurActuel.getVieFuture().ajouterCarte(carteChoisie);
                        // on la retire de la main
                        int index = main.getCarteIndex(carteChoisie);
                        main.supprimerCarte(index);
                        // on affiche un message
                        Utils.println("Vous avez placé la carte " + carteChoisie.getNom() + " sur votre vie future", "vert");
                        carteValide = true;
                    } catch (Exception e) {
                        Utils.println("Erreur : choix invalide", "rouge");
                    }
                }
            } else {
                Utils.println("Vous n'avez plus de cartes dans votre main", "vert");
            }
        }
    }
}
