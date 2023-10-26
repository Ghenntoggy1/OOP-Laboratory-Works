package Lab2.files;

import java.io.*;

public class TxtFile extends GeneralFile {
    private int lineCount = 0;
    private int wordCount = 0;
    private int characterCount = 0;
    private String fullPath = this.directoryPath + "\\" + this.fileName;

    public TxtFile(String directoryPath, String fileName, Long lastModificationDate) {
        super(directoryPath, fileName, lastModificationDate);
        this.lineCount = findLineCount(fullPath, 0);
        findWordCount();
        findCharacterCount();
    }

    private void findCharacterCount() {
        File file = new File(this.fullPath);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.characterCount += line.length();
            }
        } catch (FileNotFoundException e) {
            System.out.println("CAN'T READ FILE!");
        } catch (IOException e) {
            System.out.println("CAN'T READ LINE!");
        }
    }

    private void findWordCount() {
        File file = new File(this.fullPath);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] wordsInLine = line.split("\\s+");
                this.wordCount += wordsInLine.length;
            }
        } catch (FileNotFoundException e) {
            System.out.println("CAN'T READ FILE!");
        } catch (IOException e) {
            System.out.println("CAN'T READ LINE!");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nWord Count: " + this.wordCount + "\nLine Count: " + this.lineCount + "\nCharacter Count: " + this.characterCount;
    }
}
