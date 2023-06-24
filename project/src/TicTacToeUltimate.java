import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class TicTacToeUltimate extends gameWindow implements ActionListener {
    private int currentPlayer;
    public TicTacToeUltimate() {
        currentPlayer = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        smallBoards[i][j][k][l].addActionListener(this);
                    }
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (isBoardEnabled && button.getBackground() == Color.WHITE) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            if (button == smallBoards[i][j][k][l]) {
                                if (smallBoards[i][j][k][l].getText().equals("")) {
                                    smallBoards[i][j][k][l].setText(getPlayerSymbol());
                                    smallBoards[i][j][k][l].setBackground(Color.LIGHT_GRAY);
                                    checkWin(i, j, k, l);
                                    if (mainBoard[k][l].getBackground() == Color.WHITE) {
                                        mainBoard[k][l].setBackground(Color.YELLOW);
                                    }
                                    currentPlayer = 3 - currentPlayer; // Switch currentPlayer between 1 and 2
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private String getPlayerSymbol() {
        return currentPlayer == 1 ? "X" : "O";
    }


    private void checkWin(int mainRow, int mainCol, int subRow, int subCol) {
        // Wiersze
        for (int row = 0; row < 3; row++) {
            if (smallBoards[mainRow][mainCol][row][subCol].getText().equals("")) {
                break;
            }
            if (smallBoards[mainRow][mainCol][row][subCol].getText().equals(smallBoards[mainRow][mainCol][0][subCol].getText()) && row == 2) {
                mainBoard[mainRow][mainCol].setText(getPlayerSymbol());
                mainBoard[mainRow][mainCol].setBackground(Color.GREEN);
                checkBoardWin();
                return;
            }
        }

        // Kolumny
        for (int col = 0; col < 3; col++) {
            if (smallBoards[mainRow][mainCol][subRow][col].getText().equals("")) {
                break;
            }
            if (smallBoards[mainRow][mainCol][subRow][col].getText().equals(smallBoards[mainRow][mainCol][subRow][0].getText()) && col == 2) {
                mainBoard[mainRow][mainCol].setText(getPlayerSymbol());
                mainBoard[mainRow][mainCol].setBackground(Color.GREEN);
                checkBoardWin();
                return;
            }
        }

        // Diagonal "/"
        if (!smallBoards[mainRow][mainCol][0][0].getText().equals("") && smallBoards[mainRow][mainCol][0][0].getText().equals(smallBoards[mainRow][mainCol][1][1].getText())
                && smallBoards[mainRow][mainCol][0][0].getText().equals(smallBoards[mainRow][mainCol][2][2].getText())) {
            mainBoard[mainRow][mainCol].setText(getPlayerSymbol());
            mainBoard[mainRow][mainCol].setBackground(Color.GREEN);
            checkBoardWin();
            return;
        }

        // Diagonal "\"
        if (!smallBoards[mainRow][mainCol][0][2].getText().equals("") && smallBoards[mainRow][mainCol][0][2].getText().equals(smallBoards[mainRow][mainCol][1][1].getText())
                && smallBoards[mainRow][mainCol][0][2].getText().equals(smallBoards[mainRow][mainCol][2][0].getText())) {
            mainBoard[mainRow][mainCol].setText(getPlayerSymbol());
            mainBoard[mainRow][mainCol].setBackground(Color.GREEN);
            checkBoardWin();
        }
    }

    private void checkBoardWin() {
        // Wiersze
        for (int i = 0; i < 3; i++) {
            if (mainBoard[i][0].getText().equals("")) {
                continue;
            }
            if (mainBoard[i][0].getText().equals(mainBoard[i][1].getText()) &&
                    mainBoard[i][0].getText().equals(mainBoard[i][2].getText())) {
                highlightWinningCells(i, 0, i, 1, i, 2);
                winnerDialogue(mainBoard[i][0].getText());
                return;
            }
        }

        // Kolumny
        for (int j = 0; j < 3; j++) {
            if (mainBoard[0][j].getText().equals("")) {
                continue;
            }
            if (mainBoard[0][j].getText().equals(mainBoard[1][j].getText()) &&
                    mainBoard[0][j].getText().equals(mainBoard[2][j].getText())) {
                highlightWinningCells(0, j, 1, j, 2, j);
                winnerDialogue(mainBoard[0][j].getText());
                return;
            }
        }

        // Diagonal "/"
        if (!mainBoard[0][0].getText().equals("") && mainBoard[0][0].getText().equals(mainBoard[1][1].getText())
                && mainBoard[0][0].getText().equals(mainBoard[2][2].getText())) {
            highlightWinningCells(0, 0, 1, 1, 2, 2);
            winnerDialogue(mainBoard[0][0].getText());
            return;
        }

        // Diagonal "\"
        if (!mainBoard[0][2].getText().equals("") && mainBoard[0][2].getText().equals(mainBoard[1][1].getText())
                && mainBoard[0][2].getText().equals(mainBoard[2][0].getText())) {
            highlightWinningCells(0, 2, 1, 1, 2, 0);
            winnerDialogue(mainBoard[0][2].getText());
        }

        boolean isDraw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mainBoard[i][j].getIcon() == null) {
                    isDraw = false;
                    break;
                }
            }
        }

        if (isDraw) {
            drawDialogue();
        }
    }

    private void highlightWinningCells(int i1, int j1, int i2, int j2, int i3, int j3) {
        mainBoard[i1][j1].setBackground(Color.GREEN);
        mainBoard[i2][j2].setBackground(Color.GREEN);
        mainBoard[i3][j3].setBackground(Color.GREEN);
        isBoardEnabled = false;
    }

    private void winnerDialogue(String winner) {
        int option = JOptionPane.showOptionDialog(this, "Gracz " + winner + " wygrywa! Czy chcesz zagrać jeszcze raz?", "Koniec gry", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void drawDialogue() {
        int option = JOptionPane.showOptionDialog(this, "Remis! Czy chcesz zagrać jeszcze raz?", "Koniec gry", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mainBoard[i][j].setText("");
                mainBoard[i][j].setBackground(Color.WHITE);
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        smallBoards[i][j][k][l].setText("");
                        smallBoards[i][j][k][l].setBackground(Color.WHITE);
                    }
                }
            }
        }
        activePlayer = 1;
        isBoardEnabled = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeUltimate::new);
    }
}
