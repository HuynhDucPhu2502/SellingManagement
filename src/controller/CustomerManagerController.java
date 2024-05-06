package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import model.customers.Customer;
import model.customers.CustomerDAO;
import model.customers.CustomerType;
import model.customers.Customers;
import util.FetchDataStatus;
import view.customers.CustomerManagerView;

public class CustomerManagerController implements ActionListener{

	private CustomerManagerView customerManagerView;
	private Customers customers;
	
	private String nextCustomerID;
	
	public CustomerManagerController( CustomerManagerView customerManagerView) {
		this.customerManagerView = customerManagerView;
		this.customers = customerManagerView.getCustomers();
		
		this.addAction();
	}
	
	private void addAction() {
		this.customerManagerView.getAddBtn().addActionListener(this);
		this.customerManagerView.getRemoveBtn().addActionListener(this);
		this.customerManagerView.getUpdateBtn().addActionListener(this);
		setupTableSelection();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(customerManagerView.getAddBtn())) {
			this.addCus();
		}
		else if (o.equals(customerManagerView.getRemoveBtn())) {
			this.removeCus();	
			return;
		}
		else if (o.equals(customerManagerView.getUpdateBtn())) {
			this.updateInforOfCus();
		}
	}
	
	public void resetAll() {
		customerManagerView.getCustomers().setListCustomers(FetchDataStatus.getCustomerData());
		handleNextSupplierID();
	}
	
	private void addCus() {
		Customer temp = createCustomer();
	
		if(temp == null)
			return;
		
		if(CustomerDAO.insertData(temp)) {
			customers.setListCustomers(FetchDataStatus.getCustomerData());
			handleNextSupplierID();
			JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
		} else JOptionPane.showMessageDialog(null, "Khách hàng đã tồn tại");

	}
	
	private void removeCus() {
		int index = customerManagerView.getCustomerTable().getSelectedRow();
		
		if(index < 0) {
			JOptionPane.showMessageDialog(null, "Chưa chọn đối tượng xóa");
			return;
		}
		
		String c_ID = customers.getCustomerByIndex(index).getCustomerID();
		
		if (CustomerDAO.deleteData(c_ID)) {
			customers.setListCustomers(FetchDataStatus.getCustomerData());
			handleNextSupplierID();
			JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công");
			this.setFormEmpty();
		} else JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại");
		
	}
	
	private void updateInforOfCus() {
		int index = customerManagerView.getCustomerTable().getSelectedRow();
		
		
		if(index < 0) {
			JOptionPane.showMessageDialog(null, "Chưa chọn đối tượng sửa thông tin");
			return;
		}
		
		Customer newInfor = this.createCustomer();
		if(newInfor == null) {
			return;
		}
		
		if (CustomerDAO.updateData(newInfor)) {
			customers.setListCustomers(FetchDataStatus.getCustomerData());
			handleNextSupplierID();
			JOptionPane.showMessageDialog(null, "Sửa thông tin thành công");
		} else JOptionPane.showMessageDialog(null, "Sửa thông tin thất bại");
	}
	
	private void setupTableSelection() {
		customerManagerView.getCustomerTable().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerManagerView.getCustomerTable().getSelectionModel().addListSelectionListener(event -> {
			if (!event.getValueIsAdjusting()) {
				int index = customerManagerView.getCustomerTable().getSelectedRow();
				if (index != -1) showTxtField(index);
				else setFormEmpty();
			}
		});
	}
	
	private void showTxtField(int selectedIndex) {
		Customer customer = customers.getCustomerByIndex(selectedIndex);

		customerManagerView.getCustomerIDTxtField().setText(customer.getCustomerID());
		customerManagerView.getFirstNameTxtField().setText(customer.getFirstName());
		customerManagerView.getLastNameTxtField().setText(customer.getLastName());
		customerManagerView.getAddressTxtField().setText(customer.getAddress());
		customerManagerView.getPhoneNumbeTxtField().setText(customer.getPhoneNumber());
		customerManagerView.getCbb_Type().setSelectedItem(customer.getCustomerType());
	}
	
	private Customer createCustomer() {
		String c_ID = customerManagerView.getCustomerIDTxtField().getText();
		if(c_ID.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Mã khách hàng không được rỗng");
			customerManagerView.getCustomerIDTxtField().requestFocus();
			return null;
		}
		
		
		String FirstName = customerManagerView.getFirstNameTxtField().getText();
		if(FirstName.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Họ khách hàng không được rỗng");
			customerManagerView.getFirstNameTxtField().requestFocus();
			return null;
		}
		
		String LastName = customerManagerView.getLastNameTxtField().getText();
		if(LastName.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tên khách hàng không được rỗng");
			customerManagerView.getLastNameTxtField().requestFocus();
			return null;
		}
		
		String Address = customerManagerView.getAddressTxtField().getText();
		if(Address.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng không được rỗng");
			customerManagerView.getAddressTxtField().requestFocus();
			return null;
		}
		
		
		
		String Phone = customerManagerView.getPhoneNumbeTxtField().getText();
		if(Phone.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Địa chỉ khách hàng không được rỗng");
			customerManagerView.getPhoneNumbeTxtField().requestFocus();
			return null;
		}
		
		Customer customer = customers.getListCustomers().stream().filter(x -> x.getPhoneNumber().equalsIgnoreCase(Phone))
				.findFirst().orElse(null);
		
		if (customer != null) {
			JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại");
			return null;
		}
		
		Customer customer2 = customers.getListCustomers().stream().filter(x -> x.getCustomerID().equalsIgnoreCase(c_ID))
				.findFirst().orElse(null);
		
		if (customer2 != null) {
			JOptionPane.showMessageDialog(null, "Mã đã tồn tại");
			return null;
		}
		
		
		CustomerType customerType= (CustomerType)customerManagerView
				.getCbb_Type().getSelectedItem();
		
		return new Customer(c_ID, FirstName, LastName, Address, Phone,customerType);
	}
	
	private void setFormEmpty() {
		customerManagerView.getCustomerIDTxtField().setText(null);
		customerManagerView.getFirstNameTxtField().setText(null);
		customerManagerView.getLastNameTxtField().setText(null);
		customerManagerView.getAddressTxtField().setText(null);
		customerManagerView.getPhoneNumbeTxtField().setText(null);
		customerManagerView.getCbb_Type().setSelectedIndex(0);
		customerManagerView.getCustomerTable().setRowSorter(null);
		handleNextSupplierID();
	}
	
	private void handleNextSupplierID() {
		
		if (customers.getListCustomers().isEmpty()) nextCustomerID = "CUST001";
		else {
			String lastCustomerID = customers.getListCustomers().get(customers.getListCustomers().size() - 1).getCustomerID();
			String numberPart = lastCustomerID.substring(4);
			int nextNumber = Integer.parseInt(numberPart) + 1;
			nextCustomerID = "CUST" + String.format("%03d", nextNumber);
		}
		
		customerManagerView.getCustomerIDTxtField().setText(nextCustomerID);
	}
	
	

}
