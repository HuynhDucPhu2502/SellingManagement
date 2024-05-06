package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import model.employee.Employee;
import model.employee.EmployeeDAO;
import util.FetchDataStatus;
import model.accounts.Account;
import model.accounts.AccountDAO;
import view.base.MainFrame;
import view.accounts.AccountManagerView;

public class AccountManagerController implements ActionListener{
	
	private static AccountManagerView userManagerView;
	
	public AccountManagerController(AccountManagerView userManagerView) {
		AccountManagerController.userManagerView = userManagerView;
		
		// load data to table
		loadDataToTable(FetchDataStatus.getAccountData());
		
		// add action
		this.addAction();
	}
	
	private void addAction() {
		userManagerView.getBtn_addAccount().addActionListener(this);
		userManagerView.getBtn_removeAccount().addActionListener(this);
		userManagerView.getBtn_updateAccount().addActionListener(this);
		userManagerView.getBtn_formEmpty().addActionListener(this);
		setupTableSelection();
	}
	
	public static void resetAll() {
		loadDataToTable(FetchDataStatus.getAccountData());
	}
	
	public static void loadDataToTable(List<Account> listAccount) {
		userManagerView.getDfTableAccount().setRowCount(0);
		for(Account a : listAccount) {
			userManagerView.getDfTableAccount().addRow(new Object[] {
					a.getEmployee().getEmployeeID(),a.getUsername(), a.getPassword(), 
					a.getEmployee().getPosition().toString()
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(userManagerView.getBtn_formEmpty())) this.resetForm();
		else if(o.equals(userManagerView.getBtn_updateAccount())) { 
			int check = JOptionPane.showConfirmDialog(null, "Có chắc muốn sửa thông tin tài khoản", "Cảnh báo", JOptionPane.YES_NO_OPTION);
			if(check == JOptionPane.NO_OPTION)
				return;
			try {
				if(this.updateAccount()) {
					JOptionPane.showMessageDialog(null, "Sửa thông tin tài khoản thành công");
					loadDataToTable(FetchDataStatus.getAccountData());
				}
				this.resetForm();
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
			
		}
		else if(o.equals(userManagerView.getBtn_addAccount())) {
			try {
				if(this.addAccount()) {
					JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công");
					loadDataToTable(FetchDataStatus.getAccountData());
				}else JOptionPane.showMessageDialog(null, "Thêm tài khoản thất bại");
				this.resetForm();
			} catch (Exception e3) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e3.getMessage());
			}
		}
		else if(o.equals(userManagerView.getBtn_removeAccount())) {
			int check = JOptionPane.showConfirmDialog(null, "Có chắc muốn xóa tài khoản", "Cảnh báo", JOptionPane.YES_NO_OPTION);
			if(check == JOptionPane.NO_OPTION)
				return;
			try {
				if(this.removeAccount()) {
					JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công");
					loadDataToTable(FetchDataStatus.getAccountData());
				}
				this.resetForm();
			} catch (Exception e4) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e4.getMessage());
			}
		}
		
	}
	
	private void setupTableSelection() {
		userManagerView.getTableAccount().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userManagerView.getTableAccount().getSelectionModel().addListSelectionListener(event -> {
			if (!event.getValueIsAdjusting()) {
				int index = userManagerView.getTableAccount().getSelectedRow();
				if (index != -1) showTxtField(index);
				else resetForm();
			}
		});
	}
	
	private void resetForm() {
		userManagerView.getTxt_eID().setEditable(true);
		userManagerView.getTxt_eID().setText(null);
		userManagerView.getTxt_password().setText(null);
		userManagerView.getTxt_username().setText(null);
		userManagerView.getCbb_permission().setEditable(true);
		userManagerView.getCbb_permission().setSelectedIndex(0);
		userManagerView.getTxt_eID().requestFocus();
		userManagerView.getTableAccount().setRowSorter(null);
	}
	
	private void showTxtField(int index) {
		String username = userManagerView.getTableAccount().getValueAt(index, 1).toString();
		String password = userManagerView.getTableAccount().getValueAt(index, 2).toString();
		Account a = AccountDAO.getLogin(username, password);
		
		userManagerView.getTxt_eID().setEditable(false);
		userManagerView.getTxt_eID().setText(a.getEmployee().getEmployeeID());
		userManagerView.getTxt_password().setText(a.getPassword());
		userManagerView.getTxt_username().setText(a.getUsername());
		userManagerView.getCbb_permission().setEditable(false);
		userManagerView.getCbb_permission().setSelectedItem(a.getEmployee().getPosition().toString());
	}
	
	private boolean removeAccount() {
		int index = -1;
		index = userManagerView.getTableAccount().getSelectedRow();
		if(index < 0) throw new IllegalStateException("Chưa chọn đối tượng để xóa");
		
		String id = userManagerView.getTxt_eID().getText();
		
		if(id.equalsIgnoreCase(MainFrame.getAccount().getEmployee().getEmployeeID()))
			throw new IllegalStateException("Không thể xóa - tài khoản đang hoạt động");
			
		return AccountDAO.removeAccount(id);
	}
	
	private boolean addAccount() {
		Account a = null;
		try {
			a = this.createAccount();
		} catch (Exception e) {
			// TODO: handle exception
			throw new IllegalStateException(e.getMessage());
		}
		if(a == null) return false;
		
		return AccountDAO.insertAccount(a);
	}
	
	private Account createAccount() {
		String id = userManagerView.getTxt_eID().getText();
		if(id.trim().isEmpty()) throw new IllegalStateException("id không được rỗng");
		
		Employee e = EmployeeDAO.getEmployeeByID(id);
		
		if(e == null) throw new IllegalStateException("Nhân viên chưa tồn tại");
		
		String username = userManagerView.getTxt_username().getText();
		if(username.trim().isEmpty()) throw new IllegalStateException("Username không được rỗng");
		
		String password = userManagerView.getTxt_password().getText();
		if(password.trim().isEmpty()) throw new IllegalStateException("Password không được rỗng");
		
		ArrayList<Account> accounts = FetchDataStatus.getAccountData();
		
		for(Account a : accounts) {
			if(id.equalsIgnoreCase(a.getEmployee().getEmployeeID()))
				throw new IllegalStateException("Nhân viên đã có tài khoản");
		}
		
		Account account = accounts.stream().filter(x -> x.getUsername().equalsIgnoreCase(username))
				.findFirst().orElse(null);
		
		if (account != null)
			throw new IllegalArgumentException("Tên tài khoản đã tồn tại");
		
		
		return new Account(username, password, e);
	}
	
	private boolean updateAccount() {
		int index = -1;
		index = userManagerView.getTableAccount().getSelectedRow();
		if(index < 0) throw new IllegalStateException("Chưa chọn đối tượng để sửa");
		
		String id = userManagerView.getTxt_eID().getText();
		Employee e = EmployeeDAO.getEmployeeByID(id);
		
		String username = userManagerView.getTxt_username().getText();
		if(username.trim().isEmpty()) throw new IllegalStateException("Username không được rỗng");
		
		String password = userManagerView.getTxt_password().getText();
		if(password.trim().isEmpty()) throw new IllegalStateException("Password không được rỗng");
		
		Account newInfor = new Account(username, password, e);
		
		return AccountDAO.updateAccount(newInfor);
	}

}
