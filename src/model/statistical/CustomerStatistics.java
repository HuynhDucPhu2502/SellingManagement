package model.statistical;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class CustomerStatistics extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6116883835591513744L;
	private ArrayList<CustomerDetail> customerDetails;
	private String[] columns = {"TOP", "Mã KH", "Tên KH", "Tổng tiền"};
	
	
	public CustomerStatistics() {
		customerDetails = StatisticalDAO.getCustomerTotalPurchasesAll();
	}
	@Override
	public String getColumnName(int column) {
		return this.columns[column];
	}
	@Override
	public int getRowCount() {
		return customerDetails.size();
	}
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		CustomerDetail customerDetail = customerDetails.get(rowIndex);
		switch (columnIndex) {
			case 0: return customerDetails.indexOf(customerDetail) + 1;
			case 1: return customerDetail.getCustomerID();
			case 2: return customerDetail.getFullName();
			case 3: return customerDetail.getTotalAmount();
			default: return null;
		}
	}
	public ArrayList<CustomerDetail> getCustomerDetails() {
		return customerDetails;
	}
	public void setCustomerDetails(ArrayList<CustomerDetail> customerDetails) {
		this.customerDetails = customerDetails;
		fireTableDataChanged();
	}
	
	
}
