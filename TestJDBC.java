import java.sql.*;
public class TestJDBC {

	public static void main(String[] args)   {
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs =null;
		//load the driver
		try {
			Class.forName("com.mysql.jdbc.Driver");

			//连接到数据库

			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","");

			//execute the sql;
			stmt = connection.createStatement();
			String mysql = "insert users1(username,pid) values('young','1')";
			stmt.execute(mysql);

			rs = stmt.executeQuery("select* from users1");
			while (rs.next()) {
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("username"));
				System.out.println(rs.getInt("pid"));
			}
		}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	
	}

}
