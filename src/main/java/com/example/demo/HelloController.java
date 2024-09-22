package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello() {
        LOGGER.info("info");

        return "hello";
    }

    @GetMapping("/exception")
    public String exception() {
        throw new IllegalArgumentException("This id is invalid");
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<String> handleConflict(IllegalArgumentException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
