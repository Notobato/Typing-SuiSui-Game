import javax.swing.JPanel
import java.awt.event.ActionListener
import java.awt.event.KeyListener
import javax.swing.JButton
import java.awt.Image
import javax.swing.JLabel
import java.awt.Font
import java.awt.Graphics
import java.awt.Toolkit
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import java.util.*
import javax.swing.Timer

class BPanel internal constructor() : JPanel(), ActionListener, KeyListener {
    var timer = Timer(1, this)
    var OneSec = 0
    var EndTime = 5
    var gameTrueOrFalse = 0
    var bt = arrayOfNulls<JButton>(2)
    var bt1 = arrayOfNulls<JButton>(4)
    var img1 = Toolkit.getDefaultToolkit().getImage("./img/sushi1.png")
    var img2 = Toolkit.getDefaultToolkit().getImage("./img/sushi2.png")
    var img3 = Toolkit.getDefaultToolkit().getImage("./img/sushi3.png")
    var img4 = Toolkit.getDefaultToolkit().getImage("./img/sushi4.png")
    var img5 = Toolkit.getDefaultToolkit().getImage("./img/sushi5.png")
    var img6 = Toolkit.getDefaultToolkit().getImage("./img/sushi6.png")
    var img7 = Toolkit.getDefaultToolkit().getImage("./img/sushi7.png")
    var labImport = JLabel("お皿の数:")
    var SushiCount = 0
    var labCount = JLabel("" + SushiCount)
    var miss = 0
    var labMiss = JLabel("MISS!")
    var StartSpace = 40
    var labStart = JLabel("スペースを押して開始")
    var labTimeStr = JLabel("残り時間:")
    var labTimeInt = JLabel("" + EndTime)
    var game = false
    var StartInt = 0
    var sushix = 0
    var sushiSize = IntArray(10)
    var setJ = 0
    var setE = 0
    var mondaiJP = arrayOfNulls<String>(1000)
    var mondaiEN = arrayOfNulls<String>(1000)
    var missTyped = 0
    var trueTyped = 0
    var num = 0

    init {
        // 問題文をmondaiJPとmondaiENに上書き
        val tx = TextImport()
        setJ = 0
        while (setJ < tx.count) {
            mondaiJP[setJ] = tx.textQ[setJ]
            setJ++
        }
        setE = 0
        while (setE < tx.countEN) {
            mondaiEN[setE] = tx.textQEN[setE]
            setE++
        }
        for (i in 0..9) {
            sushiSize[i] = 0
        }
        sushiSize[0] = 200
        for (i in 0..3) {
            bt1[i] = JButton()
        }
        bt1[0]!!.setBounds(350, 100, 300, 200) // 終了の文字
        bt1[1]!!.setBounds(350, 800 - 350, 300, 50) // 仏陀ボタン
        bt1[2]!!.setBounds(350, 800 - 300, 300, 50) // 真実判定
        bt1[3]!!.setBounds(350, 800 - 250, 300, 50) // 戯言判定
        this.layout = null
        labTimeStr.font = Font(Font.DIALOG_INPUT, Font.BOLD, 24)
        labTimeStr.setBounds(50, 200, 150, 200)
        this.add(labTimeStr)
        labTimeInt.font = Font(Font.DIALOG_INPUT, Font.BOLD, 24)
        labTimeInt.setBounds(170, 276, 50, 50)
        this.add(labTimeInt)
        labStart.font = Font(Font.DIALOG_INPUT, Font.BOLD, StartSpace)
        labStart.setBounds(290, 200, 1000, 150)
        labStart.addKeyListener(this)
        this.add(labStart)
        labMiss.font = Font(Font.DIALOG_INPUT, Font.BOLD, miss)
        labMiss.setBounds(450, 400, 150, 150)
        labMiss.addKeyListener(this)
        this.add(labMiss)
        labImport.font = Font(Font.DIALOG_INPUT, Font.BOLD, 24)
        labImport.setBounds(750, 600, 150, 200)
        this.add(labImport)
        labCount.font = Font(Font.DIALOG_INPUT, Font.BOLD, 24)
        labCount.setBounds(870, 676, 50, 50)
        this.add(labCount)
        for (i in 1 downTo 0) {
            bt[i] = JButton()
            this.add(bt[i])
        }
        val numDF = RandomString()
        bt[0]!!.text = mondaiJP[numDF]
        bt[1]!!.text = mondaiEN[numDF]
        bt[0]!!.addKeyListener(this)
        bt[0]!!.addActionListener(this)
        bt[1]!!.addKeyListener(this)
        bt[1]!!.addActionListener(this)
        bt[0]!!.font = Font(Font.DIALOG_INPUT, Font.BOLD, 20)
        bt[1]!!.font = Font(Font.DIALOG_INPUT, Font.BOLD, 20)
        bt[0]!!.setBounds(350, 800 - 300, 300, 50) // テキスト日本語
        bt[1]!!.setBounds(350, 800 - 250, 300, 50) // テキストローマ字
    }

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.drawImage(img1, sushix, 50, sushiSize[0], sushiSize[0], this)
        g.drawImage(img2, sushix, 50, sushiSize[1], sushiSize[1], this)
        g.drawImage(img3, sushix, 50, sushiSize[2], sushiSize[2], this)
        g.drawImage(img4, sushix, 50, sushiSize[3], sushiSize[3], this)
        g.drawImage(img5, sushix, 50, sushiSize[4], sushiSize[4], this)
        g.drawImage(img6, sushix, 50, sushiSize[5], sushiSize[5], this)
        g.drawImage(img7, sushix, 50, sushiSize[6], sushiSize[6], this)
    }

    override fun actionPerformed(e: ActionEvent) {
        // TODO Auto-generated method stub
        if (e.source === bt[0] && gameTrueOrFalse == 0) {
            timer.start()
            StartSpace = 0
            labStart.font = Font(Font.DIALOG_INPUT, Font.BOLD, StartSpace)
            game = true
        }

        // 秒数カウント
        OneSec++
        if (OneSec >= 65) {
            EndTime--
            labTimeInt.text = "" + EndTime
            OneSec = 0

            // タイムオーバー時の処理=仏説
            if (EndTime == 0) {
                for (i in 0..9) {
                    sushiSize[i] = 0
                }
                for (i in 0..1) {
                    bt[i]!!.setBounds(0, 0, 0, 0)
                }
                for (i in 0..3) {
                    add(bt1[i])
                }
                bt1[0]!!.font = Font(Font.DIALOG, Font.BOLD, 100)
                bt1[0]!!.text = "終了"
                bt1[1]!!.font = Font(Font.DIALOG_INPUT, Font.BOLD, 24)
                bt1[1]!!.text = "お皿の数:$SushiCount"
                bt1[2]!!.font = Font(Font.DIALOG_INPUT, Font.BOLD, 24)
                bt1[2]!!.text = "正しい数:$trueTyped"
                bt1[3]!!.font = Font(Font.DIALOG_INPUT, Font.BOLD, 24)
                bt1[3]!!.text = "間違った数:$missTyped"
                labMiss.font = Font(Font.DIALOG_INPUT, Font.BOLD, 0)
                labTimeInt.font = Font(Font.DIALOG_INPUT, Font.BOLD, 0)
                labTimeStr.font = Font(Font.DIALOG_INPUT, Font.BOLD, 0)
                labImport.font = Font(Font.DIALOG_INPUT, Font.BOLD, 0)
                labCount.font = Font(Font.DIALOG_INPUT, Font.BOLD, 0)
                game = false
                gameTrueOrFalse = 1
                timer.stop()
            }
        }
        sushix += 2
        if (sushix >= width + 70) {
            sushix = 0
            for (i in 0..9) {
                sushiSize[i] = 0
            }
            sushiSize[RandomSushiImg()] = 200
            num = 0
            val endNo = RandomString()
            bt[0]!!.text = mondaiJP[endNo]
            bt[1]!!.text = mondaiEN[endNo]
        }
        repaint()
    }

    override fun keyTyped(e: KeyEvent) {
        // TODO Auto-generated method stub
        val str = bt[1]!!.text
        val text_length = str.length
        num = 0
        val head_text = str[num]
        var endNo = 0
        if (game == true) {
            if (head_text == e.keyChar) {
                // 入力が正しい
                num++
                trueTyped++
                bt[1]!!.text = str.substring(num)
                miss = 0
                labMiss.font = Font(Font.DIALOG_INPUT, Font.BOLD, miss)
            } else {
                // 入力が不正
                missTyped++
                miss = 40
                labMiss.font = Font(Font.DIALOG_INPUT, Font.BOLD, miss)
            }

            // すべて打てた時
            if (num == text_length) {
                endNo = RandomString()
                println("AllOk")
                bt[0]!!.text = mondaiJP[endNo]
                bt[1]!!.text = mondaiEN[endNo]
                sushix = 0
                for (i in 0..9) {
                    sushiSize[i] = 0
                }
                sushiSize[RandomSushiImg()] = 200
                SushiCount++
                labCount.text = "" + SushiCount
                num = 0
            }
        }
        repaint()
    }

    override fun keyPressed(e: KeyEvent) {
        // TODO Auto-generated method stub
    }

    override fun keyReleased(e: KeyEvent) {
        // TODO Auto-generated method stub
    }

    // 乱数
    fun RandomString(): Int {
        val ran = Random()
        return ran.nextInt(setE)
    }

    fun RandomSushiImg(): Int {
        val ran = Random()
        return ran.nextInt(7)
    }
}