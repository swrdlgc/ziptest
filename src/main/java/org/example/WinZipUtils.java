package org.example;

import de.idyl.winzipaes.AesZipFileDecrypter;
import de.idyl.winzipaes.AesZipFileEncrypter;
import de.idyl.winzipaes.impl.AESDecrypterBC;
import de.idyl.winzipaes.impl.AESEncrypterBC;
import de.idyl.winzipaes.impl.ExtZipEntry;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

/**
 * @author swrd
 * @version 1.0
 * @date 2023/5/9
 */
public class WinZipUtils {
    public static void encrypt(String input, String output, String password) throws IOException {
        if (password != null && !password.isEmpty()) {
            AesZipFileEncrypter.zipAndEncryptAll(new File(input), new File(output), password, new AESEncrypterBC());
        }
    }

    public static void doZip(String input, String output, String password) throws IOException {
        FileUtils.createParentDirectories(output);
        AesZipFileEncrypter azfe = new AesZipFileEncrypter(new File(output), new AESEncrypterBC());
        List<File> list = Files.walk(new File(input).toPath()).map(Path::toFile).collect(Collectors.toList());
        for (File file : list) {
            if (file.isFile()) {
                azfe.add(file, password);
            } else {
                //azfe.add(file.getPath(), new ByteArrayInputStream(new byte[0]), password);
            }
        }
        azfe.close();
    }

    public static void doUnzip(String input, String output, String password) throws IOException, DataFormatException {
        FileUtils.createParentDirectories(output);
        AesZipFileDecrypter azfd = new AesZipFileDecrypter(new File(input), new AESDecrypterBC());
        List<ExtZipEntry> list = azfd.getEntryList();
        for (ExtZipEntry entry : list) {
            String name = output + File.separatorChar + entry.getName();
            FileUtils.createParentDirectories(name);
            if (entry.isDirectory()) continue;
            azfd.extractEntry(entry, new File(name), password);
        }
    }
}
