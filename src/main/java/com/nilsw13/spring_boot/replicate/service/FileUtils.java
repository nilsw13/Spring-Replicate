package com.nilsw13.spring_boot.replicate.service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for handling file operations related to the Replicate API.
 *
 * <p>This class provides static methods to convert various types of files into
 * data URL format suitable for inclusion in Replicate API requests. Data URLs
 * allow embedding file content directly within JSON payloads without requiring
 * separate file hosting.</p>
 *
 * <p>The service enforces size limits recommended by the Replicate API documentation
 * (256KB maximum) and provides appropriate MIME type detection for different file types.</p>
 *
 * <p>When files exceed the recommended size limit, users are advised to host files
 * externally and provide HTTP URLs instead of using data URLs.</p>
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public final class FileUtils {

    /**
     * Maximum recommended size for files encoded as data URLs.
     *
     * <p>The Replicate API recommends using HTTP URLs rather than data URLs
     * for files larger than 256KB.</p>
     */
    public static final int MAX_DATA_URL_SIZE = 256 * 1024;

    private static final Map<String, String> IMAGE_MIME_TYPES = Map.of(
            ".jpg", "image/jpeg",
            ".jpeg", "image/jpeg",
            ".png", "image/png",
            ".gif", "image/gif",
            ".webp", "image/webp"
    );

    /**
     * Private constructor to prevent instantiation.
     * @throws UnsupportedOperationException if called
     */
    private FileUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Converts a file to a data URL format.
     *
     * <p>This method reads the provided file, determines its MIME type, and
     * encodes its content as a base64 data URL. The format follows the pattern:
     * "data:[MIME type];base64,[encoded content]"</p>
     *
     * <p>If the file's MIME type cannot be determined, it falls back to
     * "application/octet-stream" as a generic binary format.</p>
     *
     * @param file The file to convert to a data URL
     * @return A string containing the file content as a data URL
     * @throws IOException If the file exceeds the maximum size limit (256KB),
     *                    cannot be read, or an error occurs during encoding
     * @throws IllegalArgumentException if the file parameter is null
     */
    public static String fileToDataUrl(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }

        validateFileSize(file);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        String mimeType = Optional.ofNullable(Files.probeContentType(file.toPath()))
                .orElse("application/octet-stream");
        return formatDataUrl(mimeType, fileContent);
    }

    /**
     * Converts an image file to a data URL format with proper image MIME type.
     *
     * <p>This method is specifically optimized for image files and performs
     * additional MIME type detection for common image formats based on file extension
     * if the system cannot determine the type automatically. Supported formats include:
     * JPEG (.jpg, .jpeg), PNG (.png), GIF (.gif), and WebP (.webp).</p>
     *
     * @param imageFile The image file to convert to a data URL
     * @return A string containing the image content as a data URL
     * @throws IOException If the file exceeds the maximum size limit (256KB),
     *                    cannot be read, is not a recognized image format, or
     *                    an error occurs during encoding
     * @throws IllegalArgumentException if the imageFile parameter is null
     */
    public static String imageToDataUrl(File imageFile) throws IOException {
        if (imageFile == null) {
            throw new IllegalArgumentException("Image file cannot be null");
        }

        validateFileSize(imageFile);
        byte[] fileContent = Files.readAllBytes(imageFile.toPath());
        String mimeType = detectImageMimeType(imageFile);
        return formatDataUrl(mimeType, fileContent);
    }

    /**
     * Validates that the file size is within acceptable limits.
     *
     * @param file The file to validate
     * @throws IOException if the file exceeds the maximum allowed size
     * @throws IllegalArgumentException if the file parameter is null
     */
    private static void validateFileSize(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }

        if (file.length() > MAX_DATA_URL_SIZE) {
            throw new IOException(String.format(
                    "File size exceeds maximum allowed size of %dKB. " +
                            "Please host the file externally and provide a URL instead.",
                    MAX_DATA_URL_SIZE / 1024));
        }
    }

    /**
     * Detects the MIME type of an image file based on its extension.
     *
     * @param imageFile The image file to analyze
     * @return The detected MIME type
     * @throws IOException if the file type is not supported
     */
    private static String detectImageMimeType(File imageFile) throws IOException {
        String detectedType = Files.probeContentType(imageFile.toPath());
        if (detectedType != null && detectedType.startsWith("image/")) {
            return detectedType;
        }

        String fileName = imageFile.getName().toLowerCase();
        return IMAGE_MIME_TYPES.entrySet().stream()
                .filter(e -> fileName.endsWith(e.getKey()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new IOException(
                        "Unsupported image format: " + fileName + ". " +
                                "Supported formats are: " + IMAGE_MIME_TYPES.keySet()));
    }

    /**
     * Formats the data URL string.
     *
     * @param mimeType The MIME type of the content
     * @param content The binary content to encode
     * @return The formatted data URL string
     */
    private static String formatDataUrl(String mimeType, byte[] content) {
        return String.format("data:%s;base64,%s",
                mimeType,
                Base64.getEncoder().encodeToString(content));
    }
}