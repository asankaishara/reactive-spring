package org.ai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @GetMapping("/ping1")
    @ResponseBody
    public String pingResponse() throws InterruptedException {
        Thread.sleep(5000);
        return "pong1";
    }

    @GetMapping("/ping2")
    @ResponseBody
    public String ping2Response() throws InterruptedException {
        Thread.sleep(2000);
        return "pong2";
    }

    @GetMapping("/ping3")
    @ResponseBody
    public String ping3Response() throws InterruptedException {
        Thread.sleep(1000);
        return "pong3";
    }

}
