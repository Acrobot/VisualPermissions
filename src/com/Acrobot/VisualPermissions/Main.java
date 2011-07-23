package com.Acrobot.VisualPermissions;


import javax.swing.*;
import java.io.File;

/**
 * @author Acrobot
 */
public class Main {
    public static MainForm mf;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        JFrame frame = new JFrame("VisualPermissions by Acrobot");
        mf = new MainForm();
        frame.setContentPane(mf.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setSize(600, 500);
        frame.setVisible(true);

        mf.groups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mf.players.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mf.gPermissions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mf.pGroups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public static void showDialog(String message, String title, int style) {
        JOptionPane.showMessageDialog(null, message, title, style);
    }

    public static void showDialog(String message, String title) {
        showDialog(message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean yesNoDialog(String message, String title) {
        int result = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    public static String showInputDialog(String message, String title) {
        String toReturn = (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, null, null, "");
        if (toReturn != null && toReturn.isEmpty()) showInputDialog(message, "You didn't type in anything!");
        return toReturn;
    }

    public static String showListDialog(String message, String title, Object... possibilities) {
        return (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, null, possibilities, possibilities[0]);
    }

    public static File openChooserDialog() {
        JFileChooser fd = new JFileChooser();
        fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = fd.showOpenDialog(null);
        return (returnValue == JFileChooser.APPROVE_OPTION ? fd.getSelectedFile() : null);
    }
}
