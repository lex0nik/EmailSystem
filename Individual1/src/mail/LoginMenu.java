package mail;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LoginMenu {
	public static List<String> LogIn(String username, String password){
		Connection conn = null;
		String A[] = new String[3] ;
		List<String> mylist =  new ArrayList();
		try {
			ConnHelper h =new ConnHelper();
			conn = h.GetDBConnection();

			PreparedStatement u = conn.prepareStatement("Select username,password,level from individual.users where individual.users.username='"+username+"' and individual.users.password='"+password+"';");


			ResultSet rs = u.executeQuery();
			
			

			while (rs.next()) {
				
				String users = rs.getString("username");
				String pass = rs.getString("password");
				String level = rs.getString("level");
				if (users!=null){
				mylist.add(users);
				mylist.add(pass);
				mylist.add(level);
				}

			
			}
			

			conn.close();
			return mylist;
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
			return mylist;
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
			return mylist;
		}
	}
	
	public static User Menoudaki(){
		Scanner sc = new Scanner(System.in);
		Console a = System.console ();
		System.out.println("LOG IN ");
		System.out.println();
		String username = a.readLine("username: ");
		
		String pass = new String (a.readPassword("password: "));
		List<String> mylist = new ArrayList();
		mylist=LogIn(username, pass);

		while (mylist.size()!=3){
			
				System.out.println("Log in failed! Retry: ");
				
				username = a.readLine("username: ");
			
				pass =  new String (a.readPassword("password: "));
				mylist = new ArrayList();
				mylist=LogIn(username, pass);
			
		
		}
		if (mylist.get(0).equals("admin") && mylist.get(1).equals("admin") ) {
			Admin ad = new Admin("admin", "admin", "admin");
			return ad;
		}
			User u = new User (mylist.get(0), mylist.get(1), mylist.get(2));
			System.out.println();
			System.out.println("Welcome " + mylist.get(0)+ "! You have " + u.Unread() + " unread messages!" );
			
			
			return u;
		}
		
		
		
		
			
		
}

