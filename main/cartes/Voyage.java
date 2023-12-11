public class Voyage extends Carte{

        public static final int NB_CARTES = 2;

        public Voyage() {
            this.couleur = EnumCouleur.vert;
            this.points = 3;
            this.nom = "Voyage";
            this.description = "Puisez 3 cartes à la Source. Vous pouvez ensuite jouer une autre carte.";
        }

    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main pour ajouter la carte longevite aux cartes jouees pour pouvoir
        Pile main = joueurActuel.getMain();
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on pioche 3 cartes
        for (int i = 0; i < 3; i++) {
            // on récupère la carte
            Carte carte = Partie.getInstance().getPlateau().getLaSource().piocherCarte();
            // on l'ajoute à la main
            main.ajouterCarte(carte);
            // on affiche un message
            Utils.println("Vous avez pioché la carte " + carte.getNom(), "vert");
        }
        // on demande au joueur s'il veut jouer une autre carte
        Utils.println("Voulez-vous jouer une autre carte ? (o/n)", "vert");
        // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
        String choix = "";
        boolean choixValide = false;
        while (!choixValide) {
            try {
                choix = Utils.inputString("Choix : ", "jaune");
                if (choix.equals("o") || choix.equals("n")) {
                    choixValide = true;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                Utils.println("Erreur : choix invalide", "rouge");
            }
        }
        // si le joueur veut jouer une autre carte
        if (choix.equals("o")) {
            // on affiche la main du joueur
            Utils.println("Voici votre main :", "vert");
            Pile.cartesToString(main, true, true);
            // on demande au joueur quelle carte il veut jouer parmi sa main
            Utils.println("Quelle carte voulez-vous jouer ? (1-" + main.getNbCartes() + ")", "vert");
            // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
            int choixCarte = 0;
            boolean carteValide = false;
            while (!carteValide) {
                try {
                    choixCarte = Utils.inputInt("Choix : ", "jaune", true, main.getNbCartes());
                    // on récupère la carte choisie et on la joue
                    Carte carteChoisie = main.getCarte(choixCarte - 1);
                    carteChoisie.utiliserPouvoir();
                    carteValide = true;
                } catch (Exception e) {
                    Utils.println("Erreur : choix invalide", "rouge");
                }
            }
        }
    }
}
