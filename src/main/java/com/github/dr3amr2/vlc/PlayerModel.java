package com.github.dr3amr2.vlc;

import com.github.dr3amr2.vlc.utils.ImageUtils;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dnguyen on 2/21/14.
 */
public class PlayerModel {

    private String clipLabel;

    private String mediaFilePath;

    private static String iconPath = "/icons/mediaPlayer/";

    private List<String> vlcArgs = new ArrayList<String>();


    public String getClipLabel() {
        return clipLabel;
    }

    public void setClipLabel(String clipLabel) {
        this.clipLabel = clipLabel;
    }

    public String getMediaFilePath() {
        return mediaFilePath;
    }

    public void setMediaFilePath(String mediaFilePath) {
        this.mediaFilePath = mediaFilePath;
    }

    public List<String> getVlcArgs() {
        return vlcArgs;
    }

    public String[] getOptions() {
        return options;
    }

    String[] options = {
            ":video-title-show",
            ":video-title-timeout=5000"
    };

    public static final ImageIcon VOLUME_ICON = new ImageIcon(ImageUtils.getImageFromResources(iconPath + "volume.png"));
    public static final ImageIcon MUTE_ICON = new ImageIcon(ImageUtils.getImageFromResources(iconPath + "mute.png"));
    public static final ImageIcon PAUSE_ICON = new ImageIcon(ImageUtils.getImageFromResources(iconPath + "pause.png"));
    public static final ImageIcon STOP_ICON = new ImageIcon(ImageUtils.getImageFromResources(iconPath + "stop.png"));
    public static final ImageIcon PLAY_ICON = new ImageIcon(ImageUtils.getImageFromResources(iconPath + "play.png"));

    private static int smallIconSize = 32;

    public static final ImageIcon SMALL_VOLUME_ICON =
            new ImageIcon(ImageUtils.getScaledInstance((BufferedImage) VOLUME_ICON.getImage(), smallIconSize));
    public static final ImageIcon SMALL_MUTE_ICON =
            new ImageIcon(ImageUtils.getScaledInstance((BufferedImage) MUTE_ICON.getImage(), smallIconSize));
    public static final ImageIcon SMALL_PAUSE_ICON =
            new ImageIcon(ImageUtils.getScaledInstance((BufferedImage) PAUSE_ICON.getImage(), smallIconSize));
    public static final ImageIcon SMALL_PLAY_ICON =
            new ImageIcon(ImageUtils.getScaledInstance((BufferedImage) PLAY_ICON.getImage(), smallIconSize));
    public static final ImageIcon SMALL_STOP_ICON =
            new ImageIcon(ImageUtils.getScaledInstance((BufferedImage) STOP_ICON.getImage(), smallIconSize));


}
