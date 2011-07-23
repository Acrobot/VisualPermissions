package com.Acrobot.VisualPermissions;

import com.Acrobot.VisualPermissions.Plugins.pluginChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Acrobot
 */
public class ButtonPress implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Main.mf.loadButton) {
            File folder = Main.openChooserDialog();
            if (folder != null && pluginChooser.usedPlugin != null) {
                pluginChooser.usedPlugin.setFolder(folder);
                pluginChooser.usedPlugin.load();
            }
            return;
        }
        if (e.getSource() == Main.mf.newConfigurationButton) {
            pluginChooser.usedPlugin.newConfiguration();
            return;
        }
        if (e.getSource() == Main.mf.saveButton) {
            pluginChooser.usedPlugin.save();
            return;
        }
        if (e.getSource() == Main.mf.addPlayerButton) {
            String name = Main.showInputDialog("What will be the name of your new player?", "Name your player");
            if (name == null) return;
            pluginChooser.usedPlugin.addPlayer(name);
            refreshLists(false);
            return;
        }
        if (e.getSource() == Main.mf.removePlayerButton) {
            String player = (String) Main.mf.players.getSelectedValue();
            if (player == null) return;
            boolean really = Main.yesNoDialog("Do you really want to remove " + player + '?', "Removing player!");
            if (!really) return;
            pluginChooser.usedPlugin.removePlayer(player);
            refreshLists(false);
            return;
        }
        if (e.getSource() == Main.mf.addGroupButton) {
            String name = Main.showInputDialog("What will be the name of your new group?", "Name your group");
            if (name == null) return;
            pluginChooser.usedPlugin.addGroup(name);
            refreshLists(true);
            return;
        }
        if (e.getSource() == Main.mf.removeGroupButton) {
            String group = (String) Main.mf.groups.getSelectedValue();
            if (group == null) return;
            boolean really = Main.yesNoDialog("Do you really want to remove " + group + '?', "Removing group!");
            if (!really) return;
            pluginChooser.usedPlugin.removeGroup(group);
            refreshLists(true);
            return;
        }
        if (e.getSource() == Main.mf.addGroupPermissionButton) {
            if (Main.mf.groups.getSelectedValue() == null) return;
            String permission = Main.showInputDialog("What permission do you want to add?", "Choose permission");
            if (permission == null) return;
            pluginChooser.usedPlugin.addGroupPermission((String) Main.mf.groups.getSelectedValue(), permission);
            ListClick.refreshLists((String) Main.mf.groups.getSelectedValue(), true);
            return;
        }
        if (e.getSource() == Main.mf.removeGroupPermissionButton) {
            String permission = (String) Main.mf.gPermissions.getSelectedValue();
            if (permission == null) return;
            boolean really = Main.yesNoDialog("Do you really want to remove " + permission + '?', "Removing permission!");
            if (!really) return;
            pluginChooser.usedPlugin.removeGroupPermission((String) Main.mf.groups.getSelectedValue(), (String) Main.mf.gPermissions.getSelectedValue());
            ListClick.refreshLists((String) Main.mf.groups.getSelectedValue(), true);
            return;
        }
        if (e.getSource() == Main.mf.addPlayerGroupButton) {
            if (Main.mf.players.getSelectedValue() == null) return;
            String group = Main.showListDialog("What group do you want to add?", "Choose group", pluginChooser.usedPlugin.getGroups().toArray());
            if (group == null) return;
            pluginChooser.usedPlugin.addPlayerGroup((String) Main.mf.players.getSelectedValue(), group);
            ListClick.refreshLists((String) Main.mf.players.getSelectedValue(), false);
            return;
        }
        if (e.getSource() == Main.mf.removePlayerGroupButton) {
            String group = (String) Main.mf.pGroups.getSelectedValue();
            if (group == null) return;
            boolean really = Main.yesNoDialog("Do you really want to remove " + group + '?', "Removing group!");
            if (!really) return;
            pluginChooser.usedPlugin.removePlayerGroup((String) Main.mf.players.getSelectedValue(), (String) Main.mf.pGroups.getSelectedValue());
            ListClick.refreshLists((String) Main.mf.players.getSelectedValue(), false);
        }

    }

    private static void refreshLists(boolean group) {
        DefaultListModel model = new DefaultListModel();
        for (String value : (group ? pluginChooser.usedPlugin.getGroups() : pluginChooser.usedPlugin.getPlayers())) {
            model.addElement(value);
        }

        if (group) Main.mf.groups.setModel(model);
        if (!group) Main.mf.players.setModel(model);

        ListClick.clearLists();
    }
}
