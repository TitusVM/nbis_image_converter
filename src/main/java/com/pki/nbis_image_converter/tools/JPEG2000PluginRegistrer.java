package com.pki.nbis_image_converter.tools;

import javax.imageio.spi.IIORegistry;

import com.github.jaiimageio.jpeg2000.impl.J2KImageReaderSpi;

public class JPEG2000PluginRegistrer {
    /**
     *  This method registers the JPEG2000 plugin with the ImageIO registry.
     */
    public static void registerJPEG2000Plugin() {
        IIORegistry registry = IIORegistry.getDefaultInstance();
        registry.registerServiceProvider(new J2KImageReaderSpi());
    }
}
