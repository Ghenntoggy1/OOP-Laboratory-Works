package Lab2.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PyJavaFile extends GeneralFileWithLines{
    private int lineCount;
    private int classCount = 0;
    private int methodCount = 0;
    private String fullPath = this.directoryPath + "\\" + this.fileName;

    public PyJavaFile(String directoryPath, String fileName, Long lastModifiedDate) {
        super(directoryPath, fileName, lastModifiedDate);
        this.lineCount = findLineCount(this.fullPath, 0);
        findClassCount();
        findMethodCount();
    }

    private void findClassCount() {
        File file = new File(this.fullPath);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (extensionType.equals("py")) {
                    if (line.contains("class")) {
                        classCount++;
                    }
                }
                else if (extensionType.equals("java")) {
                    if (line.contains("class")) {
                        classCount++;
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException ignored) {
        }
    }

    private void findMethodCount() {
        File file = new File(this.fullPath);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (extensionType.equals("py")) {
                    if (line.contains("def")) {
                        methodCount++;
                    }
                }
                else if (extensionType.equals("java")) {
                    if ((line.contains("public") || line.contains("package") || line.contains("private")) && !line.contains("class") && line.contains("{")) {
                        methodCount++;
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException ignored) {
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nLine Count: " + this.lineCount + "\nClass Count: " + classCount + "\nMethod Count: " + methodCount;
    }
}
