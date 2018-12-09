package mail;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		LoginMenu l = new LoginMenu();
		Menu m = new Menu();
		
		 User u = l.Menoudaki();

		 System.out.println();
		 if (u.getUsername().equals("admin") && u.getPassword().equals("admin")){
			 m.BigAdminMenu((Admin) u);
			 
		 }
		 m.BigMenu(u);
		 


}


}
		
