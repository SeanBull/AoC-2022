import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

public class Day7 extends Day implements TwoPartProblem{

    public Day7(String filename) throws IOException {
        super(filename);
    }

    public int returnSumUnderSize(HashMap<String,Integer> dirsSize, int Size){
        int totalSize =0;
        for (String dir: dirsSize.keySet()){
            if (dirsSize.get(dir) <= Size){
                totalSize+= dirsSize.get(dir);
            }
        }
        return totalSize;
    }
    HashMap<String,Integer> dirsSize = new HashMap<>();
    public int part1(){
        String currentDir= "/";
        Stack<String> parents = new Stack<>();
        int level = 0;

        for (String line: lines){

            String[] lineArray = line.split(" ");
//            System.out.println("this is the stack: " + parents.toString());
//            System.out.println("this is the current dir " + currentDir);
            if (line.contains("$")){
                if (line.contains("cd") && !line.contains("..") && !line.contains("/")){
                    System.out.println(line);
                    parents.add(currentDir);
                    level ++;
                    currentDir = lineArray[2] + "-" + Integer.toString(level);
                    System.out.println(currentDir);
                }
                if (line.contains("..")){

                    currentDir = parents.pop();
                    level--;
                    System.out.println(".." + level);
                }
            }
            else if (!line.contains("dir")){
                if (!dirsSize.containsKey(currentDir)){
                    dirsSize.put(currentDir,Integer.parseInt(lineArray[0]));
                }
                else{
                    int newValue = dirsSize.get(currentDir) + Integer.parseInt(lineArray[0]);
                    dirsSize.put(currentDir, newValue);
                }
                for (String parent : parents){
                    if (!dirsSize.containsKey(parent)){
                        dirsSize.put(parent,Integer.parseInt(lineArray[0]));
                    }
                    else {
                        int newValue = dirsSize.get(parent) + Integer.parseInt(lineArray[0]);
                        dirsSize.put(parent, newValue);
                    }

                }

            }


        }
        System.out.println(dirsSize);
        return returnSumUnderSize(dirsSize, 100000);
    }
}
