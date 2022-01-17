package me.astoria.util.wrapper;

import me.astoria.util.http.HttpClient;
import me.astoria.util.http.hypixel.HypixelHttpClient;

public class HypixelAPI {
    private String key;
    private Boolean keyValid = false;

    public HypixelAPI(String key) {
        this.key = key;
    }

    public Boolean validKey() {
        Integer keyStatus = HypixelHttpClient.validKey(key);
        switch(keyStatus) {
            case(0): { // API key is valid.
                keyValid = true;
            }
            case(1): { // API key was invalid, due to not being correct.
                keyValid = false;
            }
            case(2): { // API key was invalid, as the player is not the key owner.
                keyValid = false;
            }
            case(3): { // API key was invalid, due to other causes.
                keyValid = false;
            }
        }
        return keyValid;
    }
}
