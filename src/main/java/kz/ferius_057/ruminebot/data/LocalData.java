package kz.ferius_057.ruminebot.data;

import java.util.HashMap;

/**
 * @author Charles_Grozny
 */
public class LocalData {
    private HashMap<Integer, Long> registerPeerIdCooldown = new HashMap<>();

    public HashMap<Integer, Long> getRegisterPeerIdCooldown() {
        return registerPeerIdCooldown;
    }

    public void setRegisterPeerIdCooldown(int key, long value) {
        registerPeerIdCooldown.put(key, value);
    }
}