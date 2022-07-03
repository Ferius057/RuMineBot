package kz.ferius_057.ruminebot.data;

import kz.ferius_057.ruminebot.database.tool.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * @author Charles_Grozny
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
public class LocalData {
    HashMap<Integer, User> users = new HashMap<>();



    @Setter
    @NonFinal
    long timeStartMs;

    @Getter(lazy = true)
    private String timeStart = makeTextTime();

    private String makeTextTime() {
        long timeStartMs = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        return simpleDateFormat.format(new Date(timeStartMs));
    }
}