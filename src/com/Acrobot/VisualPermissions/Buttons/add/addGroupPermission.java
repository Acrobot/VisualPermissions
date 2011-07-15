package com.Acrobot.VisualPermissions.Buttons.add;

import com.Acrobot.VisualPermissions.Lists.groupList;
import com.Acrobot.VisualPermissions.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Acrobot
 */
public class addGroupPermission implements ActionListener{
    public void actionPerformed(ActionEvent e){
        String permission = Main.showInputDialog("What is the permission?", "Add permission...");
        if(groupList.containsPermission(permission)){
            Main.showDialog("There is already a permission like that!", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        groupList.addGroupPermission(permission);
    }
}
