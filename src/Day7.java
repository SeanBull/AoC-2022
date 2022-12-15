import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
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
    public int returnSmallestDeletion(HashMap<String,Integer> dirsSize, int Size){
        int deletionSize = Size - (70000000- dirsSize.get("/"));
        int smallestFile = 0;
        for (String dir: dirsSize.keySet()){
            if (dirsSize.get(dir) >= deletionSize){
                if(smallestFile == 0){
                    smallestFile = dirsSize.get(dir);
                }
                else if (smallestFile> dirsSize.get(dir)){
                    smallestFile = dirsSize.get(dir);
                }

            }
        }
        return smallestFile;
    }

    public HashMap<String, Integer> readCommandsAndReturnFileSizes(){
        //setting the first level as root which is level 0
        String currentDir= "/";
        int level = 0;
        //initializing a random class to be able to assign unique names to duplicate dir names
        Random random = new Random();
        //A stack to keep on top of the parents of the current directory as any file found will increase the size of all of these.
        Stack<String> parents = new Stack<>();
        //A hash map of all created unique dir names and there sizes
        HashMap<String,Integer> dirsSize = new HashMap<>();

        //Looping through the commands
        for (String line: lines){
            String[] lineArray = line.split(" ");
            //Catching the command lines, and keeping track of the level and current directory
            if (line.contains("$")){
                if (line.contains("cd") && !line.contains("..") && !line.contains("/")){
                    parents.add(currentDir);
                    level ++;
                    currentDir = lineArray[2] + "-" + level + "-" + (char) (random.nextInt(26) + 'a');
                }
                if (line.contains("..")){
                    currentDir = parents.pop();
                    level--;

                }
            }
            //Catching the lines which are files, adding their size to the appropriate hash table entry.
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
        return dirsSize;
    }

    public int part1(){
        return returnSumUnderSize(readCommandsAndReturnFileSizes(), 100000);
    }
    public int part2(){
        return returnSmallestDeletion(readCommandsAndReturnFileSizes(), 30000000);
    }
}
