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
                        buttons[i].setIcon(xImg);
                        playerX_turn = false;
                        textfield.setText("Ruch kółka");
                        check();
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 255, 255));
                        buttons[i].setIcon(oImg);
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
                    (buttons[0].getIcon().equals(xImg)) &&
                            (buttons[1].getIcon().equals(xImg)) &&
                            (buttons[2].getIcon().equals(xImg))
            ) {
                xWins(0, 1, 2);
            }
            if (
                    (buttons[3].getIcon().equals(xImg)) &&
                            (buttons[4].getIcon().equals(xImg)) &&
                            (buttons[5].getIcon().equals(xImg))
            ) {
                xWins(3, 4, 5);
            }
            if (
                    (buttons[6].getIcon().equals(xImg)) &&
                            (buttons[7].getIcon().equals(xImg)) &&
                            (buttons[8].getIcon().equals(xImg))
            ) {
                xWins(6, 7, 8);
            }
            if (
                    (buttons[0].getIcon().equals(xImg)) &&
                            (buttons[3].getIcon().equals(xImg)) &&
                            (buttons[6].getIcon().equals(xImg))
            ) {
                xWins(0, 3, 6);
            }
            if (
                    (buttons[1].getIcon().equals(xImg)) &&
                            (buttons[4].getIcon().equals(xImg)) &&
                            (buttons[7].getIcon().equals(xImg))
            ) {
                xWins(1, 4, 7);
            }
            if (
                    (buttons[2].getIcon().equals(xImg)) &&
                            (buttons[5].getIcon().equals(xImg)) &&
                            (buttons[8].getIcon().equals(xImg))
            ) {
                xWins(2, 5, 8);
            }
            if (
                    (buttons[0].getIcon().equals(xImg)) &&
                            (buttons[4].getIcon().equals(xImg)) &&
                            (buttons[8].getIcon().equals(xImg))
            ) {
                xWins(0, 4, 8);
            }
            if (
                    (buttons[2].getIcon().equals(xImg)) &&
                            (buttons[4].getIcon().equals(xImg)) &&
                            (buttons[6].getIcon().equals(xImg))
            ) {
                xWins(2, 4, 6);
            }

            /* O win condition */
            if (
                    (buttons[0].getIcon().equals(oImg)) &&
                            (buttons[1].getIcon().equals(oImg)) &&
                            (buttons[2].getIcon().equals(oImg))
            ) {
                oWins(0, 1, 2);
            }
            if (
                    (buttons[3].getIcon().equals(oImg)) &&
                            (buttons[4].getIcon().equals(oImg)) &&
                            (buttons[5].getIcon().equals(oImg))
            ) {
                oWins(3, 4, 5);
            }
            if (
                    (buttons[6].getIcon().equals(oImg)) &&
                            (buttons[7].getIcon().equals(oImg)) &&
                            (buttons[8].getIcon().equals(oImg))
            ) {
                oWins(6, 7, 8);
            }
            if (
                    (buttons[0].getIcon().equals(oImg)) &&
                            (buttons[3].getIcon().equals(oImg)) &&
                            (buttons[6].getIcon().equals(oImg))
            ) {
                oWins(0, 3, 6);
            }
            if (
                    (buttons[1].getIcon().equals(oImg)) &&
                            (buttons[4].getIcon().equals(oImg)) &&
                            (buttons[7].getIcon().equals(oImg))
            ) {
                oWins(1, 4, 7);
            }
            if (
                    (buttons[2].getIcon().equals(oImg)) &&
                            (buttons[5].getIcon().equals(oImg)) &&
                            (buttons[8].getIcon().equals(oImg))
            ) {
                oWins(2, 5, 8);
            }
            if (
                    (buttons[0].getIcon().equals(oImg)) &&
                            (buttons[4].getIcon().equals(oImg)) &&
                            (buttons[8].getIcon().equals(oImg))
            ) {
                oWins(0, 4, 8);
            }
            if (
                    (buttons[2].getIcon().equals(oImg)) &&
                            (buttons[4].getIcon().equals(oImg)) &&
                            (buttons[6].getIcon().equals(oImg))
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
        }

        public void oWins ( int a, int b, int c){
            buttons[a].setBackground(Color.green);
            buttons[b].setBackground(Color.green);
            buttons[c].setBackground(Color.green);

            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }
            textfield.setText("Kółko wygrywa");
        }

}
