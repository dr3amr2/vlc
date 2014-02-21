/*
 * Created by JFormDesigner on Thu Feb 20 14:20:09 MST 2014
 */

package com.github.dr3amr2.vlc.oldMVC;

import javax.swing.*;

import com.github.dr3amr2.vlc.utils.ImageUtils;
import com.jgoodies.forms.layout.*;

/**
 * @author Dzuy Nguyen
 */
public class PlaybackControllerPanel extends JPanel {
    private static final ImageIcon VOLUME_ICON = new ImageIcon(ImageUtils.getImageFromResources("volume.png"));
    private static final ImageIcon MUTE_ICON = new ImageIcon(ImageUtils.getImageFromResources("mute.png"));


    public PlaybackControllerPanel() {
        initComponents();
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

    public JComboBox getVideoSpeedRateComboBox() {
        return videoSpeedRateComboBox;
    }

    public JSlider getAudioVolumeSlider() {
        return audioVolumeSlider;
    }

    public JButton getAudioButton() {
        return audioButton;
    }

    public JSlider getVideoPositionSlider() {
        return videoPositionSlider;
    }

    public JLabel getMediaNameLabel() {
        return mediaNameLabel;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Dzuy Nguyen
        openButton = new JButton();
        playButton = new JButton();
        pauseButton = new JButton();
        stopButton = new JButton();
        videoSpeedRateComboBox = new JComboBox();
        audioButton = new JButton();
        audioVolumeSlider = new JSlider();
        separator1 = new JSeparator();
        videoPositionSlider = new JSlider();
        mediaNameLabel = new JLabel();
        CellConstraints cc = new CellConstraints();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new FormLayout(
            "6*(default, $lcgap), default:grow",
            "3*(default, $lgap), default"));

        //---- openButton ----
        openButton.setText("Open");
        add(openButton, cc.xy(1, 1));
        add(playButton, cc.xy(3, 1));
        add(pauseButton, cc.xy(5, 1));
        add(stopButton, cc.xy(7, 1));
        add(videoSpeedRateComboBox, cc.xy(9, 1));
        add(audioButton, cc.xy(11, 1));
        add(audioVolumeSlider, cc.xy(13, 1));
        add(separator1, cc.xywh(1, 3, 13, 1));
        add(videoPositionSlider, cc.xywh(1, 5, 13, 1));

        //---- mediaNameLabel ----
        mediaNameLabel.setText("Media:");
        add(mediaNameLabel, cc.xywh(1, 7, 13, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Dzuy Nguyen
    private JButton openButton;
    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JComboBox videoSpeedRateComboBox;
    private JButton audioButton;
    private JSlider audioVolumeSlider;
    private JSeparator separator1;
    private JSlider videoPositionSlider;
    private JLabel mediaNameLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
