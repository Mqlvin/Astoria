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
