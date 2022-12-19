import java.sql.Array;
import java.util.*;

public class Monkey {
    public Queue<Integer> things = new LinkedList<Integer>();
    public int number;
    
    public Monkey(int number,ArrayList<Integer> startingItems, String operation, int factor, int ifFalseMonkey, int ifTrueMonkey, int testWorry){

        this.number = number;
        this.operation = operation;
        this.factor = factor;
        this.ifFalseMonkey = ifFalseMonkey;
        this.ifTrueMonkey = ifTrueMonkey;
        this.testWorry = testWorry;

        this.things.addAll(startingItems);


    }
    ArrayList<Integer> startingItems = new ArrayList<>();
    public String operation;
    public int factor;

    public int ifTrueMonkey;
    public int ifFalseMonkey;

    public int testWorry;

    public int monkeyCount=0;

    public boolean testWorry (int worry){
        if (worry % testWorry == 0 ) return true;
        else return false;
    }
    public int applyOperation(int number){
        if (operation.equals("*")){
            return number * factor;
        }
        else if (operation.equals("square")){
            return number * number;
        }
        else return number + factor;
    }

    public void addItems(ArrayList<Integer> newItems){
        things.addAll(newItems);
    }

    public HashMap<Integer, ArrayList<Integer>> clearItems(){
        HashMap<Integer, ArrayList<Integer>> nextMonkey = new HashMap<>();
        nextMonkey.put(ifTrueMonkey, new ArrayList<>() );
        nextMonkey.put(ifFalseMonkey, new ArrayList<>() );

        while (things.toArray().length > 0){
            monkeyCount++;
            int number = things.remove();
            number = applyOperation(number);
            if (testWorry(number)){
                nextMonkey.get(ifTrueMonkey).add(number/3);
            }
            else{
                nextMonkey.get(ifFalseMonkey).add(number/3);
            }


        }
        return nextMonkey;
    }


}
