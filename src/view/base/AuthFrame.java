package view.base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.AuthFrameController;
import util.LayoutHelper;

public class AuthFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7112161527964398198L;
	private JTextField usernameTxtField;
	private JPasswordField passwordTxtField;
	
	private JButton loginBtn;
	private JButton exitBtn;
	
	private JCheckBox showPasswordCBox;
	
	private AuthFrameController authController;
	
	private Image background;
	
	public AuthFrame() throws HeadlessException {
		this("Quản lý bán hàng cửa hàng tiện lợi");
	}
	
	public AuthFrame(String title) throws HeadlessException {
	    super(title);

	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setIconImage(LayoutHelper.getLogo());

	    // SET BACKGROUND
	    setupBackgroundImage();
	    BackgroundPanel backgroundPanel = new BackgroundPanel(setupBackgroundImage());
	    setLocationRelativeTo(null);
	    
	    
	    // CONTENT
	    JPanel container = new JPanel(new GridBagLayout());
	    container.setOpaque(false);
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    
	    container.add(setupMainPanel(), gbc);
	    
	    setContentPane(backgroundPanel);
	    backgroundPanel.setLayout(new BorderLayout());
	    backgroundPanel.add(container, BorderLayout.CENTER);

	    // CONTROLLER
	    authController = new AuthFrameController(this);
	    
	    // SET SIZE
	    setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

	    setVisible(true);
	}
	
	private Image setupBackgroundImage() {
		try {
            background = ImageIO.read(new File("src/images/auth_background.png"));
            return background;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
		
		return null;
	}

	
	private JPanel setupMainPanel() {
		JPanel mainPanel = LayoutHelper.getBorderLayout();
		
		mainPanel.add(setupAuthPanel(), BorderLayout.CENTER);
		mainPanel.add(setupBtnPanel(), BorderLayout.SOUTH);
		mainPanel.add(setupTitlePanel(), BorderLayout.NORTH);
		
		return mainPanel;
	}
	
	private JPanel setupAuthPanel() {
		JPanel authPanel = LayoutHelper.getGridBagLayout();
		authPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		authPanel.setOpaque(false);
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		
		LayoutHelper.addItem(0, 0, 0, 0, new JLabel("Tài khoản"), authPanel, gbc);
		LayoutHelper.addItem(0, 1, 0, 0, new JLabel("Mật khẩu"), authPanel, gbc);
		
		LayoutHelper.addItem(1, 0, 0, 0, usernameTxtField = new JTextField(20), authPanel, gbc);
		LayoutHelper.addItem(1, 1, 0, 0, passwordTxtField = new JPasswordField(20), authPanel, gbc);
		
		gbc.gridwidth = 2;
		JPanel subPanel = new JPanel();
		subPanel.add(new JLabel("Hiện mật khẩu"));
		subPanel.add(showPasswordCBox = new JCheckBox());
		subPanel.setOpaque(false);
		
		LayoutHelper.addItem(0, 2, 1, 0, subPanel, authPanel, gbc);
		
		
		return authPanel;
	}
	
	private JPanel setupBtnPanel() {
		JPanel btnPanel = LayoutHelper.getGridBagLayout();
		btnPanel.setPreferredSize(new Dimension(0, 75));
		btnPanel.setOpaque(false);
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		
		LayoutHelper.addItem(0, 0, 0, 0, loginBtn = LayoutHelper.setupBtn("Đăng nhập", "src/Images/auth_icon/login_icon.png"), btnPanel, gbc);
		LayoutHelper.addItem(1, 0, 0, 0, exitBtn = LayoutHelper.setupBtn("Thoát", "src/Images/auth_icon/exit_icon.png"), btnPanel, gbc);
		
		loginBtn.setBackground(Color.GREEN);
		exitBtn.setBackground(Color.RED);
		
		return btnPanel;
	}
	
	private JPanel setupTitlePanel() {
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		
		JLabel title = LayoutHelper.getTitle("Quản Lý Bán Hàng");
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		title.setForeground(Color.RED);
		
		title.setIcon(new ImageIcon("src/Images/auth_icon/top_icon.png"));
	    title.setHorizontalAlignment(JLabel.CENTER);
	    title.setVerticalAlignment(JLabel.TOP);
	    title.setVerticalTextPosition(JLabel.BOTTOM);
	    title.setHorizontalTextPosition(JLabel.CENTER);
	    
	    titlePanel.add(title);
		
		return titlePanel;
	}

	public JTextField getUsernameTxtField() {
		return usernameTxtField;
	}

	public void setUsernameTxtField(JTextField usernameTxtField) {
		this.usernameTxtField = usernameTxtField;
	}

	public JPasswordField getPasswordTxtField() {
		return passwordTxtField;
	}

	public void setPasswordTxtField(JPasswordField passwordTxtField) {
		this.passwordTxtField = passwordTxtField;
	}

	public JButton getLoginBtn() {
		return loginBtn;
	}

	public void setLoginBtn(JButton loginBtn) {
		this.loginBtn = loginBtn;
	}

	public JButton getExitBtn() {
		return exitBtn;
	}

	public void setExitBtn(JButton exitBtn) {
		this.exitBtn = exitBtn;
	}

	public AuthFrameController getAuthController() {
		return authController;
	}

	public void setAuthController(AuthFrameController authController) {
		this.authController = authController;
	}

	public JCheckBox getShowPasswordCBox() {
		return showPasswordCBox;
	}

	public void setShowPasswordCBox(JCheckBox showPasswordCBox) {
		this.showPasswordCBox = showPasswordCBox;
	}
	
	
	// BackgroundPanel class with overridden paintComponent
	class BackgroundPanel extends JPanel {
        private static final long serialVersionUID = -8603153054478398214L;
        private Image background;

        public BackgroundPanel(Image background) {
            this.background = background;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background != null) {
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        }

        
        
    }
	
	
	
	
	
	
}



