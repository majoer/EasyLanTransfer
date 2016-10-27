package easylantransfer.graphics;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JSpinner;

public class StaticGraphicFunctions {

    public static Box createHBox(String name, JSpinner target) {
        Box b = Box.createHorizontalBox();
        b.add(new JLabel(name));
        b.add(target);
        return b;
    }

    public static Point bestPosition(Dimension parent, Dimension child, Point parentPos) {
        Point[] candidates = {
            right(parent, parentPos),
            left(child, parentPos),
            below(parent, parentPos),
            above(child, parentPos)
        };
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle bounds = new Rectangle(resolution);
        for (Point p : candidates) {
            Rectangle window = new Rectangle(p, child);
            if (bounds.contains(window)) {
                return p;
            }
        }
        return center(parent, child, parentPos);

    }

    public static Point center(Dimension parent, Dimension child, Point parentPos) {
        Point centerParent = new Point(parent.width / 2, parent.height / 2);
        Point childPos = new Point(centerParent.x - (child.width / 2),
                centerParent.y - (child.height / 2));
        childPos.x += parentPos.x;
        childPos.y += parentPos.y;
        return childPos;
    }

    public static Point right(Dimension parent, Point parentPos) {
        Point childPos = new Point(parentPos.x + parent.width, parentPos.y);
        return childPos;
    }

    public static Point left(Dimension child, Point parentPos) {
        Point childPos = new Point(parentPos.x - child.width, parentPos.y);
        return childPos;
    }

    public static Point above(Dimension child, Point parentPos) {
        Point childPos = new Point(parentPos.x, parentPos.y - child.height);
        return childPos;
    }

    public static Point below(Dimension parent, Point parentPos) {
        Point childPos = new Point(parentPos.x, parentPos.y + parent.height);
        return childPos;
    }
}
