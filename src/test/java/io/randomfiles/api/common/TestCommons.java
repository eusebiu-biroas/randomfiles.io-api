package io.randomfiles.api.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TestCommons {

    public static int countFilesInZip(ByteArrayOutputStream file) throws IOException {
        int count = 0;
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(file.toByteArray()));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            count++;
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        return count;
    }
}
