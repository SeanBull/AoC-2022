import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day1 extends Day implements TwoPartProblem{

    public Day1(String filename) throws IOException {
        super(filename);
    }

    public  int part1() {
        int biggestTotal = 0;
        int currentTotal = 0;
        for (String line : lines) {
            if (line.isEmpty()) {
                if (currentTotal > biggestTotal) {
                    biggestTotal = currentTotal;
                }
                currentTotal = 0;
            } else {
                currentTotal += Integer.parseInt(line);
            }
        }
        return biggestTotal;
    }
    public int part2(){
        int currentTotal = 0;
        ArrayList<Integer> totals = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty()) {
                totals.add(currentTotal);
                currentTotal = 0;

            } else {
                currentTotal += Integer.parseInt(line);
            }

        }
        totals.sort(Collections.reverseOrder());
        return totals.get(0) + totals.get(1) + totals.get(2);

    }





}
