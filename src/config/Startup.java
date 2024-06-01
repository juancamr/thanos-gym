
package config;

import controller.auth.ControladorLogin;
import utils.FrameUtils;
import view.auth.PanelLogin;
import view.auth.WindowSession;

public class Startup {
    public static void initWindow() {
        WindowSession vista = new WindowSession();
        new ControladorLogin(vista, new PanelLogin());
        FrameUtils.showWindow(vista, "Welcome");
    }
}
