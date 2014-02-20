package com.github.dr3amr2.vlc;

/**
 * Created by Dizzy on 2/19/14.
 *
 * This is the vlc panel where it only controls the display of how the panel will look and nothing else.
 */

import net.miginfocom.swing.MigLayout;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;

import javax.swing.*;
import java.awt.*;

public class vlcPanel extends JPanel {

    final static MediaPlayerFactory factory = new MediaPlayerFactory();
    final static EmbeddedMediaPlayer mediaPlayer = factory.newEmbeddedMediaPlayer();

    private JPanel playbackControllerPanel = new JPanel(new MigLayout());
    private Canvas canvas = new Canvas();

    private JButton submitButton;
    private JButton openButton;
    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;

    private JSlider positionSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 5);
    private JComboBox videoRateComboBox;
    private JFileChooser fileChooser;
    private JTextField mrl;

    public vlcPanel(Container c) {
        setLayout(new MigLayout());
        final JPanel canvasPanel = new JPanel();
        setSize(300, 300);
        initComponents();

        canvas.setSize(200, 200);
        canvasPanel.add(canvas);

        add(canvasPanel);
        add(playbackControllerPanel, "south");
        c.add(this);

        CanvasVideoSurface surface = factory.newVideoSurface(canvas);
        mediaPlayer.setVideoSurface(surface);

        //		while(mediaPlayer.isPlayable() || mediaPlayer.isPlaying()) {
        //			try {
        //				Thread.currentThread().join();
        //			} catch(InterruptedException ie) {}
        //		}

    }

    private void initComponents() {
        submitButton = new JButton("Load this video");
        openButton = new JButton("Open");
        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");
        videoRateComboBox = new JComboBox(VideoPlaybackSpeed.values());
        fileChooser = new JFileChooser();
        mrl = new JTextField(10);

        playbackControllerPanel.add(playButton);
        playbackControllerPanel.add(pauseButton);
        playbackControllerPanel.add(stopButton);
        playbackControllerPanel.add(videoRateComboBox, "wrap");
        playbackControllerPanel.add(openButton);
        playbackControllerPanel.add(mrl, "span, grow");
        playbackControllerPanel.add(submitButton, "span, grow");
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

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getOpenButton() {
        return openButton;
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JComboBox getVideoRateComboBox() {
        return videoRateComboBox;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public JTextField getMrl() {
        return mrl;
    }
}