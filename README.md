# NBIS Image Converter API

A simple REST API for converting WSQ and JP2 (JPEG 2000) fingerprint images to PNG format.

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

## Requirements
- Java 17+
- Maven

## Notes
- Temporary files are used for conversion and deleted after processing.
- The API returns the converted PNG as a file download.

## License
See [LICENSE](LICENSE) for license information.
