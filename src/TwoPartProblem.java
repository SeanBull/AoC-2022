public interface TwoPartProblem {


    default int part1(){
        return 0;
    }
     default int part2(){
         return 0;
     }
     default void sOutPart2(){
        System.out.println("The answer to part 2: " + part2());
    }
      default void sOutPart1(){
        System.out.println("The answer to part 1: " + part1());
    }
}
