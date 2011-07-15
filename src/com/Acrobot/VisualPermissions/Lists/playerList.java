package com.Acrobot.VisualPermissions.Lists;

import com.Acrobot.VisualPermissions.Main;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Acrobot
 */
public class playerList {
    public static List players;
    public static List playerGroups;

    private static String currentPlayer;
    public static HashMap<String, List> pGroups = new HashMap<String, List>();

    public static void setPlayers(List Players) {
        players = Players;
        refreshPlayers();
    }

    public static void addPlayer(String name) {
        players.add(name);
        refreshPlayers();
    }

    public static void removePlayer(String player) {
        players.remove(player);
        pGroups.remove(player);
        refreshPlayers();
    }

    public static boolean contains(String group) {
        return players.contains(group);
    }

    public static void setPlayerGroups(String player, List groups) {
        currentPlayer = player;
        playerGroups = groups;
        refreshPlayerGroups();
    }

    public static void addPlayerGroup(String player) {
        playerGroups.add(player);
        refreshPlayerGroups();
    }

    public static void clearGroups() {
        playerGroups = new LinkedList();
        currentPlayer = null;
        refreshPlayerGroups();
    }

    public static void removePlayerGroup(String player) {
        playerGroups.remove(player);
        refreshPlayerGroups();
    }

    public static boolean containsPlayerGroup(String player) {
        return playerGroups.contains(player);
    }

    public static void clearPlayerList() {
        clearGroups();
        players = new LinkedList();
        refreshPlayers();
    }

    public static void refreshPlayers() {
        DefaultListModel<String> dfm = new DefaultListModel<String>();

        if (players != null) {
            for (Object g : players) {
                dfm.addElement((String) g);
            }
        }

        Main.mf.players.setModel(dfm);
    }

    public static void refreshPlayerGroups() {
        DefaultListModel<String> dfm = new DefaultListModel<String>();

        if (playerGroups != null) {
            for (Object g : playerGroups) {
                dfm.addElement((String) g);
            }
        }
        
        Main.mf.pGroups.setModel(dfm);
        if (currentPlayer != null) pGroups.put(currentPlayer, playerGroups);
    }
}
