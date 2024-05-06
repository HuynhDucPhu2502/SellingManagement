package view.employees;

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

import com.toedter.calendar.JDateChooser;

import model.employee.EmployeePosition;
import model.employee.Employees;
import model.employee.Gender;
import util.ColorHelper;
import util.FetchDataStatus;
import util.LayoutHelper;

public class EmployeeManagerView extends JFrame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7374130974626581196L;

	private JPanel pnlView;

	private JTextField txt_eID;
	private JTextField txt_email;
	private JTextField txt_lastName;
	private JTextField txt_firstName;
	private JTextField txt_phone;
	private JTextField txt_address;

	private JComboBox<String> cbb_gender;
	private JComboBox<String> cbb_position;

	private JDateChooser date_birth;

	private JButton btn_addEmployee;
	private JButton btn_removeEmployee;
	private JButton btn_updateEmployee;
	private JButton btn_formEmpty;
	
	private Employees employees;
	private JTable tableEmployee;

	private JTextField txt_coefficientsSalary;
	

	public EmployeeManagerView() {
		this.employees = new Employees(FetchDataStatus.getEmployeeData());
		
		// set panel view
		pnlView = LayoutHelper.getBorderLayout();
		
		// set north panel view
		this.pnlView.add(setupTablePanel(), BorderLayout.NORTH);
		
		// set center view
		this.pnlView.add(setupFormPanel(), BorderLayout.CENTER);
		
		// set south view
		this.pnlView.add(setupBtnPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel setupBtnPanel() {
		JPanel pnlSouth = new JPanel();
		
		btn_addEmployee = LayoutHelper.setupBtn("Thêm", "src/Images/btn_icon/add_icon.png");
		btn_removeEmployee = LayoutHelper.setupBtn("Xóa", "src/Images/btn_icon/remove_icon.png");
		btn_updateEmployee = LayoutHelper.setupBtn("Sửa", "src/Images/btn_icon/update_icon.png");
		btn_formEmpty = LayoutHelper.setupBtn("Xóa rỗng", "src/Images/btn_icon/reset_icon.png");
		
		pnlSouth.add(btn_addEmployee);
		pnlSouth.add(btn_removeEmployee);
		pnlSouth.add(btn_updateEmployee);
		pnlSouth.add(btn_formEmpty);
		
		return pnlSouth;
	}
	
	private JPanel setupTablePanel() {
		JPanel pnlCenter = LayoutHelper.getBorderLayout();
		pnlCenter.setPreferredSize(new Dimension(0, 300));
		
		pnlCenter.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Danh sách nhân viên"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		tableEmployee = new JTable(employees);
		
		JScrollPane scrollPane = new JScrollPane(tableEmployee);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		pnlCenter.add(scrollPane);
		
		return pnlCenter;
	}
	
	private JPanel setupFormPanel() {
        JPanel pnlNorth = new JPanel();
        pnlNorth.setLayout(new GridBagLayout());
        pnlNorth.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin nhân viên"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets.set(5, 5, 5, 5);

        // eID and email
        // x 0 y 0
        pnlNorth.add(LayoutHelper.setLabel("Mã nhân viên:"), gbc);
        // x 1 y 0
        gbc.gridx++;
        txt_eID = LayoutHelper.getTextField(20);
        pnlNorth.add(txt_eID, gbc);
        txt_eID.setEditable(false);
        
        // x 2 y 0
        gbc.gridx++;
        pnlNorth.add(LayoutHelper.setLabel("Email:"), gbc);
        
        // x 3 y 0
        gbc.gridx++;
        txt_email = LayoutHelper.getTextField(20);
        pnlNorth.add(txt_email, gbc);

        // box last name, first name and gender
        // x 0 y 1
        gbc.gridy++;
        gbc.gridx = 0;
        pnlNorth.add(LayoutHelper.setLabel("Họ:"), gbc);
        // x 1 y 1
        gbc.gridx++;
        pnlNorth.add(txt_lastName = LayoutHelper.getTextField(20), gbc);
        // x 2 y 1
        gbc.gridx++;
        pnlNorth.add(LayoutHelper.setLabel("Tên:"), gbc);
        // x 3 y 1
        gbc.gridx++;
        txt_firstName = LayoutHelper.getTextField(20);
        pnlNorth.add(txt_firstName, gbc);

        // box phone number and birth day
        // x 0 y 2
        gbc.gridy++;
        gbc.gridx = 0;
        pnlNorth.add(LayoutHelper.setLabel("Số điện thoại:"), gbc);
        // x 1 y 2
        gbc.gridx++;
        txt_phone = LayoutHelper.getTextField(20);
        pnlNorth.add(txt_phone, gbc);
        // x 2 y 2
        gbc.gridx++;
        pnlNorth.add(LayoutHelper.setLabel("Ngày sinh:"), gbc);
        // x 3 y 2
        gbc.gridx++;
        date_birth = new JDateChooser();
        date_birth.setPreferredSize(new Dimension(120, date_birth.getPreferredSize().height));
        pnlNorth.add(date_birth, gbc);
        // x 4 y 2
        gbc.gridx++;
        pnlNorth.add(LayoutHelper.setLabel("Giới tính:"), gbc);
        gbc.gridx++;
        cbb_gender = new JComboBox<>();
        cbb_gender.addItem(Gender.MALE.toString());
        cbb_gender.addItem(Gender.FEMALE.toString());
        pnlNorth.add(cbb_gender, gbc);

        // box position and address
        // x 0 y 3
        gbc.gridy++;
        gbc.gridx = 0;
        pnlNorth.add(LayoutHelper.setLabel("Chức vụ"), gbc);
        // x 1 y 3
        gbc.gridx++;
        cbb_position = new JComboBox<>();
        cbb_position.addItem(EmployeePosition.SALE_EMPLOYEE.toString());
        cbb_position.addItem(EmployeePosition.MANAGER.toString());
        pnlNorth.add(cbb_position, gbc);
        // x 2 y 3
        gbc.gridx++;
        pnlNorth.add(LayoutHelper.setLabel("Địa chỉ:"), gbc);
        // x 3 y 3
        gbc.gridx++;
        txt_address = LayoutHelper.getTextField(20);
        pnlNorth.add(txt_address, gbc);
        // x 4 y 3
        gbc.gridx++;
        pnlNorth.add(LayoutHelper.setLabel("Hệ số lương:"), gbc);
        gbc.gridx++;
        txt_coefficientsSalary = LayoutHelper.getTextField(20);
        txt_coefficientsSalary.setPreferredSize(new Dimension(100, txt_coefficientsSalary.getPreferredSize().height));
        pnlNorth.add(txt_coefficientsSalary, gbc);

        return pnlNorth;
    }

	public JPanel getPnlView() {
		return pnlView;
	}

	public void setPnlView(JPanel pnlView) {
		this.pnlView = pnlView;
	}

	public JTextField getTxt_eID() {
		return txt_eID;
	}

	public void setTxt_eID(JTextField txt_eID) {
		this.txt_eID = txt_eID;
	}

	public JTextField getTxt_email() {
		return txt_email;
	}

	public void setTxt_email(JTextField txt_email) {
		this.txt_email = txt_email;
	}

	public JTextField getTxt_lastName() {
		return txt_lastName;
	}

	public void setTxt_lastName(JTextField txt_lastName) {
		this.txt_lastName = txt_lastName;
	}

	public JTextField getTxt_firstName() {
		return txt_firstName;
	}

	public void setTxt_firstName(JTextField txt_firstName) {
		this.txt_firstName = txt_firstName;
	}

	public JComboBox<String> getCbb_gender() {
		return cbb_gender;
	}

	public void setCbb_gender(JComboBox<String> cbb_gender) {
		this.cbb_gender = cbb_gender;
	}

	public JTextField getTxt_phone() {
		return txt_phone;
	}

	public void setTxt_phone(JTextField txt_phone) {
		this.txt_phone = txt_phone;
	}

	public JDateChooser getDate_birth() {
		return date_birth;
	}

	public void setDate_birth(JDateChooser date_birth) {
		this.date_birth = date_birth;
	}

	public JTextField getTxt_address() {
		return txt_address;
	}

	public void setTxt_address(JTextField txt_address) {
		this.txt_address = txt_address;
	}

	public JComboBox<String> getCbb_position() {
		return cbb_position;
	}

	public void setCbb_position(JComboBox<String> cbb_position) {
		this.cbb_position = cbb_position;
	}

	public JButton getBtn_addEmployee() {
		return btn_addEmployee;
	}

	public void setBtn_addEmployee(JButton btn_addEmployee) {
		this.btn_addEmployee = btn_addEmployee;
	}

	public JButton getBtn_removeEmployee() {
		return btn_removeEmployee;
	}

	public void setBtn_removeEmployee(JButton btn_removeEmployee) {
		this.btn_removeEmployee = btn_removeEmployee;
	}

	public JButton getBtn_updateEmployee() {
		return btn_updateEmployee;
	}

	public void setBtn_updateEmployee(JButton btn_updateEmployee) {
		this.btn_updateEmployee = btn_updateEmployee;
	}

	public JButton getBtn_formEmpty() {
		return btn_formEmpty;
	}

	public void setBtn_formEmpty(JButton btn_formEmpty) {
		this.btn_formEmpty = btn_formEmpty;
	}

	public Employees getEmployees() {
		return employees;
	}

	public void setEmployees(Employees employees) {
		this.employees = employees;
	}

	public JTable getTableEmployee() {
		return tableEmployee;
	}

	public void setTableEmployee(JTable tableEmployee) {
		this.tableEmployee = tableEmployee;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTextField getTxt_coefficientsSalary() {
		return txt_coefficientsSalary;
	}

	public void setTxt_coefficientsSalary(JTextField txt_coefficientsSalary) {
		this.txt_coefficientsSalary = txt_coefficientsSalary;
	}

	
	
}
