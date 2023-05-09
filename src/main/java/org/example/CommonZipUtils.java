package org.example;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;

public class CommonZipUtils {

    public static void createFolder(String path) throws IOException {
        int idx = path.lastIndexOf(File.separatorChar);
        if (idx != -1) {
            String dir = path.substring(0, idx + 1);
            Files.createDirectories(new File(dir).toPath());
        } else {
            Files.createDirectories(new File(path).toPath());
        }
    }

    private static void doZip(File file, ZipArchiveOutputStream zaos) throws IOException {
        if (file.isDirectory()) {
            ZipArchiveEntry zae = new ZipArchiveEntry(file, file.getPath());
            zaos.putArchiveEntry(zae);
            zaos.closeArchiveEntry();
            return;
        }

        ZipArchiveEntry zae = new ZipArchiveEntry(file, file.getPath());
        try (FileInputStream fis = new FileInputStream(file)) {
            zaos.putArchiveEntry(zae);
            IOUtils.copy(fis, zaos);
            zaos.closeArchiveEntry();
        }
    }

    public static void doZip(String input, String output) throws IOException {
        createFolder(output);
        try (ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(new FileOutputStream(output))) {
            //zaos.setEncoding();
            //zaos.setUseZip64(Zip64Mode.AsNeeded);
            zaos.setLevel(Deflater.BEST_SPEED);
            zaos.setMethod(ZipEntry.DEFLATED);
            zaos.setComment("create by java");

            List<File> list = Files.walk(new File(input).toPath()).map(Path::toFile).collect(Collectors.toList());
            for (File f : list) {
                doZip(f, zaos);
            }

            zaos.finish();
        }
    }

    public static void doUnzip(String input, String output) throws IOException {
        try (ZipArchiveInputStream zais = new ZipArchiveInputStream(
                new BufferedInputStream(new FileInputStream(input)))) {
            ZipArchiveEntry zae;
            while ((zae = zais.getNextZipEntry()) != null) {
                String name = output + File.separatorChar + zae.getName();
                createFolder(name);
                if (zae.isDirectory()) continue;
                IOUtils.copy(zais, new FileOutputStream(name));
            }
        }
    }
}