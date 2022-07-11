package kz.ferius_057.ruminebot.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.val;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class Config {
    int groupId;
    String token, fileNameDataBase;

    public static Config load(final Path path) throws IOException {
        val properties = new Properties();

        if (Files.exists(path)) {
            try (val input = Files.newInputStream(path)) {
                properties.load(input);
            }
        } else {
            properties.setProperty("id_group", "1");
            properties.setProperty("token", "");
            properties.setProperty("fileNameDataBase", "");

            try (val os = Files.newOutputStream(path)) {
                properties.store(os, "Config Data");
            }
        }

        return new Config(
                Integer.parseInt(properties.getProperty("id_group")),
                properties.getProperty("token"),
                properties.getProperty("fileNameDataBase")
        );
    }
}