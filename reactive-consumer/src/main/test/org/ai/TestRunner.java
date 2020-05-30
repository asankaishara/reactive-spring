package org.ai;

import org.junit.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestRunner {

    //@Test
    public void createAFluxFromArray() throws InterruptedException {
        /*String[] fruits = new String[] {
                "Apple", "Orange", "Grape", "Banana", "Strawberry" };
        Flux<String> fruitFlux = Flux.fromArray(fruits);

        fruitFlux
                .map(String::toUpperCase)
                .subscribeOn(Schedulers.parallel())
                .subscribe(System.out::println);
        Thread.sleep(10);*/

        //fruitFlux.map(f -> {return f.toUpperCase();}).subscribe(System.out::println);

        /*Flux<String> stringFlux = fruitFlux.flatMap(
                fm -> {
                    return Mono.just(fm).map(
                            m -> {
                                return m.toLowerCase();
                            }
                    ).subscribeOn(Schedulers.parallel());
                }
        );*/
        //stringFlux.subscribe(s -> System.out.println(s));

        /*Flux<String> playerFlux = Flux
                .just("1.Michael Jordan", "2.Scottie Pippen", "3.Steve Kerr")
                .flatMap(n -> Mono.just(n)
                        .map(p -> {
                            return p.toUpperCase();
                        })
                        .subscribeOn(Schedulers.parallel())
                );

        List<String> playerList = Arrays.asList(
                "1.MICHAEL JORDAN", "2.SCOTTIE PIPPEN", "3.STEVE KERR");
        StepVerifier.create(playerFlux)
                .expectNextMatches(p -> playerList.contains(p))
                .expectNextMatches(p -> playerList.contains(p))
                .expectNextMatches(p -> playerList.contains(p))
                .verifyComplete();*/



        /*Flux.just("red", "white", "blue")
                .map(String::toUpperCase)
                .subscribeOn(Schedulers.parallel())
                .subscribe(s -> System.out.println(s));
        Thread.sleep(10);*/
    }


    @Test
    public void testRun() throws InterruptedException {

        List<HttpResponse> responses = new ArrayList<>();

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
                    //System.out.println(stringHttpResponse.body());
                    responses.add(stringHttpResponse);
                });

        int count = 0;
        while (!subscribe.isDisposed() && count < 5) {
            Thread.sleep(1000);
            count++;
            System.out.println("Waiting......");
        }

        System.out.println("All responses received");
        for (HttpResponse respons : responses) {
            System.out.println("response = " + respons.body());
        }


    }

}
