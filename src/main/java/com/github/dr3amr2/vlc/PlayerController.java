package com.github.dr3amr2.vlc;

/**
 * Created by dnguyen on 2/21/14.
 */
public class PlayerController {
    private PlayerModel model;

    public PlayerPanel getPanel() {
        return panel;
    }

    private PlayerPanel panel;

    public PlayerController(PlayerModel playerModel, PlayerPanel playerPanel){
        this.model = playerModel;
        this.panel = playerPanel;

        attach(model, panel);

    }

    private void attach(PlayerModel model, PlayerPanel panel) {
        initVlcArgs(model);
        panel.attachOptions(model.getVlcArgs());


    }

    private void initVlcArgs(PlayerModel model) {
        model.getVlcArgs().add(":file-caching=6000");
    }
}
