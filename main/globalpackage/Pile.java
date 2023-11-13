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

    public Carte piocherCarte(Carte carte) {
        Carte[] newCartes = new Carte[cartes.length - 1];
        Carte cartePiochee = null;
        for (int i = 0; i < cartes.length; i++) {
            if (cartes[i].equals(carte)) {
                cartePiochee = cartes[i];
            } else {
                newCartes[i] = cartes[i];
            }
        }
        cartes = newCartes;
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
