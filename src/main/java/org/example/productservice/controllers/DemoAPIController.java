package org.example.productservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sample")
public class DemoAPIController {
//    @DeleteMapping("/")
    @GetMapping("/sayHello/{name}")
    public String sampleSayHello(@PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping("/sayHello2")
    public String sampleSayHello2(@RequestParam int x) {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello World 2!!!\n".repeat(Math.max(0, x)));
        return sb.toString();
    }
}
