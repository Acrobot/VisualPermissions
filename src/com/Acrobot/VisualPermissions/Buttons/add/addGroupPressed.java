package com.Acrobot.VisualPermissions.Buttons.add;

import com.Acrobot.VisualPermissions.Lists.groupList;
import com.Acrobot.VisualPermissions.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Acrobot
 */
public class addGroupPressed implements ActionListener{
    public void actionPerformed(ActionEvent e){
        String groupToAdd = Main.showInputDialog("What will be the name of this group?", "Choose name...");
        if(groupList.contains(groupToAdd)){
            Main.showDialog("There is already a group named like that!", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        groupList.addGroup(groupToAdd);
    }
}
