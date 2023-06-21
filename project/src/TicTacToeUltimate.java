import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class TicTacToeUltimate extends gameWindow{

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
                                    setActivePlayer(k, l);
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
        return activePlayer == 1 ? "X" : "O";
    }

    private void setActivePlayer(int boardRow, int boardCol) {
        activePlayer = (boardRow * 3 + boardCol) + 1;
    }

    private void checkWin(int mainRow, int mainCol, int subRow, int subCol) {
        // Check rows
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

        // Check columns
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

        // Check diagonal
        if (!smallBoards[mainRow][mainCol][0][0].getText().equals("") && smallBoards[mainRow][mainCol][0][0].getText().equals(smallBoards[mainRow][mainCol][1][1].getText())
                && smallBoards[mainRow][mainCol][0][0].getText().equals(smallBoards[mainRow][mainCol][2][2].getText())) {
            mainBoard[mainRow][mainCol].setText(getPlayerSymbol());
            mainBoard[mainRow][mainCol].setBackground(Color.GREEN);
            checkBoardWin();
            return;
        }

        // Check reverse diagonal
        if (!smallBoards[mainRow][mainCol][0][2].getText().equals("") && smallBoards[mainRow][mainCol][0][2].getText().equals(smallBoards[mainRow][mainCol][1][1].getText())
                && smallBoards[mainRow][mainCol][0][2].getText().equals(smallBoards[mainRow][mainCol][2][0].getText())) {
            mainBoard[mainRow][mainCol].setText(getPlayerSymbol());
            mainBoard[mainRow][mainCol].setBackground(Color.GREEN);
            checkBoardWin();
        }
    }

    private void checkBoardWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (mainBoard[i][0].getText().equals("")) {
                continue;
            }
            if (mainBoard[i][0].getText().equals(mainBoard[i][1].getText()) &&
                    mainBoard[i][0].getText().equals(mainBoard[i][2].getText())) {
                highlightWinningCells(i, 0, i, 1, i, 2);
                showWinnerDialog(mainBoard[i][0].getText());
                return;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (mainBoard[0][j].getText().equals("")) {
                continue;
            }
            if (mainBoard[0][j].getText().equals(mainBoard[1][j].getText()) &&
                    mainBoard[0][j].getText().equals(mainBoard[2][j].getText())) {
                highlightWinningCells(0, j, 1, j, 2, j);
                showWinnerDialog(mainBoard[0][j].getText());
                return;
            }
        }

        // Check diagonal
        if (!mainBoard[0][0].getText().equals("") && mainBoard[0][0].getText().equals(mainBoard[1][1].getText())
                && mainBoard[0][0].getText().equals(mainBoard[2][2].getText())) {
            highlightWinningCells(0, 0, 1, 1, 2, 2);
            showWinnerDialog(mainBoard[0][0].getText());
            return;
        }

        // Check reverse diagonal
        if (!mainBoard[0][2].getText().equals("") && mainBoard[0][2].getText().equals(mainBoard[1][1].getText())
                && mainBoard[0][2].getText().equals(mainBoard[2][0].getText())) {
            highlightWinningCells(0, 2, 1, 1, 2, 0);
            showWinnerDialog(mainBoard[0][2].getText());
        }
    }

    private void highlightWinningCells(int i1, int j1, int i2, int j2, int i3, int j3) {
        mainBoard[i1][j1].setBackground(Color.GREEN);
        mainBoard[i2][j2].setBackground(Color.GREEN);
        mainBoard[i3][j3].setBackground(Color.GREEN);
        isBoardEnabled = false;
    }

    private void showWinnerDialog(String winner) {
        JOptionPane.showMessageDialog(this, "Gracz " + winner + " wygrywa!", "Koniec gry", JOptionPane.INFORMATION_MESSAGE);
        resetGame();
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
