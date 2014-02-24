package com.github.dr3amr2.vlc;

import uk.co.caprica.vlcj.filter.swing.SwingFileFilterFactory;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import java.awt.event.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by dnguyen on 2/21/14.
 */
public class MediaControllerController {

    private MediaControllerPanel panel;
    private PlayerModel model;

    EmbeddedMediaPlayer mediaPlayer;

    private JFileChooser fileChooser;
    private boolean mousePressedPlaying = false;
    private boolean isMute = false;


    public MediaControllerController(EmbeddedMediaPlayer mediaPlayer, PlayerModel playerModel, MediaControllerPanel mediaControllerPanel){
        this.mediaPlayer = mediaPlayer;
        this.model = playerModel;
        this.panel = mediaControllerPanel;

        setupFileChooser();
        attach(mediaPlayer, panel);

        // Setting timeLabels to default 00:00:00
        updateTimeLabel(0, panel.getStartTimeLabel());
        updateTimeLabel(0, panel.getEndTimeLabel());
        updateTimeLabel(0, panel.getCurrentTimeLabel());

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new UpdateRunnable(mediaPlayer), 0L, 1L, TimeUnit.SECONDS);

    }

    private void attach(EmbeddedMediaPlayer mediaPlayer, MediaControllerPanel panel) {
        registerListeners();

    }

    private void registerListeners() {
        mediaPlayer.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void playing(MediaPlayer mediaPlayer) {
//                updateVolume(mediaPlayer.getVolume());
            }
        });

        panel.getVideoPositionSlider().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (mediaPlayer.isPlaying()) {
                    mousePressedPlaying = true;
                    mediaPlayer.pause();
                } else {
                    mousePressedPlaying = false;
                }
                setSliderBasedPosition();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setSliderBasedPosition();
                updateUIState();
            }
        });

        panel.getStopButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.stop();
            }
        });

        panel.getPauseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.pause();
            }
        });

        panel.getPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.play();
            }
        });

        panel.getMuteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isMute = !isMute;
                if (isMute) {
                    panel.getMuteButton().setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/sound_mute.png")));
                } else {
                    panel.getMuteButton().setIcon(new ImageIcon(getClass().getClassLoader().getResource("com/github/dr3amr2/vlc/icons/volume.png")));
                }
                mediaPlayer.mute();
            }
        });

        panel.getVolumeSlider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                // if(!source.getValueIsAdjusting()) {
                mediaPlayer.setVolume(source.getValue());
                // }
            }
        });

        panel.getOpenButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.enableOverlay(false);
                if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(panel)) {
                    mediaPlayer.playMedia(fileChooser.getSelectedFile().getAbsolutePath());
                    model.setClipLabel(fileChooser.getSelectedFile().getName());
                    model.setMediaFilePath(fileChooser.getSelectedFile().getAbsolutePath());
                }
                mediaPlayer.enableOverlay(true);
            }
        });

        panel.getConnectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.enableOverlay(false);
                String mediaUrl = JOptionPane.showInputDialog(panel, "Enter a media URL", "Connect to media", JOptionPane.QUESTION_MESSAGE);
                if (mediaUrl != null && mediaUrl.length() > 0) {
                    mediaPlayer.playMedia(mediaUrl);
                }
                mediaPlayer.enableOverlay(true);
            }
        });

        panel.getPlaybackSpeedComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VideoPlaybackSpeed f = (VideoPlaybackSpeed) panel.getPlaybackSpeedComboBox().getSelectedItem();
                mediaPlayer.setRate(f.getVideoRate());
            }
        });

    }

    /**
     * Broken out position setting, handles updating mediaPlayer
     */
    private void setSliderBasedPosition() {
        if(!mediaPlayer.isSeekable()) {
            return;
        }
        float positionValue = panel.getVideoPositionSlider().getValue() / 1000.0f;
        // Avoid end of file freeze-up
        if(positionValue > 0.99f) {
            positionValue = 0.99f;
        }
        mediaPlayer.setPosition(positionValue);
    }

    private void updateUIState() {
        if(!mediaPlayer.isPlaying()) {
            // Resume play or play a few frames then pause to show current position in video
            mediaPlayer.play();
            if(!mousePressedPlaying) {
                try {
                    // Half a second probably gets an iframe
                    Thread.sleep(500);
                }
                catch(InterruptedException e) {
                    // Don't care if unblocked early
                }
                mediaPlayer.pause();
            }
        }

        long time = mediaPlayer.getTime();
        int position = (int)(mediaPlayer.getPosition() * 1000.0f);
        updateTimeLabel(time, panel.getCurrentTimeLabel());
        updatePosition(position);
    }


    private void setupFileChooser(){
        fileChooser = new JFileChooser();
        fileChooser.setApproveButtonText("Play");
        fileChooser.addChoosableFileFilter(SwingFileFilterFactory.newVideoFileFilter());
        fileChooser.addChoosableFileFilter(SwingFileFilterFactory.newAudioFileFilter());
        fileChooser.addChoosableFileFilter(SwingFileFilterFactory.newPlayListFileFilter());
        FileFilter defaultFilter = SwingFileFilterFactory.newMediaFileFilter();
        fileChooser.addChoosableFileFilter(defaultFilter);
        fileChooser.setFileFilter(defaultFilter);
    }

    private final class UpdateRunnable implements Runnable {

        private final MediaPlayer mediaPlayer;

        private UpdateRunnable(MediaPlayer mediaPlayer) {
            this.mediaPlayer = mediaPlayer;
        }

        @Override
        public void run() {
            final long time = mediaPlayer.getTime();
            final int position = (int)(mediaPlayer.getPosition() * 1000.0f);

            // Updates to user interface components must be executed on the Event
            // Dispatch Thread
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer.isPlaying()) {
                        updateTimeLabel(time, panel.getCurrentTimeLabel());
                        updatePosition(position);

                        long totalTime = mediaPlayer.getLength();
                        updateTimeLabel(totalTime, panel.getEndTimeLabel());

                    }
                }
            });
        }
    }

    private void updateTimeLabel(long millis, JLabel timeLabel) {
        String s = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
//        panel.getCurrentTimeLabel().setText(s);
        timeLabel.setText(s);
    }

    private void updatePosition(int value) {
        panel.getVideoPositionSlider().setValue(value);
    }


    public MediaControllerPanel getPanel() {
        return panel;
    }

}
