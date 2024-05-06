package view.statistical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.statistical.CustomerStatistics;
import model.statistical.EmployeeStatistics;
import model.statistical.ProductStatistics;
import util.ColorHelper;
import util.LayoutHelper;

public class StatisticalView {
	private JPanel pnlView;
	
	private ProductStatistics productStatistics;
	private JTable productDetailTable;
	
	private CustomerStatistics customerStatistics;
	private JTable customerDetailTable;
	
	private EmployeeStatistics employeeStatistics;
	private JTable employeeDetailTable;
	
	private JRadioButton byAll;
	private JRadioButton byYear;
	private JRadioButton byYearMonth;
	private JRadioButton byToday;
	
	private ButtonGroup buttonGroup;
	
	private JComboBox<Integer> yearCBox;
	private JComboBox<Integer> monthCBox;
	
	private JLabel totalAmountLabel;
	private JLabel invoiceCountLabel;

	public StatisticalView() {
		pnlView = LayoutHelper.getBorderLayout();
		
		pnlView.add(setupStatisticalPanel(), BorderLayout.CENTER);
		pnlView.add(setupSearchPanel(), BorderLayout.NORTH);
		pnlView.add(setupInfoPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel setupSearchPanel() {
		JPanel panel = LayoutHelper.getBorderLayout();
		panel.add(setupBtnPanel(), BorderLayout.NORTH);
		panel.add(setupCBoxPanel(), BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel setupInfoPanel() {
		JPanel panel = LayoutHelper.getBoxLayout(BoxLayout.X_AXIS);
		
		
		JPanel subPanel = new JPanel();
		
		totalAmountLabel = LayoutHelper.getTitle("");
		totalAmountLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
		totalAmountLabel.setIcon(new ImageIcon("src/images/btn_icon/calc_icon.png"));
		totalAmountLabel.setForeground(Color.WHITE);
		
		subPanel.add(totalAmountLabel);
		subPanel.setBackground(ColorHelper.getDarkerPrimaryColor());
		
		JPanel subPanel2 = new JPanel();
		
		invoiceCountLabel = LayoutHelper.getTitle("");
		invoiceCountLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
		invoiceCountLabel.setIcon(new ImageIcon("src/images/btn_icon/paper_icon.png"));
		invoiceCountLabel.setForeground(Color.WHITE);
		
		subPanel2.add(invoiceCountLabel);
		subPanel2.setBackground(ColorHelper.getDarkerPrimaryColor());
		
		panel.add(Box.createHorizontalGlue());
		panel.add(subPanel);
		panel.add(Box.createHorizontalStrut(50));
		panel.add(subPanel2);
		panel.add(Box.createHorizontalGlue());
		
		return panel;
	}
	
	private JPanel setupCBoxPanel() {
		JPanel CBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		Integer[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		yearCBox = new JComboBox<Integer>();
		monthCBox = new JComboBox<Integer>(months);
		
		CBoxPanel.add(new JLabel("Năm:"));
		CBoxPanel.add(yearCBox);
		CBoxPanel.add(new JLabel("Tháng:"));
		CBoxPanel.add(monthCBox);
		
		yearCBox.setEnabled(false);
		monthCBox.setEnabled(false);
		
		monthCBox.setPreferredSize(new Dimension(150, 20));
		yearCBox.setPreferredSize(new Dimension(150, 20));
		
		return CBoxPanel;
	}
	
	private JPanel setupBtnPanel() {
		JPanel panel = LayoutHelper.getBoxLayout(BoxLayout.Y_AXIS);
		
		byAll = new JRadioButton("Tất cả");
		byToday = new JRadioButton("Tháng hiện tại");
		byYear = new JRadioButton("Theo năm");
		byYearMonth = new JRadioButton("Theo tháng và năm");
			
		panel.add(byAll);
		panel.add(byToday);
		panel.add(byYear);
		panel.add(byYearMonth);
		
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(byAll);
		buttonGroup.add(byToday);
		buttonGroup.add(byYear);
		buttonGroup.add(byYearMonth);
		
		byAll.setSelected(true);
		
		return panel;
	}
	
	private JPanel setupStatisticalPanel() {
		JPanel panel = LayoutHelper.getGridBagLayout();
		GridBagConstraints gbc = LayoutHelper.getGbc();
		panel.setPreferredSize(new Dimension(0, 300));
		
		LayoutHelper.addItem(0, 0, 1, 1, setupProductStatisticalPanel(), panel, gbc);
		LayoutHelper.addItem(1, 0, 1, 1, setupCustomerStatisticalPanel(), panel, gbc);
		LayoutHelper.addItem(2, 0, 1, 1, setupEmployeeStatisticalPanel(), panel, gbc);
		
		return panel;
	}
	
	private JPanel setupProductStatisticalPanel() {
		JPanel panel = LayoutHelper.getBorderLayout();
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "TOP hàng hóa bán chạy"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		panel.setPreferredSize(new Dimension(0, 300));
		
		productStatistics = new ProductStatistics();
		productDetailTable = new JTable(productStatistics);
		
		JScrollPane scrollPane = new JScrollPane(productDetailTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel.add(scrollPane);
		
		return panel;
	}
	
	private JPanel setupCustomerStatisticalPanel() {
		JPanel panel = LayoutHelper.getBorderLayout();
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "TOP khách hàng có tổng tiền cao"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		panel.setPreferredSize(new Dimension(0, 300));
		
		customerStatistics = new CustomerStatistics();
		customerDetailTable = new JTable(customerStatistics);
		
		JScrollPane scrollPane = new JScrollPane(customerDetailTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel.add(scrollPane);
		
		return panel;
	}
	
	private JPanel setupEmployeeStatisticalPanel() {
		JPanel panel = LayoutHelper.getBorderLayout();
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "TOP nhân viên có tổng tiền cao"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		panel.setPreferredSize(new Dimension(0, 300));
		
		employeeStatistics = new EmployeeStatistics();
		employeeDetailTable = new JTable(employeeStatistics);
		
		JScrollPane scrollPane = new JScrollPane(employeeDetailTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel.add(scrollPane);
		
		return panel;
	}
	
	public ProductStatistics getProductStatistics() {
		return productStatistics;
	}

	public void setProductStatistics(ProductStatistics productStatistics) {
		this.productStatistics = productStatistics;
	}

	public JTable getProductDetailTable() {
		return productDetailTable;
	}

	public void setProductDetailTable(JTable productDetailTable) {
		this.productDetailTable = productDetailTable;
	}

	public CustomerStatistics getCustomerStatistics() {
		return customerStatistics;
	}

	public void setCustomerStatistics(CustomerStatistics customerStatistics) {
		this.customerStatistics = customerStatistics;
	}

	public JTable getCustomerDetailTable() {
		return customerDetailTable;
	}

	public void setCustomerDetailTable(JTable customerDetailTable) {
		this.customerDetailTable = customerDetailTable;
	}

	public EmployeeStatistics getEmployeeStatistics() {
		return employeeStatistics;
	}

	public void setEmployeeStatistics(EmployeeStatistics employeeStatistics) {
		this.employeeStatistics = employeeStatistics;
	}

	public JTable getEmployeeDetailTable() {
		return employeeDetailTable;
	}

	public void setEmployeeDetailTable(JTable employeeDetailTable) {
		this.employeeDetailTable = employeeDetailTable;
	}

	public JRadioButton getByAll() {
		return byAll;
	}

	public void setByAll(JRadioButton byAll) {
		this.byAll = byAll;
	}

	public JRadioButton getByYear() {
		return byYear;
	}

	public void setByYear(JRadioButton byYear) {
		this.byYear = byYear;
	}

	public JRadioButton getByYearMonth() {
		return byYearMonth;
	}

	public void setByYearMonth(JRadioButton byYearMonth) {
		this.byYearMonth = byYearMonth;
	}

	public JRadioButton getByToday() {
		return byToday;
	}

	public void setByToday(JRadioButton byToday) {
		this.byToday = byToday;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void setButtonGroup(ButtonGroup buttonGroup) {
		this.buttonGroup = buttonGroup;
	}

	public JComboBox<Integer> getYearCBox() {
		return yearCBox;
	}

	public void setYearCBox(JComboBox<Integer> yearCBox) {
		this.yearCBox = yearCBox;
	}

	public JComboBox<Integer> getMonthCBox() {
		return monthCBox;
	}

	public void setMonthCBox(JComboBox<Integer> monthCBox) {
		this.monthCBox = monthCBox;
	}

	public void setPnlView(JPanel pnlView) {
		this.pnlView = pnlView;
	}

	public JPanel getPnlView() {
		return pnlView;
	}


	public JLabel getTotalAmountLabel() {
		return totalAmountLabel;
	}

	public void setTotalAmountLabel(JLabel totalAmountLabel) {
		this.totalAmountLabel = totalAmountLabel;
	}

	public JLabel getInvoiceCountLabel() {
		return invoiceCountLabel;
	}

	public void setInvoiceCountLabel(JLabel invoiceCountLabel) {
		this.invoiceCountLabel = invoiceCountLabel;
	}
	
	
}
