package mail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {


	public static String FirstMenu(User u){
		Scanner sc = new Scanner(System.in);

		if((u.getUsername().equals("admin") && u.getPassword().equals("admin"))){
			System.out.println("\t \t MENU");
			System.out.println();
			System.out.println("Choose one of the following options: ");
			System.out.println();
			System.out.println("1. \t  Create new user.");
			System.out.println();
			System.out.println("2. \t  View users.");
			System.out.println();

			System.out.println("3. \t  Exit.");
			System.out.println();
			System.out.println("4. \t  Sign out.");
			String input = sc.nextLine();
			while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")){
				System.out.println("Please input valid argument. [1...4]");
				input = sc.nextLine();
			}
			return input;
		}
		System.out.println("\t \t MENU");
		System.out.println();
		System.out.println("Choose one of the following options: ");
		System.out.println();
		System.out.println("1. \t  Create new email.");
		System.out.println();
		System.out.println("2. \t  View inbox.");
		System.out.println();
		System.out.println("3. \t  View sent messages.");
		System.out.println();
		System.out.println("4. \t  View contacts.");
		System.out.println();
		System.out.println("5. \t  Exit.");

		System.out.println();
		System.out.println("6. \t  Sign out.");
		String input = sc.nextLine();
		while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5") && !input.equals("6")){
			System.out.println("Please input valid argument. [1...6]");
			input = sc.nextLine();
		}
		return input;
	}
	public static void BigMenu(User u) throws Exception{
		Logger log = new Logger();
		SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Scanner sc = new Scanner(System.in);
		boolean bool=false;
		String input = FirstMenu(u);
		if (input.equals("1")){
			List<String> mylist = u.ReturnOthers();
			System.out.println("Input the title of the email: ");
			String title=sc.nextLine();
			while (title.equals("0")) {
				System.out.println("You cannot use '0' as title. Input the title: ");
				title=sc.nextLine();
			}
			while (!ValidCheck(title) || title.length() > 20){
				System.out.println("Invalid input. Only digits and letters allowed, 20 characters max. ");
				title=sc.nextLine();
			}

			System.out.println("Input the receiver: ");
			String receiver=sc.nextLine();

			while (bool==false){
				for (int i=0; i<mylist.size();i++){
					if(receiver.equals(mylist.get(i))){
						bool = true;
					}
				}
				if (bool==false){
					System.out.println("User not found. Input the receiver or press 0 to return: ");
					receiver=sc.nextLine();
				}
				if(receiver.equals("0")) {
					BigMenu(u);
				}
			}
			System.out.println("Write the new message: ");
			String message=sc.nextLine();
			while (message.length() > 250){
				System.out.println("Invalid input. Provide 250 characters or less. ");
				message=sc.nextLine();
			}
			String message2 = message.replaceAll("'", "`");
			u.CreateMessage(title, receiver, message2);

			log.writedataCreate(title, message2, u.getUsername(), receiver, dateFormat.format(date));
			BigMenu(u);
		} 
		else if (input.equals("2")){

			List<String> viewlist=u.ViewInbox();
			List<Integer> IDS = u.getInboxIDS();
			if (viewlist.size()==0) {
				System.out.println("EMPTY INBOX!");
				BigMenu(u);
			}
			System.out.println();
			System.out.println("1. View an email");
			System.out.println("2. Delete an email");
			System.out.println("3. Edit an email");
			System.out.println("4. Return");
			String inputDEL=sc.nextLine();
			while (!inputDEL.equals("1") && !inputDEL.equals("2")  && !inputDEL.equals("3")  && !inputDEL.equals("4")){
				System.out.println("Please provide valid argument. [1...4]");
				inputDEL=sc.nextLine();
			}
			if (inputDEL.equals("1")){
				System.out.println("Type the number of the email you want to read: ");
				String titleinput=sc.nextLine();
				while (!isNum(titleinput)) {
					System.out.println("Please provide value arguments. Type the number of the email you want to read: ");
					titleinput=sc.nextLine();
				}

				int input2 = Integer.parseInt(titleinput);


				while (bool==false){
					if(input2 <= viewlist.size() && input2!=0 && input2>0) {
						bool=true;
					}
					if (bool==false){
						System.out.println("Email not found. Input again the number or press 0 to return: ");
						titleinput=sc.nextLine();
						while (!isNum(titleinput)) {
							System.out.println("Please provide value arguments. Input again the number or press 0 to return: ");
							titleinput=sc.nextLine();
						}
						input2 = Integer.parseInt(titleinput);
						if(titleinput.equals("0")){
							BigMenu(u);
						}
					}
				}


				u.ViewMessage(IDS.get(input2 - 1));
				u.MakeRead(IDS.get(input2 - 1));
				BigMenu(u);
			}
			else if (inputDEL.equals("2")){
				if (u.getLevel().equals("C")){
					System.out.println("Type the number of the email you want to delete: ");
					String titleinput=sc.nextLine();
					while (!isNum(titleinput)) {
						System.out.println("Please provide value arguments. Type the number of the email you want to delete: ");
						titleinput=sc.nextLine();
					}
					int input2 = Integer.parseInt(titleinput);
					while (bool==false){
						if(input2 <= viewlist.size() && input2!=0 && input2>0) {
							bool=true;
						}
						if (bool==false){
							System.out.println("Email not found. Input again the number or press 0 to return: ");
							titleinput=sc.nextLine();
							while (!isNum(titleinput)) {
								System.out.println("Please provide value arguments. Input again the number or press 0 to return: ");
								titleinput=sc.nextLine();
							}
							input2 = Integer.parseInt(titleinput);
							if(titleinput.equals("0")){
								BigMenu(u);
							}
						}
					}
					System.out.println("Are you sure you want to delete the email? Input 'Y' or 'N'.");
					String chooseDEL=sc.nextLine();
					while (!chooseDEL.equals("Y") && !chooseDEL.equals("N")){
						System.out.println("Invalid argument. Input 'Y' or 'N'.");
						chooseDEL=sc.nextLine();
					}
					if (chooseDEL.equals("Y")){
						u.DeleteMessage(IDS.get(input2 -1 ));
						BigMenu(u);
					}
					else{
						BigMenu(u);
					}
				}
				else{
					System.out.println("Sorry but you have no permission to delete a message.");
					BigMenu(u);
				}
			}
			else if(inputDEL.equals("3")) {
				if (u.getLevel().equals("C") || u.getLevel().equals("B")){
					System.out.println("Type the number of the email you want to edit: ");
					String titleinput=sc.nextLine();
					while (!isNum(titleinput)) {
						System.out.println("Please provide value arguments. Type the number of the email you want to edit: ");
						titleinput=sc.nextLine();
					}
					int input2 = Integer.parseInt(titleinput);
					while (bool==false){
						if(input2 <= viewlist.size() && input2!=0 && input2>0) {
							bool=true;
						}
						if (bool==false){
							System.out.println("Email not found. Input again the number or press 0 to return; ");
							titleinput=sc.nextLine();
							while (!isNum(titleinput)) {
								System.out.println("Please provide value arguments. Input again the number or press 0 to return: ");
								titleinput=sc.nextLine();
							}
							input2 = Integer.parseInt(titleinput);
							if(titleinput.equals("0")){
								BigMenu(u);
							}
						}
					}
					System.out.println("Type the new message: ");
					String newmsg=sc.nextLine();
					while (newmsg.length() > 250){
						System.out.println("Invalid argument. Type 250 or less characters.");
						newmsg=sc.nextLine();

					}
					String msg2 = newmsg.replaceAll("'", "`");
					u.EditMessage(IDS.get(input2 -1), msg2);
					log.writedataEdit(viewlist.get(input2-1), msg2, u.getUsername(), dateFormat.format(date));
					BigMenu(u);
				}
				else{
					System.out.println("Sorry but you have no permission to edit a message.");
					BigMenu(u);
				}

			}
			else if(inputDEL.equals("4")){
				BigMenu(u);
			}
		}
		else if(input.equals("3")){
			List<String> viewlist=u.ViewSent();
			List<Integer> IDS = u.getSentIDS();
			if (viewlist.size()==0) {
				System.out.println("0 SENT ITEMS!");
				BigMenu(u);
			}
			System.out.println();
			System.out.println("1. View an email");
			System.out.println("2. Delete an email");
			System.out.println("3. Edit an email");
			System.out.println("4. Return to menu");
			String inputDEL=sc.nextLine();
			while (!inputDEL.equals("1") && !inputDEL.equals("2") && !inputDEL.equals("3")  && !inputDEL.equals("4")){
				System.out.println("Please provide valid argument. [1...4]");
				inputDEL=sc.nextLine();
			}
			if (inputDEL.equals("1")){
				System.out.println("Type the number of the email you want to read: ");
				String titleinput=sc.nextLine();
				while (!isNum(titleinput)) {
					System.out.println("Please provide value arguments. Type the number of the email you want to read: ");
					titleinput=sc.nextLine();
				}
				int input2 = Integer.parseInt(titleinput);
				while (bool==false){
					if(input2 <= viewlist.size() && input2!=0 && input2>0) {
						bool=true;
					}
					if (bool==false){
						System.out.println("Email not found. Input again the number or press 0 to return: ");
						titleinput=sc.nextLine();
						while (!isNum(titleinput)) {
							System.out.println("Please provide value arguments. Input again the number or press 0 to return: ");
							titleinput=sc.nextLine();
						}
						input2 = Integer.parseInt(titleinput);
						if(titleinput.equals("0")){
							BigMenu(u);
						}
					}
				}


				u.ViewMessage(IDS.get(input2 -1));
				BigMenu(u);
			}
			else if (inputDEL.equals("2")){
				if (u.getLevel().equals("C")){
					System.out.println("Type the number of the email you want to delete: ");
					String titleinput=sc.nextLine(); 
					while (!isNum(titleinput)) {
						System.out.println("Please provide value arguments. Type the number of the email you want to delete: ");
						titleinput=sc.nextLine();
					}
					int input2 = Integer.parseInt(titleinput);
					while (bool==false){
						if(input2 <= viewlist.size() && input2!=0 && input2>0) {
							bool=true;
						}
						if (bool==false){
							System.out.println("Email not found. Input again the number, or press 0 to return. ");
							titleinput=sc.nextLine();
							while (!isNum(titleinput)) {
								System.out.println("Please provide value arguments. Input again the number or press 0 to return: ");
								titleinput=sc.nextLine();
							}
							input2 = Integer.parseInt(titleinput);
							if(titleinput.equals("0")){
								BigMenu(u);
							}
							if(titleinput.equals("0")){
								BigMenu(u);
							}
						}
					}
					System.out.println("Are you sure you want to delete the email? Input 'Y' or 'N'.");
					String chooseDEL=sc.nextLine();
					while (!chooseDEL.equals("Y") && !chooseDEL.equals("N")){
						System.out.println("Invalid argument. Input 'Y' for yes or 'N' for no.");
						chooseDEL=sc.nextLine();
					}
					if (chooseDEL.equals("Y")){
						u.DeleteMessage(IDS.get(input2 -1));
						BigMenu(u);
					}
					else{
						BigMenu(u);
					}

				}
				else{
					System.out.println("Sorry but you have no permission to delete a message.");
					BigMenu(u);
				}
			}
			else if(inputDEL.equals("3")) {
				if (u.getLevel().equals("C") || u.getLevel().equals("B")){
					System.out.println("Type the number of the email you want to edit: ");
					String titleinput=sc.nextLine();
					while (!isNum(titleinput)) {
						System.out.println("Please provide value arguments. Type the number of the email you want to edit: ");
						titleinput=sc.nextLine();
					}
					int input2 = Integer.parseInt(titleinput);
					while (bool==false){
						if(input2 <= viewlist.size() && input2!=0 && input2>0) {
							bool=true;
						}
						if (bool==false){
							System.out.println("Email not found. Input again the number or press 0 to return ");
							titleinput=sc.nextLine();
							while (!isNum(titleinput)) {
								System.out.println("Please provide value arguments. Input again the number or press 0 to return: ");
								titleinput=sc.nextLine();
							}
							input2 = Integer.parseInt(titleinput);
							if(titleinput.equals("0")){
								BigMenu(u);
							}
						}
					}
					System.out.println("Type the new message: ");
					String newmsg=sc.nextLine();
					while (newmsg.length() > 250){
						System.out.println("Invalid argument. Type 250 or less characters.");
						newmsg=sc.nextLine();

					}
					String msg2 = newmsg.replaceAll("'", "`");
					u.EditMessage(IDS.get(input2 -1), msg2);
					log.writedataEdit(viewlist.get(input2-1), msg2, u.getUsername(), dateFormat.format(date));
					BigMenu(u);
				}
				else{
					System.out.println("Sorry but you have no permission to edit a message.");
					BigMenu(u);
				}

			}


			else if(inputDEL.equals("4")){
				BigMenu(u);
			}
		}
		else if(input.equals("4")){
			u.ViewOthers();
			BigMenu(u);
		}
		else if(input.equals("5")){
			System.exit(0);
		}
		else if(input.equals("6")){
			LoginMenu l = new LoginMenu();
			User u2 = l.Menoudaki();
			Menu m = new Menu();
			if (u2.getUsername().equals("admin") && u2.getPassword().equals("admin")){
				m.BigAdminMenu((Admin) u2);

			}
			m.BigMenu(u2);
		}


	}
	public static boolean ValidCheck(String a){

		boolean valid=true;

		for (int i=0; i<a.length();i++){
			if (Character.isLetter(a.charAt(i)) ||  Character.isDigit(a.charAt(i) )){

			}

			else {
				valid=false;

			}
		}
		if(a.equals("")) {
			valid=false;
		}
		return valid;
	}
	//**************************************************************************************************
	public static void BigAdminMenu(Admin u) throws Exception{

		Scanner sc = new Scanner(System.in);
		boolean bool=false;
		String input = FirstMenu(u);
		if (input.equals("1")){
			List<String> mylist = u.ReturnUsers();
			System.out.println("Input the Username: ");
			String username=sc.nextLine();
			while (!ValidCheck(username)){
				System.out.println("Invalid input. Only letters and digits allowed. ");
				username=sc.nextLine();
			}
			while (bool==false){
				for (int i=0; i<mylist.size();i++){
					if(username.equals(mylist.get(i)) || username.length() > 15){

						System.out.println("If this username alrdy exists you cannot make duplicate. Provide 15 characters or less. Try again: ");
						username=sc.nextLine();
					}
					bool=true;
				}
			}
			System.out.println("Input the password ");
			String pwd=sc.nextLine();
			while (!ValidCheck(pwd) || pwd.length() > 15 || pwd.length() < 5){
				System.out.println("Invalid input. Only letters and digits allowed. Provide [5...15] characters. Try again: ");
				pwd=sc.nextLine();
			}

			u.CreateUser(username, pwd, "");

			BigAdminMenu(u);
		} 

		else if (input.equals("2")){
			List<String> viewlist=u.ViewUser();
			System.out.println();
			System.out.println("1. Edit a user");
			System.out.println("2. Delete a user");
			System.out.println("3. Return");
			String inputDEL=sc.nextLine();
			while (!inputDEL.equals("1") && !inputDEL.equals("2" ) && !inputDEL.equals("3" )){
				System.out.println("Please provide valid argument. Input 1 for edit, 2 to delete a user, 3 for return.");
				inputDEL=sc.nextLine();
			}
			if (inputDEL.equals("1")){
				System.out.println("Type username of the user you want to edit: ");
				String userinput=sc.nextLine();
				while (bool==false){
					for (int i=0; i<viewlist.size();i++){
						if(userinput.equals(viewlist.get(i))){
							bool = true;
						}
					}
					if (bool==false){
						System.out.println("User not found. Input again the username: ");
						userinput=sc.nextLine();
					}
				}
				System.out.println("User: " + userinput);
				System.out.println("What do u want to edit?");
				System.out.println("1. \t Username");
				System.out.println("2. \t Password");
				System.out.println("3. \t Level");
				String inputEDIT=sc.nextLine();
				while (!inputEDIT.equals("1") && !inputEDIT.equals("2") &&  !inputEDIT.equals("3")){
					System.out.println("Please provide valid argument. Input 1 for username, 2 for password, 3 for level.");
					inputEDIT=sc.nextLine();
				}
				if (inputEDIT.equals("1")){
					System.out.println("Input the new username: ");
					String editUsername=sc.nextLine();
					while (!ValidCheck(editUsername) || editUsername.length() > 15 ){
						System.out.println("Invalid input. Only letters and digits allowed. Provide 15 characters or less. Try again: ");
						editUsername=sc.nextLine();
					}
					while (bool==false){
						for (int i=0; i<viewlist.size();i++){
							if(editUsername.equals(viewlist.get(i))) {

								System.out.println("If this username alrdy exists you cannot make duplicate. Try again: ");
								editUsername=sc.nextLine();
							}
							bool=true;
						}
					}
					u.UpdateUsername(userinput, editUsername);
				}

				else if(inputEDIT.equals("2")){
					System.out.println("Input the new password: ");
					String editPass=sc.nextLine();
					while (!ValidCheck(editPass) || editPass.length() > 15 || editPass.length() < 5){
						System.out.println("Invalid input. Only letters and digits allowed. Provide [5...15] characters. Try again: ");
						editPass=sc.nextLine();
					}
					u.UpdatePass(userinput, editPass);

				}
				else if(inputEDIT.equals("3")){
					System.out.println("Input the new level: ");
					String editLvl=sc.nextLine();
					while (!editLvl.equals("A")  && !editLvl.equals("B")  &&!editLvl.equals("C") &&!editLvl.equals("admin") ){
						System.out.println("Invalid input. A, B or C");
						editLvl=sc.nextLine();
					}
					u.UpdateLevel(userinput, editLvl);

				}
				BigAdminMenu(u);
			}
			else if (inputDEL.equals("2")){

				System.out.println("Type the username of the user you want to delete: ");
				String username2=sc.nextLine();
				while (bool==false){
					for (int i=0; i<viewlist.size();i++){
						if(username2.equals(viewlist.get(i))){
							bool = true;
						}
					}
					if (bool==false){
						System.out.println("User not found. Input again the username: ");
						username2=sc.nextLine();
					}
				}
				System.out.println("Are you sure you want to delete the user? Input 'Y' or 'N'.");
				String chooseDEL=sc.nextLine();
				while (!chooseDEL.equals("Y") && !chooseDEL.equals("N")){
					System.out.println("Invalid argument. Input 'Y' or 'N'.");
					chooseDEL=sc.nextLine();
				}
				if (chooseDEL.equals("Y")){
					u.DeleteUser(username2);
					BigAdminMenu(u);
				}
				else{
					BigAdminMenu(u);
				}
			}
			else if(inputDEL.equals("3")){
				BigAdminMenu(u);
			}

		}
		else if(input.equals("3")){
			System.exit(0);
		}
		else if(input.equals("4")){
			LoginMenu l = new LoginMenu();
			User u2 = l.Menoudaki();
			Menu m = new Menu();
			if (u2.getUsername().equals("admin") && u2.getPassword().equals("admin")){
				m.BigAdminMenu((Admin) u2);

			}
			m.BigMenu(u2);
		}
	}

	public static boolean isNum(String input) {
		boolean bool = true;
		try {

			Integer.parseInt(input);

		}catch (NumberFormatException e) {
			bool = false;
		}
		return bool;
	}
}













