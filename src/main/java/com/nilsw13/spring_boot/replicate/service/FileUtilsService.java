package com.nilsw13.spring_boot.replicate.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

/**
 * Utility service for handling file operations related to the Replicate API.
 *
 * This service provides static methods to convert various types of files into
 * data URL format suitable for inclusion in Replicate API requests. Data URLs
 * allow embedding file content directly within JSON payloads without requiring
 * separate file hosting.
 *
 * The service enforces size limits recommended by the Replicate API documentation
 * (256KB maximum) and provides appropriate MIME type detection for different file types.
 *
 * When files exceed the recommended size limit, users are advised to host files
 * externally and provide HTTP URLs instead of using data URLs.
 *
 * @author Nilsw13
 * @since 1.0.0
 */
@Service
public class FileUtilsService {



    /**
     * Maximum recommended size for files encoded as data URLs.
     *
     * The Replicate API recommends using HTTP URLs rather than data URLs
     * for files larger than 256KB.
     */
    public static final int MAX_DATA_URL_SIZE = 256 * 1024;

    /**
     * Converts a file to a data URL format.
     *
     * This method reads the provided file, determines its MIME type, and
     * encodes its content as a base64 data URL. The format follows the pattern:
     * "data:[MIME type];base64,[encoded content]"
     *
     * If the file's MIME type cannot be determined, it falls back to
     * "application/octet-stream" as a generic binary format.
     *
     * @param file The file to convert to a data URL
     * @return A string containing the file content as a data URL
     * @throws IOException If the file exceeds the maximum size limit (256KB),
     *                     cannot be read, or an error occurs during encoding
     */
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


    /**
     * Converts an image file to a data URL format with proper image MIME type.
     *
     * This method is specifically optimized for image files and performs
     * additional MIME type detection for common image formats based on file extension
     * if the system cannot determine the type automatically. Supported formats include:
     * JPEG (.jpg, .jpeg), PNG (.png), GIF (.gif), and WebP (.webp).
     *
     * @param imageFile The image file to convert to a data URL
     * @return A string containing the image content as a data URL
     * @throws IOException If the file exceeds the maximum size limit (256KB),
     *                     cannot be read, is not a recognized image format, or
     *                     an error occurs during encoding
     */
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
                throw new IOException("unknowm image type : " + fileName);
            }
        }
        String base64Encoded = Base64.getEncoder().encodeToString(fileContent);
        return "data:" + mimeType + ";base64," + base64Encoded;
    }
}
