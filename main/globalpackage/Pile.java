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
        // on pioche la dernière carte du paquet
        Carte cartePiochee = cartes[cartes.length - 1];
        // on crée un nouveau paquet de cartes avec une carte en moins
        Carte[] newCartes = new Carte[cartes.length - 1];
        // on copie toutes les cartes sauf la dernière dans le nouveau paquet
        for (int i = 0; i < cartes.length - 1; i++) {
            newCartes[i] = cartes[i];
        }
        // on remplace le paquet de cartes par le nouveau
        cartes = newCartes;
        // on retourne la carte piochée
        return cartePiochee;
    }

    public void supprimerCarte(int index) {
        Carte[] newCartes = new Carte[cartes.length - 1];
        for (int i = 0; i < index; i++) {
            newCartes[i] = cartes[i];
        }
        for (int i = index; i < cartes.length - 1; i++) {
            newCartes[i] = cartes[i + 1];
        }
        cartes = newCartes;
    }

    public void melangerCartes() {
        for (int i = 0; i < cartes.length; i++) {
            int random = (int) (Math.random() * cartes.length);
            Carte temp = cartes[i];
            cartes[i] = cartes[random];
            cartes[random] = temp;
        }
    }

    public Carte getDerniereCarte() {
        if (cartes.length > 0) {
            return cartes[cartes.length - 1];
        } else {
            return null;
        }
    }

    public Carte getCarte(int index) {
        return cartes[index];
    }

    public static void cartesToString(Pile p, boolean avecDescription, boolean avecIndice) {
        for (int i = 0; i < p.getNbCartes(); i++) {
            if (avecIndice) {
                Utils.println(i + 1 + ") " + p.getCarte(i).getNom() + " (" + p.getCarte(i).getPoints() + "p)",
                        p.getCarte(i).getCouleur().toString());
            } else {
                Utils.println(p.getCarte(i).getNom() + " (" + p.getCarte(i).getPoints() + "p)",
                        p.getCarte(i).getCouleur().toString());
            }

            if (avecDescription) {
                Utils.println(p.getCarte(i).getDescription(), "gris");
            }
        }
    }

    public int getNbCartes() {
        return cartes.length;
    }

    public int getNbCartesBleues() {
        int nbCartesBleues = 0;
        for (Carte carte : cartes) {
            if (carte.getCouleur() == EnumCouleur.bleu) {
                nbCartesBleues++;
            }
        }
        return nbCartesBleues;
    }

    public int getNbCartesRouges() {
        int nbCartesRouges = 0;
        for (Carte carte : cartes) {
            if (carte.getCouleur() == EnumCouleur.rouge) {
                nbCartesRouges++;
            }
        }
        return nbCartesRouges;
    }

    public int getNbCartesVertes() {
        int nbCartesVertes = 0;
        for (Carte carte : cartes) {
            if (carte.getCouleur() == EnumCouleur.vert) {
                nbCartesVertes++;
            }
        }
        return nbCartesVertes;
    }

    public int getNbCartesMosaiques() {
        int nbCartesMosaiques = 0;
        for (Carte carte : cartes) {
            if (carte.getCouleur() == EnumCouleur.mosaique) {
                nbCartesMosaiques++;
            }
        }
        return nbCartesMosaiques;
    }

}
