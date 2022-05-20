package file.upload.download;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class FileDownloadController {
    @GetMapping("/downloadFile/{fileKey}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileKey") String fileKey){
        log.info("Request to download file : {}", fileKey);

        FileDownloadUtil fileDownloadUtil = new FileDownloadUtil();

        Resource resource = null;

        try {
            resource = fileDownloadUtil.getFile(fileKey);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(resource == null){
            return new ResponseEntity<>("File Not Found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);

    }
}
