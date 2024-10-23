import javax.swing.*;
import java.awt.*;

public class JPanel1 extends JPanel {
    private JFrame1 parentFrame;

    public JPanel1(JFrame1 parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(null);
        
        JLabel lblBackground = new JLabel("");
        lblBackground.setBounds(-8, -25, 800, 800);
        lblBackground.setIcon(new ImageIcon(getClass().getResource("Frame1.png")));
        add(lblBackground);
        
        JButton btnStart = new JButton("Start");
        btnStart.setIcon(new ImageIcon(getClass().getResource("Start.jpg")));
        btnStart.setOpaque(false);
        btnStart.setContentAreaFilled(false);
        btnStart.setBorderPainted(false);
        btnStart.setBounds(405, 531, 261, 90);
        btnStart.addActionListener(e -> {
            JPanel3 contentPaneFrame3 = new JPanel3(parentFrame);
            parentFrame.getContentPane().removeAll();
            parentFrame.setContentPane(contentPaneFrame3);
            parentFrame.setVisible(true);
            parentFrame.revalidate();
        });
        add(btnStart);

        JButton btnOption = new JButton("Option");
        btnOption.setIcon(new ImageIcon(getClass().getResource("Option.jpg")));
        btnOption.setOpaque(false);
        btnOption.setContentAreaFilled(false);
        btnOption.setBorderPainted(false);
        btnOption.setBounds(133, 527, 261, 99);
        btnOption.addActionListener(e -> {
            JPanel2 contentPaneFrame2 = new JPanel2(parentFrame);
            parentFrame.getContentPane().removeAll();
            parentFrame.setContentPane(contentPaneFrame2);
            parentFrame.setVisible(true);
            parentFrame.revalidate();
        });
        add(btnOption);
        
    }
}
