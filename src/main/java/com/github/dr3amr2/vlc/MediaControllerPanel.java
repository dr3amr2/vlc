/*
 * Created by JFormDesigner on Fri Feb 21 16:17:12 MST 2014
 */

package com.github.dr3amr2.vlc;

import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.layout.*;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 * @author Dzuy Nguyen
 */
public class MediaControllerPanel extends JPanel {

    public boolean isMute() {
        return isMute;
    }

    public void setMute(boolean isMute) {
        this.isMute = isMute;
    }

    private boolean isMute = false;

    public MediaControllerPanel() {
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

    public JComboBox getPlaybackSpeedComboBox() {
        return playbackSpeedComboBox;
    }

    public JButton getMuteButton() {
        return muteButton;
    }

    public JSlider getVolumeSlider() {
        return volumeSlider;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public JTextField getClipLabelTextField() {
        return clipLabelTextField;
    }

    public JSlider getVideoPositionSlider() {
        return videoPositionSlider;
    }

    public JCheckBox getRepeatCheckBox() {
        return repeatCheckBox;
    }

    public JButton getRevertButton() {
        return revertButton;
    }

    public JButton getApplyButton() {
        return applyButton;
    }

    public JLabel getStartTimeLabel() {
        return startTimeLabel;
    }

    public JLabel getCurrentTimeLabel() {
        return currentTimeLabel;
    }

    public JLabel getEndTimeLabel() {
        return endTimeLabel;
    }

    private void initComponents() {

        selectedClipPropertiesPanel = new JPanel();
        clipLabel = new JLabel();
        clipLabelTextField = new JTextField();
        timePanel = new JPanel();
        startTimeLabel = new JLabel("hh:mm:ss");
        endTimeLabel = new JLabel("hh:mm:ss");
        currentTimeLabel = new JLabel("hh:mm:ss");
        repeatCheckBox = new JCheckBox();
        revertButton = new JButton();
        applyButton = new JButton();
        CellConstraints cc = new CellConstraints();

        //======== this ========

        setLayout(new FormLayout(
            "3*(default:grow, $lcgap), default:grow",
            "2*(default, $lgap), default"));

        //======== mediaControllerPanel ========
        {
            mediaControllerPanel = new JPanel();
            mediaControllerPanel.setBorder(new TitledBorder("Media Controller"));
            mediaControllerPanel.setLayout(new FormLayout(
                "4*(default:grow, $lcgap), default:grow",
                "2*(default, $lgap), default"));

            //---- openButton ----
            openButton = new JButton();
            openButton.setText("Open");
            openButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/control_eject_blue.png")));
            openButton.setToolTipText("Load media");

            //---- playButton ----
            playButton = new JButton();
            playButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/control_play_blue.png")));
            playButton.setToolTipText("Play");

            //---- pauseButton ----
            pauseButton = new JButton();
            pauseButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/control_pause_blue.png")));
            pauseButton.setToolTipText("Play/pause");

            //---- stopButton ----
            stopButton = new JButton();
            stopButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/control_stop_blue.png")));
            stopButton.setToolTipText("Stop");

            //---- playbackSpeedButton ----
            playbackSpeedComboBox = new JComboBox(VideoPlaybackSpeed.values());
            playbackSpeedComboBox.setSelectedItem(VideoPlaybackSpeed.REGULAR);

            mediaControllerPanel.add(openButton, cc.xy(1, 1));
            mediaControllerPanel.add(playButton, cc.xy(3, 1));
            mediaControllerPanel.add(pauseButton, cc.xy(5, 1));
            mediaControllerPanel.add(stopButton, cc.xy(7, 1));
            mediaControllerPanel.add(playbackSpeedComboBox, cc.xy(9, 1));

            //---- connectButton ----
            connectButton = new JButton();
            connectButton.setText("Connect");
            mediaControllerPanel.add(connectButton, cc.xy(1, 3));

            //---- muteButton ----
            muteButton = new JButton();
            if(isMute){
                muteButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/sound_mute.png")));
            } else {
                muteButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("com/github/dr3amr2/vlc/icons/volume.png")));
            }
            muteButton.setToolTipText("Mute On/Off");

            //---- volumeSlider ----
            volumeSlider = new JSlider();

            mediaControllerPanel.add(connectButton, cc.xy(1, 3));
            mediaControllerPanel.add(muteButton, cc.xy(3, 3));
            mediaControllerPanel.add(volumeSlider, cc.xywh(5, 3, 5, 1));
        }
        add(mediaControllerPanel, cc.xywh(1, 1, 7, 1));

        //======== selectedClipPropertiesPanel ========
        {
            selectedClipPropertiesPanel.setBorder(new TitledBorder("Selected Clip Properties"));
            selectedClipPropertiesPanel.setLayout(new FormLayout(
                "right:default, $lcgap, default:grow",
                "2*(default, $lgap), default"));

            //---- clipLabel ----
            clipLabel.setText("Clip Label:");
            selectedClipPropertiesPanel.add(clipLabel, cc.xy(1, 1));
            selectedClipPropertiesPanel.add(clipLabelTextField, cc.xy(3, 1));

            //======== timePanel ========
            {
                timePanel.setBorder(new TitledBorder("Time"));
                timePanel.setLayout(new FormLayout(
                    "2*(default:grow, $lcgap), default:grow",
                    "3*(default, $lgap), default"));
                timePanel.add(startTimeLabel, cc.xy(1, 1));
                timePanel.add(endTimeLabel, cc.xy(5, 1));

                //---- videoPositionSlider ----
                videoPositionSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 5);
                videoPositionSlider.setPaintTicks(true);
                videoPositionSlider.setMinorTickSpacing(5);
                timePanel.add(videoPositionSlider, cc.xywh(1, 3, 5, 1));
                timePanel.add(currentTimeLabel, cc.xy(3, 5));

                //---- repeatCheckBox ----
                repeatCheckBox.setText("Repeat");
                timePanel.add(repeatCheckBox, cc.xy(3, 7, CellConstraints.CENTER, CellConstraints.DEFAULT));
            }
            selectedClipPropertiesPanel.add(timePanel, cc.xywh(1, 3, 3, 1));
        }
        add(selectedClipPropertiesPanel, cc.xywh(1, 3, 7, 1));

        //---- revertButton ----
        revertButton.setText("Revert");
        add(revertButton, cc.xy(3, 5));

        //---- applyButton ----
        applyButton.setText("Apply");
        add(applyButton, cc.xy(5, 5));
    }

    private JPanel mediaControllerPanel;
    private JButton openButton;
    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JComboBox playbackSpeedComboBox;
    private JButton connectButton;
    private JButton muteButton;
    private JSlider volumeSlider;
    private JPanel selectedClipPropertiesPanel;
    private JLabel clipLabel;
    private JTextField clipLabelTextField;
    private JPanel timePanel;
    private JLabel startTimeLabel;
    private JLabel endTimeLabel;
    private JSlider videoPositionSlider;
    private JLabel currentTimeLabel;
    private JCheckBox repeatCheckBox;
    private JButton revertButton;
    private JButton applyButton;
}
