package me.astoria.api.hypixel;

import me.astoria.util.http.HttpClient;
import me.astoria.util.http.HttpResponse;

public class HypixelAPI {
    private String key;
    private Integer keyStatus = 0;
    /*
    keyStatus codes:
    0: Undetermined
    1: Valid
    2: Invalid
    3: Valid but not owned by UUID TODO
    4: Other
     */

    public HypixelAPI(String key, Boolean overrideAuth) {
        this.key = key;
        if(overrideAuth) {
            keyStatus = 1;
        } else {
            HttpResponse response = HttpClient.request("https://api.hypixel.net/key?key=" + key);
            if(response.response.startsWith("java.io.IOException: ")) {
                keyStatus = 2;
            } else if(response.response.startsWith("{") && response.response.contains("record")) { // todo: check key owner vs uuid. parse json and check this using gson
                keyStatus = 1;
            } else {
                keyStatus = 4;
            }
        }
    }

    public Integer getKeyStatus() {
        return keyStatus;
    }

    public String getKey() {
        return key;
    }
}
