import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day8 extends Day implements TwoPartProblem{

    public Day8(String filename) throws IOException {
        super(filename);
    }

    public int outsideTreeFinder(){
        int width = lines.get(0).length();
        int length = lines.size();
        return width * 2 + length * 2 - 4;
    }
    public List<Integer[]> returnTreesAsArray(List<String> lines){
        List<Integer[]> treesAsArray = new LinkedList<>();

        for (String line : lines){
            treesAsArray.add(Arrays.stream(line.split("")).map(Integer::parseInt).toArray(Integer[]::new));
        }
        return treesAsArray;
    }

    public int scoreAbove(int x, int y, List<Integer[]> treesArray){
        int score = 0;

        for (int i = (y - 1); i> -1; i-- ){

            if (treesArray.get(y)[x] > treesArray.get(i)[x]){
                score++;
            }
            else{
                return score + 1;
            }
        }

        return score;
    }
    public int scoreBelow(int x, int y, List<Integer[]> treesArray){
        int score = 0;

        for (int i = y + 1; i< treesArray.size(); i++ ){

            if (treesArray.get(y)[x] > treesArray.get(i)[x]){
                score++;
            }
            else{
                return score + 1;
            }
        }

        return score;
    }
    public int scoreRight(int x, int y, List<Integer[]> treesArray){
        int score = 0;

        for (int i = x + 1; i< treesArray.get(y).length; i++ ){

            if (treesArray.get(y)[x] > treesArray.get(y)[i]){
                score++;
            }
            else{
                return score + 1;
            }
        }

        return score;
    }
    public int scoreLeft(int x, int y, List<Integer[]> treesArray){
        int score = 0;

        for (int i = x - 1; i> -1; i-- ){
            if (treesArray.get(y)[x] > treesArray.get(y)[i]){
                score++;
            }
            else{
                return score+1;
            }
        }

        return score;
    }

    public boolean viewAbove(int x, int y, List<Integer[]> treesArray){
        int currentTree = treesArray.get(y)[x];
        for (int i = (y - 1); i> -1; i-- ){
            if (currentTree <= treesArray.get(i)[x]){
                return false;
            }
        }
        return true;
    }



    public boolean viewBelow(int x, int y, List<Integer[]> treesArray){
        int currentTree = treesArray.get(y)[x];
        for (int i = y + 1; i< treesArray.size(); i++ ){
                if (currentTree <= treesArray.get(i)[x]){
                    return false;
                }
        }
        return true;
    }

    public boolean viewRight(int x, int y, List<Integer[]> treesArray){
        int currentTree = treesArray.get(y)[x];
        for (int i = x + 1; i< treesArray.get(y).length ; i++ ){

            if (currentTree <= treesArray.get(y)[i]){
                return false;
            }
        }
        return true;
    }
    public boolean viewLeft(int x, int y, List<Integer[]> treesArray){
        int currentTree = treesArray.get(y)[x];
        for (int i = x - 1; i> -1; i-- ){
            if (currentTree <= treesArray.get(y)[i]){
                return false;
            }
        }
        return true;
    }
    public int insideViewTreeChecker(List<Integer[]> treesAsArray){
        int highestScore = 0;
        for ( int y = 0; y < treesAsArray.size(); y++){
            for ( int x = 0; x < lines.get(0).length() -1; x++){
                int score = scoreAbove(x, y, treesAsArray) * scoreBelow(x, y, treesAsArray) * scoreLeft(x, y, treesAsArray) * scoreRight(x, y, treesAsArray);
                if (score>highestScore){
                    highestScore = score;
                }
            }
        }


        return highestScore;
    }
    public int treeCounter(List<Integer[]> treesAsArray){
        int treeCount = 0;
        for ( int y = 1; y < treesAsArray.size() -1 ; y++){
            for ( int x = 1; x < lines.get(0).length() -1; x++){
                if (viewAbove(x, y, treesAsArray) | viewBelow(x, y, treesAsArray)
                | viewRight(x, y, treesAsArray) | viewLeft(x, y, treesAsArray)){
                    treeCount++;
                }
            }
        }


        return treeCount;
    }

    @Override
    public int part1() {
        return outsideTreeFinder() + treeCounter(returnTreesAsArray(lines));

    }
    @Override
    public int part2() {
        return insideViewTreeChecker(returnTreesAsArray(lines));
    }
}
