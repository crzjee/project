import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public abstract class gameWindow implements ActionListener{
    boolean playerX_turn;
    Random random = new Random();

    /** Plansza */
    JFrame window = new JFrame("Ultimate Tic Tac Toe");
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel optPanel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];


    gameWindow() {
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
}
