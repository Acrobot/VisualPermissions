package com.Acrobot.VisualPermissions.Lists;

import com.Acrobot.VisualPermissions.Config;
import com.Acrobot.VisualPermissions.Main;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

/**
 * @author Acrobot
 */
public class playerListPressed implements ListSelectionListener {
    
    public void valueChanged(ListSelectionEvent e) {
        String player = (String) Main.mf.players.getSelectedValue();
        if (player == null) return;
        setPlayerGroups(player);
    }

    public static void setPlayerGroups(String player) {
        List groups = Config.players.getStringList("users." + player + ".groups", null);
        if(groups == null) return;
        playerList.setPlayerGroups(player, groups);
    }
}
