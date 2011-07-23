package com.Acrobot.VisualPermissions.Plugins;

import com.Acrobot.VisualPermissions.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * @author Acrobot
 */
public class pluginChooser implements ActionListener {
    public static PermissionPlugin usedPlugin = new Permissions3();

    public void actionPerformed(ActionEvent actionEvent) {
        usedPlugin = pPlugin.getPermissionFromNumber(Main.mf.permissionsPlugin.getSelectedIndex());

        Main.showDialog("You changed the permission plugin to: " + usedPlugin.getName(), "Permission Plugin was changed!");
    }

    private enum pPlugin {
        PERMISSIONS_3(0, new Permissions3()),
        PERMISSIONS_BUKKIT(1, new PermissionsBukkit());

        final int number;
        final PermissionPlugin plugin;
        private static final HashMap<Integer, PermissionPlugin> plugins = new HashMap<Integer, PermissionPlugin>();

        pPlugin(int integer, PermissionPlugin plugin) {
            number = integer;
            this.plugin = plugin;
        }

        public static PermissionPlugin getPermissionFromNumber(int number) {
            return plugins.get(number);
        }

        static {
            for (pPlugin pl : pPlugin.values()) {
                plugins.put(pl.number, pl.plugin);
            }
        }
    }
}
