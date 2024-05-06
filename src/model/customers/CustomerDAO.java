package model.customers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import util.DBHelper;
import util.FetchDataStatus;

public class CustomerDAO {

	public static ArrayList<Customer> getData() {
		ArrayList<Customer> data = new ArrayList<Customer>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				){
			String sql = "SELECT CustomerID, FirstName, LastName, Address, PhoneNumber, CustomerType "
					+ "FROM Customer";
			ResultSet rs = statement.executeQuery(sql);
			
			
			while (rs.next()) {
				String customerID = rs.getString(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String address = rs.getString(4);
				String phoneNumber =rs.getString(5);
				
				CustomerType customerType = rs.getString(6).equalsIgnoreCase(CustomerType.MEMBER.toString())
						? CustomerType.MEMBER : CustomerType.VIP;
				
				Customer customer = new Customer(customerID, firstName, lastName, address, phoneNumber, customerType);
				data.add(customer);				
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static boolean insertData(Customer c) {
		int n = 0;
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO "
						+ "Customer(CustomerID, FirstName, LastName, Address, PhoneNumber, CustomerType) "
						+ "VALUES(?, ?, ?, ?, ?,?)");
			)
		{
			
			insertStatement.setString(1, c.getCustomerID());
			insertStatement.setString(2, c.getFirstName());
			insertStatement.setString(3, c.getLastName());
			insertStatement.setString(4, c.getAddress());
			insertStatement.setString(5, c.getPhoneNumber());
			insertStatement.setString(6, c.getCustomerType().toString());
			
			
			n = insertStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.customerStatus = false;
		return n > 0;
	}
	
	public static boolean deleteData(String cID) {
		int n = 0;
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Customer "
						+ "WHERE CustomerID = ?");
			)
		{
			deleteStatement.setString(1, cID);
			
			n = deleteStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.customerStatus = false;
		return n > 0;
	}
	
	public static boolean updateData(Customer c) {
		int n = 0;
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement updateStatement = connection.prepareStatement("UPDATE Customer "
						+ "SET FirstName = ?, LastName = ?, Address = ?, PhoneNumber = ?, CustomerType= ? "
						+ "where CustomerID = ?");
				)
		{
			
			updateStatement.setString(1, c.getFirstName());
			updateStatement.setString(2, c.getLastName());
			updateStatement.setString(3, c.getAddress());
			updateStatement.setString(4, c.getPhoneNumber());
			updateStatement.setString(5, c.getCustomerType().toString());
			updateStatement.setString(6, c.getCustomerID());
			
			n = updateStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.customerStatus = false;
		return n > 0;
	}
	
	public static Customer searchCustomerByPhoneNumber(String phoneNumber) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement searchStatement = connection.prepareStatement(
						"SELECT CustomerID, FirstName, LastName, Address, PhoneNumber, CustomerType "
						+ "FROM Customer WHERE PhoneNumber = ?");
				)
		{
			searchStatement.setString(1, phoneNumber);
			ResultSet rs = searchStatement.executeQuery();
			
			if (rs.next()) {
				String customerID = rs.getString(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String address = rs.getString(4);
				
				CustomerType customerType = rs.getString(6).equalsIgnoreCase(CustomerType.MEMBER.toString())
						? CustomerType.MEMBER : CustomerType.VIP;
				
				return new Customer(customerID, firstName, lastName, address, phoneNumber, customerType);
			}
					
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	public static Customer getCustomerByCustomerID(String customerID) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT CustomerID, FirstName, LastName, Address, PhoneNumber, CustomerType "
						+ "FROM Customer WHERE CustomerID = ?");
				){
			statement.setString(1, customerID);
			
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String address = rs.getString(4);
				String phoneNumber =rs.getString(5);
				
				CustomerType customerType = rs.getString(6).equalsIgnoreCase(CustomerType.MEMBER.toString())
						? CustomerType.MEMBER : CustomerType.VIP;
				
				return new Customer(customerID, firstName, lastName, address, phoneNumber, customerType);			
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	
	
}
