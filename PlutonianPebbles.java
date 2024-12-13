import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class PlutonianPebbles {
    
       public static void main(String[] args) {
        try {
            File file = new File("data/day11.txt");
            // File file = new File("data/exampledata.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            ArrayList<Long> stones = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                for(String part:parts){
                    stones.add(Long.valueOf(part));
                }
            }
            for(int i = 0;i<75;i++){
                System.out.printf("BLINK %d TIMES\n",i);
                stones = blink(stones);
            }
            System.out.println("stones: " + stones.toString());
            System.out.println("stone length: "+stones.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Long> blink(ArrayList<Long> stones){
        ArrayList<Long> newStones = new ArrayList<>();

        for(Long stone: stones){
            if(stone ==0){
                newStones.add(Long.valueOf(1));
            }else if(String.valueOf(stone).length() %2 == 0){
                var str = String.valueOf(stone);
                var leftStr = str.substring(0,str.length()/2);
                var rightStr = str.substring((str.length()/2), str.length());
                newStones.add(Long.valueOf(leftStr));
                newStones.add(Long.valueOf(rightStr));
            }else{
                newStones.add(stone *2024);
            }

        }
        return newStones;
    }
}
