package com.Acrobot.VisualPermissions.Lists;

import com.Acrobot.VisualPermissions.Main;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Acrobot
 */
public class groupList {
    public static List groups;
    public static List groupPermissions;

    private static String currentGroup;
    public static HashMap<String, List> gPermissions = new HashMap<String, List>();

    public static void setGroups(List Groups) {
        groups = (Groups != null ? Groups : new LinkedList());
        refreshGroups();
    }

    public static void addGroup(String group) {
        groups.add(group);
        refreshGroups();
    }

    public static void removeGroup(String group) {
        groups.remove(group);
        gPermissions.remove(group);
        refreshGroups();
    }

    public static boolean contains(String group) {
        return groups.contains(group);
    }

    public static void setGroupPermissions(String cGroup, List GroupPermissions) {
        currentGroup = cGroup;
        groupPermissions = (GroupPermissions != null ? GroupPermissions : new LinkedList());
        refreshGroupPermissions();
    }

    public static void addGroupPermission(String permission) {
        groupPermissions.add(permission);
        refreshGroupPermissions();
    }

    public static void removeGroupPermission(String permission) {
        groupPermissions.remove(permission);
        refreshGroupPermissions();
    }

    public static boolean containsPermission(String permission) {
        return groupPermissions.contains(permission);
    }

    public static void clearPermissionsList() {
        groupPermissions = new LinkedList();
        currentGroup = null;
        refreshGroupPermissions();
    }

    public static void clearGroupList() {
        clearPermissionsList();
        groups = new LinkedList();
        refreshGroups();
    }

    public static void refreshGroups() {
        DefaultListModel<String> dfm = new DefaultListModel<String>();

        if(groups != null){
            for (Object g : groups) {
                dfm.addElement((String) g);
            }
        }

        Main.mf.groups.setModel(dfm);
    }

    public static void refreshGroupPermissions() {
        DefaultListModel<String> dfm = new DefaultListModel<String>();

        if (groupPermissions != null){
            for (Object g : groupPermissions) {
                dfm.addElement((String) g);
            }
        }

        Main.mf.gPermissions.setModel(dfm);
        if (currentGroup != null) gPermissions.put(currentGroup, groupPermissions);
    }
}
