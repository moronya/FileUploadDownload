package file.upload.download;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloadUtil {
    private Path availableFile;

    public Resource getFile(String fileKey) throws IOException {
        String folderName = "/home/amos/test-upload";

        Path filePath = Paths.get(folderName);

        Files.list(filePath).forEach(file ->{
            if(file.getFileName().toString().startsWith(fileKey)){
                availableFile = file;
                return;
            }
        });
        if(availableFile != null){
            return new UrlResource(availableFile.toUri());
        }
        return null;
    }

}
