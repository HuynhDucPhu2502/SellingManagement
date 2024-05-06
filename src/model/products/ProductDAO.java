package model.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.categories.Category;
import model.categories.CategoryDAO;
import model.shelf.Shelf;
import model.shelf.ShelfDAO;
import model.suppliers.Supplier;
import model.suppliers.SupplierDAO;
import util.DBHelper;
import util.FetchDataStatus;

public class ProductDAO {
	public static ArrayList<Product> getData() {
		ArrayList<Product> data = new ArrayList<Product>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				){
			String sql = "SELECT p.ProductID, p.ProductName, p.PurchasePrice, p.SellingPrice, p.Stock,  p.Unit, p.ReceiveDate, "
					+ "p.CategoryName, p.SupplierName, p.ShelfID "
					+ "FROM Product p";
			ResultSet rs = statement.executeQuery(sql);
			
			ArrayList<Category> categoryList = FetchDataStatus.getCategoryData();
			ArrayList<Supplier> supplierList = FetchDataStatus.getSupplierData();
			ArrayList<Shelf> shelfList = FetchDataStatus.getShelfData();
			
			while (rs.next()) {
				String productID = rs.getString(1);
				String productName = rs.getString(2);
				double purchasePrice = rs.getDouble(3);
				double sellingPrice = rs.getDouble(4);
				int stock = rs.getInt(5);
				String unit = rs.getString(6);
				Date recevieDate = rs.getDate(7);

				String categoryName = rs.getString(8);
				Category category = categoryList.stream()
				        .filter(x -> x.getCategoryName().equalsIgnoreCase(categoryName))
				        .findFirst()
				        .orElse(null);

				String supplierName = rs.getString(9);
				Supplier supplier = supplierList.stream()
				        .filter(x -> x.getSupplierName().equalsIgnoreCase(supplierName))
				        .findFirst()
				        .orElse(null);

				String shelfID = rs.getString(10);
				Shelf shelf = shelfList.stream()
				        .filter(x -> x.getShelfID().equalsIgnoreCase(shelfID))
				        .findFirst()
				        .orElse(null);

				Product product = new Product(productID, productName, purchasePrice, sellingPrice, category, stock, unit, recevieDate, supplier, shelf);
				data.add(product);

				
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static void insertData(Product product) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Product(ProductName, ProductID, PurchasePrice, "
						+ "SellingPrice, CategoryName, Stock, Unit, ReceiveDate, SupplierName,ShelfID) "
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
				PreparedStatement updateStatement = connection.prepareStatement("UPDATE ProductCategory SET ItemCount = "
						+ "(SELECT COUNT(*) FROM Product WHERE CategoryName = ?) "
						+ "WHERE CategoryName = ?")
				){
			insertStatement.setString(1, product.getName());
			insertStatement.setString(2, product.getId());
			insertStatement.setDouble(3, product.getPurchasePrice());
			insertStatement.setDouble(4, product.getSellingPrice());
			insertStatement.setString(5, product.getCategory().getCategoryName());
			insertStatement.setInt(6, product.getStock());
			insertStatement.setString(7, product.getUnit());
			insertStatement.setDate(8, new java.sql.Date(product.getRecevieDate().getTime()));
			insertStatement.setString(9, product.getSupplier().getSupplierName());
			insertStatement.setString(10, product.getShelf().getShelfID());
			
			updateStatement.setString(1, product.getCategory().getCategoryName());
			updateStatement.setString(2, product.getCategory().getCategoryName());
			
			insertStatement.executeUpdate();
			updateStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.productStatus = false;
	}
	
	public static void deleteData(String productID, String categoryName) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Product "
						+ "WHERE ProductID = ?");
				PreparedStatement updateStatement = connection.prepareStatement("UPDATE ProductCategory SET ItemCount = "
						+ "(SELECT COUNT(*) FROM Product WHERE CategoryName = ?) "
						+ "WHERE CategoryName = ?")
				)
		{
			deleteStatement.setString(1, productID);
			
			updateStatement.setString(1, categoryName);
			updateStatement.setString(2, categoryName);
			
			
			deleteStatement.executeUpdate();
			updateStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.productStatus = false;
	}
	
	public static void updateData(Product product) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement updateStatment = connection.prepareStatement("UPDATE Product "
					    + "SET ProductName = ?, CategoryName = ?, PurchasePrice = ?, "
					    + "SellingPrice = ?, Stock = ?, Unit = ?, ReceiveDate = ?, SupplierName = ?,ShelfID=?" 
						+ " WHERE ProductID = ?");
				){
			updateStatment.setString(1, product.getName());
			updateStatment.setString(2, product.getCategory().getCategoryName());
			updateStatment.setDouble(3, product.getPurchasePrice());
			updateStatment.setDouble(4, product.getSellingPrice());
			updateStatment.setInt(5, product.getStock());
			updateStatment.setString(6, product.getUnit());
			updateStatment.setDate(7, new java.sql.Date(product.getRecevieDate().getTime()));
			updateStatment.setString(8, product.getSupplier().getSupplierName());
			updateStatment.setString(9, product.getShelf().getShelfID());
			updateStatment.setString(10, product.getProductID());

			updateStatment.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.productStatus = false;
	}
	
	public static ArrayList<Product> searchContainsName(String searchStr) {
		ArrayList<Product> data = new ArrayList<Product>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				)
		{
			String sql = "SELECT p.ProductID, p.ProductName, p.PurchasePrice, p.SellingPrice, p.Stock,  p.Unit, p.ReceiveDate, "
					+ "p.CategoryName, p.SupplierName,p.ShelfID "
					+ "FROM Product p WHERE ProductName LIKE N'%" + searchStr + "%'";
			ResultSet rs = statement.executeQuery(sql);
			
			ArrayList<Category> categoryList = FetchDataStatus.getCategoryData();
			ArrayList<Supplier> supplierList = FetchDataStatus.getSupplierData();
			ArrayList<Shelf> shelfList=FetchDataStatus.getShelfData();
			
			while (rs.next()) {
				String productID = rs.getString(1);
				String productName = rs.getString(2);
				double purchasePrice = rs.getDouble(3);
				double sellingPrice = rs.getDouble(4);
				int stock = rs.getInt(5);
				String unit = rs.getString(6);
				Date recevieDate = rs.getDate(7);
				
				String categoryName = rs.getString(8);
				Category category = categoryList.stream()
						.filter(x -> x.getCategoryName().equalsIgnoreCase(categoryName))
						.findFirst()
						.orElse(null);
				
				String supplierName = rs.getString(9);
				Supplier supplier = supplierList.stream()
						.filter(x -> x.getSupplierName().equalsIgnoreCase(supplierName))
						.findFirst()
						.orElse(null);
				String shelfID = rs.getString(10);
				Shelf shelf = shelfList.stream()
				        .filter(x -> x.getShelfID().equalsIgnoreCase(shelfID))
				        .findFirst()
				        .orElse(null);
				
				Product product = new Product(productID, productName, purchasePrice, sellingPrice, category, stock, unit, recevieDate, supplier,shelf);
				data.add(product);
				
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static ArrayList<Product> searchCategoryName(String searchStr) {
		ArrayList<Product> data = new ArrayList<Product>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				)
		{
			String sql = "SELECT p.ProductID, p.ProductName, p.PurchasePrice, p.SellingPrice, p.Stock,  p.Unit, p.ReceiveDate, "
					+ "p.CategoryName, p.SupplierName,p.ShelfID "
					+ "FROM Product p WHERE CategoryName LIKE N'%" + searchStr + "%'";
			ResultSet rs = statement.executeQuery(sql);
			
			ArrayList<Category> categoryList = FetchDataStatus.getCategoryData();
			ArrayList<Supplier> supplierList = FetchDataStatus.getSupplierData();
			ArrayList<Shelf> shelfList=FetchDataStatus.getShelfData();
			
			while (rs.next()) {
				String productID = rs.getString(1);
				String productName = rs.getString(2);
				double purchasePrice = rs.getDouble(3);
				double sellingPrice = rs.getDouble(4);
				int stock = rs.getInt(5);
				String unit = rs.getString(6);
				Date recevieDate = rs.getDate(7);
				
				String categoryName = rs.getString(8);
				Category category = categoryList.stream()
						.filter(x -> x.getCategoryName().equalsIgnoreCase(categoryName))
						.findFirst()
						.orElse(null);
				
				String supplierName = rs.getString(9);
				Supplier supplier = supplierList.stream()
						.filter(x -> x.getSupplierName().equalsIgnoreCase(supplierName))
						.findFirst()
						.orElse(null);
				String shelfID = rs.getString(10);
				Shelf shelf = shelfList.stream()
				        .filter(x -> x.getShelfID().equalsIgnoreCase(shelfID))
				        .findFirst()
				        .orElse(null);
				
				Product product = new Product(productID, productName, purchasePrice, sellingPrice, category, stock, unit, recevieDate, supplier,shelf);
				data.add(product);
				
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static ArrayList<Product> searchContainsProductID(String searchStr) {
		ArrayList<Product> data = new ArrayList<Product>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				)
		{
			String sql = "SELECT p.ProductID, p.ProductName, p.PurchasePrice, p.SellingPrice, p.Stock,  p.Unit, p.ReceiveDate, "
					+ "p.CategoryName, p.SupplierName,p.ShelfID "
					+ "FROM Product p WHERE ProductID LIKE N'%" + searchStr + "%'";
			ResultSet rs = statement.executeQuery(sql);
			
			ArrayList<Category> categoryList = FetchDataStatus.getCategoryData();
			ArrayList<Supplier> supplierList = FetchDataStatus.getSupplierData();
			ArrayList<Shelf> shelfList=FetchDataStatus.getShelfData();
			while (rs.next()) {
				String productID = rs.getString(1);
				String productName = rs.getString(2);
				double purchasePrice = rs.getDouble(3);
				double sellingPrice = rs.getDouble(4);
				int stock = rs.getInt(5);
				String unit = rs.getString(6);
				Date recevieDate = rs.getDate(7);
				
				String categoryName = rs.getString(8);
				Category category = categoryList.stream()
						.filter(x -> x.getCategoryName().equalsIgnoreCase(categoryName))
						.findFirst()
						.orElse(null);
				
				String supplierName = rs.getString(9);
				Supplier supplier = supplierList.stream()
						.filter(x -> x.getSupplierName().equalsIgnoreCase(supplierName))
						.findFirst()
						.orElse(null);
				String shelfID = rs.getString(10);
				Shelf shelf = shelfList.stream()
				        .filter(x -> x.getShelfID().equalsIgnoreCase(shelfID))
				        .findFirst()
				        .orElse(null);
				
				Product product = new Product(productID, productName, purchasePrice, sellingPrice, category, stock, unit, recevieDate, supplier,shelf);
				data.add(product);
				
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static ArrayList<Product> searchContainsSupplierName(String searchStr) {
		ArrayList<Product> data = new ArrayList<Product>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				)
		{
			String sql = "SELECT p.ProductID, p.ProductName, p.PurchasePrice, p.SellingPrice, p.Stock,  p.Unit, p.ReceiveDate, "
					+ "p.CategoryName, p.SupplierName "
					+ "FROM Product p WHERE p.SupplierName LIKE N'%" + searchStr + "%'";
			ResultSet rs = statement.executeQuery(sql);
			
			ArrayList<Category> categoryList = FetchDataStatus.getCategoryData();
			ArrayList<Supplier> supplierList = FetchDataStatus.getSupplierData();
			ArrayList<Shelf> shelfList=FetchDataStatus.getShelfData();
			while (rs.next()) {
				String productID = rs.getString(1);
				String productName = rs.getString(2);
				double purchasePrice = rs.getDouble(3);
				double sellingPrice = rs.getDouble(4);
				int stock = rs.getInt(5);
				String unit = rs.getString(6);
				Date recevieDate = rs.getDate(7);
				
				String categoryName = rs.getString(8);
				Category category = categoryList.stream()
						.filter(x -> x.getCategoryName().equalsIgnoreCase(categoryName))
						.findFirst()
						.orElse(null);
				
				String supplierName = rs.getString(9);
				Supplier supplier = supplierList.stream()
						.filter(x -> x.getSupplierName().equalsIgnoreCase(supplierName))
						.findFirst()
						.orElse(null);
				String shelfID = rs.getString(10);
				Shelf shelf = shelfList.stream()
				        .filter(x -> x.getShelfID().equalsIgnoreCase(shelfID))
				        .findFirst()
				        .orElse(null);
				
				Product product = new Product(productID, productName, purchasePrice, sellingPrice, category, stock, unit, recevieDate, supplier,shelf);
				data.add(product);
				
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static void updateStock(String productID, int newStock) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement updateStatment = connection.prepareStatement("UPDATE Product "
					    + "SET Stock = ? WHERE ProductID = ?");
				){
			updateStatment.setInt(1, newStock);
			updateStatment.setString(2, productID);

			updateStatment.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.productStatus = false;
	}
	
	public static Product getProductByID(String productID) {
	    try (
	        Connection connection = DBHelper.getConnection();
	        PreparedStatement statement = connection.prepareStatement(
	        	"SELECT p.ProductID, p.ProductName, p.PurchasePrice, p.SellingPrice, p.Stock,  p.Unit, p.ReceiveDate, "
	    		+ "p.CategoryName, p.SupplierName,p.ShelfID "
	    		+ "FROM Product p WHERE p.ProductID = ?")
	    ) {
	    	
	        statement.setString(1, productID); 
	        ResultSet rs = statement.executeQuery();
			
	        if (rs.next()) {
				String productName = rs.getString(2);
				double purchasePrice = rs.getDouble(3);
				double sellingPrice = rs.getDouble(4);
				int stock = rs.getInt(5);
				String unit = rs.getString(6);
				Date recevieDate = rs.getDate(7);
				
				String categoryName = rs.getString(8);
				Category category = CategoryDAO.getCategoryByName(categoryName);
				
				String supplierName = rs.getString(9);
				Supplier supplier = SupplierDAO.getSupplierByName(supplierName);
				
				String shelfID = rs.getString(9);
				Shelf shelf = ShelfDAO.getShelfByID(shelfID);
				
				return new Product(productID, productName, purchasePrice, sellingPrice, category, stock, unit, recevieDate, supplier,shelf);			
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
}
