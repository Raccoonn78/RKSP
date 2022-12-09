package org.example.task2;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Реализация копирования файла 4 методами с замерами по времени и памяти.
 *java-4course-main\pr2-2\src\main\java\org\example\task2\bmi.txt
 C:\Users\Дмитрий\Desktop\IDEA\java-4course-main\pr2-2\src\main\java\org\example\task2\bmi.txt
 * @author - mironov Z.T. IKBO-20-19 on 09.10.2022 - 22:01
 */
public class Main {
    static final long FILE_SIZE = 1024 * 1024;
    static final String SOURCE_PATH = "C:\\Users\\Дмитрий\\Desktop\\IDEA\\java-4course-main\\pr2-2\\src\\main\\java\\org\\example\\task2\\bmi.txt";
    static final String DEST_PATH = "C:\\Users\\Дмитрий\\Desktop\\123qweasd.txt";

    public static void main(String[] args) throws IOException {
        List<CopyingFile> copyFileService = List.of(
            new CopyFileWithFileInputAndOutputStreams(),
            new CopyFileWithFIleChannel(),
            new CopyFIleWithApacheCommonsIO(),
            new CopyFileWithFilesClassInNIO2()
        );

        createSourceFile();
        for (CopyingFile service : copyFileService) {
            recreateDestFile();

            long startTime= System.currentTimeMillis(), totalMemory = Runtime.getRuntime().totalMemory();

            service.execute(SOURCE_PATH, DEST_PATH);

            long endTime = System.currentTimeMillis() - startTime, freeMemory = Runtime.getRuntime().freeMemory();

            System.out.printf("[%s] Время выполнения программы: %s ms%n", service.getClass().getSimpleName(), endTime);
            System.out.printf("[%s] Затраты по памяти: %s bytes %n", service.getClass().getSimpleName(), totalMemory - freeMemory);
        }
    }

    static void createSourceFile() throws IOException {
        Path sourcePath = Paths.get(SOURCE_PATH);
        if (Files.exists(sourcePath)) {
            File file = new File(SOURCE_PATH);
//            RandomAccessFile rw = new RandomAccessFile(file, "rw");
//            rw.setLength(FILE_SIZE);
//            rw.close();
            System.out.printf("Был создан файл с размером %s МБ \n", (double) file.length() / (1024 * 1024));
        }
    }

    static void recreateDestFile() throws IOException {
        Path destPath = Paths.get(DEST_PATH);
        if (Files.exists(destPath)) {
            Files.delete(destPath);
            System.out.println("Идет пересоздание файла для копирования");
            Files.createFile(destPath);
        }
    }
}
