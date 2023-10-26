package Lab2.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class GeneralFile {
    protected String directoryPath;
    protected String fileName;
    protected String extensionType;
    protected Long lastModificationDate;
    protected Long creationDate;

    public GeneralFile(String directoryPath, String fileFullName, Long lastModificationDate) {
        this.directoryPath = directoryPath;
        this.fileName = fileFullName;
        this.extensionType = fileFullName.split("\\.")[1];
        this.lastModificationDate = lastModificationDate;
    }

    public static GeneralFile generateNewFile(String newDirectoryPath, String newFileName, Long newLastModificationDate) {
        String newExtension = getExtensionTypeFromFilename(newFileName);
        if (newExtension.equals("txt")) {
            return generateNewTxtFile(newDirectoryPath, newFileName, newLastModificationDate);
        }
        else if (newExtension.equals("jpg") || newExtension.equals("png")) {
            return generateNewPngJpgFile(newDirectoryPath, newFileName, newLastModificationDate);
        }
        return null;
    }

    private static TxtFile generateNewTxtFile(String newDirectoryPath, String newFileName, Long newLastModificationDate) {
        return new TxtFile(newDirectoryPath, newFileName, newLastModificationDate);
    }

    private static PngJpgFile generateNewPngJpgFile(String newDirectoryPath, String newFileName, Long newLastModificationDate) {
        return new PngJpgFile(newDirectoryPath, newFileName, newLastModificationDate);
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

    public String getExtensionType() {
        return this.extensionType;
    }

    public static String getExtensionTypeFromFilename(String fileName) {
        String[] fileNameSplit = fileName.split("\\.");
        return fileNameSplit[1];
    }

    @Override
    public String toString() {
        return "File Name = '" + fileName + "' \n" +
                "Extension = '." + extensionType + "' \n" +
                "Last Modification Date = " + lastModificationDate + "' \n" +
                "Creation Date = " + creationDate;
    }
}
