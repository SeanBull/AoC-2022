import java.io.IOException;
import java.util.*;

public class Day11 extends Day implements TwoPartProblem{
    public Day11(String filename) throws IOException {
        super(filename);
    }



    public ArrayList<List<String>> getInput(List<String> lines ) {
        ArrayList<List<String>> input = new ArrayList<>();
        for (String line: lines){
            List<String> list = new ArrayList<>(Arrays.asList(line.split(" ")));
            list.removeIf(String::isEmpty);
            input.add(list);

        }
        return input;
    }

    public List<Monkey> makeMonkeys(ArrayList<List<String>> input, int worryReducer ){
        List<Monkey> monkeys = new LinkedList<>();
        int monkeyNumber = 0;
        String operation = "";
        ArrayList<Long> startingItems = new ArrayList<>();
        int factor = 0;
        int ifFalseMonkey;
        int ifTrueMonkey = 0;
        int testWorry = 0;

        for (List<String> strings : input) {
            if (!(strings.size() < 1)) {
                if (strings.size() > 1) {
                    switch (strings.get(1)) {
                        case ("true:") -> ifTrueMonkey = Integer.parseInt(strings.get(5));
                        case ("false:") -> {
                            ifFalseMonkey = Integer.parseInt(strings.get(5));
                            monkeys.add(new Monkey(
                                    monkeyNumber,
                                    startingItems,
                                    operation,
                                    factor,
                                    ifFalseMonkey,
                                    ifTrueMonkey,
                                    testWorry,
                                    worryReducer
                            ));
                        }
                    }
                }
                switch (strings.get(0)) {
                    case ("Monkey") -> monkeyNumber = Integer.parseInt(String.valueOf(strings.get(1).charAt(0)));
                    case ("Starting") -> {
                        List<String> subString = strings.subList(2, (strings.size()));
                        startingItems.clear();
                        subString.forEach((x) -> startingItems.add(Long.parseLong(x.replace(",", ""))));
                    }
                    case ("Operation:") -> {
                        operation = strings.get(4);
                        if (Objects.equals(strings.get(5), "old")) {
                            operation = "square";
                            factor = 0;
                        } else {
                            factor = Integer.parseInt(strings.get(5));
                        }


                    }
                    case ("Test:") -> testWorry = Integer.parseInt(strings.get(3));

                }
            }


        }
        return monkeys;
    }

    public List<Monkey> doRounds(int rounds, List<Monkey> monkeys){

        while (rounds > 0){
        for (Monkey monkey: monkeys){
            HashMap<Integer, ArrayList<Long>> returnItems = monkey.clearItems();
            returnItems.forEach((k,v) -> monkeys.get(k).addItems(v));
        }
        rounds--;
        }
        return monkeys;
    }

    public long returnMonkeyNumber (List<Monkey> monkeys){
        int[] ordered = new int[monkeys.toArray().length];
        for (int i = 0; i < monkeys.toArray().length; i++){
            Monkey monkey = monkeys.get(i);
            ordered[i]= monkey.monkeyCount;
        }
        Arrays.sort(ordered);
        return (long) ordered[ordered.length - 1] * ordered[ordered.length-2];
    }

    @Override
    public int part1() {
        ArrayList<List<String>> input = getInput(lines);
        List<Monkey> monkeys = makeMonkeys(input, 3);
        monkeys = doRounds(20, monkeys);
        System.out.println("The answer to part 1:" + returnMonkeyNumber(monkeys));
        return 0;
    }
    @Override
    public int part2() {
        ArrayList<List<String>> input = getInput(lines);
        List<Monkey> monkeys = makeMonkeys(input, 0);
        monkeys = doRounds(10000, monkeys);
        System.out.println("The answer to part 2:" + returnMonkeyNumber(monkeys));
        return 0;
    }
}
