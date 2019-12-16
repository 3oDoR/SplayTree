package main.java.com.spbstu.smirnov;

import javax.swing.*;
import java.awt.*;


public class SplayTreeVisualization extends JPanel {

    private SplayTreeSet splayTreeSet = new SplayTreeSet();
    private JTextField insertField = new JTextField(10);
    private JTextField removeField = new JTextField(10);
    private JTextField searchField = new JTextField(10);
    private JLabel insertLabel = new JLabel("Добавление");
    private JLabel removeLabel = new JLabel("Удаление");
    private JLabel searchLabel = new JLabel("Поиск");

    public static void main(String[] args) {
        SplayTreeVisualization splayTreeVisualization = new SplayTreeVisualization();
        splayTreeVisualization.run();
    }

    private void run() {
        JFrame topLevelContainer = new JFrame("SplayTreeSet");
        JPanel panel = new JPanel();
        topLevelContainer.setSize(900, 900);
        centreWindow(topLevelContainer);
        topLevelContainer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        insertField.addActionListener(actionEvent -> {
            splayTreeSet.insert(actionEvent.getActionCommand());
            System.out.println(splayTreeSet.size());
        });

        removeField.addActionListener(actionEvent -> {
            splayTreeSet.remove(actionEvent.getActionCommand());
            System.out.println(splayTreeSet.size());
        });

        searchField.addActionListener(actionEvent -> {
            splayTreeSet.contains(actionEvent.getActionCommand());
            System.out.println(splayTreeSet.size());
        });

        Box insertBox = Box.createHorizontalBox();
        Box removeBox = Box.createHorizontalBox();
        Box searchBox = Box.createHorizontalBox();

        insertLabel.add(insertField);
        removeLabel.add(removeField);
        searchLabel.add(searchField);
        insertBox.add(insertField);
        insertBox.add(insertLabel);
        removeBox.add(removeField);
        removeBox.add(removeLabel);
        searchBox.add(searchField);
        searchBox.add(searchLabel);
        panel.add(insertBox);
        panel.add(removeBox);
        panel.add(searchBox);

        topLevelContainer.add(panel);
        topLevelContainer.setVisible(true);
    }

    private void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

}
