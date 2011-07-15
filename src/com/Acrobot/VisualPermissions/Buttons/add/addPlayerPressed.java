package com.Acrobot.VisualPermissions.Buttons.add;

import com.Acrobot.VisualPermissions.Lists.playerList;
import com.Acrobot.VisualPermissions.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Acrobot
 */
public class addPlayerPressed implements ActionListener{
    public void actionPerformed(ActionEvent e){
        String playerToAdd = Main.showInputDialog("What is the name of this player?", "Choose name...");
        if(playerList.contains(playerToAdd)){
            Main.showDialog("There is already a player named like that!", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        playerList.addPlayer(playerToAdd);
    }
}
