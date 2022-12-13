import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Day5 extends Day implements TwoPartProblem {
    private final String[][] instructions;

    public Day5(String filename) throws IOException {
        super(filename);
        this.instructions = readInstructions();
    }
    public List<Stack<String>> returnStacksList(List<String> lines){
        List<Stack<String>> stacks = new ArrayList<>();
        //create empty stacks for each column
        int numberOfColumns = (lines.get(lines.size()-1).length() /4)+1;
        for (int i = 0; i < numberOfColumns; i++){
            stacks.add(i, new Stack<>());
        }
        for (int i = lines.size() -1 ; i > -1; i--){
            String[] splitLineArray = lines.get(i).split("");
            int k = 0;
            for (int j = 1; j < splitLineArray.length; j+=4){
                if (!splitLineArray[j].isEmpty() & !splitLineArray[j].isBlank() ){
                stacks.get(k).add(splitLineArray[j]);

                }
                k++;
            }
        }
        return stacks;

    }

    public String[][] readInstructions() throws IOException {
        List<String> instructions = readFile("resources/day5-instructions.txt");
        String[][] returningInstructions = new String[instructions.size()][];

        for (int i = 0; i < instructions.size(); i++){
            String line = instructions.get(i);
            line = line.replaceAll("move ", "");
            line = line.replaceAll(" from ", ",");
            line = line.replaceAll(" to ", ",");
            returningInstructions[i] = line.split(",");
        }
        return returningInstructions;
    }

    public List<Stack<String>> doInstruction(String[][] inputInstructions, List<Stack<String>> stacks){
        for (String[] inputInstruction : inputInstructions) {
            int number = Integer.parseInt(inputInstruction[0]);
            int start = Integer.parseInt(inputInstruction[1]) - 1;
            int end = Integer.parseInt(inputInstruction[2]) - 1;
            for (int j = 0; j < number; j++) {

                String movingItem = stacks.get(start).pop();
                stacks.get(end).add(movingItem);
            }
        }
        return stacks;
    }

    public List<Stack<String>> doInstruction9001(String[][] inputInstructions, List<Stack<String>> stacks){
        for (String[] inputInstruction : inputInstructions) {
            int number = Integer.parseInt(inputInstruction[0]);
            int start = Integer.parseInt(inputInstruction[1]) - 1;
            int end = Integer.parseInt(inputInstruction[2]) - 1;
            int top = stacks.get(start).size();
            int bottom = top - number;
            List<String> moveBlock = stacks.get(start).subList(bottom, top);
            stacks.get(end).addAll(moveBlock);
            for (int j = 0; j < number; j++) {
                stacks.get(start).pop();
            }
        }
        return stacks;
    }

    public void printMessage(List<Stack<String>> stacks){
        for (Stack<String> stack : stacks){
            System.out.print(stack.peek());
        }
        System.out.println("");
    }



    public int part1() {
        List<Stack<String>> stacks = returnStacksList(lines);
        stacks = doInstruction(this.instructions, stacks);
        System.out.println("Answer to Part 1: ");
        printMessage(stacks);


        return 0;
    }

    public int part2() {
        List<Stack<String>> stacks = returnStacksList(lines);
        stacks = doInstruction9001(this.instructions, stacks);
        System.out.println("Answer to Part 2: ");
        printMessage(stacks);


        return 0;
    }
}
