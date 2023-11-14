public class Joueur {
    protected Pile main;
    protected Pile pile;
    protected Pile vieFuture;
    protected OeuvresJoueur oeuvres;
    protected int anneauxKarmiques;
    protected EnumEchelleKarmique positionEchelleKarmique;

    protected String pseudo;

    public void initialiserJoueur(String pseudo) {
        anneauxKarmiques = 0;
        positionEchelleKarmique = EnumEchelleKarmique.bousier;
        main = new Pile();
        pile = new Pile();
        vieFuture = new Pile();
        oeuvres = new OeuvresJoueur();
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
        Pile.cartesToString(this.getMain(), true, false);

        System.out.print("\n");

        if (this.getMain().getNbCartes() == 0 && this.getPile().getNbCartes() == 0) {
            Utils.println("Vous entrez en Réincarnation", "rouge");
            Utils.waitEnter();
            reincarnation(partie);
            return;
        }

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
                this.getMain().ajouterCarte(this.getPile().piocherCarte());
                Utils.println("Vous piochez une carte de votre pile", "gris");
                Utils.waitEnter();
                partie.setJoueurActuel(joueurAdverse);
                joueurAdverse.debutTour(partie);
            }
        } else if (choix == 2) {
            Utils.clearConsole();
            Utils.println("Quelle carte souhaitez-vous jouer pour ses points :", "orange");
            Pile.cartesToString(this.getMain(), true, true);
            System.out.print("\n");
            int choixCarte = Utils.inputInt("Choix : ", "jaune");
            if (choixCarte > this.getMain().getNbCartes() || choixCarte <= 0) {
                Utils.println("Erreur : choix invalide", "rouge");
                Utils.waitEnter();
                this.debutTour(partie);
            } else {
                Carte carteChoisie = this.getMain().getCarte(choixCarte - 1);
                this.getOeuvres().ajouterCarte(carteChoisie);
                this.getMain().supprimerCarte(choixCarte - 1);
                Utils.println("Vous jouez " + carteChoisie.getNom() + " pour ses points", "gris");
                Utils.waitEnter();
                partie.setJoueurActuel(joueurAdverse);
                joueurAdverse.debutTour(partie);
            }
        } else if (choix == 3) {
            // PARTIE SUR LES POUVOIRS A DEV
        } else if (choix == 4) {
            Utils.clearConsole();
            Utils.println("Quelle carte souhaitez-vous jouer pour votre futur :", "orange");
            Pile.cartesToString(this.getMain(), true, true);
            System.out.print("\n");
            int choixCarte = Utils.inputInt("Choix : ", "jaune");
            if (choixCarte > this.getMain().getNbCartes() || choixCarte <= 0) {
                Utils.println("Erreur : choix invalide", "rouge");
                Utils.waitEnter();
                this.debutTour(partie);
            } else {
                Carte carteChoisie = this.getMain().getCarte(choixCarte - 1);
                this.getVieFuture().ajouterCarte(carteChoisie);
                this.getMain().supprimerCarte(choixCarte - 1);
                Utils.println("Vous jouez " + carteChoisie.getNom() + " pour votre futur", "gris");
                Utils.waitEnter();
                partie.setJoueurActuel(joueurAdverse);
                joueurAdverse.debutTour(partie);
            }
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

    public void reincarnation(Partie partie) {
        //EN COURS DE DEV
        int points = this.getOeuvres().compterPoints();

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

    public OeuvresJoueur getOeuvres() {
        return oeuvres;
    }

    public int getAnneauxKarmiques() {
        return anneauxKarmiques;
    }

    public EnumEchelleKarmique getPositionEchelleKarmique() {
        return positionEchelleKarmique;
    }

}
