package org.example;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.io.IOException;

public class Zip4jUtils {
    public static void doZip(String input, String output, String password) throws IOException {
        FileUtils.createParentDirectories(output);
        ZipFile zipFile;

        ZipParameters zipParameters = new ZipParameters();
        //zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
        if (password != null && !password.isEmpty()) {
            zipFile = new ZipFile(output, password.toCharArray());

            zipParameters.setEncryptFiles(true);
            zipParameters.setEncryptionMethod(EncryptionMethod.AES);
            //zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
            // AES 256 is used by default. You can override it to use AES 128.
            // AES 192 is supported only for extracting.
        } else {
            zipFile = new ZipFile(output);
        }

        File file = new File(input);
        if (file.isDirectory()) {
            zipFile.addFolder(file, zipParameters);
        } else {
            zipFile.addFile(file, zipParameters);
        }
    }

    public static void doUnzip(String input, String output, String password) throws IOException {
        new ZipFile(input, password.toCharArray()).extractAll(output);
    }

    public static void doZip(String input, String output) throws IOException {
        doZip(input, output, "");
    }

    public static void doUnzip(String input, String output) throws IOException {
        doUnzip(input, output, "");
    }
}