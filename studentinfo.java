package projects.javaproject;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class RoundedBorderStudent extends AbstractBorder {
    private int radius; private Color color;
    public RoundedBorderStudent(int r, Color c) { this.radius = r; this.color = c; }
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        Graphics2D g2 = (Graphics2D)g; g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color); g2.setStroke(new BasicStroke(2)); g2.drawRoundRect(x, y, w-1, h-1, radius, radius);
    }
}

interface StudentInterface {
    String getStudentName(); void setStudentName(String n);
    String getStudentId(); void setStudentId(String id);
    String getDateOfBirth(); void setDateOfBirth(String d);
    int getAge(); void setAge(int a);
    String getGmail(); void setGmail(String g);
    String getFatherName(); void setFatherName(String f);
    String getDepartment(); void setDepartment(String d);
    String getGender(); void setGender(String g);
    void displayInfo();
}

class Student implements StudentInterface {
    private String studentName, studentId, dateOfBirth, gmail, fatherName, department, gender;
    private int age;
    private static int idCounter = 100;
    
    public Student() { this.studentId = "STU" + (idCounter++); }
    
    public String getStudentName() { return studentName; }
    public void setStudentName(String n) { this.studentName = n; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String id) { this.studentId = id; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String d) { this.dateOfBirth = d; }
    public int getAge() { return age; }
    public void setAge(int a) { this.age = a; }
    public String getGmail() { return gmail; }
    public void setGmail(String g) { this.gmail = g; }
    public String getFatherName() { return fatherName; }
    public void setFatherName(String f) { this.fatherName = f; }
    public String getDepartment() { return department; }
    public void setDepartment(String d) { this.department = d; }
    public String getGender() { return gender; }
    public void setGender(String g) { this.gender = g; }
    public void displayInfo() {
        System.out.println("\n===== STUDENT INFO =====\nName: " + studentName + "\nID: " + studentId + "\nDOB: " + dateOfBirth + "\nAge: " + age + 
            "\nGmail: " + gmail + "\nFather: " + fatherName + "\nDept: " + department + "\nGender: " + gender + "\n=======================\n");
    }
}

public class studentinfo extends JFrame implements ActionListener {
    private Student student;
    private JTextField nameField, idField, dobField, ageField, gmailField, fatherNameField;
    private JComboBox<String> departmentCombo, genderCombo;
    private JButton submitBtn, clearBtn, displayBtn;
    private JTextArea infoDisplay;
    private Color PURPLE = new Color(106, 17, 203), BLUE = new Color(37, 117, 252), DARK = new Color(25, 25, 112), GOLD = new Color(255, 215, 0), LIGHT = new Color(240, 248, 255);
    
    public studentinfo() { student = new Student(); initializeGUI(); }
    
    private void initializeGUI() {
        setTitle("Student Course Registration System"); setSize(650, 750); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setLocationRelativeTo(null); setResizable(false);
        
        JPanel main = new JPanel(new BorderLayout(15, 15)) {
            protected void paintComponent(Graphics g) { Graphics2D g2 = (Graphics2D)g;
                g2.setPaint(new GradientPaint(0, 0, PURPLE, 0, getHeight(), BLUE)); g2.fillRect(0, 0, getWidth(), getHeight()); } };
        main.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JPanel header = createPanel(DARK, 20); header.setPreferredSize(new Dimension(650, 70)); header.setLayout(new BorderLayout());
        JLabel title = new JLabel("STUDENT INFORMATION FORM"); title.setForeground(GOLD); title.setFont(new Font("Arial", Font.BOLD, 22)); title.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel sub = new JLabel("Manage Student Records"); sub.setForeground(new Color(173, 216, 230)); sub.setFont(new Font("Arial", Font.ITALIC, 11)); sub.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel titlePanel = new JPanel(new BorderLayout()); titlePanel.setOpaque(false); titlePanel.add(title, BorderLayout.CENTER); titlePanel.add(sub, BorderLayout.SOUTH); titlePanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        header.add(titlePanel, BorderLayout.CENTER);
        
        JPanel form = createPanel(LIGHT, 15); form.setLayout(new GridLayout(9, 2, 12, 12)); form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        form.add(label("Student Name:", PURPLE)); nameField = field(); form.add(nameField);
        form.add(label("Student ID (Auto):", PURPLE)); idField = new JTextField(student.getStudentId()); idField.setEditable(false); idField.setFont(new Font("Arial", Font.PLAIN, 12));
        idField.setBackground(new Color(220, 220, 220)); idField.setBorder(new RoundedBorderStudent(10, PURPLE)); idField.setMargin(new Insets(8, 10, 8, 10)); form.add(idField);
        form.add(label("Date of Birth (DD/MM/YYYY):", PURPLE)); dobField = field(); form.add(dobField);
        form.add(label("Age:", PURPLE)); ageField = field(); form.add(ageField);
        form.add(label("Gmail:", PURPLE)); gmailField = field(); form.add(gmailField);
        form.add(label("Father Name:", PURPLE)); fatherNameField = field(); form.add(fatherNameField);
        form.add(label("Department:", PURPLE)); departmentCombo = combo(new String[]{"Select Dept", "CS", "Electronics", "Mechanical", "Civil", "Electrical", "IT"}); form.add(departmentCombo);
        form.add(label("Gender:", PURPLE)); genderCombo = combo(new String[]{"Select", "Male", "Female", "Other"}); form.add(genderCombo);
        
        JPanel buttons = createPanel(LIGHT, 15); buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 12)); buttons.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
        submitBtn = btn("Submit", new Color(34, 139, 34), 20); clearBtn = btn("Clear", new Color(220, 20, 60), 20); displayBtn = btn("Display Info", DARK, 20);
        buttons.add(submitBtn); buttons.add(clearBtn); buttons.add(displayBtn);
        
        JPanel disp = createPanel(LIGHT, 15); disp.setLayout(new BorderLayout(10, 10)); disp.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        JLabel dispLbl = new JLabel("Display Information"); dispLbl.setFont(new Font("Arial", Font.BOLD, 13)); dispLbl.setForeground(PURPLE);
        infoDisplay = new JTextArea(6, 40); infoDisplay.setEditable(false); infoDisplay.setFont(new Font("Courier New", Font.PLAIN, 11));
        infoDisplay.setBackground(new Color(255, 255, 240)); infoDisplay.setForeground(DARK);
        JScrollPane scroll = new JScrollPane(infoDisplay); scroll.setBorder(new RoundedBorderStudent(10, PURPLE));
        disp.add(dispLbl, BorderLayout.NORTH); disp.add(scroll, BorderLayout.CENTER);
        
        JPanel bottom = new JPanel(new BorderLayout(15, 15)); bottom.setOpaque(false); bottom.add(buttons, BorderLayout.NORTH); bottom.add(disp, BorderLayout.CENTER);
        main.add(header, BorderLayout.NORTH); main.add(form, BorderLayout.CENTER); main.add(bottom, BorderLayout.SOUTH);
        add(main); setVisible(true);
    }
    
    private JPanel createPanel(Color bg, int r) {
        JPanel p = new JPanel() { protected void paintComponent(Graphics g) { Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); g2.setColor(bg);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), r, r); super.paintComponent(g); } };
        p.setOpaque(false); p.setBorder(new RoundedBorderStudent(r, PURPLE)); return p;
    }
    
    private JLabel label(String txt, Color c) { JLabel l = new JLabel(txt); l.setFont(new Font("Arial", Font.BOLD, 12)); l.setForeground(c); return l; }
    private JTextField field() { JTextField f = new JTextField(); f.setFont(new Font("Arial", Font.PLAIN, 12)); f.setBackground(Color.WHITE);
        f.setForeground(DARK); f.setBorder(new RoundedBorderStudent(10, PURPLE)); f.setMargin(new Insets(8, 10, 8, 10)); return f; }
    private JComboBox<String> combo(String[] items) { JComboBox<String> c = new JComboBox<>(items); c.setFont(new Font("Arial", Font.PLAIN, 12)); c.setBackground(Color.WHITE);
        c.setForeground(DARK); c.setBorder(new RoundedBorderStudent(10, PURPLE)); return c; }
    private JButton btn(String txt, Color bg, int r) { JButton b = new JButton(txt); b.setFont(new Font("Arial", Font.BOLD, 12)); b.setBackground(bg);
        b.setForeground(Color.WHITE); b.setFocusPainted(false); b.setBorder(new RoundedBorderStudent(r, bg)); b.setPreferredSize(new Dimension(120, 38));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR)); b.addActionListener(this); return b; }
    
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == submitBtn) submit(); else if (e.getSource() == clearBtn) clear(); else if (e.getSource() == displayBtn) display();
    }
    
    private void submit() {
        if (nameField.getText().isEmpty() || dobField.getText().isEmpty() || ageField.getText().isEmpty() || gmailField.getText().isEmpty() || 
            fatherNameField.getText().isEmpty() || departmentCombo.getSelectedIndex() == 0 || genderCombo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Fill all fields!", "Error", JOptionPane.ERROR_MESSAGE); return;
        }
        try {
            student.setStudentName(nameField.getText()); student.setDateOfBirth(dobField.getText()); student.setAge(Integer.parseInt(ageField.getText())); student.setGmail(gmailField.getText());
            student.setFatherName(fatherNameField.getText()); student.setDepartment((String)departmentCombo.getSelectedItem()); student.setGender((String)genderCombo.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Success!", "OK", JOptionPane.INFORMATION_MESSAGE); student.displayInfo();
        } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Age must be number!", "Error", JOptionPane.ERROR_MESSAGE); }
    }
    
    private void clear() { nameField.setText(""); dobField.setText(""); ageField.setText(""); gmailField.setText(""); fatherNameField.setText("");
        departmentCombo.setSelectedIndex(0); genderCombo.setSelectedIndex(0); infoDisplay.setText(""); student = new Student(); idField.setText(student.getStudentId()); }
    
    private void display() {
        StringBuilder s = new StringBuilder("===== STUDENT INFO =====\nName: "); s.append(student.getStudentName()).append("\nID: ").append(student.getStudentId())
         .append("\nDOB: ").append(student.getDateOfBirth()).append("\nAge: ").append(student.getAge()).append("\nGmail: ").append(student.getGmail()).append("\nFather: ").append(student.getFatherName())
         .append("\nDept: ").append(student.getDepartment()).append("\nGender: ").append(student.getGender()).append("\n=======================");
        infoDisplay.setText(s.toString());
    }
    
    public static void main(String[] args) { } // Entry point disabled - use LOGINPAGE.java to start
}
