import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Reader {
    public static List<String> returnLineList(String filename) throws IOException {
        Path p = Paths.get(filename);
        return Files.readAllLines(p, StandardCharsets.UTF_8);
    }

}
