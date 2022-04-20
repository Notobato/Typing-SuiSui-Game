import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

public class BPanel extends JPanel implements ActionListener, KeyListener {
    Timer timer = new Timer(1, this);

    int OneSec = 0;
    int SetTime = 3;
    int EndTime = SetTime;
    int gameTrueOrFalse = 0;
    boolean IsRestart = false;
    JButton[] bt = new JButton[2];
    JButton[] bt1 = new JButton[5];

    Image img1 = Toolkit.getDefaultToolkit().getImage("./img/sushi1.png");
    Image img2 = Toolkit.getDefaultToolkit().getImage("./img/sushi2.png");
    Image img3 = Toolkit.getDefaultToolkit().getImage("./img/sushi3.png");
    Image img4 = Toolkit.getDefaultToolkit().getImage("./img/sushi4.png");
    Image img5 = Toolkit.getDefaultToolkit().getImage("./img/sushi5.png");
    Image img6 = Toolkit.getDefaultToolkit().getImage("./img/sushi6.png");
    Image img7 = Toolkit.getDefaultToolkit().getImage("./img/sushi7.png");

    JLabel labImport = new JLabel("お皿の数:");

    int SushiCount = 0;
    JLabel labCount = new JLabel("" + SushiCount);
    int miss = 0;
    JLabel labMiss = new JLabel("MISS!");
    int StartSpace = 40;
    JLabel labStart = new JLabel("スペースを押して開始");

    JLabel labTimeStr = new JLabel("残り時間:");
    JLabel labTimeInt = new JLabel("" + EndTime);

    boolean game = false;

    int StartInt = 0;
    int sushix = 0;
    int[] sushiSize = new int[10];
    int setJ = 0, setE = 0;

    String[] mondaiJP = new String[1000];
    String[] mondaiEN = new String[1000];

    int missTyped = 0;
    int trueTyped = 0;

    int num = 0;

    BPanel() throws IOException {
        // 問題文をmondaiJPとmondaiENに上書き
        TextImport tx = new TextImport();
        for (setJ = 0; setJ < tx.count; setJ++) {
            mondaiJP[setJ] = tx.textQ[setJ];

        }
        for (setE = 0; setE < tx.countEN; setE++) {
            mondaiEN[setE] = tx.textQEN[setE];
        }

        for (int i = 0; i < 10; i++) {
            sushiSize[i] = 0;
        }
        sushiSize[0] = 200;

        for (int i = 0; i < 5; i++) {
            bt1[i] = new JButton();

        }

        bt1[0].addActionListener(this);
        bt1[0].addKeyListener(this);

        this.setLayout(null);

        labTimeStr.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
        labTimeStr.setBounds(50, 200, 150, 200);
        this.add(labTimeStr);

        labTimeInt.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
        labTimeInt.setBounds(170, 276, 50, 50);
        this.add(labTimeInt);

        labStart.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, StartSpace));
        labStart.setBounds(290, 200, 1000, 150);
        labStart.addKeyListener(this);
        this.add(labStart);

        labMiss.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, miss));
        labMiss.setBounds(450, 400, 150, 150);
        labMiss.addKeyListener(this);
        this.add(labMiss);

        labImport.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
        labImport.setBounds(750, 600, 150, 200);
        this.add(labImport);

        labCount.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
        labCount.setBounds(870, 676, 50, 50);
        this.add(labCount);

        for (int i = 1; i >= 0; i--) {
            bt[i] = new JButton();
            this.add(bt[i]);
        }

        int numDF = RandomString();
        bt[0].setText(mondaiJP[numDF]);
        bt[1].setText(mondaiEN[numDF]);

        bt[0].addKeyListener(this);
        bt[0].addActionListener(this);
        bt[1].addKeyListener(this);
        bt[1].addActionListener(this);

        bt[0].setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
        bt[1].setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));

        bt[0].setBounds(350, 800 - 300, 300, 50);// テキスト日本語
        bt[1].setBounds(350, 800 - 250, 300, 50);// テキストローマ字

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img1, sushix, 50, sushiSize[0], sushiSize[0], this);
        g.drawImage(img2, sushix, 50, sushiSize[1], sushiSize[1], this);
        g.drawImage(img3, sushix, 50, sushiSize[2], sushiSize[2], this);
        g.drawImage(img4, sushix, 50, sushiSize[3], sushiSize[3], this);
        g.drawImage(img5, sushix, 50, sushiSize[4], sushiSize[4], this);
        g.drawImage(img6, sushix, 50, sushiSize[5], sushiSize[5], this);
        g.drawImage(img7, sushix, 50, sushiSize[6], sushiSize[6], this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        // 秒数カウント
        OneSec++;
        if (OneSec >= 65) {
            EndTime--;
            labTimeInt.setText("" + EndTime);
            OneSec = 0;

            // タイムオーバー時の処理=仏説
            if (EndTime == 0) {
                for (int i = 0; i < 10; i++) {
                    sushiSize[i] = 0;
                }
                for (int i = 0; i < 2; i++) {
                    bt[i].setBounds(0, 0, 0, 0);
                }
                for (int i = 0; i < 5; i++) {
                    add(bt1[i]);
                }
                bt1[0].setFont(new Font(Font.DIALOG, Font.BOLD, 100));
                bt1[0].setText("終了");
                bt1[1].setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
                bt1[1].setText("寿司の数:" + SushiCount);
                bt1[2].setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
                bt1[2].setText("うてた数:" + trueTyped);
                bt1[3].setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
                bt1[3].setText("まちがえた数:" + missTyped);

                bt1[4].setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
                double TypeAve = (double) trueTyped/SetTime;
                bt1[4].setText(String.format("平均タイプ："+"%.1f"+"回/秒", TypeAve));

                bt1[0].setBounds(350, 100, 300, 200);// 終了の文字
                bt1[1].setBounds(350, 800 - 350, 300, 50);// 仏陀ボ
                bt1[2].setBounds(350, 800 - 300, 300, 50);// 真実判定
                bt1[3].setBounds(350, 800 - 250, 300, 50);// 戯言判定
                bt1[4].setBounds(350, 800 - 150, 300, 50);// へいきんたいぷ

                labMiss.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 0));
                labTimeInt.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 0));
                labTimeStr.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 0));
                labImport.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 0));
                labCount.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 0));
                game = false;
                IsRestart = true;
                gameTrueOrFalse = 1;
                timer.stop();
            }
        }

        sushix += 2;
        if (sushix >= this.getWidth() + 70) {
            sushix = 0;

            for (int i = 0; i < 10; i++) {
                sushiSize[i] = 0;
            }
            sushiSize[RandomSushiImg()] = 200;
            num = 0;
            int endNo = RandomString();
            bt[0].setText(mondaiJP[endNo]);
            bt[1].setText(mondaiEN[endNo]);
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

        if(e.getKeyChar() == KeyEvent.VK_ESCAPE && IsRestart == true){
            gameTrueOrFalse = 0;
            IsRestart = false;
            EndTime = SetTime;
            sushix = 0;
            miss = 0;
            StartSpace = 40;

            trueTyped = 0;
            missTyped = 0;
            SushiCount = 0;

            sushiSize[0] = 200;


            for(int i = 0; i < 5; i++){
                bt1[i].setBounds(0, 0, 0, 0);
            }

            labTimeStr.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
            labTimeStr.setBounds(50, 200, 150, 200);
            labTimeInt.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
            labTimeInt.setBounds(170, 276, 50, 50);
            labStart.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, StartSpace));
            labStart.setBounds(290, 200, 1000, 150);
            labImport.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
            labImport.setBounds(750, 600, 150, 200);
            labCount.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
            labCount.setBounds(870, 676, 50, 50);

            labTimeInt.setText("" + EndTime);
            labCount.setText("" + SushiCount);

            int numDF = RandomString();
            bt[0].setText(mondaiJP[numDF]);
            bt[1].setText(mondaiEN[numDF]);

            bt[0].setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
            bt[1].setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));

            bt[0].setBounds(350, 800 - 300, 300, 50);// テキスト日本語
            bt[1].setBounds(350, 800 - 250, 300, 50);// テキストローマ字

        }

        if (e.getKeyChar() == KeyEvent.VK_SPACE && gameTrueOrFalse == 0 && IsRestart == false) {
            timer.start();

            StartSpace = 0;
            labStart.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, StartSpace));
            game = true;
        }
        
        if ((e.getKeyChar() == KeyEvent.VK_SPACE && gameTrueOrFalse == 1)
                || (e.getKeyChar() == KeyEvent.VK_ESCAPE && game == true)) {

            if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                timer.stop();
            }

            game = false;
            gameTrueOrFalse = 0;

            EndTime = SetTime;

            sushix = 0;
            miss = 0;
            StartSpace = 40;

            trueTyped = 0;
            missTyped = 0;
            SushiCount = 0;

            for (int i = 0; i < 10; i++) {
                sushiSize[i] = 0;
            }
            sushiSize[0] = 200;

            for (int i = 0; i < 5; i++) {
                bt1[i].setBounds(0, 0, 0, 0);
            }

            labTimeInt.setText("" + EndTime);
            labCount.setText("" + SushiCount);

            labTimeStr.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
            labTimeStr.setBounds(50, 200, 150, 200);

            labTimeInt.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
            labTimeInt.setBounds(170, 276, 50, 50);

            labStart.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, StartSpace));
            labStart.setBounds(290, 200, 1000, 150);
            labStart.addKeyListener(this);

            labMiss.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, miss));
            labMiss.setBounds(450, 400, 150, 150);
            labMiss.addKeyListener(this);

            labImport.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
            labImport.setBounds(750, 600, 150, 200);

            labCount.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
            labCount.setBounds(870, 676, 50, 50);

            int numDF = RandomString();
            bt[0].setText(mondaiJP[numDF]);
            bt[1].setText(mondaiEN[numDF]);

            bt[0].setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
            bt[1].setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));

            bt[0].setBounds(350, 800 - 300, 300, 50);// テキスト日本語
            bt[1].setBounds(350, 800 - 250, 300, 50);// テキストローマ字
        }

        String str = bt[1].getText();
        int text_length = str.length();
        num = 0;
        char head_text = str.charAt(num);
        int endNo = 0;

        if (game == true) {
            if (e.getKeyChar() != KeyEvent.VK_SPACE && e.getKeyChar() != KeyEvent.VK_ESCAPE) {
                if (head_text == e.getKeyChar()) {
                    // 入力が正しい
                    num++;
                    trueTyped++;
                    bt[1].setText(str.substring(num));
                    miss = 0;
                    labMiss.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, miss));
                } else {
                    // 入力が不正
                    missTyped++;
                    miss = 40;
                    labMiss.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, miss));
                }
            }

            // すべて打てた時
            if (num == text_length) {
                endNo = RandomString();
                bt[0].setText(mondaiJP[endNo]);
                bt[1].setText(mondaiEN[endNo]);
                sushix = 0;

                for (int i = 0; i < 10; i++) {
                    sushiSize[i] = 0;
                }
                sushiSize[RandomSushiImg()] = 200;

                SushiCount++;
                labCount.setText("" + SushiCount);

                num = 0;
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    // 乱数
    public int RandomString() {
        Random ran = new Random();
        int number = ran.nextInt(setE);

        return number;
    }

    public int RandomSushiImg() {
        Random ran = new Random();
        int num = ran.nextInt(7);

        return num;
    }

}
