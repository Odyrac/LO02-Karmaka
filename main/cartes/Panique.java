public class Panique extends Carte{

        public static final int NB_CARTES = 3;

        public Panique() {
            this.couleur = EnumCouleur.rouge;
            this.points = 1;
            this.nom = "Panique";
            this.description = "Défaussez la première carte de la Pile d'un joueur. Vous pouvez ensuite jouer une autre carte ";
        }

    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        Pile main = joueurActuel.getMain();
        // on récupère le joueur cible (on a toujours 2 joueurs donc c'est forcément l'autre)
        Joueur joueurAdverse = Partie.getInstance().getJoueurAdverse(joueurActuel);
        // on récupère la pile du joueur adverse
        Pile pileAdverse = joueurAdverse.getPile();
        // on ajoute la carte panique aux cartes jouées pour pouvoir
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on défausse la première carte
        try {
            // on récupère la carte
            Carte carte = pileAdverse.getCarte(0);
            // on la défausse
            pileAdverse.defausserCarte(carte);
        } catch (Exception e) {
            Utils.println("Erreur : la pile de " + joueurAdverse.getPseudo() + " est vide", "rouge");
        }
        // on affiche un message
        Utils.println("Vous avez défaussé la première carte de la pile de " + joueurAdverse.getPseudo(), "vert");


        // on demande au joueur s'il veut jouer une autre carte
        Utils.println("Voulez-vous jouer une autre carte ? (o/n)", "vert");
        // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
        boolean choixValide = false;
        while (!choixValide) {
            String choix = Utils.inputString("Choix : ", "jaune");
            if (choix.equals("o")) {
                choixValide = true;
                // on demande au joueur quelle carte il veut jouer parmi sa main - la carte panique
                Utils.println("Quelle carte voulez-vous jouer ? (1-" + (joueurActuel.getMain().getNbCartes() - 1) + ")", "vert");
                // on affiche la main du joueur sans la carte panique
                Pile cartesSansPanique = new Pile();
                for (int i = 0; i < joueurActuel.getMain().getNbCartes(); i++) {
                    if (joueurActuel.getMain().getCarte(i) != this) {
                        cartesSansPanique.ajouterCarte(joueurActuel.getMain().getCarte(i));
                    }
                }
                Pile.cartesToString(cartesSansPanique, true, true);
                // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
                int choixCarte = 0;
                boolean carteValide = false;
                while (!carteValide) {
                    try {
                        choixCarte = Utils.inputInt("Choix : ", "jaune", true, joueurActuel.getMain().getNbCartes() - 1);
                        carteValide = true;
                    } catch (Exception e) {
                        Utils.println("Erreur : choix invalide", "rouge");
                    }
                }
                // on récupère la carte choisie
                Carte carteChoisie = cartesSansPanique.getCarte(choixCarte - 1);
                // on la joue
                carteChoisie.utiliserPouvoir();
            } else if (choix.equals("n")) {
                choixValide = true;
            } else {
                Utils.println("Erreur : choix invalide", "rouge");
            }
        }
    }
}
