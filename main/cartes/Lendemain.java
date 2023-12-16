public class Lendemain extends Carte{

    public static final int NB_CARTES = 3;

    public Lendemain() {
        this.couleur = EnumCouleur.vert;
        this.points = 1;
        this.nom = "Lendemain";
        this.description = "Puisez une carte à la Source. Vous pouvez ensuite jouer une autre carte.";
    }

    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main
        Pile main = joueurActuel.getMain();
        // on ajoute la carte lendemain aux cartes jouées pour pouvoir
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on pioche une carte à la source
        try {
            Carte cartePiochee = Partie.getInstance().getPlateau().getLaSource().piocherCarte();
            joueurActuel.getMain().ajouterCarte(cartePiochee);
        } catch (Exception e) {
            Utils.println("Erreur : la source est vide", "rouge");
        }
        // on affiche la main du joueur
        Utils.println("Voici votre main :", "vert");
        Pile.cartesToString(main, true, true);

        // on demande au joueur s'il veut jouer une autre carte
        Utils.println("Voulez-vous jouer une autre carte ? (o/n)", "vert");
        boolean choixValide = false;
        while (!choixValide) {
            String choix = Utils.inputString("Choix : ", "jaune");
            if (choix.equals("o")) {
                choixValide = true;
                // on demande au joueur quelle carte il veut jouer parmi sa main - la carte lendemain
                Utils.println("Quelle carte voulez-vous jouer ? (1-" + (joueurActuel.getMain().getNbCartes() - 1) + ")", "vert");
                // on affiche la main du joueur sans la carte lendemain
                Pile cartesSansLendemain = new Pile();
                for (int i = 0; i < joueurActuel.getMain().getNbCartes(); i++) {
                    if (joueurActuel.getMain().getCarte(i) != this) {
                        cartesSansLendemain.ajouterCarte(joueurActuel.getMain().getCarte(i));
                    }
                }
                if (cartesSansLendemain.getNbCartes() > 0) {
                    Pile.cartesToString(cartesSansLendemain, true, true);
                    // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
                    int choixCarte = 0;
                    boolean carteValide = false;
                    while (!carteValide) {
                        try {
                            choixCarte = Utils.inputInt("Choix : ", "jaune", true, joueurActuel.getMain().getNbCartes() - 1);
                            // on récupère la carte choisie
                            Carte carteChoisie = cartesSansLendemain.getCarte(choixCarte - 1);
                            // on la joue
                            carteChoisie.utiliserPouvoir();
                            carteValide = true;
                        } catch (Exception e) {
                            Utils.println("Erreur : choix invalide", "rouge");
                        }
                    }
                }else{
                    Utils.println("Vous n'avez pas d'autre carte à jouer", "vert");
                }
            } else if (choix.equals("n")) {
                choixValide = true;
            } else {
                Utils.println("Erreur : choix invalide", "rouge");
            }
        }
    }
}
