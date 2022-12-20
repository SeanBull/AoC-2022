import org.w3c.dom.ls.LSOutput;

import java.math.BigInteger;
import java.sql.Array;
import java.util.*;

public class Monkey {
    private final int worryReducer;
    public Queue<Long> things = new LinkedList<>();
    public int number;
    
    public Monkey(int number,ArrayList<Long> startingItems, String operation, int factor, int ifFalseMonkey, int ifTrueMonkey, int testWorry, int worryReducer){

        this.number = number;
        this.operation = operation;
        this.factor = factor;
        this.ifFalseMonkey = ifFalseMonkey;
        this.ifTrueMonkey = ifTrueMonkey;
        this.testWorry = testWorry;
        this.worryReducer = worryReducer;

        things.addAll(startingItems);


    }
    ArrayList<Long> startingItems = new ArrayList<>();
    public String operation;
    public int factor;

    public int ifTrueMonkey;
    public int ifFalseMonkey;

    public int testWorry;

    public int monkeyCount=0;

    public boolean testWorry (Long worry){
        return (worry % testWorry) == 0;
    }
    public Long applyOperation(Long number){
        if (operation.equals("*")){
            return number * factor;
        }
        else if (operation.equals("square")){
            return number * number;
        }
        else return number + factor;
    }

    public void addItems(ArrayList<Long> newItems){
        things.addAll(newItems);
    }

    public String toString(){
        return "I am monkey %s \n I am holding items : %s".formatted(number, things);
    }

    public HashMap<Integer, ArrayList<Long>> clearItems(){
        HashMap<Integer, ArrayList<Long>> nextMonkey = new HashMap<>();
        nextMonkey.put(ifTrueMonkey, new ArrayList<>() );
        nextMonkey.put(ifFalseMonkey, new ArrayList<>() );

        while (things.toArray().length > 0){
            monkeyCount++;
            Long number = things.remove();
            if (worryReducer > 0){
                number = applyOperation(number)/worryReducer;
            }
            else{
                number = applyOperation(number) % 9699690;
            }
            if (testWorry(number)){
                nextMonkey.get(ifTrueMonkey).add((number));
            }
            else{
                nextMonkey.get(ifFalseMonkey).add((number));
            }


        }
        return nextMonkey;
    }


}
