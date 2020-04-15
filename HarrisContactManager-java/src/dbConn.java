import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbConn {

	String connString = "jdbc:mysql://db212it.cxoyanmj9kow.us-east-1.rds.amazonaws.com/HarrisContactDB";//connects to the database
	String username = "admin";
	String password = "Coventry1!";
	Connection con = null;
	
	public dbConn()
	{
		try {
			con = DriverManager.getConnection(connString,username,password);// tries to establish a connenction
			System.out.println("Connected");//log result
		} catch (SQLException e) {//try catch so that is does not die
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Not Connected");//log result
		}
	}
	public ResultSet GetAllBusiness()//app will execute this when needed
	{
		ResultSet rs=null;//defines result set variable
		String sql="{call selectAllBusiness()}";//the callable statement
		try {
			java.sql.CallableStatement cst= con.prepareCall(sql);
			rs=cst.executeQuery();//executes the query
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public void UpdateBusiness(String f,String l,String t,String em,String a1,String a2,String c,String p,String pt,String id)
	{
		ResultSet rs=null;
		String sql="{call updateBusiness(?,?,?,?,?,?,?,?,?,?)}";//calls the statement using the following specified data
		try {
			java.sql.CallableStatement cst= con.prepareCall(sql);//sql statement
			cst.setString(1, f);//variable info
			cst.setString(2, l);
			cst.setString(3, t);
			cst.setString(4, em);
			cst.setString(5, a1);
			cst.setString(6, a2);
			cst.setString(7, c);
			cst.setString(8, p);
			cst.setString(9, pt);
			cst.setString(0, id);
			rs=cst.executeQuery();
			
			cst= con.prepareCall(sql);
			rs=cst.executeQuery();//executes query
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
	}
	}
	
	public void AddBusiness(String f, String l,String t,String em, String a1, String a2, String c, String  p,String pt) 
	{
		String  sql="{call INSERTBusiness(?,?,?,?,?,?,?,?,?)}";
		
		try {
			java.sql.CallableStatement cst=con.prepareCall(sql);
			cst.setString(1,f);
			cst.setString(2, l);
			cst.setString(3, t);
			cst.setString(4, em);
			cst.setString(5, a1);
			cst.setString(6, a2);
			cst.setString(7, c);
			cst.setString(8, p);
			cst.setString(9, pt);
			cst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void DeleteBusiness(String id) 
	{
		String sql="{call DeleteBusiness(?)}";
		java.sql.CallableStatement cst;
		try {
			cst = con.prepareCall(sql);
			cst.setString(1, id);
			cst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void UpdatePersonal(String f,String l,String t,String em,String a1,String a2,String c,String p,String pt,String id)
	{
		ResultSet rs=null;
		String sql="{call updatePersonal(?,?,?,?,?,?,?,?,?,?)}";
		try {
			java.sql.CallableStatement cst= con.prepareCall(sql);
			cst.setString(1, f);
			cst.setString(2, l);
			cst.setString(3, t);
			cst.setString(4, em);
			cst.setString(5, a1);
			cst.setString(6, a2);
			cst.setString(7, c);
			cst.setString(8, p);
			cst.setString(9, pt);
			cst.setString(0, id);
			rs=cst.executeQuery();
			
			cst= con.prepareCall(sql);
			rs=cst.executeQuery();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
	}
	}
	
	public void AddPersonal(String f, String l,String t,String em, String a1, String a2, String c, String  p,String pt) 
	{
		String  sql="{call INSERTPersonal(?,?,?,?,?,?,?,?,?)}";
		
		try {
			java.sql.CallableStatement cst=con.prepareCall(sql);
			cst.setString(1,f);
			cst.setString(2, l);
			cst.setString(3, t);
			cst.setString(4, em);
			cst.setString(5, a1);
			cst.setString(6, a2);
			cst.setString(7, c);
			cst.setString(8, p);
			cst.setString(9, pt);
			cst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void DeletePersonal(String id) 
	{
		String sql="{call DeletePersonal(?)}";
		java.sql.CallableStatement cst;
		try {
			cst = con.prepareCall(sql);
			cst.setString(1, id);
			cst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

