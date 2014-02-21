package com.github.dr3amr2.vlc.oldMVC;

/**
 * Created by Dizzy on 2/19/14.
 *
 * VideoPlaybackSpeed enum is defined to help the vlc player select which speed to play the video.
 */
public enum VideoPlaybackSpeed {
    HALF(0.5f),
    REGULAR(1.0f),
    DOUBLE(2.0f),
    TRIPLE(3.0f),
    QUADRUPLE(4.0f);

    private final float videoRate;

    private VideoPlaybackSpeed(float videoRate) {
        this.videoRate = videoRate;
    }

    public float getVideoRate() {
        return videoRate;
    }
}
