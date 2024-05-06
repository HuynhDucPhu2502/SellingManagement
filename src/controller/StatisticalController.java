package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import model.statistical.StatisticalDAO;
import view.statistical.StatisticalView;

public class StatisticalController implements ActionListener {
	private StatisticalView statisticalView;
	
	int invoiceCount;

	public StatisticalController(StatisticalView statisticalView) {
		this.statisticalView = statisticalView;
		
		register();
	}
	
	private void register() {
		statisticalView.getYearCBox().removeAllItems();
		StatisticalDAO.getYears().forEach(x -> statisticalView.getYearCBox().addItem(x));
		statisticalView.getByAll().addActionListener(this);
		statisticalView.getByToday().addActionListener(this);
		statisticalView.getByYear().addActionListener(this);
		statisticalView.getByYearMonth().addActionListener(this);
		statisticalView.getMonthCBox().addActionListener(this);
		statisticalView.getYearCBox().addActionListener(this);
		invoiceCount = StatisticalDAO.getInvoiceCountByAll();
		setTxtField();
	}
	
	private void handleToggleCBox() {
		statisticalView.getMonthCBox().setEnabled(false);
		statisticalView.getYearCBox().setEnabled(false);
		
		if (statisticalView.getByYear().isSelected()) statisticalView.getYearCBox().setEnabled(true);
		else if (statisticalView.getByYearMonth().isSelected()) {
			statisticalView.getMonthCBox().setEnabled(true);
			statisticalView.getYearCBox().setEnabled(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == statisticalView.getByAll())
			handleGetAll();
		else if (source == statisticalView.getByToday())
			handleGetByYearMonth(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
		else if ((source == statisticalView.getByYear() || source == statisticalView.getYearCBox())
				&& statisticalView.getByYear().isSelected())
			handleGetByYear((int)statisticalView.getYearCBox().getSelectedItem());
		else if ((source == statisticalView.getByYearMonth() || source == statisticalView.getMonthCBox()
				|| source == statisticalView.getYearCBox()) && statisticalView.getByYearMonth().isSelected())
			handleGetByYearMonth((int)statisticalView.getYearCBox().getSelectedItem(), 
					(int)statisticalView.getMonthCBox().getSelectedItem());
		
		handleToggleCBox();
	}
	
	private void handleGetAll() {
		statisticalView.getEmployeeStatistics().setEmployeeDetails(StatisticalDAO.getEmployeeSalesTotalAll());
		statisticalView.getCustomerStatistics().setCustomerDetails(StatisticalDAO.getCustomerTotalPurchasesAll());
		statisticalView.getProductStatistics().setProductDetails(StatisticalDAO.getTotalSoldProductsQuantityAll());
		invoiceCount = StatisticalDAO.getInvoiceCountByAll();
		setTxtField();
	}
	
	private void handleGetByYearMonth(int year, int month) {
		statisticalView.getEmployeeStatistics().setEmployeeDetails(StatisticalDAO.getEmployeeSalesTotalByYearMonth(year, month));
		statisticalView.getCustomerStatistics().setCustomerDetails(StatisticalDAO.getCustomerTotalPurchasesByYearMonth(year, month));
		statisticalView.getProductStatistics().setProductDetails(StatisticalDAO.getTotalSoldProductsQuantityByYearMonth(year, month));
		invoiceCount = StatisticalDAO.getInvoiceCountByYearMonth(year, month);
		setTxtField();
	}
	
	private void handleGetByYear(int year) {
		statisticalView.getEmployeeStatistics().setEmployeeDetails(StatisticalDAO.getEmployeeSalesTotalByYear(year));
		statisticalView.getCustomerStatistics().setCustomerDetails(StatisticalDAO.getCustomerTotalPurchasesByYear(year));
		statisticalView.getProductStatistics().setProductDetails(StatisticalDAO.getTotalSoldProductsQuantityByYear(year));
		invoiceCount = StatisticalDAO.getInvoiceCountByYear(year);
		setTxtField();
	}
	
	private void setTxtField() {
		double invoiceAmount = statisticalView.getCustomerStatistics().getCustomerDetails()
				.stream().mapToDouble(x -> x.getTotalAmount())
				.sum();
		statisticalView.getTotalAmountLabel().setText("Doanh thu: " + invoiceAmount);
		statisticalView.getInvoiceCountLabel().setText("Số hóa đơn: " + invoiceCount);
	}
	
	
	
}
