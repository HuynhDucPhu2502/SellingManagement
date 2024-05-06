package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import model.employee.Employee;
import model.employee.EmployeeDAO;
import model.employee.EmployeePosition;
import model.employee.Employees;
import model.employee.Gender;
import util.FetchDataStatus;
import view.base.MainFrame;
import view.employees.EmployeeManagerView;

public class EmployeeManagerController implements ActionListener{

	private EmployeeManagerView employeeManagerView;
	private Employees employees;
	
	private String nextEmployeeID;
	
	public EmployeeManagerController(EmployeeManagerView employeeManagerView) {
		
		this.employeeManagerView = employeeManagerView;
		employees = employeeManagerView.getEmployees();
		
		// add action
		this.addAction();
		
	}
	
	private void addAction() {
		employeeManagerView.getBtn_addEmployee().addActionListener(this);
		employeeManagerView.getBtn_removeEmployee().addActionListener(this);
		employeeManagerView.getBtn_updateEmployee().addActionListener(this);
		employeeManagerView.getBtn_formEmpty().addActionListener(this);
		setupTableSelection();
		handleNextEmployeeID();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(employeeManagerView.getBtn_addEmployee())) {
			try {
				if(this.addEmp()) {
					JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");
					employees.setListEmployee(FetchDataStatus.getEmployeeData());
					setFormEmpty();
					handleNextEmployeeID();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}
		else if(o.equals(employeeManagerView.getBtn_removeEmployee())) {
			int check = JOptionPane.showConfirmDialog(null, "Có chắc muốn xóa nhân viên", "Cảnh báo", JOptionPane.YES_NO_OPTION);
			if(check != JOptionPane.YES_OPTION)
				return;
			try {
				if(this.removeEmp()) {
					JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công");
					employees.setListEmployee(FetchDataStatus.getEmployeeData());
					setFormEmpty();
					handleNextEmployeeID();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}
		else if(o.equals(employeeManagerView.getBtn_formEmpty())) {
			this.setFormEmpty();
		}
		if(o.equals(employeeManagerView.getBtn_updateEmployee())) {
			int check = JOptionPane.showConfirmDialog(null, "Có chắc muốn sửa thông tin nhân viên", "Cảnh báo", JOptionPane.YES_NO_OPTION);
			if(check != JOptionPane.YES_OPTION)
				return;
			try {
				if(this.updateInforOfEmp()) {
					JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thành công");
					employees.setListEmployee(FetchDataStatus.getEmployeeData());
					setFormEmpty();
					handleNextEmployeeID();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}
	}
	
	private boolean addEmp() {
		Employee e = null;
		try {
			e = createEmployee();
		} catch (Exception e1) {
			// TODO: handle exception
			throw new IllegalStateException(e1.getMessage());
		}
		 
		if(e == null) throw new IllegalStateException("Tạo nhân viên thất bại");
		
		String id = e.getEmployeeID();
		String phone = e.getPhoneNumber();
		
		Employee temp = employees.getListEmployee().stream()
				.filter(x -> x.getEmployeeID().equalsIgnoreCase(id))
				.findFirst().orElse(null);
		
		if(temp != null)
			throw new IllegalStateException("Nhân viên đã tồn tại");
		
		for(Employee e2 : employees.getListEmployee()) {
			if(e2.getPhoneNumber().equalsIgnoreCase(phone))
				throw new IllegalStateException("Số điện thoại đã được sử dụng");
		}
		
		setFormEmpty();
		
		return EmployeeDAO.insertData(e);
	}
	
	private boolean removeEmp() {
		int index = -1;
		index = employeeManagerView.getTableEmployee().getSelectedRow();
		
		if(index < 0) throw new IllegalStateException("Chưa chọn đối tượng xóa");
		
		String e_ID = employees.getEmployeeByIndex(index).getEmployeeID();
		
		if(e_ID.equalsIgnoreCase(MainFrame.getAccount().getEmployee().getEmployeeID())) 
			throw new IllegalStateException("Không thể xóa - tài khoản đang hoạt động");
			
		setFormEmpty();
		
		return EmployeeDAO.deleteData(e_ID);
	}
	
	private boolean updateInforOfEmp() {
		int index = -1;
		index = employeeManagerView.getTableEmployee().getSelectedRow();
		
		if(index < 0) throw new IllegalStateException("Chưa chọn đối tượng sửa");
		
		Employee newInfor = null;
		try {
			newInfor = this.createEmployee();
		} catch (Exception e) {
			// TODO: handle exception
			throw new IllegalStateException(e.getMessage());
		}
		
		if(newInfor == null) throw new IllegalStateException("Cập nhật thông tin thất bại");
		
		
		setFormEmpty();
		
		return EmployeeDAO.updateData(newInfor);
	}
	
	
	
	private Employee createEmployee() {
		String e_ID = employeeManagerView.getTxt_eID().getText();
		if(e_ID.trim().isEmpty()) {
			employeeManagerView.getTxt_eID().requestFocus();
			throw new IllegalStateException("Mã nhân viên không được rỗng");
		}
		
		String email = employeeManagerView.getTxt_email().getText();
		if(email.trim().isEmpty()) {
			employeeManagerView.getTxt_email().requestFocus();
			throw new IllegalStateException("Email nhân viên không được rỗng");
		}else if(email.length() > 50) {
			employeeManagerView.getTxt_email().requestFocus();
			throw new IllegalStateException("Email không được vượt quá 50 kí tự");
		}else if(!email.matches("^\\w+(@gmail.com)$")) {
			employeeManagerView.getTxt_email().requestFocus();
			throw new IllegalStateException("Email phải có định dạng abc@gmail.com");
		}
		
		String lastName = employeeManagerView.getTxt_lastName().getText();
		if(lastName.trim().isEmpty()) {	
			employeeManagerView.getTxt_lastName().requestFocus();
			throw new IllegalStateException("Họ nhân viên không được rỗng");
		}else if(lastName.length() > 50) {
			employeeManagerView.getTxt_lastName().requestFocus();
			throw new IllegalStateException("Họ không được vượt quá 50 kí tự");
		}
		
		String firstName = employeeManagerView.getTxt_firstName().getText();
		if(firstName.trim().isEmpty()) {
			employeeManagerView.getTxt_firstName().requestFocus();
			throw new IllegalStateException("Tên nhân viên không được rỗng");
		}else if(firstName.length() > 50) {
			employeeManagerView.getTxt_firstName().requestFocus();
			throw new IllegalStateException("Tên không được vượt quá 50 kí tự");
		}
		
		String address = employeeManagerView.getTxt_address().getText();
		if(address.trim().isEmpty()) {
			employeeManagerView.getTxt_address().requestFocus();
			throw new IllegalStateException("Địa chỉ nhân viên không được rỗng");
		}else if(address.length() > 250) {
			employeeManagerView.getTxt_address().requestFocus();
			throw new IllegalStateException("Địa chỉ không được vượt quá 250 kí tự");
		}
		
		String phone = employeeManagerView.getTxt_phone().getText();
		
		if(phone.trim().isEmpty()) {
			employeeManagerView.getTxt_phone().requestFocus();
			throw new IllegalStateException("Số điện thoại nhân viên không được rỗng");
		}else if(!phone.matches("^0\\d*")) {
			employeeManagerView.getTxt_phone().requestFocus();
			throw new IllegalStateException("Số điện thoại phải bắt đầu là 0 và phải là số");
		}else if(phone.length() != 10) {
			employeeManagerView.getTxt_phone().requestFocus();
			throw new IllegalStateException("Số điện thoại phải đủ 10 chữ số");
		}
		
		LocalDate birth = null;
		try {
			birth = employeeManagerView.getDate_birth().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		} catch (Exception e) {
			employeeManagerView.getDate_birth().requestFocus();
			throw new IllegalStateException("Ngày sinh nhân viên không hợp lệ");
		}
		
		Gender gender = employeeManagerView.getCbb_gender()
				.getSelectedItem().toString()
				.equalsIgnoreCase(Gender.MALE.toString()) 
				? Gender.MALE
				: Gender.FEMALE;
		
		EmployeePosition position = employeeManagerView.getCbb_position()
				.getSelectedItem().toString()
				.equalsIgnoreCase(EmployeePosition.SALE_EMPLOYEE.toString())
				? EmployeePosition.SALE_EMPLOYEE
				: EmployeePosition.MANAGER;
		
		double coefficientsSalary = 0;
		try {
			coefficientsSalary = Double.parseDouble(employeeManagerView.getTxt_coefficientsSalary().getText());
		} catch (Exception e) {
			employeeManagerView.getTxt_coefficientsSalary().requestFocus();
			throw new IllegalStateException("Hệ số lương phải là số");
		}
		if(coefficientsSalary <= 0)
			throw new IllegalStateException("Hệ số lương phải lớn hơn 0");
		
		return new Employee(e_ID, lastName, firstName, address, phone, email, position, birth, gender, coefficientsSalary);
	}
	
	private void setupTableSelection() {
		employeeManagerView.getTableEmployee().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		employeeManagerView.getTableEmployee().getSelectionModel().addListSelectionListener(event -> {
			if (!event.getValueIsAdjusting()) {
				int index = employeeManagerView.getTableEmployee().getSelectedRow();
				if (index != -1) showTxtField(index);
				else setFormEmpty();
			}
		});
	}
	
	private void setFormEmpty() {
		employeeManagerView.getTxt_eID().setText(null);
		employeeManagerView.getTxt_email().setText(null);
		employeeManagerView.getTxt_lastName().setText(null);
		employeeManagerView.getTxt_firstName().setText(null);
		employeeManagerView.getTxt_phone().setText(null);
		employeeManagerView.getDate_birth().setSelectableDateRange(null, null);
		employeeManagerView.getCbb_gender().setSelectedIndex(0);
		employeeManagerView.getCbb_position().setSelectedIndex(0);
		employeeManagerView.getTxt_address().setText(null);
		employeeManagerView.getTxt_coefficientsSalary().setText(null);
		employeeManagerView.getTableEmployee().setRowSorter(null);
		handleNextEmployeeID();
	}
	
	private void showTxtField(int selectedIndex) {
		Employee employee = employees.getEmployeeByIndex(selectedIndex);
		
		employeeManagerView.getTxt_eID().setText(employee.getEmployeeID());
		employeeManagerView.getTxt_lastName().setText(employee.getLastName());
		employeeManagerView.getTxt_firstName().setText(employee.getFirstName());
		employeeManagerView.getTxt_address().setText(employee.getAddress());
		employeeManagerView.getTxt_phone().setText(employee.getPhoneNumber());
		employeeManagerView.getTxt_email().setText(employee.getEmail());
		employeeManagerView.getCbb_position().setSelectedItem(employee.getPosition().toString());
		employeeManagerView.getDate_birth().setDate(java.sql.Date.valueOf(employee.getBirthDay()));
		employeeManagerView.getCbb_gender().setSelectedItem(employee.getGender().toString());
		employeeManagerView.getTxt_coefficientsSalary().setText(String.valueOf(employee.getCoefficientsSalary()));
	}
	
	public void resetAll() {
		employeeManagerView.getEmployees().setListEmployee(FetchDataStatus.getEmployeeData());
		handleNextEmployeeID();
	}
	
	private void handleNextEmployeeID() {
		
		if (employees.getListEmployee().isEmpty()) nextEmployeeID = "EMP001";
		else {
			String lastEmployeeID = employees.getListEmployee().get(employees.getListEmployee().size() - 1).getEmployeeID();
			String numberPart = lastEmployeeID.substring(3);
			int nextNumber = Integer.parseInt(numberPart) + 1;
			nextEmployeeID = "EMP" + String.format("%03d", nextNumber);
		}
		
		employeeManagerView.getTxt_eID().setText(nextEmployeeID);
	}

	
	

}
