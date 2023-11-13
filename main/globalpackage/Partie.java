public class Partie {
    private Joueur joueurActuel;
    private Plateau plateau;

  

    public void debutPartie(Partie partie) {

        if (partie == null) {
            partie = new Partie();

            Plateau plateau = new Plateau();

            Utils.clearConsole();
            Utils.println("Souhaitez-vous qu'il y ait ?", "vert");
            Utils.println("1. 2 joueurs réels", "vert");
            Utils.println("2. 1 joueur réel et 1 virtuel", "vert");
            Utils.println("3. 2 joueurs virtuels", "vert");
            System.out.print("\n");
            int choix = Utils.inputInt("Choix : ", "jaune");

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

            partie.setPlateau(plateau);

            // on set le joueur actuel au hasard entre les deux joueurs
            partie.setJoueurActuel(joueurs[(int) (Math.random() * 2)]);

            // création de LaSource
            Pile source = new Pile();

            for (int i = 0; i < Transmigration.NB_CARTES; i++) {
                source.ajouterCarte(new Transmigration());
            }

            for (int i = 0; i < Destinee.NB_CARTES; i++) {
                source.ajouterCarte(new Destinee());
            }

            for (int i = 0; i < RevesBrises.NB_CARTES; i++) {
                source.ajouterCarte(new RevesBrises());
            }

            for (int i = 0; i < CoupDOeil.NB_CARTES; i++) {
                source.ajouterCarte(new CoupDOeil());
            }

            source.melangerCartes();
            plateau.setLaSource(source);


            //on distribue 4 cartes de LaSource à chaque joueur dans leur main
            for (int i = 0; i < 4; i++) {
                for (Joueur joueur : joueurs) {
                    joueur.getMain().ajouterCarte(source.piocherCarte());
                }
            }

            //on distribue 2 cartes de LaSource à chaque joueur dans leur pile
            for (int i = 0; i < 2; i++) {
                for (Joueur joueur : joueurs) {
                    joueur.getPile().ajouterCarte(source.piocherCarte());
                }
            }


            

        }

    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public void setJoueurActuel(Joueur joueur) {
        this.joueurActuel = joueur;
    }

}
