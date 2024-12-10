import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HistorianHysteria {
    public static void main(String[] args){
        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();
        try{
            File file = new File("data/day1.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line=br.readLine())!=null){
                String[] parts = line.split("   ");
                if(parts.length<2){
                    throw new Exception("Unexpected file input");
                }
                left.add(Integer.parseInt(parts[0]));
                right.add(Integer.parseInt(parts[1]));
            }
            Collections.sort(left);
            Collections.sort(right);
            int sumOfDistances = 0;
            for(int i =0;i<left.size();i++){
                sumOfDistances += Math.abs(left.get(i) - right.get(i));
            }
            System.out.printf("SUM OF DISTANCES: %d\n",sumOfDistances);
            var rightOccurences = new HashMap<Integer,Integer>();
            for(Integer i:right){
                if(rightOccurences.containsKey(i)){
                    rightOccurences.put(i,rightOccurences.get(i)+1);
                }else{
                    rightOccurences.put(i,1);
                }
            }   
            var similarityScore = 0;         
            for(Integer j:left){
                if(rightOccurences.containsKey(j)){
                    similarityScore += j * rightOccurences.get(j);
                }
            }
            System.out.printf("SIMILARITY SCORE:%d\n",similarityScore);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
