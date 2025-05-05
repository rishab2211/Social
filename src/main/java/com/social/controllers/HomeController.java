package com.social.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

@GetMapping
    public String homeControllerHandler(){
    return "This is home page backend response";
}

@GetMapping("/home")
    public String homeController2(){
    return "Testing routing witn spring-boot-java";
}

}
