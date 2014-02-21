package com.github.dr3amr2.vlc.oldMVC;

import com.github.dr3amr2.vlc.utils.ImageUtils;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Created by Dizzy on 2/19/14.
 */
public class vlcModel {

    public static final ImageIcon VOLUME_ICON = new ImageIcon(ImageUtils.getImageFromResources("volume.png"));
    public static final ImageIcon MUTE_ICON = new ImageIcon(ImageUtils.getImageFromResources("mute.png"));
    public static final ImageIcon PAUSE_ICON = new ImageIcon(ImageUtils.getImageFromResources("pause.png"));
    public static final ImageIcon STOP_ICON = new ImageIcon(ImageUtils.getImageFromResources("stop.png"));
    public static final ImageIcon PLAY_ICON = new ImageIcon(ImageUtils.getImageFromResources("play.png"));

    private static int smallIconSize = 20;

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


    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    private String mediaName;

    public String getMediaFilePath() {
        return mediaFilePath;
    }

    public void setMediaFilePath(String mediaFilePath) {
        this.mediaFilePath = mediaFilePath;
    }

    private String mediaFilePath;


    public String[] getVlcOptions() {
        return vlcOptions;
    }

    public void setVlcOptions(String[] vlcOptions) {
        this.vlcOptions = vlcOptions;
    }

    String[] vlcOptions = {
            ":file-caching=60000"
    };

}
