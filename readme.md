## This project demonstrate consuming reset service in reactive manner

### `rest-endpoint` will expose 3 simple web service endpoints

```
    http://localhost:8080/ping1 -> 5 secounds wait for response
    http://localhost:8080/ping2 -> 2 secounds wait for response
    http://localhost:8080/ping3 -> 1 secounds wait for response
```

### `reactive-consumer` will consume this 3 services asynchronously in reactive way

 ``HttpConnector`` will make http call in given URL
 
 ``TestRunner`` will create 3 connections with Flux and subscribe to ``callGet`` parallel.
 
 while loop wait until all the results become available and print responses from the endpoints 
