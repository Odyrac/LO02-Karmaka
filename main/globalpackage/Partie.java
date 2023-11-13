public class Partie {
    private Joueur joueurActuel;
    private Plateau plateau;


    public void debutPartie(Partie partie) {
    
        if (partie == null) {
            partie = new Partie();


            Plateau plateau = new Plateau();


            //***A FAIRE*** ajouter une condition pour savoir si on joue contre un robot avec le menu
            Joueur joueur1 = new JoueurReel();
            Joueur joueur2 = new JoueurReel();
            

            Joueur[] joueurs = new Joueur[2];
            joueurs[0] = joueur1;
            joueurs[1] = joueur2;
            plateau.setJoueurs(joueurs);

            partie.setPlateau(plateau);

            //on set le joueur actuel au hasard entre les deux joueurs
            partie.setJoueurActuel(joueurs[(int) (Math.random() * 2)]);

           
            //création de LaSource
            Pile source = new Pile();
            //les cartes sont ajoutées manuellement
            source.ajouterCarte(new Transmigration());
            source.ajouterCarte(new Transmigration());
            source.ajouterCarte(new Transmigration());
            source.ajouterCarte(new Destinee());
            source.ajouterCarte(new Destinee());
            source.ajouterCarte(new Destinee());
            source.ajouterCarte(new RevesBrises());
            source.ajouterCarte(new RevesBrises());
            source.ajouterCarte(new RevesBrises());
            source.ajouterCarte(new CoupDOeil());
            source.ajouterCarte(new CoupDOeil());
            source.ajouterCarte(new CoupDOeil());


            source.melangerCartes();
            plateau.setLaSource(source);



        }

    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public void setJoueurActuel(Joueur joueur) {
        this.joueurActuel = joueur;
    }

}
