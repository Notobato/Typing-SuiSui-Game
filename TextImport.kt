import java.io.*
import kotlin.Throws
import kotlin.jvm.JvmStatic
import javax.swing.JFrame
import java.nio.charset.Charset

class TextImport internal constructor() {
    // 日本語ファイルq1.txt
    var f = File("./q/q1.txt")

    /*
     * FileReader fr = new FileReader(f);
     * BufferedReader br = new BufferedReader(fr);
     */
    var textQ = arrayOfNulls<String>(100)
    var count = 0

    // ローマ字ファイルq1EN.txt
    var fEN = File("./q/q1EN.txt")
    var frEN = FileReader(fEN)
    var brEN = BufferedReader(frEN)
    var textQEN = arrayOfNulls<String>(100)
    var countEN = 0

    init {
        BufferedReader(
            InputStreamReader(FileInputStream(f), Charset.forName("UTF-8"))
        ).use { br ->
            while (br.readLine().also { textQ[count] = it } != null) {
                count++
            }
        }
        /*
         * while((textQ[count] = br.readLine()) != null){
         * //textQ[count] = br.readLine();
         * count++;
         * }
         */while (brEN.readLine().also { textQEN[countEN] = it } != null) {
            // textQEN[count] = br.readLine();
            countEN++
        }
    }
}