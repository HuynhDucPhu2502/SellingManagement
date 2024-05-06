package model.suppliers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import util.DBHelper;
import util.FetchDataStatus;

public class SupplierDAO {
	public static ArrayList<Supplier> getData() {
		ArrayList<Supplier> data = new ArrayList<Supplier>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				){
			String sql = "SELECT SupplierID, SupplierName, Address, PhoneNumber, Email FROM Supplier";
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String supplierID = rs.getString(1);
				String supplierName = rs.getString(2);
				String address = rs.getString(3);
				String phoneNumber = rs.getString(4);
				String email = rs.getString(5);
				
				Supplier supplier = new Supplier(supplierID, supplierName, address, phoneNumber, email);
				data.add(supplier);
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static boolean insertData(Supplier supplier) {
		int n = 0;
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Supplier(SupplierID, SupplierName, Address, PhoneNumber, Email) "
						+ "VALUES(?, ?, ?, ?, ?)");
			)
		{
			insertStatement.setString(1, supplier.getSupplierID());
			insertStatement.setString(2, supplier.getSupplierName());
			insertStatement.setString(3, supplier.getAddress());
			insertStatement.setString(4, supplier.getPhoneNumber());
			insertStatement.setString(5, supplier.getEmail());
			
			n = insertStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		if (n != 0) {
			FetchDataStatus.supplierStatus = false;
			return true;
		}
		
		return false;
	}
	
	public static void deleteData(String supplierID) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Supplier "
						+ "WHERE SupplierID = ?");
			)
		{
			deleteStatement.setString(1, supplierID);
			deleteStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.supplierStatus = false;
	}
	
	public static void updateData(Supplier supplier) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement updateStatement = connection.prepareStatement("UPDATE Supplier "
						+ "SET SupplierName = ?, Address = ?, PhoneNumber = ?, Email = ? "
						+ "WHERE SupplierID = ?");
			)
		{
			updateStatement.setString(1, supplier.getSupplierName());
			updateStatement.setString(2, supplier.getAddress());
			updateStatement.setString(3, supplier.getPhoneNumber());
			updateStatement.setString(4, supplier.getEmail());
			updateStatement.setString(5, supplier.getSupplierID());
			
			updateStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.supplierStatus = false;
	}
	
	public static Supplier getSupplierByName(String supplierName) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT SupplierID, SupplierName, "
						+ "Address, PhoneNumber, Email FROM Supplier WHERE SupplierName = ?");
				){
			statement.setString(1, supplierName);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String supplierID = rs.getString(1);
				String address = rs.getString(3);
				String phoneNumber = rs.getString(4);
				String email = rs.getString(5);
				
				return new Supplier(supplierID, supplierName, address, phoneNumber, email);
			}
			
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	public static ArrayList<Supplier> searchContainsSupplierID(String findStr) {
		ArrayList<Supplier> data = new ArrayList<Supplier>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				){
			String sql = "SELECT SupplierID, SupplierName, "
					+ "Address, PhoneNumber, Email FROM Supplier WHERE SupplierID LIKE N'%" + findStr + "%'";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String supplierID = rs.getString(1);
				String supplierName = rs.getString(2);
				String address = rs.getString(3);
				String phoneNumber = rs.getString(4);
				String email = rs.getString(5);
				
				Supplier supplier = new Supplier(supplierID, supplierName, address, phoneNumber, email);
				data.add(supplier);
			}
			
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static ArrayList<Supplier> searchContainsSupplierName(String findStr) {
		ArrayList<Supplier> data = new ArrayList<Supplier>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				){
			String sql = "SELECT SupplierID, SupplierName, "
					+ "Address, PhoneNumber, Email FROM Supplier WHERE SupplierName LIKE N'%" + findStr + "%'";
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String supplierID = rs.getString(1);
				String supplierName = rs.getString(2);
				String address = rs.getString(3);
				String phoneNumber = rs.getString(4);
				String email = rs.getString(5);
				
				Supplier supplier = new Supplier(supplierID, supplierName, address, phoneNumber, email);
				data.add(supplier);
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static ArrayList<Supplier> searchContainsPhoneNumber(String findStr) {
		ArrayList<Supplier> data = new ArrayList<Supplier>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				){
			String sql = "SELECT SupplierID, SupplierName, "
					+ "Address, PhoneNumber, Email FROM Supplier WHERE PhoneNumber LIKE N'%" + findStr + "%'";
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String supplierID = rs.getString(1);
				String supplierName = rs.getString(2);
				String address = rs.getString(3);
				String phoneNumber = rs.getString(4);
				String email = rs.getString(5);
				
				Supplier supplier = new Supplier(supplierID, supplierName, address, phoneNumber, email);
				data.add(supplier);
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static String getLastSupplierID() {
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				){
			String sql = "SELECT TOP 1 SupplierID FROM Supplier ORDER BY SupplierID DESC";
			ResultSet rs = statement.executeQuery(sql);
			
			if (rs.next()) return rs.getString(1);
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
}
