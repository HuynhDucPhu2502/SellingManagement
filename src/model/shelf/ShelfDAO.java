package model.shelf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import util.DBHelper;
import util.FetchDataStatus;

public class ShelfDAO {

	public static ArrayList<Shelf> getData() {
		ArrayList<Shelf> data = new ArrayList<Shelf>();
		try (
				Connection connection = DBHelper.getConnection();
				Statement statement = connection.createStatement();
				){
			String sql = "SELECT * "
					+ "FROM Ke";
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String shelfID = rs.getString(1);
				String shelfName = rs.getString(2);
				ShelfLocation shelfLocation = rs.getString(3).equalsIgnoreCase(ShelfLocation.Left.getVitriType())
                        ? ShelfLocation.Left : ShelfLocation.Right;
				SizeType sizeType = SizeType.fromString(rs.getString(4));
				String status =rs.getString(5);
				String note =rs.getString(6);

				Shelf shelf = new Shelf(shelfID, shelfName, shelfLocation, sizeType, status, note);

				data.add(shelf);				
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		return data;
	}
	
	public static boolean insertData(Shelf c) {
		int n = 0;
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO "
						+ "Ke(IDKe, Tenke, Vitri, Kichthuoc, Trangthai,Ghichu) "
						+ "VALUES(?, ?, ?, ?, ?,?)");
			)
		{
			
			insertStatement.setString(1, c.getShelfID());
			insertStatement.setString(2, c.getShelfName());
			insertStatement.setString(3, c.getShelfLocation().getVitriType());
			insertStatement.setString(4, c.getSizeTye().getKichthuocType());
			insertStatement.setString(5, c.getStatus());
			insertStatement.setString(6, c.getNote());
			
			
			n = insertStatement.executeUpdate();
		} catch (Exception exception) {
			
		}
		
		FetchDataStatus.shelfStatus = false;
		return n > 0;
	}
	
	public static boolean deleteData(String cID) {
		int n = 0;
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Ke "
						+ "WHERE IdKe = ?");
			)
		{
			deleteStatement.setString(1, cID);
			
			n = deleteStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.shelfStatus = false;
		return n > 0;
	}
	
	public static boolean updateData(Shelf c) {
		int n = 0;
		try (
				Connection connection = DBHelper.getConnection();
				PreparedStatement updateStatement = connection.prepareStatement("UPDATE Ke "
						+ "SET TenKe = ?,Vitri = ?, Kichthuoc = ?, Trangthai = ?,Ghichu=? "
						+ "where IdKe = ?");
				)
		{
			
			updateStatement.setString(1, c.getShelfName());
			updateStatement.setString(2, c.getShelfLocation().getVitriType());
			updateStatement.setString(3, c.getSizeTye().getKichthuocType());
			updateStatement.setString(4, c.getStatus());
			updateStatement.setString(6, c.getShelfID());
			updateStatement.setString(5, c.getNote());
			
			n = updateStatement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		FetchDataStatus.shelfStatus = false;
		return n > 0;
	}
	
	public static Shelf getShelfByID(String shelfID) {
	    try (
	        Connection connection = DBHelper.getConnection();
	        PreparedStatement statement = connection.prepareStatement("SELECT IDKe, Tenke, "
	                + "Vitri, Kichthuoc, Trangthai, Ghichu FROM Ke WHERE IDKe = ?");
	        ) {
	        statement.setString(1, shelfID); // Sửa thành shelfID

	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
				String shelfName = rs.getString(2);
				ShelfLocation shelfLocation = rs.getString(3).equalsIgnoreCase(ShelfLocation.Left.getVitriType())
                        ? ShelfLocation.Left : ShelfLocation.Right;
				SizeType sizeType = SizeType.fromString(rs.getString(4));
				String status =rs.getString(5);
				String note =rs.getString(6);

				return new Shelf(shelfID, shelfName, shelfLocation, sizeType, status, note);
	        }
	    } catch (Exception exception) {
	        exception.printStackTrace();
	        System.exit(1);
	    }
	    
	    return null;
	}

}
