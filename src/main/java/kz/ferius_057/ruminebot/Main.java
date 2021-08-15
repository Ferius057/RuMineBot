package kz.ferius_057.ruminebot;

import kz.ferius_057.ruminebot.longpoll.CallbackApiLongPollHandler;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, ClientException, ApiException {
        fileDataConfig();

        VkData vkData = new VkData();
        CallbackApiLongPollHandler handler = new CallbackApiLongPollHandler(vkData.getVk(), vkData.getActor());
        handler.run();
    }

    public static void fileDataConfig() throws IOException {
        Properties properties = new Properties();
        File file = new File("config.yml");

        if (!file.exists()) {
            properties.setProperty("id_group","");
            properties.setProperty("token","");
            properties.store(new FileOutputStream(file),"Config Data");
        } else {
            properties.load(new FileInputStream(file));
            try {
                new VkData().setActor(new GroupActor(Integer.parseInt(properties.getProperty("id_group")), properties.getProperty("token")));
            } catch (Exception e) {
                System.err.println("Перепроверьте конфиг.");
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
}