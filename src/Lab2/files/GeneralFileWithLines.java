package Lab2.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GeneralFileWithLines extends GeneralFile{

    public GeneralFileWithLines(String directoryPath, String fileFullName, Long lastModificationDate) {
        super(directoryPath, fileFullName, lastModificationDate);
    }

    int findLineCount(String fullPath, int lineCount) {
        File file = new File(fullPath);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (bufferedReader.readLine() != null) {
                lineCount++;
            }
            bufferedReader.close();
        } catch (IOException ignored) {
        }
        return lineCount;
    }
}
