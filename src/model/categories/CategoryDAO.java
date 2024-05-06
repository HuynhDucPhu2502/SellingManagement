package model.categories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import util.DBHelper;
import util.FetchDataStatus;

public class CategoryDAO {
	public static ArrayList<Category> getData() {
		ArrayList<Category> data = new ArrayList<Category>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				){
			String sql = "SELECT * FROM ProductCategory";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString(2);
				String id = rs.getString(1);
				int count = rs.getInt(3);
				
				Category category = new Category(id, name, count);
				data.add(category);
			}
			
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static void insertData(Category category) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ProductCategory(CategoryID, CategoryName, ItemCount) "
						+ "VALUES(?, ?, ?)");
				){
			preparedStatement.setString(1, category.getCategoryID());
			preparedStatement.setString(2, category.getCategoryName());
			preparedStatement.setInt(3, 0);
			
			preparedStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.categoryStatus = false;
	}
	
	public static void deleteData(String categoryID) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ProductCategory "
						+ "WHERE CategoryID = ?");
				){
			preparedStatement.setString(1, categoryID);
			preparedStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.categoryStatus = false;
	}
	
	public static void updateData(String name, String categoryID) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ProductCategory "
						+ "SET CategoryName = ? " + "WHERE CategoryID = ?");
				){
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, categoryID);
			preparedStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.categoryStatus = false;
	}
		
	public static Category getCategoryByName(String categoryName) {
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM ProductCategory WHERE CategoryName = ?");
				){
			statement.setString(1, categoryName);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String name = rs.getString(2);
				String id = rs.getString(1);
				int count = rs.getInt(3);
				
				return new Category(id, name, count);
			}
			
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	
}
