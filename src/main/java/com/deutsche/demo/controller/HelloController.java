package com.deutsche.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(String[] args) {
        System.out.println("Hello");
        return "Hello World!";
    }

    @RequestMapping("hii")
    public String hii(String[] args) {
        System.out.println("Hi");
        return "Hii World!";
    }
}
