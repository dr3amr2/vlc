package com.github.dr3amr2.vlc;

/**
 * Created by Dizzy on 2/19/14.
 *
 * This is the vlc panel where it only controls the display of how the panel will look and nothing else.
 */

import net.miginfocom.swing.MigLayout;
import uk.co.caprica.vlcj.component.EmbeddedMediaListPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class vlcPanel extends JPanel {

    final static MediaPlayerFactory factory = new MediaPlayerFactory();
    final static EmbeddedMediaPlayer mediaPlayer = factory.newEmbeddedMediaPlayer();
    CanvasVideoSurface surface;

    public PlaybackControllerPanel getControllerPanel() {
        return controllerPanel;
    }

    PlaybackControllerPanel controllerPanel;

    public JPanel getPlaybackControllerPanel() {
        return playbackControllerPanel;
    }

    private JPanel canvasPanel;
    private JPanel playbackControllerPanel;
    private Canvas canvas = new Canvas();

    private JButton submitButton;
    private JButton openButton;
    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;

    private JSlider positionSlider;
    private JComboBox videoRateComboBox;
    private JFileChooser fileChooser;
    private JTextField mrl;

    public vlcPanel(Container c) {

        canvasPanel = new JPanel();
        playbackControllerPanel = new JPanel(new MigLayout());
        controllerPanel = new PlaybackControllerPanel();

        setLayout(new MigLayout());

        playbackControllerPanel.setPreferredSize(new Dimension(
                c.getPreferredSize().width,
                c.getPreferredSize().height / 3)
        );

        initComponents();

        canvas.setSize(
                c.getPreferredSize().width,
                c.getPreferredSize().height-playbackControllerPanel.getPreferredSize().height
        );

        canvasPanel.add(canvas);

        add(canvasPanel);
//        add(playbackControllerPanel, "south");
        add(controllerPanel, "south");
        c.add(this);
        surface = factory.newVideoSurface(canvas);
        mediaPlayer.setVideoSurface(surface);

        //		while(mediaPlayer.isPlayable() || mediaPlayer.isPlaying()) {
        //			try {
        //				Thread.currentThread().join();
        //			} catch(InterruptedException ie) {}
        //		}

    }

    private void initComponents() {
        fileChooser = new JFileChooser();
        mrl = new JTextField(10);
        positionSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 5);
    }

    public void resize(Component c){
        playbackControllerPanel.setPreferredSize(new Dimension(
                c.getPreferredSize().width,
                c.getPreferredSize().height/3)
        );

        canvas.setSize(
                c.getPreferredSize().width,
                c.getPreferredSize().height-playbackControllerPanel.getPreferredSize().height
        );

        surface = factory.newVideoSurface(canvas);
        mediaPlayer.setVideoSurface(surface);

        repaint();

    }

    public void playMedia(String mrl) {
        mediaPlayer.playMedia(mrl);
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

    public static EmbeddedMediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JSlider getPositionSlider() {
        return positionSlider;
    }

    public static MediaPlayerFactory getFactory() {
        return factory;
    }

    public JComboBox getVideoRateComboBox() {
        return videoRateComboBox;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }
}