import javax.swing.*;

public class JPanel2 extends JPanel {
    private JFrame1 parentFrame;

    public JPanel2(JFrame1 parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, -51, 800, 800);
        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("Frame2.png")));
        add(lblNewLabel);
        
        JButton btnNewButton = new JButton("");
        btnNewButton.setOpaque(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorderPainted(false);
        btnNewButton.setIcon(new ImageIcon(getClass().getResource("Back.jpg")));
        btnNewButton.setBounds(-30, 610, 199, 141);
        btnNewButton.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            parentFrame.setContentPane(new JPanel1(parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        add(btnNewButton);
    }
}
