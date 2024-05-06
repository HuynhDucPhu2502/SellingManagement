package util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class LayoutHelper {
	private static final Image logo = new ImageIcon("src/Images/iuh_logo.png").getImage();
	
	public static void addItem(int x, int y, int weightX, int weightY, JComponent component, JComponent panel, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = weightX;
        gbc.weighty = weightY;
        panel.add(component, gbc);
    }

    public static GridBagConstraints getGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        return gbc;
    }

    public static JPanel getGridBagLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        return panel;
    }

    public static JPanel getBoxLayout(int mode) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, mode));
        return panel;
    }

    public static JPanel getBorderLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        return panel;
    }

    public static JTabbedPane getTabbedPane() {
        JTabbedPane panel = new JTabbedPane();
        panel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        panel.setForeground(ColorHelper.getDarkerPrimaryColor());

        return panel;
    }
    public static JButton setupBtn(String title, String iconPath) {
        JButton btn = new JButton(title);
        btn.setFocusable(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(ColorHelper.getInfoColor());


        ImageIcon icon = new ImageIcon(iconPath);
        btn.setIcon(icon);

        return btn;
    }

    public static JLabel getTitle(String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        label.setHorizontalAlignment(JLabel.CENTER);

        return label;
    }

    public static JLabel setLabel(String title) {
        JLabel label = new JLabel(title);
        label.setForeground(ColorHelper.getPrimaryColor());
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        return label;
    }


    public static JTextField getTextField(int length) {
        JTextField textField = new JTextField(length);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor()),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        return textField;
    }
    
    public static JTextField getNonEditableTextField(int length, String textContent) {
    	JTextField textField = new JTextField(length);
    	textField.setEditable(false);
    	textField.setText(textContent);
    	textField.setFocusable(false);
    	
    	return textField;
    }

	public static Image getLogo() {
		return logo;
	}
    
    
    
}
