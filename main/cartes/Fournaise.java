public class Fournaise extends Carte{

        public static final int NB_CARTES = 3;

        public Fournaise() {
            this.couleur = EnumCouleur.rouge;
            this.points = 2;
            this.nom = "Fournaise";
            this.description = "Défaussez les 2 premières cartes de la Vie Future d'un rival.";
        }

    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        Pile main = joueurActuel.getMain();
        // on récupère le joueur cible (on a toujours 2 joueurs donc c'est forcément l'autre)
        Joueur joueurAdverse = Partie.getInstance().getJoueurAdverse(joueurActuel);
        // on récupère la vie future du joueur adverse
        Pile vieFutureAdverse = joueurAdverse.getVieFuture();
        // on ajoute la carte fournaise aux cartes jouées pour pouvoir
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on défausse les 2 premières cartes
        for (int i = 0; i < 2; i++) {
            try {
                // on récupère la carte
                Carte carte = vieFutureAdverse.getCarte(0);
                // on la défausse
                vieFutureAdverse.defausserCarte(carte);
            } catch (Exception e) {
                Utils.println("Erreur : la vie future de " + joueurAdverse.getPseudo() + " est vide", "rouge");
            }
        }
        // on affiche un message
        Utils.println("Vous avez défaussé les 2 premières cartes de la vie future de " + joueurAdverse.getPseudo(), "vert");
    }
}
