import java.sql.*;

public class TestProcedure {
	
	
	/**
	 * first create a procedure in database
	 * 
	 * 
	 mysql> create procedure p(IN a SMALLINT,IN b SMALLINT,OUT c SMALLINT,INOUT d SMALLINT)
    -> begin
    -> if(a>b)
    -> THEN
    -> set c=a;
    -> else
    -> set c = b;
    -> end if;
    -> set d = d+1;
    -> end;
    -> //

	 * @param args
	 */
	

	public static void main(String[] args) {
		
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","");
			callableStatement = connection.prepareCall("call p(?,?,?,?)");
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.setInt(1, 3);
			callableStatement.setInt(2, 4);
			callableStatement.setInt(4, 5);
			callableStatement.execute();
			System.out.println(callableStatement.getInt(3));
			System.out.println(callableStatement.getInt(4));
			callableStatement.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
