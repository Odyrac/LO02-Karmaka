public class CoupDOeil extends Carte {

    public static final int NB_CARTES = 3;
    
    public CoupDOeil() {
        this.couleur = EnumCouleur.bleu;
        this.points = 1;
        this.nom = "Coup d'Oeil";
        this.description = "Regardez la Main d’un rival. Vous pouvez ensuite jouer une autre carte.";
    }

    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main
        Pile main = joueurActuel.getMain();
        // on récupère le joueur cible (on a toujours 2 joueurs donc c'est forcément l'autre)
        Joueur joueurAdverse = Partie.getInstance().getJoueurAdverse(joueurActuel);
        // on récupère la main du joueur adverse
        Pile mainAdverse = joueurAdverse.getMain();
        // on ajoute la carte coup d'oeil aux cartes jouées pour pouvoir
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on affiche la main du joueur adverse
        Utils.println("Voici la main de " + joueurAdverse.getPseudo() + " :", "vert");
        Pile.cartesToString(mainAdverse, true, true);
        // on demande au joueur s'il veut jouer une autre carte
        Utils.println("Voulez-vous jouer une autre carte ? (o/n)", "vert");
        String choix = Utils.inputString("Choix : ", "jaune");

        if (choix.equals("o")) {
            // on demande au joueur quelle carte il veut jouer parmi sa main - la carte coup d'oeil
            Utils.println("Quelle carte voulez-vous jouer ? (1-" + (joueurActuel.getMain().getNbCartes() - 1) + ")", "vert");
            // on affiche la main du joueur sans la carte coup d'oeil
            Pile cartesSansCoupDOeil = new Pile();
            for (int i = 0; i < joueurActuel.getMain().getNbCartes(); i++) {
                if (joueurActuel.getMain().getCarte(i) != this) {
                    cartesSansCoupDOeil.ajouterCarte(joueurActuel.getMain().getCarte(i));
                }
            }
            if (cartesSansCoupDOeil.getNbCartes() > 0) {
                Pile.cartesToString(cartesSansCoupDOeil, true, true);
                // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
                int choixCarte = 0;
                boolean carteValide = false;
                Carte carteChoisie;
                while (!carteValide) {
                    try {
                        choixCarte = Utils.inputInt("Choix : ", "jaune", true, joueurActuel.getMain().getNbCartes() - 1);
                        // on récupère la carte choisie
                        carteChoisie = cartesSansCoupDOeil.getCarte(choixCarte - 1);
                        // on la joue
                        carteChoisie.utiliserPouvoir();
                        carteValide = true;
                    } catch (Exception e) {
                        Utils.println("Erreur : choix invalide", "rouge");
                    }
                }
            }else{
                Utils.println("Vous n'avez pas de carte à jouer", "rouge");
            }
        }
    }
}
