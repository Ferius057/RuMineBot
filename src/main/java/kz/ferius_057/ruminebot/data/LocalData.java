package kz.ferius_057.ruminebot.data;

import kz.ferius_057.ruminebot.database.tool.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;

/**
 * @author Charles_Grozny
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
public class LocalData {
    HashMap<Integer, User> users = new HashMap<>();
}