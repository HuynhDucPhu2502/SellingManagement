package view.products;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import model.products.Products;
import util.ColorHelper;
import util.FetchDataStatus;
import util.LayoutHelper;

public class ProductSearchView {
	private JPanel panel;

	private JButton searchContainsNameBtn;
	private JButton searchByIDBtn;
	private JButton searchByCategoryNameBtn;
	private JButton searchBySupplierIDBtn;
	
	private JButton resetSearchBtn;
	
	private JTextField searchContainsNameTxTField;
	private JTextField searchByIDTxtField;
	
	private JTable productTable;
	private Products products;
	
	private DefaultComboBoxModel<String> categoryDefaultComboxModel;
	private JComboBox<String> categoryNameCBox;
	
	private DefaultComboBoxModel<String> supplierDefaultComboBoxModel;
	private JComboBox<String> supplierNameCBox;
	
	
	public ProductSearchView() {
		this.panel = new JPanel(new BorderLayout());
		this.products = new Products(FetchDataStatus.getProductData());
		
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
		
		categoryDefaultComboxModel = new DefaultComboBoxModel<String>();
		supplierDefaultComboBoxModel = new DefaultComboBoxModel<String>();
		
		LayoutHelper.addItem(0, 0, 0, 0, new JLabel("Tên hàng hóa:"), searchFormPanel, gbc);
		LayoutHelper.addItem(0, 1, 0, 0, new JLabel("Mã hàng hóa:"), searchFormPanel, gbc);
		LayoutHelper.addItem(0, 2, 0, 0, new JLabel("Tên loại hàng:"), searchFormPanel, gbc);
		LayoutHelper.addItem(0, 3, 0, 0, new JLabel("Tên nhà cung cấp:"), searchFormPanel, gbc);
		
		LayoutHelper.addItem(1, 0, 1, 0, searchContainsNameTxTField = new JTextField(), searchFormPanel, gbc);
		LayoutHelper.addItem(1, 1, 1, 0, searchByIDTxtField = new JTextField(), searchFormPanel, gbc);
		LayoutHelper.addItem(1, 2, 1, 0, categoryNameCBox = new JComboBox<String>(categoryDefaultComboxModel), searchFormPanel, gbc);
		LayoutHelper.addItem(1, 3, 1, 0, supplierNameCBox = new JComboBox<String>(supplierDefaultComboBoxModel), searchFormPanel, gbc);
		
		LayoutHelper.addItem(2, 0, 0, 0, searchContainsNameBtn = new JButton("Tìm kiếm theo tên"), searchFormPanel, gbc);
		LayoutHelper.addItem(2, 1, 0, 0, searchByIDBtn = new JButton("Tìm kiếm theo mã hàng"), searchFormPanel, gbc);
		LayoutHelper.addItem(2, 2, 0, 0, searchByCategoryNameBtn = new JButton("Tìm kiếm theo mã loại hàng"), searchFormPanel, gbc);
		LayoutHelper.addItem(2, 3, 0, 0, searchBySupplierIDBtn = new JButton("Tìm kiếm theo mã nhà cung cấp"), searchFormPanel, gbc);
		
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
		
		productTable = new JTable(products);
		
		productTable.getTableHeader().setReorderingAllowed(false);
		productTable.getTableHeader().setResizingAllowed(false);
		
		productTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(productTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		tablePanel.add(scrollPane);
		
		return tablePanel;
	}
	
	
	public JPanel getPanel() {
		return panel;
	}

	public JButton getSearchContainsNameBtn() {
		return searchContainsNameBtn;
	}

	public void setSearchContainsNameBtn(JButton searchContainsNameBtn) {
		this.searchContainsNameBtn = searchContainsNameBtn;
	}

	public JButton getSearchByIDBtn() {
		return searchByIDBtn;
	}

	public void setSearchByIDBtn(JButton searchByIDBtn) {
		this.searchByIDBtn = searchByIDBtn;
	}

	

	public JButton getSearchByCategoryNameBtn() {
		return searchByCategoryNameBtn;
	}

	public void setSearchByCategoryNameBtn(JButton searchByCategoryNameBtn) {
		this.searchByCategoryNameBtn = searchByCategoryNameBtn;
	}

	public JButton getResetSearchBtn() {
		return resetSearchBtn;
	}

	public void setResetSearchBtn(JButton resetSearchBtn) {
		this.resetSearchBtn = resetSearchBtn;
	}

	public JTextField getSearchContainsNameTxTField() {
		return searchContainsNameTxTField;
	}

	public void setSearchContainsNameTxTField(JTextField searchContainsNameTxTField) {
		this.searchContainsNameTxTField = searchContainsNameTxTField;
	}

	public JTextField getSearchByIDTxtField() {
		return searchByIDTxtField;
	}

	public void setSearchByIDTxtField(JTextField searchByIDTxtField) {
		this.searchByIDTxtField = searchByIDTxtField;
	}

	public JTable getProductTable() {
		return productTable;
	}

	public void setProductTable(JTable productTable) {
		this.productTable = productTable;
	}

	
	

	public JButton getSearchBySupplierIDBtn() {
		return searchBySupplierIDBtn;
	}

	public void setSearchBySupplierIDBtn(JButton searchBySupplierIDBtn) {
		this.searchBySupplierIDBtn = searchBySupplierIDBtn;
	}

	public DefaultComboBoxModel<String> getCategoryDefaultComboxModel() {
		return categoryDefaultComboxModel;
	}

	public void setCategoryDefaultComboxModel(DefaultComboBoxModel<String> categoryDefaultComboxModel) {
		this.categoryDefaultComboxModel = categoryDefaultComboxModel;
	}

	public JComboBox<String> getCategoryNameCBox() {
		return categoryNameCBox;
	}

	public void setCategoryNameCBox(JComboBox<String> categoryNameCBox) {
		this.categoryNameCBox = categoryNameCBox;
	}

	public DefaultComboBoxModel<String> getSupplierDefaultComboBoxModel() {
		return supplierDefaultComboBoxModel;
	}

	public void setSupplierDefaultComboBoxModel(DefaultComboBoxModel<String> supplierDefaultComboBoxModel) {
		this.supplierDefaultComboBoxModel = supplierDefaultComboBoxModel;
	}

	public JComboBox<String> getSupplierNameCBox() {
		return supplierNameCBox;
	}

	public void setSupplierNameCBox(JComboBox<String> supplierNameCBox) {
		this.supplierNameCBox = supplierNameCBox;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}


	
	
	
	
}
