package model.accounts;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Accounts extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -499988539751989707L;
	private String[] columns = {"Tài khoản", "Mật khẩu", "Mã nhân viên"};
	private ArrayList<Account> accounts;
	
	public Accounts() {
		this.accounts = new ArrayList<Account>();
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}



	@Override
	public int getRowCount() {
		return accounts.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Account user = accounts.get(rowIndex);
		switch (columnIndex) {
			case 0: return user.getUsername();
			case 1: return user.getPassword();
			case 2: return user.getEmployee();
			default: return null;
		}
	}

	public ArrayList<Account> getUsers() {
		return accounts;
	}



	public void setUsers(ArrayList<Account> users) {
		this.accounts = users;
	}
	
	
	
	
	
	
	
}
