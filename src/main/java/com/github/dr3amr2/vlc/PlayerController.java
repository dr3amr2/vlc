package com.github.dr3amr2.vlc;

import com.github.dr3amr2.vlc.oldMVC.vlcPanel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by dnguyen on 2/21/14.
 */
public class PlayerController {
    private PlayerModel model;

    public PlayerPanel getPanel() {
        return panel;
    }

    private PlayerPanel panel;

    public PlayerController(PlayerModel playerModel, PlayerPanel playerPanel){
        this.model = playerModel;
        this.panel = playerPanel;

        attach(model, panel);

    }

    private void attach(PlayerModel model, PlayerPanel panel) {
        initVlcArgs(model);
        panel.attachOptions(model.getVlcArgs());

        initRootPanelListeners(panel);


    }

    private void initVlcArgs(PlayerModel model) {
        model.getVlcArgs().add(":file-caching=6000");
    }

    /**
     * This handles all the root panel windows listeners
     * @param panel
     */
    private void initRootPanelListeners(final PlayerPanel panel) {
        if(panel.getRootParent() != null) {
            panel.getRootParent().addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                    panel.getMediaPlayer().release();
                    panel.getMediaPlayerFactory().release();
                    System.exit(0);
                }

                @Override
                public void windowClosed(WindowEvent e) {
                }

                @Override
                public void windowIconified(WindowEvent e) {
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                }

                @Override
                public void windowActivated(WindowEvent e) {
                }

                @Override
                public void windowDeactivated(WindowEvent e) {
                }

            });
        } else {
            System.out.println("RootParent = null\nPlayer not displayable");
        }
    }


}
