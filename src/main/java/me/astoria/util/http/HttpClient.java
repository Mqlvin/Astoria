package me.astoria.util.http;


import me.astoria.log.Logger;
import me.astoria.log.Severity;

import java.net.URL;
import java.util.Scanner;

public class HttpClient {
    public static String request(String url_) {
        try {
            URL url = new URL(url_);
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder builder = new StringBuilder();
            while(scanner.hasNext()) {
                builder.append(scanner.next());
            }
            return builder.toString().replaceAll("<[^>]*>", "");
        } catch(Exception e) {
            Logger.report("An exception was thrown while requesting from the URL " + url_ + ". " + e, Severity.WARN);
            return null;
        }
    }
}
