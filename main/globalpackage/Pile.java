public class Pile {

   

    protected Carte[] cartes;

    public Pile() {
        cartes = new Carte[0];
    }

    public void ajouterCarte(Carte carte) {
        Carte[] newCartes = new Carte[cartes.length + 1];
        for (int i = 0; i < cartes.length; i++) {
            newCartes[i] = cartes[i];
        }
        newCartes[cartes.length] = carte;
        cartes = newCartes;
    }


    public Carte piocherCarte() {
        //on pioche la dernière carte du paquet
        Carte cartePiochee = cartes[cartes.length - 1];
        //on crée un nouveau paquet de cartes avec une carte en moins
        Carte[] newCartes = new Carte[cartes.length - 1];
        //on copie toutes les cartes sauf la dernière dans le nouveau paquet
        for (int i = 0; i < cartes.length - 1; i++) {
            newCartes[i] = cartes[i];
        }
        //on remplace le paquet de cartes par le nouveau
        cartes = newCartes;
        //on retourne la carte piochée
        return cartePiochee;
    }

    public void melangerCartes() {
        for (int i = 0; i < cartes.length; i++) {
            int random = (int) (Math.random() * cartes.length);
            Carte temp = cartes[i];
            cartes[i] = cartes[random];
            cartes[random] = temp;
        }
    }




    
}
