package com.Acrobot.VisualPermissions.Buttons.remove;

import com.Acrobot.VisualPermissions.Lists.playerList;
import com.Acrobot.VisualPermissions.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Acrobot
 */
public class removePlayerGroup implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String groupName = (String) Main.mf.pGroups.getSelectedValue();
        if (groupName == null) return;
        if (Main.yesNoDialog("Are you sure you want to delete the " + groupName + " group?", "Are you sure?")) {
            playerList.removePlayerGroup(groupName);
        }
    }
}
