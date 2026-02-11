package projects.javaproject;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class RoundedBorderLogin extends AbstractBorder {
    private int radius; private Color color;
    public RoundedBorderLogin(int r, Color c) { this.radius = r; this.color = c; }
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        Graphics2D g2 = (Graphics2D)g; g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color); g2.setStroke(new BasicStroke(2)); g2.drawRoundRect(x, y, w-1, h-1, radius, radius);
    }
}

public class LOGINPAGE extends JFrame implements ActionListener {
    private JTextField adminIdField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheck;
    private JButton loginBtn, forgetBtn, newUserBtn;
    private Color PURPLE = new Color(106, 17, 203), BLUE = new Color(37, 117, 252), DARK = new Color(25, 25, 112), GOLD = new Color(255, 215, 0), LIGHT = new Color(240, 248, 255);
    
    public LOGINPAGE() { initializeGUI(); }
    
    private void initializeGUI() {
        setTitle("Student Course Registration System - Login"); setSize(800, 650); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setLocationRelativeTo(null); setResizable(false);
        
        JPanel main = new JPanel(new BorderLayout(15, 15)) {
            protected void paintComponent(Graphics g) { Graphics2D g2 = (Graphics2D)g;
                g2.setPaint(new GradientPaint(0, 0, PURPLE, 0, getHeight(), BLUE)); g2.fillRect(0, 0, getWidth(), getHeight()); } };
        main.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JPanel header = createPanel(DARK, 20); header.setPreferredSize(new Dimension(800, 80)); header.setLayout(new BorderLayout());
        JLabel title = new JLabel("STUDENT COURSE REGISTRATION SYSTEM"); title.setForeground(GOLD); title.setFont(new Font("Arial", Font.BOLD, 22)); title.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel sub = new JLabel("Admin & User Login - (Admin: admin/admin123 | User: User/1234)"); sub.setForeground(new Color(173, 216, 230)); sub.setFont(new Font("Arial", Font.ITALIC, 11)); sub.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel titlePanel = new JPanel(new BorderLayout()); titlePanel.setOpaque(false); titlePanel.add(title, BorderLayout.CENTER); titlePanel.add(sub, BorderLayout.SOUTH); titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        header.add(titlePanel, BorderLayout.CENTER);
        
        JPanel form = createPanel(LIGHT, 15); form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS)); form.setBorder(BorderFactory.createEmptyBorder(50, 80, 50, 80));
        JPanel idRow = new JPanel(new BorderLayout()); idRow.setOpaque(false); idRow.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        JLabel idLbl = label("Admin ID:", PURPLE); idLbl.setPreferredSize(new Dimension(100, 30)); idRow.add(idLbl, BorderLayout.WEST);
        adminIdField = field(); adminIdField.setPreferredSize(new Dimension(330, 55)); idRow.add(adminIdField, BorderLayout.CENTER);
        form.add(idRow);
        
        JPanel passRow = new JPanel(new BorderLayout()); passRow.setOpaque(false); passRow.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        JLabel passLbl = label("Password:", PURPLE); passLbl.setPreferredSize(new Dimension(100, 30)); passRow.add(passLbl, BorderLayout.WEST);
        passwordField = new JPasswordField(); passwordField.setFont(new Font("Arial", Font.PLAIN, 16)); passwordField.setPreferredSize(new Dimension(330, 55));
        passwordField.setBackground(Color.WHITE); passwordField.setForeground(DARK); passwordField.setBorder(new RoundedBorderLogin(12, PURPLE)); passwordField.setMargin(new Insets(12, 18, 12, 18)); 
        JPanel passFieldPanel = new JPanel(new BorderLayout()); passFieldPanel.setOpaque(false); passFieldPanel.add(passwordField, BorderLayout.CENTER);
        showPasswordCheck = new JCheckBox("Show"); showPasswordCheck.setOpaque(false); showPasswordCheck.setForeground(PURPLE); showPasswordCheck.setFont(new Font("Arial", Font.PLAIN, 11));
        showPasswordCheck.addItemListener(e -> togglePasswordVisibility()); passFieldPanel.add(showPasswordCheck, BorderLayout.EAST); passRow.add(passFieldPanel, BorderLayout.CENTER);
        form.add(passRow);
        
        JPanel buttons = createPanel(LIGHT, 15); buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15)); buttons.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        loginBtn = btn("Login", new Color(34, 139, 34), 20); 
        buttons.add(loginBtn);
        
        JPanel footer = createPanel(LIGHT, 15); footer.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10)); footer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        forgetBtn = linkBtn("Forgot Password?", PURPLE); newUserBtn = linkBtn("New User?", BLUE);
        footer.add(forgetBtn); footer.add(newUserBtn);
        
        JPanel bottom = new JPanel(new BorderLayout(15, 15)); bottom.setOpaque(false); bottom.add(buttons, BorderLayout.NORTH); bottom.add(footer, BorderLayout.SOUTH);
        main.add(header, BorderLayout.NORTH); main.add(form, BorderLayout.CENTER); main.add(bottom, BorderLayout.SOUTH);
        add(main); setVisible(true);
    }
    
    private JPanel createPanel(Color bg, int r) {
        JPanel p = new JPanel() { protected void paintComponent(Graphics g) { Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); g2.setColor(bg);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), r, r); super.paintComponent(g); } };
        p.setOpaque(false); p.setBorder(new RoundedBorderLogin(r, PURPLE)); return p;
    }
    
    private JLabel label(String txt, Color c) { JLabel l = new JLabel(txt); l.setFont(new Font("Arial", Font.BOLD, 13)); l.setForeground(c); return l; }
    private JTextField field() { 
        JTextField f = new JTextField();
        f.setFont(new Font("Arial", Font.PLAIN, 16)); 
        f.setBackground(Color.WHITE);
        f.setForeground(DARK); 
        f.setBorder(new RoundedBorderLogin(12, PURPLE)); 
        f.setMargin(new Insets(12, 18, 12, 18)); 
        f.setMinimumSize(new Dimension(330, 55)); return f; 
    }
    private JButton btn(String txt, Color bg, int r) { JButton b = new JButton(txt); b.setFont(new Font("Arial", Font.BOLD, 14)); b.setBackground(bg);
        b.setForeground(Color.WHITE); b.setFocusPainted(false); b.setBorder(new RoundedBorderLogin(r, bg)); b.setPreferredSize(new Dimension(120, 40));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR)); b.addActionListener(this); return b; }
    private JButton linkBtn(String txt, Color fg) { JButton b = new JButton(txt); b.setFont(new Font("Arial", Font.PLAIN, 11)); b.setForeground(fg);
        b.setBackground(new Color(240, 248, 255)); b.setContentAreaFilled(false); b.setBorderPainted(false); b.setFocusPainted(false); 
        b.setCursor(new Cursor(Cursor.HAND_CURSOR)); b.addActionListener(this); return b; }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn) handleLogin();
        else if (e.getSource() == forgetBtn) handleForgotPassword();
        else if (e.getSource() == newUserBtn) handleNewUser();
    }
    
    private void togglePasswordVisibility() {
        if (showPasswordCheck.isSelected()) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('•');
        }
    }
    
    private void handleLogin() {
        String adminId = adminIdField.getText();
        String password = new String(passwordField.getPassword());
        
        if (adminId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE); return;
        }
        
        if ((adminId.equals("admin") && password.equals("admin123")) || (adminId.equals("User") && password.equals("1234"))) {
            JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new startingpage();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Admin ID or Password!", "Error", JOptionPane.ERROR_MESSAGE);
            adminIdField.setText(""); passwordField.setText("");
        }
    }
    
    private void handleForgotPassword() {
        String email = JOptionPane.showInputDialog(this, "Enter your registered email:", "Forgot Password", JOptionPane.INFORMATION_MESSAGE);
        if (email != null && !email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Recovery link sent to: " + email, "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void handleNewUser() {
        String newId = JOptionPane.showInputDialog(this, "Enter new Admin ID:", "New User Registration", JOptionPane.INFORMATION_MESSAGE);
        if (newId != null && !newId.isEmpty()) {
            String newPass = JOptionPane.showInputDialog(this, "Enter password:", "New User Registration", JOptionPane.INFORMATION_MESSAGE);
            if (newPass != null && !newPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "New Admin account created!\nID: " + newId, "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new LOGINPAGE()); }
}
