package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        long count = 0;

        File file = new File("src/main/resources/" + path);

        File[] files = file.listFiles();
        for(File f : files) {
            if(f.isDirectory()) {
                count += countFilesInDirectory(path + "/" + f.getName());
            } else {
                count++;
            }
        }

        return count;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        long count = 1;

        File file = new File("src/main/resources/" + path);
        File[] files = file.listFiles();

        for(File dir : files) {
            if(dir.isDirectory()) {
                count += countDirsInDirectory(path + "/" + dir.getName());
            }
        }

        return count;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File copyFrom = new File("src/main/resources/" + from);
        File copyTo = new File("src/main/resources/" + to);

        File[] files = copyFrom.listFiles();
        for(File f : files) {
            if(f.isFile() && f.toPath().endsWith(".txt")) {
                try {
                    Files.copy(f.toPath(), copyTo.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        File dir = new File("src/main/resources/" + path);
        File file = new File(dir, name);

        boolean dirExists = dir.mkdir();
        boolean result = false;
        if(dirExists) {
            try {
                result = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + fileName));
            while(reader.ready()) {
                result += reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
