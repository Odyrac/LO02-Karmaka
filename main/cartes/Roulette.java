public class Roulette extends Carte{

        public static final int NB_CARTES = 3;

        public Roulette() {
            this.couleur = EnumCouleur.rouge;
            this.points = 2;
            this.nom = "Roulette";
            this.description = "Défaussez jusqu'a 2 cartes de votre Main. Vous pouvez ensuite puiser à la Source autant de carte(s) +1.";
        }

        public void utiliserPouvoir() {
            // on récupère le joueur actuel
            Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
            // on récupère sa main pour ajouter la carte longevite aux cartes jouees pour pouvoir
            Pile main = joueurActuel.getMain();
            if (main.contientCarte(this)) {
                Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
            }
            // on demande au joueur combien de cartes il veut défausser
            Utils.println("Combien de cartes voulez-vous défausser ? (0-2)", "vert");
            // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
            int choixNbCarte = 0;
            boolean carteValide = false;
            while (!carteValide) {
                try {
                    choixNbCarte = Utils.inputInt("Choix : ", "jaune", true, 2);
                    carteValide = true;
                } catch (Exception e) {
                    Utils.println("Erreur : choix invalide", "rouge");
                }
            }
            // on défausse les cartes
            for (int i = 0; i < choixNbCarte; i++) {
                // on affiche la main du joueur
                Utils.println("Voici votre main :", "vert");
                Pile.cartesToString(main, true, true);
                // on demande au joueur quelle carte il veut défausser
                Utils.println("Quelle carte voulez-vous défausser ? (1-" + main.getNbCartes() + ")", "vert");
                // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
                int choixCarte = 0;
                carteValide = false;
                while (!carteValide) {
                    try {
                        choixCarte = Utils.inputInt("Choix : ", "jaune", true, main.getNbCartes());
                        carteValide = true;
                    } catch (Exception e) {
                        Utils.println("Erreur : choix invalide", "rouge");
                    }
                }
                // on récupère la carte choisie
                Carte carteChoisie = main.getCarte(choixCarte - 1);
                // on la défausse
                main.defausserCarte(carteChoisie);
                // on affiche un message
                Utils.println("Vous avez défaussé la carte " + carteChoisie.getNom(), "vert");
            }
            // on affiche la main du joueur
            Utils.println("Voici votre main :", "vert");
            Pile.cartesToString(main, true, true);
            // on demande au joueur combien de cartes il veut piocher
            Utils.println("Combien de cartes voulez-vous piocher ? (0-" + (choixNbCarte + 1) + ")", "vert");
            // on pioche les cartes
            for (int i = 0; i < choixNbCarte + 1; i++) {
                // on récupère la carte piochée
                Carte cartePiochee = Partie.getInstance().getPlateau().getLaSource().piocherCarte();
                // on l'ajoute à la main du joueur
                main.ajouterCarte(cartePiochee);
                // on affiche un message
                Utils.println("Vous avez pioché la carte " + cartePiochee.getNom(), "vert");
            }
            // on affiche la main du joueur
            Utils.println("Voici votre main :", "vert");
            Pile.cartesToString(main, true, true);
        }
}
