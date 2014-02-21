package com.github.dr3amr2.vlc.oldMVC;

/**
 * Created by Dizzy on 2/19/14.
 *
 */
import com.github.dr3amr2.vlc.oldMVC.vlcController;
import com.github.dr3amr2.vlc.oldMVC.vlcModel;
import com.github.dr3amr2.vlc.oldMVC.vlcPanel;
import com.github.dr3amr2.vlc.utils.Player;
import com.sun.jna.NativeLibrary;

import java.awt.*;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class mainVLC {
    static vlcController controller;

    public static void main(String[] args) {

        NativeLibrary.addSearchPath("libvlc", "C:\\Program Files\\VideoLAN\\VLC");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                System.out.println("Starting Media Player");
                Properties props = System.getProperties();
                props.setProperty("vlcj.log", "WARN");

                initVlcPlayer();

//                initVlcMvcMethod();

            }
        });
    }

    private static void initVlcMvcMethod() {

        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(600,600));
        controller = new vlcController(new vlcModel(), new vlcPanel(frame));
        frame.add(controller.getPanel());
        frame.pack();
        frame.setVisible(true);

    }

    private static void initVlcPlayer(){

        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(600,600));
        Player p = new Player(frame);
        p.createToolTip(); // actually doing nothing
        frame.pack();
        frame.setVisible(true);

    }
}