import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class gameWindow extends JFrame implements ActionListener {

    public JButton[][] mainBoard;
    public JButton[][][][] smallBoards;
    public int activePlayer;
    public boolean isBoardEnabled;
    public JMenuItem saveGame;
    public JMenuItem loadGame;
    public final JLabel titleLabel;

    public gameWindow() {
        setTitle("Ultimate Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());
        setVisible(true);

        mainBoard = new JButton[3][3];
        smallBoards = new JButton[3][3][3][3];
        activePlayer = 1;
        isBoardEnabled = true;

        titleLabel = new JLabel("Witaj w Tic Tac Toe Ultimate!");
        titleLabel.setFont(new Font("Arial",Font.BOLD,20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(21,21,37));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 3));
        Dimension currentSize = mainPanel.getPreferredSize();
        int desiredHeight = 500;
        Dimension newSize = new Dimension(currentSize.width, desiredHeight);
        mainPanel.setPreferredSize(newSize);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mainBoard[i][j] = new JButton();
                mainBoard[i][j].setBackground(Color.WHITE);
                mainBoard[i][j].addActionListener(this);
                mainPanel.add(mainBoard[i][j]);

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


        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalGlue());
        box.add(mainPanel);

        add(box, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Zapisz/załaduj");
        menuBar.add(fileMenu);

        saveGame = new JMenuItem("Zapisz");
        saveGame.addActionListener(this);
        fileMenu.add(saveGame);

        loadGame = new JMenuItem("Załaduj");
        loadGame.addActionListener(this);
        fileMenu.add(loadGame);

        setJMenuBar(menuBar);
    }
}
