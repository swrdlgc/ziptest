package org.example;

import de.idyl.winzipaes.AesZipFileEncrypter;
import de.idyl.winzipaes.impl.AESEncrypterBC;

import java.io.File;
import java.io.IOException;

/**
 * @author swrd
 * @version 1.0
 * @date 2023/5/9
 */
public class WinZipUtils {
    public static void doZip(String input, String output, String password) throws IOException {
        String tmpName = output + ".tmp";
        CommonZipUtils.doZip(input, tmpName);
        File tmpFile = new File(tmpName);
        if(password != null && !password.isEmpty()) {
            AesZipFileEncrypter.zipAndEncryptAll(tmpFile, new File(output), password, new AESEncrypterBC());
            tmpFile.delete();
        }
    }

    public static void doUnzip(String input, String output, String password) throws IOException {
        Zip4jUtils.doUnzip(input, output, password);
    }
}
