import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day {
    //A super class for each day to extend. Will give a simple constructor with a reader for the input data.
    public Day(String filename) throws IOException {
        this.lines = Reader.returnLineList(filename);
    }
    public  List<String> lines;

    public void setLines(String filename) throws IOException {
        Path p = Paths.get(filename);
        lines = Files.readAllLines(p, StandardCharsets.UTF_8);
    }





}
