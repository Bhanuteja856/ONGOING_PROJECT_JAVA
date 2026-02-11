package projects.javaproject;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

class RoundedBorder extends AbstractBorder {
    private int radius; private Color color;
    public RoundedBorder(int r, Color c) { this.radius = r; this.color = c; }
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        Graphics2D g2 = (Graphics2D)g; g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color); g2.setStroke(new BasicStroke(2)); g2.drawRoundRect(x, y, w-1, h-1, radius, radius);
    }
}

interface RegistrationInterface {
    String getStudentName(); void setStudentName(String n);
    String getStudentId(); void setStudentId(String id);
    String getCourseId(); void setCourseId(String cid);
    String getRegistrationId(); void setRegistrationId(String rid);
    Date getRegistrationDate(); void setRegistrationDate(Date d);
    void displayInfo();
}

class Registration implements RegistrationInterface {
    private String studentName, studentId, courseId, registrationId;
    private Date registrationDate;
    private static int regCounter = 1001;
    
    public Registration() { this.registrationId = "REG" + (regCounter++); this.registrationDate = new Date(); }
    
    public String getStudentName() { return studentName; }
    public void setStudentName(String n) { this.studentName = n; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String id) { this.studentId = id; }
    public String getCourseId() { return courseId; }
    public void setCourseId(String cid) { this.courseId = cid; }
    public String getRegistrationId() { return registrationId; }
    public void setRegistrationId(String rid) { this.registrationId = rid; }
    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date d) { this.registrationDate = d; }
    public void displayInfo() {
        System.out.println("\n===== REGISTRATION =====\nReg: " + registrationId + "\nName: " + studentName + 
            "\nStu ID: " + studentId + "\nCourse: " + courseId + "\nDate: " + registrationDate + "\n==========================\n");
    }
}

public class rigistrations extends JFrame implements ActionListener {
    private ArrayList<Registration> regList = new ArrayList<>();
    private JTextField nameField, idField, courseField;
    private JButton regBtn, clrBtn, vwBtn, delBtn;
    private JTextArea display;
    private JList<String> regDisplay;
    private DefaultListModel<String> model;
    private Color PURPLE = new Color(106, 17, 203), BLUE = new Color(37, 117, 252), DARK = new Color(25, 25, 112), GOLD = new Color(255, 215, 0), LIGHT = new Color(240, 248, 255);
    
    public rigistrations() { initializeGUI(); }
    
    private void initializeGUI() {
        setTitle("Student Course Registration System");
        setSize(800, 900); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setLocationRelativeTo(null); setResizable(false);
        
        JPanel main = new JPanel(new BorderLayout(15, 15)) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D)g;
                g2.setPaint(new GradientPaint(0, 0, PURPLE, 0, getHeight(), BLUE));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel header = createPanel(DARK, 20); header.setPreferredSize(new Dimension(800, 80)); header.setLayout(new BorderLayout());
        JLabel title = new JLabel("STUDENT COURSE REGISTRATION"); title.setForeground(GOLD); title.setFont(new Font("Arial", Font.BOLD, 24)); title.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel sub = new JLabel("Manage Your Course Enrollments"); sub.setForeground(new Color(173, 216, 230)); sub.setFont(new Font("Arial", Font.ITALIC, 12)); sub.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel titlePanel = new JPanel(new BorderLayout()); titlePanel.setOpaque(false); titlePanel.add(title, BorderLayout.CENTER); titlePanel.add(sub, BorderLayout.SOUTH); titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        header.add(titlePanel, BorderLayout.CENTER);
        
        JPanel form = createPanel(LIGHT, 15); form.setLayout(new GridLayout(3, 2, 15, 15)); form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        form.add(label("Student Name:", PURPLE)); nameField = field(); form.add(nameField);
        form.add(label("Student ID:", PURPLE)); idField = field(); form.add(idField);
        form.add(label("Course ID (e.g., CR100):", PURPLE)); courseField = field(); form.add(courseField);
        
        JPanel buttons = createPanel(LIGHT, 15); buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 12)); buttons.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
        regBtn = btn("Register", new Color(34, 139, 34), 20); clrBtn = btn("Clear", new Color(220, 20, 60), 20);
        vwBtn = btn("View All", DARK, 20); delBtn = btn("Delete", new Color(178, 34, 34), 20);
        buttons.add(regBtn); buttons.add(clrBtn); buttons.add(vwBtn); buttons.add(delBtn);
        
        JPanel list = createPanel(LIGHT, 15); list.setLayout(new BorderLayout(10, 10)); list.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        JLabel listLbl = new JLabel("Registered Students"); listLbl.setFont(new Font("Arial", Font.BOLD, 13)); listLbl.setForeground(PURPLE);
        model = new DefaultListModel<>(); regDisplay = new JList<>(model); regDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        regDisplay.setFont(new Font("Courier New", Font.PLAIN, 11)); regDisplay.setBackground(new Color(255, 255, 240)); regDisplay.setForeground(DARK);
        JScrollPane listScroll = new JScrollPane(regDisplay); listScroll.setBorder(new RoundedBorder(10, PURPLE));
        list.add(listLbl, BorderLayout.NORTH); list.add(listScroll, BorderLayout.CENTER);
        
        JPanel disp = createPanel(LIGHT, 15); disp.setLayout(new BorderLayout(10, 10)); disp.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        JLabel dispLbl = new JLabel("Registration Details"); dispLbl.setFont(new Font("Arial", Font.BOLD, 13)); dispLbl.setForeground(PURPLE);
        display = new JTextArea(5, 50); display.setEditable(false); display.setFont(new Font("Courier New", Font.PLAIN, 11));
        display.setBackground(new Color(255, 255, 240)); display.setForeground(DARK); display.setLineWrap(true); display.setWrapStyleWord(true);
        JScrollPane dispScroll = new JScrollPane(display); dispScroll.setBorder(new RoundedBorder(10, PURPLE));
        disp.add(dispLbl, BorderLayout.NORTH); disp.add(dispScroll, BorderLayout.CENTER);
        
        JPanel bottom = new JPanel(new GridLayout(1, 2, 15, 0)); bottom.setOpaque(false); bottom.add(list); bottom.add(disp);
        JPanel top = new JPanel(new BorderLayout(15, 15)); top.setOpaque(false); top.add(form, BorderLayout.CENTER); top.add(buttons, BorderLayout.SOUTH);
        
        main.add(header, BorderLayout.NORTH); main.add(top, BorderLayout.CENTER); main.add(bottom, BorderLayout.SOUTH);
        add(main); setVisible(true);
    }
    
    private JPanel createPanel(Color bg, int r) {
        JPanel p = new JPanel() { protected void paintComponent(Graphics g) { Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); g2.setColor(bg);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), r, r); super.paintComponent(g); } };
        p.setOpaque(false); p.setBorder(new RoundedBorder(r, PURPLE)); return p;
    }
    
    private JLabel label(String txt, Color c) { JLabel l = new JLabel(txt); l.setFont(new Font("Arial", Font.BOLD, 12)); l.setForeground(c); return l; }
    private JTextField field() { JTextField f = new JTextField(); f.setFont(new Font("Arial", Font.PLAIN, 12)); f.setBackground(Color.WHITE);
        f.setForeground(DARK); f.setBorder(new RoundedBorder(10, PURPLE)); f.setMargin(new Insets(8, 10, 8, 10)); return f; }
    private JButton btn(String txt, Color bg, int r) { JButton b = new JButton(txt); b.setFont(new Font("Arial", Font.BOLD, 12)); b.setBackground(bg);
        b.setForeground(Color.WHITE); b.setFocusPainted(false); b.setBorder(new RoundedBorder(r, bg)); b.setPreferredSize(new Dimension(120, 38));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR)); b.addActionListener(this); return b; }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == regBtn) register();
        else if (e.getSource() == clrBtn) clear();
        else if (e.getSource() == vwBtn) viewAll();
        else if (e.getSource() == delBtn) delete();
    }
    
    private void register() {
        if (nameField.getText().isEmpty() || idField.getText().isEmpty() || courseField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE); return;
        }
        Registration r = new Registration(); r.setStudentName(nameField.getText()); r.setStudentId(idField.getText()); r.setCourseId(courseField.getText());
        regList.add(r); model.addElement(r.getRegistrationId() + " - " + r.getStudentName() + " (" + r.getCourseId() + ")");
        JOptionPane.showMessageDialog(this, "Registered!\nID: " + r.getRegistrationId(), "Success", JOptionPane.INFORMATION_MESSAGE); r.displayInfo(); clear();
    }
    
    private void clear() { nameField.setText(""); idField.setText(""); courseField.setText(""); display.setText(""); }
    
    private void viewAll() {
        if (regList.isEmpty()) { display.setText("No registrations!"); return; }
        StringBuilder s = new StringBuilder("===== ALL REGISTRATIONS =====\n\n");
        for (int i = 0; i < regList.size(); i++) {
            Registration r = regList.get(i);
            s.append(i+1).append(". ").append(r.getRegistrationId()).append(" | ").append(r.getStudentName()).append(" | ")
             .append(r.getStudentId()).append(" | ").append(r.getCourseId()).append(" | ").append(r.getRegistrationDate()).append("\n");
        }
        s.append("\nTotal: ").append(regList.size()); display.setText(s.toString());
    }
    
    private void delete() {
        int idx = regDisplay.getSelectedIndex();
        if (idx == -1) { JOptionPane.showMessageDialog(this, "Select a registration!", "Error", JOptionPane.WARNING_MESSAGE); return; }
        Registration r = regList.remove(idx); model.remove(idx);
        JOptionPane.showMessageDialog(this, "Deleted: " + r.getStudentName(), "Success", JOptionPane.INFORMATION_MESSAGE); display.setText("");
    }
    
    public static void main(String[] args) { } // Entry point disabled - use LOGINPAGE.java to start
}
