public class Joueur {
    protected Pile main;
    protected Pile pile;
    protected Pile vieFuture;
    protected Pile oeuvres;
    protected int anneauxKarmiques;
    protected EnumEchelleKarmique positionEchelleKarmique;

    protected String pseudo;

    public void initialiserJoueur(String pseudo) {
        anneauxKarmiques = 0;
        positionEchelleKarmique = EnumEchelleKarmique.bousier;
        main = new Pile();
        pile = new Pile();
        vieFuture = new Pile();
        oeuvres = new Pile();
        if (pseudo == null) {
            this.pseudo = "BOT";
        } else {
            this.pseudo = pseudo;
        }
    }

    public void debutTour(Partie partie) {

        Utils.clearConsole();

        Joueur joueurAdverse = partie.getJoueurAdverse(this);
        Utils.println("Aversaire :", "orange");
        Utils.infosJoueur(joueurAdverse);

        Utils.infosPlateau(partie, this.pseudo);

        Utils.println("Vous :", "orange");
        Utils.infosJoueur(this);
        Utils.println("Votre main :", "orange");
        Pile.cartesToString(this.getMain());

        System.out.print("\n");

        if (this.getPile().getNbCartes() == 0) {
            Utils.println("1. Piocher une carte de votre pile [VIDE]", "gris");
        } else {
            Utils.println("1. Piocher une carte de votre pile", "vert");
        }

        Utils.println("2. Jouer une carte de ma main | points", "vert");
        Utils.println("3. Jouer une carte de ma main | pouvoir", "vert");
        Utils.println("4. Jouer une carte de ma main | futur", "vert");
        Utils.println("5. Passer", "vert");
        System.out.print("\n");
        int choix = Utils.inputInt("Choix : ", "jaune");

        if (choix == 1) {
            if (this.getPile().getNbCartes() == 0) {
                Utils.println("Vous ne pouvez pas piocher car votre pile est vide", "rouge");
                Utils.waitEnter();
                this.debutTour(partie);
            } else {
                //this.piocherCarte(partie);
            }
        } else if (choix == 2) {
            //this.jouerCarteMainPoints(partie);
        } else if (choix == 3) {
            //this.jouerCarteMainPouvoir(partie);
        } else if (choix == 4) {
            //this.jouerCarteMainFutur(partie);
        } else if (choix == 5) {
            Utils.println("Vous passez votre tour", "gris");
            Utils.waitEnter();
            partie.setJoueurActuel(joueurAdverse);
            joueurAdverse.debutTour(partie);
            
        } else {
            Utils.println("Erreur : choix invalide", "rouge");
            Utils.waitEnter();
            this.debutTour(partie);
        }


    }




    public Pile getMain() {
        return main;
    }

    public Pile getPile() {
        return pile;
    }

    public Pile getVieFuture() {
        return vieFuture;
    }



}
