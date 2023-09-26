package filecompression.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private static final String TEMP_DIR = "temp/"; // Specify your temporary directory

    public File compressFiles(List<MultipartFile> files) {
        try {
        	 String userHome = System.getProperty("user.home");
             String downloadDir = Paths.get(userHome, "Downloads").toString();

             // Create the download directory if it doesn't exist
             File dir = new File(downloadDir);
             if (!dir.exists()) {
                 dir.mkdirs();
             }

             // Create a unique ZIP file name in the download directory
             String zipFileName = Paths.get(downloadDir, "compressed-files.zip").toString();

             try (FileOutputStream fos = new FileOutputStream(zipFileName);
                     ZipOutputStream zipOut = new ZipOutputStream(fos)) {

                    for (MultipartFile file : files) {
                        ZipEntry zipEntry = new ZipEntry(file.getOriginalFilename());
                        zipOut.putNextEntry(zipEntry);
                        zipOut.write(file.getBytes());
                        zipOut.closeEntry();
                    }
                }

            // Return the created ZIP file
            return new File(zipFileName);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
            return null;
        }
    }
}
