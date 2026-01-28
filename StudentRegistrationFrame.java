import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Calendar;
import java.util.Date;

// --- Custom Rounded Border Class ---
class RoundedBorder extends AbstractBorder {
    private int radius;
    private Color color;
    RoundedBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        // Draw the rounded rectangle border
        g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius/2, this.radius/2, this.radius/2, this.radius/2);
    }
}

// --- Main Registration Frame ---
public class StudentRegistrationFrame extends JFrame {
    private JTextField txtId, txtName, txtAge, txtContact, txtAddress;
    private JRadioButton rbMale, rbFemale;
    private JSpinner dateSpinner;
    private JButton btnRegister;

    public StudentRegistrationFrame() {
        // Frame Setup
        setTitle("Project 3: Student Course Registration System");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full Screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Centering Layout
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        // Styling Constants
        RoundedBorder fieldBorder = new RoundedBorder(20, new Color(180, 180, 180));
        Font labelFont = new Font("SansSerif", Font.BOLD, 14);

        // Central Panel
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridLayout(9, 2, 15, 20));
        centralPanel.setPreferredSize(new Dimension(650, 750));
        centralPanel.setBackground(Color.WHITE);
        centralPanel.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(30, Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));

        // 1. Student ID
        addStyledLabel(centralPanel, "Student ID:", labelFont);
        txtId = new JTextField();
        txtId.setBorder(fieldBorder);
        centralPanel.add(txtId);

        // 2. Student Name
        addStyledLabel(centralPanel, "Student Name:", labelFont);
        txtName = new JTextField();
        txtName.setBorder(fieldBorder);
        centralPanel.add(txtName);

        // 3. Gender (Radio Buttons)
        addStyledLabel(centralPanel, "Gender:", labelFont);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(Color.WHITE);
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        centralPanel.add(genderPanel);

        // 4. Date of Birth (Calendar Spinner)
        addStyledLabel(centralPanel, "Date of Birth:", labelFont);
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setBorder(fieldBorder);
        centralPanel.add(dateSpinner);

        // 5. Age
        addStyledLabel(centralPanel, "Age:", labelFont);
        txtAge = new JTextField();
        txtAge.setBorder(fieldBorder);
        centralPanel.add(txtAge);

        // 6. Contact Number
        addStyledLabel(centralPanel, "Contact Number:", labelFont);
        txtContact = new JTextField();
        txtContact.setBorder(fieldBorder);
        centralPanel.add(txtContact);

        // 7. Address
        addStyledLabel(centralPanel, "Address:", labelFont);
        txtAddress = new JTextField();
        txtAddress.setBorder(fieldBorder);
        centralPanel.add(txtAddress);

        // 8. Register Button
        btnRegister = new JButton("REGISTER STUDENT");
        btnRegister.setBackground(new Color(70, 130, 180));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnRegister.setFocusPainted(false);
        centralPanel.add(new JLabel("")); // Spacer
        centralPanel.add(btnRegister);

        // Add to main frame
        add(centralPanel);

        // Action Listener
        btnRegister.addActionListener(e -> {
            String name = txtName.getText();
            if(name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter at least a name.");
            } else {
                JOptionPane.showMessageDialog(this, "Data captured for: " + name);
            }
        });
    }

    private void addStyledLabel(JPanel panel, String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        panel.add(label);
    }

    public static void main(String[] args) {
        // Ensure UI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new StudentRegistrationFrame().setVisible(true);
        });
    }
} 

