import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileOperations {

    public static String loadTxtAndMerge(String path){
        String merged_string = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            String line = "X";

            while ((line = bufferedReader.readLine()) != null) {

                if (!line.isEmpty()){
                    merged_string = merged_string + line;
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading file !" + e.getMessage());
            e.printStackTrace();
        }
        return merged_string;
    }

}
