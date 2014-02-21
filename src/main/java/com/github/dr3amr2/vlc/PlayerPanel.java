package com.github.dr3amr2.vlc;

import uk.co.caprica.vlcj.logger.Logger;
import uk.co.caprica.vlcj.player.*;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.*;
import java.awt.*;

import java.util.List;

public class PlayerPanel extends JPanel{

    private Canvas videoSurface;
    private JPanel controlsPanel;

    private MediaPlayerFactory mediaPlayerFactory;
    private EmbeddedMediaPlayer mediaPlayer;
    private List<String> vlcArgs;

    public PlayerPanel(Container c) {
        c.add(this);
    }

    private void initComponents() {
        videoSurface = new Canvas();

        videoSurface.setBackground(Color.black);
        videoSurface.setSize(800, 600); // Only for initial layout

        JPopupMenu.setDefaultLightWeightPopupEnabled(false);

        mediaPlayerFactory = new MediaPlayerFactory(vlcArgs.toArray(new String[vlcArgs.size()]));

        List<AudioOutput> audioOutputs = mediaPlayerFactory.getAudioOutputs();
        Logger.debug("audioOutputs={}", audioOutputs);

        mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(videoSurface));
        mediaPlayer.setPlaySubItems(true);

        controlsPanel = new PlayerControlsPanel(mediaPlayer);

        setLayout(new BorderLayout());
        setBackground(Color.black);
        add(videoSurface, BorderLayout.CENTER);
        add(controlsPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void attachOptions(List<String> vlcArgs) {
        this.vlcArgs = vlcArgs;
        initComponents();
    }

// ***********************************************************************************************
// *  Getters and Setters
// ***********************************************************************************************

    public JFrame getRootParent() {
        Component c = this;
        while(c.getParent() != null) {
            c = c.getParent();
        }
        if(c instanceof JFrame) {
            return (JFrame)c;
        }

        return null;
    }

    public MediaPlayerFactory getMediaPlayerFactory() {
        return mediaPlayerFactory;
    }

    public EmbeddedMediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public Canvas getVideoSurface() {
        return videoSurface;
    }
}
