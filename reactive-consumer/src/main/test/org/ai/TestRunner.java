package org.ai;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    @Test
    public void testRun() throws InterruptedException {

        List<CloseableHttpResponse> responses = new ArrayList<>();

        Disposable subscribe = Flux.just(
                new HttpConnector("http://localhost:8080/ping1"),
                new HttpConnector("http://localhost:8080/ping2"),
                new HttpConnector("http://localhost:8080/ping3")
        ).flatMap(n -> Mono.just(n)
                .map(p -> {
                    return p.callGet();
                })
                .subscribeOn(Schedulers.parallel())
        ).subscribe(
                stringHttpResponse -> {
                    responses.add(stringHttpResponse);
                });

        int count = 0;
        while (!subscribe.isDisposed() && count < 10) {
            Thread.sleep(1000);
            count++;
            System.out.println("Waiting......");
        }

        System.out.println("All responses received");
        for (CloseableHttpResponse response : responses) {
            System.out.println("response = " + response.getStatusLine());
        }


    }

}
