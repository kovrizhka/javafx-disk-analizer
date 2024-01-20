package ru.kovrizhkin.study.javafxdiskanalyzer;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class AnalyzerLogic {

    private HashMap<String, Long> sizes;

    public Map<String, Long> calculateDirectorySize(Path path) {
        try {
             sizes = new HashMap<>();
            Files.walkFileTree(
                    path,
                    new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            long size = Files.size(file);
                            updateDirectorySize(file, size);
                            return super.visitFile(file, attrs);
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException exc) {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                    });

            return sizes;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateDirectorySize(Path file, Long size) {
        String key = file.toString();
        sizes.put(key, size + sizes.getOrDefault(key, 0L));

        Path parent = file.getParent();

        if(parent != null) {
            updateDirectorySize(file, size);
        }

    }

}
