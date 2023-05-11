package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author swrd
 * @version 1.0
 * @date 2023/5/11
 */
public class FileUtils {
    public static void createParentDirectories(String path) throws IOException {
        int idx = path.lastIndexOf(File.separatorChar);
        if (idx != -1) {
            String dir = path.substring(0, idx + 1);
            Files.createDirectories(new File(dir).toPath());
        } else {
            Files.createDirectories(new File(path).toPath());
        }
    }
}
