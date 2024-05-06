package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.customers.Customer;
import util.FetchDataStatus;
import view.customers.CustomerSearchView;



public class CustomerSearchController implements ActionListener {
	private CustomerSearchView customerSearchView;

	public CustomerSearchController(CustomerSearchView customerSearchView) {
		this.customerSearchView = customerSearchView;
		
		// load emp to table
		this.readingDataFromDBToTable(FetchDataStatus.getCustomerData());
		
		// add action
		this.addAction();
	}
	
	private void addAction() {
		customerSearchView.getBtn_searchByName().addActionListener(this);
		customerSearchView.getBtn_searchById().addActionListener(this);
		customerSearchView.getBtn_searchByType().addActionListener(this);
		customerSearchView.getResetSearchBtn().addActionListener(this);
	}
	
	private void readingDataFromDBToTable(List<Customer> listCustomer) {
		for(Customer e : listCustomer) {
			customerSearchView.getDfTbCus().addRow(new Object[] {e.getCustomerID(), 
					e.getLastName(), e.getFirstName(), e.getAddress(), e.getPhoneNumber(), e.getCustomerType().toString()});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(customerSearchView.getBtn_searchByName())) {
			this.searchByName();
		}else if(o.equals(customerSearchView.getBtn_searchById())) {
			this.searchByID();
		}else if(o.equals(customerSearchView.getBtn_searchByType())) {
			this.searchByType();
		}else if(o.equals(customerSearchView.getResetSearchBtn())) {
			this.reset();
		}
	}
	
	private void searchByName() {
		String name = customerSearchView.getSearchByName().getText().trim();
		if (name.isEmpty()) {
		    JOptionPane.showMessageDialog(null, "Chưa nhập tên khách hàng để tìm kiếm");
		    customerSearchView.getSearchByName().requestFocus();
		    return;
		}

		List<Customer> dsCus = FetchDataStatus.getCustomerData();
		List<Customer> dsCusContainsName = new ArrayList<>();

		for (int i = 0; i < dsCus.size(); i++) {
		    if (dsCus.get(i).getFirstName().toUpperCase().contains(name.toUpperCase())) {
		        dsCusContainsName.add(dsCus.get(i));
		    }
		}

		customerSearchView.getDfTbCus().setRowCount(0);
		this.readingDataFromDBToTable(dsCusContainsName);
	}
	
	private void searchByID() {
		String id = customerSearchView.getSearchById().getText().trim();
		if (id.isEmpty()) {
		    JOptionPane.showMessageDialog(null, "Chưa nhập khách hàng viên để tìm kiếm");
		    customerSearchView.getSearchById().requestFocus();
		    return;
		}

		List<Customer> dsCus = FetchDataStatus.getCustomerData();
		List<Customer> dsCusId = new ArrayList<>();

		for (int i = 0; i < dsCus.size(); i++) {
		    if(dsCus.get(i).getCustomerID().equalsIgnoreCase(id)) {
		    	dsCusId.add(dsCus.get(i));
		    }
		}

		customerSearchView.getDfTbCus().setRowCount(0);
		this.readingDataFromDBToTable(dsCusId);
	}
	
	private void searchByType() {
		String positon = customerSearchView.getSearchByType().getSelectedItem().toString();
		
		List<Customer> dsCus = FetchDataStatus.getCustomerData();
		List<Customer> dsCusId = new ArrayList<>();

		for (int i = 0; i < dsCus.size(); i++) {
		    if(dsCus.get(i).getCustomerType().toString().equalsIgnoreCase(positon)) {
		    	dsCusId.add(dsCus.get(i));
		    }
		}

		customerSearchView.getDfTbCus().setRowCount(0);
		this.readingDataFromDBToTable(dsCusId);
	}
	
	public void reset() {
		customerSearchView.getDfTbCus().setRowCount(0);
		this.readingDataFromDBToTable(FetchDataStatus.getCustomerData());
	}
}
