import java.io.IOException;

public class Day4 extends Day implements TwoPartProblem {
    public Day4(String filename) throws IOException {
        super(filename);
    }

    public Integer[] splitLine(String line){
        Integer[] splitAsInts =  new Integer[4];
        String[] splitLine = line.split("[,-]+");
        for (int i = 0; i < splitLine.length; i++){
            splitAsInts[i] = Integer.valueOf(splitLine[i]);
        }
        return splitAsInts;
    }

    public Boolean isfullyContained(Integer[] splitLine){

        int AStart = splitLine[0];
        int AEnd = splitLine[1];
        int BStart = splitLine[2];
        int BEnd = splitLine[3];
        //if A contains B
        if ((AStart <= BStart) && (AEnd >= BEnd)){
            return true;
        }
        //if B contains A
        else return (BStart <= AStart) && (BEnd >= AEnd);
    }

    public Boolean isOverlapped(Integer[] splitLine){
        int AStart = splitLine[0];
        int AEnd = splitLine[1];
        int BStart = splitLine[2];
        int BEnd = splitLine[3];
        //is B anywhere in A
        if(((AStart <= BStart) && (BStart <= AEnd)) | ((AStart <= BEnd) && (BEnd <= AEnd))){
            return true;
        }
        //is A anywhere in B
        else return ((BStart <= AStart) && (AStart <= BEnd)) | ((BStart <= AEnd) && (AEnd <= BEnd));


    }

    public  int part1() {
        int totalScore = 0;
        for (String line : lines){
            if (isfullyContained(splitLine(line))){
                totalScore++;
            }
        }
        return totalScore;

    }

    public  int part2() {
        int totalScore = 0;
        for (String line : lines){
            if (isOverlapped(splitLine(line))){
                totalScore++;
            }
        }
        return totalScore;

    }




}
