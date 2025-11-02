public class TicTacToeGUI extends JFrame {

    private JButton[][] boutons;
    private Jeu jeu;

    public TicTacToeGUI() {
        jeu = new Jeu();
        boutons = new JButton[3][3];

        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        add(panel);

}
