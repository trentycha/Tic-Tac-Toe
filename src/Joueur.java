public class Joueur {

    private char id;

    public Joueur(char id) {
        this.id = id;
    }

    public char getId() {
        return this.id;
    }

    public void setId(char id) {
        this.id = id;
    }

    public boolean jouerCoup(Plateau plateau, int ligne, int colonne) {
        return plateau.placerSymbole(ligne, colonne, this.id);
    }

}
