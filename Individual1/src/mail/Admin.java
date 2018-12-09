package mail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;


public class Admin extends User {
	
	public Admin(String username, String password, String level) {
		super(username, password, level);
		// TODO Auto-generated constructor stub
	}

	public Admin(){}
	
public void CreateUser(String username, String password, String level)  throws Exception {
		
		Statement stmt = null;
		Connection conn = null;
		
		
		try {
			ConnHelper h =new ConnHelper();
			conn = h.GetDBConnection();

			stmt = conn.createStatement();

			
			
			String sql = "INSERT INTO USERS VALUES ('"+username+"','"+password+"','')";
			stmt.executeUpdate(sql);
			System.out.println("User got created successfully.");			

		}catch(SQLException se){
			
			// se.printStackTrace();
			System.out.println("Invalid inputs. Check for possible duplicate username.");
			
		}catch(Exception e){
		
			// e.printStackTrace();
			System.out.println("Invalid inputs.");
			
		}finally{
			
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
			//	se.printStackTrace();
			}//end finally try
		}//end try

	}
	

public List<String> ViewUser(){
	Connection conn = null;
	List<String> lista = new ArrayList();
	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();
		
		PreparedStatement u = conn.prepareStatement("SELECT username, password, level FROM individual.users  ;");


		ResultSet rs = u.executeQuery();
		Formatter fmt1 = new Formatter();
		fmt1.format("%-20s %-20s %-20s ", "USERNAME" , "PASSWORD", "LEVEL");
		System.out.println(fmt1);
		System.out.println("------------------------------------------------");
		System.out.println();
	

		while (rs.next()) {
			
			String user = rs.getString("username");
			String pwd=rs.getString("password");
			String level = rs.getString("level");
			Formatter fmt = new Formatter();
			fmt.format("%-20s %-20s %-20s ", user , pwd, level);
			System.out.println(fmt);
			
			lista.add(user);
			System.out.println();
		}


		conn.close();
		return lista;
	}catch(SQLException se){
		//Handle errors for JDBC
	//	se.printStackTrace();
		return lista;
	}catch(Exception e){
		//Handle errors for Class.forName
	//	e.printStackTrace();
		return lista;
	}
}

public List<String> ReturnUsers(){
	Connection conn = null;
	List<String> lista = new ArrayList();
	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();
		
		PreparedStatement u = conn.prepareStatement("SELECT username, password, level FROM individual.users ;");


		ResultSet rs = u.executeQuery();
		
	

		while (rs.next()) {
			
			String user = rs.getString("username");
			String pwd=rs.getString("password");
			String level = rs.getString("level");
			
			
			lista.add(user);
			
		}


		conn.close();
		return lista;
	}catch(SQLException se){
		//Handle errors for JDBC
		//se.printStackTrace();
		return lista;
	}catch(Exception e){
		//Handle errors for Class.forName
	//	e.printStackTrace();
		return lista;
	}
}
public void DeleteUser(String username)  throws Exception {
	
	Statement stmt = null;
	Connection conn = null;
	
	
	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();

		stmt = conn.createStatement();

		
		
		String sql = "delete from individual.users where username='"+username+"'";
		stmt.executeUpdate(sql);
		System.out.println("User got deleted successfully.");			

	}catch(SQLException se){
		
	//	se.printStackTrace();
		System.out.println("You cannot delete this user because there are messages with him involved.");
		
	}catch(Exception e){
	
		//e.printStackTrace();
		System.out.println("Invalid inputs.");
		
	}finally{
		
		try{
			if(stmt!=null)
				stmt.close();
		}catch(SQLException se2){
		}// nothing we can do
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
			se.printStackTrace();
		}//end finally try
	}//end try

}


public void UpdateUsername(String usernameOLD, String usernameNEW)  throws Exception {
	
	Statement stmt = null;
	Connection conn = null;
	

	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();

		stmt = conn.createStatement();

		
		
		String sql = "UPDATE individual.USERS SET username='"+usernameNEW+"' where username='"+usernameOLD+"'";
		stmt.executeUpdate(sql);
		System.out.println("Username got updated successfully.");			

	}catch(SQLException se){
		
		//se.printStackTrace();
		System.out.println("Invalid inputs.");
		
	}catch(Exception e){
	
		//e.printStackTrace();
		System.out.println("Invalid inputs.");
		
	}finally{
		
		try{
			if(stmt!=null)
				stmt.close();
		}catch(SQLException se2){
		}// nothing we can do
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
			se.printStackTrace();
		}//end finally try
	}//end try

}

public void UpdateLevel(String usernameOLD, String levelNEW)  throws Exception {
	
	Statement stmt = null;
	Connection conn = null;
	

	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();

		stmt = conn.createStatement();

		
		
		String sql = "UPDATE individual.USERS SET level='"+levelNEW+"' where username='"+usernameOLD+"'";
		stmt.executeUpdate(sql);
		System.out.println("Level got updated successfully.");			

	}catch(SQLException se){
		
		//se.printStackTrace();
		System.out.println("Invalid inputs.");
		
	}catch(Exception e){
	
		//e.printStackTrace();
		System.out.println("Invalid inputs.");
		
	}finally{
		
		try{
			if(stmt!=null)
				stmt.close();
		}catch(SQLException se2){
		}// nothing we can do
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
			//se.printStackTrace();
		}//end finally try
	}//end try

}
public void UpdatePass(String usernameOLD, String passNEW)  throws Exception {
	
	Statement stmt = null;
	Connection conn = null;
	

	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();

		stmt = conn.createStatement();

		
		
		String sql = "UPDATE individual.USERS SET password='"+passNEW+"' where username='"+usernameOLD+"'";
		stmt.executeUpdate(sql);
		System.out.println("Password got updated successfully.");			

	}catch(SQLException se){
		
		//se.printStackTrace();
		System.out.println("Invalid inputs.");
		
	}catch(Exception e){
	
		//e.printStackTrace();
		System.out.println("Invalid inputs.");
		
	}finally{
		
		try{
			if(stmt!=null)
				stmt.close();
		}catch(SQLException se2){
		}// nothing we can do
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
			se.printStackTrace();
		}//end finally try
	}//end try

}


}


