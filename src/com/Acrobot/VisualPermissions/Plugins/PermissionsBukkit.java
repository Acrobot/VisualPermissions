package com.Acrobot.VisualPermissions.Plugins;

import com.Acrobot.VisualPermissions.Main;
import org.bukkit.util.config.Configuration;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Acrobot
 */
public class PermissionsBukkit implements PermissionPlugin {
    private File folder;
    private Configuration config;

    private boolean active = false;

    private HashMap<String, List> userMap = new HashMap<String, List>();
    private HashMap<String, List> groupMap = new HashMap<String, List>();

    public void setFolder(File folder) {
        this.folder = folder;
    }

    public void load() {
        File configurationFile = new File(folder, "config.yml");

        if (!configurationFile.exists()) {
            Main.showDialog("This is not a valid PermissionsBukkit folder!", "Error!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        config = new Configuration(configurationFile);

        if (!loadConfigurations()) return;

        Main.showDialog("Successfully loaded PermissionsBukkit configuration file!", "Success!", JOptionPane.INFORMATION_MESSAGE);

        active = true;
        showConfigOnGUI();
    }

    private void showConfigOnGUI() {
        if (config == null) {
            showOnGUI();
            return;
        }

        if (config.getNode("groups") != null) {
            for (String group : config.getKeys("groups")) {
                List permissions = config.getKeys("groups." + group + ".permissions");
                if (permissions == null) continue;
                groupMap.put(group, permissions);
            }
        }

        if (config.getNode("users") != null) {
            for (String user : config.getKeys("users")) {
                List permissions = config.getStringList("users." + user + ".groups", null);
                userMap.put(user, permissions != null ? permissions : new LinkedList());
            }
        }

        showOnGUI();
    }

    private void showOnGUI() {
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

    private boolean loadConfigurations() {
        try {
            config.load();
            return true;
        } catch (Exception e) {
            Main.showDialog("Those files are corrupted!", "Error!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private void saveConfiguration() {
        config.save();

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

        if (config == null) {
            config = new Configuration(new File(folder, "config.yml"));

            if (!loadConfigurations()) {
                Main.showDialog("Error! Couldn't create a new configuration file!", "Error!", JOptionPane.ERROR);
                return;
            }
        }

        List<String> groupList = config.getKeys("groups");
        List<String> userList = config.getKeys("users");

        if (groupList != null && userList != null) {
            for (String group : groupList) if (!groupMap.containsKey(group)) config.removeProperty("groups." + group);
            for (String user : userList) if (!userMap.containsKey(user)) config.removeProperty("users." + user);
        }

        for (String group : groupMap.keySet()) {
            String groupNode = "groups." + group + ".permissions";
            if (config.getProperty(groupNode) != null) config.removeProperty(groupNode);
            Map<String, Object> list = new HashMap<String, Object>();
            for (Object permission : groupMap.get(group)) list.put((String) permission, true);
            config.setProperty("groups." + group + ".permissions", list);
        }

        for (String user : userMap.keySet()) {
            String userNode = "users." + user + ".groups";
            if (config.getProperty(userNode) != null) config.removeProperty(userNode);
            config.setProperty(userNode, userMap.get(user));
        }

        saveConfiguration();

        boolean hasDefaultGroup = config.getProperty("groups.default") != null;

        if (!hasDefaultGroup) {
            Main.showDialog("You haven't got a group called \"default\"!", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Main.showDialog("Successfully saved the configuration files!", "Success!", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getName() {
        return "PermissionsBukkit";
    }
}
