import java.io.IOException;

public class Day2 extends Day implements TwoPartProblem{
    public Day2(String filename) throws IOException {
        super(filename);
    }


    public int resultScore(String[] splitLine){
        //choice
        switch (splitLine[1]) {
            //Rock
            case "X" -> {
                if (splitLine[0].equals("A")){return 3 + 1;}
                else if (splitLine[0].equals("C")){return 6 + 1;}
                return 1;
            }
            //Paper
            case "Y" -> {
                if (splitLine[0].equals("B")){return 3 + 2;}
                else if (splitLine[0].equals("A")){return 6 + 2;}
                return 2;
            }
            //Scissors
            case "Z" -> {
                if (splitLine[0].equals("C")){return 3 + 3;}
                else if (splitLine[0].equals("B")){return 6 + 3;}
                return 3;
            }
            default -> {
                System.out.println("error on choice");
                return 0;
            }
        }
    }


    public int forcedChoiceScore(String[] splitLine){
        //Result
        switch (splitLine[1]) {
            //lose
            case "X" -> {
                if (splitLine[0].equals("A")){return 3;}
                else if (splitLine[0].equals("B")){return 1;}
                return 2;
            }
            //draw
            case "Y" -> {
                if (splitLine[0].equals("A")){return 1 + 3;}
                else if (splitLine[0].equals("B")){return 2 + 3;}
                return 3 + 3;
            }
            //win
            case "Z" -> {
                if (splitLine[0].equals("C")){return 1 + 6;}
                else if (splitLine[0].equals("B")){return 3 + 6;}
                return 2 + 6;
            }
            default -> {
                System.out.println("error on choice");
                return 0;
            }
        }
    }
    public  int part1() {
        int score = 0;
        for (String i : lines){
            String[] splitLine = i.split("\\s+");
            score += resultScore(splitLine);
        }
        return score;
    }
    public int part2(){
        int score = 0;
        for (String i : lines){
            String[] splitLine = i.split("\\s+");
            score += forcedChoiceScore(splitLine);
        }
        return score;
    }


}
