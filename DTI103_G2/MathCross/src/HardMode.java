import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class HardMode extends JPanel {

    private static final long serialVersionUID = 1L;
    private final int ROWS = 7;
    private final int COLS = 7;
    private Color TC = Color.GREEN;
    private Color FC = Color.RED;
    private JFrame1 parentFrame;
    private JTextField[][] puzzleCells;
    private final int[][] numPosition = {{0, 0}, {0, 2}, {0, 4}, {2, 0}, {2, 2}, {2, 4}, {4, 0}, {4, 2}, {4, 4}};
    private final int[][] operPosition = {{0, 1}, {0, 3}, {1, 0}, {1, 2}, {1, 4}, {2, 1}, {2, 3}, {3, 0}, {3, 2}, {3, 4}, {4, 1}, {4, 3}};
    private final int[][] ansPosition = {{0, 6}, {2, 6}, {4, 6}, {6, 0}, {6, 2}, {6, 4}};
    private int countdown90;
    private Timer timer;

    public HardMode(JFrame1 parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(null);

        countdown90 = 90;

        JLabel countdownLabel = new JLabel("Time : " + countdown90 + "s");
        countdownLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        countdownLabel.setBounds(203, 125, 113, 50);
        add(countdownLabel, BorderLayout.NORTH);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	countdown90--;
                if (countdown90 >= 0) {
                    countdownLabel.setText("Time : " + countdown90 + "s");
                } else {
                    ((Timer) e.getSource()).stop();
                    parentFrame.getContentPane().removeAll();
                    parentFrame.setContentPane(new EasyLose(parentFrame));
                    parentFrame.revalidate();
                    parentFrame.repaint();
                }
            }
        });
        timer.start();

        puzzleCells = new JTextField[ROWS][COLS];
        int x = 145;
        int y = 184;
        int width = 65;
        int height = 68;
        Font font = new Font("Arial", Font.PLAIN, 24);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                puzzleCells[i][j] = new JTextField();
                puzzleCells[i][j].setBounds(x, y, width, height);
                puzzleCells[i][j].setHorizontalAlignment(JTextField.CENTER);
                puzzleCells[i][j].setFont(font);
                puzzleCells[i][j].setOpaque(false);
                puzzleCells[i][j].setBorder(null);
                add(puzzleCells[i][j]);
                x += 72;
            }
            x = 145;
            y += 72;
        }

        int[][] equalPosition = {{0, 5}, {2, 5}, {4, 5}, {5, 0}, {5, 2}, {5, 4}};

        int[][] blackPosition = {{1, 1}, {1, 3}, {1, 5}, {1, 6}, {3, 1}, {3, 3}, {3, 5}, {3, 6}, {5, 1}, {5, 3}, {5, 5}, {5, 6}, {6, 1}, {6, 3}, {6, 5}, {6, 6}};

        Set<Integer> usedNumbers = new HashSet<>();

        for (int[] pos : blackPosition) {
            puzzleCells[pos[0]][pos[1]].setBackground(Color.BLACK);
            puzzleCells[pos[0]][pos[1]].setEditable(false);
        }

        for (int[] pos : equalPosition) {
            puzzleCells[pos[0]][pos[1]].setText("=");
            puzzleCells[pos[0]][pos[1]].setEditable(false);
        }

        Random rand = new Random();
        for (int[] pos : numPosition) {
            int randomNumber;
            do {
                randomNumber = rand.nextInt(90)+10;
            } while (usedNumbers.contains(randomNumber));
            usedNumbers.add(randomNumber);
            puzzleCells[pos[0]][pos[1]].setText(Integer.toString(randomNumber));
            puzzleCells[pos[0]][pos[1]].setEditable(false);
        }

        Random ran = new Random();
        for (int[] pos : operPosition) {
            int randomOper = rand.nextInt(2);
            String operator = randomOper == 0 ? "+" : "-";
            puzzleCells[pos[0]][pos[1]].setText(operator);
            puzzleCells[pos[0]][pos[1]].setEditable(false);
        }

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("Frame4.png")));
        lblNewLabel.setBounds(-14, 0, 800, 740);
        add(lblNewLabel);

        setupMouseClickListeners();
    }

    private void setupMouseClickListeners() {
        for (int[] pos : ansPosition) {
            final int row = pos[0];
            final int col = pos[1];
            puzzleCells[row][col].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    handleCellClick(row, col);
                }
            });
        }
    }

    private void handleCellClick(int row, int col) {
        String input = JOptionPane.showInputDialog(parentFrame, "Enter a number :");
        try {
            if ((puzzleCells[row][col].isEditable()) && (puzzleCells[6][col].isEditable())){
                int number = Integer.parseInt(input);
                int num1 = Integer.parseInt(puzzleCells[0][col].getText());
                int num2 = Integer.parseInt(puzzleCells[2][col].getText());
                int num3 = Integer.parseInt(puzzleCells[4][col].getText());

                int result = 0;
                String operation1 = puzzleCells[1][col].getText().trim();
                String operation2 = puzzleCells[3][col].getText().trim();
                switch (operation1) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                }
                switch (operation2) {
                    case "+":
                        result += num3;
                        break;
                    case "-":
                        result -= num3;
                        break;
                }
                String text = Integer.toString(number);
                if (number == result) {
                    puzzleCells[6][col].setText(text);
                    puzzleCells[6][col].setForeground(TC);
                    puzzleCells[6][col].setEditable(false);
                } else {
                    puzzleCells[6][col].setText(text);
                    puzzleCells[6][col].setForeground(FC);
                    countdown90-=10;
                }
                
            }
            if ((puzzleCells[row][col].isEditable()) && (puzzleCells[row][6].isEditable())){
                int number = Integer.parseInt(input);
                int num1 = Integer.parseInt(puzzleCells[row][0].getText());
                int num2 = Integer.parseInt(puzzleCells[row][2].getText());
                int num3 = Integer.parseInt(puzzleCells[row][4].getText());

                int result = 0;
                String operation1 = puzzleCells[row][1].getText().trim();
                String operation2 = puzzleCells[row][3].getText().trim();
                switch (operation1) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                }
                switch (operation2) {
                    case "+":
                        result += num3;
                        break;
                    case "-":
                        result -= num3;
                        break;
                }
                String text = Integer.toString(number);
                if (number == result) {
                    puzzleCells[row][6].setText(text);
                    puzzleCells[row][6].setForeground(TC);
                    puzzleCells[row][6].setEditable(false);
                } else {
                    puzzleCells[row][6].setText(text);
                    puzzleCells[row][6].setForeground(FC);
                    countdown90-=10;
                }
                
            }
            if (allCellsAreGreen()) {
                Timer delayTimer = new Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        parentFrame.getContentPane().removeAll();
                        parentFrame.setContentPane(new EasyWin(parentFrame));
                        parentFrame.revalidate();
                        parentFrame.repaint();
                    }
                });
                timer.stop();
                delayTimer.setRepeats(false);
                delayTimer.start();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parentFrame, "Please enter a valid number.");
        }
    }
    private boolean allCellsAreGreen() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (!puzzleCells[i][j].isEditable()) {
                    continue; 
                }
                if (!puzzleCells[i][j].getForeground().equals(TC)) {
                    return false; 
                }
            }
        }
        return true;
    }
}

