package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.employee.Employee;
import util.FetchDataStatus;
import view.employees.EmployeeSearchView;

public class EmployeeSearchController implements ActionListener{
	
	private static EmployeeSearchView employeeSearchView;
	
	public EmployeeSearchController(EmployeeSearchView employeeSearchView) {
		
		EmployeeSearchController.employeeSearchView = employeeSearchView;
		
		readingDataFromDBToTable(FetchDataStatus.getEmployeeData());
		
		this.addAction();
	}
	
	private void addAction() {
		employeeSearchView.getBtn_searchByName().addActionListener(this);
		employeeSearchView.getBtn_searchById().addActionListener(this);
		employeeSearchView.getBtn_searchByPosition().addActionListener(this);
		employeeSearchView.getResetSearchBtn().addActionListener(this);
	}
	
	public static void readingDataFromDBToTable(ArrayList<Employee> listEmployee) {
		for(Employee e : listEmployee) {
			employeeSearchView.getDfTbEmp().addRow(new Object[] {e.getEmployeeID(), 
					e.getLastName(), e.getFirstName(), e.getAddress(), e.getPhoneNumber(),
					e.getEmail(), e.getPosition().toString(), e.getBirthDay(), e.getGender().toString(),
					e.getCoefficientsSalary()});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(employeeSearchView.getBtn_searchByName())) {
			try {
				this.searchByName();
			} catch (Exception e2) {
				// TODO: handle exceptionM
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}else if(o.equals(employeeSearchView.getBtn_searchById())) {
			try {
				this.searchByID();
			} catch (Exception e2) {
				// TODO: handle exceptionM
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}else if(o.equals(employeeSearchView.getBtn_searchByPosition())) {
			this.searchByPosition();
		}else if(o.equals(employeeSearchView.getResetSearchBtn())) {
			resetAll();
		}
	}
	
	private void searchByName() {
		String name = employeeSearchView.getSearchByName().getText().trim();
		if (name.isEmpty()) {
		    employeeSearchView.getSearchByName().requestFocus();
		    throw new IllegalStateException("Chưa nhập tên nhân viên để tìm kiếm");
		}

		ArrayList<Employee> dsEmp = FetchDataStatus.getEmployeeData();
		ArrayList<Employee> dsEmpContainsName = new ArrayList<>();

		for (int i = 0; i < dsEmp.size(); i++) {
		    if (dsEmp.get(i).getFirstName().toUpperCase().contains(name.toUpperCase())) {
		        dsEmpContainsName.add(dsEmp.get(i));
		    }
		}

		employeeSearchView.getDfTbEmp().setRowCount(0);
		readingDataFromDBToTable(dsEmpContainsName);
	}
	
	private void searchByID() {
		String id = employeeSearchView.getSearchById().getText().trim();
		if (id.isEmpty()) {
		    employeeSearchView.getSearchById().requestFocus();
		    throw new IllegalStateException("Chưa nhập mã nhân viên để tìm kiếm");
		}

		ArrayList<Employee> dsEmp = FetchDataStatus.getEmployeeData();
		ArrayList<Employee> dsEmpId = new ArrayList<>();

		for (int i = 0; i < dsEmp.size(); i++) {
		    if(dsEmp.get(i).getEmployeeID().equalsIgnoreCase(id)) {
		    	dsEmpId.add(dsEmp.get(i));
		    }
		}

		employeeSearchView.getDfTbEmp().setRowCount(0);
		readingDataFromDBToTable(dsEmpId);
	}
	
	private void searchByPosition() {
		String positon = employeeSearchView.getSearchByPosition().getSelectedItem().toString();
		
		ArrayList<Employee> dsEmp = FetchDataStatus.getEmployeeData();
		ArrayList<Employee> dsEmpId = new ArrayList<>();

		for (int i = 0; i < dsEmp.size(); i++) {
		    if(dsEmp.get(i).getPosition().toString().equalsIgnoreCase(positon)) {
		    	dsEmpId.add(dsEmp.get(i));
		    }
		}

		employeeSearchView.getDfTbEmp().setRowCount(0);
		readingDataFromDBToTable(dsEmpId);
	}
	
	public static void resetAll() {
		employeeSearchView.getDfTbEmp().setRowCount(0);
		readingDataFromDBToTable(FetchDataStatus.getEmployeeData());
	}

	public EmployeeSearchView getEmployeeSearchView() {
		return employeeSearchView;
	}

}
