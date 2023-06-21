import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class gameWindow extends JFrame implements ActionListener {

    public final JButton[][] mainBoard;
    public final JButton[][][][] smallBoards;
    public int activePlayer;
    public boolean isBoardEnabled;

    public gameWindow() {
        setTitle("Ultimate Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(3, 3));

        mainBoard = new JButton[3][3];
        smallBoards = new JButton[3][3][3][3];
        activePlayer = 1;
        isBoardEnabled = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mainBoard[i][j] = new JButton();
                mainBoard[i][j].setBackground(Color.WHITE);
                mainBoard[i][j].addActionListener(this);
                add(mainBoard[i][j]);

                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        smallBoards[i][j][k][l] = new JButton();
                        smallBoards[i][j][k][l].setBackground(Color.WHITE);
                        smallBoards[i][j][k][l].addActionListener(this);
                        mainBoard[i][j].setLayout(new GridLayout(3, 3));
                        mainBoard[i][j].add(smallBoards[i][j][k][l]);
                    }
                }
            }
        }
        setVisible(true);
    }
}
