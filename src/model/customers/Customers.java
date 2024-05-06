package model.customers;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Customers extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9069707705168880367L;
	private ArrayList<Customer> listCustomers;
	private final String[] columns= {"Mã KH","Họ","Tên","Địa chỉ","Số điện thoại","Loại Khách Hàng"};
	
	public Customers() {
		this.listCustomers = CustomerDAO.getData();
	}

	public Customers(ArrayList<Customer> listCustomers) {
		super();
		this.listCustomers = listCustomers;
	}

	@Override
	public String getColumnName(int column) {
		return this.columns[column];
	}

	@Override
	public int getRowCount() {
		return listCustomers.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Customer customer = listCustomers.get(rowIndex);
		switch (columnIndex) {
			case 0: return customer.getCustomerID();
			case 1: return customer.getLastName();
			case 2: return customer.getFirstName();
			case 3: return customer.getAddress();
			case 4: return customer.getPhoneNumber();
			case 5: return customer.getCustomerType();
			default: return null;
		}
	}
	
	public Customer getCustomerByID(String cID) {
		return listCustomers.stream()
				.filter(x -> x.getCustomerID().equalsIgnoreCase(cID))
				.findFirst().orElse(null);
	}
	
	public Customer getCustomerByIndex(int index) {
		if(index < 0 || index >= listCustomers.size())
			return null;
		return listCustomers.get(index);
	}

	public ArrayList<Customer> getListCustomers() {
		return listCustomers;
	}

	public void setListCustomers(ArrayList<Customer> listCustomers) {
		this.listCustomers = listCustomers;
		fireTableDataChanged();
	}
	
	
	
}
