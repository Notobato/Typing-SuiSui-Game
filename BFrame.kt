import javax.swing.JFrame

class BFrame internal constructor() : JFrame() {
    init {
        val p = BPanel()
        this.add(p)
    }
}