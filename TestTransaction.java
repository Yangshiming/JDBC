import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * 
 * the use of Transaction .
 */
public class TestTransaction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection cnn = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","");
			cnn.setAutoCommit(false); // forbid the aoto commit, change it to commit manually;
			preparedStatement = cnn.prepareStatement("insert into student value(?,?,?)");
			
			preparedStatement.setInt(1, 14);
			preparedStatement.setString(2, "Jack");
			preparedStatement.setInt(3, 5);
			preparedStatement.addBatch();
			
			preparedStatement.setInt(1, 15);
			preparedStatement.setString(2, "Tom");
			preparedStatement.setInt(3, 5);
			preparedStatement.addBatch();
			
			preparedStatement.setInt(1, 16);
			preparedStatement.setString(2, "Smith");
			preparedStatement.setInt(3, 5);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			
			cnn.commit();
			cnn.setAutoCommit(true);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			
			if (cnn != null) {
				try {
					cnn.rollback();
					cnn.setAutoCommit(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		}finally{
			
			
				try {
					if (preparedStatement != null) 
					preparedStatement.close();
					if (cnn != null) {
						cnn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
}
