package com.github.dr3amr2.vlc;

/**
 * Created by Dizzy on 2/19/14.
 */
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class MinimalPlayer {

    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public static void main(final String[] args) {
        NativeLibrary.addSearchPath("libvlc", "C:\\Program Files\\VideoLAN\\VLC");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                MinimalPlayer tut = new MinimalPlayer(frame);
                tut.toString();
            }
        });
    }

    private MinimalPlayer(final JFrame frame) {
        Properties props = System.getProperties();
        props.setProperty("vlcj.log", "DEBUG");

//		final JFrame frame = new JFrame("vlcj Tutorial");
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

        frame.setContentPane(mediaPlayerComponent);

        frame.setLocation(100, 100);
        frame.setSize(300, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventListener() {

            @Override
            public void mediaChanged(MediaPlayer mediaPlayer,
                                     libvlc_media_t media, String mrl) {
            }

            @Override
            public void opening(MediaPlayer mediaPlayer) {
            }

            @Override
            public void buffering(MediaPlayer mediaPlayer, float newCache) {
            }

            @Override
            public void playing(MediaPlayer mediaPlayer) {
            }

            @Override
            public void paused(MediaPlayer mediaPlayer) {
            }

            @Override
            public void stopped(MediaPlayer mediaPlayer) {
            }

            @Override
            public void forward(MediaPlayer mediaPlayer) {
            }

            @Override
            public void backward(MediaPlayer mediaPlayer) {
            }

            @Override
            public void finished(MediaPlayer mediaPlayer) {
            }

            @Override
            public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
            }

            @Override
            public void positionChanged(MediaPlayer mediaPlayer,
                                        float newPosition) {
            }

            @Override
            public void seekableChanged(MediaPlayer mediaPlayer, int newSeekable) {
            }

            @Override
            public void pausableChanged(MediaPlayer mediaPlayer, int newPausable) {
            }

            @Override
            public void titleChanged(MediaPlayer mediaPlayer, int newTitle) {
            }

            @Override
            public void snapshotTaken(MediaPlayer mediaPlayer, String filename) {
            }

            @Override
            public void lengthChanged(MediaPlayer mediaPlayer, long newLength) {
            }

            @Override
            public void videoOutput(MediaPlayer mediaPlayer, int newCount) {
                frame.setSize(mediaPlayerComponent.getMediaPlayer().getVideoDimension());
                System.out.println("INFO: Size set.");
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
            }

            @Override
            public void mediaMetaChanged(MediaPlayer mediaPlayer, int metaType) {
            }

            @Override
            public void mediaSubItemAdded(MediaPlayer mediaPlayer,
                                          libvlc_media_t subItem) {
            }

            @Override
            public void mediaDurationChanged(MediaPlayer mediaPlayer,
                                             long newDuration) {
            }

            @Override
            public void mediaParsedChanged(MediaPlayer mediaPlayer,
                                           int newStatus) {
            }

            @Override
            public void mediaFreed(MediaPlayer mediaPlayer) {
            }

            @Override
            public void mediaStateChanged(MediaPlayer mediaPlayer, int newState) {
            }

            @Override
            public void newMedia(MediaPlayer mediaPlayer) {
            }

            @Override
            public void subItemPlayed(MediaPlayer mediaPlayer, int subItemIndex) {
            }

            @Override
            public void subItemFinished(MediaPlayer mediaPlayer,
                                        int subItemIndex) {
            }

            @Override
            public void endOfSubItems(MediaPlayer mediaPlayer) {
            }

        });

        mediaPlayerComponent.getMediaPlayer().playMedia("trololo.mp4");
    }
}