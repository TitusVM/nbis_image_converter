package com.pki.nbis_image_converter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NbisImageConverterApplicationIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void pingEndpointReturnsPong() {
        ResponseEntity<String> response = restTemplate.getForEntity("/nbis-image-converter/api/ping", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("pong", response.getBody());
    }

    @Test
    void wsqToPngConversionReturnsPng() throws IOException {
        File wsqFile = new File("src/test/assets/fingerprint.wsq");
        assertTrue(wsqFile.exists());
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(wsqFile));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<byte[]> response = restTemplate.postForEntity("/nbis-image-converter/api/wsq-png", request, byte[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.IMAGE_PNG, response.getHeaders().getContentType());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void jp2ToPngConversionReturnsPng() throws IOException {
        File jp2File = new File("src/test/assets/face.jp2");
        assertTrue(jp2File.exists());
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(jp2File));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<byte[]> response = restTemplate.postForEntity("/nbis-image-converter/api/jp2-png", request, byte[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.IMAGE_PNG, response.getHeaders().getContentType());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }
}
