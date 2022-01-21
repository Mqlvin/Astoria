package me.astoria.util.http;


import me.astoria.log.Logger;
import me.astoria.log.Severity;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class HttpClient {
    public static HttpResponse request(String url_) {
        try {
            URL url = new URL(url_);
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder builder = new StringBuilder();
            while(scanner.hasNext()) {
                builder.append(scanner.next());
            }
            return new HttpResponse(builder.toString().replaceAll("<[^>]*>", ""));
        } catch(IOException e) {

            Logger.report("An exception was thrown during HTTP request. " + e, Severity.WARN);
            return new HttpResponse(e.toString());
        }
    }
}
