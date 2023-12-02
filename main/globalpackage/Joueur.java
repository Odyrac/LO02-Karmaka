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
        Utils.printLigne();
        Utils.println("Aversaire :", "orange");
        Utils.infosJoueur(joueurAdverse);

        Utils.infosPlateau(partie, this.pseudo);

        Utils.println("Vous :", "orange");
        Utils.infosJoueur(this);
        Utils.println("Votre main :", "orange");
        Pile.cartesToString(this.getMain(), true, false);

        System.out.print("\n");

        // on vérifie si les conditions pour la réincarnation sont réunies
        if (this.getMain().getNbCartes() == 0 && this.getPile().getNbCartes() == 0) {
            Utils.printlnImportant("Vous entrez en Réincarnation", "orange");
            Utils.waitEnter();
            this.reincarnation(partie);
            return;
        }

        if (this.getPile().getNbCartes() == 0) {
            Utils.println("1. Piocher une carte de votre pile [VIDE]", "gris");
        } else {
            Utils.println("1. Piocher une carte de votre pile", "vert");
        }

        if (this.getMain().getNbCartes() == 0) {
            Utils.println("2. Jouer une carte de ma main | points [VIDE]", "gris");
            Utils.println("3. Jouer une carte de ma main | pouvoir [VIDE]", "gris");
            Utils.println("4. Jouer une carte de ma main | futur [VIDE]", "gris");
        } else {
            Utils.println("2. Jouer une carte de ma main | points", "vert");
            Utils.println("3. Jouer une carte de ma main | pouvoir", "vert");
            Utils.println("4. Jouer une carte de ma main | futur", "vert");
        }

        Utils.println("5. Passer", "vert");
        System.out.print("\n");
        int choix = Utils.inputInt("Choix : ", "jaune", true, 5);

        if (choix == 1) {
            // on pioche une carte de la pile
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
            // on joue une carte de la main pour ses points
            Utils.clearConsole();
            Utils.println("Quelle carte souhaitez-vous jouer pour ses points :", "orange");
            Pile.cartesToString(this.getMain(), true, true);
            System.out.print("\n");
            int choixCarte = Utils.inputInt("Choix : ", "jaune", true, this.getMain().getNbCartes());
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
            // on joue une carte de la main pour son pouvoir
            Utils.clearConsole();
            Utils.println("Quelle carte souhaitez-vous jouer pour son pouvoir :", "orange");
            Pile.cartesToString(this.getMain(), true, true);
            System.out.print("\n");
            int choixCarte = Utils.inputInt("Choix : ", "jaune", true, this.getMain().getNbCartes());
            if (choixCarte > this.getMain().getNbCartes() || choixCarte <= 0) {
                Utils.println("Erreur : choix invalide", "rouge");
                Utils.waitEnter();
                this.debutTour(partie);
            } else {
                Carte carteChoisie = this.getMain().getCarte(choixCarte - 1);
                carteChoisie.utiliserPouvoir();
                this.getMain().supprimerCarte(choixCarte - 1);
                // On propose au joueur adverse de recuperer la carte jouee
                Utils.println("Le joueur adverse veut-il récuperer la carte jouée ? (o/n)", "vert");
                String choixRecuperer = Utils.inputString("Choix : ", "jaune");
                if (choixRecuperer.equals("o")) {
                    joueurAdverse.getMain().ajouterCarte(carteChoisie);
                    Utils.println("Le joueur adverse a récupéré la carte jouée", "gris");
                } else {
                    partie.getPlateau().getLaFosse().ajouterCarte(carteChoisie);
                    Utils.println("Le joueur adverse n'a pas récupéré la carte jouée", "gris");
                }
                // on supprime la carte de la main du joueur
                this.getMain().supprimerCarte(choixCarte - 1);
                Utils.println("Vous avez joué " + carteChoisie.getNom() + " pour son pouvoir", "gris");
                Utils.waitEnter();
                partie.setJoueurActuel(joueurAdverse);
                joueurAdverse.debutTour(partie);
            }

        } else if (choix == 4) {
            Utils.clearConsole();
            Utils.println("Quelle carte souhaitez-vous jouer pour votre futur :", "orange");
            Pile.cartesToString(this.getMain(), true, true);
            System.out.print("\n");
            int choixCarte = Utils.inputInt("Choix : ", "jaune", true, this.getMain().getNbCartes());
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

        Utils.clearConsole();
        int points = this.getOeuvres().compterPoints();
        Utils.println(
                "Vous avez actuellement " + points + " points et " + this.getAnneauxKarmiques() + " Anneaux Karmiques",
                "orange");

        // prochaine position echelle karmique, l'élement suivant de l'enum
        EnumEchelleKarmique prochainePosition = EnumEchelleKarmique.values()[this.getPositionEchelleKarmique().ordinal()
                + 1];
        Utils.println(" _ " + prochainePosition.toString() + " (" + enumKarmiquetoPoints(prochainePosition)
                + " points nécessaires)", "vert");
        Utils.println("/ \\", "vert");
        Utils.println("| |", "vert");
        Utils.println(this.getPositionEchelleKarmique().toString(), "vert");

        // ceci représente le cas où le joueur avance d'une Echelle Karmique sans avoir
        // besoin d'utiliser ses Anneaux
        if (points >= enumKarmiquetoPoints(prochainePosition)) {
            Utils.printlnImportant("Vous progressez sur l'Echelle Karmique !", "orange");
            Utils.waitEnter();
            if (prochainePosition == EnumEchelleKarmique.transcendance) {
                partie.finPartie();
                return;
            } else {
                reincarnationActions(prochainePosition, partie);
            }

        } else if (points + this.getAnneauxKarmiques() < enumKarmiquetoPoints(prochainePosition)) { // cas où le joueur
                                                                                                    // ne peut pas
                                                                                                    // avancer sur
                                                                                                    // l'Echelle
                                                                                                    // Karmique (pas
                                                                                                    // assez de points +
                                                                                                    // pas assez
                                                                                                    // d'Anneaux)
            Utils.printlnImportant("Vous ne progressez pas sur l'Echelle Karmique (pas assez de points et d'Anneaux)",
                    "orange");
            Utils.println("Vous recevez un Anneau en compensation", "vert");
            Utils.waitEnter();
            this.anneauxKarmiques++;
            reincarnationActions(this.getPositionEchelleKarmique(), partie);

        } else if (points + this.getAnneauxKarmiques() >= enumKarmiquetoPoints(prochainePosition)) { // le joueur a la
                                                                                                     // possibilité
                                                                                                     // d'utiliser ses
                                                                                                     // Anneaux
            int pointsManquants = enumKarmiquetoPoints(prochainePosition) - points;
            Utils.printlnImportant("Vous pouvez progresser sur l'Echelle Karmique", "orange");
            Utils.println("Vous utilisez " + pointsManquants + " Anneaux Karmiques", "vert");
            Utils.waitEnter();
            this.anneauxKarmiques -= pointsManquants;
            if (prochainePosition == EnumEchelleKarmique.transcendance) {
                partie.finPartie();
                return;
            } else {
                reincarnationActions(prochainePosition, partie);
            }
        }

    }

    public void reincarnationActions(EnumEchelleKarmique prochainePosition, Partie partie) {
        this.setPositionEchelleKarmique(prochainePosition);
        // on défausse les oeuvres dans la fosses
        for (int i = 0; i < this.getOeuvres().getNbCartes(); i++) {
            partie.getPlateau().getLaFosse().ajouterCarte(this.getOeuvres().piocherCarte());
        }

        // on prend toutes les cartes de notre vie future comme nouvelle main
        for (int i = 0; i < this.getVieFuture().getNbCartes(); i++) {
            this.getMain().ajouterCarte(this.getVieFuture().piocherCarte());
        }

        // on constitue la nouvelle pile
        if (this.getMain().getNbCartes() < 6) {
            while (this.getMain().getNbCartes() + this.getPile().getNbCartes() <= 6) {
                this.getPile().ajouterCarte(partie.getPlateau().getLaSource().piocherCarte());
            }
        }

        partie.setJoueurActuel(partie.getJoueurAdverse(this));
        partie.getJoueurActuel().debutTour(partie);
        return;
    };

    public int enumKarmiquetoPoints(EnumEchelleKarmique echelle) {
        switch (echelle) {
            case bousier:
                return 0;
            case serpent:
                return 4;
            case loup:
                return 5;
            case singe:
                return 6;
            case transcendance:
                return 7;
            default:
                return 0;
        }
    }

    public String getPseudo() {
        return pseudo;
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

    public void setPositionEchelleKarmique(EnumEchelleKarmique positionEchelleKarmique) {
        this.positionEchelleKarmique = positionEchelleKarmique;
    }

}
