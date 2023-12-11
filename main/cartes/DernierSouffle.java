public class DernierSouffle extends Carte{

        public static final int NB_CARTES = 3;

        public DernierSouffle() {
            this.couleur = EnumCouleur.rouge;
            this.points = 1;
            this.nom = "Dernier Souffle";
            this.description = "Le joueur de votre choix défausse une carte de sa Main.";
        }

    public void utiliserPouvoir() {
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        //on récupère la main du joueur actuel
        Pile main = joueurActuel.getMain();
        Joueur joueurAdverse = Partie.getInstance().getJoueurAdverse(joueurActuel);
        // on ajoute la carte crise aux cartes jouées pour pouvoir
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        Pile mainAdverse = joueurAdverse.getMain();
        if (mainAdverse.getNbCartes() > 0 && mainAdverse != null) {
            //on passe le tour du joueur adverse
            Partie.getInstance().setJoueurActuel(joueurAdverse);
            // on demande au joueur quelle carte il veut défausser parmi s
            Utils.println("Quelle carte voulez-vous défausser ? (1-" + mainAdverse.getNbCartes() + ")", "vert");
            // on affiche la main du joueur adverse
            Pile.cartesToString(mainAdverse, true, true);
            // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
            int choixCarte = 0;
            boolean carteValide = false;
            while (!carteValide) {
                try {
                    choixCarte = Utils.inputInt("Choix : ", "jaune", true, mainAdverse.getNbCartes());
                    // on récupère la carte choisie
                    Carte carteChoisie = mainAdverse.getCarte(choixCarte - 1);
                    mainAdverse.defausserCarte(carteChoisie);
                    Utils.println("Vous avez défaussé la carte " + carteChoisie.getNom() + " de la main de " + joueurAdverse.getPseudo(), "vert");
                    carteValide = true;
                } catch (Exception e) {
                    Utils.println("Erreur : choix invalide", "rouge");
                }
            }
        } else {
            Utils.println("Le joueur adverse n'a pas de carte à défausser", "vert");
        }
        Partie.getInstance().setJoueurActuel(joueurActuel);
        // on affiche un message
        Utils.println("C'est à nouveau à votre tour de jouer", "vert");
    }
}
