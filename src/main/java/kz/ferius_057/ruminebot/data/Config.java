package kz.ferius_057.ruminebot.data;

import com.vk.api.sdk.client.actors.GroupActor;
import kz.ferius_057.ruminebot.VkData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static String fileNameDataBase;

    public void fileDataConfig() throws IOException {
        Properties properties = new Properties();
        File file = new File("config.yml");

        if (!file.exists()) {
            properties.setProperty("id_group", "");
            properties.setProperty("token", "");
            properties.setProperty("fileNameDataBase", "");
            properties.store(new FileOutputStream(file), "Config Data");
            System.out.println("Создан config.yml, настройте конфигурации.");
            System.exit(0);
        } else {
            properties.load(new FileInputStream(file));
            try {
                new VkData().setActor(new GroupActor(Integer.parseInt(properties.getProperty("id_group")), properties.getProperty("token")));
                setFileNameDataBase(properties.getProperty("fileNameDataBase"));
                if (properties.getProperty("fileNameDataBase").equals("")) {
                    System.err.println("Установите название файла базы данных.");
                    System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Перепроверьте конфиг.");
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    public static String getFileNameDataBase() {
        return fileNameDataBase;
    }

    public static void setFileNameDataBase(String fileNameDataBase) {
        Config.fileNameDataBase = fileNameDataBase;
    }
}