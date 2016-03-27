import java.sql.*;

import org.omg.CORBA.StringHolder;
public class TestJDBC {

	public static void main(String[] args)   {
		
		/** Insert user input data to database  **/		
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

		
		
		/** the procedure of execute JDBC**/
		Connection connection = null;
		Statement stmt = null;           
		ResultSet rs =null;
		PreparedStatement pstmt = null;  //the subInterface of Statement , advanced and more function than Statement 
		//load the driver
		try {
			//1. Loader the Driver
			Class.forName("com.mysql.jdbc.Driver");
			//2. get the connection to the database
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","");
			//3. execute the sql;
				//3.1 the use of Statement
			/**
			stmt = connection.createStatement();
			String mysql = "insert student(id,name,age) values("+userID+",'"+name+"',"+age+")";
			stmt.execute(mysql);
			System.out.println(mysql);
			**/
			
				//3.2 the use of PreparedStatement 
			pstmt = connection.prepareStatement("insert student(id,name,age) values(?,?,?)");
			pstmt.setInt(1, userID);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.execute();
			
			//4. retrieve the result data;
			rs = pstmt.executeQuery("select* from student");
			while (rs.next()) {
			//5. Show the result data
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("age"));
			}
		}catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//7. close
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
				e.printStackTrace();
			}
		} 
	
	}

}
