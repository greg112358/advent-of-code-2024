import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BridgeRepair {
    public static void main(String[] args) {
        try {
            File file = new File("data/day7.txt");
            // File file = new File("data/exampledata.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
            }
            var sum = 0;
            System.out.println("SUM: " + sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
