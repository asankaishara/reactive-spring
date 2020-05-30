package org.ai;

public class Tests {

    //@Test
    /*public void createAFluxFromArray() throws InterruptedException {
        String[] fruits = new String[] {
                "Apple", "Orange", "Grape", "Banana", "Strawberry" };
        Flux<String> fruitFlux = Flux.fromArray(fruits);

        fruitFlux
                .map(String::toUpperCase)
                .subscribeOn(Schedulers.parallel())
                .subscribe(System.out::println);
        Thread.sleep(10);

        //fruitFlux.map(f -> {return f.toUpperCase();}).subscribe(System.out::println);

        Flux<String> stringFlux = fruitFlux.flatMap(
                fm -> {
                    return Mono.just(fm).map(
                            m -> {
                                return m.toLowerCase();
                            }
                    ).subscribeOn(Schedulers.parallel());
                }
        );
        stringFlux.subscribe(s -> System.out.println(s));

        Flux<String> playerFlux = Flux
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
                .verifyComplete();



        Flux.just("red", "white", "blue")
                .map(String::toUpperCase)
                .subscribeOn(Schedulers.parallel())
                .subscribe(s -> System.out.println(s));
        Thread.sleep(10);
    }*/
}
