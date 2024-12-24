import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GardenGroups {
    record Point(int x, int y) {
    };

    record Row(Character ch, int startIndex, int endIndex) {
    };

    record Group(Character ch, ArrayList<Point> points) {
    };

    public static void main(String[] args) {
        try {
            File file = new File("data/day12.txt");
            //File file = new File("data/exampledata.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            ArrayList<ArrayList<Character>> garden = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                ArrayList<Character> row = new ArrayList<>();
                for (char ch : line.toCharArray()) {
                    row.add(ch);
                }
                garden.add(row);
            }
            ArrayList<Group> groups = new ArrayList<>();
            HashSet<Point> seen = new HashSet<>();
            for (int i = 0; i < garden.size(); i++) {
                for (int j = 0; j < garden.get(i).size(); j++) {
                    Point p = new Point(i, j);
                    Character ch = garden.get(i).get(j);
                    if (!seen.contains(p)) {
                        Group g = new Group(ch, new ArrayList<Point>(List.of()));
                        addPoint(g, seen, garden, p);
                        groups.add(g);
                    }
                }
            }
            var sum = 0;
            for (Group g : groups) {
                System.out.printf("%c: %d %d\n",g.ch(),g.points().size(),getPerimeter(g));
                sum += g.points().size() * getPerimeter(g);
            }
            System.out.println("SUM: " + sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addPoint(Group group, HashSet<Point> seen, ArrayList<ArrayList<Character>> garden, Point p) {
        if (p.x() < 0 || p.x() >= garden.size() || p.y() < 0 || p.y() >= garden.get(p.x()).size()) {
            return;
        }
        if (seen.contains(p))
            return;

        if (garden.get(p.x()).get(p.y()).equals(group.ch())) {
            group.points().add(p);
        }else{
            return;
        }
        seen.add(p);
        addPoint(group, seen, garden, new Point(p.x() - 1, p.y()));
        addPoint(group, seen, garden, new Point(p.x() + 1, p.y()));
        addPoint(group, seen, garden, new Point(p.x(), p.y() - 1));
        addPoint(group, seen, garden, new Point(p.x(), p.y() + 1));
    }

    public static int getPerimeter(Group group) {
        Set<Point> setOfPoints = new HashSet<>();
        for (Point point : group.points()) {
            setOfPoints.add(point);
        }
        var sum = 0;
        for (Point point : group.points()) {
            var miniSum = 4;
            // check up
            if (setOfPoints.contains(new Point(point.x(), point.y() - 1))) {
                miniSum -= 1;
            }
            ;

            // check down
            if (setOfPoints.contains(new Point(point.x(), point.y() + 1))) {
                miniSum -= 1;
            }
            ;

            // check left
            if (setOfPoints.contains(new Point(point.x() - 1, point.y()))) {
                miniSum -= 1;
            }
            ;

            // check right
            if (setOfPoints.contains(new Point(point.x() + 1, point.y()))) {
                miniSum -= 1;
            }
            ;
            sum += miniSum;
        }
        return sum;
    }

    public static int getPerimeter2(Group group){
        return 0;
    }

    public static boolean isAdjacent(Point a, Point b) {
        return (Math.abs(a.x() - b.x()) == 1 && a.y() == b.y())
                || (Math.abs(a.y() - b.y()) == 1 && a.x() == b.x());
    }

}
