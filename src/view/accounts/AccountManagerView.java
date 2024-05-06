package view.accounts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.employee.EmployeePosition;
import util.ColorHelper;
import util.LayoutHelper;

public class AccountManagerView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pnlView;
	private JButton btn_addAccount;
	private JButton btn_removeAccount;
	private JButton btn_updateAccount;
	private JButton btn_formEmpty;
	private JTable tableAccount;
	private DefaultTableModel dfTableAccount;
	private JTextField txt_eID;
	private JTextField txt_username;
	private JTextField txt_password;
	private JComboBox<String> cbb_permission;

	public AccountManagerView() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// set panel view
		pnlView = LayoutHelper.getBorderLayout();
		
		// set north panel view
		this.pnlView.add(setTablePanel(), BorderLayout.NORTH);
		
		// set center view
		this.pnlView.add(setAccountInfoPanel(), BorderLayout.CENTER);
		
		// set south view
		this.pnlView.add(setBtnPanel(), BorderLayout.SOUTH);
		
		this.add(pnlView);
	}
	
	public static void main(String[] args) {
		new AccountManagerView().setVisible(true);
	}
	
	private JPanel setBtnPanel() {
		JPanel btnPanel = new JPanel();
		
		btn_addAccount = LayoutHelper.setupBtn("Thêm", "src/Images/btn_icon/add_icon.png");
		btn_removeAccount = LayoutHelper.setupBtn("Xóa", "src/Images/btn_icon/remove_icon.png");
		btn_updateAccount = LayoutHelper.setupBtn("Sửa", "src/Images/btn_icon/update_icon.png");
		btn_formEmpty = LayoutHelper.setupBtn("Xóa rỗng", "src/Images/btn_icon/reset_icon.png");
		
		btnPanel.add(btn_addAccount);
		btnPanel.add(btn_removeAccount);
		btnPanel.add(btn_updateAccount);
		btnPanel.add(btn_formEmpty);
		
		return btnPanel;
	}
	
	private JPanel setTablePanel() {
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setPreferredSize(new Dimension(0, 300));
		
		tablePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Danh sách tài khoản"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		String[] columns = {"Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Nhóm quyền"};
		dfTableAccount = new DefaultTableModel(columns, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 2439651786028479492L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
		tableAccount = new JTable(dfTableAccount);
		
		tablePanel.add(new JScrollPane(tableAccount));
		
		return tablePanel;
	}
	
	private JPanel setAccountInfoPanel() {
        JPanel accountInfoPanel = new JPanel();
        accountInfoPanel.setLayout(new GridBagLayout());
        accountInfoPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin tài khoản"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets.set(5, 5, 5, 5);

        // eID and email
        // x 0 y 0
        accountInfoPanel.add(LayoutHelper.setLabel("Mã nhân viên:"), gbc);
        // x 1 y 0
        gbc.gridx++;
        txt_eID = LayoutHelper.getTextField(20);
        accountInfoPanel.add(txt_eID, gbc);
        
        // x 2 y 0
        gbc.gridx++;
        accountInfoPanel.add(LayoutHelper.setLabel("Email:"), gbc);
        
        // x 3 y 0
        gbc.gridx++;
        cbb_permission = new JComboBox<String>();
        cbb_permission.addItem(EmployeePosition.MANAGER.toString());
        cbb_permission.addItem(EmployeePosition.SALE_EMPLOYEE.toString());
        accountInfoPanel.add(cbb_permission, gbc);

        // box last name, first name and gender
        // x 0 y 1
        gbc.gridy++;
        gbc.gridx = 0;
        accountInfoPanel.add(LayoutHelper.setLabel("Tên đăng nhập:"), gbc);
        // x 1 y 1
        gbc.gridx++;
        txt_username = LayoutHelper.getTextField(20);
        accountInfoPanel.add(txt_username, gbc);
        // x 2 y 1
        gbc.gridx++;
        accountInfoPanel.add(LayoutHelper.setLabel("Mật khẩu:"), gbc);
        // x 3 y 1
        gbc.gridx++;
        txt_password = LayoutHelper.getTextField(20);
        accountInfoPanel.add(txt_password, gbc);

        return accountInfoPanel;
    }

	public JPanel getPnlView() {
		return pnlView;
	}

	public void setPnlView(JPanel pnlView) {
		this.pnlView = pnlView;
	}

	public JButton getBtn_addAccount() {
		return btn_addAccount;
	}

	public void setBtn_addAccount(JButton btn_addAccount) {
		this.btn_addAccount = btn_addAccount;
	}

	public JButton getBtn_removeAccount() {
		return btn_removeAccount;
	}

	public void setBtn_removeAccount(JButton btn_removeAccount) {
		this.btn_removeAccount = btn_removeAccount;
	}

	public JButton getBtn_updateAccount() {
		return btn_updateAccount;
	}

	public void setBtn_updateAccount(JButton btn_updateAccount) {
		this.btn_updateAccount = btn_updateAccount;
	}

	public JButton getBtn_formEmpty() {
		return btn_formEmpty;
	}

	public void setBtn_formEmpty(JButton btn_formEmpty) {
		this.btn_formEmpty = btn_formEmpty;
	}

	public JTable getTableAccount() {
		return tableAccount;
	}

	public void setTableAccount(JTable tableAccount) {
		this.tableAccount = tableAccount;
	}

	public DefaultTableModel getDfTableAccount() {
		return dfTableAccount;
	}

	public void setDfTableAccount(DefaultTableModel dfTableAccount) {
		this.dfTableAccount = dfTableAccount;
	}

	public JTextField getTxt_eID() {
		return txt_eID;
	}

	public void setTxt_eID(JTextField txt_eID) {
		this.txt_eID = txt_eID;
	}

	public JTextField getTxt_username() {
		return txt_username;
	}

	public void setTxt_username(JTextField txt_username) {
		this.txt_username = txt_username;
	}

	public JTextField getTxt_password() {
		return txt_password;
	}

	public void setTxt_password(JTextField txt_password) {
		this.txt_password = txt_password;
	}

	public JComboBox<String> getCbb_permission() {
		return cbb_permission;
	}

	public void setCbb_permission(JComboBox<String> cbb_permission) {
		this.cbb_permission = cbb_permission;
	}
	
	
}
