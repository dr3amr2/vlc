package com.github.dr3amr2.vlc;

/**
 * Created by Dizzy on 2/19/14.
 */
public class vlcController {



    vlcModel model;
    vlcPanel panel;

    public vlcController(vlcModel vlcModel, vlcPanel vlcPanel) {
        this.model = vlcModel;
        this.panel = vlcPanel;

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
