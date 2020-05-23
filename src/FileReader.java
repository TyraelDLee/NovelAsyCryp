import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class FileReader {
    private static OutputStreamWriter osw;
    private static BufferedWriter fileWrite;

    public FileReader(){

    }
    
    private String readFile(String src){
        String fileContent = "";
        try {
            BufferedReader fileReader = new BufferedReader(new java.io.FileReader(src));
            for (String out = ""; (out = fileReader.readLine())!=null;){
                fileContent+=out+"\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}
