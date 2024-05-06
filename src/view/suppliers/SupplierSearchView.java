package view.suppliers;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import model.suppliers.Suppliers;
import util.ColorHelper;
import util.FetchDataStatus;
import util.LayoutHelper;

public class SupplierSearchView {
	private JPanel panel;

	private JButton searchContainsSupplierIDBtn;
	private JButton searchContainsSupplierNameBtn;
	private JButton searchContainsPhoneNumberBtn;
	
	private JButton resetSearchBtn;
	
	private JTextField searchContainsSupplierIDTxtField;
	private JTextField searchContainsSupplierNameTxtField;
	private JTextField searchContainsPhoneNumberTxtField;
	
	private JTable supplierTable;
	private Suppliers suppliers;
	
	
	public SupplierSearchView() {
		this.panel = new JPanel(new BorderLayout());
		this.suppliers = new Suppliers(FetchDataStatus.getSupplierData());
		
		panel.add(setupSearchFormPanel(), BorderLayout.NORTH);
		panel.add(setupTablePanel(), BorderLayout.CENTER);
		panel.add(setupSouthPanel(), BorderLayout.SOUTH);
		
	}
	
	private JPanel setupSearchFormPanel() {
		JPanel searchFormPanel = LayoutHelper.getGridBagLayout();
		searchFormPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Tìm kiếm"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		

		
		LayoutHelper.addItem(0, 0, 0, 0, new JLabel("Mã nhà cung cấp:"), searchFormPanel, gbc);
		LayoutHelper.addItem(0, 1, 0, 0, new JLabel("Tên nhà cung cấp:"), searchFormPanel, gbc);
		LayoutHelper.addItem(0, 2, 0, 0, new JLabel("SĐT nhà cung cấp:"), searchFormPanel, gbc);
		
		LayoutHelper.addItem(1, 0, 1, 0, searchContainsSupplierIDTxtField = new JTextField(), searchFormPanel, gbc);
		LayoutHelper.addItem(1, 1, 1, 0, searchContainsSupplierNameTxtField = new JTextField(), searchFormPanel, gbc);
		LayoutHelper.addItem(1, 2, 1, 0, searchContainsPhoneNumberTxtField = new JTextField(), searchFormPanel, gbc);
		
		LayoutHelper.addItem(2, 0, 0, 0, searchContainsSupplierIDBtn = new JButton("Tìm kiếm theo mã NCC"), searchFormPanel, gbc);
		LayoutHelper.addItem(2, 1, 0, 0, searchContainsSupplierNameBtn = new JButton("Tìm kiếm theo tên NCC"), searchFormPanel, gbc);
		LayoutHelper.addItem(2, 2, 0, 0, searchContainsPhoneNumberBtn = new JButton("Tìm kiếm theo SDT NCC"), searchFormPanel, gbc);
		
		return searchFormPanel;
		
	}
	
	private JPanel setupSouthPanel() {
		JPanel southPanel = LayoutHelper.getGridBagLayout();
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		LayoutHelper.addItem(0, 0, 1, 0, resetSearchBtn = new JButton("Làm mới tìm kiếm"), southPanel, gbc);
		
		return southPanel;
	}
	
	private JPanel setupTablePanel() {
		JPanel tablePanel = LayoutHelper.getBorderLayout();
		tablePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Kết quả tìm kiếm"),
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
	
	
	public JPanel getPanel() {
		return panel;
	}

	public JButton getSearchContainsSupplierIDBtn() {
		return searchContainsSupplierIDBtn;
	}

	public void setSearchContainsSupplierIDBtn(JButton searchContainsSupplierIDBtn) {
		this.searchContainsSupplierIDBtn = searchContainsSupplierIDBtn;
	}

	public JButton getSearchContainsSupplierNameBtn() {
		return searchContainsSupplierNameBtn;
	}

	public void setSearchContainsSupplierNameBtn(JButton searchContainsSupplierNameBtn) {
		this.searchContainsSupplierNameBtn = searchContainsSupplierNameBtn;
	}

	public JButton getSearchContainsPhoneNumberBtn() {
		return searchContainsPhoneNumberBtn;
	}

	public void setSearchContainsPhoneNumberBtn(JButton searchContainsPhoneNumberBtn) {
		this.searchContainsPhoneNumberBtn = searchContainsPhoneNumberBtn;
	}

	public JButton getResetSearchBtn() {
		return resetSearchBtn;
	}

	public void setResetSearchBtn(JButton resetSearchBtn) {
		this.resetSearchBtn = resetSearchBtn;
	}

	

	public JTextField getSearchContainsSupplierIDTxtField() {
		return searchContainsSupplierIDTxtField;
	}

	public void setSearchContainsSupplierIDTxtField(JTextField searchContainsSupplierIDTxtField) {
		this.searchContainsSupplierIDTxtField = searchContainsSupplierIDTxtField;
	}

	public JTextField getSearchContainsSupplierNameTxtField() {
		return searchContainsSupplierNameTxtField;
	}

	public void setSearchContainsSupplierNameTxtField(JTextField searchContainsSupplierNameTxtField) {
		this.searchContainsSupplierNameTxtField = searchContainsSupplierNameTxtField;
	}

	public JTextField getSearchContainsPhoneNumberTxtField() {
		return searchContainsPhoneNumberTxtField;
	}

	public void setSearchContainsPhoneNumberTxtField(JTextField searchContainsPhoneNumberTxtField) {
		this.searchContainsPhoneNumberTxtField = searchContainsPhoneNumberTxtField;
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
