package com.Acrobot.VisualPermissions.Buttons.remove;

import com.Acrobot.VisualPermissions.Lists.playerList;
import com.Acrobot.VisualPermissions.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Acrobot
 */
public class removePlayerPressed implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String playerName = (String) Main.mf.players.getSelectedValue();
        if (playerName == null) return;
        if (Main.yesNoDialog("Are you sure you want to delete " + playerName + "?", "Are you sure?")) {
            playerList.removePlayer(playerName);
        }
        playerList.clearGroups();
    }
}
