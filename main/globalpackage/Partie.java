import java.io.Serializable;
import java.util.ArrayList;
import java.io.IOException;


/**
 * La classe Partie gère le déroulement d'une partie du jeu.
 * Elle implémente le modèle Singleton.
 */
public class Partie implements Serializable {
    private Joueur joueurActuel;
    private Plateau plateau;
    private int timestamp;

    // Instance unique de la classe Partie
    private static Partie instance;

    /**
     * Méthode statique permettant d'obtenir l'instance unique de Partie.
     * Si l'instance n'existe pas, elle est créée.
     * 
     * @return L'instance unique de Partie.
     */
    public static Partie getInstance() {
        if (instance == null) {
            instance = new Partie();
        }
        return instance;
    }

    /**
     * Constructeur privé de la classe Partie.
     */
    private Partie() {
        // Constructeur privé pour assurer la singleton-ness
    }

    /**
     * Initialise une nouvelle partie.
     * Si la partie passée en paramètre est nulle, une nouvelle partie est créée.
     * Les joueurs et le plateau sont initialisés en fonction du choix du joueur.
     * 
     * @param partie La partie à initialiser.
     */
    public void debutPartie(Partie partie) {
        if (partie == null) {
            partie = Partie.getInstance();
            Plateau plateau = new Plateau();

            Utils.clearConsole();
            Utils.println("Souhaitez-vous qu'il y ait ?", "vert");
            Utils.println("1. 2 joueurs réels", "vert");
            Utils.println("2. 1 joueur réel et 1 virtuel", "vert");
            Utils.println("3. 2 joueurs virtuels", "vert");
            System.out.print("\n");
            int choix = Utils.inputInt("Choix : ", "jaune", false, 3);

            Joueur joueur1 = null;
            Joueur joueur2 = null;

            if (choix == 1) {
                String pseudo1;
                String pseudo2;
                System.out.print("\n");
                pseudo1 = Utils.inputString("Entrez le pseudo du joueur 1 : ", "jaune");
                pseudo2 = Utils.inputString("Entrez le pseudo du joueur 2 : ", "jaune");
                joueur1 = new JoueurReel(pseudo1);
                joueur2 = new JoueurReel(pseudo2);

            } else if (choix == 2) {
                String pseudo;
                System.out.print("\n");
                pseudo = Utils.inputString("Entrez votre pseudo : ", "jaune");
                joueur1 = new JoueurReel(pseudo);
                joueur2 = new JoueurVirtuel();
            } else if (choix == 3) {
                joueur1 = new JoueurVirtuel();
                joueur2 = new JoueurVirtuel();
            } else {
                // on affiche un message d'erreur
                Utils.clearConsole();
                Utils.println("Erreur : choix invalide", "rouge");
                Utils.waitEnter();
                Main.main(null);
            }

            Joueur[] joueurs = new Joueur[2];
            joueurs[0] = joueur1;
            joueurs[1] = joueur2;
            plateau.setJoueurs(joueurs);

            partie.setTimestamp((int) (System.currentTimeMillis() / 1000L));

            partie.setPlateau(plateau);

            // on set le joueur actuel au hasard entre les deux joueurs
            partie.setJoueurActuel(joueurs[(int) (Math.random() * 2)]);

            // création de LaSource
            Pile source = new Pile();


            // Ajout des cartes pour Bassesse
            for (int i = 0; i < Bassesse.NB_CARTES; i++) {
                source.ajouterCarte(new Bassesse());
            }

            // Ajout des cartes pour CoupDOeil
            for (int i = 0; i < CoupDOeil.NB_CARTES; i++) {
                source.ajouterCarte(new CoupDOeil());
            }

            // Ajout des cartes pour Crise
            for (int i = 0; i < Crise.NB_CARTES; i++) {
                source.ajouterCarte(new Crise());
            }

            // Ajout des cartes pour Deni
            for (int i = 0; i < Deni.NB_CARTES; i++) {
                source.ajouterCarte(new Deni());
            }

            // Ajout des cartes pour DernierSouffle
            for (int i = 0; i < DernierSouffle.NB_CARTES; i++) {
                source.ajouterCarte(new DernierSouffle());
            }

            // Ajout des cartes pour Duperie
            for (int i = 0; i < Duperie.NB_CARTES; i++) {
                source.ajouterCarte(new Duperie());
            }

            // Ajout des cartes pour Fournaise
            for (int i = 0; i < Fournaise.NB_CARTES; i++) {
                source.ajouterCarte(new Fournaise());
            }

            // Ajout des cartes pour Incarnation
            for (int i = 0; i < Incarnation.NB_CARTES; i++) {
                source.ajouterCarte(new Incarnation());
            }

            // Ajout des cartes pour Jubile
            for (int i = 0; i < Jubile.NB_CARTES; i++) {
                source.ajouterCarte(new Jubile());
            }

            // Ajout des cartes pour Lendemain
            for (int i = 0; i < Lendemain.NB_CARTES; i++) {
                source.ajouterCarte(new Lendemain());
            }

            // Ajout des cartes pour Longevite
            for (int i = 0; i < Longevite.NB_CARTES; i++) {
                source.ajouterCarte(new Longevite());
            }

            // Ajout des cartes pour Mimetisme
            for (int i = 0; i < Mimetisme.NB_CARTES; i++) {
                source.ajouterCarte(new Mimetisme());
            }

            // Ajout des cartes pour Panique
            for (int i = 0; i < Panique.NB_CARTES; i++) {
                source.ajouterCarte(new Panique());
            }

            // Ajout des cartes pour Recyclage
            for (int i = 0; i < Recyclage.NB_CARTES; i++) {
                source.ajouterCarte(new Recyclage());
            }

            // Ajout des cartes pour RevesBrises
            for (int i = 0; i < RevesBrises.NB_CARTES; i++) {
                source.ajouterCarte(new RevesBrises());
            }

            // Ajout des cartes pour Roulette
            for (int i = 0; i < Roulette.NB_CARTES; i++) {
                source.ajouterCarte(new Roulette());
            }

            // Ajout des cartes pour Sauvetage
            for (int i = 0; i < Sauvetage.NB_CARTES; i++) {
                source.ajouterCarte(new Sauvetage());
            }

            // Ajout des cartes pour Semis
            for (int i = 0; i < Semis.NB_CARTES; i++) {
                source.ajouterCarte(new Semis());
            }

            // Ajout des cartes pour Transmigration
            for (int i = 0; i < Transmigration.NB_CARTES; i++) {
                source.ajouterCarte(new Transmigration());
            }

            // Ajout des cartes pour Vengeance
            for (int i = 0; i < Vengeance.NB_CARTES; i++) {
                source.ajouterCarte(new Vengeance());
            }

            // Ajout des cartes pour Vol
            for (int i = 0; i < Vol.NB_CARTES; i++) {
                source.ajouterCarte(new Vol());
            }

            // Ajout des cartes pour Voyage
            for (int i = 0; i < Voyage.NB_CARTES; i++) {
                source.ajouterCarte(new Voyage());
            }

            // Ajout des cartes pour Destinee
            for (int i = 0; i < Destinee.NB_CARTES; i++) {
                source.ajouterCarte(new Destinee());
            }


            source.melangerCartes();
            plateau.setLaSource(source);

            // on distribue 4 cartes de LaSource à chaque joueur dans leur main
            for (int i = 0; i < 4; i++) {
                for (Joueur joueur : joueurs) {
                    joueur.getMain().ajouterCarte(source.piocherCarte());
                }
            }

            // on distribue 2 cartes de LaSource à chaque joueur dans leur pile
            for (int i = 0; i < 2; i++) {
                for (Joueur joueur : joueurs) {
                    joueur.getPile().ajouterCarte(source.piocherCarte());
                }
            }

            partie.getJoueurActuel().debutTour(partie);
        } else {
            partie.getJoueurActuel().debutTour(partie);
        }
    }

    /**
     * Affiche l'écran de fin de partie.
     * Indique le gagnant de la partie.
     */
    public void finPartie() {
        Utils.clearConsole();
        Utils.println("Vous avez atteint la transcendance !", "orange");
        Utils.printlnImportant("Fin de la partie", "orange");
        Utils.println("Le gagnant est " + this.getJoueurActuel().getPseudo(), "orange");
        Utils.waitEnter();
        Main.main(null);
    }

    /**
     * Définit le plateau de la partie.
     * 
     * @param plateau Le plateau de jeu.
     */
    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    /**
     * Obtient le plateau de la partie.
     * 
     * @return Le plateau de jeu.
     */
    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * Définit le joueur actuel.
     * 
     * @param joueur Le joueur actuel.
     */
    public void setJoueurActuel(Joueur joueur) {
        this.joueurActuel = joueur;
    }

    /**
     * Obtient le joueur actuel.
     * 
     * @return Le joueur actuel.
     */
    public Joueur getJoueurActuel() {
        return joueurActuel;
    }

    public void sauvegarderPartie() {

        ArrayList<Partie> parties = Utils.chargerParties();

        boolean partieExiste = false;
        for (int i = 0; i < parties.size(); i++) {
            if (parties.get(i).getTimestamp() == this.getTimestamp()) {
                parties.set(i, this);
                partieExiste = true;
            }
        }

        if (!partieExiste) {
            Utils.printlnImportant("ATTENTION CREATION DUNE SAUV", "rouge");
            parties.add(this);
        }

        try {
            Utils.sauvegarderParties(parties, "parties.ser");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la sauvegarde de la partie : " + e.getMessage());
        }
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getTimestamp() {
        return timestamp;
    }

    

    /**
     * Obtient le joueur adverse d'un joueur donné.
     * 
     * @param joueur Le joueur dont on veut l'adversaire.
     * @return Le joueur adverse.
     */
    public Joueur getJoueurAdverse(Joueur joueur) {
        Joueur[] joueurs = plateau.getJoueurs();
        if (joueur == joueurs[0]) {
            return joueurs[1];
        } else {
            return joueurs[0];
        }
    }


    public String toString() {
        String str = "";
        str += "Joueur actuel : " + this.getJoueurActuel().getPseudo() + "\n";
        str += "Joueur adverse : " + this.getJoueurAdverse(this.getJoueurActuel()).getPseudo() + "\n";
        str += "Date : " + Utils.timestampToDate(this.getTimestamp()) + "\n";
        return str;
    }
}
