package com.github.dr3amr2.vlc.oldMVC;

/**
 * Created by Dizzy on 2/19/14.
 *
 * This handles all the controls that interact between the panel and the model
 */

import com.github.dr3amr2.vlc.VideoPlaybackSpeed;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class vlcController {

    vlcModel model;
    vlcPanel panel;

    public vlcController(vlcModel vlcModel, vlcPanel vlcPanel) {
        this.model = vlcModel;
        this.panel = vlcPanel;

        attach(model, panel);
    }

    private void attach(vlcModel model, final vlcPanel panel) {

        initMediaPlayerListeners(panel);
        initPlaybackControllers(panel);
        initRootPanelListeners(panel);

    }

    /**
     * This handles all the root panel windows listeners
     * @param panel
     */
    private void initRootPanelListeners(final vlcPanel panel) {
        if(panel.getRootParent() != null) {
            panel.getRootParent().addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                    panel.getMediaPlayer().release();
                    panel.getFactory().release();
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

            panel.getRootParent().addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    super.componentResized(e);
                    // System.out.println("Window Resized: Frame");
                    panel.resize(e.getComponent());
                }
            });
        } else {
            System.out.println("RootParent = null\nPlayer not displayable");
        }
    }

    /**
     * This handles all the playback controller buttons
     * @param panel
     */
    private void initPlaybackControllers(final vlcPanel panel) {

        //---- playButton ----
        panel.getControllerPanel().getPlayButton().setMargin(new Insets(0,0,0,0));
        panel.getControllerPanel().getPlayButton().setIcon(model.SMALL_PLAY_ICON);
        panel.getControllerPanel().getPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.getMediaPlayer().play();
            }
        });

        //---- pauseButton ----
        panel.getControllerPanel().getPauseButton().setMargin(new Insets(0,0,0,0));
        panel.getControllerPanel().getPauseButton().setIcon(model.SMALL_PAUSE_ICON);
        panel.getControllerPanel().getPauseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.getMediaPlayer().pause();
            }
        });

        //---- stopButton ----
        panel.getControllerPanel().getStopButton().setMargin(new Insets(0, 0, 0, 0));
        panel.getControllerPanel().getStopButton().setIcon(model.SMALL_STOP_ICON);
        panel.getControllerPanel().getStopButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.getMediaPlayer().stop();
            }
        });

        //---- videoSpeedRateComboBox ----
        panel.getControllerPanel().getVideoSpeedRateComboBox().setModel(new DefaultComboBoxModel(VideoPlaybackSpeed.values()));
        panel.getControllerPanel().getVideoSpeedRateComboBox().setSelectedItem(VideoPlaybackSpeed.REGULAR);
        panel.getControllerPanel().getVideoSpeedRateComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VideoPlaybackSpeed f = (VideoPlaybackSpeed) panel.getVideoRateComboBox().getSelectedItem();
                panel.getMediaPlayer().setRate(f.getVideoRate());
            }
        });

        //---- openButton ----
        panel.getControllerPanel().getOpenButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = panel.getFileChooser().showOpenDialog(panel);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = panel.getFileChooser().getSelectedFile();
                    model.setMediaFilePath(panel.getFileChooser().getSelectedFile().getAbsolutePath());
                    System.out.println("Opening: " + file.getName() + ".\n");

                    if (model.getMediaFilePath() != null) {
                        String path = model.getMediaFilePath();
                        System.out.println("Playing: " + path);
                        model.setMediaName(panel.getFileChooser().getName());
                        panel.getControllerPanel().getMediaNameLabel().setText(model.getMediaName());
                        panel.getMediaPlayer().playMedia(path , model.getVlcOptions());
                    }

                } else {
                    System.out.println("Open command cancelled by user.\n");
                }
            }
        });

        //---- audioButton ----
        panel.getControllerPanel().getAudioButton().setMargin(new Insets(0, 0, 0, 0));
        panel.getControllerPanel().getAudioButton().setIcon(model.SMALL_VOLUME_ICON);
        panel.getControllerPanel().getAudioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        panel.getControllerPanel().getAudioVolumeSlider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        panel.getControllerPanel().getVideoPositionSlider().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
//                panel.getMediaPlayer().setPosition(panel.getControllerPanel().getVideoPositionSlider().getValue());
//                panel.getMediaPlayer().play();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    /**
     * This handles all the listeners for the Media Player
     * @param panel
     */
    private void initMediaPlayerListeners(final vlcPanel panel) {
        panel.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventListener() {
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
                int iPos = (int)(newPosition * 100.0);
                panel.getControllerPanel().getVideoPositionSlider().setValue(iPos);
            }

            @Override
            public void seekableChanged(MediaPlayer mediaPlayer,
                                        int newSeekable) {
            }

            @Override
            public void pausableChanged(MediaPlayer mediaPlayer,
                                        int newPausable) {
            }

            @Override
            public void titleChanged(MediaPlayer mediaPlayer, int newTitle) {
            }

            @Override
            public void snapshotTaken(MediaPlayer mediaPlayer,
                                      String filename) {
            }

            @Override
            public void lengthChanged(MediaPlayer mediaPlayer,
                                      long newLength) {
            }

            @Override
            public void videoOutput(MediaPlayer player, int newCount) {
                // Set the canvas to match the size of the parent panel
                updateCanvasSize();

            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
            }

            @Override
            public void mediaMetaChanged(MediaPlayer mediaPlayer,
                                         int metaType) {
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
            public void mediaStateChanged(MediaPlayer mediaPlayer,
                                          int newState) {
            }

            @Override
            public void newMedia(MediaPlayer mediaPlayer) {
            }

            @Override
            public void subItemPlayed(MediaPlayer mediaPlayer,
                                      int subItemIndex) {
            }

            @Override
            public void subItemFinished(MediaPlayer mediaPlayer,
                                        int subItemIndex) {
            }

            @Override
            public void endOfSubItems(MediaPlayer mediaPlayer) {
            }

        });
    }

    private void updateCanvasSize() {
        panel.getCanvas().setSize(
                panel.getRootParent().getPreferredSize().width,
                panel.getRootParent().getPreferredSize().height - panel.getPlaybackControllerPanel().getPreferredSize().height
        );
        panel.getRootParent().pack();
    }

    // ***********************************************************************************************
// *  Getters and Setters
// ***********************************************************************************************
    public vlcModel getModel() {
        return model;
    }

    public void setModel(vlcModel model) {
        this.model = model;
    }

    public vlcPanel getPanel() {
        return panel;
    }

    public void setPanel(vlcPanel panel) {
        this.panel = panel;
    }
}
