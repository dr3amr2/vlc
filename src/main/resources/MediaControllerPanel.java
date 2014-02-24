import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Fri Feb 21 17:02:17 MST 2014
 */



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

    public JLabel getStartTimeLabel() {
        return startTimeLabel;
    }

    public JLabel getEndTimeLabel() {
        return endTimeLabel;
    }

    public JSlider getVideoPositionSlider() {
        return videoPositionSlider;
    }

    public JLabel getCurrentTimeLabel() {
        return currentTimeLabel;
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

    public JButton getConnectButton() {
        return connectButton;
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
        connectButton = new JButton();
        audioButton = new JButton();
        audioVolumeSlider = new JSlider();
        selectedClipPropertiesPanel = new JPanel();
        clipLabel = new JLabel();
        clipLabelTextField = new JTextField();
        timePanel = new JPanel();
        startTimeLabel = new JLabel();
        endTimeLabel = new JLabel();
        videoPositionSlider = new JSlider();
        currentTimeLabel = new JLabel();
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

            //---- connectButton ----
            connectButton.setText("Connect");
            mediaControllerPanel.add(connectButton, cc.xy(1, 3));
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

                //---- startTimeLabel ----
                startTimeLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                startTimeLabel.setText("Testing");
                timePanel.add(startTimeLabel, cc.xy(1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

                //---- endTimeLabel ----
                endTimeLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                timePanel.add(endTimeLabel, cc.xy(5, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

                //---- videoPositionSlider ----
                videoPositionSlider.setPaintTicks(true);
                videoPositionSlider.setMinorTickSpacing(5);
                timePanel.add(videoPositionSlider, cc.xywh(1, 3, 5, 1));

                //---- currentTimeLabel ----
                currentTimeLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                timePanel.add(currentTimeLabel, cc.xy(3, 5, CellConstraints.CENTER, CellConstraints.DEFAULT));

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
    private JButton connectButton;
    private JButton audioButton;
    private JSlider audioVolumeSlider;
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
