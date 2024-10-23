import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JPanel3 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JFrame1 parentFrame;

    public JPanel3(JFrame1 parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(-16, -27, 800, 800);
        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("Mode.png")));
        add(lblNewLabel);

        JButton btnNewButton = new JButton("easy");
        btnNewButton.setBounds(241, 394, 302, 119);
        btnNewButton.setIcon(new ImageIcon(getClass().getResource("Easy.jpg")));
        btnNewButton.setOpaque(false); 
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorderPainted(false);
        btnNewButton.addActionListener(e -> {
        	parentFrame.getContentPane().removeAll();
            parentFrame.setContentPane(new EasyMode(parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        add(btnNewButton);

        JButton btnNewButton_1 = new JButton("hard");
        btnNewButton_1.setBounds(241, 524, 302, 107);
        btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("Hard.jpg")));
        btnNewButton_1.setOpaque(false); 
        btnNewButton_1.setContentAreaFilled(false);
        btnNewButton_1.setBorderPainted(false);
        btnNewButton_1.addActionListener(e -> {
        	parentFrame.getContentPane().removeAll();
            parentFrame.setContentPane(new HardMode(parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        add(btnNewButton_1);
    }
}
