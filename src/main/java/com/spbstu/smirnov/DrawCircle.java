package main.java.com.spbstu.smirnov;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class DrawCircle extends JPanel {
    SplayTreeSet splayTreeSet;

    DrawCircle(SplayTreeSet splayTreeSet) {
        this.splayTreeSet = splayTreeSet;
        this.setPreferredSize(new Dimension(900, 900));
        this.setBackground(new Color(100, 143, 110));
    }

    //    g.drawString((String) iterator.next(),425,80);
//    g.drawOval(400,50,50,50);
//


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println(splayTreeSet);

        Set hashSet = new HashSet();
        SplayTreeSet.Node current = null;
        int x = 400;
        int y = 50;
        int count = 0;
        int firstLeft = 0;




        if (!splayTreeSet.isEmpty()) {
            current = splayTreeSet.getRoot();
        } else return;

        while (hashSet.size() != splayTreeSet.size()) {
            if (current == splayTreeSet.getRoot() && count != 1) {
                hashSet.add(current);
                count++;
                g.drawOval(x, y, 50, 50);
                g.drawString(current.element.toString(), x, y);
                if (splayTreeSet.size() == hashSet.size()) {
                    return;
                }
            }


            if (current.left != null && !hashSet.contains(current.left)) {
                current = current.left;
                if (firstLeft / 2 != 0) {
                    x -= 100;
                    y += 50;
                    firstLeft++;
                } else {
                    x -= 50;
                    y += 50;
                }

                hashSet.add(current);
                g.drawOval(x, y, 50, 50);
                g.drawString(current.element.toString(), x, y);
                if (splayTreeSet.size() == hashSet.size()) {
                    return;
                }
            }

            if (current.right != null && !hashSet.contains(current.right)) {
                current = current.right;
                firstLeft++;
                if (firstLeft / 2 == 0) {
                    x += 100;
                    y += 50;
                } else {
                    x += 50;
                    y += 50;
                }

                hashSet.add(current);
                g.drawOval(x, y, 50, 50);
                g.drawString(current.element.toString(), x, y);
                if (splayTreeSet.size() == hashSet.size()) {
                    return;
                }
            }

            if (current != splayTreeSet.getRoot()) {
                if (current.left == null && current.right == null) {
                    if (current.parent.right == current) {
                        firstLeft++;
                        if (firstLeft / 2 == 0) {
                            x -= 100;
                            y -= 50;
                            firstLeft--;
                        } else {
                            x -= 50;
                            y -= 50;
                        }
                    } else if (current.parent.left == current) {
                        firstLeft++;
                        if (firstLeft / 2 == 0) {
                            x += 100;
                            y -= 50;
                            firstLeft--;
                        } else {
                            x += 50;
                            y -= 50;
                        }
                    }
                    current = current.parent;
                }

                if (current.left != null && current.right == null && hashSet.contains(current.left)) {
                    if (current == current.parent.left) {
                        firstLeft++;
                        if (firstLeft / 2 == 0) {
                            x += 100;
                            y -= 50;
                            firstLeft--;
                        } else {
                            x += 50;
                            y -= 50;
                        }

                    }
                    if (current == current.parent.right) {
                        firstLeft++;
                        if (firstLeft / 2 == 0) {
                            x -= 100;
                            y -= 50;
                            firstLeft--;
                        } else {
                            x -= 50;
                            y -= 50;
                        }

                    }
                    current = current.parent;
                }

                if (current.right != null && current.left == null && hashSet.contains(current.right)) {
                    firstLeft++;
                    if (firstLeft / 2 == 0) {
                        if (firstLeft / 2 == 0) {
                            x += 100;
                            y -= 50;
                            firstLeft--;
                        } else {
                            x += 50;
                            y -= 50;
                        }

                    }
                    if (current == current.parent.right) {
                        firstLeft++;
                        if (firstLeft / 2 == 0) {
                            x -= 100;
                            y -= 50;
                            firstLeft--;
                        } else {
                            x -= 50;
                            y -= 50;
                        }

                    }
                    current = current.parent;
                }
                if (current.right != null && current.left != null && hashSet.contains(current.right) && hashSet.contains(current.left)) {
                    if (current == current.parent.left) {
                        firstLeft++;
                        if (firstLeft / 2 == 0) {
                            x += 100;
                            y -= 50;
                            firstLeft--;
                        } else {
                            x += 50;
                            y -= 50;
                        }

                    }
                    if (current == current.parent.right) {
                        firstLeft++;
                        if (firstLeft / 2 == 0) {
                            x -= 100;
                            y -= 50;
                            firstLeft--;
                        } else {
                            x -= 50;
                            y -= 50;
                        }
                    }
                    current = current.parent;
                }
            }
        }
    }
}



