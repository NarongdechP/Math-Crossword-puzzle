import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.util.Random;

public class MathCrosswordPuzzle extends JFrame {
    private JTextField[][] puzzleCells;
    private final int rows = 7;
    private final int columns = 7;
    private JPanel panel;
    Color colorIN = Color.WHITE;
    Color colorOUT = Color.BLUE;
    Color TC = Color.GREEN;
    Color FC = Color.RED;
    private JLabel countdownLabel;
    private Timer timer;
    private int countdown60 = 60;
    Random random = new Random();

    public MathCrosswordPuzzle() {
        setTitle("Math Crossword Puzzle");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(rows, columns));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100)); // เพิ่ม Border ให้กับ JPanel เพื่อสร้างพื้นที่ว่างรอบ
        
        Font buttonFont = new Font("Arial", Font.PLAIN, 35);

        puzzleCells = new JTextField[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                puzzleCells[i][j] = new JTextField();
                puzzleCells[i][j].setHorizontalAlignment(JTextField.CENTER);
                puzzleCells[i][j].setFont(buttonFont);
                panel.add(puzzleCells[i][j]);
            }
        }
        
        add(panel);
        
        // สร้าง JLabel สำหรับแสดงเวลานับถอยหลัง
        countdownLabel = new JLabel("Time Left: " + countdown60 + "s");
        countdownLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        countdownLabel.setHorizontalAlignment(SwingConstants.LEFT);
        countdownLabel.setBorder(new EmptyBorder(50, 50, 10, 10));
        add(countdownLabel, BorderLayout.NORTH);

        // สร้าง Timer สำหรับการนับถอยหลัง
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdown60--;
                if (countdown60 >= 0) {
                    countdownLabel.setText("Time Left: " + countdown60 + "s");
                } else {
                    ((Timer) e.getSource()).stop(); // หยุด Timer เมื่อเวลานับถอยหลังหมด
                    //JOptionPane.showMessageDialog(null, "Time's up!");
                }
            }
        });
        timer.start(); // เริ่มต้นการนับถอยหลัง
        
        
        // กำหนดตัวอักษรและการเข้าถึงการแก้ไข
        puzzleCells[0][0].setText(String.valueOf(random.nextInt(10)));
        puzzleCells[0][2].setText(String.valueOf(random.nextInt(10)));
        puzzleCells[0][4].setText(String.valueOf(random.nextInt(10)));
        puzzleCells[0][1].setText(Operation(random.nextInt(2)));
        puzzleCells[0][3].setText(Operation(random.nextInt(2)));
        puzzleCells[0][5].setText("=");
        
        puzzleCells[1][0].setText(Operation(random.nextInt(2)));
        puzzleCells[1][2].setText(Operation(random.nextInt(2)));
        puzzleCells[1][4].setText(Operation(random.nextInt(2)));
        
        puzzleCells[2][0].setText(String.valueOf(random.nextInt(10)));
        puzzleCells[2][2].setText(String.valueOf(random.nextInt(10)));
        puzzleCells[2][4].setText(String.valueOf(random.nextInt(10)));
        puzzleCells[2][1].setText(Operation(random.nextInt(2)));
        puzzleCells[2][3].setText(Operation(random.nextInt(2)));
        puzzleCells[2][5].setText("=");
        
        puzzleCells[3][0].setText(Operation(random.nextInt(2)));
        puzzleCells[3][2].setText(Operation(random.nextInt(2)));
        puzzleCells[3][4].setText(Operation(random.nextInt(2)));
        
        puzzleCells[4][0].setText(String.valueOf(random.nextInt(10)));
        puzzleCells[4][2].setText(String.valueOf(random.nextInt(10)));
        puzzleCells[4][4].setText(String.valueOf(random.nextInt(10)));
        puzzleCells[4][1].setText(Operation(random.nextInt(2)));
        puzzleCells[4][3].setText(Operation(random.nextInt(2)));
        puzzleCells[4][5].setText("=");
        
        puzzleCells[5][0].setText("=");
        puzzleCells[5][2].setText("=");
        puzzleCells[5][4].setText("=");
        

        
        //กำหนดการเข้าถึงการแก้ไข
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (puzzleCells[i][j].getText().equals("+") || 
                    puzzleCells[i][j].getText().equals("-") ||
                    puzzleCells[i][j].getText().equals("=") ||
                    !puzzleCells[i][j].getText().isEmpty()) {
                	puzzleCells[i][j].setEditable(false);
                } else {
                	puzzleCells[i][j].setEditable(false);
                	puzzleCells[0][6].setEditable(true);
                	puzzleCells[2][6].setEditable(true);
                	puzzleCells[4][6].setEditable(true);
                	puzzleCells[6][0].setEditable(true);
                	puzzleCells[6][2].setEditable(true);
                	puzzleCells[6][4].setEditable(true);
                }
                
                //กำหนดสีช่อง
                if (puzzleCells[i][j].getText().equals("+") || 
                    puzzleCells[i][j].getText().equals("-") ||
                    puzzleCells[i][j].getText().equals("=") ||
                    !puzzleCells[i][j].getText().isEmpty()||
                    puzzleCells[i][j].isEditable()) {
                    puzzleCells[i][j].setBackground(colorIN);
                    
                } else {
                	puzzleCells[i][j].setBackground(colorOUT);

                }
                
            }
            
        }
        check();
        setVisible(true);
    }
    
    public static String Operation(int x){
        switch (x) {
            case 1:
                return "-";
            default:
                return "+";
        }
    }
    
    public void check() {
    	
        puzzleCells[0][6].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter a number :");
                try {
                	if (puzzleCells[0][6].isEditable()) {
                		int number = Integer.parseInt(input);
                        int num1 = Integer.parseInt(puzzleCells[0][0].getText());
                        int num2 = Integer.parseInt(puzzleCells[0][2].getText());
                        int num3 = Integer.parseInt(puzzleCells[0][4].getText());
                        
                        int result = 0;
                        String operation1 = puzzleCells[0][1].getText().trim(); // ดึงเครื่องหมายการดำเนินการ
                        String operation2 = puzzleCells[0][3].getText().trim(); // ดึงเครื่องหมายการดำเนินการ
                        // ดำเนินการคำนวณตามเครื่องหมายการดำเนินการ
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
                        	
                        	puzzleCells[0][6].setText(text);
                        	puzzleCells[0][6].setForeground(TC);
                        	puzzleCells[0][6].setEditable(false);;
                        }else {
                        	puzzleCells[0][6].setText(text);
                        	puzzleCells[0][6].setForeground(FC);
                        }
                	}
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }
        });
        
        puzzleCells[2][6].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter a number :");
                try {
                	if (puzzleCells[2][6].isEditable()) {
                		int number = Integer.parseInt(input);
                        int num1 = Integer.parseInt(puzzleCells[2][0].getText());
                        int num2 = Integer.parseInt(puzzleCells[2][2].getText());
                        int num3 = Integer.parseInt(puzzleCells[2][4].getText());
                        
                        int result = 0;
                        String operation1 = puzzleCells[2][1].getText().trim(); // ดึงเครื่องหมายการดำเนินการ
                        String operation2 = puzzleCells[2][3].getText().trim(); // ดึงเครื่องหมายการดำเนินการ
                        // ดำเนินการคำนวณตามเครื่องหมายการดำเนินการ
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
                        	puzzleCells[2][6].setText(text);
                        	puzzleCells[2][6].setForeground(TC);
                        	puzzleCells[2][6].setEditable(false);;
                        } else {
                        	puzzleCells[2][6].setText(text);
                        	puzzleCells[2][6].setForeground(FC);
                        }
                	}
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }
        });
        
        puzzleCells[4][6].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter a number :");
                try {
                	if (puzzleCells[4][6].isEditable()) {
                		int number = Integer.parseInt(input);
                        int num1 = Integer.parseInt(puzzleCells[4][0].getText());
                        int num2 = Integer.parseInt(puzzleCells[4][2].getText());
                        int num3 = Integer.parseInt(puzzleCells[4][4].getText());
                        
                        int result = 0;
                        String operation1 = puzzleCells[4][1].getText().trim(); // ดึงเครื่องหมายการดำเนินการ
                        String operation2 = puzzleCells[4][3].getText().trim(); // ดึงเครื่องหมายการดำเนินการ
                        // ดำเนินการคำนวณตามเครื่องหมายการดำเนินการ
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
                        	
                        	puzzleCells[4][6].setText(text);
                        	puzzleCells[4][6].setForeground(TC);
                        	puzzleCells[4][6].setEditable(false);;
                        }else {
                        	puzzleCells[4][6].setText(text);
                        	puzzleCells[4][6].setForeground(FC);
                        }
                	}
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }
        });
        
        puzzleCells[6][0].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter a number :");
                try {
                	if (puzzleCells[6][0].isEditable()) {
                		int number = Integer.parseInt(input);
                        int num1 = Integer.parseInt(puzzleCells[0][0].getText());
                        int num2 = Integer.parseInt(puzzleCells[2][0].getText());
                        int num3 = Integer.parseInt(puzzleCells[4][0].getText());
                        
                        int result = 0;
                        String operation1 = puzzleCells[1][0].getText().trim(); // ดึงเครื่องหมายการดำเนินการ
                        String operation2 = puzzleCells[3][0].getText().trim(); // ดึงเครื่องหมายการดำเนินการ
                        // ดำเนินการคำนวณตามเครื่องหมายการดำเนินการ
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
                        	
                        	puzzleCells[6][0].setText(text);
                        	puzzleCells[6][0].setForeground(TC);
                        	puzzleCells[6][0].setEditable(false);;
                        }else {
                        	puzzleCells[6][0].setText(text);
                        	puzzleCells[6][0].setForeground(FC);
                        }
                	}
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }
        });
        
        puzzleCells[6][2].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter a number :");
                try {
                	if (puzzleCells[6][2].isEditable()) {
                		int number = Integer.parseInt(input);
                        int num1 = Integer.parseInt(puzzleCells[0][2].getText());
                        int num2 = Integer.parseInt(puzzleCells[2][2].getText());
                        int num3 = Integer.parseInt(puzzleCells[4][2].getText());
                        
                        int result = 0;
                        String operation1 = puzzleCells[1][2].getText().trim(); // ดึงเครื่องหมายการดำเนินการ
                        String operation2 = puzzleCells[3][2].getText().trim(); // ดึงเครื่องหมายการดำเนินการ
                        // ดำเนินการคำนวณตามเครื่องหมายการดำเนินการ
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
                        	
                        	puzzleCells[6][2].setText(text);
                        	puzzleCells[6][2].setForeground(TC);
                        	puzzleCells[6][2].setEditable(false);;
                        }else {
                        	puzzleCells[6][2].setText(text);
                        	puzzleCells[6][2].setForeground(FC);
                        }
                	}
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }
        });
        
        puzzleCells[6][4].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter a number :");
                try {
                	if (puzzleCells[6][4].isEditable()) {
                		int number = Integer.parseInt(input);
                        int num1 = Integer.parseInt(puzzleCells[0][4].getText());
                        int num2 = Integer.parseInt(puzzleCells[2][4].getText());
                        int num3 = Integer.parseInt(puzzleCells[4][4].getText());
                        
                        int result = 0;
                        String operation1 = puzzleCells[1][4].getText().trim(); // ดึงเครื่องหมายการดำเนินการ
                        String operation2 = puzzleCells[3][4].getText().trim(); // ดึงเครื่องหมายการดำเนินการ
                        // ดำเนินการคำนวณตามเครื่องหมายการดำเนินการ
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
                        	
                        	puzzleCells[6][4].setText(text);
                        	puzzleCells[6][4].setForeground(TC);
                        	puzzleCells[6][4].setEditable(false);;
                        }else {
                        	puzzleCells[6][4].setText(text);
                        	puzzleCells[6][4].setForeground(FC);
                        }
                	}
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MathCrosswordPuzzle();
            }
        });
    }
}