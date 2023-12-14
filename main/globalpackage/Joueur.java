/**
 * La classe Joueur représente un joueur dans le jeu.
 */
public class Joueur {

    /** La pile de cartes en main du joueur. */
    protected Pile main;

    /** La pile de cartes du joueur. */
    protected Pile pile;

    /** La pile de cartes jouées pour le pouvoir. */
    protected Pile cartesJoueesPourPouvoir;

    /** La pile de cartes représentant la vie future du joueur. */
    protected Pile vieFuture;

    /** Les œuvres du joueur. */
    protected OeuvresJoueur oeuvres;

    /** Le nombre d'anneaux karmiques du joueur. */
    protected int anneauxKarmiques;

    /** La position actuelle sur l'échelle karmique du joueur. */
    protected EnumEchelleKarmique positionEchelleKarmique;

    /** Le pseudonyme du joueur. */
    protected String pseudo;

    /**
     * Initialise le joueur avec un pseudonyme donné et initialise ses piles de
     * cartes et œuvres.
     *
     * @param pseudo Le pseudonyme du joueur.
     */
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

    /**
     * Débute le tour du joueur, affichant des informations sur l'état du jeu et
     * proposant des actions au joueur.
     *
     * @param partie La partie en cours.
     */
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

        // on vide la pile de cartes jouées pour le pouvoir
        this.cartesJoueesPourPouvoir = new Pile();

        // On propose au joueur de recuperer les cartes jouées par l'adversaire pour le
        // pouvoir
        Pile cartesJoueesPourPouvoirAdverse = joueurAdverse.getCartesJoueesPourPouvoir();
        // on vérifie si c'est vide
        if (cartesJoueesPourPouvoirAdverse != null && cartesJoueesPourPouvoirAdverse.getNbCartes() > 0) {
            for (int i = 0; i < cartesJoueesPourPouvoirAdverse.getNbCartes(); i++) {
                Utils.println(
                        "Voulez-vous récupérer " + cartesJoueesPourPouvoirAdverse.getCarte(i).getNom() + " ? (o/n)",
                        "vert");
                String choixRecuperer = Utils.inputString("Choix : ", "jaune");
                // si o on ajoute la carte à la main du joueur adverse
                // si n on jette la carte dans la fosse
                boolean choixValide = false;
                while (!choixValide) {
                    if (choixRecuperer.equals("o")) {
                        this.getMain().ajouterCarte(cartesJoueesPourPouvoirAdverse.getCarte(i));
                        Utils.println("Vous récupérez " + cartesJoueesPourPouvoirAdverse.getCarte(i).getNom(), "gris");
                        choixValide = true;
                    } else if (choixRecuperer.equals("n")) {
                        partie.getPlateau().getLaFosse().ajouterCarte(cartesJoueesPourPouvoirAdverse.getCarte(i));
                        Utils.println(
                                "Vous jetez " + cartesJoueesPourPouvoirAdverse.getCarte(i).getNom() + " dans la fosse",
                                "gris");
                        choixValide = true;
                    } else {
                        Utils.println("Erreur : choix invalide", "rouge");
                        choixRecuperer = Utils.inputString("Choix : ", "jaune");
                    }
                }
            }
        }

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
            if (this.getMain().getNbCartes() == 0) {
                Utils.println("Votre main est vide", "rouge");
                Utils.waitEnter();
                this.debutTour(partie);
            } else {
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
            }
        } else if (choix == 3) {
            if (this.getMain().getNbCartes() == 0) {
                Utils.println("Votre main est vide", "rouge");
                Utils.waitEnter();
                this.debutTour(partie);
            } else {
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

                    for (int i = 0; i < this.getCartesJoueesPourPouvoir().getNbCartes(); i++) {
                        Utils.println("Vous avez joué " + this.getCartesJoueesPourPouvoir().getCarte(i).getNom()
                                + " pour son pouvoir", "gris");
                    }
                    for (int i = 0; i < this.getCartesJoueesPourPouvoir().getNbCartes(); i++) {
                        Carte carte = this.getCartesJoueesPourPouvoir().getCarte(i);
                        int index = this.getMain().getCarteIndex(carte);
                        try {
                            this.getMain().supprimerCarte(index);
                            Utils.println("Vous défaussez " + this.getCartesJoueesPourPouvoir().getCarte(i).getNom(),
                                    "gris");
                        } catch (Exception e) {
                            Utils.println("La carte " + carte.getNom() + " à déjà été sortie de votre main", "rouge");
                        }
                    }
                    Utils.waitEnter();
                    partie.setJoueurActuel(joueurAdverse);
                    joueurAdverse.debutTour(partie);
                }
            }

        } else if (choix == 4) {
            if (this.getMain().getNbCartes() == 0) {
                Utils.println("Votre main est vide", "rouge");
                Utils.waitEnter();
                this.debutTour(partie);
            } else {
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
            } else {
                reincarnationActions(prochainePosition, partie);
            }
        }

    }

    /**
     * Effectue les actions nécessaires pour la réincarnation du joueur en fonction
     * de sa position sur l'échelle karmique.
     *
     * @param prochainePosition La prochaine position sur l'échelle karmique.
     * @param partie            La partie en cours.
     */
    public void reincarnationActions(EnumEchelleKarmique prochainePosition, Partie partie) {
        // ... (le contenu de la méthode a été précédemment commenté)
    }

    /**
     * Convertit une position sur l'échelle karmique en points associés.
     *
     * @param echelle La position sur l'échelle karmique.
     * @return Le nombre de points associés à la position.
     */
    public int enumKarmiquetoPoints(EnumEchelleKarmique echelle) {
        int points;
        switch (echelle) {
            case bousier:
                points = 0;
                break;
            case serpent:
                points = 4;
                break;
            case loup:
                points = 5;
                break;
            case singe:
                points = 6;
                break;
            case transcendance:
                points = 7;
                break;
            default:
                points = 0;
                break;
        }
        return points;
    }

    /**
     * Retourne le pseudonyme du joueur.
     *
     * @return Le pseudonyme du joueur.
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Retourne la pile de cartes en main du joueur.
     *
     * @return La pile de cartes en main du joueur.
     */
    public Pile getMain() {
        return main;
    }

    /**
     * Retourne la pile de cartes du joueur.
     *
     * @return La pile de cartes du joueur.
     */
    public Pile getPile() {
        return pile;
    }

    /**
     * Retourne la pile de cartes jouées pour le pouvoir du joueur.
     *
     * @return La pile de cartes jouées pour le pouvoir du joueur.
     */
    public Pile getCartesJoueesPourPouvoir() {
        return cartesJoueesPourPouvoir;
    }

    /**
     * Retourne la pile de cartes représentant la vie future du joueur.
     *
     * @return La pile de cartes représentant la vie future du joueur.
     */
    public Pile getVieFuture() {
        return vieFuture;
    }

    /**
     * Retourne les œuvres du joueur.
     *
     * @return Les œuvres du joueur.
     */
    public OeuvresJoueur getOeuvres() {
        return oeuvres;
    }

    /**
     * Retourne le nombre d'anneaux karmiques du joueur.
     *
     * @return Le nombre d'anneaux karmiques du joueur.
     */
    public int getAnneauxKarmiques() {
        return anneauxKarmiques;
    }

    /**
     * Retourne la position actuelle sur l'échelle karmique du joueur.
     *
     * @return La position actuelle sur l'échelle karmique du joueur.
     */
    public EnumEchelleKarmique getPositionEchelleKarmique() {
        return positionEchelleKarmique;
    }

    /**
     * Définit la position sur l'échelle karmique du joueur.
     *
     * @param positionEchelleKarmique La nouvelle position sur l'échelle karmique.
     */
    public void setPositionEchelleKarmique(EnumEchelleKarmique positionEchelleKarmique) {
        this.positionEchelleKarmique = positionEchelleKarmique;
    }
}
