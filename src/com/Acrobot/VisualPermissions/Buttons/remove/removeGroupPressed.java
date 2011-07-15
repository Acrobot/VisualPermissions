package com.Acrobot.VisualPermissions.Buttons.remove;

import com.Acrobot.VisualPermissions.Lists.groupList;
import com.Acrobot.VisualPermissions.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Acrobot
 */
public class removeGroupPressed implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String groupName = (String) Main.mf.groups.getSelectedValue();
        if (groupName == null) return;
        if (Main.yesNoDialog("Are you sure you want to delete the " + groupName + " group?", "Are you sure?")) {
            groupList.removeGroup(groupName);
        }
        groupList.clearPermissionsList();
    }
}
