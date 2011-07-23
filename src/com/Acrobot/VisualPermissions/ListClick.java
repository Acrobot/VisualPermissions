package com.Acrobot.VisualPermissions;

import com.Acrobot.VisualPermissions.Plugins.pluginChooser;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Acrobot
 */
public class ListClick implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == Main.mf.groups) {
            String selected = (String) Main.mf.groups.getSelectedValue();
            if (Main.mf.groups.getSelectedValue() == null) return;
            refreshLists(selected, true);
        }
        if (e.getSource() == Main.mf.players) {
            String selected = (String) Main.mf.players.getSelectedValue();
            if (Main.mf.players.getSelectedValue() == null) return;
            refreshLists(selected, false);
        }
    }

    public static void refreshLists(String groupOrPlayer, boolean group) {
        DefaultListModel model = new DefaultListModel();
        for (String value : (group ? pluginChooser.usedPlugin.getGroupPermissions(groupOrPlayer) : pluginChooser.usedPlugin.getPlayerGroups(groupOrPlayer))) {
            model.addElement(value);
        }

        if (group) Main.mf.gPermissions.setModel(model);
        if (!group) Main.mf.pGroups.setModel(model);
    }

    public static void clearLists() {
        DefaultListModel model = new DefaultListModel();
        Main.mf.pGroups.setModel(model);
        Main.mf.gPermissions.setModel(model);
    }
}
