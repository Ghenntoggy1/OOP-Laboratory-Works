package Lab2.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.TimeUnit;

public class GeneralFile {
    protected String directoryPath;
    protected String fileName;
    protected String extensionType;
    protected Long lastModificationDate;
    protected Long creationDate;

    public GeneralFile(String directoryPath, String fileFullName) {
        this.directoryPath = directoryPath;
        this.fileName = fileFullName.split("\\.")[0];
        this.extensionType = fileFullName.split("\\.")[1];
    }

    public void setCreationDate() {
        Path filePath = Paths.get(directoryPath, fileName);
        try {
            BasicFileAttributes metaData = Files.readAttributes(filePath, BasicFileAttributes.class);
            this.creationDate = metaData.creationTime().to(TimeUnit.SECONDS);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    @Override
    public String toString() {
        return "File Name = '" + fileName + "' \n" +
                "Extension = '" + extensionType + "' \n" +
                "Last Modification Date = " + lastModificationDate + "' \n" +
                "Creation Date = " + creationDate;
    }
}
