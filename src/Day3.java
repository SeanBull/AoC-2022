import java.io.IOException;

public class Day3 extends Day implements TwoPartProblem {


    public Day3(String filename) throws IOException {
        super(filename);
    }
    public Character commonChar(String s1, String s2) {
        for(Character c : s1.toCharArray()) {
            if(s2.indexOf(c) >= 0) {
                return c;
            }
        }
        return null;
    }
    public Character commonCharAcross3(String s1, String s2, String s3) {
        for(Character c : s1.toCharArray()) {
            if(s2.indexOf(c) >= 0 && s3.indexOf(c) >= 0) {
                return c;
            }
        }
        return null;
    }

    public int itemValueReturner (Character c){
        int score;
        if (Character.isUpperCase(c)){
            score = c - 'A' + 27;
        }
        else {
            score= c - 'a' + 1;
        }

        return score;
    }

    public Character mistakenItemReturner(String line){
            int middle = line.length() / 2;
            String firstHalf = line.substring(0, middle);
            String secondHalf = line.substring(middle);
            return commonChar(firstHalf, secondHalf);

    }

    public  int part1() {
        int totalScore = 0;
        for (String line: lines){
            Character mistakenItem = mistakenItemReturner(line);
            int itemValue = itemValueReturner(mistakenItem);
            totalScore += itemValue;
        }
        return totalScore;
    }
    public int part2(){
        int totalScore = 0;
        String S1;
        String S2;
        String S3;
        for (int i = 0; i < lines.size(); i ++){
            // catching every third line to determine a group of elves
            if ((i+1) % 3 == 0){
                S3 = lines.get(i);
                S2 = lines.get(i-1);
                S1 = lines.get(i-2);
                Character commonChar = commonCharAcross3(S1, S2, S3);
                totalScore += itemValueReturner(commonChar);
            }

        }
        return totalScore;
    }
}
