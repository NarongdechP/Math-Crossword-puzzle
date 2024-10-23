import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HardWin extends JPanel {
    private JFrame1 parentFrame;

    public HardWin(JFrame1 parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("HardWin.png")));
        lblNewLabel.setBounds(-14, 0, 800, 740);
        add(lblNewLabel);   
        
        JButton btnHome = new JButton("Home");
		btnHome.setIcon(new ImageIcon(getClass().getResource("Home.jpg")));
        btnHome.setOpaque(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setBorderPainted(false);
        btnHome.setBounds(0, 640, 119, 98);
        btnHome.addActionListener(e -> {
        	parentFrame.getContentPane().removeAll();
            parentFrame.setContentPane(new JPanel1(parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        add(btnHome);
    }
}