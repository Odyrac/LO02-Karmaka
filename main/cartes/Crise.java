public class Crise extends Carte{

    public static final int NB_CARTES = 3;

    public Crise() {
        this.couleur = EnumCouleur.rouge;
        this.points = 2;
        this.nom = "Crise";
        this.description = "Le rival de votre choix défausse une de ses Oeuvres.";
    }

    public void utiliserPouvoir() {
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        Joueur joueurAdverse = Partie.getInstance().getJoueurAdverse(joueurActuel);
        Pile mainAdverse = joueurAdverse.getMain();
        // On passe au joueur adverse
        Utils.println("C'est au tour de " + joueurAdverse.getPseudo() + " de choisir une carte à défausser", "vert");
        Partie.getInstance().setJoueurActuel(joueurAdverse);
        // On affiche la main du joueur adverse
        Pile.cartesToString(mainAdverse, true, true);
        Utils.println("Quelle carte voulez-vous défausser ? (1-" + mainAdverse.getNbCartes() + ")", "vert");
        // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
        int choixCarte = 0;
        boolean carteValide = false;
        while (!carteValide) {
            try {
                choixCarte = Utils.inputInt("Choix : ", "jaune", true, mainAdverse.getNbCartes());
                carteValide = true;
            } catch (Exception e) {
                Utils.println("Erreur : choix invalide", "rouge");
            }
        }
        Carte carteChoisie = mainAdverse.getCarte(choixCarte - 1);
        mainAdverse.defausserCarte(carteChoisie);
        // on affiche un message
        Utils.println("Vous avez défaussé la carte " + carteChoisie.getNom() + " de la main de " + joueurAdverse.getPseudo(), "vert");
        // on ajoute la carte crise aux cartes jouées pour pouvoir
        Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
    }
}
