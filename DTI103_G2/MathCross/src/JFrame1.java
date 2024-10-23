import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class JFrame1 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public JFrame1() {
    	setMinimumSize(new Dimension(800, 800));
        pack();
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Frame1.png")));
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setTitle("Math Cross");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
   
        JButton btnStart = new JButton("Start");
        btnStart.setOpaque(false);
        btnStart.setContentAreaFilled(false);
        btnStart.setBorderPainted(false);
        btnStart.setForeground(new Color(255, 255, 255));
        btnStart.setBounds(405, 531, 261, 90);
        btnStart.setIcon(new ImageIcon(getClass().getResource("Start.jpg")));
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JPanel contentPaneFrame3 = new JPanel3(JFrame1.this);
                getContentPane().removeAll();
                setContentPane(contentPaneFrame3);
                setVisible(true);
                revalidate();   
            }
        });

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(-8, -25, 800, 800);
        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("Frame1.png")));
        contentPane.add(lblNewLabel);
        contentPane.add(btnStart);

        JButton btnNewButton = new JButton("Option");
        btnNewButton.setOpaque(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorderPainted(false);
        btnNewButton.setVerticalAlignment(SwingConstants.TOP);
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBounds(133, 527, 261, 99);
        btnNewButton.setIcon(new ImageIcon(getClass().getResource("Option.jpg")));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JPanel contentPaneFrame2 = new JPanel2(JFrame1.this);
                getContentPane().removeAll();
                setContentPane(contentPaneFrame2);
                setVisible(true);
                revalidate();         
            }
        });
        contentPane.add(btnNewButton);
        
    }
}
