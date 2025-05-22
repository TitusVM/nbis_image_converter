# NBIS Image Converter API

A simple REST API for converting WSQ and JP2 (JPEG 2000) fingerprint images to PNG format.


## Install and run

1. **Install Java 21**  
   Download and install [OpenJDK 21](https://adoptium.net/temurin/releases/?version=21).

2. **Clone the repository**  
   ```powershell
   git clone git@github.com:TitusVM/nbis_image_converter.git
   cd nbis_image_converter
   ```

3. **Build the project**  
   ```powershell
   .\mvnw.cmd clean package
   ```

4. **Run the Spring Boot app**  
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

5. **Access the API**  
   The server will start at [http://localhost:8080/nbis-image-converter/api/ping](http://localhost:8080/nbis-image-converter/api/ping).

**Note:**  
- Make sure you have an internet connection for Maven to download dependencies on first run.
- For Windows, use mvnw.cmd; for Linux/macOS, use mvnw.

## Endpoints

### 1. Health Check
- **GET** `/nbis-image-converter/api/ping`
  - Returns: `pong`

### 2. Convert WSQ to PNG
- **POST** `/nbis-image-converter/api/wsq-png`
  - Form field: `file` (WSQ file, `multipart/form-data`)
  - Returns: PNG image as a file download

### 3. Convert JP2 to PNG
- **POST** `/nbis-image-converter/api/jp2-png`
  - Form field: `file` (JP2 file, `multipart/form-data`)
  - Returns: PNG image as a file download

## Usage Example (cURL)

```
curl -X POST http://localhost:8080/nbis-image-converter/api/wsq-png \
  -F "file=@/path/to/your/file.wsq" --output output.png

curl -X POST http://localhost:8080/nbis-image-converter/api/jp2-png \
  -F "file=@/path/to/your/file.jp2" --output output.png
```

## CORS
- CORS is enabled for `http://localhost:5173` by default.

## Notes
- Temporary files are used for conversion and deleted after processing.
- The API returns the converted PNG as a file download.

## License
See [LICENSE](LICENSE) for license information.
