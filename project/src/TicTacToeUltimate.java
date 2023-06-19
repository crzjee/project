import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;



public class TicTacToeUltimate implements ActionListener{
    final String instructions = "Instrukcje: \n\n";
    boolean playerX_turn;
    Random random = new Random();

    /** Plansza */
    JFrame window = new JFrame("Ultimate Tic Tac Toe");
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel optPanel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];

    /** Instrukcje */
    JButton instButton = new JButton("Instrukcje");
    JFrame instWindow = new JFrame("Instrukcje");
    JTextArea instText = new JTextArea(instructions);
    JScrollPane instPane = new JScrollPane(instText);
    JPanel instPanel = new JPanel();




    TicTacToeUltimate() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 600);
        window.getContentPane().setBackground(new Color(250, 250, 0));
        window.setLayout(new BorderLayout());
        window.add(optPanel, BorderLayout.SOUTH);
        window.setVisible(true);

        textfield.setBackground(new Color(21, 27, 31));
        textfield.setForeground(new Color(255, 255, 255));
        textfield.setFont(new Font("Sans Serif", Font.BOLD, 30));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Ultimate Tic Tac Toe");
        textfield.setOpaque(true);

        instWindow.setSize(300,250);
        instText.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        instText.setLineWrap(true);
        instText.setWrapStyleWord(true);
        instText.setEditable(false);
        instPane.setPreferredSize(new Dimension(500, 300));
        instPanel.add(instPane);
        instWindow.setResizable(false);
        instWindow.add(instPanel);
        instWindow.pack();
        instButton.setActionCommand("Instrukcje");
        instButton.addActionListener(this);

        optPanel.add(instButton);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 600, 50);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(0, 245, 0));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(21,27,31));
        mainPanel.setLayout(new GridLayout(3,3,10,10));
        for(int i=0;i< 9;i++){
            window.add(mainPanel, BorderLayout.CENTER);
            for( i=0; i<9; i++){
                buttons[i] = new JButton();
                mainPanel.add(buttons[i]);
                buttons[i].setBackground(new Color(0,0,0));
                buttons[i].setFont(new Font("Sans Serif", Font.BOLD, 30));
                buttons[i].setFocusable(false);
                buttons[i].addActionListener(this);
            }
        }

        titlePanel.add(textfield);
        window.add(titlePanel, BorderLayout.NORTH);


        firstTurn();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (playerX_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 255, 255));
                        buttons[i].setText("X");
                        playerX_turn = false;
                        textfield.setText("Ruch kółka");
                        check();
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 255, 255));
                        buttons[i].setText("O");
                        playerX_turn = true;
                        textfield.setText("Ruch krzyżyka");
                        check();
                    }
                }
            }
        }
        if(e.getActionCommand().equals("Instrukcje")){
            instWindow.setVisible(true);
        }
    }

        public void firstTurn () {

            try {
                Thread.sleep(1234);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (random.nextInt(2) == 0) {
                playerX_turn = true;
                textfield.setText("Ruch krzyżyka");
            } else {
                playerX_turn = false;
                textfield.setText("Ruch kółka");
            }
        }

        public void check () {
            /** X win condition */
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
            /** O win condition */
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
