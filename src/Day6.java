import java.io.IOException;


public class Day6 extends Day implements TwoPartProblem {
    public Day6(String filename) throws IOException {
        super(filename);
    }

    public final String input = this.lines.get(0);
    private int returnIndex(String input, int length)
    {
        for (int i = 0; i < input.chars().count(); i++)
        {
            if (input.substring(i, i + length).chars().distinct().count() == length)
            {
                return length + i;
            }
        }

        return -1;
    }
    public int part1() {
        return returnIndex(input, 4);
    }
    public int part2() {
        return returnIndex(input, 14);
    }
}
