public class Semis extends Carte{

    public static final int NB_CARTES = 3;

    public Semis() {
        this.couleur = EnumCouleur.vert;
        this.points = 2;
        this.nom = "Semis";
        this.description = "Puisez 2 cartes à la Source, puis placez sur votre Vie Future 2 cartes de votre Main.";
    }

    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main pour ajouter la carte longevite aux cartes jouees pour pouvoir
        Pile main = joueurActuel.getMain();
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on pioche 2 cartes à la source
        Pile source = Partie.getInstance().getPlateau().getLaSource();
        for (int i = 0; i < 2; i++) {
            try{
                // on récupère la carte
                Carte carte = source.piocherCarte();
                // on l'ajoute à la main
                main.ajouterCarte(carte);
            }catch(Exception e){
                Utils.println("Erreur : la source est vide", "rouge");
            }
        }
        // on affiche un message
        Utils.println("Vous avez pioché 2 cartes à la source", "vert");

        // placer 2 cartes de la main sur la vie future
        //on cree une pile cartessanssemis pour ne pas pouvoir placer la carte semis sur la vie future
        Pile cartesSansSemis = new Pile();
        for (int i = 0; i < main.getNbCartes(); i++) {
            if (main.getCarte(i) != this) {
                cartesSansSemis.ajouterCarte(main.getCarte(i));
            }
        }

        for(int i = 0; i < 2; i++) {
            // on affiche les cartes sans la carte semis
            Pile.cartesToString(cartesSansSemis, true, true);
            if (cartesSansSemis.getNbCartes() > 0) {
                // on demande au joueur quelle carte il veut placer sur sa vie future
                Utils.println("Quelle carte voulez-vous placer sur votre vie future ? (1-" + cartesSansSemis.getNbCartes() + ")", "vert");
                // on récupère le choix du joueur en repetant la question tant qu'il ne choisit pas une carte valide avec les exceptions
                int choixCarte = 0;
                boolean carteValide = false;
                while (!carteValide) {
                    try {
                        choixCarte = Utils.inputInt("Choix : ", "jaune", true, cartesSansSemis.getNbCartes());
                        // on récupère la carte choisie
                        Carte carteChoisie = cartesSansSemis.getCarte(choixCarte - 1);
                        // on l'ajoute à la vie future
                        joueurActuel.getVieFuture().ajouterCarte(carteChoisie);
                        carteValide = true;
                        // on la retire de la main et de la pile cartessanssemis
                        main.supprimerCarte(main.getCarteIndex(carteChoisie));
                        cartesSansSemis.supprimerCarte(cartesSansSemis.getCarteIndex(carteChoisie));
                        // on affiche un message
                        Utils.println("Vous avez placé la carte " + carteChoisie.getNom() + " sur votre vie future", "vert");
                    } catch (Exception e) {
                        Utils.println("Erreur : choix invalide", "rouge");
                    }
                }
            } else {
                Utils.println("Vous n'avez plus de cartes dans votre main", "vert");
            }
        }
    }
}
