package com.pki.nbis_image_converter.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pki.nbis_image_converter.tools.Converter;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/nbis-image-converter/api")
public class ApiController {
    
    /**
     * Simple ping endpoint to check if the server is running.
     * @return A simple "pong" response.
    */
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    /**
     * Route to receive a WSQ file and convert it to PNG.
     * @param file The WSQ file to be converted.
     * @return The converted PNG file.
     * @throws IOException If an error occurs during file conversion.
    */
    @PostMapping("/wsq-png")
    public ResponseEntity<byte[]> convertWsqToPng(@RequestParam("file") MultipartFile file) throws IOException {
        // Turn the MultipartFile into a File
        File wsq = File.createTempFile("temp", ".wsq");
        file.transferTo(wsq);

        // Convert the WSQ file to PNG
        String pngFilePath = Converter.convertWsqToPng(wsq);
        File pngFile = new File(pngFilePath);
        byte[] pngBytes = Files.readAllBytes(pngFile.toPath());

        // IMPORTANT Delete the temporary files
        pngFile.delete();
        wsq.delete();

        // Set the headers for the response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentDispositionFormData("attachment", pngFile.getName());
        headers.setContentLength(pngBytes.length);
        
        // Return the PNG file as a response entity
        return ResponseEntity.ok()
                .headers(headers)
                .body(pngBytes);
    }

    /**
     * Route to receive a JP2 file and convert it to PNG.
     * @param file The JP2 file to be converted.
     * @return The converted PNG file.
     * @throws IOException If an error occurs during file conversion.
     */
    @PostMapping("/jp2-png")
    public ResponseEntity<byte[]> convertJp2ToPng(@RequestParam("file") MultipartFile file) throws IOException {
        // Turn the MultipartFile into a File
        File jp2 = File.createTempFile("temp", ".jp2");
        file.transferTo(jp2);

        // Convert the JP2 file to PNG
        String pngFilePath = Converter.convertJp2ToPng(jp2);
        File pngFile = new File(pngFilePath);
        byte[] pngBytes = Files.readAllBytes(pngFile.toPath());

        // IMPORTANT Delete the temporary files
        pngFile.delete();
        jp2.delete();

        // Set the headers for the response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentDispositionFormData("attachment", pngFile.getName());
        headers.setContentLength(pngBytes.length);
        
        // Return the PNG file as a response entity
        return ResponseEntity.ok()
                .headers(headers)
                .body(pngBytes);
    }
}
