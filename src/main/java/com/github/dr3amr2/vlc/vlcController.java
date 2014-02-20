package com.github.dr3amr2.vlc;

import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;

/**
 * Created by Dizzy on 2/19/14.
 */
public class vlcController {

    vlcModel model;
    vlcPanel panel;

    public vlcController(vlcModel vlcModel, vlcPanel vlcPanel) {
        this.model = vlcModel;
        this.panel = vlcPanel;

        attach(model, panel);
    }

    private void attach(vlcModel model, final vlcPanel panel) {

        initMediaPlayerListeners(panel);
        initPlaybackControllers(panel);

    }

    private void initPlaybackControllers(final vlcPanel panel) {

    }

    private void initMediaPlayerListeners(final vlcPanel panel) {
        panel.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventListener() {
            @Override
            public void mediaChanged(MediaPlayer mediaPlayer,
                                     libvlc_media_t media, String mrl) {
            }

            @Override
            public void opening(MediaPlayer mediaPlayer) {
            }

            @Override
            public void buffering(MediaPlayer mediaPlayer, float newCache) {
            }

            @Override
            public void playing(MediaPlayer mediaPlayer) {
            }

            @Override
            public void paused(MediaPlayer mediaPlayer) {
            }

            @Override
            public void stopped(MediaPlayer mediaPlayer) {
            }

            @Override
            public void forward(MediaPlayer mediaPlayer) {
            }

            @Override
            public void backward(MediaPlayer mediaPlayer) {
            }

            @Override
            public void finished(MediaPlayer mediaPlayer) {
            }

            @Override
            public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
            }

            @Override
            public void positionChanged(MediaPlayer mediaPlayer,
                                        float newPosition) {
                int iPos = (int)(newPosition * 100.0);
                panel.getPositionSlider().setValue(iPos);
            }

            @Override
            public void seekableChanged(MediaPlayer mediaPlayer,
                                        int newSeekable) {
            }

            @Override
            public void pausableChanged(MediaPlayer mediaPlayer,
                                        int newPausable) {
            }

            @Override
            public void titleChanged(MediaPlayer mediaPlayer, int newTitle) {
            }

            @Override
            public void snapshotTaken(MediaPlayer mediaPlayer,
                                      String filename) {
            }

            @Override
            public void lengthChanged(MediaPlayer mediaPlayer,
                                      long newLength) {
            }

            @Override
            public void videoOutput(MediaPlayer player, int newCount) {
                // Set the canvas to match the size of the parent panel
                panel.getCanvas().setSize(panel.getRootParent().getPreferredSize());
                panel.getRootParent().pack();
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
            }

            @Override
            public void mediaMetaChanged(MediaPlayer mediaPlayer,
                                         int metaType) {
            }

            @Override
            public void mediaSubItemAdded(MediaPlayer mediaPlayer,
                                          libvlc_media_t subItem) {
            }

            @Override
            public void mediaDurationChanged(MediaPlayer mediaPlayer,
                                             long newDuration) {
            }

            @Override
            public void mediaParsedChanged(MediaPlayer mediaPlayer,
                                           int newStatus) {
            }

            @Override
            public void mediaFreed(MediaPlayer mediaPlayer) {
            }

            @Override
            public void mediaStateChanged(MediaPlayer mediaPlayer,
                                          int newState) {
            }

            @Override
            public void newMedia(MediaPlayer mediaPlayer) {
            }

            @Override
            public void subItemPlayed(MediaPlayer mediaPlayer,
                                      int subItemIndex) {
            }

            @Override
            public void subItemFinished(MediaPlayer mediaPlayer,
                                        int subItemIndex) {
            }

            @Override
            public void endOfSubItems(MediaPlayer mediaPlayer) {
            }

        });
    }


    public vlcModel getModel() {
        return model;
    }

    public void setModel(vlcModel model) {
        this.model = model;
    }

    public vlcPanel getPanel() {
        return panel;
    }

    public void setPanel(vlcPanel panel) {
        this.panel = panel;
    }
}
