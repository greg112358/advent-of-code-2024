import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Optional;

public class MullItOver {
    record Mul(int a, int b){};
   public static void main(String[] args){
       System.out.println("waaat");
       try{
           File file = new File("data/day3.txt");
           BufferedReader br = new BufferedReader(new FileReader(file));

           StringBuilder builder = new StringBuilder();
           String line;
           while((line=br.readLine())!=null){
               builder.append(line).append("\n");
           }
           String str = builder.toString();
           int sum = 0;
           while(true){
               int mulIndex = str.indexOf("mul(", 0);
               if(mulIndex==-1)break;
               str = str.substring(mulIndex);
               Optional<Mul> optionalMul = getMul(str);
               if(optionalMul.isPresent()){
                   Mul mul = optionalMul.get();
                   sum+=mul.a() * mul.b();
               }
               try{
                   str = str.substring(4);
               }catch(Exception e){
                   break;
               }
           }
           System.out.println("sum "+sum);
       }catch(Exception e){
           e.printStackTrace();
       }
   }
   public static boolean isNumeric(char ch){
       return
           ch=='0'
           || ch=='1'
           || ch=='2'
           || ch=='3'
           || ch=='4'
           || ch=='5'
           || ch=='6'
           || ch=='7'
           || ch=='8'
           || ch=='9';
   }

   public static Optional<Mul> getMul(String str){
       if(!str.substring(0,4).equals("mul(")){
           return Optional.empty();
       }
       String firstNumString = "";
       int i;
       for(i = 4;i<str.length();i++){
           if(isNumeric(str.charAt(i))){
               firstNumString+=str.charAt(i);
           }else{
               break;
           }
       }
       if(!(str.charAt(i) == ',')){
           return Optional.empty();
       }
       i++;
       String secondNumString = "";
       for(;i<str.length();i++){
           if(isNumeric(str.charAt(i))){
               secondNumString+=str.charAt(i);
           }else{
               break;
           }
       }
       if(!(str.charAt(i)==')')){
           return Optional.empty();
       }
       return Optional.of(new Mul(Integer.parseInt(firstNumString),Integer.parseInt(secondNumString)));
       
   }
}

