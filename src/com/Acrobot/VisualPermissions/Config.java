package com.Acrobot.VisualPermissions;

import com.Acrobot.VisualPermissions.Lists.groupList;
import com.Acrobot.VisualPermissions.Lists.groupListPressed;
import com.Acrobot.VisualPermissions.Lists.playerList;
import com.Acrobot.VisualPermissions.Lists.playerListPressed;
import org.bukkit.util.config.Configuration;

import javax.swing.*;
import java.io.File;

/**
 * @author Acrobot
 */
public class Config {
    public static Configuration groups = new Configuration(new File("groups.yml"));
    public static Configuration players = new Configuration(new File("users.yml"));
    public static File folder = new File(".");

    public static boolean setUpConfiguration(File Folder) {
        folder = Folder;

        File gFile = new File(Folder, "groups.yml");
        File pFile = new File(Folder, "users.yml");

        if (!gFile.exists() || !pFile.exists()) {
            Main.showDialog("This is not a valid Permissions 3.x folder!", "Error!", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        groups = new Configuration(gFile);
        players = new Configuration(pFile);

        if (!loadConfigurations()) return false;

        Main.showDialog("Successfully loaded Permissions 3.x configuration files!", "Success!", JOptionPane.INFORMATION_MESSAGE);

        showOnLists();
        return true;
    }

    private static void showOnLists() {
        groupList.setGroups(groups.getKeys("groups"));
        playerList.setPlayers(players.getKeys("users"));

        showInHashMaps();
    }

    private static void showInHashMaps() {
        if (groupList.groups != null) {
            for (Object group : groupList.groups) {
                groupListPressed.setGroupPermissions((String) group);
            }
        }
        if (playerList.players != null) {
            for (Object player : playerList.players) {
                playerListPressed.setPlayerGroups((String) player);
            }
        }
    }

    private static boolean loadConfigurations() {
        try {
            groups.load();
            players.load();
            return true;
        } catch (Exception e) {
            Main.showDialog("Those files are corrupted!", "Error!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
}
