package projects.javaproject;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class RoundedBorderStart extends AbstractBorder {
    private int radius; private Color color;
    public RoundedBorderStart(int r, Color c) { this.radius = r; this.color = c; }
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        Graphics2D g2 = (Graphics2D)g; g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color); g2.setStroke(new BasicStroke(2)); g2.drawRoundRect(x, y, w-1, h-1, radius, radius);
    }
}

public class startingpage extends JFrame implements ActionListener {
    private JButton newStudentBtn, newCourseBtn, registrationBtn;
    private Color PURPLE = new Color(106, 17, 203), BLUE = new Color(37, 117, 252), DARK = new Color(25, 25, 112), GOLD = new Color(255, 215, 0), LIGHT = new Color(240, 248, 255);
    
    public startingpage() { initializeGUI(); }
    
    private void initializeGUI() {
        setTitle("Student Course Registration System"); setSize(700, 500); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setLocationRelativeTo(null); setResizable(false);
        
        JPanel main = new JPanel(new BorderLayout(15, 15)) {
            protected void paintComponent(Graphics g) { Graphics2D g2 = (Graphics2D)g;
                g2.setPaint(new GradientPaint(0, 0, PURPLE, 0, getHeight(), BLUE)); g2.fillRect(0, 0, getWidth(), getHeight()); } };
        main.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JPanel header = createPanel(DARK, 20); header.setPreferredSize(new Dimension(700, 100)); header.setLayout(new BorderLayout());
        JLabel title = new JLabel("STUDENT COURSE REGISTRATION SYSTEM"); title.setForeground(GOLD); title.setFont(new Font("Arial", Font.BOLD, 28)); title.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel sub = new JLabel("Select an option to get started"); sub.setForeground(new Color(173, 216, 230)); sub.setFont(new Font("Arial", Font.ITALIC, 13)); sub.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel titlePanel = new JPanel(new BorderLayout()); titlePanel.setOpaque(false); titlePanel.add(title, BorderLayout.CENTER); titlePanel.add(sub, BorderLayout.SOUTH); titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        header.add(titlePanel, BorderLayout.CENTER);
        
        JPanel options = createPanel(LIGHT, 15); options.setLayout(new GridLayout(3, 1, 15, 20)); options.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        newStudentBtn = btnLarge("➕ New Student", new Color(34, 139, 34), 25);
        newCourseBtn = btnLarge("📚 New Course Entry", new Color(37, 117, 252), 25);
        registrationBtn = btnLarge("✓ Student Course Registration", new Color(220, 20, 60), 25);
        options.add(newStudentBtn); options.add(newCourseBtn); options.add(registrationBtn);
        
        main.add(header, BorderLayout.NORTH); main.add(options, BorderLayout.CENTER);
        add(main); setVisible(true);
    }
    
    private JPanel createPanel(Color bg, int r) {
        JPanel p = new JPanel() { protected void paintComponent(Graphics g) { Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); g2.setColor(bg);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), r, r); super.paintComponent(g); } };
        p.setOpaque(false); p.setBorder(new RoundedBorderStart(r, PURPLE)); return p;
    }
    
    private JButton btnLarge(String txt, Color bg, int r) { JButton b = new JButton(txt); b.setFont(new Font("Arial", Font.BOLD, 16)); b.setBackground(bg);
        b.setForeground(Color.WHITE); b.setFocusPainted(false); b.setBorder(new RoundedBorderStart(r, bg)); b.setPreferredSize(new Dimension(250, 60));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR)); b.addActionListener(this); return b; }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newStudentBtn) new studentinfo();
        else if (e.getSource() == newCourseBtn) new coursesinfo();
        else if (e.getSource() == registrationBtn) new rigistrations();
    }
    
    public static void main(String[] args) { } // Entry point disabled - use LOGINPAGE.java to start
}
