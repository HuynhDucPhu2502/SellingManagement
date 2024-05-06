package model.statistical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import util.DBHelper;

public class StatisticalDAO {
	public static ArrayList<ProductDetail> getTotalSoldProductsQuantityAll() {
		 ArrayList<ProductDetail> data = new ArrayList<ProductDetail>();
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement Statement = connection.prepareStatement(
						"SELECT ProductID, ProductName, TotalSoldQuantity FROM DBO.TotalSoldProductsQuantityAll()");
				){
			ResultSet rs= Statement.executeQuery();
			
			while (rs.next()) {
				String productID = rs.getString(1);
				String productName = rs.getString(2);
				int totalSoldQuantity = rs.getInt(3);
				
				data.add(new ProductDetail(productID, productName, totalSoldQuantity));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data.stream()
				.sorted(Comparator.comparingInt(ProductDetail::getTotalSoldQuantity).reversed())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static ArrayList<ProductDetail> getTotalSoldProductsQuantityByYearMonth(int year, int month) {
		 ArrayList<ProductDetail> data = new ArrayList<ProductDetail>();
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement Statement = connection.prepareStatement(
						"SELECT ProductID, ProductName, TotalSoldQuantity FROM DBO.TotalSoldProductsQuantityByYearMonth(?, ?)");
				){
			Statement.setInt(1, year);
			Statement.setInt(2, month);
			ResultSet rs= Statement.executeQuery();
			
			while (rs.next()) {
				String productID = rs.getString(1);
				String productName = rs.getString(2);
				int totalSoldQuantity = rs.getInt(3);
				
				data.add(new ProductDetail(productID, productName, totalSoldQuantity));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data.stream()
				.sorted(Comparator.comparingInt(ProductDetail::getTotalSoldQuantity).reversed())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static ArrayList<ProductDetail> getTotalSoldProductsQuantityByYear(int year) {
		 ArrayList<ProductDetail> data = new ArrayList<ProductDetail>();
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement Statement = connection.prepareStatement(
						"SELECT ProductID, ProductName, TotalSoldQuantity FROM DBO.TotalSoldProductsQuantityByYear(?)");
				){
			Statement.setInt(1, year);
			ResultSet rs= Statement.executeQuery();
			
			while (rs.next()) {
				String productID = rs.getString(1);
				String productName = rs.getString(2);
				int totalSoldQuantity = rs.getInt(3);
				
				data.add(new ProductDetail(productID, productName, totalSoldQuantity));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data.stream()
				.sorted(Comparator.comparingInt(ProductDetail::getTotalSoldQuantity).reversed())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static ArrayList<CustomerDetail> getCustomerTotalPurchasesAll() {
		 ArrayList<CustomerDetail> data = new ArrayList<CustomerDetail>();
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement Statement = connection.prepareStatement(
						"SELECT customerID, fullName, totalAmount FROM DBO.CustomerTotalPurchasesAll()");
				){
			ResultSet rs= Statement.executeQuery();
			
			while (rs.next()) {
				String customerID = rs.getString(1);
				String fullName = rs.getString(2);
				double totalAmount = rs.getDouble(3);
				
				data.add(new CustomerDetail(customerID, fullName, totalAmount));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data.stream()
				.sorted(Comparator.comparingDouble(CustomerDetail::getTotalAmount).reversed())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static ArrayList<CustomerDetail> getCustomerTotalPurchasesByYearMonth(int year, int month) {
		 ArrayList<CustomerDetail> data = new ArrayList<CustomerDetail>();
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement Statement = connection.prepareStatement(
						"SELECT customerID, fullName, totalAmount FROM DBO.CustomerTotalPurchasesByYearMonth(?, ?)");
				){
			Statement.setInt(1, year);
			Statement.setInt(2, month);
			ResultSet rs= Statement.executeQuery();
			
			while (rs.next()) {
				String customerID = rs.getString(1);
				String fullName = rs.getString(2);
				double totalAmount = rs.getDouble(3);
				
				data.add(new CustomerDetail(customerID, fullName, totalAmount));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data.stream()
				.sorted(Comparator.comparingDouble(CustomerDetail::getTotalAmount).reversed())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static ArrayList<CustomerDetail> getCustomerTotalPurchasesByYear(int year) {
		 ArrayList<CustomerDetail> data = new ArrayList<CustomerDetail>();
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement Statement = connection.prepareStatement(
						"SELECT customerID, fullName, totalAmount FROM DBO.CustomerTotalPurchasesByYear(?)");
				){
			Statement.setInt(1, year);
			ResultSet rs= Statement.executeQuery();
			
			while (rs.next()) {
				String customerID = rs.getString(1);
				String fullName = rs.getString(2);
				double totalAmount = rs.getDouble(3);
				
				data.add(new CustomerDetail(customerID, fullName, totalAmount));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data.stream()
				.sorted(Comparator.comparingDouble(CustomerDetail::getTotalAmount).reversed())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static ArrayList<EmployeeDetail> getEmployeeSalesTotalAll() {
		 ArrayList<EmployeeDetail> data = new ArrayList<EmployeeDetail>();
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement Statement = connection.prepareStatement(
						"SELECT employeeID, fullName, totalAmount FROM DBO.EmployeeSalesTotalAll()");
				){
			ResultSet rs= Statement.executeQuery();
			
			while (rs.next()) {
				String customerID = rs.getString(1);
				String fullName = rs.getString(2);
				double totalAmount = rs.getDouble(3);
				
				data.add(new EmployeeDetail(customerID, fullName, totalAmount));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data.stream()
				.sorted(Comparator.comparingDouble(EmployeeDetail::getTotalAmount).reversed())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static ArrayList<EmployeeDetail> getEmployeeSalesTotalByYearMonth(int year, int month) {
		 ArrayList<EmployeeDetail> data = new ArrayList<EmployeeDetail>();
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement Statement = connection.prepareStatement(
						"SELECT employeeID, fullName, totalAmount FROM DBO.EmployeeSalesTotalByYearMonth(?, ?)");
				){
			Statement.setInt(1, year);
			Statement.setInt(2, month);
			ResultSet rs= Statement.executeQuery();
			
			while (rs.next()) {
				String customerID = rs.getString(1);
				String fullName = rs.getString(2);
				double totalAmount = rs.getDouble(3);
				
				data.add(new EmployeeDetail(customerID, fullName, totalAmount));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data.stream()
				.sorted(Comparator.comparingDouble(EmployeeDetail::getTotalAmount).reversed())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static ArrayList<EmployeeDetail> getEmployeeSalesTotalByYear(int year) {
		 ArrayList<EmployeeDetail> data = new ArrayList<EmployeeDetail>();
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement Statement = connection.prepareStatement(
						"SELECT employeeID, fullName, totalAmount FROM DBO.EmployeeSalesTotalByYear(?)");
				){
			Statement.setInt(1, year);
			ResultSet rs= Statement.executeQuery();
			
			while (rs.next()) {
				String customerID = rs.getString(1);
				String fullName = rs.getString(2);
				double totalAmount = rs.getDouble(3);
				
				data.add(new EmployeeDetail(customerID, fullName, totalAmount));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data.stream()
				.sorted(Comparator.comparingDouble(EmployeeDetail::getTotalAmount).reversed())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static ArrayList<Integer> getYears() {
		 ArrayList<Integer> data = new ArrayList<Integer>();
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement Statement = connection.prepareStatement(
						"SELECT YEAR(InvoiceDate) "
						+ "FROM Invoice "
						+ "GROUP BY YEAR(InvoiceDate)");
				){
			ResultSet rs= Statement.executeQuery();
			
			while (rs.next()) {
				data.add(rs.getInt(1));
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data.stream()
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static int getInvoiceCountByAll() {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement Statement = connection.prepareStatement(
						"SELECT COUNT(InvoiceID) FROM Invoice");
				){
			ResultSet rs= Statement.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return 0;
	}
	public static int getInvoiceCountByYear(int year) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement Statement = connection.prepareStatement(
						"SELECT COUNT(InvoiceID) FROM Invoice WHERE YEAR(InvoiceDate) = ?");
				){
			Statement.setInt(1, year);
			ResultSet rs= Statement.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return 0;
	}
	public static int getInvoiceCountByYearMonth(int year, int month) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement Statement = connection.prepareStatement(
						"SELECT COUNT(InvoiceID) FROM Invoice WHERE YEAR(InvoiceDate) = ? AND MONTH(InvoiceDate) = ?");
				){
			Statement.setInt(1, year);
			Statement.setInt(2, month);
			ResultSet rs= Statement.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return 0;
	}
}
