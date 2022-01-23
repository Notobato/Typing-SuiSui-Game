import java.io.IOException;

import javax.swing.JFrame;

public class BFrame extends JFrame {

    BFrame() throws IOException {
        BPanel p = new BPanel();
        this.add(p);
    }
}
