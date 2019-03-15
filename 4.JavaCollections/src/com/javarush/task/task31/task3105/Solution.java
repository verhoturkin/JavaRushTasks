package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path filename = Paths.get(args[0]);
        Path zipFile = Paths.get(args[1]);
        Map<String, ByteArrayOutputStream> buffer = new HashMap<>();


        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                String file = zipEntry.getName();
                if (!file.equals(filename.getFileName())) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buff = new byte[8 * 1024];
                    int len;
                    while ((len = zipInputStream.read(buff)) > 0) {
                        baos.write(buff, 0, len);
                    }
                    zipInputStream.closeEntry();
                    buffer.put(file, baos);
                }
                zipEntry = zipInputStream.getNextEntry();
            }
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {
            for (Map.Entry<String, ByteArrayOutputStream> pair : buffer.entrySet()) {
                zipOutputStream.putNextEntry(new ZipEntry(pair.getKey()));
                zipOutputStream.write(pair.getValue().toByteArray());
                zipOutputStream.closeEntry();
            }


            zipOutputStream.putNextEntry(new ZipEntry("new\\" + filename.getFileName()));
            Files.copy(filename, zipOutputStream);
            zipOutputStream.closeEntry();
        }

    }
}
