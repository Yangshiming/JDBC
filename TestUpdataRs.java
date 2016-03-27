import java.sql.*;
public class TestUpdataRs {
    public static void main(String args[]){
	
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection  conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","");
	    Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	    
	    ResultSet rs=stmt.executeQuery("select * from student");
	    
	    rs.next();
	    //����һ������
	    rs.updateString("name","AAAA");
	    rs.updateRow();

	    //��������
	    rs.moveToInsertRow();
	    rs.updateInt(1, 9999);
	    rs.updateString("name","AAAA");
	    rs.updateInt("age", 7839);
	    rs.insertRow();
	    //������ƶ����½�����
	    rs.moveToCurrentRow();

	    //ɾ����
	    rs.absolute(5);
	    rs.deleteRow();

	    //ȡ������
	    //rs.cancelRowUpdates();

	  }catch(SQLException e){
	    e.printStackTrace();
	  } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
