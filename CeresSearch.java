import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CeresSearch {
      public static void main(String[] args){
       System.out.println("waaat");
       
       try{
           File file = new File("data/day4.txt");
           BufferedReader br = new BufferedReader(new FileReader(file));

           StringBuilder builder = new StringBuilder();
           String line;
           ArrayList<ArrayList<Character>> puzzle = new ArrayList<>();
           while((line=br.readLine())!=null){
               var charLine = new ArrayList<Character>();
               for(char ch: line.toCharArray()){
                   charLine.add(ch);
               }
               puzzle.add(charLine);
           }
           var sumPart1 = 0;
           var sumPart2 = 0;
           for(int i = 0;i<puzzle.size();i++){
               for(int j = 0;j<puzzle.get(i).size();j++){
                   sumPart1+=getijCnt(puzzle, i, j);
                   if(isXmas(puzzle, i, j)){
                       sumPart2+=1;
                   }
               }
           }
           System.out.println("SUM PART 1: "+sumPart1);
           System.out.println("SUM PART 2: "+sumPart2);

       }catch(Exception e){
           e.printStackTrace();
       }
   }

   public static int getijCnt(ArrayList<ArrayList<Character>> puzzle,int i, int j){
       int cnt = 0;
       if(!(puzzle.get(i).get(j)=='X'))return cnt;
       //right
       try{
       if(puzzle.get(i).get(j+1)=='M' && puzzle.get(i).get(j+2)=='A' && puzzle.get(i).get(j+3)=='S'){
           cnt+=1;
       }
       }catch(IndexOutOfBoundsException e){

       }
       //left
       try{
           if(puzzle.get(i).get(j-1)=='M' && puzzle.get(i).get(j-2)=='A' && puzzle.get(i).get(j-3)=='S'){
               cnt+=1;
           }
           }catch(IndexOutOfBoundsException e){
   
           }
       //down
       try{
           if(puzzle.get(i+1).get(j)=='M' && puzzle.get(i+2).get(j)=='A' && puzzle.get(i+3).get(j)=='S'){
               cnt+=1;
           }
           }catch(IndexOutOfBoundsException e){
   
           }
       //up
       try{
           if(puzzle.get(i-1).get(j)=='M' && puzzle.get(i-2).get(j)=='A' && puzzle.get(i-3).get(j)=='S'){
               cnt+=1;
           }
           }catch(IndexOutOfBoundsException e){
   
           }
       //diagonal right down
       try{
           if(puzzle.get(i+1).get(j+1)=='M' && puzzle.get(i+2).get(j+2)=='A' && puzzle.get(i+3).get(j+3)=='S'){
               cnt+=1;
           }
           }catch(IndexOutOfBoundsException e){
   
           }
       //diagonal right up
       try{
           if(puzzle.get(i-1).get(j+1)=='M' && puzzle.get(i-2).get(j+2)=='A' && puzzle.get(i-3).get(j+3)=='S'){
               cnt+=1;
           }
           }catch(IndexOutOfBoundsException e){
   
           }
       //diagonal left down
       try{
           if(puzzle.get(i+1).get(j-1)=='M' && puzzle.get(i+2).get(j-2)=='A' && puzzle.get(i+3).get(j-3)=='S'){
               cnt+=1;
           }
           }catch(IndexOutOfBoundsException e){
   
           }
       //diagonal left up
       try{
           if(puzzle.get(i-1).get(j-1)=='M' && puzzle.get(i-2).get(j-2)=='A' && puzzle.get(i-3).get(j-3)=='S'){
               cnt+=1;
           }
           }catch(IndexOutOfBoundsException e){
   
           }
       return cnt;
   }

   public static boolean isXmas(ArrayList<ArrayList<Character>> puzzle, int i, int j){
       boolean isXmas = false;
       if(puzzle.get(i).get(j)!='A'){
           return false;
       }

       try{
           Character topLeft = puzzle.get(i-1).get(j-1);
           Character bottomRight = puzzle.get(i+1).get(j+1);
           Character topRight = puzzle.get(i-1).get(j+1);
           Character bottomLeft = puzzle.get(i+1).get(j-1);
           boolean firstDiagonal = topLeft == 'M' && bottomRight=='S'
                                   || topLeft == 'S' && bottomRight=='M';
           boolean secondDiagonal = topRight == 'M' && bottomLeft=='S'
                                   || topRight == 'S' && bottomLeft=='M';
           isXmas = firstDiagonal && secondDiagonal;
       }catch(IndexOutOfBoundsException e){
           return false;
       }
       return isXmas;
   }
}
