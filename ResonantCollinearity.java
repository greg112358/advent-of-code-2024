import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ResonantCollinearity {
    record Point(int i, int j){};
    record Pair(Point a, Point b){};
    public static void main(String[] args) {
        try {
            //File file = new File("data/day8.txt");
             File file = new File("data/exampledata.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int j = 0;
            int width = 0;
            HashMap<Character,ArrayList<Point>> locations = new HashMap<>();
            ArrayList<Pair> pairs = new ArrayList<Pair>();
            while ((line = br.readLine()) != null) {
                width = line.length();
                for(int i = 0;i<line.length();i++){
                    Character ch = line.charAt(i);
                    if(!ch.equals('.')){
                        if(!locations.containsKey(ch)){
                            var list = new ArrayList<Point>();
                            list.add(new Point(i,j));
                            locations.put(ch,list);
                        }else{
                            Point newPoint = new Point(i,j);
                            for(Point p: locations.get(ch)){
                                pairs.add(new Pair(newPoint,p));
                            }
                            locations.get(ch).add(newPoint);
                        }
                    }
                }
                j++;
            }
            int height = j;

            Set<Point> antiNodeLocations = new HashSet<Point>();
            Set<Point> antiNodeLocationspt2 = new HashSet<Point>();
            for(Pair p : pairs){
                int run = p.b().i() - p.a().i();
                int rise = p.b().j() - p.a().j();
                Point antiNode1 = new Point(p.a().i()-run, p.a().j()-rise);
                Point antiNode2 = new Point(p.b().i()+run, p.b().j()+rise);
                if(isInBounds(antiNode1,width,height)){
                    antiNodeLocations.add(antiNode1);
                }
                if(isInBounds(antiNode2,width,height)){
                    antiNodeLocations.add(antiNode2);
                }
                while(isInBounds(antiNode1, width, height)){
                    antiNodeLocationspt2.add(antiNode1);
                    antiNode1 = new Point(antiNode1.i()-rise, antiNode1.j()-run);
                }
                while(isInBounds(antiNode2, width, height)){
                    antiNodeLocationspt2.add(antiNode2);
                    antiNode2 = new Point(antiNode2.i()+rise, antiNode2.j()+run);
                }
            }

            var sum = 0;
            System.out.println("SUM, part 1: " + antiNodeLocations.size());
            System.out.println("SUM, part 2: " + antiNodeLocationspt2.size());
            for(j = 0;j<height;j++){
                for(int i= 0;i<height;i++){
                    if(antiNodeLocationspt2.contains(new Point(i,j))){
                        System.out.print("#");
                    }else{
                        System.out.print(".");
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    private static boolean isInBounds(Point p, int width, int height){
        return p.i() >= 0 && p.i()< width && p.j()>=0 && p.j()<height;
    }
}
