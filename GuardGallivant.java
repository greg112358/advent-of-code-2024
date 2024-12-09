import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class GuardGallivant {
    enum Direction {
        Right,
        Left,
        Up,
        Down
    }
    public static void main(String[] args){
       try{
           File file = new File("data/day6.txt");
           //File file = new File("data/exampledata.txt");
           BufferedReader br = new BufferedReader(new FileReader(file));
           String line;
           ArrayList<ArrayList<Character>> maze = new ArrayList<>();
           while((line=br.readLine())!=null){
            ArrayList<Character> chars= new ArrayList<>();
            for(Character ch : line.toCharArray()){
                if(ch=='\n')continue;
                chars.add(ch);
            }
            maze.add(chars);
           }
           int ii=-1;
           int jj =-1;
           for(int i = 0;i<maze.size();i++){
            for(int j = 0;j<maze.get(i).size();j++){
                if(maze.get(i).get(j).equals('^')){
                    ii=i;
                    jj=j;
                }
            }
           }
           Direction direction = Direction.Up;
           int numOfMoves = 0;
           while(true){
            if(direction.equals(Direction.Up)){
                if(ii<=0){
                    break;
                }
                if(maze.get(ii-1).get(jj).equals('#')){
                    direction = Direction.Right;
                    continue;
                }else{
                    ii = ii -1;
                }
            }else if(direction.equals(Direction.Right)){
                if(jj>=maze.get(ii).size()-1){
                    break;
                }
                if(maze.get(ii).get(jj+1).equals('#')){
                    direction = Direction.Down;
                    continue;
                }else{
                    jj = jj + 1;
                }
            }else if(direction.equals(Direction.Down)){
                if(ii>=maze.size()-1){
                    break;
                }
                if(maze.get(ii+1).get(jj).equals('#')){
                    direction = Direction.Left;
                    continue;
                }else{
                    ii = ii +1;
                }
            }else if(direction.equals(Direction.Left)){
                if(jj<=0){
                    break;
                }
                if(maze.get(ii).get(jj-1).equals('#')){
                    direction = Direction.Up;
                    continue;
                }else{
                    jj = jj -1;
                }
            }
            numOfMoves ++;
           }
           System.out.println("Number of moves: "+numOfMoves);
       }catch(Exception e){
           e.printStackTrace();
       }
   }
}
