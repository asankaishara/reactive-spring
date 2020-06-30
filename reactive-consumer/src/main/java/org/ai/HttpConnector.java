package org.ai;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpConnector {

    private String url;
    CloseableHttpClient httpClient = HttpClients.createDefault();

    public HttpConnector(String url) {
        this.url = url;
    }

    public CloseableHttpResponse callGet() {
        try {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(request);
            System.out.println("Response received from endpoint:["+url+"]");
            return response;
        } catch (Exception e) {
            System.out.println("e = " + e);
            return null;
        }
    }
}
