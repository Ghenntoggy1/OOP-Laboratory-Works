package Lab2.files;

import java.io.*;

public class TxtFile extends GeneralFileWithLines {
    private int lineCount;
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
            bufferedReader.close();
        } catch (IOException ignored) {
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
            bufferedReader.close();
        } catch (IOException ignored) {
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nWord Count: " + this.wordCount + "\nLine Count: " + this.lineCount + "\nCharacter Count: " + this.characterCount;
    }
}
