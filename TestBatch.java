import java.sql.*;
/*
 * 
 * the use of Bat
 * 
 */
public class TestBatch {

	public static void main(String[] args) {
		
		PreparedStatement preparedStatement = null;
		Connection cnn;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","");
			preparedStatement = cnn.prepareStatement("insert into student value(?,?,?)");
			
			preparedStatement.setInt(1, 11);
			preparedStatement.setString(2, "Jack");
			preparedStatement.setInt(3, 5);
			preparedStatement.addBatch();
			
			preparedStatement.setInt(1, 12);
			preparedStatement.setString(2, "Tom");
			preparedStatement.setInt(3, 5);
			preparedStatement.addBatch();
			
			preparedStatement.setInt(1, 13);
			preparedStatement.setString(2, "Smith");
			preparedStatement.setInt(3, 5);
			preparedStatement.addBatch();
			
			preparedStatement.executeBatch();
			preparedStatement.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
