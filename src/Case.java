public class Case {

    private char symbole;

    public Case() {
        this.symbole = ' ';
    }

    public boolean estVide() {
        return symbole == ' ';
    }

    public char getSymbole() {
        return symbole;
    }

    public void setSymbole(char symbole) {
        this.symbole = symbole;
    }

}
