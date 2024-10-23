import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class button {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MATH CROSS WORD PUZZLE");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set background color of main frame to white
        frame.getContentPane().setBackground(Color.WHITE);

        // Create a JPanel to contain components
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create a JPanel for JLabel and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Set background color of panel to white
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Math Cross Word");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 40)); // Use Comic Sans MS font
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add padding to top and bottom

        JButton button1 = new JButton("Start");
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30)); // Use Comic Sans MS font
        button1.setMargin(new Insets(20, 50, 20, 50)); // Set button margin

        JButton button2 = new JButton("Option");
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30)); // Use Comic Sans MS font
        button2.setMargin(new Insets(20, 50, 20, 50)); // Set button margin

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30)); // Use Comic Sans MS font
        backButton.setMargin(new Insets(20, 50, 20, 50)); // Set button margin
        backButton.setVisible(false);

        // Add ActionListener to the "Start" button
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button1.setText("Easy");
                button2.setText("Hard");
                backButton.setVisible(true);
            }
        });

        // Add ActionListener to the "Back" button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button1.setText("Start");
                button2.setText("Option");
                backButton.setVisible(false);
            }
        });

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Add vertical gap between label and button
        panel.add(button1);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical gap between buttons
        panel.add(button2);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical gap between button2 and backButton
        panel.add(backButton);

        // Add the JPanel with JLabel and buttons to the mainPanel and center it
        mainPanel.add(panel, gbc);

        // Set background color of mainPanel to white
        mainPanel.setBackground(Color.WHITE);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
