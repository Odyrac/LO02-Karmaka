public class Jubile extends Carte{

    public static final int NB_CARTES = 2;

    public Jubile() {
        this.couleur = EnumCouleur.vert;
        this.points = 3;
        this.nom = "Jubilé";
        this.description = "Placez jusqu'à 2 cartes de votre Main sur vos Oeuvres.";
    }

    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main
        Pile main = joueurActuel.getMain();
        // on ajoute la carte jubile aux cartes jouées pour pouvoir
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on affiche la main du joueur
        Utils.println("Voici votre main :", "vert");
        Pile.cartesToString(main, true, true);
        // on demande 2 fois au joueur quelle carte il veut placer sur ses oeuvres (0 pour quitter)
        for (int i = 0; i < 2; i++) {
            // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
            // on crée une pile de cartes sans la carte jubile pour ne pas pouvoir la placer sur ses oeuvres
            Pile cartesSansJubile = new Pile();
            for (int j = 0; j < main.getNbCartes(); j++) {
                if (main.getCarte(j) != this) {
                    cartesSansJubile.ajouterCarte(main.getCarte(j));
                }
            }
            int choixCarte = 0;
            boolean carteValide = false;
            while (!carteValide) {
                try {
                    choixCarte = Utils.inputInt("Quelle carte voulez-vous placer sur vos oeuvres ? (1-" + main.getNbCartes() + ") (0 pour quitter)", "jaune", true, main.getNbCartes());
                    // on récupère la carte choisie
                    Carte carteChoisie = main.getCarte(choixCarte - 1);
                    if (choixCarte == 0) {
                        carteValide = true;
                        break;
                    }
                    // on la place sur les oeuvres
                    joueurActuel.getOeuvres().ajouterCarte(carteChoisie);
                    // on la retire de la main
                    main.supprimerCarte(main.getCarteIndex(carteChoisie));
                    Utils.println("Vous avez placé la carte " + carteChoisie.getNom() + " sur vos oeuvres", "vert");
                    carteValide = true;
                } catch (Exception e) {
                    Utils.println("Erreur : choix invalide", "rouge");
                }
            }
        }
    }

}
