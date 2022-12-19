import java.io.IOException;
import java.util.*;

public class Day9 extends Day implements TwoPartProblem{

    public Day9(String filename) throws IOException {
        super(filename);
    }
    public List<String[]> returnSplitLines(List<String> lines){
        List<String[]> splitLines = new LinkedList<>();
        for (String line : lines){
            splitLines.add(line.split(" "));
        }
        return splitLines;
    }

    public boolean doesTailMove(Integer[] head, Integer[] tail){
        return Math.abs(head[0] - tail[0]) > 1 | Math.abs(head[1] - tail[1]) > 1;
    }
    public int moveAxis(int one, int two){
        return Integer.compare(two, one);

    }
    public void addKnots(HashMap<Integer, Integer[]> nineTailsAndHead, int noKnots ){
        for(int i=0; i < noKnots; i++) {
            Integer[] XY = {0,0};
            nineTailsAndHead.put(i, XY);
        }
    }
    public int[][] drawPositions(HashMap<Integer, Integer[]> headAndTails, ArrayList<Integer[]> visits){
        Optional<Integer[]> maxX = visits.stream().max((first, second) -> first[0].compareTo(second[0]));
        Optional<Integer[]> minX = visits.stream().min((first, second) -> first[0].compareTo(second[0]));
        Optional<Integer[]> maxY = visits.stream().max((first, second) -> first[1].compareTo(second[1]));
        Optional<Integer[]> minY = visits.stream().min((first, second) -> first[1].compareTo(second[1]));
        int[][] board = new int[Math.abs(minY.get()[1])+ maxY.get()[1]][Math.abs(minX.get()[0])+ maxX.get()[0]];
        headAndTails.forEach((item,coors)-> board[coors[1]+board.length/2][coors[0]+board[0].length/2] = item );
        for (int[] row: board){
            System.out.println(Arrays.toString(row));
        }
        return board;
    }

    public void moveHead(ArrayList<Integer[]> visits,HashMap<Integer, Integer[]> headAndTails, String direction, int moves) {
        for (int i = 0; i < moves; i++) {
            int newX;
            int newY;
            Integer[] head = headAndTails.get(0);
            switch (direction) {
                case ("R") -> {
                    newX = head[0] + 1;
                    head[0] = newX;
                }
                case ("L") -> {
                    newX = head[0]- 1;
                    head[0] = newX;
                }
                case ("U") -> {
                    newY = head[1]- 1;
                    head[1] = newY;
                }
                case ("D") -> {
                   newY = head[1] + 1;
                    head[1] = newY;
                }
            }

            for (int j = 0; j < headAndTails.size() - 1; j++) {
                Integer[] leadingKnot = headAndTails.get(j);
                Integer[] followingKnot = headAndTails.get(j+1);
                if (doesTailMove(leadingKnot, followingKnot)) {
                    followingKnot[0] = followingKnot[0] + moveAxis(followingKnot[0], leadingKnot[0]);
                    followingKnot[1] = followingKnot[1] + moveAxis(followingKnot[1], leadingKnot[1]);
                }
            }

            Integer[] tail = headAndTails.get(headAndTails.size() -1);
            if (visits.stream().filter((x)-> Arrays.equals(x, tail)).toArray().length==0){
               visits.add(tail.clone());

            }
        }


        }


    public int instructions(List<String[]> input, int noKnots){
        HashMap<Integer, Integer[]> headsAndTails = new HashMap<>();
        addKnots(headsAndTails, noKnots);
        ArrayList<Integer[]> visits = new ArrayList<>();
        for (String[] line: input ){
            moveHead(visits, headsAndTails,line[0], Integer.parseInt(line[1]));
        }
//        drawPositions(headsAndTails, visits);
        return visits.size();
    }
    @Override
    public int part1() {
        List<String[]> input = returnSplitLines(lines);
        return instructions(input, 2);

    }

    @Override
    public int part2() {
        List<String[]> input = returnSplitLines(lines);
        return instructions(input, 10);
    }
}
