package org.example;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.io.IOException;

public class Zip4jUtils {
    private static ZipParameters buildZipParameters(CompressionMethod compressionMethod, boolean encrypt,
                                                    EncryptionMethod encryptionMethod, AesKeyStrength aesKeyStrength) {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionMethod(compressionMethod);
        zipParameters.setEncryptionMethod(encryptionMethod);
        zipParameters.setAesKeyStrength(aesKeyStrength);
        zipParameters.setEncryptFiles(encrypt);
        return zipParameters;
    }

    public static void doZip(String input, String output, String password) throws IOException {
        CommonZipUtils.createFolder(output);

        // AES 256 is used by default. You can override it to use AES 128.
        // AES 192 is supported only for extracting.
        ZipParameters zipParameters = buildZipParameters(CompressionMethod.DEFLATE, true, EncryptionMethod.AES, AesKeyStrength.KEY_STRENGTH_256);

        ZipFile zipFile = new ZipFile(output, password.toCharArray());
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
}