import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.objectsNotPrimitives.lab.Surface;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        Test test = new Test();
        List<Surface> surfaceList = new ArrayList<>();

        surfaceList.add(new Surface(1, 64.03,6.5, 1.615193));
        surfaceList.add(new Surface(2, -121.55, 1.85, 1));
        surfaceList.add(new Surface(3, -105.74, 2.95, 1.734294));
        surfaceList.add(new Surface(4, 855.4, 20, 1));
        surfaceList.add(new Surface(5, 25.24, 9.15, 1.518296));
        surfaceList.add(new Surface(6, 20, 0, 1));

        try (FileWriter writer = new FileWriter("src/main/resources/param.txt", false)) {
            String resString = test.listToString(surfaceList);
            writer.write(resString);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String surfaceToJson(Surface surface) {
        try {
            return mapper.writeValueAsString(surface);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String listToString(List<Surface> list) {
        return list.stream().map(this::surfaceToJson).collect(Collectors.joining(System.lineSeparator()));
    }
}
