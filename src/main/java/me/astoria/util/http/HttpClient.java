/*
--------------------------------------------------------------------
Copyright (C) 2021-2022 by Mqlvin | Contact: %%license_contact%%
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
--------------------------------------------------------------------
Description: Requests data from a page via the HTTP protocol.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.2
--------------------------------------------------------------------
*/

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
