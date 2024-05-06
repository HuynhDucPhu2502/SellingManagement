package view.customers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.customers.CustomerType;
import model.customers.Customers;
import util.ColorHelper;
import util.FetchDataStatus;
import util.LayoutHelper;

public class CustomerManagerView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6830502551713030154L;

	private JPanel panel;
	
	private JTextField customerIDTxtField;
	private JTextField FirstNameTxtField;
	private JTextField LastNameTxtField;
	private JTextField AddressTxtField;
	private JTextField PhoneNumbeTxtField;
	
	private JComboBox<CustomerType> cbb_Type;
	
	private JButton addBtn;
	private JButton removeBtn;
	private JButton updateBtn;

	private Customers customers;
	private JTable customerTable;

	public CustomerManagerView(){
		this.customers = new Customers(FetchDataStatus.getCustomerData());
		
		this.panel=new JPanel(new BorderLayout());
		
		panel.add(setTablePanel(),BorderLayout.NORTH);
		panel.add(setupFormPanel(),BorderLayout.CENTER);
		panel.add(setupBtnPanel(),BorderLayout.SOUTH);
		
		this.add(panel);
	}
	
	
	
	public JPanel getPanel() {
		return panel;
	}
	
	private JPanel setupFormPanel() {
		JPanel formPanel = LayoutHelper.getGridBagLayout();
		formPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Kết quả tìm kiếm"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		
		LayoutHelper.addItem(0, 0, 0, 0, LayoutHelper.setLabel("Mã khách hàng:"), formPanel, gbc);
		LayoutHelper.addItem(1, 0, 1, 0, customerIDTxtField = LayoutHelper.getTextField(10), formPanel, gbc);
		LayoutHelper.addItem(0, 1, 0, 0, LayoutHelper.setLabel("Tên:"), formPanel, gbc);
		LayoutHelper.addItem(1, 1, 1, 0, FirstNameTxtField = LayoutHelper.getTextField(10), formPanel, gbc);
		LayoutHelper.addItem(0, 2, 0, 0, LayoutHelper.setLabel("Họ:"), formPanel, gbc);
		LayoutHelper.addItem(1, 2, 1, 0, LastNameTxtField = LayoutHelper.getTextField(10), formPanel, gbc);
		LayoutHelper.addItem(0, 3, 0, 0, LayoutHelper.setLabel("Địa chỉ:"), formPanel, gbc);
		LayoutHelper.addItem(1, 3, 1, 0, AddressTxtField = LayoutHelper.getTextField(10), formPanel, gbc);
		LayoutHelper.addItem(0, 4, 0, 0, LayoutHelper.setLabel("Số điện thoại:"), formPanel, gbc);
		LayoutHelper.addItem(1, 4, 1, 0, PhoneNumbeTxtField = LayoutHelper.getTextField(10), formPanel, gbc);
		LayoutHelper.addItem(0, 5, 0, 0, LayoutHelper.setLabel("Loại khách hàng:"), formPanel, gbc);
		LayoutHelper.addItem(1, 5, 1, 0, cbb_Type=new JComboBox<>(), formPanel, gbc);
		
		customerIDTxtField.setEditable(false);
		cbb_Type.addItem(CustomerType.MEMBER);
		cbb_Type.addItem(CustomerType.VIP);
		return formPanel;
		
	}
	
	private JPanel setTablePanel() {
		JPanel pTable= new JPanel(new BorderLayout());
		pTable.setPreferredSize(new Dimension(0, 250));
		pTable.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin hóa đơn"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		customerTable = new JTable(customers);
		
		JScrollPane scrollPane = new JScrollPane(customerTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		pTable.add(scrollPane);
		
		return pTable;
	}
	
	private JPanel setupBtnPanel() {
		JPanel btnPanel = new JPanel();
		
		btnPanel.add(addBtn = LayoutHelper.setupBtn("Thêm", "src/Images/btn_icon/add_icon.png"));
		btnPanel.add(removeBtn = LayoutHelper.setupBtn("Xóa", "src/Images/btn_icon/remove_icon.png"));
		btnPanel.add(updateBtn =  LayoutHelper.setupBtn("Cập nhật", "src/Images/btn_icon/update_icon.png"));
		
		return btnPanel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}



	public JTextField getCustomerIDTxtField() {
		return customerIDTxtField;
	}



	public void setCustomerIDTxtField(JTextField customerIDTxtField) {
		this.customerIDTxtField = customerIDTxtField;
	}



	public JTextField getFirstNameTxtField() {
		return FirstNameTxtField;
	}



	public void setFirstNameTxtField(JTextField firstNameTxtField) {
		FirstNameTxtField = firstNameTxtField;
	}



	public JTextField getLastNameTxtField() {
		return LastNameTxtField;
	}



	public void setLastNameTxtField(JTextField lastNameTxtField) {
		LastNameTxtField = lastNameTxtField;
	}



	public JTextField getAddressTxtField() {
		return AddressTxtField;
	}



	public void setAddressTxtField(JTextField addressTxtField) {
		AddressTxtField = addressTxtField;
	}



	public JTextField getPhoneNumbeTxtField() {
		return PhoneNumbeTxtField;
	}



	public void setPhoneNumbeTxtField(JTextField phoneNumbeTxtField) {
		PhoneNumbeTxtField = phoneNumbeTxtField;
	}



	public JComboBox<CustomerType> getCbb_Type() {
		return cbb_Type;
	}



	public void setCbb_Type(JComboBox<CustomerType> cbb_Type) {
		this.cbb_Type = cbb_Type;
	}



	public JButton getAddBtn() {
		return addBtn;
	}



	public void setAddBtn(JButton addBtn) {
		this.addBtn = addBtn;
	}



	public JButton getRemoveBtn() {
		return removeBtn;
	}



	public void setRemoveBtn(JButton removeBtn) {
		this.removeBtn = removeBtn;
	}



	public JButton getUpdateBtn() {
		return updateBtn;
	}



	public void setUpdateBtn(JButton updateBtn) {
		this.updateBtn = updateBtn;
	}



	public Customers getCustomers() {
		return customers;
	}



	public void setCustomers(Customers customers) {
		this.customers = customers;
	}



	public JTable getCustomerTable() {
		return customerTable;
	}



	public void setCustomerTable(JTable customerTable) {
		this.customerTable = customerTable;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	
	
}
