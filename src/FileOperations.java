import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileOperations {

    public static String loadTxtAndMerge(String path){
        StringBuilder merged_string = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            String line = "X";

            while ((line = bufferedReader.readLine()) != null) {

                if (!line.isEmpty()){
                    merged_string.append(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading file !" + e.getMessage());
            e.printStackTrace();
        }
        return merged_string.toString();
    }

}
