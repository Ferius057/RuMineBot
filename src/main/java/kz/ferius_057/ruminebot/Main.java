package kz.ferius_057.ruminebot;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import kz.ferius_057.ruminebot.data.Config;
import kz.ferius_057.ruminebot.database.Commands;
import kz.ferius_057.ruminebot.database.Data;
import kz.ferius_057.ruminebot.database.Manager;
import kz.ferius_057.ruminebot.longpoll.CallbackApiLongPollHandler;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException, ClientException, ApiException {
        /*

        Данный коммит для сохранения исходников,
        Было добавлено бд, архитектура будет переделана.


        * */

        new Config().fileDataConfig();

        new Manager().open();

        Commands.getChats();

/*
        if (Commands.checkTable("peerIds")) {
            Commands.createTable("peerIds");
        }
        if (Commands.checkTable("users")) {
            Commands.createTable("users");
        }
        if (Commands.checkTable("users_data")) {
            Commands.createTable("users_data");
        }

        VkData vkData = new VkData();
        CallbackApiLongPollHandler handler = new CallbackApiLongPollHandler(vkData.getVk(), vkData.getActor());
        handler.run();*/
    }
}