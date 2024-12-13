import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BridgeRepair {
    record Equation(Long sum, List<Long> operands){};
    public static void main(String[] args) {
        try {
            File file = new File("data/day7.txt");
            //File file = new File("data/exampledata.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            List<Equation> equations = new ArrayList<Equation>();
            while ((line = br.readLine()) != null) {
                var parts = line.split(":");
                var sum = Long.parseLong(parts[0]);
                var secondParts = parts[1].trim().split(" ");
                List<Long> operands = new ArrayList<Long>();
                for(String num:secondParts){
                    operands.add(Long.parseLong(num));
                }
                Equation equation = new Equation(sum, operands);
                equations.add(equation);
            }
            Long sum = Long.valueOf(0);
            for(Equation equation: equations){
                if(isValid(equation)){
                    sum+=equation.sum();
                }
            }
            System.out.println("SUM: " + sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean isValid(Equation equation){
        return isValid(equation.sum(),equation.operands().get(0),equation.operands().subList(1,equation.operands().size()));
    }
    public static boolean isValid(Long target, Long sum, List<Long> operands){
        if(operands.size()==0 && sum.equals(target)){
            return true;
        }else if(operands.size()==0 && !sum.equals(target)){
            return false;
        }else{
            return isValid(target,sum*operands.get(0),operands.subList(1,operands.size())) ||
            isValid(target,sum+operands.get(0),operands.subList(1, operands.size()));
        }
    }
}
