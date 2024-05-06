package model.statistical;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class EmployeeStatistics extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3738977687501618236L;
	private ArrayList<EmployeeDetail> employeeDetails;
	private String[] columns = {"TOP", "Mã NV", "Tên NV", "Tổng tiền"};
	
	
	public EmployeeStatistics() {
		employeeDetails = StatisticalDAO.getEmployeeSalesTotalAll();
	}
	@Override
	public String getColumnName(int column) {
		return this.columns[column];
	}
	@Override
	public int getRowCount() {
		return employeeDetails.size();
	}
	
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		EmployeeDetail employeeDetail = employeeDetails.get(rowIndex);
		switch (columnIndex) {
			case 0: return employeeDetails.indexOf(employeeDetail) + 1;
			case 1: return employeeDetail.getEmployeeID();
			case 2: return employeeDetail.getFullName();
			case 3: return employeeDetail.getTotalAmount();
			default: return null;
		}
	}
	public ArrayList<EmployeeDetail> getEmployeeDetails() {
		return employeeDetails;
	}
	public void setEmployeeDetails(ArrayList<EmployeeDetail> employeeDetails) {
		this.employeeDetails = employeeDetails;
		fireTableDataChanged();
	}
	
}
