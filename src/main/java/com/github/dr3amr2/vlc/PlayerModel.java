package com.github.dr3amr2.vlc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dnguyen on 2/21/14.
 */
public class PlayerModel {


    private String clipLabel;

    private String mediaFilePath;

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

}
