package org.ai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestEndPoint {

    @GetMapping("/consume-rest")
    @ResponseBody
    public List<Object> consumeRest() throws InterruptedException {

        List<HttpResponse> responses = new ArrayList<>();

        Disposable subscribe = Flux.just(
                new HttpConnector("http://localhost:8081/ping1"),
                new HttpConnector("http://localhost:8081/ping2"),
                new HttpConnector("http://localhost:8081/ping3")
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
        while (!subscribe.isDisposed() && count < 4) {
            Thread.sleep(1000);
            count++;
            System.out.println("Waiting......");
        }

        System.out.println("All responses received");
        for (HttpResponse respons : responses) {
            System.out.println("response = " + respons.body());
        }

        return responses.stream().map( s -> {return s.body();}).collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/hello")
    public String sayHello() {
        return "Hi Hello...";
    }

}
