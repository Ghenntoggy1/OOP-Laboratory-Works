package Lab2.files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class GeneralFile {
    protected String directoryPath;
    protected String fileName;
    protected String extensionType;
    protected Long lastModificationDate;
    protected Long creationDate;
    protected Long size;

    public GeneralFile(String directoryPath, String fileFullName, Long lastModificationDate) {
        this.directoryPath = directoryPath;
        this.fileName = fileFullName;
        this.extensionType = fileFullName.split("\\.")[1];
        this.lastModificationDate = lastModificationDate;
        setCreationDate();
        setSizeFile();
    }

    public int findLineCount(String fullPath, int lineCount) {
        File file = new File(fullPath);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (bufferedReader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException ignored) {
        }
        return lineCount;
    }

    public void setCreationDate() {
        Path filePath = Paths.get(directoryPath, fileName);
        try {
            BasicFileAttributes metaData = Files.readAttributes(filePath, BasicFileAttributes.class);
            this.creationDate = metaData.creationTime().toMillis();
        } catch (IOException ignored) {
        }
    }

    private void setSizeFile() {
        Path filePath = Paths.get(directoryPath, fileName);
        BasicFileAttributes metaData;
        try {
            metaData = Files.readAttributes(filePath, BasicFileAttributes.class);
            this.size = metaData.size();
        } catch (IOException e) {
            System.out.println("COULDN'T READ FILE SIZE!");
        }
    }

    public Long getCreationDate() {
        return this.creationDate;
    }

    public void setLastModificationDate() {
        Path filePath = Paths.get(directoryPath, fileName);
        try {
            BasicFileAttributes metaData = Files.readAttributes(filePath, BasicFileAttributes.class);
            this.lastModificationDate = metaData.lastModifiedTime().to(TimeUnit.SECONDS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getLastModificationDate() {
        return this.lastModificationDate;
    }

    public String getExtensionType() {
        return this.extensionType;
    }

    @Override
    public String toString() {
        return "File Name: '" + this.fileName + "' \n" +
                "Extension: '." + this.extensionType + "' \n" +
                "Last Modification Date: " + new Timestamp(this.lastModificationDate) + " \n" +
                "Creation Date: " + new Timestamp(this.creationDate) + "\n" +
                "Size: " + this.size + " bytes";
    }
}
