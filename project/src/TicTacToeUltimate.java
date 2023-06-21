import java.awt.*;
import java.awt.event.*;

public class TicTacToeUltimate extends gameWindow{
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (playerX_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 255, 255));
                        buttons[i].setText("X");
//                        buttons[i].setIcon(xImg);
                        playerX_turn = false;
                        textfield.setText("Ruch kółka");
                        check();
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 255, 255));
                        buttons[i].setText("O");
//                        buttons[i].setIcon(oImg);
                        playerX_turn = true;
                        textfield.setText("Ruch krzyżyka");
                        check();
                    }
                }
            }
        }
    }


        public void check () {
            /* X win condition */
            if (
                    (buttons[0].getText().equals("X")) &&
                            (buttons[1].getText().equals("X")) &&
                            (buttons[2].getText().equals("X"))
            ) {
                xWins(0, 1, 2);
            }
            if (
                    (buttons[3].getText().equals("X")) &&
                            (buttons[4].getText().equals("X")) &&
                            (buttons[5].getText().equals("X"))
            ) {
                xWins(3, 4, 5);
            }
            if (
                    (buttons[6].getText().equals("X")) &&
                            (buttons[7].getText().equals("X")) &&
                            (buttons[8].getText().equals("X"))
            ) {
                xWins(6, 7, 8);
            }
            if (
                    (buttons[0].getText().equals("X")) &&
                            (buttons[3].getText().equals("X")) &&
                            (buttons[6].getText().equals("X"))
            ) {
                xWins(0, 3, 6);
            }
            if (
                    (buttons[1].getText().equals("X")) &&
                            (buttons[4].getText().equals("X")) &&
                            (buttons[7].getText().equals("X"))
            ) {
                xWins(1, 4, 7);
            }
            if (
                    (buttons[2].getText().equals("X")) &&
                            (buttons[5].getText().equals("X")) &&
                            (buttons[8].getText().equals("X"))
            ) {
                xWins(2, 5, 8);
            }
            if (
                    (buttons[0].getText().equals("X")) &&
                            (buttons[4].getText().equals("X")) &&
                            (buttons[8].getText().equals("X"))
            ) {
                xWins(0, 4, 8);
            }
            if (
                    (buttons[2].getText().equals("X")) &&
                            (buttons[4].getText().equals("X")) &&
                            (buttons[6].getText().equals("X"))
            ) {
                xWins(2, 4, 6);
            }

            /* O win condition */
            if (
                    (buttons[0].getText().equals("O")) &&
                            (buttons[1].getText().equals("O")) &&
                            (buttons[2].getText().equals("O"))
            ) {
                oWins(0, 1, 2);
            }
            if (
                    (buttons[3].getText().equals("O")) &&
                            (buttons[4].getText().equals("O")) &&
                            (buttons[5].getText().equals("O"))
            ) {
                oWins(3, 4, 5);
            }
            if (
                    (buttons[6].getText().equals("O")) &&
                            (buttons[7].getText().equals("O")) &&
                            (buttons[8].getText().equals("O"))
            ) {
                oWins(6, 7, 8);
            }
            if (
                    (buttons[0].getText().equals("O")) &&
                            (buttons[3].getText().equals("O")) &&
                            (buttons[6].getText().equals("O"))
            ) {
                oWins(0, 3, 6);
            }
            if (
                    (buttons[1].getText().equals("O")) &&
                            (buttons[4].getText().equals("O")) &&
                            (buttons[7].getText().equals("O"))
            ) {
                oWins(1, 4, 7);
            }
            if (
                    (buttons[2].getText().equals("O")) &&
                            (buttons[5].getText().equals("O")) &&
                            (buttons[8].getText().equals("O"))
            ) {
                oWins(2, 5, 8);
            }
            if (
                    (buttons[0].getText().equals("O")) &&
                            (buttons[4].getText().equals("O")) &&
                            (buttons[8].getText().equals("O"))
            ) {
                oWins(0, 4, 8);
            }
            if (
                    (buttons[2].getText().equals("O")) &&
                            (buttons[4].getText().equals("O")) &&
                            (buttons[6].getText().equals("O"))
            ) {
                oWins(2, 4, 6);
            }

        }

        public void xWins ( int a, int b, int c){
            buttons[a].setBackground(Color.green);
            buttons[b].setBackground(Color.green);
            buttons[c].setBackground(Color.green);

            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }
            textfield.setText("Krzyzyk wygrywa");
            startNew();
        }

        public void oWins ( int a, int b, int c){
            buttons[a].setBackground(Color.green);
            buttons[b].setBackground(Color.green);
            buttons[c].setBackground(Color.green);

            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }
            textfield.setText("Kółko wygrywa");
            startNew();
        }
        public void startNewGame(int a, int b, int c){
            for (int i=0; i<9; i++){
                buttons[i].setEnabled(true);
                buttons[i].setText("");
                buttons[a].setBackground(new Color(21,27,31));
                buttons[b].setBackground(new Color(21,27,31));
                buttons[c].setBackground(new Color(21,27,31));
            }
        }

}
