import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 extends Day implements TwoPartProblem{

    public Day1(String filename) throws IOException {
        super(filename);
    }

    // Creation of a function that will take in the input as a List of Strings, accumulate the numbers between blank lines, and returns a sorted List.
    protected List<Integer> sumAndSort(){
        int currentTotal = 0;
        ArrayList<Integer> totals = new ArrayList<>();
        // Loop through and sum the rows between the blank rows.
        for (String line : lines) {
            if (line.isEmpty()) {
                totals.add(currentTotal);
                currentTotal = 0;

            } else {
                currentTotal += Integer.parseInt(line);
            }

        }
        // cheat sort :)
        totals.sort(Collections.reverseOrder());
        return totals;
    }

    public  int part1() {
        return sumAndSort().get(0);
    }
    public int part2(){
        List<Integer> totals = sumAndSort();
        return totals.get(0) + totals.get(1) + totals.get(2);

    }





}
