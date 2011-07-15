package com.Acrobot.VisualPermissions.Buttons.remove;

import com.Acrobot.VisualPermissions.Lists.groupList;
import com.Acrobot.VisualPermissions.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Acrobot
 */
public class removePermissionPressed implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String permission = (String) Main.mf.gPermissions.getSelectedValue();
        if (permission == null) return;
        if (Main.yesNoDialog("Are you sure you want to remove " + permission + "?", "Are you sure?")) {
            groupList.removeGroupPermission(permission);
        }
    }
}
