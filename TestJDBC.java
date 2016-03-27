import java.sql.*;

import org.omg.CORBA.StringHolder;
public class TestJDBC {

	public static void main(String[] args)   {
		
		//parse the data from user input
		int userID = 0;
		String name = null;
		int age = 0;
		try {
			userID = Integer.parseInt(args[0]);
			age = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			System.out.println("Parameter Error: UserID should be int, age should be int");
			System.exit(-1);
		}
		name = args[1];

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
			String mysql = "insert student(id,name,age) values("+userID+",'"+name+"',"+age+")";
			System.out.println(mysql);
			stmt.execute(mysql);

			rs = stmt.executeQuery("select* from student");
			while (rs.next()) {
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("age"));
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
