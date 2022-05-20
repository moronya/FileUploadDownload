package file.upload.download;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

// class for uploading file
public class FileUploadUtil {


    public static String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        String folderName = "/home/amos/test-upload";
        Path path = Paths.get(folderName);

        //create directory if it doesnt exist
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }

        String fileKey = RandomStringUtils.randomAlphanumeric(10);

        InputStream inputStream = multipartFile.getInputStream(); //remember file contents are stored as byte streams
        Path filePath = path.resolve(fileKey +"-" + fileName); //resolve method is used to combine the two paths
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileKey;
    }

}
