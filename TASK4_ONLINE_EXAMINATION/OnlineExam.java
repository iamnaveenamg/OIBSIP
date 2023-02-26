package TASK4_ONLINE_EXAMINATION;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import java.util.Timer;
import java.util.TimerTask;

class OnlineExam {
    public static void main(String args[]) {
        try {
            login form = new login();
            form.setSize(400, 150);
            form.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}