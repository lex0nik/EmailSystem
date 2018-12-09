package mail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

	
	
	public static void writedataCreate(String title, String message, String Sender, String Receiver, String date) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        try {
        File file = new File("C:/Users/user/eclipse-workspace/Individual1/bin/mail/logger.txt");
        // if file does not exists, then create it
        if (!file.exists()) {
        file.createNewFile();
        }
        fw = new FileWriter(file.getAbsoluteFile(), true);
        bw = new BufferedWriter(fw);



String result = new String (String.format("%s %s %s %s %s %s \r\n", "Datetime: " + date , " \t Action: Created"," \t Title: " + title,"                     \t Sender: " + Sender," \t Receiver: " + Receiver, " \t Message: "  + message) );



    bw.write(result);


}catch (IOException e){
}
finally {

    try {

        if (bw != null)
            bw.close();

        if (fw != null)
            fw.close();

    } catch (IOException ex) {}
}
}
	
	public static void writedataEdit(String title, String message, String Editor, String date) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        PrintWriter writer;
        try {
        File file = new File("C:/Users/user/eclipse-workspace/Individual1/bin/mail/logger.txt");
        // if file does not exists, then create it
        if (!file.exists()) {
        file.createNewFile();
        }
        fw = new FileWriter(file.getAbsoluteFile(), true);
        bw = new BufferedWriter(fw);



String result = new String (String.format("%s %s %s %s %s \r\n", "Datetime: " + date, " \t Action: Edited"," \t Title: " + title,"                     \t Editor: " + Editor, " \t Message: "  + message ));



    bw.write(result);


}catch (IOException e){
}
finally {

    try {

        if (bw != null)
            bw.close();

        if (fw != null)
            fw.close();

    } catch (IOException ex) {}
}
}
	
	
}
