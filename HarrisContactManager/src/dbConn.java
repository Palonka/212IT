import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbConn {

	String connString = "jdbc:mysql://db212it.cxoyanmj9kow.us-east-1.rds.amazonaws.com/HarrisContactDB";
	String username = "admin";
	String password = "Coventry1!";
	Connection con = null;
	
	public dbConn()
	{
		try {
			con = DriverManager.getConnection(connString,username,password);
			System.out.println("Connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Not Connected");
		}
	}
	public ResultSet GetAllPersonal()
	{
		ResultSet rs=null;
		String sql="{call selectAllPersonal()}";
		try {
			java.sql.CallableStatement cst= con.prepareCall(sql);
			rs=cst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
}
