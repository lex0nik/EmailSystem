package mail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Scanner;



public class User {
	String username;
	String password;
	String level;
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public User(){}
	
	public User(String username, String password, String level) {
		super();
		this.username = username;
		this.password = password;	
		this.level=level;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//*****************************************************************************************************
	public List<Integer> getInboxIDS(){
		Connection conn = null;
		List<Integer> lista = new ArrayList();
		try {
			ConnHelper h =new ConnHelper();
			conn = h.GetDBConnection();
			
			PreparedStatement u = conn.prepareStatement("SELECT DATE_SENT, message_id FROM individual.messages where individual.messages.sentto='"+this.username+"' ORDER BY DATE_SENT desc;");


			ResultSet rs = u.executeQuery();
			
			while (rs.next()) {
				int id= rs.getInt("message_id");
				lista.add(id);
				System.out.println();
			}


			conn.close();
			return lista;
		}catch(SQLException se){
			System.out.println("Invalid inputs.");
			//se.printStackTrace();
			return lista;
		}catch(Exception e){
			System.out.println("Invalid inputs.");
			//e.printStackTrace();
			return lista;
		}
	}
	
public List<String> ViewInbox(){
	Connection conn = null;
	List<String> lista = new ArrayList();
	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();
		
		PreparedStatement u = conn.prepareStatement("SELECT title, DATE_SENT, sentBY FROM individual.messages where individual.messages.sentto='"+this.username+"' ORDER BY DATE_SENT desc;");


		ResultSet rs = u.executeQuery();
		int counter=0;
		System.out.println("INBOX");
		System.out.println();
		while (rs.next()) {
			counter++;
			String title = rs.getString("title");
			String date_cr=rs.getString("DATE_SENT");
			String sentBY = rs.getString("sentBY");
			Formatter fmt = new Formatter();
			fmt.format("%-5s %-35s %-40s  %-30s ", counter + ".","Title: " + title,"Sent at :" + date_cr ,"Sent by: " + sentBY);
			System.out.println(fmt);
			
			lista.add(title);
			System.out.println();
		}


		conn.close();
		return lista;
	}catch(SQLException se){
		System.out.println("Invalid inputs.");
		//se.printStackTrace();
		return lista;
	}catch(Exception e){
		System.out.println("Invalid inputs.");
		//e.printStackTrace();
		return lista;
	}
}



public void ViewMessage(int id){
	Connection conn = null;
	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();

		PreparedStatement u = conn.prepareStatement("Select title, description, SentTO, SentBY, date_sent from individual.messages where message_id="+id+" ;");
		ResultSet rs = u.executeQuery();
		
		while (rs.next()) {
			String title = rs.getString("title");
			String message = rs.getString("description");
			String Sentto = rs.getString("SentTO");
			String Sentby = rs.getString("SentBy");
			String date_sent = rs.getString("date_sent");
			
			
			
			
			System.out.println("Title:   \t" +title);
			System.out.println();
			System.out.println("Message: \t" +message);
			System.out.println();
			System.out.println("Receiver: \t" +Sentto);
			System.out.println();
			System.out.println("Sender: \t" +Sentby);
			System.out.println();
			System.out.println("Date sent: \t" +date_sent );
			System.out.println();

		}


		conn.close();
	}catch(SQLException se){
		System.out.println("Invalid inputs.");
		se.printStackTrace();
	}catch(Exception e){
		System.out.println("Invalid inputs.");
		e.printStackTrace();
	}
	}


 public void CreateMessage(String title, String sentTO, String descr)  throws Exception {
		
		Statement stmt = null;
		Connection conn = null;
		SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		try {
			ConnHelper h =new ConnHelper();
			conn = h.GetDBConnection();

			stmt = conn.createStatement();

			
			
			String sql = "Insert into individual.messages values(null,'"+title+"', '"+descr+"', '"+dateFormat.format(date)+"','"+this.username+"','"+sentTO+"','0' );";
			stmt.executeUpdate(sql);
			System.out.println("Message got sent successfully.");			
			System.out.println();
		}catch(SQLException se){
			
		//	se.printStackTrace();
			System.out.println("Invalid inputs.");
			
		}catch(Exception e){
		
		//	e.printStackTrace();
			System.out.println("Invalid inputs.");
			
		}finally{
			
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			} 
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
			//	se.printStackTrace();
			}//end finally try
		}//end try

	}
 
 public void EditMessage(int id, String descr)  throws Exception {
		
		Statement stmt = null;
		Connection conn = null;
		
		
		try {
			ConnHelper h =new ConnHelper();
			conn = h.GetDBConnection();

			stmt = conn.createStatement();

			
			
			String sql = "UPDATE  messages SET description='"+descr+"' where message_id ="+id+"  ;";
			stmt.executeUpdate(sql);
			System.out.println("Message got updated successfully.");			
			System.out.println();
		}catch(SQLException se){
			
		//	se.printStackTrace();
			System.out.println("Invalid inputs.");
			
		}catch(Exception e){
		
		//	e.printStackTrace();
			System.out.println("Invalid inputs.");
			
		}finally{
			
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			} 
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
			//	se.printStackTrace();
			}//end finally try
		}//end try

	}
 
	
public List<String> ViewOthers(){
	Connection conn = null;
	List<String> lista = new ArrayList();
	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();

		PreparedStatement u = conn.prepareStatement("Select individual.users.Username as u from individual.users where individual.users.username!='"+this.username+"' and username!='admin';");


		ResultSet rs = u.executeQuery();
		int counter=0;
		System.out.println("YOUR CONTACTS:");
		System.out.println();

		while (rs.next()) {
			counter++;
			String users = rs.getString("u");
			System.out.println(counter + ". \t " +users);
			System.out.println();
			lista.add(users);
		}
		

		conn.close();
		return lista;
	}catch(SQLException se){
		 
		//se.printStackTrace();
		return lista;
	}catch(Exception e){
		 
		//e.printStackTrace();
		return lista;
	}
}

public List<String> ReturnOthers(){
	Connection conn = null;
	List<String> lista = new ArrayList();
	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();

		PreparedStatement u = conn.prepareStatement("Select individual.users.Username as u from individual.users where individual.users.username!='"+this.username+"';");


		ResultSet rs = u.executeQuery();
		

		while (rs.next()) {
			
			String users = rs.getString("u");
			
			lista.add(users);
		}
		

		conn.close();
		return lista;
	}catch(SQLException se){
		 
		//se.printStackTrace();
		return lista;
	}catch(Exception e){
		 
		//e.printStackTrace();
		return lista;
	}
}
public List<String> ViewSent(){
	Connection conn = null;
	List<String> lista = new ArrayList();
	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();

		PreparedStatement u = conn.prepareStatement("SELECT title, DATE_SENT, sentTO FROM individual.messages where individual.messages.sentBY='"+this.username+"'  ORDER BY DATE_SENT desc;");


		ResultSet rs = u.executeQuery();
		int counter=0;
		System.out.println("SENT ITEMS: ");
		System.out.println();
		while (rs.next()) {
			
			counter++;
			String title = rs.getString("title");
			String date_cr=rs.getString("DATE_SENT");
			String sentTO = rs.getString("sentTO");
			Formatter fmt = new Formatter();
			fmt.format("%-5s %-35s %-40s  %-30s ", counter + ".","Title: " + title,"Sent at :" + date_cr ,"Sent to: " + sentTO);
			System.out.println(fmt);
			
			lista.add(title);
			System.out.println();
		}


		conn.close();
		return lista;
	}catch(SQLException se){
		System.out.println("Invalid inputs.");
		//se.printStackTrace();
		return lista;
	}catch(Exception e){
		System.out.println("Invalid inputs.");
		//e.printStackTrace();
		return lista;
	}
}

public List<Integer> getSentIDS(){
	Connection conn = null;
	List<Integer> lista = new ArrayList();
	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();

		PreparedStatement u = conn.prepareStatement("SELECT DATE_SENT, message_id FROM individual.messages where individual.messages.sentBY='"+this.username+"' order by DATE_SENT desc;");


		ResultSet rs = u.executeQuery();
		
		
		while (rs.next()) {
			
			int id = rs.getInt("message_id");
			lista.add(id);
			System.out.println();
		}


		conn.close();
		return lista;
	}catch(SQLException se){
		System.out.println("Invalid inputs.");
		//se.printStackTrace();
		return lista;
	}catch(Exception e){
		System.out.println("Invalid inputs.");
		//e.printStackTrace();
		return lista;
	}
}



public void DeleteMessage(int id)  throws Exception {
	
	Statement stmt = null;
	Connection conn = null;
	//SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();

	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();

		stmt = conn.createStatement();

		
		
		String sql = "delete from individual.messages where message_id='"+id+"'";
		stmt.executeUpdate(sql);
		System.out.println("Message got deleted successfully.");			
		System.out.println();
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
		}
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
			//se.printStackTrace();
		}//end finally try
	}//end try

}


public String Unread(){
	Connection conn = null;
	String isREAD=null;
	List<String> lista = new ArrayList();
	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();

		PreparedStatement u = conn.prepareStatement("SELECT count(message_id) as C FROM individual.messages where isREAD='0' and individual.messages.sentTO='"+this.username+"';");


		ResultSet rs = u.executeQuery();
		

		while (rs.next()) {
			
			
			isREAD = rs.getString("C");
		
			
		}


		conn.close();
		return isREAD;
	}catch(SQLException se){
		 
	//	se.printStackTrace();
		return isREAD;
	}catch(Exception e){
		 
	//	e.printStackTrace();
		return isREAD;
	}
}
public void MakeRead(int id)  throws Exception {
	
	Statement stmt = null;
	Connection conn = null;
	
	try {
		ConnHelper h =new ConnHelper();
		conn = h.GetDBConnection();

		stmt = conn.createStatement();

		
		
		String sql = "Update  individual.messages set isREAD='1' where message_id="+id+" ";
		stmt.executeUpdate(sql);
					

	}catch(SQLException se){
		
	//	se.printStackTrace();
		System.out.println("Invalid inputs.");
		
	}catch(Exception e){
	
	//	e.printStackTrace();
		System.out.println("Invalid inputs.");
		
	}finally{
		
		try{
			if(stmt!=null)
				stmt.close();
		}catch(SQLException se2){
		}
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException se){
	//		se.printStackTrace();
		}//end finally try
	}//end try

}
}
	


