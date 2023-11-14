public class Plateau {
    private Pile laSource;
    private Pile laFosse;

    private Joueur[] joueurs;

    public Plateau() {
        joueurs = new Joueur[2];
        laSource = new Pile();
        laFosse = new Pile();
    }

    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    public void setLaSource(Pile laSource) {
        this.laSource = laSource;
    }

    public void setLaFosse(Pile laFosse) {
        this.laFosse = laFosse;
    }

    public Pile getLaSource() {
        return laSource;
    }

    public Pile getLaFosse() {
        return laFosse;
    }

    
    
}
