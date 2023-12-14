import java.util.ArrayList;

/**
 * La classe Main est la classe principale du jeu.
 */
public class Main {

    /**
     * Méthode principale qui lance le jeu.
     * 
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {

        // Efface la console
        Utils.clearConsole();

        // Affiche le titre du jeu en art ASCII
        Utils.println("Ladies and Gentlemen, bienvenue sur :\n", "cyan");
        Utils.println("██╗  ██╗ █████╗ ██████╗ ███╗   ███╗ █████╗ ██╗  ██╗ █████╗ ", "mosaique");
        Utils.println("██║ ██╔╝██╔══██╗██╔══██╗████╗ ████║██╔══██╗██║ ██╔╝██╔══██╗", "mosaique");
        Utils.println("█████╔╝ ███████║██████╔╝██╔████╔██║███████║█████╔╝ ███████║", "mosaique");
        Utils.println("██╔═██╗ ██╔══██║██╔══██╗██║╚██╔╝██║██╔══██║██╔═██╗ ██╔══██║", "mosaique");
        Utils.println("██║  ██╗██║  ██║██║  ██║██║ ╚═╝ ██║██║  ██║██║  ██╗██║  ██║", "mosaique");
        Utils.println("╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝", "mosaique");

        // Affiche le menu avec les options Nouvelle partie, Charger une partie,
        // Crédits, et Quitter
        System.out.print("\n");
        Utils.println("1. Nouvelle partie", "vert");
        Utils.println("2. Charger une partie", "vert");
        Utils.println("3. Crédits", "vert");
        Utils.println("4. Quitter", "vert");
        System.out.print("\n");

        // Demande au joueur de faire un choix
        int choix = Utils.inputInt("Choix : ", "jaune", false, 4);

        if (choix == 1) {
            // Lance une nouvelle partie
            Partie.getInstance().debutPartie(null);

        } else if (choix == 2) {
            // on charge une partie avec la serialisation

            ArrayList<Partie> parties = Utils.chargerParties();

            if (parties.size() == 0) {
                Utils.clearConsole();
                Utils.println("Aucune partie sauvegardée", "rouge");
                Utils.waitEnter();
                main(null);
            }

            Utils.clearConsole();

            Utils.println("Parties sauvegardées :", "orange");
            //System.out.println(parties.size());

            for (int i = 0; i < parties.size(); i++) {
                Partie partie = parties.get(i);

                // Vérifiez si la partie est null
                if (partie != null) {
                    // Imprime la représentation de la partie
                    //Utils.println(partie.toString(), "rouge");

                    Joueur joueurActuel = partie.getJoueurActuel();

                    // Vérifiez si le joueur actuel est null
                    if (joueurActuel != null) {
                        Joueur joueurAdverse = partie.getJoueurAdverse(joueurActuel);

                    //System.out.println(joueurActuel.toString());

                        // Vérifiez si le joueur adverse est null
                        if (joueurAdverse != null) {
                            String date = Utils.timestampToDate(partie.getTimestamp());
                            Utils.printlnImportant((i + 1) + ". " + joueurActuel.getPseudo() + " VS "
                                    + joueurAdverse.getPseudo() + " (" + date + ")", "vert");
                        } else {
                            System.err.println("Erreur : JoueurAdverse est null dans la partie " + (i + 1));
                        }
                    } else {
                        System.err.println("Erreur : JoueurActuel est null dans la partie " + (i + 1));
                    }
                } else {
                    // System.err.println("Erreur : La partie " + (i + 1) est null.");
                }
            }

            System.out.print("\n");
            int choixPartie = Utils.inputInt("Choix : ", "jaune", false, parties.size());

            if (choixPartie == 0 || choixPartie > parties.size()) {
                Utils.clearConsole();
                Utils.println("Erreur : choix invalide", "rouge");
                Utils.waitEnter();
                main(null);
                return;
            }

            Partie partie = parties.get(choixPartie - 1);
            Partie.getInstance().debutPartie(partie);

        } else if (choix == 3) {
            // Affiche les crédits
            credits();

        } else if (choix == 4) {
            // Quitte le jeu
            System.exit(0);

        } else {
            // Affiche un message d'erreur en cas de choix invalide
            Utils.clearConsole();
            Utils.println("Erreur : choix invalide", "rouge");
            Utils.waitEnter();
            main(null);
        }
    }

    /**
     * Affiche les crédits du projet.
     */
    public static void credits() {
        Utils.clearConsole();
        Utils.println("Ce projet est développé dans le cadre de l'UE LO02.", "vert");
        Utils.println("Il est réalisé par :", "vert");
        Utils.println(" - Thomas MATAMBA", "mosaique");
        Utils.println(" - Louis HALLEY", "mosaique");

        // Attend une action de l'utilisateur avant de revenir au menu principal
        Utils.waitEnter();
        main(null);
    }
}
