package view.suppliers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import model.suppliers.Suppliers;
import util.ColorHelper;
import util.FetchDataStatus;
import util.LayoutHelper;

public class SupplierManagerView {
	private JPanel panel;
	
	private JTextField supplierIDTxtField;
	private JTextField supplierNameTxtField;
	private JTextField addressTxtField;
	private JTextField phoneNumberTxtField;
	private JTextField emailTxtField;
	
	private JButton addBtn;
	private JButton removeBtn;
	private JButton updateBtn;
	
	private JTable supplierTable;
	private Suppliers suppliers;
	

	public SupplierManagerView() {
		panel = LayoutHelper.getBorderLayout();
		this.suppliers = new Suppliers(FetchDataStatus.getSupplierData());
		
		panel.add(setupTablePanel(), BorderLayout.NORTH);
		panel.add(setupFormPanel(), BorderLayout.CENTER);
		panel.add(setupBtnPanel(), BorderLayout.SOUTH);
	}
		
	private JPanel setupFormPanel() {
		JPanel formPanel = LayoutHelper.getGridBagLayout();
		formPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin nhà cung cấp"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		
		LayoutHelper.addItem(0, 0, 0, 0, LayoutHelper.setLabel("Mã NCC:"), formPanel, gbc);
		LayoutHelper.addItem(1, 0, 1, 0, supplierIDTxtField = LayoutHelper.getTextField(10), formPanel, gbc);
		LayoutHelper.addItem(2, 0, 0, 0, LayoutHelper.setLabel("Tên NCC:"), formPanel, gbc);
		LayoutHelper.addItem(3, 0, 1, 0, supplierNameTxtField = LayoutHelper.getTextField(10), formPanel, gbc);
		
		LayoutHelper.addItem(0, 1, 0, 0, LayoutHelper.setLabel("Địa chỉ:"), formPanel, gbc);
		LayoutHelper.addItem(1, 1, 1, 0, addressTxtField =LayoutHelper.getTextField(10), formPanel, gbc);
		LayoutHelper.addItem(2, 1, 0, 0, LayoutHelper.setLabel("Số điện thoại:"), formPanel, gbc);
		LayoutHelper.addItem(3, 1, 1, 0, phoneNumberTxtField = LayoutHelper.getTextField(10), formPanel, gbc);
		
		LayoutHelper.addItem(0, 2, 0, 0, LayoutHelper.setLabel("Email:"), formPanel, gbc);
		LayoutHelper.addItem(1, 2, 1, 0, emailTxtField = LayoutHelper.getTextField(10), formPanel, gbc);
		
		supplierIDTxtField.setEditable(false);
		
		return formPanel;
	}
	
	private JPanel setupTablePanel() {
		JPanel tablePanel = LayoutHelper.getBorderLayout();
		tablePanel.setPreferredSize(new Dimension(0, 300));
		tablePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Danh sách Nhà cung cấp"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		supplierTable = new JTable(suppliers);
		supplierTable.getTableHeader().setReorderingAllowed(false);
		supplierTable.getTableHeader().setResizingAllowed(false);
		supplierTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(supplierTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		tablePanel.add(scrollPane);
		
		return tablePanel;
	}
	
	private JPanel setupBtnPanel() {
		JPanel btnPanel = new JPanel();
		
		btnPanel.add(addBtn = LayoutHelper.setupBtn("Thêm", "src/Images/btn_icon/add_icon.png"));
		btnPanel.add(removeBtn = LayoutHelper.setupBtn("Xóa", "src/Images/btn_icon/remove_icon.png"));
		btnPanel.add(updateBtn =  LayoutHelper.setupBtn("Cập nhật", "src/Images/btn_icon/update_icon.png"));
		
		
		
		return btnPanel;
	}
	

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextField getSupplierIDTxtField() {
		return supplierIDTxtField;
	}

	public void setSupplierIDTxtField(JTextField supplierIDTxtField) {
		this.supplierIDTxtField = supplierIDTxtField;
	}

	public JTextField getSupplierNameTxtField() {
		return supplierNameTxtField;
	}

	public void setSupplierNameTxtField(JTextField supplierNameTxtField) {
		this.supplierNameTxtField = supplierNameTxtField;
	}

	public JTextField getAddressTxtField() {
		return addressTxtField;
	}

	public void setAddressTxtField(JTextField addressTxtField) {
		this.addressTxtField = addressTxtField;
	}

	public JTextField getPhoneNumberTxtField() {
		return phoneNumberTxtField;
	}

	public void setPhoneNumberTxtField(JTextField phoneNumberTxtField) {
		this.phoneNumberTxtField = phoneNumberTxtField;
	}

	public JTextField getEmailTxtField() {
		return emailTxtField;
	}

	public void setEmailTxtField(JTextField emailTxtField) {
		this.emailTxtField = emailTxtField;
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

	public JTable getSupplierTable() {
		return supplierTable;
	}

	public void setSupplierTable(JTable supplierTable) {
		this.supplierTable = supplierTable;
	}

	public Suppliers getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Suppliers suppliers) {
		this.suppliers = suppliers;
	}

	
	
	
	
	
	
}
