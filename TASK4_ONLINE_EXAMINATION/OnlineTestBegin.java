package TASK4_ONLINE_EXAMINATION;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import java.util.Timer;
import java.util.TimerTask;

class OnlineTestBegin extends JFrame implements ActionListener {
    JLabel l;
    JLabel l1;
    JRadioButton jb[] = new JRadioButton[6];
    JButton b1, b2, log;
    ButtonGroup bg;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];
    Timer timer = new Timer();

    OnlineTestBegin(String s) {
        super(s);
        l = new JLabel();
        l1 = new JLabel();
        add(l);
        add(l1);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            jb[i] = new JRadioButton();
            add(jb[i]);
            bg.add(jb[i]);
        }
        b1 = new JButton("Save and Next");
        b2 = new JButton("Save for later");
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(b1);
        add(b2);
        set();
        l.setBounds(30, 40, 450, 20);
        l1.setBounds(20, 20, 450, 20);
        jb[0].setBounds(50, 80, 100, 20);
        jb[1].setBounds(50, 110, 100, 20);
        jb[2].setBounds(50, 140, 100, 20);
        jb[3].setBounds(50, 170, 100, 20);
        b1.setBounds(95, 240, 140, 30);
        b2.setBounds(270, 240, 150, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 600;

            public void run() {
                l1.setText("Time left: " + i);
                i--;
                if (i < 0) {
                    timer.cancel();
                    l1.setText("Time Out");
                }
            }
        }, 0, 1000);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (check())
                count = count + 1;
            current++;
            set();
            if (current == 9) {
                b1.setEnabled(false);
                b2.setText("Result");
            }
        }
        if (e.getActionCommand().equals("Save for later")) {
            JButton bk = new JButton("Review" + x);
            bk.setBounds(480, 20 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();
            if (current == 9)
                b2.setText("Result");
            setVisible(false);
            setVisible(true);
        }
        for (int i = 0, y = 1; i < x; i++, y++) {
            if (e.getActionCommand().equals("Review" + y)) {
                if (check())
                    count = count + 1;
                now = current;
                current = m[y];
                set();
                ((JButton) e.getSource()).setEnabled(false);
                current = now;
            }
        }
        if (e.getActionCommand().equals("Result")) {
            if (check())
                count = count + 1;
            current++;
            JOptionPane.showMessageDialog(this, "Score =" + count);
            System.exit(0);
        }
    }

    void set() {
        jb[4].setSelected(true);
        if (current == 0) {
            l.setText("Que1: Who is known as father of java programming language?");
            jb[0].setText("charles Babbage");
            jb[1].setText("James Gosling");
            jb[2].setText("M.P.Java");
            jb[3].setText("Blais Pascal");
        }
        if (current == 1) {
            l.setText("Que2: Number of primitive data types in java are?");
            jb[0].setText("6");
            jb[1].setText("7");
            jb[2].setText("8");
            jb[3].setText("9");
        }
        if (current == 2) {
            l.setText("Que3: String is mutable or immutable?");
            jb[0].setText("Immutable Type");
            jb[1].setText("Mutable Type");
            jb[2].setText("Both Option");
            jb[3].setText("None");
        }
        if (current == 3) {
            l.setText("Que4: Expected created by try block is caaught in which block.?");
            jb[0].setText("catch");
            jb[1].setText("throw");
            jb[2].setText("final");
            jb[3].setText("thrown");
        }
        if (current == 4) {
            l.setText(
                    "Que5: Identify the keyword among the following that makes a variable belong to a class,rather than being defined for each instance of the class.");
            jb[0].setText("abstract");
            jb[1].setText("final");
            jb[2].setText("static");
            jb[3].setText("volatile");
        }
        if (current == 5) {
            l.setText("Que6: compareTo() returns");
            jb[0].setText("true");
            jb[1].setText("false");
            jb[2].setText("in int value");
            jb[3].setText("none");
        }
        if (current == 6) {
            l.setText("Que7: String str = “Hellow”; System.out.println(str.indexOf(‘t)); ");
            jb[0].setText("0");
            jb[1].setText("-1");
            jb[2].setText("1");
            jb[3].setText("None");
        }
        if (current == 7) {
            l.setText("Que8: Arrays in java are-");
            jb[0].setText("Object References");
            jb[1].setText("Primitive Data Type");
            jb[2].setText("Class");
            jb[3].setText("Object");
        }
        if (current == 8) {
            l.setText("Que9: When an array is passed to a method, what does the method receive ?");
            jb[0].setText("Class Object");
            jb[1].setText("A copy of array");
            jb[2].setText("The reference of the array");
            jb[3].setText("Copy of Last Element");
        }
        if (current == 9) {
            l.setText("Que10: What is the size of float and double in java?");
            jb[0].setText("65 and 64");
            jb[1].setText("32 and 32");
            jb[2].setText("32 and 64");
            jb[3].setText("32 and 65");
        }
        l.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 90; i += 30, j++)
            jb[j].setBounds(50, 80 + i, 200, 20);
    }

    boolean check() {
        if (current == 0)
            return (jb[1].isSelected());
        if (current == 1)
            return (jb[1].isSelected());
        if (current == 2)
            return (jb[1].isSelected());
        if (current == 3)
            return (jb[0].isSelected());
        if (current == 4)
            return (jb[2].isSelected());
        if (current == 5)
            return (jb[2].isSelected());
        if (current == 6)
            return (jb[1].isSelected());
        if (current == 7)
            return (jb[3].isSelected());
        if (current == 8)
            return (jb[2].isSelected());
        if (current == 9)
            return (jb[2].isSelected());
        return false;
    }
}
