package kz.ferius_057.ruminebot.data;

import com.google.gson.Gson;
import kz.ferius_057.ruminebot.object.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.val;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * @author Charles_Grozny
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
public class LocalData {

    Gson gson = new Gson();

    HashMap<Integer, User> users = new HashMap<>(); // TODO: 04.07.2022 | сделать что бы обновлялось

    @Setter
    @NonFinal
    long timeStartMs;

    @Getter(lazy = true)
    private String timeStart = makeTextTime();

    private String makeTextTime() {
        val timeStartMs = System.currentTimeMillis();
        val simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        return simpleDateFormat.format(new Date(timeStartMs));
    }

}