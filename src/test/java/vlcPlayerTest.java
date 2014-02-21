import com.github.dr3amr2.vlc.PlayerController;
import com.github.dr3amr2.vlc.PlayerModel;
import com.github.dr3amr2.vlc.PlayerPanel;
import com.github.dr3amr2.vlc.oldMVC.vlcController;
import com.github.dr3amr2.vlc.oldMVC.vlcModel;
import com.github.dr3amr2.vlc.oldMVC.vlcPanel;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.LibVlcFactory;
import uk.co.caprica.vlcj.logger.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

/**
 * Created by dnguyen on 2/21/14.
 */
public class vlcPlayerTest {

    static PlayerController controller;

    public static void main(final String[] args) throws Exception {
        NativeLibrary.addSearchPath("libvlc", "C:\\Program Files\\VideoLAN\\VLC");

        LibVlc libVlc = LibVlcFactory.factory().create();

        Logger.info("  version: {}", libVlc.libvlc_get_version());
        Logger.info(" compiler: {}", libVlc.libvlc_get_compiler());
        Logger.info("changeset: {}", libVlc.libvlc_get_changeset());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Starting Media Player");
                Properties props = System.getProperties();
                props.setProperty("vlcj.log", "WARN");

                initVlcMvcMethod();

            }
        });
    }

    private static void initVlcMvcMethod() {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(600,600));
        controller = new PlayerController(new PlayerModel(), new PlayerPanel());
        frame.add(controller.getPanel());
        frame.pack();
        frame.setVisible(true);
    }

}
