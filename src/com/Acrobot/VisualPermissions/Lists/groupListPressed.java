package com.Acrobot.VisualPermissions.Lists;

import com.Acrobot.VisualPermissions.Config;
import com.Acrobot.VisualPermissions.Main;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

/**
 * @author Acrobot
 */
public class groupListPressed implements ListSelectionListener {
    
    public void valueChanged(ListSelectionEvent e) {
        String group = (String) Main.mf.groups.getSelectedValue();
        if (group == null) return;
        setGroupPermissions(group);
    }

    public static void setGroupPermissions(String group) {
        List permissions = Config.groups.getStringList("groups." + group + ".permissions", null);
        if(permissions == null) return;
        groupList.setGroupPermissions(group, permissions);
    }
}
