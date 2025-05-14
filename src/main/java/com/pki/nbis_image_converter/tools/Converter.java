package com.pki.nbis_image_converter.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import org.jnbis.api.Jnbis;

public class Converter {
    /**
     * Converts a WSQ file to PNG format.
     * @param wsq The WSQ file.
     * @return The converted PNG file.
     * @throws IOException 
     */
    public static String convertWsqToPng(File wsq) throws IOException {
        // Save the WSQ file to a temporary location
        File tempWsq = File.createTempFile("temp", ".wsq");
        Files.copy(wsq.toPath(), tempWsq.toPath(), StandardCopyOption.REPLACE_EXISTING);

        String tempWsqPath = tempWsq.getAbsolutePath();
        String tempPngPath = tempWsqPath.replace(".wsq", ".png");

        // Decode the WSQ file to PNG
        File png = Jnbis.wsq()
                .decode(tempWsqPath)
                .toPng()
                .asFile(tempPngPath);

        System.out.println("PNG file created at: " + png.getAbsolutePath());
        System.out.println("PNG file size: " + png.length() + " bytes");

        // Delete the temporary WSQ file
        tempWsq.delete();

        // Return the path to the converted PNG file
        return png.getAbsolutePath();
    }

    /**
     * Converts a JP2 file to PNG format.
     * @param jp2 The JP2 file.
     * @return The converted PNG file.
     * @throws IOException 
     */
    public static String convertJp2ToPng(File jp2) throws IOException {
        JPEG2000PluginRegistrer.registerJPEG2000Plugin();

        BufferedImage image = ImageIO.read(jp2);

        if (image != null) {
            // Save the image as PNG
            String pngFilePath = jp2.getAbsolutePath().replace(".jp2", ".png");
            File pngFile = new File(pngFilePath);
            ImageIO.write(image, "png", pngFile);

            System.out.println("PNG file created at: " + pngFile.getAbsolutePath());
            System.out.println("PNG file size: " + pngFile.length() + " bytes");

            return pngFile.getAbsolutePath();
        } else {
            throw new IOException("Failed to read the JPEG2000 file.");
        }
    }
}
