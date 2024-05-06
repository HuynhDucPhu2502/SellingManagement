package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.accounts.Account;
import model.accounts.AccountDAO;
import view.base.AuthFrame;
import view.base.MainFrame;

public class AuthFrameController implements ActionListener {
	private AuthFrame authFrame;

	public AuthFrameController(AuthFrame authFrame) {
		this.authFrame = authFrame;
		
		register();
	}
	
	private void register() {
		authFrame.getUsernameTxtField().addActionListener(this);
		authFrame.getPasswordTxtField().addActionListener(this);
		authFrame.getLoginBtn().addActionListener(this);
		authFrame.getExitBtn().addActionListener(this);
		authFrame.getShowPasswordCBox().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		try {
			if (source == authFrame.getUsernameTxtField() ||  source == authFrame.getPasswordTxtField()
				|| source == authFrame.getLoginBtn()) handleLogin();
			else if (source == authFrame.getExitBtn()) System.exit(0);
			else if (source == authFrame.getShowPasswordCBox()) handleShowPassword();
		} catch (Exception exception) {
			JOptionPane.showOptionDialog(null, exception.getMessage(), "Hệ thống", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
					null, null, null);
		}
	}
	
	private void handleLogin() {
		String username = authFrame.getUsernameTxtField().getText().trim();
		String password = String.valueOf(authFrame.getPasswordTxtField().getPassword());
		
		Account account = AccountDAO.getLogin(username, password);
		
		if (account == null)
			throw new IllegalArgumentException("Tài khoản hoặc mật khẩu không đúng");
		else {
			new MainFrame(account);
			authFrame.dispose();
		}
	}
	
	private void handleShowPassword() {
		if (authFrame.getShowPasswordCBox().isSelected()) 
			authFrame.getPasswordTxtField().setEchoChar((char) 0);
        else
        	authFrame.getPasswordTxtField().setEchoChar('*');
	}
	
	
}
