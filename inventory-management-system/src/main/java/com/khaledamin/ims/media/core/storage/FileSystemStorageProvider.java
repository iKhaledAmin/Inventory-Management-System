package com.khaledamin.ims.media.core.storage;

import com.khaledamin.ims.media.core.properties.StorageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

@Service
@RequiredArgsConstructor
public class FileSystemStorageProvider implements StorageProvider {

    private final StorageProperties storageProperties;

    @Override
    public void store(String storageKey, InputStream content) {

        Path path = resolve(storageKey);

        try {

            Files.createDirectories(path.getParent());

            Files.copy(
                    content,
                    path,
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException exception) {

            throw new RuntimeException(
                    "Failed to store file: " + storageKey,
                    exception
            );
        }
    }

    @Override
    public InputStream load(String storageKey) {

        Path path = resolve(storageKey);

        try {

            return Files.newInputStream(path);

        } catch (IOException exception) {

            throw new RuntimeException(
                    "Failed to load file: " + storageKey,
                    exception
            );
        }
    }

    @Override
    public void delete(String storageKey) {

        Path path = resolve(storageKey);

        try {

            Files.deleteIfExists(path);

            removeEmptyParents(path.getParent());

        } catch (IOException exception) {

            throw new RuntimeException(
                    "Failed to delete file: " + storageKey,
                    exception
            );
        }
    }

    @Override
    public boolean exists(String storageKey) {

        return Files.exists(resolve(storageKey));
    }

    private Path resolve(String storageKey) {

        return Paths.get(
                storageProperties.imagesRoot(),
                storageKey
        );
    }

    private void removeEmptyParents(Path directory) {

        Path storageRoot = Paths.get(storageProperties.imagesRoot())
                .toAbsolutePath()
                .normalize();

        while (directory != null
                && !directory.toAbsolutePath().normalize().equals(storageRoot)) {

            try {

                if (!Files.isDirectory(directory)) {
                    return;
                }

                try (DirectoryStream<Path> stream =
                             Files.newDirectoryStream(directory)) {

                    if (stream.iterator().hasNext()) {
                        return;
                    }
                }

                Files.delete(directory);

                directory = directory.getParent();

            } catch (IOException exception) {

                return;
            }
        }
    }
}