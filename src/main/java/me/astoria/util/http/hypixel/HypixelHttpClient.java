package me.astoria.util.http.hypixel;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.astoria.log.Logger;
import me.astoria.log.Severity;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HypixelHttpClient {
    public static void getData() {

    }

    public static Integer validKey(String key) {
        try {
            URL url = new URL("https://api.hypixel.net/key?key=" + key);
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder builder = new StringBuilder();
            while(scanner.hasNext()) {
                builder.append(scanner.next());
            }
            JsonObject response = new JsonParser().parse(builder.toString().replaceAll("<[^>]*>", "")).getAsJsonObject();
            System.out.println(response);
            System.out.println(response.get("success").toString());
            if(!response.has("record")) { // not parsing response into json just to find one value
                if(response.get("record").getAsJsonObject().get("owner").toString().equals("a")) {
                    return 0;
                }
                return 2;
            }
        } catch(Exception e) {
            Logger.report("Error while validating API key: " + e, Severity.WARN);
            if(e.getMessage().contains("403")) {
                return 1;
            }
        }
        return 3;
    }
}
