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

    public int insideTreeChecker(List<Integer[]> treesAsArray){
        int treeCount = 0;
        boolean[][] isViewable = new boolean[lines.size()][lines.get(0).length()];
        // if tree is visible on x-axis
        for ( int y = 1; y < treesAsArray.size() -1; y++){
            int biggestTreeBehindFromStart = treesAsArray.get(y)[0];
            for ( int x = 1; x < lines.get(y).length() -2; x++){
                int currentTree =  treesAsArray.get(y)[x];
                if (currentTree>biggestTreeBehindFromStart){
                    biggestTreeBehindFromStart = currentTree;
                    if (!isViewable[x][y]){
                        treeCount++;
                        isViewable[x][y] = true;
                    }
                }
            }
            int biggestTreeBehindFromEnd = treesAsArray.get(y)[treesAsArray.size()-1];
            for ( int x = treesAsArray.size()-2; x > 0; x--){
//                System.out.println("from end: " + biggestTreeBehindFromEnd);
                int currentTree =  treesAsArray.get(y)[x];
                if (currentTree>biggestTreeBehindFromEnd){
                    biggestTreeBehindFromEnd = currentTree;
                    if (!isViewable[x][y]){
                        treeCount++;

                        isViewable[x][y] = true;
                    }

                }

            }
        }
        for ( int x = 1; x < lines.get(0).length() -2; x++){
            int biggestTreeBehindFromStart = treesAsArray.get(0)[x];
            for (int y = 1; y < treesAsArray.size() -2; y++){
                int currentTree =  treesAsArray.get(y)[x];
                if (currentTree>biggestTreeBehindFromStart){
                    biggestTreeBehindFromStart = currentTree;
                    if (!isViewable[x][y]){
                        treeCount++;
                        isViewable[x][y] = true;
                    }
                }
            }
            int biggestTreeBehindFromEnd = treesAsArray.get(treesAsArray.size()-1)[x];
            for ( int y = treesAsArray.size()-2; y > 0; y--){
                int currentTree =  treesAsArray.get(y)[x];
                if (currentTree>biggestTreeBehindFromEnd){
                    biggestTreeBehindFromEnd = currentTree;
                    if (!isViewable[x][y]) {
                        treeCount++;
                        isViewable[x][y] = true;
                    }
                }
            }
        }
    return treeCount;
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

    @Override
    public int part1() {
        return outsideTreeFinder() + insideTreeChecker(returnTreesAsArray(lines));
    }
    @Override
    public int part2() {
        return insideViewTreeChecker(returnTreesAsArray(lines));
    }
}
