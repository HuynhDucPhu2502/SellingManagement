package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.accounts.AccountInfo;
import view.base.AuthFrame;
import view.base.MainFrame;

public class MainFrameController implements ActionListener {
	private MainFrame mainFrame;

	public MainFrameController(MainFrame mainFrame) {
		super();
		this.mainFrame = mainFrame;
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == mainFrame.getAccountInfoMenuItem())
			new AccountInfo(mainFrame.getAccount(), mainFrame);
		else if (source == mainFrame.getLogoutAccountMenuItem())
			handleLogout();
		else {
			String title = "";
			
			if (source == mainFrame.getProductManagerMenuItem()) {
				title = "Quản lý hàng hóa";
				mainFrame.getProductManagerController().resetAll();
			}
			else if (source == mainFrame.getProductSearchMenuItem()) {
				title = "Tìm kiếm hàng hóa";
				mainFrame.getProductSearchController().resetAll();
			}
			else if (source == mainFrame.getSupplierManagerMenuItem()) {
				title = "Quản lý nhà cung cấp";
				mainFrame.getCustomerManagerController().resetAll();
			}
			else if (source == mainFrame.getEmployeeManagerMenuItem()) {
				title = "Quản lý nhân viên";
				mainFrame.getEmployeeManagerController().resetAll();
			}
			else if (source == mainFrame.getCustomerManagerMenuItem()) {
				title = "Quản lý khách hàng";
				mainFrame.getCustomerManagerController().resetAll();
			}
			else if (source == mainFrame.getEmployeeSearhMenuItem()) { 
				title = "Tìm kiếm nhân viên";
				EmployeeSearchController.resetAll();
			}
			else if (source == mainFrame.getEmployeeFileMenuItem()) { 
				title = "Xuất file";
				FileManagerController.resetAll();
			}
			else if (source == mainFrame.getNewInvoiceMenuItem()) {
				title = "Tạo hóa đơn";
				mainFrame.getNewInvoiceController().resetAll();
			}
			else if (source == mainFrame.getInvoiceManagerMenuItem()) {
				title = "Quản lý hóa đơn";
				mainFrame.getInvoiceManagerController().resetAll();
			}
			else if (source == mainFrame.getAccountManagerMenuItem()) { 
				title = "Quản lí tài khoản";
				AccountManagerController.resetAll();
			}
			else if (source == mainFrame.getStatisticalManagerMenuItem()) {
				title = "Thống kế doanh thu";
			}
			else if (source == mainFrame.getSupplierSearchMenuItem()) {
				title = "Tìm kiếm nhà cung cấp";
				mainFrame.getSupplierController().resetAll();
			}
			else if (source == mainFrame.getCustomerSearchMenuItem()) {
				title = "Tìm kiếm khách hàng";
				mainFrame.getCustomerSearchController().reset();
			}
			else if (source == mainFrame.getShelfManagerMenuItem()) {
				title = "Quản lý kệ";
				mainFrame.getShelfManagerController().resetAll();
			}
			
			
			mainFrame.getCardLayout().show(mainFrame.getCardPanel(), title);
			mainFrame.updateTitle(title);
		}
			
		
	}
	
	private void handleLogout() {
		new AuthFrame();
		mainFrame.dispose();
	}
	

	
}
