package com.github.dr3amr2.vlc;

import com.github.dr3amr2.vlc.oldMVC.vlcPanel;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.logger.Logger;
import uk.co.caprica.vlcj.player.*;

import javax.swing.*;
import java.awt.*;
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

        panel.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventListener());


    }

    private void initVlcArgs(PlayerModel model) {
        model.getVlcArgs().add("--file-caching=6000");
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

    private final class MediaPlayerEventListener extends MediaPlayerEventAdapter {
        @Override
        public void mediaChanged(MediaPlayer mediaPlayer, libvlc_media_t media, String mrl) {
            Logger.debug("mediaChanged(mediaPlayer={},media={},mrl={})", mediaPlayer, media, mrl);
        }

        @Override
        public void finished(MediaPlayer mediaPlayer) {
            Logger.debug("finished(mediaPlayer={})", mediaPlayer);
        }

        @Override
        public void paused(MediaPlayer mediaPlayer) {
            Logger.debug("paused(mediaPlayer={})", mediaPlayer);
        }

        @Override
        public void playing(MediaPlayer mediaPlayer) {
            Logger.debug("playing(mediaPlayer={})", mediaPlayer);
            MediaDetails mediaDetails = mediaPlayer.getMediaDetails();
            Logger.info("mediaDetails={}", mediaDetails);
        }

        @Override
        public void stopped(MediaPlayer mediaPlayer) {
            Logger.debug("stopped(mediaPlayer={})", mediaPlayer);
        }

        @Override
        public void videoOutput(MediaPlayer mediaPlayer, int newCount) {
            Logger.debug("videoOutput(mediaPlayer={},newCount={})", mediaPlayer, newCount);
            if(newCount == 0) {
                return;
            }

            MediaDetails mediaDetails = mediaPlayer.getMediaDetails();
            Logger.info("mediaDetails={}", mediaDetails);

            MediaMeta mediaMeta = mediaPlayer.getMediaMeta();
            Logger.info("mediaMeta={}", mediaMeta);

            final Dimension dimension = mediaPlayer.getVideoDimension();
            Logger.debug("dimension={}", dimension);
            if(dimension != null) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        panel.getVideoSurface().setSize(dimension);
                    }
                });
            }
        }

        @Override
        public void error(MediaPlayer mediaPlayer) {
            Logger.debug("error(mediaPlayer={})", mediaPlayer);
        }

        @Override
        public void mediaSubItemAdded(MediaPlayer mediaPlayer, libvlc_media_t subItem) {
            Logger.debug("mediaSubItemAdded(mediaPlayer={},subItem={})", mediaPlayer, subItem);
        }

        @Override
        public void mediaDurationChanged(MediaPlayer mediaPlayer, long newDuration) {
            Logger.debug("mediaDurationChanged(mediaPlayer={},newDuration={})", mediaPlayer, newDuration);
        }

        @Override
        public void mediaParsedChanged(MediaPlayer mediaPlayer, int newStatus) {
            Logger.debug("mediaParsedChanged(mediaPlayer={},newStatus={})", mediaPlayer, newStatus);
        }

        @Override
        public void mediaFreed(MediaPlayer mediaPlayer) {
            Logger.debug("mediaFreed(mediaPlayer={})", mediaPlayer);
        }

        @Override
        public void mediaStateChanged(MediaPlayer mediaPlayer, int newState) {
            Logger.debug("mediaStateChanged(mediaPlayer={},newState={})", mediaPlayer, newState);
        }

        @Override
        public void mediaMetaChanged(MediaPlayer mediaPlayer, int metaType) {
            Logger.debug("mediaMetaChanged(mediaPlayer={},metaType={})", mediaPlayer, metaType);
        }
    }

}
