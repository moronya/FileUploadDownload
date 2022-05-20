package file.upload.download;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class FileUploadController {
    @PostMapping("/uploadfile")
    public ResponseEntity<FileDomain> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        log.info("Request to upload file : {}", multipartFile.getOriginalFilename());
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        Long size = multipartFile.getSize();

        String fileCode = FileUploadUtil.saveFile(fileName, multipartFile);

        FileDomain fileDomain = new FileDomain();

        fileDomain.setFileName(fileName);
        fileDomain.setFileSize(size);
        fileDomain.setDownloadURI("/downloadFile/"+fileCode);

        return  new ResponseEntity<>(fileDomain, HttpStatus.OK);
    }
}
