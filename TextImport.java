import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class TextImport {
    
    //日本語ファイルq1.txt
    File f = new File("./q/q1.txt");
    /*FileReader fr = new FileReader(f);
    BufferedReader br = new BufferedReader(fr);
    */
    String[] textQ = new String[100];
    int count = 0;
    
    

    //ローマ字ファイルq1EN.txt
    File fEN = new File("./q/q1EN.txt");
    FileReader frEN = new FileReader(fEN);
    BufferedReader brEN = new BufferedReader(frEN);
    String[] textQEN = new String[100];
    int countEN = 0;


    TextImport() throws IOException{
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), Charset.forName("UTF-8")))) {
            while ((textQ[count] = br.readLine()) != null) {
                count++;
            }
        }
        /*
        while((textQ[count] = br.readLine()) != null){
            //textQ[count] = br.readLine();
            count++;
        }
        */

        while((textQEN[countEN] = brEN.readLine()) != null){
            //textQEN[count] = br.readLine();
            countEN++;
        }
    }
}

