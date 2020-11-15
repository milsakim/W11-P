import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DepartmentsCrudTest {
	
	private static final String CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:49161:xe";
	private static final String USER = "hr";
	private static final String PASSWORD = "1234";
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName(CLASS_NAME);
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			System.out.println("--- getConnection(): connection succeed  ---");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("--- getConnection(): connection fail ---");
			
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public void closeAll(Connection conn, PreparedStatement pstm, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			
			if (pstm != null) {
				pstm.close();
			}
			
			if (conn != null) {
				conn.close();
			}
			
			System.out.println("--- closeAll(): close succeed ---");
		} catch (SQLException e) {
			System.out.println("--- closeAll(): close failed ---");
			
			e.printStackTrace();
		}
	}
	
	private void select() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = "SELECT * FROM (SELECT rownum, d.* FROM departments d ORDER BY rownum DESC) WHERE rownum = 1";
		
		try {
			connection = getConnection();
			
			statement = connection.prepareStatement(sql);
			
			result = statement.executeQuery();
			
			int numberOfSelectedRows = 0;
			
			while(result.next()) {
				System.out.print("[ department_id: " + result.getString("department_id"));
				System.out.print("\tdepartment_name: " + result.getString("department_name"));
				System.out.println("\tlocation_id: " + result.getString("location_id") + " ]");
				
				numberOfSelectedRows += 1;
			}
			
			System.out.println("--- select(): " + numberOfSelectedRows + "row(s) selected ---");
		} catch (SQLException e) {
			System.out.println("--- select(): Select Query Failed ---");
			
			e.printStackTrace();
		} finally {
			this.closeAll(connection, statement, result);
		}
	}
	
	private void insert() {
		String insertSQL = "INSERT INTO departments VALUES (280, 'Test Department', null, null)";
		Connection connection = null;
		PreparedStatement insertStatement = null;
		
		try {
			connection = getConnection();
			
			insertStatement = connection.prepareStatement(insertSQL);
			
			int numberOfInsertedRows = insertStatement.executeUpdate();
			
			System.out.println("--- insert(): " + numberOfInsertedRows + " row(s) inserted ---");
		} catch (SQLException e) {
			System.out.println("--- insert(): SQLException occurred ---");
			
			e.printStackTrace();
		} finally {
			this.closeAll(connection, insertStatement, null);
		}
	}
	
	private void update() {
		String updateSQL = "UPDATE departments SET location_id = 2400 WHERE department_id = 280";
		Connection connection = null;
		PreparedStatement updateStatement = null;
		
		try {
			connection = getConnection();
			
			updateStatement = connection.prepareStatement(updateSQL);
			
			int numberOfUpdatedRows = updateStatement.executeUpdate();
			
			System.out.println("--- update(): " + numberOfUpdatedRows + " rows updated ---");
		} catch (SQLException e) {
			System.out.println("--- update(): fail to execute update SQL ---");
		} finally {
			this.closeAll(connection, updateStatement, null);
		}
	}
	
	private void delete() {
		String deleteSQL = "DELETE FROM departments WHERE department_id = 280";
		Connection connection = null;
		PreparedStatement deleteStatement = null;
		
		try {
			connection = getConnection();
			
			deleteStatement = connection.prepareStatement(deleteSQL);
			
			int numberOfDeletedRows = deleteStatement.executeUpdate();
			
			System.out.println("--- delete(): " + numberOfDeletedRows + " row(s) deleted ---");
		} catch (SQLException e) {
			System.out.println("--- deleted(): fail to execute delete SQL ---");
			
			e.printStackTrace();
		} finally {
			this.closeAll(connection, deleteStatement, null);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DepartmentsCrudTest test= new DepartmentsCrudTest();
		
		test.select();
		
		System.out.println("\n\n");
		
		test.insert();
		test.select();
		
		System.out.println("\n\n");
		
		test.update();
		test.select();
		
		System.out.println("\n\n");
		
		test.delete();
		test.select();
	}

}
