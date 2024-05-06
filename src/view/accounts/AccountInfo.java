package view.accounts;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.accounts.Account;
import util.LayoutHelper;
import view.base.MainFrame;

public class AccountInfo extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4709024065355308559L;
	private Account account;
	

	public AccountInfo(Account account, MainFrame mainFrame) {
		this.account = account;
		
		setTitle("Thông tin người dùng");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(LayoutHelper.getLogo());
		setSize(500, 300);
		
		add(setupInfoPanel(), BorderLayout.CENTER);
		setVisible(true);
	}
	
	private JPanel setupInfoPanel() {
		JPanel infoPanel = LayoutHelper.getGridBagLayout();
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		
		LayoutHelper.addItem(0, 0, 0, 0, new JLabel("Tài khoản:"), infoPanel, gbc);
		LayoutHelper.addItem(1, 0, 1, 0, LayoutHelper.getNonEditableTextField(10, account.getUsername()), infoPanel, gbc);
		LayoutHelper.addItem(2, 0, 0, 0, new JLabel("Mật khẩu:"), infoPanel, gbc);
		LayoutHelper.addItem(3, 0, 1, 0,  LayoutHelper.getNonEditableTextField(10, account.getPassword()), infoPanel, gbc);
		
		LayoutHelper.addItem(0, 1, 0, 0, new JLabel("Mã nhân viên:"), infoPanel, gbc);
		LayoutHelper.addItem(1, 1, 1, 0, LayoutHelper.getNonEditableTextField(10, account.getEmployee().getEmployeeID()), infoPanel, gbc);
		LayoutHelper.addItem(2, 1, 0, 0, new JLabel("Địa chỉ:"), infoPanel, gbc);
		LayoutHelper.addItem(3, 1, 1, 0,  LayoutHelper.getNonEditableTextField(10, account.getEmployee().getAddress()), infoPanel, gbc);
		
		LayoutHelper.addItem(0, 2, 0, 0, new JLabel("Họ:"), infoPanel, gbc);
		LayoutHelper.addItem(1, 2, 1, 0, LayoutHelper.getNonEditableTextField(10, account.getEmployee().getFirstName()), infoPanel, gbc);
		LayoutHelper.addItem(2, 2, 0, 0, new JLabel("Tên:"), infoPanel, gbc);
		LayoutHelper.addItem(3, 2, 1, 0,  LayoutHelper.getNonEditableTextField(10, account.getEmployee().getLastName()), infoPanel, gbc);
		
		LayoutHelper.addItem(0, 3, 0, 0, new JLabel("Số điện thoại:"), infoPanel, gbc);
		LayoutHelper.addItem(1, 3, 1, 0, LayoutHelper.getNonEditableTextField(10, account.getEmployee().getPhoneNumber()), infoPanel, gbc);
		LayoutHelper.addItem(2, 3, 0, 0, new JLabel("Email:"), infoPanel, gbc);
		LayoutHelper.addItem(3, 3, 1, 0,  LayoutHelper.getNonEditableTextField(10, account.getEmployee().getEmail()), infoPanel, gbc);
		
		LayoutHelper.addItem(0, 4, 0, 0, new JLabel("Vị trí:"), infoPanel, gbc);
		LayoutHelper.addItem(1, 4, 1, 0, LayoutHelper.getNonEditableTextField(10, account.getEmployee().getPosition().toString()), infoPanel, gbc);
		LayoutHelper.addItem(2, 4, 0, 0, new JLabel("Ngày sinh:"), infoPanel, gbc);
		LayoutHelper.addItem(3, 4, 1, 0,  LayoutHelper.getNonEditableTextField(10, account.getEmployee().getBirthDay().toString()), infoPanel, gbc);
		
		LayoutHelper.addItem(0, 5, 0, 0, new JLabel("Giới tính:"), infoPanel, gbc);
		LayoutHelper.addItem(1, 5, 1, 0, LayoutHelper.getNonEditableTextField(10, account.getEmployee().getGender().toString()), infoPanel, gbc);
		
		return infoPanel;
	}

	
	
}
