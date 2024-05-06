package view.invoices;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import model.accounts.Account;
import model.invoices.ProductsInCart;
import model.invoices.ProductsSummary;
import util.ColorHelper;
import util.FetchDataStatus;
import util.LayoutHelper;

public class NewInvoiceView {
	private JPanel panel;
	
	private Account account;
	
	private ProductsSummary productsList;
	private ProductsInCart cartList;
	
	private JTable productsListTable;
	private JTable cartListTable;
	
	private JTextField invoiceIDTxtField;
	private JTextField employeeIDTxtField;
	private JTextField phoneNumberTxtField;
	private JTextField customerIDTxtField;
	private JTextField customerNameTxtField;
	private JTextField customerPaymentAmountTxtField;
	private JTextField changeAmountTxtField;
	private JTextField addressTxtField;
	private JTextField customerTypeTxtField;
	private JTextField employeeNameTxtField;
	private JDateChooser invoiceDateTxtField;
	
	private JTextField finalTotal;
	private JTextField taxAmount;
	private JTextField subTotalTxtField;
	
	private JTextField searchTxtField;
	
	private JButton searchBtn;
	private JButton removeOneItemBtn;
	private JButton removeAllItemBtn;
	private JButton newInvoiceBtn;
	private JButton resetBtn;
	
	

	public NewInvoiceView(Account account) {
		this.account = account;
		panel = LayoutHelper.getBorderLayout();
		
		productsList = new ProductsSummary(FetchDataStatus.getProductData());
		cartList = new ProductsInCart();
		
		panel.add(setupCenterPanel(), BorderLayout.CENTER);
		panel.add(setupEastPanel(), BorderLayout.EAST);
		panel.add(setupInvoiceBtnPanel(), BorderLayout.SOUTH);
		
	}
	
	private JPanel setupCenterPanel() {
		JPanel centerTable = LayoutHelper.getBorderLayout();
		centerTable.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Danh sách sản phẩm"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		centerTable.add(setupProductListTable(), BorderLayout.CENTER);
		centerTable.add(setupSearchPanel(), BorderLayout.NORTH);
		
		return centerTable;
	}
	
	private JPanel setupSearchPanel() {
		JPanel searchPanel = LayoutHelper.getBoxLayout(BoxLayout.X_AXIS);
		
		searchPanel.add(new JLabel("Tìm kiếm sản phẩm theo tên:"));
		searchPanel.add(Box.createHorizontalStrut(5));
		searchPanel.add(searchTxtField = new JTextField());
		searchPanel.add(Box.createHorizontalStrut(5));
		searchPanel.add(searchBtn = LayoutHelper.setupBtn("Tìm kiếm", "src/Images/btn_icon/search_icon.png"));
		
		return searchPanel;
	}
	
	private JPanel setupProductListTable() {
		JPanel productListPanel = LayoutHelper.getBorderLayout();
		
		productsListTable = new JTable(productsList);
		
		
		JScrollPane scrollPane = new JScrollPane(productsListTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		productListPanel.add(scrollPane);
		
		return productListPanel;
	}
	
	private JPanel setupEastPanel() {
		JPanel eastPanel = LayoutHelper.getBorderLayout();
		eastPanel.setPreferredSize(new Dimension(600, 0));
		
		eastPanel.add(setupCartPanel(), BorderLayout.CENTER);
		eastPanel.add(setupFormPanel(), BorderLayout.SOUTH);
		
		return eastPanel;
	}
	
	private JPanel setupCartPanel() {
		JPanel cartPanel = LayoutHelper.getBorderLayout();
		cartPanel.setPreferredSize(new Dimension(0, 275));
		cartPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Giỏ hàng"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		
		cartPanel.add(setupCartTable(), BorderLayout.CENTER);
		cartPanel.add(setupCartButtonPanel(), BorderLayout.SOUTH);
		
		return cartPanel;
	}
	
	private JPanel setupCartTable() {
		JPanel cartTablePanel = LayoutHelper.getBorderLayout();
		
		cartListTable = new JTable(cartList);
		
		
		JScrollPane scrollPane = new JScrollPane(cartListTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		cartTablePanel.add(scrollPane);
		
		return cartTablePanel;
	}
	
	private JPanel setupCartButtonPanel() {
		JPanel cartButtonPanel = LayoutHelper.getGridBagLayout();
		GridBagConstraints gbc = LayoutHelper.getGbc();
		
		LayoutHelper.addItem(0, 0, 0, 0, removeOneItemBtn =  LayoutHelper.setupBtn("Xóa một", "src/Images/btn_icon/remove_icon.png"), cartButtonPanel, gbc);
		LayoutHelper.addItem(1, 0, 0, 0, removeAllItemBtn =  LayoutHelper.setupBtn("Xóa tất cả", "src/Images/btn_icon/remove_all_icon.png"), cartButtonPanel, gbc);
		
		return cartButtonPanel;
	}
	
	private JPanel setupFormPanel() {
		JPanel formPanel = LayoutHelper.getGridBagLayout();
		formPanel.setPreferredSize(new Dimension(0, 275));
		formPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin hóa đơn"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		
		LayoutHelper.addItem(0, 0, 0, 0, new JLabel("Mã hóa đơn:"), formPanel, gbc);
		LayoutHelper.addItem(1, 0, 1, 0, invoiceIDTxtField = new JTextField(10), formPanel, gbc);
		LayoutHelper.addItem(2, 0, 0, 0, new JLabel("Mã NV:"), formPanel, gbc);
		LayoutHelper.addItem(3, 0, 1, 0, employeeIDTxtField = new JTextField(10), formPanel, gbc);
		
		LayoutHelper.addItem(0, 1, 0, 0, new JLabel("Tên NV:"), formPanel, gbc);
		LayoutHelper.addItem(1, 1, 0, 0, employeeNameTxtField = new JTextField(10), formPanel, gbc);
		LayoutHelper.addItem(2, 1, 0, 0, new JLabel("Tổng tiền:"), formPanel, gbc);
		LayoutHelper.addItem(3, 1, 0, 0, subTotalTxtField = new JTextField(10), formPanel, gbc);
		
		LayoutHelper.addItem(0, 2, 0, 0, new JLabel("Mã KH:"), formPanel, gbc);
		LayoutHelper.addItem(1, 2, 0, 0, customerIDTxtField = new JTextField(10), formPanel, gbc);
		LayoutHelper.addItem(2, 2, 0, 0, new JLabel("Thuế (VAT 10%):"), formPanel, gbc);
		LayoutHelper.addItem(3, 2, 0, 0, taxAmount = new JTextField(10), formPanel, gbc);
		
		LayoutHelper.addItem(0, 3, 0, 0, new JLabel("Tên KH:"), formPanel, gbc);
		LayoutHelper.addItem(1, 3, 0, 0, customerNameTxtField = new JTextField(10), formPanel, gbc);
		LayoutHelper.addItem(2, 3, 0, 0, new JLabel("Thành tiền:"), formPanel, gbc);
		LayoutHelper.addItem(3, 3, 0, 0, finalTotal = new JTextField(10), formPanel, gbc);
		
		LayoutHelper.addItem(0, 4, 0, 0, new JLabel("Địa chỉ:"), formPanel, gbc);
		LayoutHelper.addItem(1, 4, 0, 0, addressTxtField = new JTextField(10), formPanel, gbc);
		LayoutHelper.addItem(2, 4, 0, 0, new JLabel("Tiền trả:"), formPanel, gbc);
		LayoutHelper.addItem(3, 4, 0, 0, customerPaymentAmountTxtField = new JTextField(10), formPanel, gbc);
		
		
		LayoutHelper.addItem(0, 5, 0, 0, new JLabel("Loại khách hàng:"), formPanel, gbc);
		LayoutHelper.addItem(1, 5, 0, 0, customerTypeTxtField = new JTextField(), formPanel, gbc);
		LayoutHelper.addItem(2, 5, 0, 0, new JLabel("Tiền thối:"), formPanel, gbc);
		LayoutHelper.addItem(3, 5, 0, 0, changeAmountTxtField = new JTextField(10), formPanel, gbc);
		
		LayoutHelper.addItem(0, 6, 0, 0, new JLabel("SĐT KH:"), formPanel, gbc);
		LayoutHelper.addItem(1, 6, 0, 0, phoneNumberTxtField = new JTextField(10), formPanel, gbc);
		LayoutHelper.addItem(2, 6, 0, 0, new JLabel("Ngày lập hóa đơn:"), formPanel, gbc);
		LayoutHelper.addItem(3, 6, 0, 0, invoiceDateTxtField = new JDateChooser(), formPanel, gbc);
		
		employeeIDTxtField.setEditable(false);
		employeeNameTxtField.setEditable(false);
		customerTypeTxtField.setEditable(false);
		taxAmount.setEditable(false);
		customerIDTxtField.setEditable(false);
		finalTotal.setEditable(false);
		customerNameTxtField.setEditable(false);
		addressTxtField.setEditable(false);
		changeAmountTxtField.setEditable(false);
		invoiceIDTxtField.setEditable(false);
		subTotalTxtField.setEditable(false);
		
		invoiceDateTxtField.setOpaque(true);
		invoiceDateTxtField.setDateFormatString("yyyy-MM-dd");
		invoiceDateTxtField.setEnabled(false);
		invoiceDateTxtField.setDate(new Date());
		
		return formPanel;
	}
	
	private JPanel setupInvoiceBtnPanel() {
		JPanel invoiceBtnPanel = LayoutHelper.getGridBagLayout();
		GridBagConstraints gbc = LayoutHelper.getGbc();
		
		LayoutHelper.addItem(0, 0, 1, 0, newInvoiceBtn =  LayoutHelper.setupBtn("Tạo hóa đơn", "src/Images/btn_icon/add_icon.png"), invoiceBtnPanel, gbc);
		LayoutHelper.addItem(1, 0, 1, 0, resetBtn =  LayoutHelper.setupBtn("Làm mới", "src/Images/btn_icon/reset_icon.png"), invoiceBtnPanel, gbc);
		
		newInvoiceBtn.setBackground(ColorHelper.getDarkerPrimaryColor());
		resetBtn.setBackground(ColorHelper.getDarkerPrimaryColor());
		
		return invoiceBtnPanel;
	}
	

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ProductsSummary getProductsList() {
		return productsList;
	}

	public void setProductsList(ProductsSummary productsList) {
		this.productsList = productsList;
	}

	

	public JButton getRemoveOneItemBtn() {
		return removeOneItemBtn;
	}

	public void setRemoveOneItemBtn(JButton removeOneItemBtn) {
		this.removeOneItemBtn = removeOneItemBtn;
	}

	public JButton getRemoveAllItemBtn() {
		return removeAllItemBtn;
	}

	public void setRemoveAllItemBtn(JButton removeAllItemBtn) {
		this.removeAllItemBtn = removeAllItemBtn;
	}

	public ProductsInCart getCartList() {
		return cartList;
	}

	public void setCartList(ProductsInCart cartList) {
		this.cartList = cartList;
	}

	public JTable getProductsListTable() {
		return productsListTable;
	}

	public void setProductsListTable(JTable productsListTable) {
		this.productsListTable = productsListTable;
	}

	public JTable getCartListTable() {
		return cartListTable;
	}

	public void setCartListTable(JTable cartListTable) {
		this.cartListTable = cartListTable;
	}

	public JTextField getInvoiceIDTxtField() {
		return invoiceIDTxtField;
	}

	public void setInvoiceIDTxtField(JTextField invoiceIDTxtField) {
		this.invoiceIDTxtField = invoiceIDTxtField;
	}

	public JTextField getEmployeeIDTxtField() {
		return employeeIDTxtField;
	}

	public void setEmployeeIDTxtField(JTextField employeeIDTxtField) {
		this.employeeIDTxtField = employeeIDTxtField;
	}

	public JTextField getPhoneNumberTxtField() {
		return phoneNumberTxtField;
	}

	public void setPhoneNumberTxtField(JTextField phoneNumberTxtField) {
		this.phoneNumberTxtField = phoneNumberTxtField;
	}

	public JTextField getVatTxtField() {
		return taxAmount;
	}

	public void setVatTxtField(JTextField vatTxtField) {
		this.taxAmount = vatTxtField;
	}

	public JTextField getCustomerIDTxtField() {
		return customerIDTxtField;
	}

	public void setCustomerIDTxtField(JTextField customerIDTxtField) {
		this.customerIDTxtField = customerIDTxtField;
	}

	

	public JTextField getFinalTotal() {
		return finalTotal;
	}

	public void setFinalTotal(JTextField finalTotal) {
		this.finalTotal = finalTotal;
	}

	public JTextField getSubTotalTxtField() {
		return subTotalTxtField;
	}

	public void setSubTotalTxtField(JTextField subTotalTxtField) {
		this.subTotalTxtField = subTotalTxtField;
	}

	public JTextField getCustomerNameTxtField() {
		return customerNameTxtField;
	}

	public void setCustomerNameTxtField(JTextField customerNameTxtField) {
		this.customerNameTxtField = customerNameTxtField;
	}

	public JTextField getCustomerPaymentAmountTxtField() {
		return customerPaymentAmountTxtField;
	}

	public void setCustomerPaymentAmountTxtField(JTextField customerPaymentAmountTxtField) {
		this.customerPaymentAmountTxtField = customerPaymentAmountTxtField;
	}

	public JTextField getChangeAmountTxtField() {
		return changeAmountTxtField;
	}

	public void setChangeAmountTxtField(JTextField changeAmountTxtField) {
		this.changeAmountTxtField = changeAmountTxtField;
	}

	public JTextField getAddressTxtField() {
		return addressTxtField;
	}

	public void setAddressTxtField(JTextField addressTxtField) {
		this.addressTxtField = addressTxtField;
	}

	public JDateChooser getInvoiceDateTxtField() {
		return invoiceDateTxtField;
	}

	public void setInvoiceDateTxtField(JDateChooser invoiceDateTxtField) {
		this.invoiceDateTxtField = invoiceDateTxtField;
	}

	public JTextField getCustomerTypeTxtField() {
		return customerTypeTxtField;
	}

	public void setCustomerTypeTxtField(JTextField customerTypeTxtField) {
		this.customerTypeTxtField = customerTypeTxtField;
	}

	public JTextField getEmployeeNameTxtField() {
		return employeeNameTxtField;
	}

	public void setEmployeeNameTxtField(JTextField employeeNameTxtField) {
		this.employeeNameTxtField = employeeNameTxtField;
	}

	public JTextField getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(JTextField taxAmount) {
		this.taxAmount = taxAmount;
	}

	public JButton getNewInvoiceBtn() {
		return newInvoiceBtn;
	}

	public void setNewInvoiceBtn(JButton newInvoiceBtn) {
		this.newInvoiceBtn = newInvoiceBtn;
	}

	public JButton getResetBtn() {
		return resetBtn;
	}

	public void setResetBtn(JButton resetBtn) {
		this.resetBtn = resetBtn;
	}

	

	public JTextField getSearchTxtField() {
		return searchTxtField;
	}

	public void setSearchTxtField(JTextField searchTxtField) {
		this.searchTxtField = searchTxtField;
	}

	public JButton getSearchBtn() {
		return searchBtn;
	}

	public void setSearchBtn(JButton searchBtn) {
		this.searchBtn = searchBtn;
	}

	

	

	
	
	
	
	
}
