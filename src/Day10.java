import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Day10 extends Day implements TwoPartProblem{
    public Day10(String filename) throws IOException {
        super(filename);
    }
    public List<String[]> returnSplitLines(List<String> lines){
        List<String[]> splitLines = new LinkedList<>();
        for (String line : lines){
            splitLines.add(line.split(" "));
        }
        return splitLines;
    }
    public int returnOnTick(int endTick,  List<String[]> lines ){
    int X = 1;
    int tick = 1;
    //looping each command
        for (String[] line : lines) {
            //if no operation do nothing and add to tick
            if (line[0].equals("noop")) {
                tick++;
                //if addition decide if there are enough ticks for operation to happen and then compute or end the command block
            } else {
                if (tick + 2 < endTick + 1) {
                    X = X + Integer.parseInt(line[1]);
                } else break;
                tick += 2;
            }
        }

    return X;
    }

    public int returnDrawing( List<String[]> lines ){

        //drawing our tv screen and also setting the initial row
        String[][] TV = new String[6][40];
        int row = 0;

        //a little block to adjust i for which row we are on ie 40 positions on each row and 6 rows
        for (int i = 0; i < 240; i++){
            if (i % 40 == 0 && !(i ==0)){
                row++;
                if (row == 6){
                    row =0;
                }

            }
            //return the position of the sprite on the current tick (ie i+1)
            int position = returnOnTick(i+1, lines);
            //total position of sprite
            int cathEnd = position + 1;
            int cathStart = position -1;
            int realI = (i - (row*40));
            //is in position of sprite?
            if(cathEnd >= realI && cathStart <= realI){
                TV[row][(realI)] = "#";
            }
            else{
                TV[row][(realI)] = ".";
            }
        }
        //draw output at the end
        for (String[] line: TV){
            System.out.println(" ");
            for (String pic : line){
                System.out.print(pic);
            }
        }

        return 0;
    }

    @Override
    public int part1() {
        List<String[]> input = returnSplitLines(lines);

        return returnOnTick(20, input) * 20
                +returnOnTick(60, input) * 60
                +returnOnTick(100, input) * 100
                +returnOnTick(140, input) * 140
                +returnOnTick(180, input) * 180
                +returnOnTick(220, input) * 220
                ;
    }

    @Override
    public int part2() {
        List<String[]> input = returnSplitLines(lines);
        return returnDrawing(input);
    }
}
