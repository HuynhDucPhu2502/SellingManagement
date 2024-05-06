package model.employee;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Employees extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6764600478995549190L;
	private ArrayList<Employee> listEmployee;
	String[] columns = {"Mã nhân viên", "Họ", "Tên", "Địa chỉ", "Số điện thoại",
			"Chức vụ", "Email", "Ngày sinh", "Giới tính", "Hệ số lương"};

	public Employees() {
		this.listEmployee = EmployeeDAO.getData();
	}
	
	
	public Employees(ArrayList<Employee> listEmployee) {
		super();
		this.listEmployee = listEmployee;
	}

	@Override
	public String getColumnName(int column) {
		return this.columns[column];
	}

	@Override
	public int getRowCount() {
		return listEmployee.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Employee employee = listEmployee.get(rowIndex);
		switch (columnIndex) {
			case 0: return employee.getEmployeeID();
			case 1: return employee.getFirstName();
			case 2: return employee.getLastName();
			case 3: return employee.getAddress();
			case 4:	return employee.getPhoneNumber();
			case 5: return employee.getPosition();
			case 6: return employee.getEmail();
			case 7: return employee.getBirthDay();
			case 8: return employee.getGender().toString();
			case 9: return employee.getCoefficientsSalary();
			default: return null;
		}
	}



	public ArrayList<Employee> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(ArrayList<Employee> listEmployee) {
		this.listEmployee = listEmployee;
		fireTableDataChanged();
	}
	
	
	public Employee getEmployeeByID(String eID) {
		return listEmployee.stream()
				.filter(x -> x.getEmployeeID().equalsIgnoreCase(eID))
				.findFirst().orElse(null);
	}
	
	public Employee getEmployeeByIndex(int index) {
		if(index < 0 || index >= listEmployee.size())
			return null;
		return listEmployee.get(index);
	}
	
}
