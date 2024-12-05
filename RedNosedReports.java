import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RedNosedReports {

    public static void main(String[] args){
        List<List<Integer>> reports = new ArrayList<>();
        try{
            File file = new File("day2.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            
            while((line=br.readLine())!=null){
                String[] nums = line.split("   ");
                List<Integer> report = new ArrayList<Integer>();
                for(String num:nums){
                    report.add(Integer.parseInt(num));
                }
                reports.add(report);
            }

            int safeReports = 0;
            for(List<Integer> report: reports){
                if(isSafeReport(report)){
                    safeReports++;
                }
            }
            System.out.printf("Safe Reports: %d\n",safeReports);

            int safeReportsWithProblemDampener = 0;
            var testList = List.of(1,2,3,4,5);
            System.out.println(testList);
            System.out.println(getSubArrayWithoutIndex(testList, 0));
            System.out.println(getSubArrayWithoutIndex(testList, 1));
            System.out.println(getSubArrayWithoutIndex(testList, 2));
            System.out.println(getSubArrayWithoutIndex(testList, 3));
            System.out.println(getSubArrayWithoutIndex(testList, 4));
            for(List<Integer> report:reports){
                if(isSafeReport(report)||
                isSafeReport(getSubArrayWithoutIndex(report, 0))||
                isSafeReport(getSubArrayWithoutIndex(report, 1))||
                isSafeReport(getSubArrayWithoutIndex(report, 2))||
                isSafeReport(getSubArrayWithoutIndex(report, 3))||
                isSafeReport(getSubArrayWithoutIndex(report, 4))
                ){
                    safeReportsWithProblemDampener++;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static boolean isSafeReport(List<Integer> report){
        if(report.size() < 1) {
            throw new RuntimeException("report's fucked");
        }
        int prev = report.get(0);
        boolean shownIncrease = false;
        boolean shownDecrease = false;
        for(int i =1;i<report.size();i++){
            int curr = report.get(i);
            if(prev==curr)return false;
            boolean isIncreasing = prev < curr;
            boolean weHaveAProblem = (isIncreasing && shownDecrease) || (!isIncreasing && shownIncrease);
            if(weHaveAProblem){
                return false;
            }
            shownIncrease = isIncreasing;
            shownDecrease = !isIncreasing;
        }
        return true;
    }

    public static List<Integer> getSubArrayWithoutIndex(List<Integer> list , int index){
        if(index < 0 || index>=list.size()) {
            throw new RuntimeException("index is nonsense");
        }
        if(index == 0){
            return list.subList(1,list.size());
        }else if(index == list.size()-1){
            return list.subList(0, index);
        }else{
            return Stream.concat(list.subList(0, index).stream(), list.subList(index+1, list.size()).stream()).collect(Collectors.toList());
        }
    }
}
