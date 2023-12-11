public class RevesBrises extends Carte {

    public static final int NB_CARTES = 3;

    public RevesBrises() {
        this.couleur = EnumCouleur.bleu;
        this.points = 1;
        this.nom = "Rêves Brisés";
        this.description = "Placez la première carte de la Vie Future d'un rival sur la vôtre.";
    }

    public void utiliserPouvoir() {
        // on récupère le joueur actuel
        Joueur joueurActuel = Partie.getInstance().getJoueurActuel();
        // on récupère sa main pour ajouter la carte longevite aux cartes jouees pour pouvoir
        Pile main = joueurActuel.getMain();
        if (main.contientCarte(this)) {
            Partie.getInstance().getJoueurActuel().getCartesJoueesPourPouvoir().ajouterCarte(this);
        }
        // on récupère le joueur cible (on a toujours 2 joueurs donc c'est forcément l'autre)
        Joueur joueurAdverse = Partie.getInstance().getJoueurAdverse(joueurActuel);
        // on récupère la carte de la vie future
        Carte carte = joueurAdverse.getVieFuture().getCarte(0);
        // on l'ajoute à sa vie future
        Pile vieFuture = joueurActuel.getVieFuture();
        vieFuture.ajouterCarte(carte);
        // on la retire de la vie future
        int index = joueurAdverse.getVieFuture().getCarteIndex(carte);
        joueurAdverse.getVieFuture().supprimerCarte(index);
        // on affiche un message
        Utils.println("Vous avez placé la première carte de la vie future de " + joueurAdverse.getPseudo() + " sur votre vie future", "vert");
    }
}
