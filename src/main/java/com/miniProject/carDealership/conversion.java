package com.miniProject.carDealership;

import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.*;

public class conversion {
    static File file = new File("image.jpg");
    public WritableImage getImage(InputStream picture) throws IOException {
        while(picture!=null){
            file.createNewFile();
            try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
                int read;
                byte[] bytes = new byte[8192];
                while ((read = picture.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                return javafx.embed.swing.SwingFXUtils.toFXImage(ImageIO.read(file), null);
            }
        }
        return null;
    }
}
