package util;

import java.util.ArrayList;

import model.accounts.Account;
import model.accounts.AccountDAO;
import model.categories.Category;
import model.categories.CategoryDAO;
import model.customers.Customer;
import model.customers.CustomerDAO;
import model.employee.Employee;
import model.employee.EmployeeDAO;
import model.invoices.Invoice;
import model.invoices.InvoiceDAO;
import model.invoices.InvoiceDetails;
import model.invoices.InvoiceDetailsDAO;
import model.products.Product;
import model.products.ProductDAO;
import model.shelf.Shelf;
import model.shelf.ShelfDAO;
import model.suppliers.Supplier;
import model.suppliers.SupplierDAO;

public class FetchDataStatus {
	public static boolean accountStatus;
	public static boolean categoryStatus;
	public static boolean customerStatus;
	public static boolean employeeStatus;
	public static boolean invoiceStatus;
	public static boolean productStatus;
	public static boolean supplierStatus;
	public static boolean invoiceDetailsStatus;
	public static boolean shelfStatus;
	
	public static ArrayList<Account> accounts;
	public static ArrayList<Category> categories;
	public static ArrayList<Customer> customers;
	public static ArrayList<Employee> employees;
	public static ArrayList<Invoice> invoices;
	public static ArrayList<Supplier> suppliers;
	public static ArrayList<Shelf> shelfList;
	public static ArrayList<Product> products;
	public static ArrayList<InvoiceDetails> invoiceDetailsList;
	
	public static void loadAll() {
		customers = CustomerDAO.getData();
		customerStatus = true;
		
		categories = CategoryDAO.getData(); 
		categoryStatus = true;
		
		suppliers = SupplierDAO.getData();
		supplierStatus = true;
		
		shelfList =ShelfDAO.getData();
		shelfStatus =true;
		
		employees = EmployeeDAO.getData();
		employeeStatus = true;
		
		accounts = AccountDAO.getData();
		accountStatus = true;
		
		products = ProductDAO.getData();
		productStatus = true;
		
		invoices = InvoiceDAO.getData();
		invoiceStatus = true;
		
		invoiceDetailsList = InvoiceDetailsDAO.getData();
		invoiceDetailsStatus = true;
	}
	
	public static ArrayList<Employee> getEmployeeData() {
		if (employeeStatus) return employees;
		else {
			employees = EmployeeDAO.getData();
			employeeStatus = true;
			accountStatus = false;
			return employees;
		}
	}
	
	public static ArrayList<Category> getCategoryData() {
		if (categoryStatus) return categories;
		else {
			categories = CategoryDAO.getData(); 
			categoryStatus = true;
			productStatus = false;
			return categories;
		}
	}
	
	public static ArrayList<Supplier> getSupplierData() {
		if (supplierStatus) {
			return suppliers;
		}
		else {
			suppliers = SupplierDAO.getData();
			supplierStatus = true;
			productStatus = false;
			return suppliers;
		}
	}
	public static ArrayList<Shelf> getShelfData() {
		if (shelfStatus) {
			return shelfList;
		}
		else {
			shelfList = ShelfDAO.getData();
			shelfStatus = true;
			productStatus = false;
			return shelfList;
		}
	}
	
	public static ArrayList<Customer> getCustomerData() {
		if (customerStatus) return customers;
		else {
			customers = CustomerDAO.getData();
			customerStatus = true;
			invoiceStatus = false;
			return customers;
		}
	}
	
	public static ArrayList<Account> getAccountData() {
		if (accountStatus) return accounts;
		else {
			accounts = AccountDAO.getData();
			accountStatus = true;
			return accounts;
		}
	}
	
	public static ArrayList<Product> getProductData() {
		if (productStatus) return products;
		else {
			products = ProductDAO.getData();
			productStatus = true;
			categoryStatus = false;
			return products;
		}
	}
	
	public static ArrayList<Invoice> getInvoiceData() {
		if (invoiceStatus) return invoices;
		else {
			invoices = InvoiceDAO.getData();
			invoiceStatus = true;
			invoiceDetailsStatus = false;
			return invoices;
		}
	}
	
	public static ArrayList<InvoiceDetails> getInvoiceDetailsData() {
		if (invoiceDetailsStatus) return invoiceDetailsList;
		else {
			invoiceDetailsList = InvoiceDetailsDAO.getData();
			invoiceDetailsStatus = true;
			return invoiceDetailsList;
		}
	}
	
	
}
