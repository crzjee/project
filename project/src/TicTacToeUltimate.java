import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;



public class TicTacToeUltimate extends gameWindow implements ActionListener {
    private int currentPlayer;

    public TicTacToeUltimate() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        smallBoards[i][j][k][l].addActionListener(this);
                    }
                }
            }
        }

        Random firstMove = new Random();
        currentPlayer = firstMove.nextInt(2)+1;

        Thread titleUpdateThread = new Thread(() -> {
            try {
                Thread.sleep(1234);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (true) {
                String playerSymbol = getPlayerSymbol();
                String title = "Ruch gracza " + playerSymbol;
                titleLabel.setText(title);

                try {
                    Thread.sleep(789);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        titleUpdateThread.start();
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (isBoardEnabled && button.getBackground() == Color.WHITE && button.isEnabled()) {
            resetSmallBoardsBackground();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            if (button == smallBoards[i][j][k][l]) {
                                if (smallBoards[i][j][k][l].getText().equals("")) {
                                    smallBoards[i][j][k][l].setText(getPlayerSymbol());
                                    smallBoards[i][j][k][l].setBackground(Color.LIGHT_GRAY);
                                    checkWin(i, j, k, l);

                                    mainBoard[k][l].setBackground(Color.YELLOW);

                                    currentPlayer = 3 - currentPlayer;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void resetSmallBoardsBackground() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mainBoard[i][j].setBackground(Color.WHITE);
            }
        }
    }
    private String getPlayerSymbol() {
        return currentPlayer == 1 ? "X" : "O";
    }


    private void checkWin(int mainRow, int mainCol, int subRow, int subCol) {
        String currentPlayerSymbol = getPlayerSymbol();

        // Wiersze
        boolean rowWin = true;
        for (int row = 0; row < 3; row++) {
            if (smallBoards[mainRow][mainCol][row][subCol].getText().equals("")
                    || !smallBoards[mainRow][mainCol][row][subCol].getText().equals(currentPlayerSymbol)) {
                rowWin = false;
                break;
            }
        }

        if (rowWin) {
            mainBoard[mainRow][mainCol].setText(currentPlayerSymbol);
            mainBoard[mainRow][mainCol].setBackground(Color.GREEN);
            swapSmallBoard(mainRow, mainCol, currentPlayerSymbol);
            checkBoardWin();
            return;
        }

        // Kolumny
        boolean colWin = true;
        for (int col = 0; col < 3; col++) {
            if (smallBoards[mainRow][mainCol][subRow][col].getText().equals("")
                    || !smallBoards[mainRow][mainCol][subRow][col].getText().equals(currentPlayerSymbol)) {
                colWin = false;
                break;
            }
        }

        if (colWin) {
            mainBoard[mainRow][mainCol].setText(currentPlayerSymbol);
            mainBoard[mainRow][mainCol].setBackground(Color.GREEN);
            swapSmallBoard(mainRow, mainCol, currentPlayerSymbol);
            checkBoardWin();
            return;
        }

        // Diagonal "/"
        boolean diagWin = true;
        for (int i = 0; i < 3; i++) {
            if (smallBoards[mainRow][mainCol][i][i].getText().equals("")
                    || !smallBoards[mainRow][mainCol][i][i].getText().equals(currentPlayerSymbol)) {
                diagWin = false;
                break;
            }
        }

        if (diagWin) {
            mainBoard[mainRow][mainCol].setText(currentPlayerSymbol);
            mainBoard[mainRow][mainCol].setBackground(Color.GREEN);
            swapSmallBoard(mainRow, mainCol, currentPlayerSymbol);
            checkBoardWin();
            return;
        }

        // Diagonal "\"
        boolean revDiagWin = true;
        for (int i = 0; i < 3; i++) {
            if (smallBoards[mainRow][mainCol][i][2 - i].getText().equals("")
                    || !smallBoards[mainRow][mainCol][i][2 - i].getText().equals(currentPlayerSymbol)) {
                revDiagWin = false;
                break;
            }
        }

        if (revDiagWin) {
            mainBoard[mainRow][mainCol].setText(currentPlayerSymbol);
            mainBoard[mainRow][mainCol].setBackground(Color.GREEN);
            swapSmallBoard(mainRow, mainCol, currentPlayerSymbol);
            checkBoardWin();
        }
    }

    private void swapSmallBoard(int mainRow, int mainCol, String playerSymbol) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!smallBoards[mainRow][mainCol][i][j].getText().equals(playerSymbol)) {
                    smallBoards[mainRow][mainCol][i][j].setText(playerSymbol);
                    smallBoards[mainRow][mainCol][i][j].setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }

    private void checkBoardWin() {
        // Wiesze
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
            return;
        }

        boolean isDraw = true;
        outerLoop:
        for (int mainRow = 0; mainRow < 3; mainRow++) {
            for (int mainCol = 0; mainCol < 3; mainCol++) {
                for (int subRow = 0; subRow < 3; subRow++) {
                    for (int subCol = 0; subCol < 3; subCol++) {
                        if (smallBoards[mainRow][mainCol][subRow][subCol].getText().equals("")) {
                            isDraw = false;
                            break outerLoop;
                        }
                    }
                }
            }
        }

        if (isDraw) {
            drawDialogue();
        }
    }

    private void highlightWinningCells(int i1, int j1, int i2, int j2, int i3, int j3) {
        Color originalColor = mainBoard[i1][j1].getBackground();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == i1 && j == j1) {
                    continue;
                }
                if (i == i2 && j == j2) {
                    continue;
                }
                if (i == i3 && j == j3) {
                    continue;
                }
                mainBoard[i][j].setBackground(originalColor);
                mainBoard[i][j].setEnabled(false);
            }
        }

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
        Random firstMove = new Random();
        currentPlayer = firstMove.nextInt(2)+1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mainBoard[i][j].setText("");
                mainBoard[i][j].setBackground(Color.WHITE);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        smallBoards[i][j][k][l].setText("");
                        smallBoards[i][j][k][l].setBackground(Color.WHITE);
                        smallBoards[i][j][k][l].setEnabled(true);
                    }
                }
            }
        }

        isBoardEnabled = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeUltimate::new);
    }
}