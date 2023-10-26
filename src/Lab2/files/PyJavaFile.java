package Lab2.files;

import java.io.*;

public class PyJavaFile extends GeneralFile{
    private int lineCount = 0;
    private int classCount = 0;
    private int methodCount = 0;
    private String fullPath = this.directoryPath + "\\" + this.fileName;

    public PyJavaFile(String directoryPath, String fileName, Long lastModifiedDate) {
        super(directoryPath, fileName, lastModifiedDate);
        this.lineCount=findLineCount(this.fullPath, 0);
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
        } catch (FileNotFoundException e) {
            System.out.println("CAN'T READ FILE!");
        } catch (IOException e) {
            System.out.println("CAN'T READ LINE!");
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
        } catch (FileNotFoundException e) {
            System.out.println("CAN'T READ FILE!");
        } catch (IOException e) {
            System.out.println("CAN'T READ LINE!");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nLine Count: " + this.lineCount + "\nClass Count: " + classCount + "\nMethod Count: " + methodCount;
    }
}
