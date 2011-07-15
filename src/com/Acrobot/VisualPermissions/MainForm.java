package com.Acrobot.VisualPermissions;

import com.Acrobot.VisualPermissions.Buttons.add.addGroupPermission;
import com.Acrobot.VisualPermissions.Buttons.add.addGroupPressed;
import com.Acrobot.VisualPermissions.Buttons.add.addPlayerGroup;
import com.Acrobot.VisualPermissions.Buttons.add.addPlayerPressed;
import com.Acrobot.VisualPermissions.Buttons.loadButtonPressed;
import com.Acrobot.VisualPermissions.Buttons.remove.removeGroupPressed;
import com.Acrobot.VisualPermissions.Buttons.remove.removePermissionPressed;
import com.Acrobot.VisualPermissions.Buttons.remove.removePlayerGroup;
import com.Acrobot.VisualPermissions.Buttons.remove.removePlayerPressed;
import com.Acrobot.VisualPermissions.Buttons.saveButtonPressed;
import com.Acrobot.VisualPermissions.Lists.groupListPressed;
import com.Acrobot.VisualPermissions.Lists.playerListPressed;

import javax.swing.*;

/**
 * @author Acrobot
 */
public class MainForm {
    private JTabbedPane tabs;
    public JPanel mainPanel;
    private JPanel Players;
    private JPanel Groups;
    private JButton saveButton;
    private JButton loadButton;
    public JList gPermissions;
    public JList pGroups;
    public JList players;
    public JList groups;
    private JButton addPlayerButton;
    private JButton removePlayerButton;
    private JButton addPlayerGroupButton;
    private JButton removePlayerGroupButton;
    private JButton addGroupButton;
    private JButton removeGroupButton;
    private JButton addGroupPermissionButton;
    private JButton removeGroupPermissionButton;

    public MainForm() {
        loadButton.addActionListener(new loadButtonPressed());
        saveButton.addActionListener(new saveButtonPressed());

        addGroupButton.addActionListener(new addGroupPressed());
        removeGroupButton.addActionListener(new removeGroupPressed());
        addPlayerButton.addActionListener(new addPlayerPressed());
        removePlayerButton.addActionListener(new removePlayerPressed());

        groups.addListSelectionListener(new groupListPressed());
        players.addListSelectionListener(new playerListPressed());

        addPlayerGroupButton.addActionListener(new addPlayerGroup());
        removePlayerGroupButton.addActionListener(new removePlayerGroup());

        removeGroupPermissionButton.addActionListener(new removePermissionPressed());
        addGroupPermissionButton.addActionListener(new addGroupPermission());
    }
}
