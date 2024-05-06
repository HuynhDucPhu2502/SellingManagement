package model.invoices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import model.accounts.Account;
import model.accounts.AccountDAO;
import model.customers.Customer;
import model.customers.CustomerDAO;
import util.DBHelper;
import util.FetchDataStatus;

public class InvoiceDAO {
	public static ArrayList<Invoice> getData() {
		ArrayList<Invoice> data = new ArrayList<Invoice>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				){
			String sql = "SELECT InvoiceID, InvoiceDate, TotalAmount, "
					+ "CustomerID, EmployeeID FROM Invoice";
			ResultSet rs = statement.executeQuery(sql);
			
			ArrayList<Customer> customerList = FetchDataStatus.getCustomerData();
			ArrayList<Account> accountList = FetchDataStatus.getAccountData();
			
			while (rs.next()) {
				String invoiceID = rs.getString(1);
				Date invoiceDate = rs.getDate(2);
				double totalAmount = rs.getDouble(3);
				
				String customerID = rs.getString(4);
	            Customer customer = customerList.stream()
	            			.filter(x -> x.getCustomerID().equalsIgnoreCase(customerID))
	            			.findFirst()
	            			.orElse(null);
	            
	            
	            
	            String employeeID = rs.getString(5);
				Account account = accountList.stream()
            			.filter(x -> x.getEmployee().getEmployeeID().equalsIgnoreCase(employeeID))
            			.findFirst().orElse(null);
				
				
				Invoice invoice = new Invoice(invoiceID, account, customer, invoiceDate, totalAmount);
				
				data.add(invoice);
			}
			
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static String getLastInvoiceID() {
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				){
			String sql = "SELECT TOP 1 InvoiceID FROM Invoice ORDER BY InvoiceID DESC";
			ResultSet rs = statement.executeQuery(sql);
			
			if (rs.next()) return rs.getString(1);
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	public static void insertInvoice(Invoice invoice) {
	    try (
	        Connection connection = DBHelper.getConnection();
	        PreparedStatement insertStatement = connection.prepareStatement(
	            "INSERT INTO Invoice(InvoiceID, EmployeeID, CustomerID, InvoiceDate, TotalAmount) VALUES (?, ?, ?, ?, ?)"
	        );
	    ) {
	        insertStatement.setString(1, invoice.getInvoiceID());
	        insertStatement.setString(2, invoice.getAccount().getEmployee().getEmployeeID());
	        
	        if (invoice.getCustomer() == null) {
	            insertStatement.setNull(3, java.sql.Types.NVARCHAR);
	        } else {
	            insertStatement.setString(3, invoice.getCustomer().getCustomerID());
	        }
	        
	        insertStatement.setDate(4, new java.sql.Date(invoice.getInvoiceDate().getTime()));
	        insertStatement.setDouble(5, invoice.getTotalAmount());

	        insertStatement.execute();
	    } catch (Exception exception) {
	        exception.printStackTrace();
	        System.exit(1);
	    }
	    
	    FetchDataStatus.invoiceStatus = false;
	}
	
	public static Invoice getInvoiceByID(String invoiceID) {
	    try (
	        Connection connection = DBHelper.getConnection();
	        PreparedStatement statement = connection.prepareStatement(
	        	"SELECT InvoiceID, InvoiceDate, TotalAmount, "
	    		+ "CustomerID, EmployeeID FROM Invoice "
	            + "WHERE InvoiceID = ?")
	    ) {
	        statement.setString(1, invoiceID);
	        ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				Date invoiceDate = rs.getDate(2);
				double totalAmount = rs.getDouble(3);
				
				Customer customer = null;
	            if (rs.getString(4) != null) {
	            	customer = CustomerDAO.getCustomerByCustomerID(rs.getString(4));
	            }
	            
	            String employeeID = rs.getString(5);
				Account account = AccountDAO.getAccountByEmployeeID(employeeID);
				
				return new Invoice(invoiceID, account, customer, invoiceDate, totalAmount);
				
			}
			
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		return null;	
	}
}
