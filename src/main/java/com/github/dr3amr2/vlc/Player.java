package com.github.dr3amr2.vlc;

/**
 * Created by Dizzy on 2/19/14.
 */
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;

public class Player extends JPanel {

    static JPanel controls = new JPanel(new MigLayout());
    static JPanel panel = new JPanel();
    final static MediaPlayerFactory factory = new MediaPlayerFactory();
    final static EmbeddedMediaPlayer mediaPlayer = factory.newEmbeddedMediaPlayer();

    Player(Container c) {
        panel.setLayout(new MigLayout("debug"));
        final JPanel player = new JPanel();
        panel.setSize(300, 300);

        makeControls();

        final Canvas canv = new Canvas();
        canv.setSize(200, 200);

        player.add(canv);

        panel.add(player);
        panel.add(controls, "south");
        c.add(panel);

        CanvasVideoSurface surface = factory.newVideoSurface(canv);
        mediaPlayer.setVideoSurface(surface);

        //		while(mediaPlayer.isPlayable() || mediaPlayer.isPlaying()) {
        //			try {
        //				Thread.currentThread().join();
        //			} catch(InterruptedException ie) {}
        //		}

        mediaPlayer.addMediaPlayerEventListener(new MediaPlayerEventListener() {

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
                canv.setSize(mediaPlayer.getVideoDimension());
                getRootParent().pack();
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
        if(getRootParent() != null) {
            getRootParent().addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                    mediaPlayer.release();
                    factory.release();
                    System.exit(0);
                }

                @Override
                public void windowClosed(WindowEvent e) {
                }

                @Override
                public void windowIconified(WindowEvent e) {
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                }

                @Override
                public void windowActivated(WindowEvent e) {
                }

                @Override
                public void windowDeactivated(WindowEvent e) {
                }

            });
        } else {
            System.out.println("RootParent = null\nPlayer not displayable");
        }
    }

    public void playMedia(String mrl) {
        mediaPlayer.playMedia(mrl);
    }

    private void makeControls() {
        JButton play = new JButton("play");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.play();
            }
        });

        JButton pause = new JButton("pause");
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.pause();
            }
        });

        JButton stop = new JButton("stop");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.stop();
            }
        });

        final JTextField mrl = new JTextField(10);

        JButton submit = new JButton("Load this video");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = mrl.getText();
                System.out.println("PATH: \"" +path +"\"");
                mediaPlayer.playMedia(path);
                System.out.println("Playing: " +path);
                // FIXME: check if path == null
            }
        });

        controls.add(play);
        controls.add(pause);
        controls.add(stop, "wrap");
        controls.add(mrl);
        controls.add(submit);
    }

    private JFrame getRootParent() {
        Component c = panel;
        while(c.getParent() != null) {
            c = c.getParent();
        }
        if(c instanceof JFrame) {
            return (JFrame)c;
        }

        return null;
    }
}