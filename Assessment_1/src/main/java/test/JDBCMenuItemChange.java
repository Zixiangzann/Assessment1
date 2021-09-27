package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JDBCMenuItemChange {
	
	private static Connection con;
	private static Statement stmt;
	private static ResultSet rs;
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/assessment_1";
		String username = "root";
		String password = "admin";
		//String query = "SELECT * FROM menu_mapping WHERE category = 'Smart Home';";

		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(dbUrl,username,password);
		stmt = con.createStatement();
		//rs = stmt.executeQuery(query);
		
		
		HashMap<String, String> sub = new HashMap<String, String>();
		sub.put("Amazon Brilliant Home","Amazon Smart Home");
		updateSubCategory(sub);
		
		insertSubCategory("Shop By Department","Smart Home","Smart Plant",3);
				
	}
	
	public static void updateSubCategory(HashMap<String, String> subCategory) {

		String updateString = "UPDATE menu_mapping SET sub_category = ? WHERE sub_category = ?;";

		try (PreparedStatement updateSubCategory = con.prepareStatement(updateString);)
		{
			con.setAutoCommit(false);
			for (Map.Entry<String, String> e : subCategory.entrySet()) {
				updateSubCategory.setString(1, e.getKey().toString());
				updateSubCategory.setString(2, e.getValue());
				updateSubCategory.executeUpdate();
				System.out.println(updateSubCategory);
				con.commit();
				System.out.println("commited");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			if (con != null) {
				try {
					System.err.print("roll back");
					con.rollback();
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}	  
			}
		}
	}
	
	public static void insertSubCategory(String title,String category,String sub_category, Integer menu_groupID) {

		String insertString = "INSERT INTO menu_mapping VALUES (?,?,?,?);";

		try (PreparedStatement insertSubCategory = con.prepareStatement(insertString);)
		{
			con.setAutoCommit(false);
			insertSubCategory.setString(1, title);
			insertSubCategory.setString(2, category);
			insertSubCategory.setString(3, sub_category);
			insertSubCategory.setInt(4, 3);
			insertSubCategory.executeUpdate();
			System.out.println(insertSubCategory);
			con.commit();
			System.out.println("commited");
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			if (con != null) {
				try {
					System.err.print("roll back");
					con.rollback();
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}	  

			}	

		}
	}
}
