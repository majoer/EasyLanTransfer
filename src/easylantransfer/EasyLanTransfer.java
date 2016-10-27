package easylantransfer;

import easylantransfer.Core.Core;
import easylantransfer.graphics.MainGraphics;

public class EasyLanTransfer {

    public static void main(String[] args) {
        Core core = new Core();
        MainGraphics g = new MainGraphics(core);
        core.setMainGraphcs(g);
        g.setVisible(true);
        core.start();
    }
}
