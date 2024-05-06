package view.invoices;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;

import model.accounts.Account;
import model.customers.Customer;
import model.invoices.Invoice;
import model.invoices.InvoiceDetailsDAO;
import model.invoices.InvoiceDetailsList;
import model.invoices.Invoices;
import util.ColorHelper;
import util.FetchDataStatus;
import util.LayoutHelper;

public class InvoiceManagerView {
	private JPanel panel;

	private Account account;
	
	private JTable invoiceTable;
	private JTable invoiceDetailsTable;
	
	private Invoices invoices;
	private InvoiceDetailsList invoiceDetailsList;
	
	private JTextField invoiceIDTxtField;
	private JTextField employeeIDTxtField;
	private JTextField employeeNameTxtField;
	private JTextField customerIDTxtField;
	private JTextField customerNameTxtField;
	private JTextField customerTypeTxtField;
	private JDateChooser invoiceDateTxtField;
	
	public InvoiceManagerView(Account account) {
		panel = LayoutHelper.getBorderLayout();
		this.account = account;
		this.invoices = new Invoices(FetchDataStatus.getInvoiceData());
		this.invoiceDetailsList = new InvoiceDetailsList(FetchDataStatus.getInvoiceDetailsData());
		
		panel.add(setupNorthPanel(), BorderLayout.NORTH);
		panel.add(setupInvoiceDetailTablePanel(), BorderLayout.CENTER);
	}
	
	private JPanel setupNorthPanel() {
		JPanel northPanel = LayoutHelper.getBorderLayout();
		northPanel.setPreferredSize(new Dimension(0, 300));
		
		northPanel.add(setupInvoiceTablePanel(), BorderLayout.EAST);
		northPanel.add(setupInvoiceInfoPanel(), BorderLayout.CENTER);
		
		return northPanel;
	}
	
	private JPanel setupInvoiceInfoPanel() {
		JPanel invoiceInfoPanel = LayoutHelper.getGridBagLayout();
		invoiceInfoPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin hóa đơn"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		
		LayoutHelper.addItem(0, 0, 0, 0, new JLabel("Mã hóa đơn:"), invoiceInfoPanel, gbc);
		LayoutHelper.addItem(1, 0, 1, 0, invoiceIDTxtField = new JTextField(10), invoiceInfoPanel, gbc);
		LayoutHelper.addItem(2, 0, 0, 0, new JLabel("Mã nhân viên:"), invoiceInfoPanel, gbc);
		LayoutHelper.addItem(3, 0, 1, 0, employeeIDTxtField = new JTextField(10), invoiceInfoPanel, gbc);
		
		LayoutHelper.addItem(0, 1, 0, 0, new JLabel("Tên nhân viên:"), invoiceInfoPanel, gbc);
		LayoutHelper.addItem(1, 1, 1, 0, employeeNameTxtField = new JTextField(10), invoiceInfoPanel, gbc);
		LayoutHelper.addItem(2, 1, 0, 0, new JLabel("Mã khách hàng:"), invoiceInfoPanel, gbc);
		LayoutHelper.addItem(3, 1, 1, 0, customerIDTxtField = new JTextField(10), invoiceInfoPanel, gbc);
		
		LayoutHelper.addItem(0, 2, 0, 0, new JLabel("Tên khách hàng:"), invoiceInfoPanel, gbc);
		LayoutHelper.addItem(1, 2, 1, 0, customerNameTxtField = new JTextField(10), invoiceInfoPanel, gbc);
		LayoutHelper.addItem(2, 2, 0, 0, new JLabel("Loại khách hàng:"), invoiceInfoPanel, gbc);
		LayoutHelper.addItem(3, 2, 1, 0, customerTypeTxtField = new JTextField(10), invoiceInfoPanel, gbc);
		
		LayoutHelper.addItem(0, 3, 0, 0, new JLabel("Ngày lập hóa đơn"), invoiceInfoPanel, gbc);
		LayoutHelper.addItem(1, 3, 1, 0, invoiceDateTxtField = new JDateChooser(), invoiceInfoPanel, gbc);
		
		invoiceIDTxtField.setEditable(false);
		employeeIDTxtField.setEditable(false);
		employeeNameTxtField.setEditable(false);
		customerIDTxtField.setEditable(false);
		customerNameTxtField.setEditable(false);
		customerTypeTxtField.setEditable(false);
		invoiceDateTxtField.setEnabled(false);

		return invoiceInfoPanel;
	}
	
	private JPanel setupInvoiceTablePanel() {
		JPanel invoiceTablePanel = LayoutHelper.getBorderLayout();
		
		invoiceTablePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Danh sách hóa đơn"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		invoiceTablePanel.setPreferredSize(new Dimension(900, 0));
		
		invoiceTable = new JTable(invoices);
		invoiceTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		invoiceTable.getSelectionModel().addListSelectionListener(event -> {
			if (!event.getValueIsAdjusting()) {
				int selectedRow = invoiceTable.getSelectedRow();
				if (selectedRow != -1) showTxtField(selectedRow);
				else resetTxtField();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(invoiceTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		invoiceTablePanel.add(scrollPane);
		
		return invoiceTablePanel;
	}
	
	private JPanel setupInvoiceDetailTablePanel() {
		JPanel invoiceDetailTablePanel = LayoutHelper.getBorderLayout();
		invoiceDetailTablePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Danh sách chi tiết hóa đơn"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		invoiceDetailsTable = new JTable(invoiceDetailsList);
	
		JScrollPane scrollPane = new JScrollPane(invoiceDetailsTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		invoiceDetailTablePanel.add(scrollPane);
		
		return invoiceDetailTablePanel;
	}
	
	private void showTxtField(int selectedRow) {
		Invoice invoice = invoices.getInvoices().get(selectedRow);
		
		invoiceIDTxtField.setText(invoice.getInvoiceID());
		employeeIDTxtField.setText(invoice.getAccount().getEmployee().getEmployeeID());
		employeeNameTxtField.setText(invoice.getAccount().getEmployee().getFirstName() + " " + invoice.getAccount().getEmployee().getLastName());
		
		Customer customer = invoice.getCustomer();
		if (customer != null) {
			customerIDTxtField.setText(customer.getCustomerID());
			customerNameTxtField.setText(customer.getFirstName() + " " + customer.getLastName());
			customerTypeTxtField.setText(customer.getCustomerType().toString());
		} else {
			String text = "Vãng lai";
			customerIDTxtField.setText(text);
			customerNameTxtField.setText(text);
			customerTypeTxtField.setText(text);
		}
		
		invoiceDateTxtField.setDate(invoice.getInvoiceDate());
		invoiceDetailsList.setInvoiceDetailsList(InvoiceDetailsDAO.getDataByInvoiceID(invoice.getInvoiceID()));
	}
	
	private void resetTxtField() {
		invoiceIDTxtField.setText(null);
		employeeIDTxtField.setText(null);
		employeeNameTxtField.setText(null);
		customerIDTxtField.setText(null);
		customerNameTxtField.setText(null);
		customerTypeTxtField.setText(null);
		invoiceDateTxtField.setDate(null);
		invoiceDetailsList.setInvoiceDetailsList(InvoiceDetailsDAO.getData());
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

	public Invoices getInvoices() {
		return invoices;
	}

	public void setInvoices(Invoices invoices) {
		this.invoices = invoices;
	}

	public JTable getInvoiceTable() {
		return invoiceTable;
	}

	public void setInvoiceTable(JTable invoiceTable) {
		this.invoiceTable = invoiceTable;
	}

	public InvoiceDetailsList getInvoiceDetailsList() {
		return invoiceDetailsList;
	}

	public void setInvoiceDetailsList(InvoiceDetailsList invoiceDetailsList) {
		this.invoiceDetailsList = invoiceDetailsList;
	}

	public JTable getInvoiceDetailsTable() {
		return invoiceDetailsTable;
	}

	public void setInvoiceDetailsTable(JTable invoiceDetailsTable) {
		this.invoiceDetailsTable = invoiceDetailsTable;
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

	public JTextField getEmployeeNameTxtField() {
		return employeeNameTxtField;
	}

	public void setEmployeeNameTxtField(JTextField employeeNameTxtField) {
		this.employeeNameTxtField = employeeNameTxtField;
	}

	public JTextField getCustomerIDTxtField() {
		return customerIDTxtField;
	}

	public void setCustomerIDTxtField(JTextField customerIDTxtField) {
		this.customerIDTxtField = customerIDTxtField;
	}

	public JTextField getCustomerNameTxtField() {
		return customerNameTxtField;
	}

	public void setCustomerNameTxtField(JTextField customerNameTxtField) {
		this.customerNameTxtField = customerNameTxtField;
	}

	public JTextField getCustomerTypeTxtField() {
		return customerTypeTxtField;
	}

	public void setCustomerTypeTxtField(JTextField customerTypeTxtField) {
		this.customerTypeTxtField = customerTypeTxtField;
	}

	public JDateChooser getInvoiceDateTxtField() {
		return invoiceDateTxtField;
	}

	public void setInvoiceDateTxtField(JDateChooser invoiceDateTxtField) {
		this.invoiceDateTxtField = invoiceDateTxtField;
	}

	
	
	
	
	
	
}
