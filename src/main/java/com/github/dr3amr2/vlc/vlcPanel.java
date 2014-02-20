package com.github.dr3amr2.vlc;

import net.miginfocom.swing.MigLayout;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

/**
 * Created by Dizzy on 2/19/14.
 */
public class vlcPanel extends JPanel {

    final static MediaPlayerFactory factory = new MediaPlayerFactory();

    final static EmbeddedMediaPlayer mediaPlayer = factory.newEmbeddedMediaPlayer();

    static JPanel playbackControllerPanel = new JPanel(new MigLayout());

    private String filePath;

    JSlider positionSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 5);

    final Canvas canvas = new Canvas();


    public vlcPanel(Container c) {
        setLayout(new MigLayout("debug"));
        final JPanel canvasPanel = new JPanel();
        setSize(300, 300);

        makeControls();

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

        if(getRootParent() != null) {
            getRootParent().addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                    mediaPlayer.release();
                    factory.release();
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

    public void playMedia(String mrl) {
        mediaPlayer.playMedia(mrl);
    }

    private JButton submitButton = new JButton("Load this video");
    private JButton openButton = new JButton("Open");
    private JButton playButton = new JButton("Play");
    private JButton pauseButton = new JButton("Pause");
    private JButton stopButton = new JButton("Stop");
    private final JComboBox videoRateComboBox = new JComboBox(VideoPlaybackSpeed.values());
    private final JFileChooser fileChooser = new JFileChooser();
    final JTextField mrl = new JTextField(10);

    private void makeControls() {
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.play();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.pause();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.stop();
            }
        });

        videoRateComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VideoPlaybackSpeed f = (VideoPlaybackSpeed) videoRateComboBox.getSelectedItem();
                mediaPlayer.setRate(f.getVideoRate());
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mrl != null) {
                    String path = mrl.getText();
                    System.out.println("Playing: " + path);
                    mediaPlayer.playMedia(path);
                }
            }
        });


        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(vlcPanel.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    //This is where a real application would open the file.
                    setFilePath(fileChooser.getSelectedFile().getAbsolutePath());
                    mrl.setText(getFilePath());
                    System.out.println("Opening: " + file.getName() + ".\n");
                } else {
                    System.out.println("Open command cancelled by user.\n");
                }
            }
        });

        playbackControllerPanel.add(playButton);
        playbackControllerPanel.add(pauseButton);
        playbackControllerPanel.add(stopButton);
        playbackControllerPanel.add(videoRateComboBox, "wrap");
        playbackControllerPanel.add(openButton);
        playbackControllerPanel.add(mrl, "span, grow");
        playbackControllerPanel.add(submitButton, "span, grow");
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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


}