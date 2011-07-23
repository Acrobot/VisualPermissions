package com.Acrobot.VisualPermissions;

import com.Acrobot.VisualPermissions.Plugins.pluginChooser;

import javax.swing.*;

/**
 * @author Acrobot
 */
public class MainForm {
    public JTabbedPane tabs;
    public JPanel mainPanel;
    public JPanel Players;
    public JPanel Groups;
    public JButton saveButton;
    public JButton loadButton;
    public JList gPermissions;
    public JList pGroups;
    public JList players;
    public JList groups;
    public JButton addPlayerButton;
    public JButton removePlayerButton;
    public JButton addPlayerGroupButton;
    public JButton removePlayerGroupButton;
    public JButton addGroupButton;
    public JButton removeGroupButton;
    public JButton addGroupPermissionButton;
    public JButton removeGroupPermissionButton;
    public JComboBox permissionsPlugin;
    public JButton newConfigurationButton;

    public MainForm() {
        permissionsPlugin.addActionListener(new pluginChooser());
        ButtonPress buttonListener = new ButtonPress();

        saveButton.addActionListener(buttonListener);
        loadButton.addActionListener(buttonListener);
        newConfigurationButton.addActionListener(buttonListener);

        addPlayerButton.addActionListener(buttonListener);
        removePlayerButton.addActionListener(buttonListener);

        addGroupButton.addActionListener(buttonListener);
        removeGroupButton.addActionListener(buttonListener);

        addGroupPermissionButton.addActionListener(buttonListener);
        removeGroupPermissionButton.addActionListener(buttonListener);

        addPlayerGroupButton.addActionListener(buttonListener);
        removePlayerGroupButton.addActionListener(buttonListener);

        ListClick listListener = new ListClick();
        groups.addListSelectionListener(listListener);
        players.addListSelectionListener(listListener);

        gPermissions.addListSelectionListener(listListener);
        pGroups.addListSelectionListener(listListener);
    }
}
