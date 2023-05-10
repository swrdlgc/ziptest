package org.example;

import de.idyl.winzipaes.AesZipFileDecrypter;
import de.idyl.winzipaes.AesZipFileEncrypter;
import de.idyl.winzipaes.impl.AESDecrypterBC;
import de.idyl.winzipaes.impl.AESEncrypterBC;
import de.idyl.winzipaes.impl.ExtZipEntry;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * @author swrd
 * @version 1.0
 * @date 2023/5/9
 */
//Require CommonZipUtils
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

    public static void doUnzip(String input, String output, String password) throws IOException, DataFormatException {
        CommonZipUtils.createFolder(output);
        AesZipFileDecrypter azfd = new AesZipFileDecrypter(new File(input), new AESDecrypterBC());
        List<ExtZipEntry> list = azfd.getEntryList();
        for(ExtZipEntry entry : list) {
            CommonZipUtils.createFolder(entry.getName());
            if(entry.isDirectory()) continue;
            azfd.extractEntry(entry, new File(entry.getName()), password);
        }
    }
}
