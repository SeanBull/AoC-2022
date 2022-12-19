import java.io.IOException;
import java.util.*;

public class Day11 extends Day implements TwoPartProblem{
    public Day11(String filename) throws IOException {
        super(filename);
    }



    public ArrayList<List<String>> getInput(List<String> lines ) {
        ArrayList<List<String>> input = new ArrayList<>();
        for (String line: lines){
            List<String> list = new ArrayList<String>(Arrays.asList(line.split(" ")));
            list.removeIf(String::isEmpty);
            input.add(list);

        }
        return input;
    }

    public List<Monkey> makeMonkeys(ArrayList<List<String>> input ){
        List<Monkey> monkeys = new LinkedList<>();
        int monkeyNumber = 0;
        String operation = "";
        ArrayList<Integer> startingItems = new ArrayList<>();
        int factor = 0;
        int ifFalseMonkey = 0;
        int ifTrueMonkey = 0;
        int testWorry = 0;

        for (int i = 0; i <  input.size(); i++){
            System.out.println(input.get(i));
            if(!(input.get(i).size() < 1)) {
                if (input.get(i).size() > 1) {
                    switch (input.get(i).get(1)) {
                        case ("true:") -> {
                            ifTrueMonkey = Integer.parseInt(input.get(i).get(5));
                            break;
                        }
                        case ("false:") -> {
                            ifFalseMonkey = Integer.parseInt(input.get(i).get(5));
                            monkeys.add(new Monkey(
                                    monkeyNumber,
                                    startingItems,
                                    operation,
                                    factor,
                                    ifFalseMonkey,
                                    ifTrueMonkey,
                                    testWorry
                            ));
                        }
                    }
                }
                switch (input.get(i).get(0)) {
                    case ("Monkey") -> {
                        monkeyNumber = Integer.parseInt(String.valueOf(input.get(0).get(1).charAt(0)));
                    }
                    case ("Starting") -> {
                        List<String> subString = input.get(i).subList(2, (input.get(i).size()));
                        subString.forEach((x) -> startingItems.add(Integer.parseInt(String.valueOf(x.charAt(0)))));
                        System.out.println(startingItems);
                    }
                    case ("Operation:") -> {
                        operation = input.get(i).get(4);
                        if (Objects.equals(input.get(i).get(5), "old")){
                            operation = "square";
                            factor = 0;
                        }
                        else{
                            factor = Integer.parseInt(input.get(i).get(5));
                        }


                    }
                    case ("Test:") -> {
                        testWorry = Integer.parseInt(input.get(i).get(3));

                    }

                }
            }


        }
        return monkeys;
    }

    public List<Monkey> doRounds(int rounds, List<Monkey> monkeys){
        while (rounds > 0){
        for (Monkey monkey: monkeys){
            HashMap<Integer, ArrayList<Integer>> returnItems = monkey.clearItems();
            returnItems.forEach((k,v) -> monkeys.get(k).addItems(v));
        }
        rounds--;
        }
        return monkeys;
    }

    public int returnMonkeyNumber (List<Monkey> monkeys){
        int monkey1 = 0;
        int monkey2 = 0;
        for (Monkey monkey: monkeys){
            System.out.println(monkey.monkeyCount);
            if(monkey.monkeyCount > monkey1){
                monkey1 = monkey.monkeyCount;
            }
            else if (monkey.monkeyCount > monkey2){
                monkey2 = monkey.monkeyCount;
            }
        }
        return monkey1 * monkey2;
    }

    @Override
    public int part1() {
        ArrayList<List<String>> input = getInput(lines);
        List<Monkey> monkeys = makeMonkeys(input);
        monkeys = doRounds(20, monkeys);
        return returnMonkeyNumber(monkeys);
    }
}
