package com.nilsw13.spring_replicate.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Service
public class FileUtilsService {



    public static final int MAX_DATA_URL_SIZE = 256 * 1024;


    public static  String fileToDataUrl(File file) throws IOException {
        if (file.length() > MAX_DATA_URL_SIZE) {
            throw new IOException("File size is too big , you need to host it somewhere and pass the Url instead");
        }

        byte[] fileContent = Files.readAllBytes(file.toPath());
        String mimeType = Files.probeContentType(file.toPath());

        if (mimeType ==  null) {
            mimeType = "application/octet-stream";
        }
        String base64Encoded = Base64.getEncoder().encodeToString(fileContent);
        return "data:" + mimeType + ";base64," + base64Encoded;
    }

    public static String imageToDataUrl(File imageFile) throws IOException {
        if (imageFile.length() > MAX_DATA_URL_SIZE) {
            throw new IOException("Image size is too big , you need to host it somewhere and pass the Url instead");
        }

        byte[] fileContent = Files.readAllBytes(imageFile.toPath());
        String mimeType = Files.probeContentType(imageFile.toPath());
        if (mimeType == null || !mimeType.startsWith("image/")) {
            String fileName = imageFile.getName().toLowerCase();
            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                mimeType = "image/jpeg";
            } else if (fileName.endsWith(".png")) {
                mimeType = "image/png";
            } else if (fileName.endsWith(".gif")) {
                mimeType = "image/gif";
            } else if (fileName.endsWith(".webp")) {
                mimeType = "image/webp";
            } else {
                throw new IOException("Type d'image non reconnu : " + fileName);
            }
        }
        String base64Encoded = Base64.getEncoder().encodeToString(fileContent);
        return "data:" + mimeType + ";base64," + base64Encoded;
    }
}
