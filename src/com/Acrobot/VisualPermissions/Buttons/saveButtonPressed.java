package com.Acrobot.VisualPermissions.Buttons;

import com.Acrobot.VisualPermissions.Config;
import com.Acrobot.VisualPermissions.Lists.groupList;
import com.Acrobot.VisualPermissions.Lists.playerList;
import com.Acrobot.VisualPermissions.Main;
import org.bukkit.util.config.Configuration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

/**
 * @author Acrobot
 */
public class saveButtonPressed implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if(!Config.folder.exists()) {
            Config.folder = new File(".");
            Config.groups = new Configuration(new File(Config.folder, "groups.yml"));
        }
        saveGroups();
        savePlayers();

        Main.showDialog("Successfully saved Permissions configuration files!", "Success!");
    }

    private void savePlayers() {
        Configuration playerConfig = new Configuration(new File(Config.folder, "users.yml"));

        for (String player : playerList.pGroups.keySet()) {
            playerConfig.setProperty("users." + player + ".groups", playerList.pGroups.get(player));
        }

        playerConfig.save();
    }

    private void saveGroups() {
        Configuration groupConfig = Config.groups;
        groupConfig.load();

        try{
            for (String group : groupConfig.getKeys("groups")) {
                if (!groupList.gPermissions.containsKey(group)) {
                    groupConfig.removeProperty("groups." + group);
                    continue;
                }
                for (String permission : groupConfig.getStringList("groups." + group + ".permissions", null)) {
                    if (!groupList.gPermissions.get(group).contains(permission))
                        groupConfig.removeProperty("groups." + group + ".permissions." + permission);
                }
            }
        } catch (Exception ignored){}
        for (String group : groupList.gPermissions.keySet()) {
            if (groupConfig.getProperty("groups." + group) == null) {
                String groupNode = "groups." + group;
                groupConfig.setProperty(groupNode + ".default", false);
                groupConfig.setProperty(groupNode + ".info.prefix", "");
                groupConfig.setProperty(groupNode + ".info.suffix", "");
                groupConfig.setProperty(groupNode + ".info.build", true);
                groupConfig.setProperty(groupNode + ".inheritance", new LinkedList());
            }
            groupConfig.setProperty("groups." + group + ".permissions", groupList.gPermissions.get(group));
        }
        groupConfig.save();
        groupConfig.load();
        boolean anyIsDefault = false;
        for (String group : groupConfig.getKeys("groups")){
            anyIsDefault = anyIsDefault || groupConfig.getBoolean("groups." + group + ".default", false);
        }
        if(!anyIsDefault){
            groupConfig.setProperty("groups." + (groupConfig.getProperty("groups.Default") != null ? "Default" : groupConfig.getKeys("groups").get(0)) + ".default", true);
        }
        groupConfig.save();
    }
}
