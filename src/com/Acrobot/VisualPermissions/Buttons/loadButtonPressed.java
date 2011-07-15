package com.Acrobot.VisualPermissions.Buttons;

import com.Acrobot.VisualPermissions.Config;
import com.Acrobot.VisualPermissions.Lists.groupList;
import com.Acrobot.VisualPermissions.Lists.playerList;
import com.Acrobot.VisualPermissions.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Acrobot
 */
public class loadButtonPressed implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        clearEverything();
        setConfigurationFile();
    }

    void setConfigurationFile(){
        File file = Main.openChooserDialog();
        if(file == null) return;
        Config.setUpConfiguration(file);
    }
    void clearEverything(){
        groupList.clearGroupList();
        playerList.clearPlayerList();
    }
}
