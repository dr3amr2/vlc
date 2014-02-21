/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009, 2010, 2011, 2012, 2013, 2014 Caprica Software Limited.
 */

package com.github.dr3amr2.vlc;

import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.LibVlcFactory;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.logger.Logger;
import uk.co.caprica.vlcj.player.*;
import uk.co.caprica.vlcj.player.embedded.DefaultFullScreenStrategy;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple test harness creates an AWT Window and plays a video.
 * <p>
 * This is <strong>very</strong> basic but should give you an idea of how to build a media player.
 * <p>
 * In case you didn't realise, you can press F12 to toggle the visibility of the player controls.
 * <p>
 * Java7 provides -Dsun.java2d.xrender=True or -Dsun.java2d.xrender=true, might give some general
 * performance improvements in graphics rendering.
 */
public class PlayerPanel extends JPanel{

//    private JPanel mainFrame;
    private Canvas videoSurface;
    private JPanel controlsPanel;

    private MediaPlayerFactory mediaPlayerFactory;

    private EmbeddedMediaPlayer mediaPlayer;
    private List<String> vlcArgs;

    public PlayerPanel() {
//        initComponents();

    }

    private void initComponents() {
        videoSurface = new Canvas();

        videoSurface.setBackground(Color.black);
        videoSurface.setSize(800, 600); // Only for initial layout

        // Since we're mixing lightweight Swing components and heavyweight AWT
        // components this is probably a good idea
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);

        PlayerMouseListener mouseListener = new PlayerMouseListener();
        videoSurface.addMouseListener(mouseListener);
        videoSurface.addMouseMotionListener(mouseListener);
        videoSurface.addMouseWheelListener(mouseListener);



        // Special case to help out users on Windows (supposedly this is not actually needed)...
//        if(RuntimeUtil.isWindows()) {
//            vlcArgs.add("--plugin-path=" + WindowsRuntimeUtil.getVlcInstallDir() + "\\plugins");
//        }
//        else {
//            vlcArgs.add("--plugin-path=/home/linux/vlc/lib");
//        }
//
//        vlcArgs.add("--plugin-path=" + System.getProperty("user.home") + "/.vlcj");

//        Logger.debug("vlcArgs={}", vlcArgs);

//        mainFrame = new JPanel();

//        FullScreenStrategy fullScreenStrategy = new DefaultFullScreenStrategy(mainFrame);

        mediaPlayerFactory = new MediaPlayerFactory(vlcArgs.toArray(new String[vlcArgs.size()]));
        mediaPlayerFactory.setUserAgent("vlcj test player");

        List<AudioOutput> audioOutputs = mediaPlayerFactory.getAudioOutputs();
        Logger.debug("audioOutputs={}", audioOutputs);

        mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(videoSurface));
        mediaPlayer.setPlaySubItems(true);

        mediaPlayer.setEnableKeyInputHandling(false);
        mediaPlayer.setEnableMouseInputHandling(false);

        controlsPanel = new PlayerControlsPanel(mediaPlayer);

        setLayout(new BorderLayout());
        setBackground(Color.black);
        add(videoSurface, BorderLayout.CENTER);
        add(controlsPanel, BorderLayout.SOUTH);
//        mainFrame.pack();
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent evt) {
//                Logger.debug("windowClosing(evt={})", evt);
//
//                if(mediaPlayer != null) {
//                    mediaPlayer.release();
//                    mediaPlayer = null;
//                }
//
//                if(mediaPlayerFactory != null) {
//                    mediaPlayerFactory.release();
//                    mediaPlayerFactory = null;
//                }
//            }
//        });

        setVisible(true);

        mediaPlayer.addMediaPlayerEventListener(new MediaPlayerEventListener());
    }

    public void attachOptions(List<String> vlcArgs) {
        this.vlcArgs = vlcArgs;
        initComponents();
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
                        videoSurface.setSize(dimension);
//                        mainFrame.pack();
                    }
                });
            }

            // You can set a logo like this if you like...
            File logoFile = new File("./etc/vlcj-logo.png");
            if(logoFile.exists()) {
                mediaPlayer.setLogoFile(logoFile.getAbsolutePath());
                mediaPlayer.setLogoOpacity(0.5f);
                mediaPlayer.setLogoLocation(10, 10);
                mediaPlayer.enableLogo(true);
            }

            // Demo the marquee
            mediaPlayer.setMarqueeText("vlcj java bindings for vlc");
            mediaPlayer.setMarqueeSize(40);
            mediaPlayer.setMarqueeOpacity(95);
            mediaPlayer.setMarqueeColour(Color.white);
            mediaPlayer.setMarqueeTimeout(5000);
            mediaPlayer.setMarqueeLocation(50, 120);
            mediaPlayer.enableMarquee(true);

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

    /**
     *
     *
     * @param enable
     */
    @SuppressWarnings("unused")
    private void enableMousePointer(boolean enable) {
        Logger.debug("enableMousePointer(enable={})", enable);
        if(enable) {
            videoSurface.setCursor(null);
        }
        else {
            Image blankImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            videoSurface.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(blankImage, new Point(0, 0), ""));
        }
    }

    /**
     *
     */
    private final class PlayerMouseListener extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            Logger.trace("mouseMoved(e={})", e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Logger.debug("mousePressed(e={})", e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Logger.debug("mouseReleased(e={})", e);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            Logger.debug("mouseClicked(e={})", e);
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            Logger.debug("mouseWheelMoved(e={})", e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            Logger.debug("mouseEntered(e={})", e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Logger.debug("mouseExited(e={})", e);
        }
    }
}
