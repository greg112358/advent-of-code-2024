import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrintQueue {
    public static void main(String[] args){
       try{
           File file = new File("data/day5.txt");
           //File file = new File("data/exampledata.txt");
           BufferedReader br = new BufferedReader(new FileReader(file));

           HashMap<Integer,Set<Integer>> rules = new HashMap();
           List<List<Integer>> updates = new ArrayList<>();
           String line;
           while((line=br.readLine())!=null){
               line = line.replace("|", "X");
               if(line.contains("X")){
                String[] parts = line.split("X");
                Integer a = Integer.parseInt(parts[0]);
                Integer b = Integer.parseInt(parts[1]);
                if(rules.containsKey(a)){
                    rules.get(a).add(b);
                }else{
                    var newSet = new HashSet<Integer>();
                    newSet.add(b);
                    rules.put(a,newSet);
                }
               }else if(line.contains(",")){
                    String[] parts = line.split(",");
                    List<Integer> update = new ArrayList<Integer>();
                    for(String part:parts){
                        update.add(Integer.parseInt(part));
                    }
                    updates.add(update);
               }
           }
           var sum = 0;
           var sum2 = 0;
           for(List<Integer> update: updates){
                boolean isValidUpdate = true;
                Set<Integer> seen = new HashSet<Integer>();
                for(Integer page: update){
                    Set<Integer> intsInRule = rules.get(page);
                    if(intsInRule==null){
                        seen.add(page);
                        continue;
                    }
                    var iterator = intsInRule.iterator();
                    while(iterator.hasNext()){
                        Integer iRule =iterator.next();
                        if(seen.contains(iRule)){
                            isValidUpdate = false;
                            break;
                        }
                    }
                    seen.add(page);
                    if(!isValidUpdate)break;
                }
                if(isValidUpdate){
                    sum+=update.get(update.size()/2);
                }else{
                    
                }
           }
           System.out.println("SUM: "+sum);
       }catch(Exception e){
           e.printStackTrace();
       }
   }
}
