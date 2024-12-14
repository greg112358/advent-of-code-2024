import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

public class HoofIt {
     public static void main(String[] args){        
       try{
           File file = new File("data.txt");
           //File file = new File("example.txt");
           BufferedReader br = new BufferedReader(new FileReader(file));
           String line;
           ArrayList<ArrayList<Integer>> map = new ArrayList<>();
           while((line=br.readLine())!=null){
               ArrayList<Integer> row = new ArrayList<>();
               for(char ch : line.toCharArray()){
                   row.add(Integer.parseInt(ch+""));
               }
               map.add(row);
           }
           var part1sum = 0;
           for(int i = 0;i<map.size();i++){
               for(int j = 0;j<map.size();j++){
                   HashSet<String> seen = new HashSet<>();
                   getTrailHeadScore(map,i,j,0,seen);
                   part1sum += seen.size();
               }
           }

           var part2sum = 0;
           for(int i = 0;i<map.size();i++){
               for(int j = 0;j<map.size();j++){
                   part2sum+=getTrailHeadScore(map,i,j,0);
               }
           }
           System.out.println("part1 sum:" +part1sum);
           System.out.println("part2 sum: "+part2sum);
       }catch(Exception e){
           e.printStackTrace();
       }
   }

   public static int getTrailHeadScore(ArrayList<ArrayList<Integer>> map, int i, int j, int target){
       if(i<0 || i>=map.size()){
           return 0;
       }
       if(j<0 || j>=map.get(i).size()){
           return 0;
       }
       if(map.get(i).get(j)!=target){
           return 0;
       }
       if(target == 9){
           return 1;
       }
       return getTrailHeadScore(map,i-1,j,target+1) + getTrailHeadScore(map,i+1,j,target+1) + getTrailHeadScore(map,i,j-1,target+1) + getTrailHeadScore(map, i, j+1, target+1);
   }

   public static void getTrailHeadScore(ArrayList<ArrayList<Integer>> map, int i, int j, int target,HashSet<String> set){
       if(i<0 || i>=map.size()){
           return;
       }
       if(j<0 || j>=map.get(i).size()){
           return;
       }
       if(map.get(i).get(j)!=target){
           return;
       }
       if(target == 9){
           set.add(i+","+j);
           return;
       }
       getTrailHeadScore(map,i-1,j,target+1,set);
       getTrailHeadScore(map,i+1,j,target+1,set);
       getTrailHeadScore(map,i,j-1,target+1,set);
       getTrailHeadScore(map, i, j+1, target+1,set);
   }
}
