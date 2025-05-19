package com.pki.nbis_image_converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pki.nbis_image_converter.tools.JPEG2000PluginRegistrer;

@SpringBootApplication
public class NbisImageConverterApplication {

	public static void main(String[] args) {
		// Register the JPEG2000 plugin with the ImageIO registry
		// This is necessary for the ImageIO library to handle JPEG2000 images.
        JPEG2000PluginRegistrer.registerJPEG2000Plugin();
		SpringApplication.run(NbisImageConverterApplication.class, args);
	}
}
