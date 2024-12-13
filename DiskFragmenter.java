import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class DiskFragmenter {


   public static void main(String[] args){        
       try{
           File file = new File("data/day9.txt");
           //File file = new File("data/exampledata.txt");
           BufferedReader br = new BufferedReader(new FileReader(file));
           String line;
           ArrayList<Character> compressedCharacters = new ArrayList<>();
           while((line=br.readLine())!=null){
               var chars = line.toCharArray();
               for(int i = 0;i<chars.length;i++){
                   compressedCharacters.add(chars[i]);
               }
           }
           int id = 0;
           ArrayList<String> uncompressedCharacters = new ArrayList<>();

           int freeSpaceBlockCnt = 0;
           for(int i = 0;i<compressedCharacters.size();i++){
               String str;
               if(i%2==0){
                   str = String.valueOf(id);
                   id++;
               }else{
                   str = ".";
                   freeSpaceBlockCnt+=Integer.valueOf(compressedCharacters.get(i).toString());
               }
               for(int j = 0;j<Integer.valueOf(compressedCharacters.get(i).toString());j++){
                   uncompressedCharacters.add(str);
               }
           }

           int freeSpaceBlocksSeen = 0;
           int jj = uncompressedCharacters.size()-1;
           for(int i = 0;i<uncompressedCharacters.size();i++){
               if(uncompressedCharacters.get(i).equals(".")){
                   freeSpaceBlocksSeen++;
                   for(int j = jj;j>=i+1;j--){
                       if(isInt(uncompressedCharacters.get(j))){
                           uncompressedCharacters.set(i, uncompressedCharacters.get(j));
                           uncompressedCharacters.set(j, ".");
                           jj = j;
                           break;
                       }
                   }
                   if(freeSpaceBlocksSeen>=freeSpaceBlockCnt)break;
               }
           }
       
           var sum = Long.valueOf(0);
           for(int i = 0;i<uncompressedCharacters.size();i++){
               if(isInt(uncompressedCharacters.get(i))){
                   sum+=Integer.parseInt(uncompressedCharacters.get(i))*i;
               }
           }

           System.out.println("SUM PART 1: "+sum);
           // part 2
           record Chunk(int size, String ch){};
           ArrayList<Chunk> chunks = new ArrayList<>();
           id = 0;
           for(int i = 0;i<compressedCharacters.size();i++){
               String str;
               int size = Integer.valueOf(compressedCharacters.get(i).toString());
               if(i%2==0){
                   str = String.valueOf(id);
                   id++;
               }else{
                   str = ".";
               }
               chunks.add(new Chunk(size,str));
           }
           for(int j = chunks.size()-1;j>=0;j--){
               if(isInt(chunks.get(j).ch())){
                   for(int i=0;i<j;i++){
                       if(chunks.get(i).ch().equals(".")){
                           int diff = chunks.get(i).size() - chunks.get(j).size();
                           if(diff>=0){
                               //chunk[j] is the numbers we're trying to move left
                               //chunks[i] is the "." spaces
                               Chunk chunk1 = new Chunk(chunks.get(j).size(),chunks.get(j).ch());
                               Chunk chunk2 = new Chunk(chunks.get(j).size(),".");
                               chunks.set(i,chunk1);
                               chunks.set(j,chunk2);
                           }
                           if(diff > 0){
                               // 3rd one is . of size diff
                               Chunk chunk3 = new Chunk(diff,".");
                               ArrayList<Chunk> newChunks = new ArrayList<>();

                               newChunks.addAll(chunks.subList(0, i+1));
                               newChunks.add(chunk3);
                               newChunks.addAll(chunks.subList(i+1, chunks.size()));
                               chunks = newChunks;
                           }
                           if(diff>=0){
                               break;
                           }
                       }
                   }
               }else{
                   
               }
           }
           ArrayList<String> files = new ArrayList<>();
           for(Chunk chunk: chunks){
               for(int i = 0;i<chunk.size();i++){
                   System.out.printf("%s",chunk.ch());
                   files.add(chunk.ch());
               }
           }
           sum = Long.valueOf(0);
           for(int i = 0;i<files.size();i++){
               String str = files.get(i);
               if(isInt(str)){
                   sum+=Integer.parseInt(str)*i;
               }
           }
           System.out.println("SUM PART 2: "+sum);


       }catch(Exception e){
           e.printStackTrace();
       }
   }

   public static boolean isInt(String str){
       try{
           Integer.parseInt(str);
           return true;
       }catch(Exception e){
           return false;
       }
   }
}
