/*
 * Created by JFormDesigner on Fri Feb 21 16:17:12 MST 2014
 */

package com.github.dr3amr2.vlc;

import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.layout.*;

/**
 * @author Dzuy Nguyen
 */
public class MediaControllerPanel extends JPanel {
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

    public JComboBox getVideoSpeedRateComboBox() {
        return videoSpeedRateComboBox;
    }

    public JButton getAudioButton() {
        return audioButton;
    }

    public JSlider getAudioVolumeSlider() {
        return audioVolumeSlider;
    }

    public JTextField getClipLabelTextField() {
        return clipLabelTextField;
    }

    public JTextField getStartTimeTextField() {
        return startTimeTextField;
    }

    public JTextField getEndTimeTextField() {
        return endTimeTextField;
    }

    public JSlider getVideoPositionSlider() {
        return videoPositionSlider;
    }

    public JTextField getCurrentTimeTextField() {
        return currentTimeTextField;
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Dzuy Nguyen
        mediaControllerPanel = new JPanel();
        openButton = new JButton();
        playButton = new JButton();
        pauseButton = new JButton();
        stopButton = new JButton();
        videoSpeedRateComboBox = new JComboBox();
        audioButton = new JButton();
        audioVolumeSlider = new JSlider();
        selectedClipPropertiesPanel = new JPanel();
        clipLabel = new JLabel();
        clipLabelTextField = new JTextField();
        timePanel = new JPanel();
        startTimeTextField = new JTextField();
        endTimeTextField = new JTextField();
        videoPositionSlider = new JSlider();
        currentTimeTextField = new JTextField();
        repeatCheckBox = new JCheckBox();
        revertButton = new JButton();
        applyButton = new JButton();
        CellConstraints cc = new CellConstraints();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new FormLayout(
            "3*(default:grow, $lcgap), default:grow",
            "2*(default, $lgap), default"));

        //======== mediaControllerPanel ========
        {
            mediaControllerPanel.setBorder(new TitledBorder("Media Controller"));
            mediaControllerPanel.setLayout(new FormLayout(
                "4*(default:grow, $lcgap), default:grow",
                "2*(default, $lgap), default"));

            //---- openButton ----
            openButton.setText("Open");
            mediaControllerPanel.add(openButton, cc.xy(1, 1));
            mediaControllerPanel.add(playButton, cc.xy(3, 1));
            mediaControllerPanel.add(pauseButton, cc.xy(5, 1));
            mediaControllerPanel.add(stopButton, cc.xy(7, 1));
            mediaControllerPanel.add(videoSpeedRateComboBox, cc.xy(9, 1));
            mediaControllerPanel.add(audioButton, cc.xy(3, 3));
            mediaControllerPanel.add(audioVolumeSlider, cc.xywh(5, 3, 5, 1));
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
                timePanel.add(startTimeTextField, cc.xy(1, 1));
                timePanel.add(endTimeTextField, cc.xy(5, 1));

                //---- videoPositionSlider ----
                videoPositionSlider.setPaintTicks(true);
                videoPositionSlider.setMinorTickSpacing(5);
                timePanel.add(videoPositionSlider, cc.xywh(1, 3, 5, 1));
                timePanel.add(currentTimeTextField, cc.xy(3, 5));

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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Dzuy Nguyen
    private JPanel mediaControllerPanel;
    private JButton openButton;
    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JComboBox videoSpeedRateComboBox;
    private JButton audioButton;
    private JSlider audioVolumeSlider;
    private JPanel selectedClipPropertiesPanel;
    private JLabel clipLabel;
    private JTextField clipLabelTextField;
    private JPanel timePanel;
    private JTextField startTimeTextField;
    private JTextField endTimeTextField;
    private JSlider videoPositionSlider;
    private JTextField currentTimeTextField;
    private JCheckBox repeatCheckBox;
    private JButton revertButton;
    private JButton applyButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
