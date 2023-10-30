package Lab2.behavior;

import Lab2.files.GeneralFile;
import Lab2.files.PngJpgFile;
import Lab2.files.PyJavaFile;
import Lab2.files.TxtFile;

public class FilesGenerator {

    private TxtFile generateNewTxtFile(String newDirectoryPath, String newFileName, Long newLastModificationDate) {
        return new TxtFile(newDirectoryPath, newFileName, newLastModificationDate);
    }

    private PngJpgFile generateNewPngJpgFile(String newDirectoryPath, String newFileName, Long newLastModificationDate) {
        return new PngJpgFile(newDirectoryPath, newFileName, newLastModificationDate);
    }

    private PyJavaFile generateNewPyJavaFile(String newDirectoryPath, String newFileName, Long newLastModificationData) {
        return new PyJavaFile(newDirectoryPath, newFileName, newLastModificationData);
    }

    public static String getExtensionTypeFromFilename(String fileName) {
        String[] fileNameSplit = fileName.split("\\.");
        return fileNameSplit[1];
    }

    public GeneralFile generateNewFile(String newDirectoryPath, String newFileName, Long newLastModificationDate) {
        String newExtension = getExtensionTypeFromFilename(newFileName);
        if (newExtension.equals("txt")) {
            return generateNewTxtFile(newDirectoryPath, newFileName, newLastModificationDate);
        }
        else if (newExtension.equals("jpg") || newExtension.equals("png")) {
            return generateNewPngJpgFile(newDirectoryPath, newFileName, newLastModificationDate);
        }
        else if (newExtension.equals("py") || newExtension.equals("java")) {
            return generateNewPyJavaFile(newDirectoryPath, newFileName, newLastModificationDate);
        }
        return null;
    }
}
