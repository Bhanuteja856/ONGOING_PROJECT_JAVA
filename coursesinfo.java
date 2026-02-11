package projects.javaproject;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class RoundedBorderCourse extends AbstractBorder {
    private int radius; private Color color;
    public RoundedBorderCourse(int r, Color c) { this.radius = r; this.color = c; }
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        Graphics2D g2 = (Graphics2D)g; g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color); g2.setStroke(new BasicStroke(2)); g2.drawRoundRect(x, y, w-1, h-1, radius, radius);
    }
}

interface CourseInterface {
    String getCourseName(); void setCourseName(String n);
    String getCourseCode(); void setCourseCode(String c);
    String getCourseDuration(); void setCourseDuration(String d);
    double getCourseCost(); void setCourseCost(double c);
    String getCourseContent(); void setCourseContent(String c);
    void displayInfo();
}

class Course implements CourseInterface {
    private String courseName, courseCode, courseDuration, courseContent;
    private double courseCost;
    private static int codeCounter = 100;
    
    public Course() { this.courseCode = "CR" + (codeCounter++); }
    
    public String getCourseName() { return courseName; }
    public void setCourseName(String n) { this.courseName = n; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String c) { this.courseCode = c; }
    public String getCourseDuration() { return courseDuration; }
    public void setCourseDuration(String d) { this.courseDuration = d; }
    public double getCourseCost() { return courseCost; }
    public void setCourseCost(double c) { this.courseCost = c; }
    public String getCourseContent() { return courseContent; }
    public void setCourseContent(String c) { this.courseContent = c; }
    public void displayInfo() {
        System.out.println("\n===== COURSE =====\nName: " + courseName + "\nCode: " + courseCode + "\nDuration: " + courseDuration + 
            "\nCost: ₹" + courseCost + "\nContent: " + courseContent + "\n==================\n");
    }
}

public class coursesinfo extends JFrame implements ActionListener {
    private ArrayList<Course> courseList = new ArrayList<>();
    private JTextField nameField, codeField, durationField, costField;
    private JTextArea contentArea, display;
    private JButton addBtn, clrBtn, vwBtn, delBtn;
    private JList<String> courseDisplay;
    private DefaultListModel<String> model;
    private Color PURPLE = new Color(106, 17, 203), BLUE = new Color(37, 117, 252), DARK = new Color(25, 25, 112), GOLD = new Color(255, 215, 0), LIGHT = new Color(240, 248, 255);
    
    public coursesinfo() { initializeGUI(); }
    
    private void initializeGUI() {
        setTitle("Student Course Registration System"); setSize(850, 900); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setLocationRelativeTo(null); setResizable(false);
        
        JPanel main = new JPanel(new BorderLayout(15, 15)) {
            protected void paintComponent(Graphics g) { Graphics2D g2 = (Graphics2D)g;
                g2.setPaint(new GradientPaint(0, 0, PURPLE, 0, getHeight(), BLUE)); g2.fillRect(0, 0, getWidth(), getHeight()); } };
        main.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JPanel header = createPanel(DARK, 20); header.setPreferredSize(new Dimension(850, 75)); header.setLayout(new BorderLayout());
        JLabel title = new JLabel("COURSE MANAGEMENT SYSTEM"); title.setForeground(GOLD); title.setFont(new Font("Arial", Font.BOLD, 22)); title.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel sub = new JLabel("Manage Your Courses"); sub.setForeground(new Color(173, 216, 230)); sub.setFont(new Font("Arial", Font.ITALIC, 11)); sub.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel titlePanel = new JPanel(new BorderLayout()); titlePanel.setOpaque(false); titlePanel.add(title, BorderLayout.CENTER); titlePanel.add(sub, BorderLayout.SOUTH); titlePanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        header.add(titlePanel, BorderLayout.CENTER);
        
        JPanel form = createPanel(LIGHT, 15); form.setLayout(new GridLayout(5, 2, 12, 12)); form.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        form.add(label("Course Name:", PURPLE)); nameField = field(); form.add(nameField);
        form.add(label("Course Code (Auto):", PURPLE)); codeField = new JTextField(); codeField.setEditable(false); codeField.setFont(new Font("Arial", Font.PLAIN, 12));
        codeField.setBackground(new Color(220, 220, 220)); codeField.setBorder(new RoundedBorderCourse(10, PURPLE)); codeField.setMargin(new Insets(8, 10, 8, 10)); form.add(codeField);
        form.add(label("Duration (e.g., 6 months):", PURPLE)); durationField = field(); form.add(durationField);
        form.add(label("Cost (₹):", PURPLE)); costField = field(); form.add(costField);
        form.add(label("Content:", PURPLE)); contentArea = new JTextArea(2, 30); contentArea.setFont(new Font("Arial", Font.PLAIN, 12));
        contentArea.setBackground(Color.WHITE); contentArea.setLineWrap(true); contentArea.setWrapStyleWord(true); contentArea.setBorder(new RoundedBorderCourse(10, PURPLE));
        JScrollPane contentScroll = new JScrollPane(contentArea); contentScroll.setBorder(BorderFactory.createEmptyBorder()); form.add(contentScroll);
        
        JPanel buttons = createPanel(LIGHT, 15); buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 12)); buttons.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
        addBtn = btn("Add Course", new Color(34, 139, 34), 20); clrBtn = btn("Clear", new Color(220, 20, 60), 20);
        vwBtn = btn("Display All", DARK, 20); delBtn = btn("Delete", new Color(178, 34, 34), 20);
        buttons.add(addBtn); buttons.add(clrBtn); buttons.add(vwBtn); buttons.add(delBtn);
        
        JPanel list = createPanel(LIGHT, 15); list.setLayout(new BorderLayout(10, 10)); list.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        JLabel listLbl = new JLabel("Courses Added"); listLbl.setFont(new Font("Arial", Font.BOLD, 13)); listLbl.setForeground(PURPLE);
        model = new DefaultListModel<>(); courseDisplay = new JList<>(model); courseDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseDisplay.setFont(new Font("Courier New", Font.PLAIN, 11)); courseDisplay.setBackground(new Color(255, 255, 240)); courseDisplay.setForeground(DARK);
        JScrollPane listScroll = new JScrollPane(courseDisplay); listScroll.setBorder(new RoundedBorderCourse(10, PURPLE));
        list.add(listLbl, BorderLayout.NORTH); list.add(listScroll, BorderLayout.CENTER);
        
        JPanel disp = createPanel(LIGHT, 15); disp.setLayout(new BorderLayout(10, 10)); disp.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        JLabel dispLbl = new JLabel("Course Information Display"); dispLbl.setFont(new Font("Arial", Font.BOLD, 13)); dispLbl.setForeground(PURPLE);
        display = new JTextArea(5, 50); display.setEditable(false); display.setFont(new Font("Courier New", Font.PLAIN, 11));
        display.setBackground(new Color(255, 255, 240)); display.setForeground(DARK);
        JScrollPane dispScroll = new JScrollPane(display); dispScroll.setBorder(new RoundedBorderCourse(10, PURPLE));
        disp.add(dispLbl, BorderLayout.NORTH); disp.add(dispScroll, BorderLayout.CENTER);
        
        JPanel bottom = new JPanel(new GridLayout(1, 2, 15, 15)); bottom.setOpaque(false); bottom.add(list); bottom.add(disp);
        JPanel top = new JPanel(new BorderLayout(15, 15)); top.setOpaque(false); top.add(form, BorderLayout.CENTER); top.add(buttons, BorderLayout.SOUTH);
        
        main.add(header, BorderLayout.NORTH); main.add(top, BorderLayout.CENTER); main.add(bottom, BorderLayout.SOUTH);
        add(main); setVisible(true);
    }
    
    private JPanel createPanel(Color bg, int r) {
        JPanel p = new JPanel() { protected void paintComponent(Graphics g) { Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); g2.setColor(bg);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), r, r); super.paintComponent(g); } };
        p.setOpaque(false); p.setBorder(new RoundedBorderCourse(r, PURPLE)); return p;
    }
    
    private JLabel label(String txt, Color c) { JLabel l = new JLabel(txt); l.setFont(new Font("Arial", Font.BOLD, 12)); l.setForeground(c); return l; }
    private JTextField field() { JTextField f = new JTextField(); f.setFont(new Font("Arial", Font.PLAIN, 12)); f.setBackground(Color.WHITE);
        f.setForeground(DARK); f.setBorder(new RoundedBorderCourse(10, PURPLE)); f.setMargin(new Insets(8, 10, 8, 10)); return f; }
    private JButton btn(String txt, Color bg, int r) { JButton b = new JButton(txt); b.setFont(new Font("Arial", Font.BOLD, 12)); b.setBackground(bg);
        b.setForeground(Color.WHITE); b.setFocusPainted(false); b.setBorder(new RoundedBorderCourse(r, bg)); b.setPreferredSize(new Dimension(140, 38));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR)); b.addActionListener(this); return b; }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) add(); else if (e.getSource() == clrBtn) clear();
        else if (e.getSource() == vwBtn) viewAll(); else if (e.getSource() == delBtn) delete();
    }
    
    private void add() {
        if (nameField.getText().isEmpty() || durationField.getText().isEmpty() || costField.getText().isEmpty() || contentArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all fields!", "Error", JOptionPane.ERROR_MESSAGE); return;
        }
        try {
            Course c = new Course(); c.setCourseName(nameField.getText()); c.setCourseDuration(durationField.getText());
            c.setCourseCost(Double.parseDouble(costField.getText())); c.setCourseContent(contentArea.getText());
            courseList.add(c); model.addElement(c.getCourseCode() + " - " + c.getCourseName());
            JOptionPane.showMessageDialog(this, "Added!", "Success", JOptionPane.INFORMATION_MESSAGE); c.displayInfo(); clear();
        } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Invalid cost!", "Error", JOptionPane.ERROR_MESSAGE); }
    }
    
    private void clear() { nameField.setText(""); durationField.setText(""); costField.setText(""); contentArea.setText(""); codeField.setText(new Course().getCourseCode()); display.setText(""); }
    
    private void viewAll() {
        if (courseList.isEmpty()) { display.setText("No courses!"); return; }
        StringBuilder s = new StringBuilder("===== ALL COURSES =====\n\n");
        for (int i = 0; i < courseList.size(); i++) {
            Course c = courseList.get(i);
            s.append(i+1).append(". ").append(c.getCourseCode()).append(" | ").append(c.getCourseName()).append(" | ")
             .append(c.getCourseDuration()).append(" | ₹").append(c.getCourseCost()).append(" | ").append(c.getCourseContent()).append("\n");
        }
        s.append("\nTotal: ").append(courseList.size()); display.setText(s.toString());
    }
    
    private void delete() {
        int idx = courseDisplay.getSelectedIndex();
        if (idx == -1) { JOptionPane.showMessageDialog(this, "Select a course!", "Error", JOptionPane.WARNING_MESSAGE); return; }
        Course c = courseList.remove(idx); model.remove(idx);
        JOptionPane.showMessageDialog(this, "Deleted: " + c.getCourseName(), "Success", JOptionPane.INFORMATION_MESSAGE); display.setText("");
    }
    
    public static void main(String[] args) { } // Entry point disabled - use LOGINPAGE.java to start
}
