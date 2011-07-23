package com.Acrobot.VisualPermissions.Plugins;

import com.Acrobot.VisualPermissions.Main;
import org.bukkit.util.config.Configuration;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Acrobot
 */
public class Permissions3 implements PermissionPlugin {
    protected File folder;
    protected Configuration users;
    protected Configuration groups;

    private boolean active = false;

    protected HashMap<String, List> userMap = new HashMap<String, List>();
    protected HashMap<String, List> groupMap = new HashMap<String, List>();


    public void setFolder(File folder) {
        this.folder = folder;
    }

    public void load() {
        File groupFile = new File(folder, "groups.yml");
        File userFile = new File(folder, "users.yml");

        if (!groupFile.exists() || !userFile.exists()) {
            Main.showDialog("This is not a valid Permissions 3.x folder!", "Error!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        groups = new Configuration(groupFile);
        users = new Configuration(userFile);

        if (!loadConfigurations()) return;

        Main.showDialog("Successfully loaded Permissions 3.x configuration files!", "Success!", JOptionPane.INFORMATION_MESSAGE);

        active = true;
        showConfigOnGUI();
    }

    protected void showConfigOnGUI() {
        if (groups != null && groups.getNode("groups") != null) {
            for (String group : groups.getKeys("groups")) {
                List permissions = groups.getStringList("groups." + group + ".permissions", null);
                if (permissions == null) continue;
                groupMap.put(group, permissions);
            }
        }

        if (users != null && users.getNode("users") != null) {
            for (String user : users.getKeys("users")) {
                List permissions = users.getStringList("users." + user + ".groups", null);
                userMap.put(user, permissions != null ? permissions : new LinkedList());
            }
        }

        showOnGUI();
    }

    protected void showOnGUI() {
        DefaultListModel groupModel = new DefaultListModel();
        DefaultListModel userModel = new DefaultListModel();

        for (String user : userMap.keySet()) {
            userModel.addElement(user);
        }

        for (String group : groupMap.keySet()) {
            groupModel.addElement(group);
        }

        Main.mf.players.setModel(userModel);
        Main.mf.groups.setModel(groupModel);
    }

    protected boolean loadConfigurations() {
        try {
            groups.load();
            users.load();
            return true;
        } catch (Exception e) {
            Main.showDialog("Those files are corrupted!", "Error!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    protected void saveConfiguration() {
        groups.save();
        users.save();

        loadConfigurations();
    }

    public void newConfiguration() {
        String fileName = Main.showInputDialog("What is your world name?", "World name");
        Main.showDialog("Now select the folder, where we'll store the config!", "Select the folder!");

        if (fileName == null) return;

        File file = Main.openChooserDialog();
        folder = new File((file != null ? file.getAbsolutePath() : "."), fileName);
        active = true;
        groupMap = new HashMap<String, List>();
        userMap = new HashMap<String, List>();
        showOnGUI();
    }

    public List<String> getGroups() {
        return new LinkedList<String>(groupMap.keySet());
    }

    public List<String> getGroupPermissions(String group) {
        return groupMap.get(group);
    }

    public List<String> getPlayers() {
        return new LinkedList<String>(userMap.keySet());
    }

    public List<String> getPlayerGroups(String player) {
        return userMap.get(player);
    }

    public void addPlayer(String player) {
        userMap.put(player, new LinkedList());
        showOnGUI();
    }

    public void addPlayerGroup(String player, String group) {
        userMap.get(player).add(group);
    }

    public void addGroup(String group) {
        groupMap.put(group, new LinkedList());
        showOnGUI();
    }

    public void addGroupPermission(String group, String permission) {
        groupMap.get(group).add(permission);
    }

    public void removePlayer(String player) {
        userMap.remove(player);
    }

    public void removePlayerGroup(String player, String group) {
        userMap.get(player).remove(group);
    }

    public void removeGroup(String group) {
        groupMap.remove(group);
    }

    public void removeGroupPermission(String group, String permission) {
        groupMap.get(group).remove(permission);
    }

    public void save() {
        if (!active) return;

        if (groups == null || users == null) {
            groups = new Configuration(new File(folder, "groups.yml"));
            users = new Configuration(new File(folder, "users.yml"));

            if (!loadConfigurations()) {
                Main.showDialog("Error! Couldn't create new configuration files!", "Error!", JOptionPane.ERROR);
                return;
            }
        }

        List<String> groupList = groups.getKeys("groups");
        List<String> userList = users.getKeys("users");

        if (groupList != null && userList != null) {
            for (String group : groupList) if (!groupMap.containsKey(group)) groups.removeProperty("groups." + group);
            for (String user : userList) if (!userMap.containsKey(user)) users.removeProperty("users." + user);
        }

        for (String group : groupMap.keySet()) {
            String groupNode = "groups." + group;
            if (groups.getProperty(groupNode) == null) {
                groups.setProperty(groupNode + ".default", false);
                groups.setProperty(groupNode + ".info.prefix", "");
                groups.setProperty(groupNode + ".info.suffix", "");
                groups.setProperty(groupNode + ".info.build", true);
                groups.setProperty(groupNode + ".inheritance", new LinkedList());
            }
            groups.setProperty(groupNode + ".permissions", groupMap.get(group));
        }

        for (String player : userMap.keySet()) {
            String userNode = "users." + player;
            if (users.getProperty(userNode) == null) {
                users.setProperty(userNode + ".permissions", new LinkedList());
            }
            users.setProperty(userNode + ".groups", userMap.get(player));
        }

        saveConfiguration();

        boolean hasDefaultGroup = false;
        for (String group : groups.getKeys("groups")) {
            hasDefaultGroup = hasDefaultGroup || groups.getBoolean("groups." + group + ".default", false);
        }

        if (!hasDefaultGroup) {
            String group = Main.showListDialog("Choose default group", "Default group", groups.getKeys("groups").toArray());
            if (group != null) groups.setProperty("groups." + group + ".default", true);
        }

        saveConfiguration();

        Main.showDialog("Successfully saved the configuration files!", "Success!", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getName() {
        return "Permissions 3";
    }
}
