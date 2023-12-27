public class Recyclage extends Carte{

    public static final int NB_CARTES = 3;

    public Recyclage() {
        this.couleur = EnumCouleur.vert;
        this.points = 1;
        this.nom = "Recyclage";
        this.description = "Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse.";
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
        // on récupère la vie future du joueur actuel
        Pile vieFuture = joueurActuel.getVieFuture();
        // on récupère les 3 dernières cartes de la fosse
        Pile cartesFosse = new Pile();
        for (int i = 0; i < 3; i++) {
            try {
                // on récupère la carte
                Carte carte = fosse.getCarte(fosse.getNbCartes() - 1);
                // on l'ajoute à la pile
                cartesFosse.ajouterCarte(carte);
                // on la supprime de la fosse
                int index = fosse.getCarteIndex(carte);
                fosse.supprimerCarte(index);
            } catch (Exception e) {
                Utils.println("Erreur : la fosse est vide", "rouge");
            }
        }
        if (cartesFosse.getNbCartes() > 0) {
            // on affiche les 3 dernières cartes de la fosse
            Utils.println("Voici les 3 dernières cartes de la fosse :", "vert");
            Pile.cartesToString(cartesFosse, true, true);
            // on demande au joueur d'en choisir une dans le range des cartes de la fosse
            boolean choixValide = false;
            while (!choixValide) {
                Utils.println("Quelle carte voulez-vous ajouter à votre vie future ? (1-" + cartesFosse.getNbCartes() + ")", "vert");
                // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
                int choixCarte = 0;
                boolean carteValide = false;
                Carte carteChoisie = null;
                while (!carteValide) {
                    try {
                        choixCarte = Utils.inputInt("Choix : ", "jaune", true, cartesFosse.getNbCartes());
                        // on récupère la carte choisie
                        carteChoisie = cartesFosse.getCarte(choixCarte - 1);
                        carteValide = true;
                    } catch (Exception e) {
                        Utils.println("Erreur : choix invalide", "rouge");
                    }
                }
                // on l'ajoute à la vie future du joueur actuel
                vieFuture.ajouterCarte(carteChoisie);
                // on affiche un message
                Utils.println("Vous avez ajouté la carte " + carteChoisie.getNom() + " à votre vie future", "vert");
                choixValide = true;
            }
        } else {
            Utils.println("Il n'y a plus de cartes dans la fosse", "vert");
        }
    }
}
