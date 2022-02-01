import kotlin.Throws
import java.io.IOException
import kotlin.jvm.JvmStatic
import javax.swing.JFrame
import java.io.File
import java.io.FileReader
import java.io.BufferedReader
import java.io.FileInputStream
import java.nio.charset.Charset

internal object test2 {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val f = BFrame()
        f.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        f.setBounds(500, 100, 1000, 800)
        f.title = "Sushi SuiSui Game"
        f.isVisible = true
    }
}