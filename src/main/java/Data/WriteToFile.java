package Data;
import java.io.FileWriter;
import java.io.IOException;
public class WriteToFile {
        public static void writeUserData(String message){
            try (FileWriter fw = new FileWriter("Lottery.txt",true)){
                fw.append(message);
                fw.append("\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

