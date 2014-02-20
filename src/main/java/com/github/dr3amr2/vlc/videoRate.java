package com.github.dr3amr2.vlc;

/**
 * Created by Dizzy on 2/19/14.
 *
 * videoRate enum is defined to help the vlc player select which speed to play the video.
 */
public enum videoRate {
    HALF(0.5f),
    REGULAR(1.0f),
    DOUBLE(2.0f),
    TRIPLE(3.0f),
    QUADRUPLE(4.0f);

    private final float videoRate;

    private videoRate(float videoRate) {
        this.videoRate = videoRate;
    }

    public float getVideoRate() {
        return videoRate;
    }
}
