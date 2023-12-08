public class Sauvetage extends Carte{

    public static final int NB_CARTES = 3;

    public Sauvetage() {
        this.couleur = EnumCouleur.vert;
        this.points = 2;
        this.nom = "Sauvetage";
        this.description = "Ajoutez à votre Main une des 3 dernières cartes de la Fosse.";
    }

    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main pour ajouter la carte longevite aux cartes jouees pour pouvoir
        Pile main = joueurActuel.getMain();
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on récupère la fosse
        Pile fosse = Partie.getInstance().getPlateau().getLaFosse();
        // on crée une pile temporaire pour les 3 dernières cartes de la fosse
        Pile cartesFosse = new Pile();
        for (int i = 0; i < 3; i++) {
            try {
                // on récupère la carte
                Carte carte = fosse.getCarte(fosse.getNbCartes() - 1 - i);
                // on l'ajoute à la pile
                cartesFosse.ajouterCarte(carte);
            } catch (Exception e) {
                Utils.println("Erreur : la fosse est vide", "rouge");
            }
        }
        // on affiche les 3 dernières cartes de la fosse
        Utils.println("Voici les 3 dernières cartes de la fosse :", "vert");
        Pile.cartesToString(cartesFosse, true, true);
        // on demande au joueur d'en choisir une dans le range des cartes de la fosse
        boolean choixValide = false;
        while (!choixValide) {
            Utils.println("Quelle carte voulez-vous ajouter à votre main ? (1-" + cartesFosse.getNbCartes() + ")", "vert");
            // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
            int choixCarte = 0;
            boolean carteValide = false;
            while (!carteValide) {
                try {
                    choixCarte = Utils.inputInt("Choix : ", "jaune", true, cartesFosse.getNbCartes());
                    carteValide = true;
                } catch (Exception e) {
                    Utils.println("Erreur : choix invalide", "rouge");
                }
            }
            // on récupère la carte choisie
            Carte carteChoisie = cartesFosse.getCarte(choixCarte - 1);
            // on l'ajoute à la main du joueur actuel
            main.ajouterCarte(carteChoisie);
            // on affiche un message
            Utils.println("Vous avez ajouté la carte " + carteChoisie.getNom() + " à votre main", "vert");
            // on supprime la carte de la fosse
            int index = fosse.getCarteIndex(carteChoisie);
            fosse.supprimerCarte(index);
        }
    }
}
