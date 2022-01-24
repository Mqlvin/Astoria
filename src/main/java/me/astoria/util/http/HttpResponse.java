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
Description: Object which holds data from a requests URL.
Authors: [Mqlvin/melvinkelvin#6328]
Modified: b0.2
--------------------------------------------------------------------
*/

package me.astoria.util.http;

public class HttpResponse {
    public String response;

    public HttpResponse(String response) {
        this.response = response;
    }
}

/*
Why is this object a thing? I might add status codes and other information about the request as time goes on, so I'm just trying to be prepared.
 */
