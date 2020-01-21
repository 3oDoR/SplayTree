package main.java.com.spbstu.smirnov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SplayTreeVisualization extends JFrame {

    public static void main(String[] args) {
        SplayTreeVisualization splayTreeVisualization = new SplayTreeVisualization();
        splayTreeVisualization.run();
    }

    JFrame topLevelContainer = new JFrame("SplayTreeSet");
    SplayTreeSet splayTreeSet = new SplayTreeSet();

    private void run() {
        topLevelContainer.setSize(900, 900);
        centreWindow(topLevelContainer);
        topLevelContainer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        topLevelContainer.setVisible(true);
        initComponent();
    }

    private void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    private void initComponent() {
        DrawCircle drawCircle = new DrawCircle(splayTreeSet);
        JLabel insetLabel = new JLabel("Insert");
        JLabel removeLabel = new JLabel("Remove");
        JLabel containsLabel = new JLabel("Contains");

        JTextField insetField = new JTextField(5);

        insetField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                drawCircle.splayTreeSet.insert(Integer.parseInt(actionEvent.getActionCommand()));
                drawCircle.repaint();

            }
        });

        JTextField removeField = new JTextField(5);
        removeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(actionEvent.getActionCommand());
            }
        });

        JTextField containsField = new JTextField(5);
        containsField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(actionEvent.getActionCommand());
            }
        });




        drawCircle.add(insetLabel);
        drawCircle.add(insetField);
        drawCircle.add(removeLabel);
        drawCircle.add(removeField);
        drawCircle.add(containsLabel);
        drawCircle.add(containsField);

        topLevelContainer.getContentPane().add(BorderLayout.CENTER, drawCircle);
        topLevelContainer.setVisible(true);
    }
}








