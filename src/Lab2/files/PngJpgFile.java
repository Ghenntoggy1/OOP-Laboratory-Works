package Lab2.files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PngJpgFile extends GeneralFile{
    private int width = 0;
    private int height = 0;

    public PngJpgFile(String directoryPath, String fileName, Long lastModifiedDate) {
        super(directoryPath, fileName, lastModifiedDate);
        File file = new File(directoryPath + "\\" + fileName);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            this.height = bufferedImage.getHeight();
            this.width = bufferedImage.getWidth();
        } catch (IOException e) {
            System.out.println("CAN'T READ IMAGE!");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Image size: " + this.width + "x" + this.height;
    }
}
